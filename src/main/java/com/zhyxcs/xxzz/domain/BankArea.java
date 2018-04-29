package com.zhyxcs.xxzz.domain;

public class BankArea {
    private String sbankareacode;

    private String sareaname;

    public BankArea(String sbankareacode, String sareaname) {
        this.sbankareacode = sbankareacode;
        this.sareaname = sareaname;
    }

    public BankArea() {
        super();
    }

    public String getSbankareacode() {
        return sbankareacode;
    }

    public void setSbankareacode(String sbankareacode) {
        this.sbankareacode = sbankareacode == null ? null : sbankareacode.trim();
    }

    public String getSareaname() {
        return sareaname;
    }

    public void setSareaname(String sareaname) {
        this.sareaname = sareaname == null ? null : sareaname.trim();
    }
}