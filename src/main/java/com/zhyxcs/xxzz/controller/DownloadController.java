package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.config.WordConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public void Download(HttpServletResponse res) {
        String baseDirectory = wordConfig.getBaseDirectory();

        String fileName = "chrome_installer.exe";
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
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("## Error Information ##: {}", e);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("## Error Information ##: {}", e);
                }
            }
        }
    }
}
