package com.lanou.service.impl;

import com.lanou.dao.*;
import com.lanou.entity.*;
import com.lanou.service.OrderService;
import com.lanou.service.ShoppingCarService;
import com.lanou.util.FastJson_All;
import javafx.beans.binding.ObjectExpression;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
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
        String isSave = request.getParameter("isSave");
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

    public Map<String,Object> payForOrder(HttpServletRequest request){
//      由两个入口进入，提交订单后直接付款+查看订单选择付款(都要传oId)
        int oId = Integer.parseInt(request.getParameter("oId"));
        //orderMapper.payForOrder(oId);//订单状态改为1：已付款
        User user = (User)request.getSession().getAttribute("user1");
        int uId = user.getuId();
        double leftMoney = orderMapper.findUserMoney(uId);
        double orderMoney = orderMapper.findOrderByOid(oId).getTotalMoney();
        Map<String,Object> info = new HashMap<String, Object>();
        if(leftMoney>=orderMoney){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("leftMoney",leftMoney-orderMoney);
            map.put("uId",uId);
            orderMapper.payMoney(map);
            orderMapper.payForOrder(oId);//订单状态改为1：已付款
            info.put("result",true);
        }else{
            info.put("result",false);
        }
        info.put("orderMoney",orderMoney);
        info.put("userMoney",leftMoney);
        return info;

    }


    public boolean cancelOrder(HttpServletRequest request){
//     手动取消
        int oId = Integer.parseInt(request.getParameter("oId"));
        return  orderMapper.cancelOrder(oId);

    } 



    public List<Order> findMyOrder(HttpServletRequest request){
//        在查看我的订单的时候， 如果超过一定期限自动取消订单（当前时间减去下单时间超过5h）

        Date nowDate = new Date();
        User user = (User)request.getSession().getAttribute("user1");
        int uId = user.getuId();
        List<Order> orders = orderMapper.findOrderByUid(uId);

        for(int i=0;i<orders.size();i++){

            String time = orders.get(i).getOrder_time();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date orderTime = null;
            try {
                 orderTime = format.parse(time);
            }catch (ParseException e){
                e.getStackTrace();
            }
            if(nowDate.getTime()-orderTime.getTime()>1000*60*60*5){
                orderMapper.cancelOrder(orders.get(i).getoId());
            }

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
        int o_id = order.getoId();
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
        order.setItems(items);
        int rId = orderMapper.findOrderReceive(oId);
        order.setReceive(receiveMapper.findReceiveById(rId));
        return order;
    }

    public List<Order> findOrderByLimit(HttpServletRequest request){
//        根据条件查询个人订单
        User user = (User)request.getSession().getAttribute("user1");
        int uId = user.getuId();
        String oId = request.getParameter("oId");
        Integer oId1 = null;
        if(oId != null){
            oId1 = Integer.parseInt(oId);
        }
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        String state = request.getParameter("state");
        String likeName = request.getParameter("likeName");
        Integer state1 = null;
        if(state !=null){
            state1 = Integer.parseInt(state);
        }

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("oId",oId1);
        map.put("beginTime",beginTime);
        map.put("endTime",endTime);
        map.put("state",state1);
        map.put("userId",uId);
        List<Integer> orderIds = orderMapper.findByLimit(map);
        List<Integer> orderIds1 = new ArrayList<Integer>();  //最后放满足条件的oId集合
        List<Order> orders = new ArrayList<Order>();  //最后放满足条件的Order集合
        for (int i=0;i<orderIds.size();i++){
            List<Integer>  gIds = orderMapper.findGidByOid(orderIds.get(i));
            Map<String,Object> map1 = new HashMap<String,Object>();
            map1.put("likeName",likeName);
            map1.put("list",gIds);
            if(orderMapper.findOrderByLikeName(map1)>0){
                orderIds1.add(orderIds.get(i));
            }
        }
        if(orderIds1.size()==0){
            return null;
        }else{
            orders = orderMapper.findOrderInOid(orderIds1);
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
        }
        return orders;
    }

 //****************后台管理系统****************
 //   查看所有逻辑存在的订单
    public List<Order> findOrdersByManager(Integer oId,Integer state,Integer page,Integer count,String receiveName){
        page = (page-1)*count;
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("oId",oId);
        map.put("state",state);
        map.put("page",page);
        map.put("count",count);
        List<Integer> oIds =  orderMapper.findOrderByManager(map);
        List<Integer> oIds2 = new ArrayList<Integer>();//用来装满足条件的oId集合
        List<Order> orders = new ArrayList<Order>();
        for (int i=0;i<oIds.size();i++){
            Map<String,Object> map1 = new HashMap<String,Object>();
            map1.put("receive_name",receiveName);
            map1.put("oId",oIds.get(i));
            if(orderMapper.findOrderByReceiveName(map1)>0){
                oIds2.add(oIds.get(i));
            }
        }
        if(oIds2.size()==0){
            return null;
        }else{
            orders = orderMapper.findOrderInOid(oIds2);
            for(int i=0;i<orders.size();i++){
//                int o_id = orders.get(i).getoId();
//                List<ShoppingCarItem> items = orderMapper.findOrderGoodsByOid(o_id);
//                for(int j=0;j<items.size();j++){
//                    int gId = items.get(j).getgId();
//                    Goods goods = goodsMapper.findByGid(gId);
//                    items.get(j).setgName(goods.getgName());
//                    items.get(j).setImg(goods.getUrl());
//                    items.get(j).setColor(detailsMapper.findColorBycId(items.get(j).getColor_id()));
//                    items.get(j).setSize(detailsMapper.findGuigeBygId(items.get(j).getGuige_id()));
//                    items.get(j).setgStock(goods.getgStock());
//                    items.get(j).setPrice(goods.getPrice());
//                    items.get(j).setSubtotal(items.get(j).getNum()*goods.getPrice());
//                }
                orders.get(i).setReceive(receiveMapper.findReceiveById(orders.get(i).getReceive_id()));
                //orders.get(i).setItems(items);
            }
        }
        return orders;

    }

//    逻辑删除订单
    public boolean deleteOrder(Integer oId){
        return orderMapper.deleteByLogic(oId);
    }

//    按详细内容查询
    public List<Order> findByDetails(HttpServletRequest request,Receive receive){

        String username = request.getParameter("username");
        String oId = request.getParameter("oId");
        Integer oId1 = null;
        if(oId != null){
            oId1 = Integer.parseInt(oId);
        }
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        String state = request.getParameter("state");
        Integer state1 =null;
        if(state != null){
            state1 = Integer.parseInt(state);
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("oId",oId1);
        map.put("beginTime",beginTime);
        map.put("endTime",endTime);
        map.put("state",state1);
        List<Integer> orderIds = orderMapper.findByLimit(map);
        System.out.println("集合1:"+orderIds);
        List<Integer> orderIds1 = new ArrayList<Integer>();  //最后放满足条件的oId集合
        List<Order> orders = new ArrayList<Order>();  //最后放满足条件(除购买人)的Order集合
        for(int i=0;i<orderIds.size();i++){
            Map<String,Object> map1 = new HashMap<String,Object>();
            map1.put("receive_name",receive.getName());
            map1.put("receive_area",receive.getArea());
            map1.put("receive_address",receive.getAddress());
            map1.put("receive_mobile",receive.getMobile());
            map1.put("receive_telephone",receive.getTelephone());
            map1.put("receive_code",receive.getCode());
            map1.put("oId",orderIds.get(i));
            if(orderMapper.selectByReceive(map1)>0){
                orderIds1.add(orderIds.get(i));
            }
        }
        if(orderIds1.size()==0){
            return null;
        }else{
            System.out.println(orderIds1);
            List<Integer> orderIds2 = new ArrayList<Integer>();
            for(int k=0;k<orderIds1.size();k++){
                Map<String,Object> map2 = new HashMap<String,Object>();
                map2.put("username",username);
                map2.put("oId",orderIds1.get(k));
                if(orderMapper.selectOrderByUserName(map2)>0){
                    orderIds2.add(orderIds1.get(k));
                }
            }
            orders = orderMapper.findOrderInOid(orderIds2);
            for(int i=0;i<orders.size();i++){
                orders.get(i).setReceive(receiveMapper.findReceiveById(orders.get(i).getReceive_id()));
            }
        }
        return orders;
    }
}
