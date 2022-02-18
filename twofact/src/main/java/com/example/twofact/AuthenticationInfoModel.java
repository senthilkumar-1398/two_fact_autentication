package com.example.twofact;

public class AuthenticationInfoModel {
    public int status;
    public String uniqueNo;
    public int autoGenCode;

    public AuthenticationInfoModel() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUniqueNo() {
        return uniqueNo;
    }

    public void setUniqueNo(String uniqueNo) {
        this.uniqueNo = uniqueNo;
    }

    public int getAutoGenCode() {
        return autoGenCode;
    }

    public void setAutoGenCode(int autoGenCode) {
        this.autoGenCode = autoGenCode;
    }

}
