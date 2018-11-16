package com.wonders.itemstaffmanage.vo;

public class LoginVo {
    private  String stId;
    private String stUsername;
    private String stPassword;

    public String getStUsername() {
        return stUsername;
    }

    public String getStId() {
        return stId;
    }

    public void setStId(String stId) {
        this.stId = stId;
    }

    public void setStUsername(String stUsername) {
        this.stUsername = stUsername;
    }

    public String getStPassword() {
        return stPassword;
    }

    public void setStPassword(String stPassword) {
        this.stPassword = stPassword;
    }
}
