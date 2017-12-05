package com.lanou.service;

import com.lanou.entity.Goods;

import java.util.List;

/**
 * Created by lanou on 2017/12/2.
 */
public interface GoodsService {

    //根据第三层级的id查找商品
    public List<Goods> findGoodsByCategoryId(int gCategory_id);


    //分页查询
    public List<Goods> findGoodsByCategoryIdFenYe(int gCategory_id);


    public List<Goods> findGoodsByLikeName(String likeName);



    //根据价格排序(升序)
    public List<Goods> findGoodsByCategoryIdOderByPrice(int gCategory_id);

    //根据价格排序(降序)
    public List<Goods> findGoodsByCategoryIdOderByPriceDesc(int gCategory_id);

    //商品列表页根据销量Sale排序（降序）
    public List<Goods> findGoodsByCategoryIdOrderBySale(int gCategory_id);
}
