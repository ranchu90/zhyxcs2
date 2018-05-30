package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.domain.GroundsForReturn;
import com.zhyxcs.xxzz.service.GroundsForReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/grounds")
public class GroundsForReturnController extends BaseController {
    @Autowired
    private GroundsForReturnService groundsForReturnService;

    @RequestMapping(value = "/grounds", method = RequestMethod.GET)
    public List<GroundsForReturn> getAllGrounds(){
        return  groundsForReturnService.selectAll();
    }
}
