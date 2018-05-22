package com.zhyxcs.xxzz.domain;

import java.util.Date;

public class WorkIndex {
    private String stransactionnum;

    private String sdepositorname;

    private String sapprovalstate;

    private String sbusinessemergency;

    private Integer sreturntimes;

    private String sbusinesscategory;

    private String saccounttype;

    private String sbankcode;

    private String spbcbankcode;

    private String sbankname;

    private String supusercode;

    private String supusername;

    private String sapprovalcode;

    private String sidentifier;

    private Date sstarttime;

    private Date sendtime;

    private String sreviewusercode;

    private String scheckusercode;

    private String srecheckusercode;

    private String srecheckusername;

    private String srecheckresult;

    private String srecheckopinion;

    private Date srechecktime;

    public WorkIndex(String stransactionnum, String sdepositorname, String sapprovalstate, String sbusinessemergency, Integer sreturntimes, String sbusinesscategory, String saccounttype, String sbankcode, String spbcbankcode, String sbankname, String supusercode, String supusername, String sapprovalcode, String sidentifier, Date sstarttime, Date sendtime, String sreviewusercode, String scheckusercode, String srecheckusercode, String srecheckusername, String srecheckresult, String srecheckopinion, Date srechecktime) {
        this.stransactionnum = stransactionnum;
        this.sdepositorname = sdepositorname;
        this.sapprovalstate = sapprovalstate;
        this.sbusinessemergency = sbusinessemergency;
        this.sreturntimes = sreturntimes;
        this.sbusinesscategory = sbusinesscategory;
        this.saccounttype = saccounttype;
        this.sbankcode = sbankcode;
        this.spbcbankcode = spbcbankcode;
        this.sbankname = sbankname;
        this.supusercode = supusercode;
        this.supusername = supusername;
        this.sapprovalcode = sapprovalcode;
        this.sidentifier = sidentifier;
        this.sstarttime = sstarttime;
        this.sendtime = sendtime;
        this.sreviewusercode = sreviewusercode;
        this.scheckusercode = scheckusercode;
        this.srecheckusercode = srecheckusercode;
        this.srecheckusername = srecheckusername;
        this.srecheckresult = srecheckresult;
        this.srecheckopinion = srecheckopinion;
        this.srechecktime = srechecktime;
    }

    public WorkIndex() {
        super();
    }

    public String getStransactionnum() {
        return stransactionnum;
    }

    public void setStransactionnum(String stransactionnum) {
        this.stransactionnum = stransactionnum == null ? null : stransactionnum.trim();
    }

    public String getSdepositorname() {
        return sdepositorname;
    }

    public void setSdepositorname(String sdepositorname) {
        this.sdepositorname = sdepositorname == null ? null : sdepositorname.trim();
    }

    public String getSapprovalstate() {
        return sapprovalstate;
    }

    public void setSapprovalstate(String sapprovalstate) {
        this.sapprovalstate = sapprovalstate == null ? null : sapprovalstate.trim();
    }

    public String getSbusinessemergency() {
        return sbusinessemergency;
    }

    public void setSbusinessemergency(String sbusinessemergency) {
        this.sbusinessemergency = sbusinessemergency == null ? null : sbusinessemergency.trim();
    }

    public Integer getSreturntimes() {
        return sreturntimes;
    }

    public void setSreturntimes(Integer sreturntimes) {
        this.sreturntimes = sreturntimes;
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

    public String getSbankcode() {
        return sbankcode;
    }

    public void setSbankcode(String sbankcode) {
        this.sbankcode = sbankcode == null ? null : sbankcode.trim();
    }

    public String getSpbcbankcode() {
        return spbcbankcode;
    }

    public void setSpbcbankcode(String spbcbankcode) {
        this.spbcbankcode = spbcbankcode == null ? null : spbcbankcode.trim();
    }

    public String getSbankname() {
        return sbankname;
    }

    public void setSbankname(String sbankname) {
        this.sbankname = sbankname == null ? null : sbankname.trim();
    }

    public String getSupusercode() {
        return supusercode;
    }

    public void setSupusercode(String supusercode) {
        this.supusercode = supusercode == null ? null : supusercode.trim();
    }

    public String getSupusername() {
        return supusername;
    }

    public void setSupusername(String supusername) {
        this.supusername = supusername == null ? null : supusername.trim();
    }

    public String getSapprovalcode() {
        return sapprovalcode;
    }

    public void setSapprovalcode(String sapprovalcode) {
        this.sapprovalcode = sapprovalcode == null ? null : sapprovalcode.trim();
    }

    public String getSidentifier() {
        return sidentifier;
    }

    public void setSidentifier(String sidentifier) {
        this.sidentifier = sidentifier == null ? null : sidentifier.trim();
    }

    public Date getSstarttime() {
        return sstarttime;
    }

    public void setSstarttime(Date sstarttime) {
        this.sstarttime = sstarttime;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String getSreviewusercode() {
        return sreviewusercode;
    }

    public void setSreviewusercode(String sreviewusercode) {
        this.sreviewusercode = sreviewusercode == null ? null : sreviewusercode.trim();
    }

    public String getScheckusercode() {
        return scheckusercode;
    }

    public void setScheckusercode(String scheckusercode) {
        this.scheckusercode = scheckusercode == null ? null : scheckusercode.trim();
    }

    public String getSrecheckusercode() {
        return srecheckusercode;
    }

    public void setSrecheckusercode(String srecheckusercode) {
        this.srecheckusercode = srecheckusercode == null ? null : srecheckusercode.trim();
    }

    public String getSrecheckusername() {
        return srecheckusername;
    }

    public void setSrecheckusername(String srecheckusername) {
        this.srecheckusername = srecheckusername == null ? null : srecheckusername.trim();
    }

    public String getSrecheckresult() {
        return srecheckresult;
    }

    public void setSrecheckresult(String srecheckresult) {
        this.srecheckresult = srecheckresult == null ? null : srecheckresult.trim();
    }

    public String getSrecheckopinion() {
        return srecheckopinion;
    }

    public void setSrecheckopinion(String srecheckopinion) {
        this.srecheckopinion = srecheckopinion == null ? null : srecheckopinion.trim();
    }

    public Date getSrechecktime() {
        return srechecktime;
    }

    public void setSrechecktime(Date srechecktime) {
        this.srechecktime = srechecktime;
    }
}