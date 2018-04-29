package com.zhyxcs.xxzz.domain;

public class BankType {
    private String sbanktypecode;

    private String stypename;

    public BankType(String sbanktypecode, String stypename) {
        this.sbanktypecode = sbanktypecode;
        this.stypename = stypename;
    }

    public BankType() {
        super();
    }

    public String getSbanktypecode() {
        return sbanktypecode;
    }

    public void setSbanktypecode(String sbanktypecode) {
        this.sbanktypecode = sbanktypecode == null ? null : sbanktypecode.trim();
    }

    public String getStypename() {
        return stypename;
    }

    public void setStypename(String stypename) {
        this.stypename = stypename == null ? null : stypename.trim();
    }
}