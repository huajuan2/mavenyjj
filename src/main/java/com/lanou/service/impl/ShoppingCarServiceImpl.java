package com.lanou.service.impl;

import com.lanou.dao.DetailsMapper;
import com.lanou.dao.GoodsMapper;
import com.lanou.entity.Goods;
import com.lanou.entity.ShoppingCar;
import com.lanou.entity.ShoppingCarItem;
import com.lanou.service.ShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanou on 2017/12/5.
 */
@Service("shoppingCarService")
public class ShoppingCarServiceImpl implements ShoppingCarService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private DetailsMapper detailsMapper;

    public boolean addToShoppingCar(int gId, int count, int colorId,int sizeId, HttpServletRequest request) {

        boolean result = true;

        ShoppingCarItem item = new ShoppingCarItem();
        Goods goods = goodsMapper.findByGid(gId);
        item.setgId(gId);
        item.setgName(goods.getgName());
        item.setColor(detailsMapper.findColorBycId(colorId));
        item.setSize(detailsMapper.findGuigeBygId(sizeId));
        item.setImg(goods.getUrl());
        item.setPrice(goods.getPrice());
        item.setNum(count);
        item.setgStock(goods.getgStock());
        item.setSubtotal(count*goods.getPrice());
//        形成一个条目


        ShoppingCar sc = (ShoppingCar) request.getSession().getAttribute("shoppingCar");
        if(sc == null){
//            如果购物车为空，那就肯定没有买过上面的这个商品
            ShoppingCar car = new ShoppingCar();
            List<ShoppingCarItem> shoppingCarItems = new ArrayList<ShoppingCarItem>();
            shoppingCarItems.add(item);
            car.setItems(shoppingCarItems);
            car.setCount(count);
            car.setTotalMoney(count*goods.getPrice());
            car.setKinds(1);
            sc = car;
        }else{
//            购物车不为空
            List<ShoppingCarItem> items = sc.getItems();//现有购物车里的条目
            int i = 0;
            for(i=0;i<items.size();i++){
                if(items.get(i).getgId() == gId){
//                    如果购物车里已经有这类商品
                    result = false;//提示购物车里已经有这件商品了
                    break;
                }
            }
            if(i==items.size()){
//                如果购物车里没有这类商品
                items.add(item);
                sc.setCount(sc.getCount()+count);
                sc.setTotalMoney(sc.getTotalMoney()+count*goods.getPrice());
                sc.setKinds(sc.getKinds()+1);
            }

        }
        request.getSession().setAttribute("shoppingCar",sc);
        return result;

    }

//    数目加1
    public void addOne(int gId,HttpServletRequest request){
        ShoppingCar car = (ShoppingCar)request.getSession().getAttribute("shoppingCar");
        List<ShoppingCarItem> items = car.getItems();
        double addMoney = 0;
        for(int i=0;i<items.size();i++){
            if(gId == items.get(i).getgId()){
                items.get(i).setNum(items.get(i).getNum()+1);
                addMoney = items.get(i).getPrice();
                items.get(i).setSubtotal(items.get(i).getSubtotal()+addMoney);
                break;
            }
        }
        car.setCount(car.getCount()+1);
        car.setTotalMoney(car.getTotalMoney()+addMoney);
        request.getSession().setAttribute("shoppingCar",car);
    }

//    数目减
    public void reduceOne(int gId,HttpServletRequest request){
        ShoppingCar car = (ShoppingCar)request.getSession().getAttribute("shoppingCar");
        List<ShoppingCarItem> items = car.getItems();
        double reduceMoney = 0;
        for (int i=0;i<items.size();i++){
            if(gId == items.get(i).getgId()){
                items.get(i).setNum(items.get(i).getNum()-1);
                reduceMoney = items.get(i).getPrice();
                items.get(i).setSubtotal(items.get(i).getSubtotal()-reduceMoney);
                break;
            }
        }
        car.setCount(car.getCount()-1);
        car.setTotalMoney(car.getTotalMoney()-reduceMoney);
        request.getSession().setAttribute("shoppingCar",car);
    }

//    删除一类商品
    public void removeFromShoppingCar(int gId,HttpServletRequest request){
        ShoppingCar car = (ShoppingCar)request.getSession().getAttribute("shoppingCar");
        List<ShoppingCarItem> items = car.getItems();
        int removeCount = 0;
        double removeMoney = 0;
        for(int i=0;i<items.size();i++){
            if(gId == items.get(i).getgId()){
                removeCount = items.get(i).getNum();
                removeMoney = items.get(i).getSubtotal();
                items.remove(i);
                break;
            }
        }
        car.setCount(car.getCount()-removeCount);
        car.setTotalMoney(car.getTotalMoney()-removeMoney);
        car.setKinds(car.getKinds()-1);
        request.getSession().setAttribute("shoppingCar",car);
    }

//  删除选中商品
    public void removeBySelect(HttpServletRequest request){
        String gIds[] = request.getParameterValues("gId");
        ShoppingCar car = (ShoppingCar)request.getSession().getAttribute("shoppingCar");
        List<ShoppingCarItem> items = car.getItems();
        int removeCount = 0;
        double removeMoney = 0;
        for(int i=0;i<gIds.length;i++){
            for(int j=0;j<items.size();j++){
                if(Integer.parseInt(gIds[i])==items.get(j).getgId()){

                    removeCount = removeCount+items.get(i).getNum();
                    removeMoney = removeMoney+items.get(i).getSubtotal();
                    items.remove(j);
                    break;
                }
            }
        }
        car.setCount(car.getCount()-removeCount);
        car.setTotalMoney(car.getTotalMoney()-removeMoney);
        car.setKinds(car.getKinds()-gIds.length);
        request.getSession().setAttribute("shoppingCar",car);
    }
}
