package com.lanou.service.impl;

import com.lanou.dao.CategoryMapper;
import com.lanou.dao.GoodsMapper;
import com.lanou.entity.Brand;
import com.lanou.entity.Category;
import com.lanou.entity.Goods;
import com.lanou.service.CategoryService;
import com.lanou.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by lanou on 2017/12/2.
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryService categoryService;

    public List<Goods> findGoodsByCategoryId(int gCategory_id) {

        Category category = categoryMapper.findById(gCategory_id);
        if (category.getParent_id() ==0 ){
            List<Category> categoryList = categoryMapper.findChildCategory(category.getcId());
            List<Goods> goodsLists = new ArrayList<Goods>();
            for (int i =0 ;i<categoryList.size();i++){
                int cId = categoryList.get(i).getcId();
                List<Category> categoryList1 = categoryMapper.findChildCategory(cId);
                for (int j=0; j<categoryList1.size();j++){
                    int gCategory_id1 = categoryList1.get(j).getcId();
                    List<Goods> goodsList1 = goodsMapper.findGoodsByCategoryId(gCategory_id1);
                    for (int k=0;k<goodsList1.size();k++){
                        goodsLists.add(goodsList1.get(k));
                    }
                }
            }
            return goodsLists;
        }

        Category category1 = categoryMapper.findById(category.getParent_id());
        if (category1.getParent_id() == 0){
            List<Goods> goodsLists = new ArrayList<Goods>();
            List<Category> categoryList = categoryMapper.findChildCategory(category.getcId());
            for (int i = 0;i<categoryList.size();i++){

                int gCategory_id1 = categoryList.get(i).getcId();
                List<Goods> goodsList2 = goodsMapper.findGoodsByCategoryId(gCategory_id1);
                for (int k=0;k<goodsList2.size();k++){
                    goodsLists.add(goodsList2.get(k));
                }
            }
            return goodsLists;
        }

        List<Goods> goodsLists = goodsMapper.findGoodsByCategoryId(gCategory_id);

        return goodsLists;
    }



    public List<Goods> findGoodsByCategoryIdFenYe(int gCategory_id) {

        List<Integer> list = new ArrayList<Integer>();
        List<Goods> goodsLists = new ArrayList<Goods>();

        list = categoryService.findThirdCategoryIdByCategoryId(gCategory_id);

        goodsLists = goodsMapper.findGoodsByCategoryIdFenYe(list);

        return goodsLists;
    }


    //根据价格排序(升序)
    public List<Goods> findGoodsByCategoryIdOderByPrice(int gCategory_id){



        List<Integer> list = categoryService.findThirdCategoryIdByCategoryId(gCategory_id);

        List<Goods> goodsLists = goodsMapper.findGoodsByCategoryIdOderByPrice(list);

        return goodsLists;
    }

    //根据价格排序(降序)
    public List<Goods> findGoodsByCategoryIdOderByPriceDesc(int gCategory_id){




        List<Integer> list = categoryService.findThirdCategoryIdByCategoryId(gCategory_id);

        List<Goods> goodsLists = goodsMapper.findGoodsByCategoryIdOderByPriceDesc(list);

        return goodsLists;

    }


    //商品列表页根据销量Sale排序（降序）
    public List<Goods> findGoodsByCategoryIdOrderBySale(int gCategory_id) {

        List<Integer> list = categoryService.findThirdCategoryIdByCategoryId(gCategory_id);
        List<Goods> goodsLists = goodsMapper.findGoodsByCategoryIdOrderBySale(list);

        return goodsLists;
    }

    public List<Goods> findGoodsByLikeName(String likeName) {
        return goodsMapper.findByLikeName(likeName);

    }


    public List<Goods> findTuangou(int page){
        return goodsMapper.findTuangou(page);
    }

    public List<Goods> findAllTuangou(){
        return goodsMapper.findAllTuangou();
    }
    public List<Goods> findTemai(int page){
        return goodsMapper.findTemai(page);
    }
    public List<Goods> findAllTemai(){
        return goodsMapper.findAllTemai();
    }


    //根据商品价格区间进行查找商品
    public List<Goods> findGoodsByCategoryAndPriceQuJian(int gCategory_id,double price1,double price2){

        List<Integer> list = categoryService.findThirdCategoryIdByCategoryId(gCategory_id);
        Map<String,Object> map = new HashMap<String,Object>();

        map.put("list",list);
        map.put("price1",price1);
        map.put("price2",price2);

        List<Goods> goodsLists = goodsMapper.findGoodsByCategoryAndPriceQuJian(map);

        return goodsLists;
    }


    //根据商品id集合查找商品
    public List<Goods> findGoodsById(Map<String,Object> map){

        List<Goods> goodsList = goodsMapper.findGoodsById(map);

        return goodsList;

    }


    //根据品牌id和第三层级的id查找商品
    public List<Goods> findGoodsByBrandId(int gCategory_id,int brandId){

        List<Integer> list = categoryService.findThirdCategoryIdByCategoryId(gCategory_id);
        Map<String,Object> map = new HashMap<String,Object>();

        map.put("list",list);
        map.put("brandId",brandId);

        List<Goods> goodsList = goodsMapper.findGoodsByBrandId(map);

        return goodsList;

    }


    //*****************************************************************************************//
    //后台管理系统

    public List<Goods> selectAllGoods(int page,List<Integer> list,int count,int brandId,String likeName){
        page = (page-1)*count;

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("page",page);
        map.put("count",count);
        map.put("list",list);
        map.put("brandId",brandId);
        map.put("likeName",likeName);

        List<Goods> goodsList = goodsMapper.selectAllGoods(map);

        return goodsList;
    }
    //得到获得的商品的ID的集合，得到总个数size
    public List<Integer> selectAllGoodsId(List<Integer> list,int brandId,String likeName){

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("list",list);
        map.put("brandId",brandId);
        map.put("likeName",likeName);

        List<Integer> lists = goodsMapper.selectAllGoodsId(map);
        return lists;
    }

    //根据商品的具体id查找商品（查看商品时展示，此时不可点，以及修改商品时的展示，此时可以进行修改）
    public Goods selectGoodsById(int goodsId){
        Goods goods = goodsMapper.selectGoodsById(goodsId);



        return goods;
    }

    //修改商品(点击确认修改时，发的请求)
    public boolean updateGoodsById(Goods goods){

        Map<String,Object> map = new HashMap<String,Object>();

        int gId = goods.getgId();
//        int gBrand_id = goods.getBrandId();
        String gName = goods.getgName();
//        int gCategory_id = goods.getCategoryId();
//        String gImg = goods.getUrl();
        double gPrice = goods.getPrice();
        double gMaketPrice = goods.getgMaketPrice();
        int gStock =goods.getgStock();
        int gJiFen = goods.getgJiFen();

        map.put("gId",gId);
//        map.put("gBrand_id",gBrand_id);
        map.put("gName",gName);
//        map.put("gCategory_id",gCategory_id);
//        map.put("gImg",gImg);
        map.put("gPrice",gPrice);
        map.put("gMaketPrice",gMaketPrice);
        map.put("gStock",gStock);
        map.put("gJiFen",gJiFen);


        boolean result = goodsMapper.updateGoodsById(map);

        return result;
    }

    //删除商品（逻辑删除）
    public boolean luojiDelete(int goodsId){

        boolean result = goodsMapper.luojiDelete(goodsId);

        return result;
    }

    //----------------------------添加新的商品------------------------------------//
    public boolean addNewGoods(Goods goods){

        Map<String,Object> map = new HashMap<String,Object>();

        int gBrand_id = goods.getBrandId();
        String gName = goods.getgName();
        int gCategory_id = goods.getCategoryId();
        String gImg = goods.getUrl();
        double gPrice = goods.getPrice();
        double gMaketPrice = goods.getgMaketPrice();
        int gStock =goods.getgStock();
        int gJiFen = goods.getgJiFen();

        map.put("gBrand_id",gBrand_id);
        map.put("gName",gName);
        map.put("gCategory_id",gCategory_id);
        map.put("gImg",gImg);
        map.put("gPrice",gPrice);
        map.put("gMaketPrice",gMaketPrice);
        map.put("gStock",gStock);
        map.put("gJiFen",gJiFen);

        boolean result = goodsMapper.addNewGoods(map);

        return result;
    }


    //----------------------------商品回收站------------------------------------//

    //查看回收站的商品
    public List<Goods> selectRecycleGoods(int page,int count){
        page = (page-1)*count;

        List<Goods> goodsList = goodsMapper.selectRecycleGoods(page,count);


        return goodsList;
    }
    //查看回收站的商品
    public List<Integer> selectRecycleGoodsId(){

        List<Integer> list = goodsMapper.selectRecycleGoodsId();

        return list;
    }

    //把商品从回收站放回到商品（反删除）
    public boolean huiFuGoods(int goodsId){

        boolean result =goodsMapper.huiFuGoods(goodsId);

        return result;
    }

}


