package com.lanou.dao;

import com.lanou.entity.Goods;

import java.util.List;

/**
 * Created by lanou on 2017/12/2.
 */
public interface GoodsMapper {
    //根据第三层级的id查找商品
    public List<Goods> findGoodsByCategoryId(int gCategory_id);

    public List<Goods> findGoodsByCategoryIdRandom(int gCategory_id);
}
