package com.zhyxcs.xxzz.controller.sv;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhyxcs.xxzz.config.AccountSysConfig;
import com.zhyxcs.xxzz.controller.BaseController;
import com.zhyxcs.xxzz.domain.*;
import com.zhyxcs.xxzz.service.AccountManageService;
import com.zhyxcs.xxzz.service.DataImportService;
import com.zhyxcs.xxzz.utils.CommonUtils;
import com.zhyxcs.xxzz.utils.CramsConstants;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/accountsys")
public class AccountManageSysController extends BaseController {
    @Autowired
    private AccountSysConfig accountSysConfig;
    @Autowired
    private AccountManageService accountManageService;
    @Autowired
    private DataImportService dataImportService;

    //deprecated
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public List<AccountManageSys> getAccountInformtion(@RequestParam(value = "unitCode", required = false) String unitCode,
                                             @RequestParam(value = "accountNum", required = false) String accountNum,
                                             @RequestParam(value = "accountOpenTime", required = false) String accountOpenTime){
        HttpSession session = super.request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        Orga orga = (Orga) session.getAttribute(CramsConstants.SESSION_ORGA_WITH_USER);

        if (!"4".equals(user.getSuserlevel())){
            return null;
        }

        return accountManageService.selectByUnitCodeAccountNumAndOpenDate(unitCode, accountNum, accountOpenTime);
    }

    @RequestMapping(value = "/accountInfo", method = RequestMethod.GET)
    public List<AccountManageSys> getAccountInformtionVersionTwo(@RequestParam(value = "status", required = false) String status,
                                                                 @RequestParam(value = "accountNum", required = false) String accountNum,
                                                                 @RequestParam(value = "depositorName", required = false) String depositorName,
                                                                 @RequestParam(value = "unitCode", required = false) String unitCode,
                                                                 @RequestParam(value = "ifBlurry", required = true) String ifBlurry){
        HttpSession session = super.request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);

        if (!("4".equals(user.getSuserlevel()) || "5".equals(user.getSuserlevel()))){
            return null;
        }

        boolean blurryFlag = "yes".equals(ifBlurry)? true : false;

        if (blurryFlag) {
            return accountManageService.selectByUnitCodeAccountNumAndOpenDateBlur(depositorName, unitCode);
        } else {
            return accountManageService.selectByUnitCodeAccountNumAndOpenDateVersionTwo(status, accountNum);
        }
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public HashMap updateAccountSysByDay(@RequestParam (value = "file") MultipartFile file){
        HttpSession session = super.request.getSession();
        HashMap<String, String> result = new HashMap();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);

        if (!"7".equals(user.getSuserlevel()) || !"admin".equals(user.getSusercode())){

            result.put("result", "error");
            result.put("message", "您没有权限");
            return result;
        }

        String fileName = String.valueOf(UUID.randomUUID());
        String bathPath = accountSysConfig.getBasePath();

        Date currentDate = CommonUtils.newDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        String datePath = format.format(currentDate);
        String yearMounthPath = datePath.substring(0, 6);
        String dayPath = datePath.substring(6);

        String storePath = bathPath + File.separatorChar +  yearMounthPath + File.separatorChar + dayPath
                + File.separatorChar + fileName + ".txt";

        try {
            InputStream fileInputStream = new BufferedInputStream(file.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "GBK"));

            String strLine = null;
            int num = 0;
            int successNum = 0;

            while (true) {
                strLine = bufferedReader.readLine();
                if(strLine!=null) {
//                    System.out.println(strLine);
                    ++num;
                    //验证个数
                    String[] strArrays = strLine.split("\\|");
                    if (strArrays.length != 14){
                        result.put("extra", "以下数据长度不为14，不满足要求！" + strLine);
                        continue;
                    }

                    if (num == 1){
                        if (!strArrays[0].equals("存款人名称") || !strArrays[1].equals("注册地地区代码") || !strArrays[2].equals("法定代表人或负责人姓名") ||
                                !strArrays[3].equals("上级单位名称") || !strArrays[4].equals("上级法人基本存款账户开户许可证核准号") || !strArrays[5].equals("法定代表人/负责人姓名") ||
                                !strArrays[6].equals("存款人证明文件1编号")|| !strArrays[7].equals("开户银行机构代码") || !strArrays[8].equals("账户性质") ||
                                !strArrays[9].equals("账户账号")|| !strArrays[10].equals("账户名称")|| !strArrays[11].equals("开户日期") ||
                                !strArrays[12].equals("销户日期") || !strArrays[13].equals("账户状态")) {

                            result.put("result", "error");
                            result.put("message", "数据格式不符合要求");
                            result.put("extra", strLine);
                            break;
                        }


                        // 验证字段
                    } else {
                        //插入数据
                        AccountManageSys record = new AccountManageSys();
                        record.setSdepositorname(strArrays[0]);
                        record.setSregisterareacode(strArrays[1]);
                        record.setSstatutoryname(strArrays[2]);
                        record.setSsuperiorunit(strArrays[3]);
                        record.setSsuperiorstatutoryaccountapprovalcode(strArrays[4]);
                        record.setSsuperiorstatutoryname(strArrays[5]);
                        record.setSdepositorsupportdocumentfirstcode(strArrays[6]);
                        record.setSaccountopenbankcode(strArrays[7]);
                        record.setSaccountkind(strArrays[8]);
                        record.setSaccountnum(strArrays[9]);
                        record.setSaccountname(strArrays[10]);
                        record.setSaccountopendate(strArrays[11]);
                        record.setSaccountclosedate(strArrays[12]);
                        record.setSaccountstatus(strArrays[13]);

                        accountManageService.insert(record);

                        ++successNum;
                    }
                }
                else
                    break;
            }

            if (num > 1){
                String msg = "导入成功：" + successNum + "条；失败：" + (num-successNum-1) + "条";

                result.put("result", "success");
                result.put("message", "数据导入成功！");
                result.put("extra", result.containsKey("extra")? result.get("extra") + msg:msg);
            }

            DataImport dataImport = new DataImport();
            dataImport.setFailnum(num-successNum-1);
            dataImport.setUpdatenum(num-1);
            dataImport.setUploadipaddress(CommonUtils.getIpAddress(super.request));
            dataImport.setUploadtime(CommonUtils.newDate());
            dataImport.setUploadusername(user.getSusername());
            dataImport.setUploadusercode(user.getSusercode());

            dataImportService.insert(dataImport);

            fileInputStream.close();
            bufferedReader.close();

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }

    @RequestMapping(value = "/record", method = RequestMethod.GET)
    public Map<String, Object> getImportRecords(@RequestParam(value = "pageSize", required = false) String pageSize,
                                                   @RequestParam(value = "pageNum", required = false) String pageNum){
        HttpSession session = super.request.getSession();
        User user = (User) session.getAttribute(CramsConstants.SESSION_LOGIN_USER);
        Map<String, Object> map = new HashMap<String, Object>();

        if (!"7".equals(user.getSuserlevel()) || !"admin".equals(user.getSusercode())){
            return null;
        }

        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<DataImport> recordList = dataImportService.selectAll();
        PageInfo<SystemLog> pageInfo = new PageInfo(recordList);
        map.put("pageInfo", pageInfo);

        return map;
    }
}
