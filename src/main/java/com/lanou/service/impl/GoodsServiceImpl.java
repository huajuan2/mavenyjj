package com.lanou.service.impl;

import com.lanou.dao.CategoryMapper;
import com.lanou.dao.GoodsMapper;
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
}


