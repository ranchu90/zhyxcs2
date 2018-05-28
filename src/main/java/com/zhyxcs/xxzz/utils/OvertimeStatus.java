package com.zhyxcs.xxzz.utils;

/*用于统计业务的审核超时状态
OVER，表示人民银行审核超时
NOOVER,表示人民银行审核未超时
 */
public enum OvertimeStatus {
    OVER("审核已超时", "0"), NOOVER("审核未超时", "1");
    private String name;
    private String value;

    OvertimeStatus(String name, String value) {
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
        return "OvertimeStatus{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
