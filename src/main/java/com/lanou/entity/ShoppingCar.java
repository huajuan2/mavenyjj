package com.lanou.entity;

import java.util.List;

/**
 * Created by lanou on 2017/12/5.
 */
public class ShoppingCar {


    private List<ShoppingCarItem> items;

    private int counts; //总数

    private Double totalMoney;

    private int kinds;  //商品种类数

    private int uId; //用户id

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public List<ShoppingCarItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCarItem> items) {
        this.items = items;
    }


    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getKinds() {
        return kinds;
    }

    public void setKinds(int kinds) {
        this.kinds = kinds;
    }

    public ShoppingCar() {
    }


    @Override
    public String toString() {
        return "ShoppingCar{" +
                "items=" + items +
                ", counts=" + counts +
                ", totalMoney=" + totalMoney +
                ", kinds=" + kinds +
                ", uId=" + uId +
                '}';
    }
}
