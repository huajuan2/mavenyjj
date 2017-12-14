package com.lanou.entity;

/**
 * Created by lanou on 2017/12/13.
 */
public class Manager {
    private Integer mId;
    private String mName;
    private String mPassword;

    public Manager(Integer mId, String mName, String mPassword) {
        this.mId = mId;
        this.mName = mName;
        this.mPassword = mPassword;
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mPassword='" + mPassword + '\'' +
                '}';
    }

    public Manager() {
    }
}
