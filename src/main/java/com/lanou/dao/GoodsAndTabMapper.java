package com.lanou.dao;

import java.util.List;

/**
 * Created by lanou on 2017/12/5.
 */
public interface GoodsAndTabMapper {

    //根据传过来的两个名字字段查询商品的id
    public List<Integer> findGoodsIdByName(String name1,String name2);
}
