package com.lanou.entity;

/**
 * Created by lanou on 2017/12/2.
 */
public class Details {

    private Integer detailsId;
    private String detailsColor;
    private String detailsImgUrl;
    private String detailsGuiGe;
    private Integer detailsStock;
    private Double detailsPrice;
    private Double detailsMarketPrice;
    private Integer detailsJiFen;
    private Integer detailsStatus;
    private Integer goods_id;

    public Integer getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(Integer detailsId) {
        this.detailsId = detailsId;
    }

    public String getDetailsColor() {
        return detailsColor;
    }

    public void setDetailsColor(String detailsColor) {
        this.detailsColor = detailsColor;
    }

    public String getDetailsImgUrl() {
        return detailsImgUrl;
    }

    public void setDetailsImgUrl(String detailsImgUrl) {
        this.detailsImgUrl = detailsImgUrl;
    }

    public String getDetailsGuiGe() {
        return detailsGuiGe;
    }

    public void setDetailsGuiGe(String detailsGuiGe) {
        this.detailsGuiGe = detailsGuiGe;
    }

    public Integer getDetailsStock() {
        return detailsStock;
    }

    public void setDetailsStock(Integer detailsStock) {
        this.detailsStock = detailsStock;
    }

    public Double getDetailsPrice() {
        return detailsPrice;
    }

    public void setDetailsPrice(Double detailsPrice) {
        this.detailsPrice = detailsPrice;
    }

    public Double getDetailsMarketPrice() {
        return detailsMarketPrice;
    }

    public void setDetailsMarketPrice(Double detailsMarketPrice) {
        this.detailsMarketPrice = detailsMarketPrice;
    }

    public Integer getDetailsJiFen() {
        return detailsJiFen;
    }

    public void setDetailsJiFen(Integer detailsJiFen) {
        this.detailsJiFen = detailsJiFen;
    }

    public Integer getDetailsStatus() {
        return detailsStatus;
    }

    public void setDetailsStatus(Integer detailsStatus) {
        this.detailsStatus = detailsStatus;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }


    public Details(Integer detailsId, String detailsColor, String detailsImgUrl, String detailsGuiGe, Integer detailsStock, Double detailsPrice, Double detailsMarketPrice, Integer detailsJiFen, Integer detailsStatus, Integer goods_id) {
        this.detailsId = detailsId;
        this.detailsColor = detailsColor;
        this.detailsImgUrl = detailsImgUrl;
        this.detailsGuiGe = detailsGuiGe;
        this.detailsStock = detailsStock;
        this.detailsPrice = detailsPrice;
        this.detailsMarketPrice = detailsMarketPrice;
        this.detailsJiFen = detailsJiFen;
        this.detailsStatus = detailsStatus;
        this.goods_id = goods_id;
    }

    public Details() {
    }

    @Override
    public String toString() {
        return "Details{" +
                "detailsId=" + detailsId +
                ", detailsColor='" + detailsColor + '\'' +
                ", detailsImgUrl='" + detailsImgUrl + '\'' +
                ", detailsGuiGe='" + detailsGuiGe + '\'' +
                ", detailsStock=" + detailsStock +
                ", detailsPrice=" + detailsPrice +
                ", detailsMarketPrice=" + detailsMarketPrice +
                ", detailsJiFen=" + detailsJiFen +
                ", detailsStatus=" + detailsStatus +
                ", goods_id=" + goods_id +
                '}';
    }
}
