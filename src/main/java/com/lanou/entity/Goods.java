package com.lanou.entity;

import java.util.List;

/**
 * Created by lanou on 2017/12/2.
 */
public class Goods {

    private Integer gId;
    private String gName;
    private Integer categoryId;
    private List<Details> detailsList;
    private List<User> userList;

    public Goods(Integer gId, String gName, Integer categoryId, List<Details> detailsList, List<User> userList) {
        this.gId = gId;
        this.gName = gName;
        this.categoryId = categoryId;
        this.detailsList = detailsList;
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<Details> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<Details> detailsList) {
        this.detailsList = detailsList;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "gId=" + gId +
                ", gName='" + gName + '\'' +
                ", categoryId=" + categoryId +
                ", detailsList=" + detailsList +
                ", userList=" + userList +
                '}';
    }

    public Goods() {

    }

}
