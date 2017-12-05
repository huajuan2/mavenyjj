package com.lanou.controller;

import com.lanou.entity.Goods;
import com.lanou.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public List<Goods> findGoodsByCategoryIdOderByPriceAsc(int categoryId){

        List<Goods> goodsList = goodsService.findGoodsByCategoryIdOderByPrice(categoryId);

        return goodsList;
    }

    //商品列表页的根据价格排序（升序）
    @RequestMapping("/findGoodsOrderByDesc.do")
    @ResponseBody
    public List<Goods> findGoodsByCategoryIdOderByPriceDesc(int categoryId){

        List<Goods> goodsList = goodsService.findGoodsByCategoryIdOderByPriceDesc(categoryId);

        return goodsList;
    }

    //商品列表页根据销量Sale排序（降序）
    @RequestMapping("/findGoodsOrderBySale.do")
    @ResponseBody
    public List<Goods> findGoodsByCategoryIdOrderBySale(int categoryId){

        List<Goods> goodsList = goodsService.findGoodsByCategoryIdOrderBySale(categoryId);

        return goodsList;
    }


}
