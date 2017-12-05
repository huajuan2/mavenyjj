package com.lanou.dao;

import com.lanou.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lanou on 2017/12/2.
 */
public interface GoodsMapper {
    //根据第三层级的id查找商品
    public List<Goods> findGoodsByCategoryId(int gCategory_id);

    public List<Integer> findGoodsIdByCategory1Random(int cId);

    public List<Integer> findGoodsIdByCategory2Random(int cId);

    public List<Goods> findGoodsInCategoryId(@Param("list")List<Integer> list);

    //根据第三层级的id查找商品，随机8个
    public List<Goods> findGoodsByCategoryIdRandom(int gCategory_id);


    //分页查询
    public List<Goods> findGoodsByCategoryIdFenYe(@Param("list")List<Integer> list,int num);
}
