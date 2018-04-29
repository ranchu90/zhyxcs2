package com.zhyxcs.xxzz.domain;

public class Orga {
    private String sbankcode;

    private String sbanktypecode;

    private String sbankkind;

    private String sbankname;

    private String stopbankcode;

    private String spbcode;

    private String sbankareacode;

    private String sbankcitycode;

    private String sbankstate;

    public Orga(String sbankcode, String sbanktypecode, String sbankkind, String sbankname, String stopbankcode, String spbcode, String sbankareacode, String sbankcitycode, String sbankstate) {
        this.sbankcode = sbankcode;
        this.sbanktypecode = sbanktypecode;
        this.sbankkind = sbankkind;
        this.sbankname = sbankname;
        this.stopbankcode = stopbankcode;
        this.spbcode = spbcode;
        this.sbankareacode = sbankareacode;
        this.sbankcitycode = sbankcitycode;
        this.sbankstate = sbankstate;
    }

    public Orga() {
        super();
    }

    public String getSbankcode() {
        return sbankcode;
    }

    public void setSbankcode(String sbankcode) {
        this.sbankcode = sbankcode == null ? null : sbankcode.trim();
    }

    public String getSbanktypecode() {
        return sbanktypecode;
    }

    public void setSbanktypecode(String sbanktypecode) {
        this.sbanktypecode = sbanktypecode == null ? null : sbanktypecode.trim();
    }

    public String getSbankkind() {
        return sbankkind;
    }

    public void setSbankkind(String sbankkind) {
        this.sbankkind = sbankkind == null ? null : sbankkind.trim();
    }

    public String getSbankname() {
        return sbankname;
    }

    public void setSbankname(String sbankname) {
        this.sbankname = sbankname == null ? null : sbankname.trim();
    }

    public String getStopbankcode() {
        return stopbankcode;
    }

    public void setStopbankcode(String stopbankcode) {
        this.stopbankcode = stopbankcode == null ? null : stopbankcode.trim();
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

    public String getSbankstate() {
        return sbankstate;
    }

    public void setSbankstate(String sbankstate) {
        this.sbankstate = sbankstate == null ? null : sbankstate.trim();
    }
}