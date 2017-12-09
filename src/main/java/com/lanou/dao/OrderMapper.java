package com.lanou.dao;

import com.lanou.entity.Order;
import com.lanou.entity.ShoppingCarItem;

import java.util.List;
import java.util.Map;

/**
 * Created by lanou on 2017/12/8.
 */
public interface OrderMapper {

    public boolean addOrder(Order order);
    public int findNewId();

    public boolean addOrderGoods(Map<String,Object> map);

    public List<Order> findOrderByUid(int u_id);

    public List<ShoppingCarItem> findOrderGoodsByOid(int o_Id);

    public Order findOrderByOid(int oId);

    public int findOrderReceive(int oId);
}
