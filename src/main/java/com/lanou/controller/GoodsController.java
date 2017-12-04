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


    public List<Goods> findGoodsByCategoryIdFenYe(int categoryId,int page){

        List<Goods> goodsList = goodsService.findGoodsByCategoryIdFenYe(categoryId,page);

        return goodsList;

    }


    @RequestMapping("findGoodsRandom.do")
    @ResponseBody
    public Map<String,Object> findGoodsByCategoryIdRandom(int categoryId){

        Map<String,Object> maps = new HashMap<String,Object>();

        List<Goods> goodsList = goodsService.findGoodsByCategoryIdRandom(categoryId);
        maps.put("goodsList",goodsList);

        return maps;
    }


}
