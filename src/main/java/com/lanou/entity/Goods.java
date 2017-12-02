package com.lanou.entity;

/**
 * Created by lanou on 2017/12/2.
 */
public class Goods {

    private Integer gId;
    private String gName;
    private Integer categoryId;

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

    @Override
    public String toString() {
        return "Goods{" +
                "gId=" + gId +
                ", gName='" + gName + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }

    public Goods() {
    }
}
