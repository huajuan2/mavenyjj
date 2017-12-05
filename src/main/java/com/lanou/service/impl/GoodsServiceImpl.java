package com.lanou.service.impl;

import com.lanou.dao.CategoryMapper;
import com.lanou.dao.GoodsMapper;
import com.lanou.entity.Category;
import com.lanou.entity.Goods;
import com.lanou.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanou on 2017/12/2.
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private CategoryMapper categoryMapper;

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



    public List<Goods> findGoodsByCategoryIdFenYe(int gCategory_id,int page) {

        int num = (page-1)*20;
        List<Integer> list = new ArrayList<Integer>();

        Category category = categoryMapper.findById(gCategory_id);
        if (category.getParent_id() ==0 ){
            List<Category> categoryList = categoryMapper.findChildCategory(category.getcId());
            List<Goods> goodsLists = new ArrayList<Goods>();
            for (int i =0 ;i<categoryList.size();i++){
                int cId = categoryList.get(i).getcId();
                List<Category> categoryList1 = categoryMapper.findChildCategory(cId);

                for (int j=0; j<categoryList1.size();j++){
                    int gCategory_id1 = categoryList1.get(j).getcId();
                    list.add(gCategory_id1);
//                    List<Goods> goodsList1 = goodsMapper.findGoodsByCategoryIdFenYe(gCategory_id1,num);
//                    for (int k=0;k<goodsList1.size();k++){
//                        goodsLists.add(goodsList1.get(k));
//                    }
                }
            }
            goodsLists = goodsMapper.findGoodsByCategoryIdFenYe(list,num);

            return goodsLists;
        }

        Category category1 = categoryMapper.findById(category.getParent_id());
        if (category1.getParent_id() == 0){
            List<Goods> goodsLists = new ArrayList<Goods>();
            List<Category> categoryList = categoryMapper.findChildCategory(category.getcId());

            list = new ArrayList<Integer>();
            for (int i = 0;i<categoryList.size();i++){

                int gCategory_id1 = categoryList.get(i).getcId();
                list.add(gCategory_id1);
//                List<Goods> goodsList2 = goodsMapper.findGoodsByCategoryIdFenYe(gCategory_id1,num);
//                for (int k=0;k<goodsList2.size();k++){
//                    goodsLists.add(goodsList2.get(k));
//                }
            }
            goodsLists = goodsMapper.findGoodsByCategoryIdFenYe(list,num);
            return goodsLists;
        }

        list = new ArrayList<Integer>();
        list.add(gCategory_id);
        List<Goods> goodsLists = goodsMapper.findGoodsByCategoryIdFenYe(list,num);

        return goodsLists;
    }




}


