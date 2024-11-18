package com.wing.httpdemo;

public class LoginPasswordRequest {
    private String mobile; // 手机号
    private String password; // 密码
    private String socialType; // 社交平台的类型（可选）
    private String socialCode; // 授权码（可选）
    private String socialState; // state（可选）


    // Getter 和 Setter
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSocialType() {
        return socialType;
    }

    public void setSocialType(String socialType) {
        this.socialType = socialType;
    }

    public String getSocialCode() {
        return socialCode;
    }

    public void setSocialCode(String socialCode) {
        this.socialCode = socialCode;
    }

    public String getSocialState() {
        return socialState;
    }

    public void setSocialState(String socialState) {
        this.socialState = socialState;
    }
}
