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
    private Integer gStock;
    private double gMaketPrice;
    private Integer gJiFen;
    private Integer gDeleteId;
    private List<Details> detailsList;
    private List<Color> colorList;
    private List<GuiGe> guiGeList;
    private List<User> userList;
    private List<Img> imgList;

    public Goods(Integer gId, String gName, Integer brandId, Integer categoryId, String url, double price, Integer gSale, Integer gStock, double gMaketPrice, Integer gJiFen, Integer gDeleteId, List<Details> detailsList, List<Color> colorList, List<GuiGe> guiGeList, List<User> userList, List<Img> imgList) {
        this.gId = gId;
        this.gName = gName;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.url = url;
        this.price = price;
        this.gSale = gSale;
        this.gStock = gStock;
        this.gMaketPrice = gMaketPrice;
        this.gJiFen = gJiFen;
        this.gDeleteId = gDeleteId;
        this.detailsList = detailsList;
        this.colorList = colorList;
        this.guiGeList = guiGeList;
        this.userList = userList;
        this.imgList = imgList;
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

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public Integer getgSale() {
        return gSale;
    }

    public void setgSale(Integer gSale) {
        this.gSale = gSale;
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

    public List<Details> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<Details> detailsList) {
        this.detailsList = detailsList;
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

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Img> getImgList() {
        return imgList;
    }

    public void setImgList(List<Img> imgList) {
        this.imgList = imgList;
    }

    public Integer getgDeleteId() {
        return gDeleteId;
    }

    public void setgDeleteId(Integer gDeleteId) {
        this.gDeleteId = gDeleteId;
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
                ", gStock=" + gStock +
                ", gMaketPrice=" + gMaketPrice +
                ", gJiFen=" + gJiFen +
                ", gDeleteId=" + gDeleteId +
                ", detailsList=" + detailsList +
                ", colorList=" + colorList +
                ", guiGeList=" + guiGeList +
                ", userList=" + userList +
                ", imgList=" + imgList +
                '}';
    }

    public Goods() {

    }

}
