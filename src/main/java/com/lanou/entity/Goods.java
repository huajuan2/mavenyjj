package com.lanou.entity;

import java.util.List;

/**
 * Created by lanou on 2017/12/2.
 */
public class Goods {

    private Integer gId;
    private String gName;
    private Integer categoryId;
    private String url;
    private double price;
    private Integer gSale;
    private Integer gStock;
    private double gMaketPrice;
    private Integer gJiFen;
    private List<Details> detailsList;
    private List<Color> colorList;
    private List<GuiGe> guiGeList;
    private List<User> userList;

    public Goods(Integer gId, String gName, Integer categoryId, String url, double price, Integer gSale, Integer gStock, double gMaketPrice, Integer gJiFen, List<Details> detailsList, List<Color> colorList, List<GuiGe> guiGeList, List<User> userList) {
        this.gId = gId;
        this.gName = gName;
        this.categoryId = categoryId;
        this.url = url;
        this.price = price;
        this.gSale = gSale;
        this.gStock = gStock;
        this.gMaketPrice = gMaketPrice;
        this.gJiFen = gJiFen;
        this.detailsList = detailsList;
        this.colorList = colorList;
        this.guiGeList = guiGeList;
        this.userList = userList;
    }

    public List<Color> getColorList() {
        return colorList;
    }

    public void setColorList(List<Color> colorList) {
        this.colorList = colorList;
    }

    public List<GuiGe> getGuiGeList() {
        return guiGeList;
    }

    public void setGuiGeList(List<GuiGe> guiGeList) {
        this.guiGeList = guiGeList;
    }

    public Integer getgStock() {
        return gStock;
    }

    public void setgStock(Integer gStock) {
        this.gStock = gStock;
    }

    public double getgMaketPrice() {
        return gMaketPrice;
    }

    public void setgMaketPrice(double gMaketPrice) {
        this.gMaketPrice = gMaketPrice;
    }

    public Integer getgJiFen() {
        return gJiFen;
    }

    public void setgJiFen(Integer gJiFen) {
        this.gJiFen = gJiFen;
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

    public void setgSale(Integer gSale) {
        this.gSale = gSale;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "gId=" + gId +
                ", gName='" + gName + '\'' +
                ", categoryId=" + categoryId +
                ", url='" + url + '\'' +
                ", price=" + price +
                ", gSale=" + gSale +
                ", gStock=" + gStock +
                ", gMaketPrice=" + gMaketPrice +
                ", gJiFen=" + gJiFen +
                ", detailsList=" + detailsList +
                ", colorList=" + colorList +
                ", guiGeList=" + guiGeList +
                ", userList=" + userList +
                '}';
    }

    public Goods() {

    }

}
