package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.domain.Orga;
import com.zhyxcs.xxzz.domain.User;
import com.zhyxcs.xxzz.mapper.OrgaMapper;
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

    @RequestMapping(value = "/userWithBankType", method = RequestMethod.GET)
    public List<HashMap> getUsersByAddUserCodeAndBankType(@RequestParam(value = "addUserCode") String addUserCode,
                                                          @RequestParam(value = "bankTypeCode") String bankTypeCode){

        return userService.selectBysAddUserCodeAndBankType(addUserCode, bankTypeCode);
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

    @RequestMapping(value = "/pwd", method = RequestMethod.POST)
    public HashMap changePassword(@RequestParam(value = "oldPwd") String oldPwd,
                                  @RequestParam(value = "newPwd") String newPwd){
        HashMap map = new HashMap();

        HttpSession session = super.request.getSession();
        User cur_user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);

        if (oldPwd != null & newPwd != null){
            String md5Password = CommonUtils.MD5(oldPwd);

            if (md5Password.equals(cur_user.getSpassword())){
                String newMd5Password = CommonUtils.MD5(newPwd);
                cur_user.setSpassword(newMd5Password);

                userService.updateByPrimaryKey(cur_user);

                map.put("status", "success");
                map.put("message", "密码修改成功");
            } else {
                map.put("status", "failed");
                map.put("message", "旧密码不正确！");
            }

        } else {
            map.put("status", "failed");
            map.put("message", "密码不能为空！");
        }

        return map;
    }

    @RequestMapping(value = "/originPwd", method = RequestMethod.POST)
    public HashMap resetPassword(@RequestParam(value = "userCode") String userCode) {
        HashMap map = new HashMap();
        User user = userService.selectByPrimaryKey(userCode);

        if (user != null) {
            HttpSession session = super.request.getSession();
            User cur_user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);

            String addUsercode = user.getSaddusercode();

            //判断该用户是否属于该人行所辖
            if ( addUsercode != null && cur_user.getSusercode().equals(addUsercode)){
                String md5Password = CommonUtils.MD5("crams888");
                user.setSpassword(md5Password);
                user.setSpwderror(Byte.valueOf("0"));

                userService.updateByPrimaryKey(user);

                map.put("status", "success");
                map.put("message", "密码重置成功！！");
            } else {
                map.put("status", "failed");
                map.put("message", "没有权限操作该用户！！");
            }

        } else {
            map.put("status", "failed");
            map.put("message", "用户不存在！！");
        }

        return map;
    }
}
