package com.lanou.dao;

import com.lanou.entity.Order;
import com.lanou.entity.ShoppingCarItem;
import org.apache.ibatis.annotations.Param;

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

    public boolean payForOrder(int oId);

    public double findUserMoney(int uId);

    public boolean payMoney(Map<String,Object> map);

    public boolean cancelOrder(int oId);

    public Integer findIfBuying(Map<String,Object> map);

    public List<Integer> findBuyingOidByUid(int u_id);

    public List<Integer> findByLimit(Map<String,Object> map);

    public Integer findOrderByLikeName(Map<String,Object> map);

    public List<Integer> findGidByOid(int oId);

    public List<Order> findOrderInOid(@Param("list")List<Integer> list);

//    订单管理
    public List<Integer> findOrderByManager(Map<String,Object> map);

    public Integer findOrderByReceiveName(Map<String,Object> map);
}
