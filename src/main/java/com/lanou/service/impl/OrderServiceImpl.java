package com.lanou.service.impl;

import com.lanou.dao.*;
import com.lanou.entity.*;
import com.lanou.service.OrderService;
import com.lanou.service.ShoppingCarService;
import com.lanou.util.FastJson_All;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.HashMap;
/**
 * Created by lanou on 2017/12/8.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService{

    @Autowired
    private ShoppingCarMapper shoppingCarMapper;

    @Autowired
    private ReceiveMapper receiveMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private DetailsMapper detailsMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ShoppingCarService shoppingCarService;

//    立即购买
    public Map<String,Object> buyAtFirst(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user1");
        int gId = Integer.parseInt(request.getParameter("gId"));
        int colorId = Integer.parseInt(request.getParameter("colorId"));
        int sizeId = Integer.parseInt(request.getParameter("sizeId"));
        int counts = Integer.parseInt(request.getParameter("count"));
        ShoppingCarItem item = new ShoppingCarItem();
        Goods goods = goodsMapper.findByGid(gId);
        item.setgId(gId);
        item.setgName(goods.getgName());
        item.setImg(goods.getUrl());
        item.setPrice(goods.getPrice());
        item.setNum(counts);
        item.setgStock(goods.getgStock());
        item.setColor_id(colorId);
        item.setColor(detailsMapper.findColorBycId(colorId));
        item.setGuige_id(sizeId);
        item.setSize(detailsMapper.findGuigeBygId(sizeId));
        item.setSubtotal(counts*goods.getPrice());

        List<ShoppingCarItem> items = new ArrayList<ShoppingCarItem>();
        items.add(item);
        List<Receive> receives =  receiveMapper.findReceivesByUid(user.getuId());
        double totalMoney = counts*goods.getPrice();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("receives",receives);
        map.put("shoppingGoods",items);
        map.put("totalMoney",totalMoney);
        request.getSession().setAttribute("shoppingGoods",items);
        request.getSession().setAttribute("totalMoney",totalMoney);

        return map;
    }


//    从购物车里购买
    public Map<String,Object> confirmOrder(HttpServletRequest request) {
        String[] gIds = request.getParameterValues("gId");
        String[] colorId = request.getParameterValues("colorId");
        String[] sizeId = request.getParameterValues("sizeId");
        User user = (User)request.getSession().getAttribute("user1");
        int uId = user.getuId();
        int sId = shoppingCarMapper.findSid(uId);
//        ShoppingCar car = shoppingCarMapper.findShoppingCarByUid(uId);
        int length = gIds.length;
        List<Integer> ids = new ArrayList<Integer>();
//        List<ShoppingCarItem> items = new ArrayList<ShoppingCarItem>();
        for(int i=0;i<length;i++){
//            for(int j=0;j<car.getItems().size();j++){
//                if(Integer.parseInt(gIds[i])==car.getItems().get(i).getgId() &&
//                        (Integer.parseInt(colorId[i])==car.getItems().get(i).getColor_id()) &&
//                        (Integer.parseInt(sizeId[i]) ==car.getItems().get(i).getGuige_id())){
//                    items.add(car.getItems().get(i));
//                    break;
//                }
//            }
            int id = shoppingCarMapper.selectId(sId,Integer.parseInt(gIds[i]),Integer.parseInt(colorId[i]),Integer.parseInt(sizeId[i]));
            ids.add(id);
        }
        List<ShoppingCarItem> items = shoppingCarMapper.selectInId(ids);
        List<Receive> receives = receiveMapper.findReceivesByUid(uId);
        double totalMoney = 0;
        for(int i=0;i<items.size();i++){
           Goods goods = goodsMapper.findByGid(items.get(i).getgId());
           items.get(i).setgName(goods.getgName());
           items.get(i).setImg(goods.getUrl());
           items.get(i).setColor(detailsMapper.findColorBycId(items.get(i).getColor_id()));
           items.get(i).setSize(detailsMapper.findGuigeBygId(items.get(i).getGuige_id()));
           items.get(i).setPrice(goods.getPrice());
           items.get(i).setgStock(goods.getgStock());
           items.get(i).setSubtotal(items.get(i).getNum()*goods.getPrice());
           totalMoney+=items.get(i).getNum()*goods.getPrice();
        }


        Map<String,Object> map = new HashMap<String,Object>();
        map.put("receives",receives);
        map.put("shoppingGoods",items);
        map.put("totalMoney",totalMoney);
        request.getSession().setAttribute("gIds",gIds);
        request.getSession().setAttribute("colorId",colorId);
        request.getSession().setAttribute("sizeId",sizeId);
        request.getSession().setAttribute("shoppingGoods",items);
        request.getSession().setAttribute("totalMoney",totalMoney);
        request.getSession().setAttribute("isFromShoppingCar","yes");
        return map;
    }

    public Map<String,Object> submitOrder(Receive newReceive, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user1");
        String isSave = (String)request.getSession().getAttribute("isSave");
        double totalMoney = (Double)request.getSession().getAttribute("totalMoney");
        List<ShoppingCarItem> items = (List<ShoppingCarItem>)request.getSession().getAttribute("shoppingGoods");
        String id = request.getParameter("id");
        int oId = 0;
        Order order = new Order();
        if(id==null){
//            用户选的是新地址
            newReceive.setU_id(user.getuId());
            receiveMapper.addOneTime(newReceive);
            int rId = receiveMapper.findNewId();
            order.setReceive_id(rId);
            if("yes".equals(isSave)){
                receiveMapper.saveOneTime(rId);
            }

        }else {
//            用户选的是已有的地址
            Receive receive = receiveMapper.findReceiveById(Integer.parseInt(id));
            order.setReceive_id(receive.getId());
        }
            order.setuId(user.getuId());
            order.setState(0);//0：未付款
            order.setTotalMoney(totalMoney);
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String orderTime = format.format(date);
            order.setOrder_time(orderTime);
            orderMapper.addOrder(order);
            oId = orderMapper.findNewId();//获取到刚刚新增的订单表记录的id
            Map<String,Object> map = new HashMap<String,Object>();
            for(int i=0;i<items.size();i++){
                int gId = items.get(i).getgId();
                int num = items.get(i).getNum();
                int colorId = items.get(i).getColor_id();
                int guigeId = items.get(i).getGuige_id();
                map.put("g_id",gId);
                map.put("num",num);
                map.put("color_id",colorId);
                map.put("guige_id",guigeId);
                map.put("o_id",oId);
                orderMapper.addOrderGoods(map);
            }


//        订单提交成功后要在购物车中把放到订单中的商品删除，还要清空session里的shoppingGoods和totalMoney
        String isFromShoppingCar = (String) request.getSession().getAttribute("isFromShoppingCar");
        if("yes".equals(isFromShoppingCar)) {
//            是从购物车页面进来的
            String[] gIds = (String[]) request.getSession().getAttribute("gIds");
            String[] colorId = (String[]) request.getSession().getAttribute("colorId");
            String[] sizeId = (String[]) request.getSession().getAttribute("sizeId");

            int uId = user.getuId();
            int sId = shoppingCarMapper.findSid(uId);
            ShoppingCar car = shoppingCarMapper.findShoppingCarByUid(uId);
            int length = gIds.length;
            List<Integer> ids = new ArrayList<Integer>();
            int removeCount = 0;
            double removeMoney = 0;
            for (int i = 0; i < length; i++) {
                Goods goods = goodsMapper.findByGid(Integer.parseInt(gIds[i]));
//            goods.getPrice();
                int id1 = shoppingCarMapper.selectId(sId, Integer.parseInt(gIds[i]), Integer.parseInt(colorId[i]), Integer.parseInt(sizeId[i]));
                ids.add(id1);
                removeMoney += shoppingCarMapper.findNumById(id1) * goods.getPrice();
                removeCount += shoppingCarMapper.findNumById(id1);
            }
            shoppingCarMapper.deleteInId(ids);

            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("sId", sId);
            map2.put("kinds", car.getKinds() - length);
            map2.put("counts", car.getCounts() - removeCount);
            map2.put("totalMoney", car.getTotalMoney() - removeMoney);
            shoppingCarMapper.fillShoppingCar(map2);
        }
        request.getSession().setAttribute("isFromShoppingCar",null);
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("orderId",oId);
        map1.put("totalMoney",totalMoney);
        return map1;
    }

    public List<Order> findMyOrder(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user1");
        int uId = user.getuId();
        List<Order> orders = orderMapper.findOrderByUid(uId);
        for(int i=0;i<orders.size();i++){
            int o_id = orders.get(i).getoId();
            List<ShoppingCarItem> items = orderMapper.findOrderGoodsByOid(o_id);
            for(int j=0;j<items.size();j++){
                int gId = items.get(j).getgId();
                Goods goods = goodsMapper.findByGid(gId);
                items.get(j).setgName(goods.getgName());
                items.get(j).setImg(goods.getUrl());
                items.get(j).setColor(detailsMapper.findColorBycId(items.get(j).getColor_id()));
                items.get(j).setSize(detailsMapper.findGuigeBygId(items.get(j).getGuige_id()));
                items.get(j).setgStock(goods.getgStock());
                items.get(j).setPrice(goods.getPrice());
                items.get(j).setSubtotal(items.get(j).getNum()*goods.getPrice());
            }
            orders.get(i).setItems(items);
        }
        return orders;
    }

    public Order findOneOrder(int oId){
//        找单个订单要把收货地址展示出来
        Order order = orderMapper.findOrderByOid(oId);
        order.setItems(orderMapper.findOrderGoodsByOid(oId));
        int rId = orderMapper.findOrderReceive(oId);
        order.setReceive(receiveMapper.findReceiveById(rId));
        return order;
    }

}
