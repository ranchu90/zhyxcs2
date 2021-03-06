package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.config.ImageConfig;
import com.zhyxcs.xxzz.domain.LicenceImage;
import com.zhyxcs.xxzz.domain.User;
import com.zhyxcs.xxzz.service.LicenceImageService;
import com.zhyxcs.xxzz.utils.CommonUtils;
import com.zhyxcs.xxzz.utils.CramsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
@RequestMapping("/api/licence")
public class LicenceImageController extends BaseController{
    @Autowired
    private ImageConfig imageConfig;

    @Autowired
    private LicenceImageService licenceImageService;

    //公平锁
    private Lock lock = new ReentrantLock(true);

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/licence", method = RequestMethod.POST)
    public int uploadLicenceImage(@RequestParam(value = "transactionNum") String transactionNum,
                                  @RequestParam(value = "approvalCode") String approvalCode,
                                  @RequestParam(value = "identifier") String identifier,
                                  @RequestParam(value = "businessCategory") String businessCategory,
                                  @RequestParam(value = "accountType") String accountType,
                                  @RequestParam(value = "bankCode") String bankCode,
                                  @RequestParam(value = "transactionNum") String bankName,
                                  @RequestParam (value = "licenceImage") MultipartFile licenceImage){

        HttpSession session = super.request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);

        String basePath = imageConfig.getLicenceBasePath();
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
                licenceImage.transferTo(file);

                LicenceImage image = new LicenceImage();
                image.setStransactionnum(transactionNum);
                image.setSaccounttype(accountType);
                image.setSapprovalcode(approvalCode);
                image.setSbankcode(bankCode);
                image.setSbankname(bankName);
                image.setSbusinesscategory(businessCategory);
                image.setSidentifier(identifier);
                image.setSlicenceimagename(imageName);
                image.setSstorepath(storePath);
                image.setSuploadusercode(user.getSusercode());
                image.setSuploadusername(user.getSusername());

                try {
                    lock.lock();
                    licenceImageService.insert(image);
                } catch (DuplicateKeyException e) {
                    licenceImageService.updateByPrimaryKey(image);
                } finally {
                    lock.unlock();
                }

                return 1;
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }

    @RequestMapping(value = "/licence", method = RequestMethod.DELETE)
    public int deleteLicenceImage(@RequestParam(value = "transactionNum") String transactionNum){
        LicenceImage image = licenceImageService.selectByPrimaryKey(transactionNum);

        if (image != null){
            String licenceBasePath = imageConfig.getLicenceBasePath();
            String path = image.getSstorepath();

            File file = new File(licenceBasePath + path);

            if (file != null && file.delete()){
                licenceImageService.deleteByPrimaryKey(transactionNum);
                return 1;
            }
        }

        return 0;
    }

    @RequestMapping(value = "/licence", method = RequestMethod.GET)
    public void getLicenceImage(@RequestParam(value = "transactionNum") String transactionNum, HttpServletResponse response){
        LicenceImage image = licenceImageService.selectByPrimaryKey(transactionNum);
        String licenceBasePath = imageConfig.getLicenceBasePath();

        if (image != null) {
            CommonUtils.downloadImage(licenceBasePath + image.getSstorepath(), response);
        }

//        Map map = new HashMap<String, Object>();
//
//        if (image!=null){
//            String base64 = this.encryptToBase64(licenceBasePath + image.getSstorepath());
//
//            map.put("src", base64);
//            map.put("approvalCode", image.getSapprovalcode()!=null? image.getSapprovalcode() : "");
//            map.put("identifier", image.getSidentifier()!=null? image.getSidentifier() : "");
//        } else {
//            map.put("src", null);
//        }
//        return map;
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
