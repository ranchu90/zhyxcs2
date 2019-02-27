package com.zhyxcs.xxzz.utils;

public class ActionType {
    public static final String COMMIT = "commit";
    public static final String COMMIT_REN = "commit_ren";
    public static final String CALL_BACK = "call_back";
    public static final String SEND_BACK = "send_back";
    public static final String SEND_BACK_REN = "send_back_ren";
    public static final String REVIEW = "review";
    public static final String CHECK = "check";
    public static final String RECHECK = "recheck";
    public static final String UPLOAD_LICENCE = "upload_licence";
    public static final String ACCELERATE = "accelerate";
    public static final String END = "end";

    //影像传输系统
    //商业银行录入员新增任务，没有提交给商业银行复核员或没有提交给人民银行审核员之前
    public static final String APPROVAL_STATE_COMMERCE_NEW="0";
    //商业银行录入员完成影像录入，提交给商业银行复核员之后
    public static final String APPROVAL_STATE_COMMERCE_REVIEW="1";
    //商业银行录入员完成影像录入或商业银行复核员完成复核，提交给人民银行审核员之后
    public static final String APPROVAL_STATE_PBC_CHECK="2";
    //人民银行审核员完成审核并通过审核
    public static final String APPROVAL_STATE_PBC_PASS_AUDIT="3";
    //商业银行复核员或人民银行审核员对商业银行录入员提交的业务不通过
    public static final String APPROVAL_STATE_NO_PASS="4";
    //人民银行审核员终止业务，因商业银行提交的业务发生不可容忍的错误
    public static final String APPROVAL_STATE_ERROR="5";

    //事后监督系统
    /*
    * 'APPROVAL_STATE_COMMERCE_NEW': 1,
    'APPROVAL_STATE_COMMERCE_REVIEW': 2,
    'APPROVAL_STATE_PBC_CHECK': 3,
    'APPROVAL_STATE_PBC_RECHECK': 4,
    'APPROVAL_STATE_NO_PASS': 0,
    'APPROVAL_STATE_END': 5,
    * */
    //商业银行录入员新增任务，没有提交给商业银行复核员或没有提交给人民银行审核员之前
    public static final String SV_APPROVAL_STATE_COMMERCE_NEW="1";
    //商业银行录入员完成影像录入，提交给商业银行复核员之后
    public static final String SV_APPROVAL_STATE_COMMERCE_REVIEW="2";
    //商业银行录入员完成影像录入或商业银行复核员完成复核，提交给人民银行审核员之后
    public static final String SV_APPROVAL_STATE_PBC_CHECK="3";
    //人民银行审核员完成审核并通过审核
    public static final String SV_APPROVAL_STATE_PBC_RECHECK="4";
    //商业银行复核员或人民银行审核员对商业银行录入员提交的业务不通过,返回整改的业务状态
    public static final String SV_APPROVAL_STATE_NO_PASS="0";
    //人民银行审核员终止业务，因商业银行提交的业务发生不可容忍的错误
    public static final String SV_APPROVAL_STATE_END="5";

    //业务不需要加急
    public static final String BUSINESS_EMERGENCY_NO="0";
    //业务需要加急
    public static final String BUSINESS_EMERGENCY_YES="1";

    //没有上传开户许可证
    public static final String UPLOADED_LICENCE_NO="0";
    //已经上传开户许可证
    public static final String UPLOADED_LICENCE_YES="1";



}
