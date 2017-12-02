package com.lanou.entity;

/**
 * Created by lanou on 2017/12/2.
 */
public class Carousel {
    private Integer cId;
    private String cName;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Carousel() {
    }

    @Override
    public String toString() {
        return "Carousel{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                '}';
    }
}
