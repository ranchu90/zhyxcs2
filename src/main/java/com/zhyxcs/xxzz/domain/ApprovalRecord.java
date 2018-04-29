package com.zhyxcs.xxzz.domain;

import java.util.Date;

public class ApprovalRecord {
    private Long sid;

    private String stransactionnum;

    private String sapprovelresult;

    private String sapprovelopinion;

    private String sapprovelusercode;

    private String sapprovelusername;

    private Date sapproveltime;

    public ApprovalRecord(Long sid, String stransactionnum, String sapprovelresult, String sapprovelopinion, String sapprovelusercode, String sapprovelusername, Date sapproveltime) {
        this.sid = sid;
        this.stransactionnum = stransactionnum;
        this.sapprovelresult = sapprovelresult;
        this.sapprovelopinion = sapprovelopinion;
        this.sapprovelusercode = sapprovelusercode;
        this.sapprovelusername = sapprovelusername;
        this.sapproveltime = sapproveltime;
    }

    public ApprovalRecord() {
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

    public String getSapprovelresult() {
        return sapprovelresult;
    }

    public void setSapprovelresult(String sapprovelresult) {
        this.sapprovelresult = sapprovelresult == null ? null : sapprovelresult.trim();
    }

    public String getSapprovelopinion() {
        return sapprovelopinion;
    }

    public void setSapprovelopinion(String sapprovelopinion) {
        this.sapprovelopinion = sapprovelopinion == null ? null : sapprovelopinion.trim();
    }

    public String getSapprovelusercode() {
        return sapprovelusercode;
    }

    public void setSapprovelusercode(String sapprovelusercode) {
        this.sapprovelusercode = sapprovelusercode == null ? null : sapprovelusercode.trim();
    }

    public String getSapprovelusername() {
        return sapprovelusername;
    }

    public void setSapprovelusername(String sapprovelusername) {
        this.sapprovelusername = sapprovelusername == null ? null : sapprovelusername.trim();
    }

    public Date getSapproveltime() {
        return sapproveltime;
    }

    public void setSapproveltime(Date sapproveltime) {
        this.sapproveltime = sapproveltime;
    }
}