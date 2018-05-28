package com.zhyxcs.xxzz.utils;

/*用于统计业务的是否被退回状态
APPROVAL，表示业务被人民银行审核通过
SENDBACK,表示业务未被审核通过，并被人民银行退回
 */
public enum AuditStatus {
    APPROVAL("通过", "1"), UNTREAD("退回", "0");

    private String name;
    private String value;


    AuditStatus(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AuditStatus{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
