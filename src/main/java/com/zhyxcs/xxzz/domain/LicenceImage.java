package com.zhyxcs.xxzz.domain;

import java.util.Date;

public class LicenceImage {
    private String stransactionnum;

    private String slicenceimagename;

    private String suploadusercode;

    private String suploadusername;

    private String sstorepath;

    private String sapprovalcode;

    private String sidentifier;

    private String sbusinesscategory;

    private String saccounttype;

    private String sbankcode;

    private String sbankname;

    private Date suploadtime;

    public LicenceImage(String stransactionnum, String slicenceimagename, String suploadusercode, String suploadusername, String sstorepath, String sapprovalcode, String sidentifier, String sbusinesscategory, String saccounttype, String sbankcode, String sbankname, Date suploadtime) {
        this.stransactionnum = stransactionnum;
        this.slicenceimagename = slicenceimagename;
        this.suploadusercode = suploadusercode;
        this.suploadusername = suploadusername;
        this.sstorepath = sstorepath;
        this.sapprovalcode = sapprovalcode;
        this.sidentifier = sidentifier;
        this.sbusinesscategory = sbusinesscategory;
        this.saccounttype = saccounttype;
        this.sbankcode = sbankcode;
        this.sbankname = sbankname;
        this.suploadtime = suploadtime;
    }

    public LicenceImage() {
        super();
    }

    public String getStransactionnum() {
        return stransactionnum;
    }

    public void setStransactionnum(String stransactionnum) {
        this.stransactionnum = stransactionnum == null ? null : stransactionnum.trim();
    }

    public String getSlicenceimagename() {
        return slicenceimagename;
    }

    public void setSlicenceimagename(String slicenceimagename) {
        this.slicenceimagename = slicenceimagename == null ? null : slicenceimagename.trim();
    }

    public String getSuploadusercode() {
        return suploadusercode;
    }

    public void setSuploadusercode(String suploadusercode) {
        this.suploadusercode = suploadusercode == null ? null : suploadusercode.trim();
    }

    public String getSuploadusername() {
        return suploadusername;
    }

    public void setSuploadusername(String suploadusername) {
        this.suploadusername = suploadusername == null ? null : suploadusername.trim();
    }

    public String getSstorepath() {
        return sstorepath;
    }

    public void setSstorepath(String sstorepath) {
        this.sstorepath = sstorepath == null ? null : sstorepath.trim();
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

    public String getSbankname() {
        return sbankname;
    }

    public void setSbankname(String sbankname) {
        this.sbankname = sbankname == null ? null : sbankname.trim();
    }

    public Date getSuploadtime() {
        return suploadtime;
    }

    public void setSuploadtime(Date suploadtime) {
        this.suploadtime = suploadtime;
    }
}