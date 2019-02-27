package com.zhyxcs.xxzz.controller.sv;

import com.zhyxcs.xxzz.config.ImageConfig;
import com.zhyxcs.xxzz.controller.BaseController;
import com.zhyxcs.xxzz.domain.*;
import com.zhyxcs.xxzz.service.ImageService;
import com.zhyxcs.xxzz.service.SVImageService;
import com.zhyxcs.xxzz.service.SupervisionService;
import com.zhyxcs.xxzz.service.WorkIndexService;
import com.zhyxcs.xxzz.utils.ActionType;
import com.zhyxcs.xxzz.utils.CommonUtils;
import com.zhyxcs.xxzz.utils.CramsConstants;
import com.zhyxcs.xxzz.utils.Logs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/api/svimage")
public class SVImageController extends BaseController {
    @Autowired
    private SVImageService svImageService;
    @Autowired
    private ImageConfig imageConfig;
    @Autowired
    private SupervisionService supervisionService;

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

        String basePath = imageConfig.getSvBasePath();
        String bankCodePath = user.getSbankcode();

        Date currentDate = CommonUtils.newDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        String datePath = format.format(currentDate);
        String yearMounthPath = datePath.substring(0, 6);
        String dayPath = datePath.substring(6);
        String imageName = String.valueOf(UUID.randomUUID());

        //文件相对路径，存数据库
//        String storePath = bankCodePath + File.separatorChar + datePath
//                + File.separatorChar + imageName;
        //年月-日-流水号
        String storePath = yearMounthPath + File.separatorChar + dayPath + File.separatorChar + transactionNum
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

                SupervisionImage image = new SupervisionImage();
                image.setStransactionnum(transactionNum);
                image.setSimagetype(imageType);
                image.setSproofname(proofName);
                image.setSoriginalname(imageOriginName);
                image.setSimagesn(Short.valueOf(imageSN));
                image.setSimagename(imageName);
                image.setSstorepath(storePath);

                lock.lock();
                svImageService.insert(image);
                this.writeLog(Logs.IMAGE_UPLOAD + " " + image.toString());
                return image.getSid();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        return Long.valueOf(0);
    }

    @RequestMapping(value = "/image", method = RequestMethod.DELETE)
    public int deleteImage(@RequestParam(value = "sID") Long sID){
        SupervisionImage image = svImageService.selectByPrimaryKey(sID);

        if (image != null){
            String workIndexNum = image.getStransactionnum();
            Supervision supervision = supervisionService.selectByPrimaryKey(workIndexNum);
            String state = supervision.getSapprovalstate();
            //只有编辑状态的可以删除
            if (ActionType.APPROVAL_STATE_COMMERCE_NEW.equals(state)) {
                String path = image.getSstorepath();
                String basePath = imageConfig.getBasePath();
                File file = new File(basePath + path);

                if (file != null && file.delete()) {
                    svImageService.deleteByPrimaryKey(sID);
                    this.writeLog(Logs.IMAGE_DELETE);
                    return 1;
                }
            } else {
                this.writeLog(Logs.IMAGE_DELETE_ILLEGAL + " " + workIndexNum);
            }
        }

        return 0;
    }

    @RequestMapping(value = "/images", method = RequestMethod.GET)
    public List<SupervisionImage> getImagesByTranID(@RequestParam(value = "stransactionnum") String sTransactionNum){
        return svImageService.selectImagesByTranID(sTransactionNum);
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
