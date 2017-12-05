package com.lanou.service;

import com.lanou.entity.Goods;

import java.util.List;

/**
 * Created by lanou on 2017/12/2.
 */
public interface GoodsService {

    //根据第三层级的id查找商品
    public List<Goods> findGoodsByCategoryId(int gCategory_id);

<<<<<<< HEAD
    //分页查询
    public List<Goods> findGoodsByCategoryIdFenYe(int gCategory_id,int page);
=======
>>>>>>> 35fae93b2616c8467316510185db8c22882b70bb

    public List<Goods> findGoodsByCategoryIdRandom(int gCategory_id);

}
