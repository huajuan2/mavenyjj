package com.lanou.controller;

import com.lanou.dao.OrderMapper;
import com.lanou.service.OrderService;
import com.lanou.util.FastJson_All;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by lanou on 2017/12/12.
 */
@Controller
@RequestMapping("/orderManage")
public class OrderManageController {

//    后台管理系统--订单
    @Autowired
    private OrderService orderService;
//    按照条件展示订单
//    @RequestMapping("/findAllOrders.do")
//    public void findAllOrders(Integer oId, Integer state, Integer page, Integer count, HttpServletResponse response){
//        FastJson_All.toJson(orderService.findOrdersByManager(oId,state,page,count),response);
//    }
}
