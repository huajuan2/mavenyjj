package com.lanou.entity;

import java.util.List;

/**
 * Created by lanou on 2017/12/8.
 */
public class OrderGoods {

    private List<ShoppingCarItem> items;
    private int o_id;

    public List<ShoppingCarItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCarItem> items) {
        this.items = items;
    }

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public OrderGoods() {
    }

    @Override
    public String toString() {
        return "OrderGoods{" +
                "items=" + items +
                ", o_id=" + o_id +
                '}';
    }
}
