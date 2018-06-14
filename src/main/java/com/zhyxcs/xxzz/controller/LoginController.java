package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.config.SessionListener;
import com.zhyxcs.xxzz.domain.Orga;
import com.zhyxcs.xxzz.domain.User;
import com.zhyxcs.xxzz.service.OrgaService;
import com.zhyxcs.xxzz.service.UserService;
import com.zhyxcs.xxzz.utils.CommonUtils;
import com.zhyxcs.xxzz.utils.CramsConstants;
import com.zhyxcs.xxzz.utils.Logs;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class LoginController extends BaseController{
    @Autowired
    private UserService userService;
    @Autowired
    private OrgaService orgaService;

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public Map loginVerify(@RequestBody User user){
        Map<String, HttpSession> loginUser = SessionListener.loginUser;
        Map result = new HashMap();

        if (loginUser.containsKey(user.getSusercode())){
            result.put("state", "failed");
            result.put("message", "用户已登陆！");
            result.put("code", 20000);
            this.writeLog(Logs.LOGIN_FAILED);

            return result;
        }

        User dbUser = userService.selectByPrimaryKey(user.getSusercode());
        HttpSession session = super.request.getSession();

//        System.out.println("login: " + session.getId());

        if (dbUser != null){
            if ("0".equals(dbUser.getSuserstate())) {
                if (dbUser.getSpwderror() < 5){
                    String md5Password = CommonUtils.MD5(user.getSpassword());
                    if (md5Password.equals(dbUser.getSpassword())) {
                        //获取机构信息
                        Orga orga = orgaService.selectByPrimaryKey(dbUser.getSbankcode());
                        //登陆成功，清楚错误密码次数
                        dbUser.setSpwderror((byte) 0);
                        userService.updateByPrimaryKey(dbUser);

                        session.setAttribute(CramsConstants.SESSION_LOGIN_USER, dbUser);
                        session.setAttribute(CramsConstants.SESSION_ORGA_WITH_USER, orga);
//                        session.setMaxInactiveInterval(5);

                        HashMap userMap = new HashMap();
                        userMap.put("username", dbUser.getSusername());
                        userMap.put("usercode", dbUser.getSusercode());
                        userMap.put("bankcode", dbUser.getSbankcode());
                        userMap.put("userstate", dbUser.getSuserstate());
                        userMap.put("userlevel", dbUser.getSuserlevel());
                        userMap.put("telephone", dbUser.getStelephone());
                        userMap.put("email", dbUser.getSemail());
                        userMap.put("bankAreaCode", orga.getSbankareacode());
                        userMap.put("bankCityCode", orga.getSbankcitycode());
                        userMap.put("userBankName", orga.getSbankname());

                        result.put("state", "success");
                        result.put("token", "op");
                        result.put("user", userMap);

                        this.writeLog(Logs.LOGIN_SUCCESS);

                    } else {
                        //记录密码错误次数
                        byte pwdErrorTimes = dbUser.getSpwderror();
                        pwdErrorTimes = (byte) (pwdErrorTimes + 1);
                        dbUser.setSpwderror(pwdErrorTimes);

                        userService.updateByPrimaryKey(dbUser);

                        result.put("state", "failed");
                        result.put("message", "密码错误！");

                        this.writeLog(Logs.LOGIN_FAILED);
                    }
                } else {
                    result.put("state", "failed");
                    result.put("message", "密码错误超过5次！");
                    this.writeLog(Logs.LOGIN_FAILED);
                }
            } else {
                result.put("state", "failed");
                result.put("message", "用户未启用！");
                this.writeLog(Logs.LOGIN_FAILED);
            }
        } else {
            result.put("state", "failed");
            result.put("message", "用户不存在！");
            this.writeLog(Logs.LOGIN_FAILED);
        }

        result.put("code", 20000);

        return result;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Map loginOut(HttpSession session){
        session.removeAttribute(CramsConstants.SESSION_ORGA_WITH_USER);
        session.removeAttribute(CramsConstants.SESSION_LOGIN_USER);

        Map result = new HashMap();
        result.put("state", "true");
        result.put("code", 20000);

        this.writeLog(Logs.LOGIN_LOGOUT);

        return result;
    }

    @RequestMapping(value = "/force_logout", method = RequestMethod.POST)
    public Map forceLoginOut(@Param("userCode") String userCode){
        Map<String, HttpSession> loginUser = SessionListener.loginUser;
        Map result = new HashMap();
        result.put("state", "true");
        result.put("code", 20000);

        HttpSession sessionOwn = super.request.getSession();
        User user = (User) sessionOwn.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        User tmpUSer = userService.selectByPrimaryKey(userCode);
        if (tmpUSer == null) {
            result.put("message", userCode + "用户不存在！");
        } else {
            Orga tmpOrga = orgaService.selectByPrimaryKey(tmpUSer.getSbankcode());
            String pbcCode = tmpOrga.getSpbcode();

            if (pbcCode.equals(user.getSbankcode()) || user.getSbankcode().equals(tmpUSer.getSbankcode())) {
                if (loginUser.containsKey(userCode)) {
                    HttpSession session = loginUser.get(userCode);
                    session.removeAttribute(CramsConstants.SESSION_ORGA_WITH_USER);
                    session.removeAttribute(CramsConstants.SESSION_LOGIN_USER);
                    loginUser.remove(userCode);
                    result.put("message", "已注销" + userCode + "用户！");
                } else {
                    result.put("message", userCode + "用户未登陆！");
                }
            } else {
                result.put("message", "无权操作该用户！");
            }
        }

        return result;
    }

}
