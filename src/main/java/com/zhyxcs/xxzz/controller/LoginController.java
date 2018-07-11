package com.zhyxcs.xxzz.controller;

import com.zhyxcs.xxzz.config.SessionListener;
import com.zhyxcs.xxzz.domain.Orga;
import com.zhyxcs.xxzz.domain.User;
import com.zhyxcs.xxzz.service.OrgaService;
import com.zhyxcs.xxzz.service.UserService;
import com.zhyxcs.xxzz.utils.CommonUtils;
import com.zhyxcs.xxzz.utils.CramsConstants;
import com.zhyxcs.xxzz.utils.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class LoginController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrgaService orgaService;

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public Map loginVerify(@RequestBody User user) {
        Map<String, HttpSession> loginUser = SessionListener.loginUser;
        Map result = new HashMap();
        String userCode = user.getSusercode();

        if (loginUser.containsKey(userCode)) {
            result.put("state", "failed");
            result.put("message", "用户已登陆！");
            result.put("code", 20000);
            this.writeLog(Logs.LOGIN_FAILED);

            return result;
        }

        User dbUser = userService.selectByPrimaryKey(user.getSusercode());
        HttpSession session = super.request.getSession();

//        System.out.println("login: " + session.getId());

        if (dbUser != null) {
            if ("0".equals(dbUser.getSuserstate())) {
                if (dbUser.getSpwderror() < 5) {
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
                        String userLevel = dbUser.getSuserlevel();

                        if (!"3".equals(userLevel) && !"6".equals(userLevel) && !"7".equals(userLevel)) {
                            //记录密码错误次数
                            byte pwdErrorTimes = dbUser.getSpwderror();
                            pwdErrorTimes = (byte) (pwdErrorTimes + 1);
                            dbUser.setSpwderror(pwdErrorTimes);

                            userService.updateByPrimaryKey(dbUser);
                        }

                        result.put("state", "failed");
                        result.put("message", "密码错误！");

                        this.writeLog(Logs.LOGIN_FAILED);
                    }
                } else {
                    result.put("state", "failed");
                    result.put("message", "密码错误超过5次！请联系管理员重置密码！");
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
    public Map loginOut() {
        HttpSession session = super.request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        session.removeAttribute(CramsConstants.SESSION_ORGA_WITH_USER);
        session.removeAttribute(CramsConstants.SESSION_LOGIN_USER);

        Map<String, HttpSession> loginUserMap = SessionListener.loginUser;
        if (loginUserMap.containsKey(user.getSusercode())) {
            loginUserMap.remove(user.getSusercode());
        }

        Map result = new HashMap();
        result.put("state", "true");
        result.put("code", 20000);

        this.writeLog(Logs.LOGIN_LOGOUT);

        return result;
    }

    @RequestMapping(value = "/force_logout", method = RequestMethod.POST)
    public Map forceLoginOut(@RequestParam("userCode") String userCode) {
        Map result = new HashMap();
        result.put("state", "true");
        result.put("code", 20000);

        HttpSession currentSession = request.getSession();
        User currentLoginUser = (User) currentSession.getAttribute(CramsConstants.SESSION_LOGIN_USER);

        User loginOutUser = userService.selectByPrimaryKey(userCode);

        if (loginOutUser == null) {
            result.put("message", userCode + "用户不存在！");
            return result;
        }

        Orga loginOutUserHasOrga = orgaService.selectByPrimaryKey(loginOutUser.getSbankcode());

        String currentLoginUserBankCode = currentLoginUser.getSbankcode();
        String loginOutUserPBCode = loginOutUserHasOrga.getSpbcode();
        String loginOutUserBankCode = loginOutUser.getSbankcode();
        //当前登录用户的机构类别
//        String currentLoginUserBankKind = currentLoginUserBankCode.substring(0, 1);


        //当前登录用户是人民银行用户
//        if ("0".equals(currentLoginUserBankKind)) {
//            if (currentLoginUserBankCode.equals(loginOutUserPBCode) ||
//                    currentLoginUserBankCode.equals(loginOutUserBankCode)) {
//                if (removeUserLoginInfo(userCode)) {
//                    result.put("message", "已注销" + userCode + "用户！");
//                } else {
//                    result.put("message", "注销" + userCode + "用户失败！");
//                }
//            }
//        } else {
//            if (currentLoginUserBankCode.equals(loginOutUserBankCode)) {
//                if (removeUserLoginInfo(userCode)) {
//                    result.put("message", "已注销" + userCode + "用户！");
//                } else {
//                    result.put("message", "注销" + userCode + "用户失败！");
//                }
//            }
//        }

        if (currentLoginUserBankCode.equals(loginOutUserPBCode) ||
                currentLoginUserBankCode.equals(loginOutUserBankCode)) {
            if (removeUserLoginInfo(userCode)) {
                result.put("message", "已注销" + userCode + "用户！");
            } else {
                result.put("message", userCode + "用户未登陆，无法注销！");
            }
        } else {
            result.put("message",  "无权操作用户" + userCode);
        }
        return result;
    }

    private boolean removeUserLoginInfo(String loginOutUserCode) {

        Map<String, HttpSession> loginUserMap = SessionListener.loginUser;
        HttpSession mapSession;
        if (loginUserMap == null) {
            return false;
        }
        try {
            if (loginUserMap.containsKey(loginOutUserCode)) {
                mapSession = loginUserMap.get(loginOutUserCode);
                mapSession.removeAttribute(CramsConstants.SESSION_ORGA_WITH_USER);
                mapSession.removeAttribute(CramsConstants.SESSION_LOGIN_USER);

                if (loginUserMap.containsKey(loginOutUserCode)) {
                    loginUserMap.remove(loginOutUserCode);
                }
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
