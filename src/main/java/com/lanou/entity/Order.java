package com.lanou.entity;

import java.util.List;

/**
 * Created by lanou on 2017/12/8.
 */
public class Order {

    private Integer oId;
    private String order_time;
    private int state;
    private Receive receive;
    private int receive_id;
    private int uId;
    private List<ShoppingCarItem> items; //订单的商品由购物车里的全部/部分商品组成
    private double totalMoney;

    public int getReceive_id() {
        return receive_id;
    }

    public void setReceive_id(int receive_id) {
        this.receive_id = receive_id;
    }

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Receive getReceive() {
        return receive;
    }

    public void setReceive(Receive receive) {
        this.receive = receive;
    }

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

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "oId=" + oId +
                ", order_time='" + order_time + '\'' +
                ", state=" + state +
                ", receive=" + receive +
                ", uId=" + uId +
                ", items=" + items +
                ", totalMoney=" + totalMoney +
                '}';
    }
}
