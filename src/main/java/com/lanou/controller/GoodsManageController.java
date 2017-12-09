package com.lanou.controller;

import com.lanou.entity.Brand;
import com.lanou.entity.Category;
import com.lanou.entity.Goods;
import com.lanou.service.BrandService;
import com.lanou.service.CategoryService;
import com.lanou.service.GoodsService;
import com.lanou.util.FastJson_All;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by lanou on 2017/12/8.
 */
@Controller
@RequestMapping("/goodsManage")
public class GoodsManageController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;

    //*****************************************************************************************//
    //后台管理系统

    //查看所有商品（里面包括对三个条件的判断查询）
    @RequestMapping("/selectAllGoods")
    public void selectAllGoods(HttpServletResponse response, int page,int categoryId,int brandId,String likeName){

        Map<String,Object> map = new HashMap<String,Object>();

        List<Category> categoryList = categoryService.findCategory();
        List<Brand> brandList = brandService.showAll();

        //根据层级ID查找第三层级ID的集合
        List<Integer> list = categoryService.findThirdCategoryIdByCategoryId(categoryId);

        Map<String,Object> goodsMap = new HashMap<String,Object>();

        //得到获得的商品的ID的集合，得到总个数
        List<Integer> lists = goodsService.selectAllGoodsId(list,brandId,likeName);
        //分页获得商品list
        List<Goods> goodsList = goodsService.selectAllGoods(page,list,brandId,likeName);

        goodsMap.put("size",lists.size());
        goodsMap.put("goodsList",goodsList);


        map.put("goodsMap",goodsMap);

        map.put("categoryList",categoryList);
        map.put("brandList",brandList);
        FastJson_All.toJson(map,response);
    }

    //根据商品的具体id查找商品
    @RequestMapping("/selectGoodsById")
    public void selectGoodsById(HttpServletResponse response,int goodsId){

        Map<String,Object> map = new HashMap<String,Object>();
        Goods goods = goodsService.selectGoodsById(goodsId);
        map.put("goods",goods);

        FastJson_All.toJson(map,response);
    }

}
