package com.zhyxcs.xxzz.domain;

import java.util.Date;

public class Supervision {
    private String stransactionnum;

    private String saccountnum;

    private String sdepositorname;

    private String suniquesocialcreditcode;

    private String saccounttime;

    private String sapprovalcode;

    private Date sbusinessdate;

    private Date sbusinesscertificatedate;

    private String srelatedtransctionnum;

    private String skind;

    private String sapprovalstate;

    private Date scommittimes;

    private Date sreturntimes;

    private Date spbcreturntimes;

    private Date scompletetimes;

    private String sbusinesscategory;

    private String saccounttype;

    private String sbankcode;

    private String spbcbankcode;

    private String sbankname;

    private String supusercode;

    private String supusername;

    private Date sstarttime;

    private Date sendtime;

    private String sreviewusercode;

    private String scheckusercode;

    private String srecheckusercode;

    private String srecheckresult;

    private String srecheckopinion;

    private Date srechecktime;

    public Supervision(String stransactionnum, String saccountnum, String sdepositorname, String suniquesocialcreditcode, String saccounttime, String sapprovalcode, Date sbusinessdate, Date sbusinesscertificatedate, String srelatedtransctionnum, String skind, String sapprovalstate, Date scommittimes, Date sreturntimes, Date spbcreturntimes, Date scompletetimes, String sbusinesscategory, String saccounttype, String sbankcode, String spbcbankcode, String sbankname, String supusercode, String supusername, Date sstarttime, Date sendtime, String sreviewusercode, String scheckusercode, String srecheckusercode, String srecheckresult, String srecheckopinion, Date srechecktime) {
        this.stransactionnum = stransactionnum;
        this.saccountnum = saccountnum;
        this.sdepositorname = sdepositorname;
        this.suniquesocialcreditcode = suniquesocialcreditcode;
        this.saccounttime = saccounttime;
        this.sapprovalcode = sapprovalcode;
        this.sbusinessdate = sbusinessdate;
        this.sbusinesscertificatedate = sbusinesscertificatedate;
        this.srelatedtransctionnum = srelatedtransctionnum;
        this.skind = skind;
        this.sapprovalstate = sapprovalstate;
        this.scommittimes = scommittimes;
        this.sreturntimes = sreturntimes;
        this.spbcreturntimes = spbcreturntimes;
        this.scompletetimes = scompletetimes;
        this.sbusinesscategory = sbusinesscategory;
        this.saccounttype = saccounttype;
        this.sbankcode = sbankcode;
        this.spbcbankcode = spbcbankcode;
        this.sbankname = sbankname;
        this.supusercode = supusercode;
        this.supusername = supusername;
        this.sstarttime = sstarttime;
        this.sendtime = sendtime;
        this.sreviewusercode = sreviewusercode;
        this.scheckusercode = scheckusercode;
        this.srecheckusercode = srecheckusercode;
        this.srecheckresult = srecheckresult;
        this.srecheckopinion = srecheckopinion;
        this.srechecktime = srechecktime;
    }

    public Supervision() {
        super();
    }

    public String getStransactionnum() {
        return stransactionnum;
    }

    public void setStransactionnum(String stransactionnum) {
        this.stransactionnum = stransactionnum == null ? null : stransactionnum.trim();
    }

    public String getSaccountnum() {
        return saccountnum;
    }

    public void setSaccountnum(String saccountnum) {
        this.saccountnum = saccountnum == null ? null : saccountnum.trim();
    }

    public String getSdepositorname() {
        return sdepositorname;
    }

    public void setSdepositorname(String sdepositorname) {
        this.sdepositorname = sdepositorname == null ? null : sdepositorname.trim();
    }

    public String getSuniquesocialcreditcode() {
        return suniquesocialcreditcode;
    }

    public void setSuniquesocialcreditcode(String suniquesocialcreditcode) {
        this.suniquesocialcreditcode = suniquesocialcreditcode == null ? null : suniquesocialcreditcode.trim();
    }

    public String getSaccounttime() {
        return saccounttime;
    }

    public void setSaccounttime(String saccounttime) {
        this.saccounttime = saccounttime == null ? null : saccounttime.trim();
    }

    public String getSapprovalcode() {
        return sapprovalcode;
    }

    public void setSapprovalcode(String sapprovalcode) {
        this.sapprovalcode = sapprovalcode == null ? null : sapprovalcode.trim();
    }

    public Date getSbusinessdate() {
        return sbusinessdate;
    }

    public void setSbusinessdate(Date sbusinessdate) {
        this.sbusinessdate = sbusinessdate;
    }

    public Date getSbusinesscertificatedate() {
        return sbusinesscertificatedate;
    }

    public void setSbusinesscertificatedate(Date sbusinesscertificatedate) {
        this.sbusinesscertificatedate = sbusinesscertificatedate;
    }

    public String getSrelatedtransctionnum() {
        return srelatedtransctionnum;
    }

    public void setSrelatedtransctionnum(String srelatedtransctionnum) {
        this.srelatedtransctionnum = srelatedtransctionnum == null ? null : srelatedtransctionnum.trim();
    }

    public String getSkind() {
        return skind;
    }

    public void setSkind(String skind) {
        this.skind = skind == null ? null : skind.trim();
    }

    public String getSapprovalstate() {
        return sapprovalstate;
    }

    public void setSapprovalstate(String sapprovalstate) {
        this.sapprovalstate = sapprovalstate == null ? null : sapprovalstate.trim();
    }

    public Date getScommittimes() {
        return scommittimes;
    }

    public void setScommittimes(Date scommittimes) {
        this.scommittimes = scommittimes;
    }

    public Date getSreturntimes() {
        return sreturntimes;
    }

    public void setSreturntimes(Date sreturntimes) {
        this.sreturntimes = sreturntimes;
    }

    public Date getSpbcreturntimes() {
        return spbcreturntimes;
    }

    public void setSpbcreturntimes(Date spbcreturntimes) {
        this.spbcreturntimes = spbcreturntimes;
    }

    public Date getScompletetimes() {
        return scompletetimes;
    }

    public void setScompletetimes(Date scompletetimes) {
        this.scompletetimes = scompletetimes;
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