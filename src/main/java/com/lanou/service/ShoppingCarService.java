package com.lanou.service;

import com.lanou.entity.ShoppingCar;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lanou on 2017/12/5.
 */
public interface ShoppingCarService {


    public boolean addToShoppingCar(int gId, int count, int colorId, int sizeId, HttpServletRequest request);

    public void addOne(int gId,int colorId,int sizeId,HttpServletRequest request);

    public void reduceOne(int gId,int colorId,int sizeId,HttpServletRequest request);

    public void removeFromShoppingCar(int gId,int colorId,int sizeId,HttpServletRequest request);

    public void removeBySelect(HttpServletRequest request);

    public ShoppingCar findShoppingCarByUid(int uId);

    public boolean addToShoppingCarWithUser(int gId, int count, int colorId, int sizeId,int uId);

    public void addOneWithUser(int gId, int colorId,int sizeId,int uId);
    public void reduceOneWithUser(int gId, int colorId,int sizeId,int uId);
    public void deleteOneWithUser(int gId, int colorId,int sizeId,int uId);

    public void prepareShoppingCar(HttpServletRequest request);
}
