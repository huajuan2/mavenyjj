package com.lanou.dao;

import com.lanou.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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


    //根据商品价格区间进行查找商品
    public List<Goods> findGoodsByCategoryAndPriceQuJian(Map<String,Object> map);

    //根据商品id查找商品
    public List<Goods> findGoodsById(Map<String,Object> map);


    //根据品牌id和第三层级的id集合查找商品
    public List<Goods> findGoodsByBrandId(Map<String,Object> map);




    //*****************************************************************************************//
    //******后台管理系统****//

    //根据条件分页展示商品
    public List<Goods> selectAllGoods(Map<String,Object> map);
    //得到获得的商品的ID的集合，得到总个数size
    public List<Integer> selectAllGoodsId(Map<String,Object> map);

    //根据商品的具体id查找商品
    public Goods selectGoodsById(int goodsId);


    //修改商品
    public boolean updateGoodsById(Map<String,Object> map);

    //删除商品（逻辑删除）
    public boolean luojiDelete(int goodsId);

    //----------------------------添加新的商品------------------------------------//
    public boolean addNewGoods(Map<String,Object> map);



    //查看回收站的商品
    public List<Goods> selectRecycleGoods(int page,int count);
    //查看回收站的商品
    public List<Integer> selectRecycleGoodsId();

    //把商品从回收站放回到商品（反删除）
    public boolean huiFuGoods(int goodsId);

    public List<Goods> selectRandom10();

    public List<String> findLikeList(String likeName);
}
