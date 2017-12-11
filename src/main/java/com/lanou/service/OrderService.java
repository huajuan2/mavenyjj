package com.lanou.service;

import com.lanou.entity.Order;
import com.lanou.entity.Receive;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by lanou on 2017/12/8.
 */
public interface OrderService {
    public Map<String,Object> confirmOrder(HttpServletRequest request);

    public Map<String,Object> submitOrder(Receive receive,HttpServletRequest request);

    public Map<String,Object> buyAtFirst(HttpServletRequest request);

    public List<Order> findMyOrder(HttpServletRequest request);

    public Order findOneOrder(int oId);

    public Map<String,Object> payForOrder(HttpServletRequest request);

    public boolean cancelOrder(HttpServletRequest request);

    public List<Order> findOrderByLimit(HttpServletRequest request);
}
