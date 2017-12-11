package com.lanou.service;

import com.lanou.entity.Goods;


import java.util.List;
import java.util.Map;

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


    public List<Goods> findTuangou(int page);

    public List<Goods> findAllTuangou();

    public List<Goods> findTemai(int page);

    public List<Goods> findAllTemai();

    //根据商品价格区间进行查找商品
    public List<Goods> findGoodsByCategoryAndPriceQuJian(int gCategory_id,double price1,double price2);

    //根据商品id集合查找商品
    public List<Goods> findGoodsById(Map<String,Object> map);

    //根据品牌id和第三层级的id查找商品
    public List<Goods> findGoodsByBrandId(int gCategory_id,int brandId);


    //*****************************************************************************************//
    //****后台管理系统****//

    //根据条件分页展示商品
    public List<Goods> selectAllGoods(int page,List<Integer> list,int count,int brandId,String likeName);
    //得到获得的商品的ID的集合，得到总个数size
    public List<Integer> selectAllGoodsId(List<Integer> list,int brandId,String likeName);

    //根据商品的具体id查找商品
    public Goods selectGoodsById(int goodsId);


    //修改商品
    public boolean updateGoodsById(Goods goods);

    //删除商品（逻辑删除）
    public boolean luojiDelete(int goodsId);

    //----------------------------添加新的商品------------------------------------//
    //添加新商品
    public boolean addNewGoods(Goods goods);



    //查看回收站的商品
    public List<Goods> selectRecycleGoods(int page,int count);
    //查看回收站的商品
    public List<Integer> selectRecycleGoodsId();

    //把商品从回收站放回到商品（反删除）
    public boolean huiFuGoods(int goodsId);

    public List<Goods> selectBottomGoods();


}
