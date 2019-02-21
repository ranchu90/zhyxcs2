package com.zhyxcs.xxzz.domain;

public class SupervisionImageStandard {
    private Integer sid;

    private String sbusineescategory;

    private String saccounttype;

    private String sproofname;

    private String sproofamount;

    private Integer sifneedlicence;

    public SupervisionImageStandard(Integer sid, String sbusineescategory, String saccounttype, String sproofname, String sproofamount, Integer sifneedlicence) {
        this.sid = sid;
        this.sbusineescategory = sbusineescategory;
        this.saccounttype = saccounttype;
        this.sproofname = sproofname;
        this.sproofamount = sproofamount;
        this.sifneedlicence = sifneedlicence;
    }

    public SupervisionImageStandard() {
        super();
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSbusineescategory() {
        return sbusineescategory;
    }

    public void setSbusineescategory(String sbusineescategory) {
        this.sbusineescategory = sbusineescategory == null ? null : sbusineescategory.trim();
    }

    public String getSaccounttype() {
        return saccounttype;
    }

    public void setSaccounttype(String saccounttype) {
        this.saccounttype = saccounttype == null ? null : saccounttype.trim();
    }

    public String getSproofname() {
        return sproofname;
    }

    public void setSproofname(String sproofname) {
        this.sproofname = sproofname == null ? null : sproofname.trim();
    }

    public String getSproofamount() {
        return sproofamount;
    }

    public void setSproofamount(String sproofamount) {
        this.sproofamount = sproofamount == null ? null : sproofamount.trim();
    }

    public Integer getSifneedlicence() {
        return sifneedlicence;
    }

    public void setSifneedlicence(Integer sifneedlicence) {
        this.sifneedlicence = sifneedlicence;
    }
}