package com.lanou.dao;

import com.lanou.entity.ShoppingCar;
import com.lanou.entity.ShoppingCarItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by lanou on 2017/12/6.
 */
public interface ShoppingCarMapper {


    public ShoppingCar findShoppingCarByUid(int uId);

    public void addToShoppingCarItem(ShoppingCarItem item);

    public void addToShoppingCar(ShoppingCar car);

    public int findSid(int uId);

    public void fillShoppingCar(Map<String,Object> map);

    public void addOne(int sId,int g_id,int color_id, int guige_id);

    public void reduceOne(int sId,int g_id,int color_id, int guige_id);

    public void deleteOneKind(int sId,int g_id,int color_id, int guige_id);

    public void addMore(int num,int sId,int g_id,int color_id, int guige_id);

    public int selectId(int sId,int g_id,int color_id, int guige_id);

    public void deleteInId(@Param("list")List<Integer> ids);

    public int findNumById(int id);

    public List<ShoppingCarItem> selectInId(@Param("list")List<Integer> ids);
}
