package com.zhyxcs.xxzz.controller.sv;

import com.zhyxcs.xxzz.controller.BaseController;
import com.zhyxcs.xxzz.domain.GroundsForReturn;
import com.zhyxcs.xxzz.domain.SupervisionGroundsForReturn;
import com.zhyxcs.xxzz.service.GroundsForReturnService;
import com.zhyxcs.xxzz.service.SVGroundsForReturnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/svgrounds")
public class SVGroundsForReturnController extends BaseController {
    @Autowired
    private SVGroundsForReturnService svGroundsForReturnService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/grounds", method = RequestMethod.GET)
    public List<SupervisionGroundsForReturn> getAllGrounds(){
        return  svGroundsForReturnService.selectAll();
    }
}
