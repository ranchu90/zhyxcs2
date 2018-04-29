package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.domain.WorkIndex;
import com.zhyxcs.xxzz.service.WorkIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkIndexController {
    @Autowired
    private WorkIndexService workIndexService;
    
    @RequestMapping(value = "workIndex", method = RequestMethod.POST)
    public WorkIndex insert(@RequestBody WorkIndex workIndex){
        workIndexService.newWorkIndex(workIndex);
        return workIndex;
    }
}
