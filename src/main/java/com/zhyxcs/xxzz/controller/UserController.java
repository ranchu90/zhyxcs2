package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.domain.User;
import com.zhyxcs.xxzz.service.UserService;
import com.zhyxcs.xxzz.utils.CommonUtils;
import com.zhyxcs.xxzz.utils.CramsConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<HashMap> getUsersByAddUserCode(@RequestParam(value = "addUserCode") String addUserCode){

        return userService.selectBysAddUserCode(addUserCode);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public int addUsersByAddUserCode(@RequestBody User user){

        String md5Password = CommonUtils.MD5("crams888");
        user.setSpassword(md5Password);

        HttpSession session = super.request.getSession();
        User cur_user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);

        user.setSaddusercode(cur_user.getSusercode() );

        return userService.insert(user);
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    public int updateBasicByPrimaryKey(@RequestBody User user){

        return userService.updateBasicByPrimaryKey(user);
    }
}
