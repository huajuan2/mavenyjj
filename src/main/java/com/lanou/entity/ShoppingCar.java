package com.lanou.entity;

import java.util.List;

/**
 * Created by lanou on 2017/12/5.
 */
public class ShoppingCar {


    private List<ShoppingCarItem> items;

    private int count; //总数

    private Double totalMoney;

    private int kinds;  //商品种类数

    public List<ShoppingCarItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCarItem> items) {
        this.items = items;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
                ", count=" + count +
                ", totalMoney=" + totalMoney +
                ", kinds=" + kinds +
                '}';
    }
}
