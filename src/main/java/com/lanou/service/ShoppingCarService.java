package com.lanou.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lanou on 2017/12/5.
 */
public interface ShoppingCarService {


    public boolean addToShoppingCar(int gId, int count, int colorId, int sizeId, HttpServletRequest request);

    public void addOne(int gId,HttpServletRequest request);

    public void reduceOne(int gId,HttpServletRequest request);

    public void removeFromShoppingCar(int gId,HttpServletRequest request);

    public void removeBySelect(HttpServletRequest request);
}
