package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.config.WebSecurityConfig;
import com.zhyxcs.xxzz.domain.Orga;
import com.zhyxcs.xxzz.domain.User;
import com.zhyxcs.xxzz.service.OrgaService;
import com.zhyxcs.xxzz.service.UserService;
import com.zhyxcs.xxzz.utils.CommonUtils;
import com.zhyxcs.xxzz.utils.CramsConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrgaService orgaService;

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public Map loginVerify(@RequestBody User user, HttpSession session){
        User dbUser = userService.selectByPrimaryKey(user.getSusercode());
        Map result = new HashMap();

        if (dbUser != null){
            String md5Password = CommonUtils.MD5(user.getSpassword());
            if (md5Password.equals(dbUser.getSpassword())){
                Orga orga = orgaService.selectByPrimaryKey(dbUser.getSbankcode());
                session.setAttribute(CramsConstants.SESSION_LOGIN_USER, dbUser);
                session.setAttribute(CramsConstants.SESSION_ORGA_WITH_USER, orga);
                result.put("state", "success");
                result.put("token", "op");
            } else {
                result.put("state", "failed");
                result.put("message", "密码错误！");
            }
        } else {
            result.put("state", "failed");
            result.put("message", "用户不存在！");
        }

        return result;
    }

}
