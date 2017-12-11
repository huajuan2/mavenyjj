package com.lanou.controller;

import com.lanou.entity.Receive;
import com.lanou.entity.ShoppingCar;
import com.lanou.entity.User;
import com.lanou.service.OrderService;
import com.lanou.service.ReceiveService;
import com.lanou.service.ShoppingCarService;
import com.lanou.util.FastJson_All;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
/**
 * Created by lanou on 2017/12/8.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

//    @Autowired
//    private ReceiveService receiveService;
//
//    @Autowired
//    private ShoppingCarService shoppingCarService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/confirmOrder.do")
    public void confirmOrder(HttpServletRequest request, HttpServletResponse response){

        FastJson_All.toJson(orderService.confirmOrder(request),response);
    }

    @RequestMapping("/submitOrder.do")
    public void submitOrder(Integer id,Receive receive,HttpServletRequest request, HttpServletResponse response){

        FastJson_All.toJson(orderService.submitOrder(receive,request),response);
    }

    @RequestMapping("/buyAtFirst.do")
    public void buyAtFirst(HttpServletRequest request,HttpServletResponse response){
        FastJson_All.toJson(orderService.buyAtFirst(request),response);
    }

    @RequestMapping("/findMyOrders.do")
    public void findMyOrders(HttpServletRequest request,HttpServletResponse response){
        FastJson_All.toJson(orderService.findMyOrder(request),response);
    }

    @RequestMapping("/findOneOrder.do")
    public void findOneOrder(int oId,HttpServletResponse response){
        FastJson_All.toJson(orderService.findOneOrder(oId),response);
    }

    @RequestMapping("/payForOrder.do")
    public void payForOrder(HttpServletRequest request,HttpServletResponse response){
        FastJson_All.toJson(orderService.payForOrder(request),response);
    }

    @RequestMapping("/cancelOrder.do")
    public void cancelOrder(HttpServletRequest request,HttpServletResponse response){
        Boolean result = orderService.cancelOrder(request);
        FastJson_All.toJson(result,response);
    }

    @RequestMapping("/findOrderByLimit.do")
    public void findOrderByLimit(HttpServletRequest request,HttpServletResponse response){
        FastJson_All.toJson(orderService.findOrderByLimit(request),response);
    }
}
