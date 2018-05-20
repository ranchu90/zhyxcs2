package com.zhyxcs.xxzz.domain;

import java.util.Date;

public class User {
    private String susercode;

    private String sbankcode;

    private String susername;

    private String spassword;

    private String suserstate;

    private String suserlevel;

    private Date suserontime;

    private Byte spwderror;

    private String stelephone;

    private String semail;

    private String saddusercode;

    public User(String susercode, String sbankcode, String susername, String spassword, String suserstate, String suserlevel, Date suserontime, Byte spwderror, String stelephone, String semail, String saddusercode) {
        this.susercode = susercode;
        this.sbankcode = sbankcode;
        this.susername = susername;
        this.spassword = spassword;
        this.suserstate = suserstate;
        this.suserlevel = suserlevel;
        this.suserontime = suserontime;
        this.spwderror = spwderror;
        this.stelephone = stelephone;
        this.semail = semail;
        this.saddusercode = saddusercode;
    }

    public User() {
        super();
    }

    public String getSusercode() {
        return susercode;
    }

    public void setSusercode(String susercode) {
        this.susercode = susercode == null ? null : susercode.trim();
    }

    public String getSbankcode() {
        return sbankcode;
    }

    public void setSbankcode(String sbankcode) {
        this.sbankcode = sbankcode == null ? null : sbankcode.trim();
    }

    public String getSusername() {
        return susername;
    }

    public void setSusername(String susername) {
        this.susername = susername == null ? null : susername.trim();
    }

    public String getSpassword() {
        return spassword;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword == null ? null : spassword.trim();
    }

    public String getSuserstate() {
        return suserstate;
    }

    public void setSuserstate(String suserstate) {
        this.suserstate = suserstate == null ? null : suserstate.trim();
    }

    public String getSuserlevel() {
        return suserlevel;
    }

    public void setSuserlevel(String suserlevel) {
        this.suserlevel = suserlevel == null ? null : suserlevel.trim();
    }

    public Date getSuserontime() {
        return suserontime;
    }

    public void setSuserontime(Date suserontime) {
        this.suserontime = suserontime;
    }

    public Byte getSpwderror() {
        return spwderror;
    }

    public void setSpwderror(Byte spwderror) {
        this.spwderror = spwderror;
    }

    public String getStelephone() {
        return stelephone;
    }

    public void setStelephone(String stelephone) {
        this.stelephone = stelephone == null ? null : stelephone.trim();
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail == null ? null : semail.trim();
    }

    public String getSaddusercode() {
        return saddusercode;
    }

    public void setSaddusercode(String saddusercode) {
        this.saddusercode = saddusercode == null ? null : saddusercode.trim();
    }
}