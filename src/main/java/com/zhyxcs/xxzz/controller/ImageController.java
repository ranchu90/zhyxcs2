package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.domain.Image;
import com.zhyxcs.xxzz.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "image", method = RequestMethod.POST)
    public int insertNewImage(@RequestBody Image record){
//        Date date = new Date();
//        record.setSuploadtime(date);
        return imageService.insert(record);
    }
}
