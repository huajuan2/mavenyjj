package com.lanou.controller;

import com.lanou.entity.Brand;
import com.lanou.entity.Category;
import com.lanou.entity.Goods;
import com.lanou.entity.Tab;
import com.lanou.service.BrandService;
import com.lanou.service.CategoryService;
import com.lanou.util.FastJson_All;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
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
//    @ResponseBody
    public void findGoodsList(HttpServletResponse response, int categoryId){

        Map<String,Object> map = new HashMap<String,Object>();

//        Map<String,Object> goodsMap = new HashMap<String,Object>();
//        List<Goods> goodsList = goodsController.findGoodsByCategoryIdFenYe(categoryId,page);
//
//        List<Goods> goodsList1 = goodsController.findGoodsByCategoryId(categoryId);
//
//        goodsMap.put("size",goodsList1.size());
//        goodsMap.put("goodsList",goodsList);
//
//        map.put("goods",goodsMap);

        List<Tab> tabList = tabController.finds(categoryId);

        List<Category> categoryList = categoryService.findCategoryListByCategoryId(categoryId);

        if (categoryList != null){

            Map<String,Object> map1 = new HashMap<String,Object>();
            map1.put("tabName","分类");
            map1.put("categoryList",categoryList);

            map.put("categorys",map1);
        }

        int num = categoryService.findFirstCategoryIdByCategoryId(categoryId);
        if (num != -1){
            Map<String,Object> map2 = new HashMap<String,Object>();
            List<Brand> brandList = brandService.showByC_id(num);
            map2.put("tabName","品牌");
            map2.put("brandList",brandList);

            map.put("brands",map2);
        }
        map.put("tabList",tabList);

        FastJson_All.toJson(map,response);

    }


    @RequestMapping("/findGoods.do")
//    @ResponseBody
    public void findGoods(HttpServletResponse response,int categoryId){

        Map<String,Object> map = new HashMap<String,Object>();

        Map<String,Object> goodsMap = new HashMap<String,Object>();
        List<Goods> goodsList = goodsController.findGoodsByCategoryIdFenYe(categoryId);

        List<Goods> goodsList1 = goodsController.findGoodsByCategoryId(categoryId);

        goodsMap.put("size",goodsList1.size());
        goodsMap.put("goodsList",goodsList);

        map.put("goods",goodsMap);


        FastJson_All.toJson(map,response);
    }







}
