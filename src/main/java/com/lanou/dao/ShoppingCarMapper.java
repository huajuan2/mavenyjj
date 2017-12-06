package com.lanou.dao;

import com.lanou.entity.ShoppingCarItem;

import java.util.List;

/**
 * Created by lanou on 2017/12/6.
 */
public interface ShoppingCarMapper {


    public List<ShoppingCarItem> findShoppingCarByUid(int uId);
}
