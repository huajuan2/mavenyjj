package com.lanou.entity;

/**
 * Created by lanou on 2017/12/5.
 */
public class GoodsAndTab {

    private Integer goods_tabId;
    private Integer goods_tab_goodsId;
    private String goods_tabName;
    private String goods_tabName2;

    public Integer getGoods_tabId() {
        return goods_tabId;
    }

    public void setGoods_tabId(Integer goods_tabId) {
        this.goods_tabId = goods_tabId;
    }

    public Integer getGoods_tab_goodsId() {
        return goods_tab_goodsId;
    }

    public void setGoods_tab_goodsId(Integer goods_tab_goodsId) {
        this.goods_tab_goodsId = goods_tab_goodsId;
    }

    public String getGoods_tabName() {
        return goods_tabName;
    }

    public void setGoods_tabName(String goods_tabName) {
        this.goods_tabName = goods_tabName;
    }

    public String getGoods_tabName2() {
        return goods_tabName2;
    }

    public void setGoods_tabName2(String goods_tabName2) {
        this.goods_tabName2 = goods_tabName2;
    }

    public GoodsAndTab() {
    }

    @Override
    public String toString() {
        return "GoodsAndTab{" +
                "goods_tabId=" + goods_tabId +
                ", goods_tab_goodsId=" + goods_tab_goodsId +
                ", goods_tabName='" + goods_tabName + '\'' +
                ", goods_tabName2='" + goods_tabName2 + '\'' +
                '}';
    }
}
