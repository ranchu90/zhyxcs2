package com.zhyxcs.xxzz.domain;

import java.util.Date;

public class SystemLog {
    private Long sid;

    private String susercode;

    private String susename;

    private String sbankcode;

    private String sbankname;

    private String sipaddress;

    private String smacaddress;

    private String scomments;

    private Date slogtime;

    public SystemLog(Long sid, String susercode, String susename, String sbankcode, String sbankname, String sipaddress, String smacaddress, String scomments, Date slogtime) {
        this.sid = sid;
        this.susercode = susercode;
        this.susename = susename;
        this.sbankcode = sbankcode;
        this.sbankname = sbankname;
        this.sipaddress = sipaddress;
        this.smacaddress = smacaddress;
        this.scomments = scomments;
        this.slogtime = slogtime;
    }

    public SystemLog() {
        super();
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getSusercode() {
        return susercode;
    }

    public void setSusercode(String susercode) {
        this.susercode = susercode == null ? null : susercode.trim();
    }

    public String getSusename() {
        return susename;
    }

    public void setSusename(String susename) {
        this.susename = susename == null ? null : susename.trim();
    }

    public String getSbankcode() {
        return sbankcode;
    }

    public void setSbankcode(String sbankcode) {
        this.sbankcode = sbankcode == null ? null : sbankcode.trim();
    }

    public String getSbankname() {
        return sbankname;
    }

    public void setSbankname(String sbankname) {
        this.sbankname = sbankname == null ? null : sbankname.trim();
    }

    public String getSipaddress() {
        return sipaddress;
    }

    public void setSipaddress(String sipaddress) {
        this.sipaddress = sipaddress == null ? null : sipaddress.trim();
    }

    public String getSmacaddress() {
        return smacaddress;
    }

    public void setSmacaddress(String smacaddress) {
        this.smacaddress = smacaddress == null ? null : smacaddress.trim();
    }

    public String getScomments() {
        return scomments;
    }

    public void setScomments(String scomments) {
        this.scomments = scomments == null ? null : scomments.trim();
    }

    public Date getSlogtime() {
        return slogtime;
    }

    public void setSlogtime(Date slogtime) {
        this.slogtime = slogtime;
    }
}