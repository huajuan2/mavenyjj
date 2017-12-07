package com.lanou.entity;

/**
 * Created by lanou on 2017/12/2.
 */
public class Carousel {
    private Integer cId;
    private String cName;
    private Integer floor_id;

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

    public Integer getFloor_id() {
        return floor_id;
    }

    public void setFloor_id(Integer floor_id) {
        this.floor_id = floor_id;
    }

    public Carousel() {
    }

    @Override
    public String toString() {
        return "Carousel{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                ", floor_id=" + floor_id +
                '}';
    }
}
