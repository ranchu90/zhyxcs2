package com.zhyxcs.xxzz.domain;

import java.util.Date;

public class Image {
    private Long sid;

    private String stransactionnum;

    private String simagetype;

    private String sproofname;

    private String soriginalname;

    private String simagename;

    private Short simagesn;

    private String sstorepath;

    private Date suploadtime;

    public Image(Long sid, String stransactionnum, String simagetype, String sproofname, String soriginalname, String simagename, Short simagesn, String sstorepath, Date suploadtime) {
        this.sid = sid;
        this.stransactionnum = stransactionnum;
        this.simagetype = simagetype;
        this.sproofname = sproofname;
        this.soriginalname = soriginalname;
        this.simagename = simagename;
        this.simagesn = simagesn;
        this.sstorepath = sstorepath;
        this.suploadtime = suploadtime;
    }

    public Image() {
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

    public String getSimagetype() {
        return simagetype;
    }

    public void setSimagetype(String simagetype) {
        this.simagetype = simagetype == null ? null : simagetype.trim();
    }

    public String getSproofname() {
        return sproofname;
    }

    public void setSproofname(String sproofname) {
        this.sproofname = sproofname == null ? null : sproofname.trim();
    }

    public String getSoriginalname() {
        return soriginalname;
    }

    public void setSoriginalname(String soriginalname) {
        this.soriginalname = soriginalname == null ? null : soriginalname.trim();
    }

    public String getSimagename() {
        return simagename;
    }

    public void setSimagename(String simagename) {
        this.simagename = simagename == null ? null : simagename.trim();
    }

    public Short getSimagesn() {
        return simagesn;
    }

    public void setSimagesn(Short simagesn) {
        this.simagesn = simagesn;
    }

    public String getSstorepath() {
        return sstorepath;
    }

    public void setSstorepath(String sstorepath) {
        this.sstorepath = sstorepath == null ? null : sstorepath.trim();
    }

    public Date getSuploadtime() {
        return suploadtime;
    }

    public void setSuploadtime(Date suploadtime) {
        this.suploadtime = suploadtime;
    }

    @Override
    public String toString() {
        return "Image{" +
                "stransactionnum='" + stransactionnum + '\'' +
                ", simagetype='" + simagetype + '\'' +
                ", sproofname='" + sproofname + '\'' +
                ", soriginalname='" + soriginalname + '\'' +
                ", simagename='" + simagename + '\'' +
                ", simagesn=" + simagesn +
                ", sstorepath='" + sstorepath + '\'' +
                ", suploadtime=" + suploadtime +
                '}';
    }
}