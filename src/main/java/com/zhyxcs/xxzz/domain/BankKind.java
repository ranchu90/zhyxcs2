package com.zhyxcs.xxzz.domain;

public class BankKind {
    private String sbankkind;

    private String skindname;

    public BankKind(String sbankkind, String skindname) {
        this.sbankkind = sbankkind;
        this.skindname = skindname;
    }

    public BankKind() {
        super();
    }

    public String getSbankkind() {
        return sbankkind;
    }

    public void setSbankkind(String sbankkind) {
        this.sbankkind = sbankkind == null ? null : sbankkind.trim();
    }

    public String getSkindname() {
        return skindname;
    }

    public void setSkindname(String skindname) {
        this.skindname = skindname == null ? null : skindname.trim();
    }
}