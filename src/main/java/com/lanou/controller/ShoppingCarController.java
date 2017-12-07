package com.lanou.controller;

import com.lanou.entity.ShoppingCar;
import com.lanou.entity.User;
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
 * Created by lanou on 2017/12/6.
 */
@Controller
@RequestMapping("/shoppingCar")
public class ShoppingCarController {

    @Autowired
    private ShoppingCarService shoppingCarService;

    @RequestMapping("/addToShoppingCar.do")
    public void addToShoppingCar(int gId, int count, int colorId, int sizeId, HttpServletRequest request,HttpServletResponse response){
        boolean result = shoppingCarService.addToShoppingCar(gId,count,colorId,sizeId,request);
        ShoppingCar car = (ShoppingCar)request.getSession().getAttribute("shoppingCar");
        Map<String,Object> map = new HashMap<String,Object>();
        if(result){
            map.put("msg","添加到购物车成功。");
        }
        else{
            map.put("msg","该商品已经在购物车中了");
        }
        int kinds = car.getKinds();
        double totalMoney = car.getTotalMoney();
        map.put("kinds",kinds);
        map.put("totalMoney",totalMoney);
        FastJson_All.toJson(map,response);
    }

    @RequestMapping("/findShoppingCar.do")
    public void findShoppingCar(HttpServletRequest request, HttpServletResponse response){
        User user = (User)request.getSession().getAttribute("user1");
        if(user == null){
            ShoppingCar car = (ShoppingCar)request.getSession().getAttribute("shoppingCar");
            FastJson_All.toJson(car,response);
        }else{
            int uId = user.getuId();
            FastJson_All.toJson(shoppingCarService.findShoppingCarByUid(uId),response);
        }

//        ShoppingCar car = (ShoppingCar)request.getSession().getAttribute("shoppingCar");
//        FastJson_All.toJson(car,response);
    }

    @RequestMapping("/addOne.do")
    public void addOne(int gId,int colorId, int sizeId,HttpServletRequest request,HttpServletResponse response){
        shoppingCarService.addOne(gId,colorId,sizeId,request);
        ShoppingCar car = (ShoppingCar)request.getSession().getAttribute("shoppingCar");
        FastJson_All.toJson(car,response);
    }

    @RequestMapping("/reduceOne.do")
    public void reduceOne(int gId,int colorId, int sizeId,HttpServletRequest request,HttpServletResponse response){
        shoppingCarService.reduceOne(gId,colorId,sizeId,request);
        ShoppingCar car = (ShoppingCar)request.getSession().getAttribute("shoppingCar");
        FastJson_All.toJson(car,response);
    }

    @RequestMapping("/removeOneKind.do")
    public void removeOneKind(int gId,int colorId, int sizeId,HttpServletRequest request,HttpServletResponse response){
        shoppingCarService.removeFromShoppingCar(gId,colorId,sizeId,request);
        ShoppingCar car = (ShoppingCar)request.getSession().getAttribute("shoppingCar");
        FastJson_All.toJson(car,response);
    }

    @RequestMapping("/removeBySelect.do")
    public void removeBySelect(HttpServletRequest request,HttpServletResponse response){
        shoppingCarService.removeBySelect(request);
        ShoppingCar car = (ShoppingCar)request.getSession().getAttribute("shoppingCar");
        FastJson_All.toJson(car,response);
    }
}
