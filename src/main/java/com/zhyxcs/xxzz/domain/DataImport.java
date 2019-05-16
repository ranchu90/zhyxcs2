package com.zhyxcs.xxzz.domain;

import java.util.Date;

public class DataImport {
    private Integer id;

    private Integer updatenum;

    private Integer failnum;

    private String uploadipaddress;

    private String uploadusercode;

    private String uploadusername;

    private Date uploadtime;

    public DataImport(Integer id, Integer updatenum, Integer failnum, String uploadipaddress, String uploadusercode, String uploadusername, Date uploadtime) {
        this.id = id;
        this.updatenum = updatenum;
        this.failnum = failnum;
        this.uploadipaddress = uploadipaddress;
        this.uploadusercode = uploadusercode;
        this.uploadusername = uploadusername;
        this.uploadtime = uploadtime;
    }

    public DataImport() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUpdatenum() {
        return updatenum;
    }

    public void setUpdatenum(Integer updatenum) {
        this.updatenum = updatenum;
    }

    public Integer getFailnum() {
        return failnum;
    }

    public void setFailnum(Integer failnum) {
        this.failnum = failnum;
    }

    public String getUploadipaddress() {
        return uploadipaddress;
    }

    public void setUploadipaddress(String uploadipaddress) {
        this.uploadipaddress = uploadipaddress == null ? null : uploadipaddress.trim();
    }

    public String getUploadusercode() {
        return uploadusercode;
    }

    public void setUploadusercode(String uploadusercode) {
        this.uploadusercode = uploadusercode == null ? null : uploadusercode.trim();
    }

    public String getUploadusername() {
        return uploadusername;
    }

    public void setUploadusername(String uploadusername) {
        this.uploadusername = uploadusername == null ? null : uploadusername.trim();
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }
}