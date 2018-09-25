package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.config.ImageConfig;
import com.zhyxcs.xxzz.domain.Image;
import com.zhyxcs.xxzz.domain.User;
import com.zhyxcs.xxzz.service.ImageService;
import com.zhyxcs.xxzz.utils.CommonUtils;
import com.zhyxcs.xxzz.utils.CramsConstants;
import com.zhyxcs.xxzz.utils.Logs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/api/image")
public class ImageController extends BaseController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageConfig imageConfig;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //公平锁
    private Lock lock = new ReentrantLock(true);

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public Long insertNewImage(@RequestParam (value = "transactionNum") String transactionNum,
                              @RequestParam (value = "imageType") String imageType,
                              @RequestParam (value = "proofName") String proofName,
                              @RequestParam (value = "imageOriginName") String imageOriginName,
                              @RequestParam (value = "imageSN") String imageSN,
                              @RequestParam (value = "imageFile") MultipartFile imageFile) {

        HttpSession session = super.request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);

        String basePath = imageConfig.getBasePath();
        String bankCodePath = user.getSbankcode();

        Date currentDate = CommonUtils.newDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyMMdd");

        String datePath = format.format(currentDate);
        String imageName = String.valueOf(UUID.randomUUID());

        //文件相对路径，存数据库
        String storePath = bankCodePath + File.separatorChar + datePath
                + File.separatorChar + imageName;

        //image文件存储真实路径
        String imageStorePath = basePath + storePath;


        //文件上传
        File file = new File(imageStorePath);
        if (!file.exists()){

            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            try {
                imageFile.transferTo(file);

                Image image = new Image();
                image.setStransactionnum(transactionNum);
                image.setSimagetype(imageType);
                image.setSproofname(proofName);
                image.setSoriginalname(imageOriginName);
                image.setSimagesn(Short.valueOf(imageSN));
                image.setSimagename(imageName);
                image.setSstorepath(storePath);

                lock.lock();
                imageService.insert(image);
                lock.unlock();

                this.writeLog(Logs.IMAGE_UPLOAD);
                return image.getSid();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return Long.valueOf(0);
    }

    @RequestMapping(value = "/image", method = RequestMethod.DELETE)
    public int deleteImage(@RequestParam(value = "sID") Long sID){
        Image image = imageService.selectByPrimaryKey(sID);

        if (image != null){
            String path = image.getSstorepath();
            String basePath = imageConfig.getBasePath();
            File file = new File(basePath + path);

            if (file != null && file.delete()){
                imageService.deleteByPrimaryKey(sID);
                this.writeLog(Logs.IMAGE_DELETE);
                return 1;
            }
        }

        return 0;
    }

    @RequestMapping(value = "/images", method = RequestMethod.GET)
    public List<Image> getImagesByTranID(@RequestParam(value = "stransactionnum") String sTransactionNum){
        return imageService.selectImagesByTranID(sTransactionNum);
    }

//    @RequestMapping(value = "/image64", method = RequestMethod.GET)
//    public Map getBase64Image(@RequestParam(value = "path") String path){
//        String basePath = imageConfig.getBasePath();
//        String base64 = this.encryptToBase64(basePath + path);
//        HashMap map = new HashMap();
//        map.put("src", base64);
//        return map;
//    }

    @RequestMapping(value = "/image64", method = RequestMethod.GET)
    public void getBase64Image(@RequestParam(value = "path") String path, HttpServletResponse response){
        String basePath = imageConfig.getBasePath();

        CommonUtils.downloadImage(basePath + path, response);
    }

    // 文件转base64
    private String encryptToBase64(String filePath) {
        if (filePath == null) {
            return null;
        }
        try {
            byte[] b = Files.readAllBytes(Paths.get(filePath));
            return Base64.getEncoder().encodeToString(b);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
