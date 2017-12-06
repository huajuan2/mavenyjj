package com.lanou.controller;

import com.lanou.entity.Goods;
import com.lanou.service.CategoryService;
import com.lanou.service.GoodsAndTabService;
import com.lanou.service.GoodsService;
import com.lanou.util.FastJson_All;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by lanou on 2017/12/2.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsAndTabService goodsAndTabService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/findGoods.do")
    @ResponseBody
    public List<Goods> findGoodsByCategoryId(int categoryId){

        List<Goods> goodsList = goodsService.findGoodsByCategoryId(categoryId);

        return goodsList;
    }

    //商品列表的分页
    public List<Goods> findGoodsByCategoryIdFenYe(int categoryId){

        List<Goods> goodsList = goodsService.findGoodsByCategoryIdFenYe(categoryId);

        return goodsList;

    }



    //商品列表页的根据价格排序（升序）
    @RequestMapping("/findGoodsOrderByAsc.do")
//    @ResponseBody
    public void findGoodsByCategoryIdOderByPriceAsc(HttpServletResponse response, int categoryId){

        List<Goods> goodsList = goodsService.findGoodsByCategoryIdOderByPrice(categoryId);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("size",goodsList.size());
        map.put("goodsList",goodsList);


        FastJson_All.toJson(map,response);
    }

    //商品列表页的根据价格排序（降序）
    @RequestMapping("/findGoodsOrderByDesc.do")
//    @ResponseBody
    public void findGoodsByCategoryIdOderByPriceDesc(HttpServletResponse response,int categoryId){

        List<Goods> goodsList = goodsService.findGoodsByCategoryIdOderByPriceDesc(categoryId);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("size",goodsList.size());
        map.put("goodsList",goodsList);

        FastJson_All.toJson(map,response);
    }

    //商品列表页根据销量Sale排序（降序）
    @RequestMapping("/findGoodsOrderBySale.do")
//    @ResponseBody
    public void findGoodsByCategoryIdOrderBySale(HttpServletResponse response,int categoryId){

        List<Goods> goodsList = goodsService.findGoodsByCategoryIdOrderBySale(categoryId);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("size",goodsList.size());
        map.put("goodsList",goodsList);

        FastJson_All.toJson(map,response);
    }


    //根据商品价格区间进行查找商品
    @RequestMapping("/findGoodsByCategoryAndPriceQuJian.do")
//    @ResponseBody
    public void findGoodsByCategoryAndPriceQuJian(HttpServletResponse response,int categoryId,Double price1,Double price2){

        if (price1==null){
            price1=0.0;
        }
        if (price2==null){
            price2=100000000.00;
        }

        if (price1 > price2){
            double a = price1;
            price1=price2;
            price2=a;
        }
        List<Goods> goodsList = goodsService.findGoodsByCategoryAndPriceQuJian(categoryId,price1,price2);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("size",goodsList.size());
        map.put("goodsList",goodsList);


        FastJson_All.toJson(map,response);
    }

    //根据条件(两个名字)查找商品
    @RequestMapping("/findGoodsByName.do")
    public void findGoodsByName(HttpServletResponse response,int categoryId,String name1,String name2){

        List<Integer> list = goodsAndTabService.findGoodsIdByName(name1,name2);

        List<Integer> list1 = categoryService.findThirdCategoryIdByCategoryId(categoryId);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("list",list);
        map.put("list1",list1);

        List<Goods> goodsList = goodsService.findGoodsById(map);

        FastJson_All.toJson(goodsList,response);

    }


    //根据品牌查商品
    @RequestMapping("/findGoodsByBrandId.do")
    public void findGoodsByBrandId(HttpServletResponse response,int categoryId,int brandId){

        List<Goods> goodsList = goodsService.findGoodsByBrandId(categoryId,brandId);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("size",goodsList.size());
        map.put("goodsList",goodsList);

        FastJson_All.toJson(map,response);


    }


}
