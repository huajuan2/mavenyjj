package com.lanou.controller;

import com.lanou.entity.Brand;
import com.lanou.entity.Category;
import com.lanou.entity.Goods;
import com.lanou.entity.Tab;
import com.lanou.service.BrandService;
import com.lanou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by lanou on 2017/12/4.
 */


@Controller
@RequestMapping("/goodsList")
public class GoodsListController {

    @Autowired
    private TabController tabController;
    @Autowired
    private GoodsController goodsController;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;


    @RequestMapping("/findGoodsList.do")
    @ResponseBody
    public Map<String,Object> findGoodsList(int categoryId){

        Map<String,Object> map = new HashMap<String,Object>();

        List<Goods> goodsList = goodsController.findGoodsByCategoryId(categoryId);

        List<Tab> tabList = tabController.finds(categoryId);

        List<Category> categoryList = categoryService.findCategoryListByCategoryId(categoryId);

        if (categoryList != null){

            Map<String,Object> map1 = new HashMap<String,Object>();
            map1.put("分类",categoryList);
            map.put("categoryList",map1);
        }

        int num = categoryService.findFirstCategoryIdByCategoryId(categoryId);
        if (num != -1){
            Map<String,Object> map2 = new HashMap<String,Object>();
            List<Brand> brandList = brandService.showByC_id(num);
            map2.put("品牌",brandList);
            map.put("brandList",map2);
        }
        map.put("tabList",tabList);
        map.put("goodsList",goodsList);

        return map;
    }



}
