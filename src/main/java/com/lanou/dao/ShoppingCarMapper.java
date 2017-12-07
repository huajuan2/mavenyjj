package com.lanou.dao;

import com.lanou.entity.ShoppingCar;
import com.lanou.entity.ShoppingCarItem;

import java.util.List;

/**
 * Created by lanou on 2017/12/6.
 */
public interface ShoppingCarMapper {


    public ShoppingCar findShoppingCarByUid(int uId);
}
