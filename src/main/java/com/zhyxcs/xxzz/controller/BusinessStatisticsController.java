package com.zhyxcs.xxzz.controller;


import com.zhyxcs.xxzz.service.BusinessStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class BusinessStatisticsController {
    @Autowired
    private BusinessStatisticsService businessStatisticsService;


}
