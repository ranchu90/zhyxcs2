package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.config.WordConfig;
import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/api/download")
public class DownloadController {

    @Autowired
    private WordConfig wordConfig;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "/chrome", method = RequestMethod.GET)
    public void Download(HttpServletResponse res, @RequestParam(value = "type", required = false) String type) {
        String baseDirectory = wordConfig.getBaseDirectory();

        String fileName;

        if (type != null && "xp".equals(type.toLowerCase())){
            fileName = "chrome_installer_xp.exe";
        } else {
            fileName = "chrome_installer.exe";
        }

        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(baseDirectory
                    + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                i = bis.read(buff);
            }
        } catch (IOException e) {
            //如果是客户端断开连接，不记录异常
            if (!(e instanceof org.apache.catalina.connector.ClientAbortException)) {
                e.printStackTrace();
                logger.error("## Error Information ##: {DownloadController} #1", e);
            } else {
                System.out.println("客户端断开下载浏览器链接1");
            }
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("## Error Information ##: {DownloadController} #2", e);
                }
            }

            if (os != null){
                try {
                    os.flush();
                } catch (IOException e) {
                    //如果是客户端断开连接，不记录异常,不打印信息
                    if (!(e instanceof org.apache.catalina.connector.ClientAbortException)) {
                        e.printStackTrace();
                    } else {
                        System.out.println("客户端断开下载浏览器链接2");
                    }
                }
            }
        }
    }
}
