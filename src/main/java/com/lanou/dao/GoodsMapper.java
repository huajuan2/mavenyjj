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


    public List<Integer> findGoodsIdByCategory1(int cId);

    public List<Integer> findGoodsIdByCategory2(int cId);

    public List<Goods> findGoodsInCategoryIdRandom(@Param("list")List<Integer> list);



    //分页查询
    public List<Goods> findGoodsByCategoryIdFenYe(@Param("list")List<Integer> list);


    //根据价格排序
    public List<Goods> findGoodsByCategoryIdOderByPrice(@Param("list")List<Integer> list);

    //根据价格排序
    public List<Goods> findGoodsByCategoryIdOderByPriceDesc(@Param("list")List<Integer> list);

    //商品列表页根据销量Sale排序（降序）
    public List<Goods> findGoodsByCategoryIdOrderBySale(@Param("list")List<Integer> list);


    //分页查询
    public List<Goods> findGoodsByCategoryIdFenYe(@Param("list")List<Integer> list,int num);

//    模糊查询
    public List<Goods> findByLikeName(String likeName);

//    根据商品Id查商品
    public Goods findByGid(int gId);

    public List<Goods> findTuangou(int page);

    public List<Goods> findAllTuangou();
    public List<Goods> findTemai(int page);
    public List<Goods> findAllTemai();
}
