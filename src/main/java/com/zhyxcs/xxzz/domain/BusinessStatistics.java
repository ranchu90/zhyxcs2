package com.zhyxcs.xxzz.domain;

import java.util.Date;

public class BusinessStatistics {
    private Long sid;

    private String stransactionnum;

    private String spbcode;

    private String sbankareacode;

    private String sbankcitycode;

    private String sbankkind;

    private String sbanktypecode;

    private String sbusinesscategory;

    private String saccounttype;

    private String spass;

    private String sovertime;

    private String serrortype;

    private Date shappentimes;

    public BusinessStatistics(Long sid, String stransactionnum, String spbcode, String sbankareacode, String sbankcitycode, String sbankkind, String sbanktypecode, String sbusinesscategory, String saccounttype, String spass, String sovertime, String serrortype, Date shappentimes) {
        this.sid = sid;
        this.stransactionnum = stransactionnum;
        this.spbcode = spbcode;
        this.sbankareacode = sbankareacode;
        this.sbankcitycode = sbankcitycode;
        this.sbankkind = sbankkind;
        this.sbanktypecode = sbanktypecode;
        this.sbusinesscategory = sbusinesscategory;
        this.saccounttype = saccounttype;
        this.spass = spass;
        this.sovertime = sovertime;
        this.serrortype = serrortype;
        this.shappentimes = shappentimes;
    }

    public BusinessStatistics() {
        super();
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getStransactionnum() {
        return stransactionnum;
    }

    public void setStransactionnum(String stransactionnum) {
        this.stransactionnum = stransactionnum == null ? null : stransactionnum.trim();
    }

    public String getSpbcode() {
        return spbcode;
    }

    public void setSpbcode(String spbcode) {
        this.spbcode = spbcode == null ? null : spbcode.trim();
    }

    public String getSbankareacode() {
        return sbankareacode;
    }

    public void setSbankareacode(String sbankareacode) {
        this.sbankareacode = sbankareacode == null ? null : sbankareacode.trim();
    }

    public String getSbankcitycode() {
        return sbankcitycode;
    }

    public void setSbankcitycode(String sbankcitycode) {
        this.sbankcitycode = sbankcitycode == null ? null : sbankcitycode.trim();
    }

    public String getSbankkind() {
        return sbankkind;
    }

    public void setSbankkind(String sbankkind) {
        this.sbankkind = sbankkind == null ? null : sbankkind.trim();
    }

    public String getSbanktypecode() {
        return sbanktypecode;
    }

    public void setSbanktypecode(String sbanktypecode) {
        this.sbanktypecode = sbanktypecode == null ? null : sbanktypecode.trim();
    }

    public String getSbusinesscategory() {
        return sbusinesscategory;
    }

    public void setSbusinesscategory(String sbusinesscategory) {
        this.sbusinesscategory = sbusinesscategory == null ? null : sbusinesscategory.trim();
    }

    public String getSaccounttype() {
        return saccounttype;
    }

    public void setSaccounttype(String saccounttype) {
        this.saccounttype = saccounttype == null ? null : saccounttype.trim();
    }

    public String getSpass() {
        return spass;
    }

    public void setSpass(String spass) {
        this.spass = spass == null ? null : spass.trim();
    }

    public String getSovertime() {
        return sovertime;
    }

    public void setSovertime(String sovertime) {
        this.sovertime = sovertime == null ? null : sovertime.trim();
    }

    public String getSerrortype() {
        return serrortype;
    }

    public void setSerrortype(String serrortype) {
        this.serrortype = serrortype == null ? null : serrortype.trim();
    }

    public Date getShappentimes() {
        return shappentimes;
    }

    public void setShappentimes(Date shappentimes) {
        this.shappentimes = shappentimes;
    }
}