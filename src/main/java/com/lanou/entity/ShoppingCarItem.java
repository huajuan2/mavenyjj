package com.lanou.entity;

/**
 * Created by lanou on 2017/12/5.
 */
public class ShoppingCarItem {

    private Integer gId;
    private Integer color_id;
    private Integer guige_id;
    private Integer s_id;

    private String gName;
    private String img;
    private String color;
    private String size;
    private Double price;
    private Integer num;
    private Integer gStock;
    private Double subtotal;

    public Integer getS_id() {
        return s_id;
    }

    public void setS_id(Integer s_id) {
        this.s_id = s_id;
    }

    public Integer getColor_id() {
        return color_id;
    }

    public void setColor_id(Integer color_id) {
        this.color_id = color_id;
    }

    public Integer getGuige_id() {
        return guige_id;
    }

    public void setGuige_id(Integer guige_id) {
        this.guige_id = guige_id;
    }

    public Integer getgStock() {
        return gStock;
    }

    public void setgStock(Integer gStock) {
        this.gStock = gStock;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public ShoppingCarItem() {
    }

    @Override
    public String toString() {
        return "ShoppingCarItem{" +
                "gId=" + gId +
                ", gName='" + gName + '\'' +
                ", img='" + img + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", subtotal=" + subtotal +
                '}';
    }
}
