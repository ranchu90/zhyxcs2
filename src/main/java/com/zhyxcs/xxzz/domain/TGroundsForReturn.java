package com.zhyxcs.xxzz.domain;

public class TGroundsForReturn {
    private Long sid;

    private String sgrounds;

    private String sgroundstate;

    public TGroundsForReturn(Long sid, String sgrounds, String sgroundstate) {
        this.sid = sid;
        this.sgrounds = sgrounds;
        this.sgroundstate = sgroundstate;
    }

    public TGroundsForReturn() {
        super();
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getSgrounds() {
        return sgrounds;
    }

    public void setSgrounds(String sgrounds) {
        this.sgrounds = sgrounds == null ? null : sgrounds.trim();
    }

    public String getSgroundstate() {
        return sgroundstate;
    }

    public void setSgroundstate(String sgroundstate) {
        this.sgroundstate = sgroundstate == null ? null : sgroundstate.trim();
    }
}