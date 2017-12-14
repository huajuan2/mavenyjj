package com.lanou.controller;

import com.lanou.dao.OrderMapper;
import com.lanou.entity.Receive;
import com.lanou.service.OrderService;
import com.lanou.util.FastJson_All;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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
    @RequestMapping("/findAllOrders.do")
    public void findAllOrders(Integer oId, Integer state, Integer page, Integer count,String receiveName, HttpServletResponse response){
        FastJson_All.toJson(orderService.findOrdersByManager(oId,state,page,count,receiveName),response);
    }

    @RequestMapping("/deleteOrder.do")
    public void deleteOrder(int oId,HttpServletResponse response){
        boolean result = orderService.deleteOrder(oId);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result",result);
        FastJson_All.toJson(map,response);
    }

    @RequestMapping("/findOneOrder.do")
    public void findOneOrder(int oId,HttpServletResponse response){
        FastJson_All.toJson(orderService.findOneOrder(oId),response);
    }

    @RequestMapping("/findByDetails.do")
    public void findByDetails(HttpServletRequest request, Receive receive, HttpServletResponse response){
        FastJson_All.toJson(orderService.findByDetails(request,receive),response);
    }
}
