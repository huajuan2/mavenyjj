package com.lanou.service.impl;

import com.lanou.dao.DetailsMapper;
import com.lanou.dao.GoodsMapper;
import com.lanou.dao.ShoppingCarMapper;
import com.lanou.entity.Goods;
import com.lanou.entity.ShoppingCar;
import com.lanou.entity.ShoppingCarItem;
import com.lanou.entity.User;
import com.lanou.service.ShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by lanou on 2017/12/5.
 */
@Service("shoppingCarService")
public class ShoppingCarServiceImpl implements ShoppingCarService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private DetailsMapper detailsMapper;


    @Autowired
    private ShoppingCarMapper shoppingCarMapper;

//    *************下面是不登录的情况*************
    public boolean addToShoppingCar(int gId, int count, int colorId,int sizeId, HttpServletRequest request) {

        boolean result = true;

        ShoppingCarItem item = new ShoppingCarItem();
        Goods goods = goodsMapper.findByGid(gId);
        item.setgId(gId);
        item.setgName(goods.getgName());
        item.setColor_id(colorId);
        item.setGuige_id(sizeId);
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
            car.setCounts(count);
            car.setTotalMoney(count*goods.getPrice());
            car.setKinds(1);
            sc = car;
        }else{
//            购物车不为空
            List<ShoppingCarItem> items = sc.getItems();//现有购物车里的条目
            int i = 0;
            for(i=0;i<items.size();i++){
                if(items.get(i).getgId() == gId && (items.get(i).getColor_id()== colorId) && (items.get(i).getGuige_id()==sizeId)){
//                    如果购物车里已经有这类商品
                    result = false;//提示购物车里已经有这件商品了
                    break;
                }
            }
            if(i==items.size()){
//                如果购物车里没有这类商品
                items.add(item);
                sc.setCounts(sc.getCounts()+count);
                sc.setTotalMoney(sc.getTotalMoney()+count*goods.getPrice());
                sc.setKinds(sc.getKinds()+1);
            }

        }
        request.getSession().setAttribute("shoppingCar",sc);
        return result;

    }

//    数目加1
    public void addOne(int gId, int colorId,int sizeId,HttpServletRequest request){
        ShoppingCar car = (ShoppingCar)request.getSession().getAttribute("shoppingCar");
        List<ShoppingCarItem> items = car.getItems();
        double addMoney = 0;
        for(int i=0;i<items.size();i++){
            if(items.get(i).getgId() == gId && (items.get(i).getColor_id()== colorId) && (items.get(i).getGuige_id()==sizeId)){
                items.get(i).setNum(items.get(i).getNum()+1);
                addMoney = items.get(i).getPrice();
                items.get(i).setSubtotal(items.get(i).getSubtotal()+addMoney);
                break;
            }
        }
        car.setCounts(car.getCounts()+1);
        car.setTotalMoney(car.getTotalMoney()+addMoney);
        request.getSession().setAttribute("shoppingCar",car);
    }

//    数目减
    public void reduceOne(int gId,int colorId,int sizeId,HttpServletRequest request){
        ShoppingCar car = (ShoppingCar)request.getSession().getAttribute("shoppingCar");
        List<ShoppingCarItem> items = car.getItems();
        double reduceMoney = 0;
        for (int i=0;i<items.size();i++){
            if(items.get(i).getgId() == gId && (items.get(i).getColor_id()== colorId) && (items.get(i).getGuige_id()==sizeId)){
                items.get(i).setNum(items.get(i).getNum()-1);
                reduceMoney = items.get(i).getPrice();
                items.get(i).setSubtotal(items.get(i).getSubtotal()-reduceMoney);
                break;
            }
        }
        car.setCounts(car.getCounts()-1);
        car.setTotalMoney(car.getTotalMoney()-reduceMoney);
        request.getSession().setAttribute("shoppingCar",car);
    }

//    删除一类商品
    public void removeFromShoppingCar(int gId,int colorId,int sizeId,HttpServletRequest request){
        ShoppingCar car = (ShoppingCar)request.getSession().getAttribute("shoppingCar");
        List<ShoppingCarItem> items = car.getItems();
        int removeCount = 0;
        double removeMoney = 0;
        for(int i=0;i<items.size();i++){
            if(items.get(i).getgId() == gId && (items.get(i).getColor_id()== colorId) && (items.get(i).getGuige_id()==sizeId)){
                removeCount = items.get(i).getNum();
                removeMoney = items.get(i).getSubtotal();
                items.remove(i);
                break;
            }
        }
        car.setCounts(car.getCounts()-removeCount);
        car.setTotalMoney(car.getTotalMoney()-removeMoney);
        car.setKinds(car.getKinds()-1);
        request.getSession().setAttribute("shoppingCar",car);
    }

//  删除选中商品
    public void removeBySelect(HttpServletRequest request){
        String gIds[] = request.getParameterValues("gId");
        String colorId[] = request.getParameterValues("colorId");
        String sizeId[] = request.getParameterValues("sizeId");
        ShoppingCar car = (ShoppingCar)request.getSession().getAttribute("shoppingCar");
        List<ShoppingCarItem> items = car.getItems();
        int removeCount = 0;
        double removeMoney = 0;
        for(int i=0;i<gIds.length;i++){
            for(int j=0;j<items.size();j++){
                if(Integer.parseInt(gIds[i])==items.get(j).getgId() && (Integer.parseInt(colorId[i])==items.get(j).getColor_id())
                        && (Integer.parseInt(sizeId[i])==items.get(j).getGuige_id())){

                    removeCount = removeCount+items.get(i).getNum();
                    removeMoney = removeMoney+items.get(i).getSubtotal();
                    items.remove(j);
                    break;
                }
            }
        }
        car.setCounts(car.getCounts()-removeCount);
        car.setTotalMoney(car.getTotalMoney()-removeMoney);
        car.setKinds(car.getKinds()-gIds.length);
        request.getSession().setAttribute("shoppingCar",car);
    }

//  ************下面是登录的情况***************

    public ShoppingCar findShoppingCarByUid(int uId) {
//        List<ShoppingCarItem> items = shoppingCarMapper.findShoppingCarByUid(uId);
//        if(items == null){
//            return null;
//        }
//        double totalMoney = 0;
//        int count = 0;
//        for (int i = 0;i<items.size();i++){
//            int gId = items.get(i).getgId();
//            Goods goods = goodsMapper.findByGid(gId);
//            items.get(i).setgName(goods.getgName());
//            items.get(i).setImg(goods.getUrl());
//            items.get(i).setPrice(goods.getPrice());
//            items.get(i).setgStock(goods.getgStock());
//            items.get(i).setColor(detailsMapper.findColorBycId(items.get(i).getColor_id()));
//            items.get(i).setSize(detailsMapper.findGuigeBygId(items.get(i).getGuige_id()));
//            items.get(i).setSubtotal((items.get(i).getNum())*(goods.getPrice()));
//            totalMoney = totalMoney+(items.get(i).getNum())*(goods.getPrice());
//            count = count + items.get(i).getNum();
//        }
//        ShoppingCar car = new ShoppingCar();
//        car.setItems(items);
//        car.setKinds(items.size());
//        car.setTotalMoney(totalMoney);
//        car.setCount(count);
//        return car;

        ShoppingCar car = shoppingCarMapper.findShoppingCarByUid(uId);
        if(car == null){
            return null;
        }
        List<ShoppingCarItem> items = car.getItems();
        double totalMoney = 0;
        int count = 0;
        for(int i=0;i<items.size();i++){
            int gId = items.get(i).getgId();
//            根据gId来填充ShoppingCarItem
            Goods goods = goodsMapper.findByGid(gId);
            items.get(i).setgName(goods.getgName());
            items.get(i).setImg(goods.getUrl());
            items.get(i).setPrice(goods.getPrice());
            items.get(i).setgStock(goods.getgStock());
            items.get(i).setColor(detailsMapper.findColorBycId(items.get(i).getColor_id()));
            items.get(i).setSize(detailsMapper.findGuigeBygId(items.get(i).getGuige_id()));
            items.get(i).setSubtotal((items.get(i).getNum())*(goods.getPrice()));
            totalMoney = totalMoney+(items.get(i).getNum())*(goods.getPrice());
            count = count+items.get(i).getNum();
        }

        return car;
    }

    public boolean addToShoppingCarWithUser(int gId,int count,int colorId,int sizeId,int uId){
        boolean result = true;

        ShoppingCarItem item = new ShoppingCarItem();
        Goods goods = goodsMapper.findByGid(gId);
        item.setgId(gId);
        item.setgName(goods.getgName());
        item.setColor_id(colorId);
        item.setGuige_id(sizeId);
        item.setColor(detailsMapper.findColorBycId(colorId));
        item.setSize(detailsMapper.findGuigeBygId(sizeId));
        item.setImg(goods.getUrl());
        item.setPrice(goods.getPrice());
        item.setNum(count);
        item.setgStock(goods.getgStock());
        item.setSubtotal(count*goods.getPrice());

        ShoppingCar car = shoppingCarMapper.findShoppingCarByUid2(uId);
        if(car == null){
//            登录的用户的购物车为空，这样填加到购物车的话要改动两张表
            ShoppingCar car2 = new ShoppingCar();
            car2.setuId(uId);
            shoppingCarMapper.addToShoppingCar(car2);
            int sId = shoppingCarMapper.findSid(uId);
            item.setS_id(sId);
            shoppingCarMapper.addToShoppingCarItem(item);
//            List<ShoppingCarItem> items = new ArrayList<ShoppingCarItem>();
//            items.add(item);
//            car2.setItems(items);
//            car2.setKinds(1);
//            car2.setCounts(count);
//            car2.setTotalMoney(count*goods.getPrice());
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("sId",sId);
            map.put("kinds",1);
            map.put("counts",count);
            map.put("totalMoney",count*goods.getPrice());
            shoppingCarMapper.fillShoppingCar(map);
        }else{
//            登录的用户的购物车不为空，这样添加到购物车的话只要改动一张表
            List<ShoppingCarItem> items2 = car.getItems();
            int i=0;
            for(i=0;i<items2.size();i++){
                if(gId == items2.get(i).getgId() && (colorId==items2.get(i).getColor_id())
                        && (sizeId == items2.get(i).getGuige_id())){
                    result = false;
                    break;
                }
            }
            if(i==items2.size()){
//                如果购物车里没有这类商品
                int sid = shoppingCarMapper.findSid(uId);
                item.setS_id(sid);
                shoppingCarMapper.addToShoppingCarItem(item);

                Map<String,Object> map = new HashMap<String,Object>();
                map.put("sId",sid);
                map.put("kinds",car.getKinds()+1);
                map.put("counts",car.getCounts()+count);
                map.put("totalMoney",car.getTotalMoney()+count*goods.getPrice());
                shoppingCarMapper.fillShoppingCar(map);
            }

        }

        return result;
    }

    public void addOneWithUser(int gId, int colorId,int sizeId,int uId){
        int sId = shoppingCarMapper.findSid(uId);
        ShoppingCar car = shoppingCarMapper.findShoppingCarByUid(uId);
        List<ShoppingCarItem> items = car.getItems();
        for(int i=0;i<items.size();i++){
            if(gId==items.get(i).getgId() && (colorId==items.get(i).getColor_id())
                    && (sizeId==items.get(i).getGuige_id())){
                Goods goods = goodsMapper.findByGid(gId);
//                items.get(i).setNum(items.get(i).getNum()+1);
                shoppingCarMapper.addOne(sId,gId,colorId,sizeId);
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("sId",sId);
                map.put("kinds",car.getKinds());
                map.put("counts",car.getCounts()+1);
                map.put("totalMoney",car.getTotalMoney()+goods.getPrice());
                shoppingCarMapper.fillShoppingCar(map);
            }
        }
    }

    public void reduceOneWithUser(int gId, int colorId,int sizeId,int uId){
        int sId = shoppingCarMapper.findSid(uId);
        ShoppingCar car = shoppingCarMapper.findShoppingCarByUid(uId);
        List<ShoppingCarItem> items = car.getItems();
        for(int i=0;i<items.size();i++){
            if(gId==items.get(i).getgId() && (colorId==items.get(i).getColor_id())
                    && (sizeId==items.get(i).getGuige_id())){
                Goods goods = goodsMapper.findByGid(gId);
//                items.get(i).setNum(items.get(i).getNum()+1);
                shoppingCarMapper.reduceOne(sId,gId,colorId,sizeId);
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("sId",sId);
                map.put("kinds",car.getKinds());
                map.put("counts",car.getCounts()-1);
                map.put("totalMoney",car.getTotalMoney()-goods.getPrice());
                shoppingCarMapper.fillShoppingCar(map);
            }
        }
    }

    public void deleteOneWithUser(int gId, int colorId,int sizeId,int uId){
        int sId = shoppingCarMapper.findSid(uId);
        ShoppingCar car = shoppingCarMapper.findShoppingCarByUid(uId);
        List<ShoppingCarItem> items = car.getItems();
        int deleteCount = 0;
        for(int i=0;i<items.size();i++){
            if(gId==items.get(i).getgId() && (colorId==items.get(i).getColor_id())
                    && (sizeId==items.get(i).getGuige_id())){
                deleteCount = items.get(i).getNum();
                break;
            }
        }
        Goods goods = goodsMapper.findByGid(gId);
        shoppingCarMapper.deleteOneKind(sId,gId,colorId,sizeId);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("sId",sId);
        map.put("kinds",car.getKinds()-1);
        map.put("counts",car.getCounts()-deleteCount);
        map.put("totalMoney",car.getTotalMoney()-goods.getPrice()*deleteCount);
        shoppingCarMapper.fillShoppingCar(map);
    }

    public void deleteBySelectWithUser(HttpServletRequest request){
        String gIds[] = request.getParameterValues("gId");
        String colorId[] = request.getParameterValues("colorId");
        String sizeId[] = request.getParameterValues("sizeId");

        User user = (User)request.getSession().getAttribute("user1");
        int uId = user.getuId();
        int sId = shoppingCarMapper.findSid(uId);
        ShoppingCar car = shoppingCarMapper.findShoppingCarByUid(uId);
        int length = gIds.length;
        List<Integer> ids = new ArrayList<Integer>();
        int removeCount = 0;
        double removeMoney = 0;
        for(int i=0;i<length;i++){
            Goods goods = goodsMapper.findByGid(Integer.parseInt(gIds[i]));
//            goods.getPrice();
            int id = shoppingCarMapper.selectId(sId,Integer.parseInt(gIds[i]),Integer.parseInt(colorId[i]),Integer.parseInt(sizeId[i]));
            ids.add(id);
            removeMoney += shoppingCarMapper.findNumById(id)*goods.getPrice();
            removeCount += shoppingCarMapper.findNumById(id);
        }
        System.out.println("要删除的id集合："+ids);
        shoppingCarMapper.deleteInId(ids);


        Map<String,Object> map = new HashMap<String,Object>();
        map.put("sId",sId);
        map.put("kinds",car.getKinds()-length);
        map.put("counts",car.getCounts()-removeCount);
        map.put("totalMoney",car.getTotalMoney()-removeMoney);
        shoppingCarMapper.fillShoppingCar(map);

    }




    public void prepareShoppingCar(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user1");
        ShoppingCar car = (ShoppingCar)request.getSession().getAttribute("shoppingCar");//session中的购物车
        int uId = user.getuId();
        ShoppingCar shoppingCar = shoppingCarMapper.findShoppingCarByUid(uId);//用户的购物车
        if(shoppingCar != null && car != null){
            int sId = shoppingCarMapper.findSid(uId);
            List<ShoppingCarItem> items = shoppingCar.getItems();//用户的购物车
            List<ShoppingCarItem> items2 = car.getItems();//session中的购物车
            for(int i=0;i<items2.size();i++){
                shoppingCar = shoppingCarMapper.findShoppingCarByUid(uId); //之所以要重新获取shoppingCar是因为每循环一次导致数据库里面的shoppingCar变动了
                int j=0;
                for(j=0;j<items.size();j++){
                    if(items2.get(i).getgId()==items.get(j).getgId() &&
                            items2.get(i).getColor_id() == items.get(j).getColor_id() &&
                            items2.get(i).getGuige_id() == items.get(j).getGuige_id()){
//                        session购物车的东西和用户购物车的东西重复了，改一张表
                            int gId = items2.get(i).getgId();
                            int addNum = items2.get(i).getNum();//session购物车一种商品的个数
                            Goods goods = goodsMapper.findByGid(gId);
                          //  double addMoney = addNum*goods.getPrice();
                            shoppingCarMapper.addMore(items.get(j).getNum()+addNum,sId,gId,items2.get(i).getColor_id(),items2.get(i).getGuige_id());
//                        Map<String,Object> map = new HashMap<String,Object>();
//                        map.put("sId",sId);
//                        map.put("kinds",shoppingCar.getKinds());
//                        map.put("counts",shoppingCar.getCounts()+addNum);
//                        map.put("totalMoney",shoppingCar.getTotalMoney()+addMoney);
//                        shoppingCarMapper.fillShoppingCar(map);
                        break;
                    }
                }
                if(j==items.size()){
//                    session购物车的东西和用户购物车的东西不重复
                    items2.get(i).setS_id(sId);
                    shoppingCarMapper.addToShoppingCarItem(items2.get(i));
//                    int gId = items2.get(i).getgId();
//                    Goods goods = goodsMapper.findByGid(gId);
//                    Map<String,Object> map = new HashMap<String,Object>();
//                    map.put("sId",sId);
//                    map.put("kinds",shoppingCar.getKinds()+1);
//                    map.put("counts",shoppingCar.getCounts()+items2.get(i).getNum());
//                    map.put("totalMoney",shoppingCar.getTotalMoney()+ items2.get(i).getNum()*goods.getPrice());
//                    shoppingCarMapper.fillShoppingCar(map);
                }
                Goods goods = goodsMapper.findByGid(items2.get(i).getgId());
                int addcount = items2.get(i).getNum();
                double addmoney = items2.get(i).getNum()*goods.getPrice();
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("sId",sId);
                map.put("counts",shoppingCar.getCounts()+addcount);
                map.put("totalMoney",shoppingCar.getTotalMoney()+addmoney);
                if(j==items.size()){
                    map.put("kinds",shoppingCar.getKinds()+1);
                }else{
                    map.put("kinds",shoppingCar.getKinds());
                }
                shoppingCarMapper.fillShoppingCar(map);
            }

        }else if(shoppingCar == null && car !=null){
                car.setuId(uId);
                shoppingCarMapper.addToShoppingCar(car);
            int sId = shoppingCarMapper.findSid(uId);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("sId",sId);
            map.put("kinds",car.getKinds());
            map.put("counts",car.getCounts());
            map.put("totalMoney",car.getTotalMoney());
            shoppingCarMapper.fillShoppingCar(map);

                for(int i=0;i<car.getItems().size();i++){
                    car.getItems().get(i).setS_id(sId);
                    shoppingCarMapper.addToShoppingCarItem(car.getItems().get(i));
                }
        }

    }



//    最近浏览
    public List<Goods> guessYouLike(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return null;
        }else{
            System.out.println(cookies);
            System.out.println("+++++++++:"+cookies.length);
            List<Goods> goods = new ArrayList<Goods>();
            for(int i=0;i<cookies.length;i++){
                if("userLike".equals(cookies[i].getName())){
                    String likes = cookies[i].getValue();
                    likes = likes.substring(1, likes.length() - 1);
                    String userlikes[] = likes.split(",");
                    for (int j=userlikes.length-1;j>=0;j--){
                        Goods good = goodsMapper.findByGid(Integer.parseInt(userlikes[j].trim()));
                        goods.add(good);
                    }
                }
            }
            return goods;
        }

    }
}
