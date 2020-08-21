package com.BookSharing.ui;

public class Bookdata {

    private String Bookname;
    private String AuthersName;
    private String Details;
    private String MobileNO;
    private String url;

    public String getBookname() {
        return Bookname;
    }

    public void setMaterialname(String materialname) {
        Bookname = materialname;
    }

    public String getAuthersName() {
        return AuthersName;
    }

    public void setSubjectName(String subjectname) {
        AuthersName = subjectname;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getMobileNO() {
        return MobileNO;
    }

    public void setMobileNO(String mobileNO) {
        MobileNO = mobileNO;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}