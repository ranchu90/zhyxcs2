package com.zhyxcs.xxzz.domain;

public class AccountManageSys {
    private String sdepositorname;

    private String sregisterareacode;

    private String sstatutoryname;

    private String ssuperiorunit;

    private String ssuperiorstatutoryaccountapprovalcode;

    private String ssuperiorstatutoryname;

    private String sdepositorsupportdocumentfirstcode;

    private String saccountopenbankcode;

    private String saccountkind;

    private String saccountnum;

    private String saccountname;

    private String saccountopendate;

    private String saccountclosedate;

    private String saccountstatus;

    public AccountManageSys(String sdepositorname, String sregisterareacode, String sstatutoryname, String ssuperiorunit, String ssuperiorstatutoryaccountapprovalcode, String ssuperiorstatutoryname, String sdepositorsupportdocumentfirstcode, String saccountopenbankcode, String saccountkind, String saccountnum, String saccountname, String saccountopendate, String saccountclosedate, String saccountstatus) {
        this.sdepositorname = sdepositorname;
        this.sregisterareacode = sregisterareacode;
        this.sstatutoryname = sstatutoryname;
        this.ssuperiorunit = ssuperiorunit;
        this.ssuperiorstatutoryaccountapprovalcode = ssuperiorstatutoryaccountapprovalcode;
        this.ssuperiorstatutoryname = ssuperiorstatutoryname;
        this.sdepositorsupportdocumentfirstcode = sdepositorsupportdocumentfirstcode;
        this.saccountopenbankcode = saccountopenbankcode;
        this.saccountkind = saccountkind;
        this.saccountnum = saccountnum;
        this.saccountname = saccountname;
        this.saccountopendate = saccountopendate;
        this.saccountclosedate = saccountclosedate;
        this.saccountstatus = saccountstatus;
    }

    public AccountManageSys() {
        super();
    }

    public String getSdepositorname() {
        return sdepositorname;
    }

    public void setSdepositorname(String sdepositorname) {
        this.sdepositorname = sdepositorname == null ? null : sdepositorname.trim();
    }

    public String getSregisterareacode() {
        return sregisterareacode;
    }

    public void setSregisterareacode(String sregisterareacode) {
        this.sregisterareacode = sregisterareacode == null ? null : sregisterareacode.trim();
    }

    public String getSstatutoryname() {
        return sstatutoryname;
    }

    public void setSstatutoryname(String sstatutoryname) {
        this.sstatutoryname = sstatutoryname == null ? null : sstatutoryname.trim();
    }

    public String getSsuperiorunit() {
        return ssuperiorunit;
    }

    public void setSsuperiorunit(String ssuperiorunit) {
        this.ssuperiorunit = ssuperiorunit == null ? null : ssuperiorunit.trim();
    }

    public String getSsuperiorstatutoryaccountapprovalcode() {
        return ssuperiorstatutoryaccountapprovalcode;
    }

    public void setSsuperiorstatutoryaccountapprovalcode(String ssuperiorstatutoryaccountapprovalcode) {
        this.ssuperiorstatutoryaccountapprovalcode = ssuperiorstatutoryaccountapprovalcode == null ? null : ssuperiorstatutoryaccountapprovalcode.trim();
    }

    public String getSsuperiorstatutoryname() {
        return ssuperiorstatutoryname;
    }

    public void setSsuperiorstatutoryname(String ssuperiorstatutoryname) {
        this.ssuperiorstatutoryname = ssuperiorstatutoryname == null ? null : ssuperiorstatutoryname.trim();
    }

    public String getSdepositorsupportdocumentfirstcode() {
        return sdepositorsupportdocumentfirstcode;
    }

    public void setSdepositorsupportdocumentfirstcode(String sdepositorsupportdocumentfirstcode) {
        this.sdepositorsupportdocumentfirstcode = sdepositorsupportdocumentfirstcode == null ? null : sdepositorsupportdocumentfirstcode.trim();
    }

    public String getSaccountopenbankcode() {
        return saccountopenbankcode;
    }

    public void setSaccountopenbankcode(String saccountopenbankcode) {
        this.saccountopenbankcode = saccountopenbankcode == null ? null : saccountopenbankcode.trim();
    }

    public String getSaccountkind() {
        return saccountkind;
    }

    public void setSaccountkind(String saccountkind) {
        this.saccountkind = saccountkind == null ? null : saccountkind.trim();
    }

    public String getSaccountnum() {
        return saccountnum;
    }

    public void setSaccountnum(String saccountnum) {
        this.saccountnum = saccountnum == null ? null : saccountnum.trim();
    }

    public String getSaccountname() {
        return saccountname;
    }

    public void setSaccountname(String saccountname) {
        this.saccountname = saccountname == null ? null : saccountname.trim();
    }

    public String getSaccountopendate() {
        return saccountopendate;
    }

    public void setSaccountopendate(String saccountopendate) {
        this.saccountopendate = saccountopendate == null ? null : saccountopendate.trim();
    }

    public String getSaccountclosedate() {
        return saccountclosedate;
    }

    public void setSaccountclosedate(String saccountclosedate) {
        this.saccountclosedate = saccountclosedate == null ? null : saccountclosedate.trim();
    }

    public String getSaccountstatus() {
        return saccountstatus;
    }

    public void setSaccountstatus(String saccountstatus) {
        this.saccountstatus = saccountstatus == null ? null : saccountstatus.trim();
    }
}