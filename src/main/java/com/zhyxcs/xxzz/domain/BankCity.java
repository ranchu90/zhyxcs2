package com.zhyxcs.xxzz.domain;

public class BankCity {
    private String sbankcitycode;

    private String scityname;

    private String sbankareacode;

    public BankCity(String sbankcitycode, String scityname, String sbankareacode) {
        this.sbankcitycode = sbankcitycode;
        this.scityname = scityname;
        this.sbankareacode = sbankareacode;
    }

    public BankCity() {
        super();
    }

    public String getSbankcitycode() {
        return sbankcitycode;
    }

    public void setSbankcitycode(String sbankcitycode) {
        this.sbankcitycode = sbankcitycode == null ? null : sbankcitycode.trim();
    }

    public String getScityname() {
        return scityname;
    }

    public void setScityname(String scityname) {
        this.scityname = scityname == null ? null : scityname.trim();
    }

    public String getSbankareacode() {
        return sbankareacode;
    }

    public void setSbankareacode(String sbankareacode) {
        this.sbankareacode = sbankareacode == null ? null : sbankareacode.trim();
    }
}