package com.lanou.entity;

/**
 * Created by lanou on 2017/12/7.
 */
public class Img {
    private Integer imgId;
    private String imgName;
    private Integer imgStatus;
    private Integer goods_id;

    public Img(Integer imgId, String imgName, Integer imgStatus, Integer goods_id) {
        this.imgId = imgId;
        this.imgName = imgName;
        this.imgStatus = imgStatus;
        this.goods_id = goods_id;
    }

    public Img() {
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public Integer getImgStatus() {
        return imgStatus;
    }

    public void setImgStatus(Integer imgStatus) {
        this.imgStatus = imgStatus;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    @Override
    public String toString() {
        return "Img{" +
                "imgId=" + imgId +
                ", imgName='" + imgName + '\'' +
                ", imgStatus=" + imgStatus +
                '}';
    }
}
