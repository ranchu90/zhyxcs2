package com.zhyxcs.xxzz.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhyxcs.xxzz.domain.Orga;
import com.zhyxcs.xxzz.domain.User;
import com.zhyxcs.xxzz.mapper.OrgaMapper;
import com.zhyxcs.xxzz.service.UserService;
import com.zhyxcs.xxzz.utils.CommonUtils;
import com.zhyxcs.xxzz.utils.CramsConstants;
import com.zhyxcs.xxzz.utils.Logs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Map<String, Object> getUsersByAddUserCode(@RequestParam(value = "pageSize", required = false) String pageSize,
                                                     @RequestParam(value = "pageNum", required = false) String pageNum,
                                                     @RequestParam(value = "addUserCode") String addUserCode,
                                                     @RequestParam(value = "bankCode", required = false) String bankCode,
                                                     @RequestParam(value = "bankName", required = false) String bankName,
                                                     @RequestParam(value = "bankTypeCode", required = false) String bankTypeCode,
                                                     @RequestParam(value = "userName", required = false) String userName){
        pageSize = (pageSize == null || "".equals(pageSize.trim())) ? this.getDisplayCount() : pageSize;
        pageNum = (pageNum == null || "".equals(pageNum.trim())) ? "1" : pageNum;

        Map<String, Object> map = new HashMap();
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<HashMap> mapList = userService.selectBysAddUserCode(addUserCode, bankTypeCode, bankCode, bankName, userName);
        PageInfo<HashMap> pageInfo = new PageInfo(mapList);
        map.put("pageInfo", pageInfo);

        return map;
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

        User oldUser = userService.selectByPrimaryKey(user.getSusercode());

        int code = 0;

        if (oldUser == null) {
            try {
                code = userService.insert(user);
                this.writeLog(Logs.USER_NEW);
                return code;
            } catch (Exception e) {
                logger.error("## Error Information ##: {}", e);
            }
        }

        return code;
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    public int updateBasicByPrimaryKey(@RequestBody User user){
        this.writeLog(Logs.USER_UPDATE);
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

                this.writeLog(Logs.USER_UPDATE_PASSWORD);
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

                this.writeLog(Logs.USER_UPDATE_PASSWORD_RESET);
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

    @RequestMapping(value = "/bankReviewCheck", method = RequestMethod.GET)
    public Map<String, Object> ifBankEntryHasBankReview(){
        HttpSession session = super.request.getSession();
        User cur_user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);

        Map<String, Object> result = new HashMap();
        String addUserCode = cur_user.getSaddusercode();
        String userLevel = cur_user.getSuserlevel();

        if ("1".equals(userLevel)){
            List<HashMap> list = userService.ifBankEntryHasBankReview(addUserCode);

            if (list != null && list.size() > 0){
                result.put("ifBankEntryHasBankReview", "true");
            } else {
                result.put("ifBankEntryHasBankReview", "false");
            }

        } else {
            result.put("ifBankEntryHasBankReview", "false");
        }



        return result;
    }
}
