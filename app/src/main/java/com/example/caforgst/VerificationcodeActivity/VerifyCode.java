package com.example.caforgst.VerificationcodeActivity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyCode {

    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("verify_code")
    @Expose
    private Integer verifycode;

    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("user_exist")
    @Expose
    private Integer userexist;

    @SerializedName("message")
    @Expose
    private String message;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getVerifycode() {
        return verifycode;
    }

    public void setVerifycode(Integer verifycode) {
        this.verifycode = verifycode;
    }

    public Integer getUserexist() {
        return userexist;
    }

    public void setUserexist(Integer userexist) {
        this.userexist = userexist;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
