package com.lanou.entity;

import java.util.List;

/**
 * Created by lanou on 2017/12/2.
 */
public class Goods {

    private Integer gId;
    private String gName;
    private Integer brandId;
    private Integer categoryId;
    private String url;
    private double price;
    private Integer gSale;
    private List<Details> detailsList;
    private List<User> userList;

    public Goods(Integer gId, String gName, Integer brandId, Integer categoryId, String url, double price, Integer gSale, List<Details> detailsList, List<User> userList) {
        this.gId = gId;
        this.gName = gName;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.url = url;
        this.price = price;
        this.gSale = gSale;
        this.detailsList = detailsList;
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public Integer getgSale() {
        return gSale;
    }
    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public void setgSale(Integer gSale) {
        this.gSale = gSale;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "gId=" + gId +
                ", gName='" + gName + '\'' +
                ", brandId=" + brandId +
                ", categoryId=" + categoryId +
                ", url='" + url + '\'' +
                ", price=" + price +
                ", gSale=" + gSale +
                ", detailsList=" + detailsList +
                ", userList=" + userList +
                '}';
    }

    public Goods() {

    }

}
