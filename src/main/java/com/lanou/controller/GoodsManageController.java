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

    //----------------------------查看商品------------------------------------//

    //查看所有商品（里面包括对三个条件的判断查询）
    @RequestMapping("/selectAllGoods.do")
    public void selectAllGoods(HttpServletResponse response,Integer count, Integer page,Integer categoryId,Integer brandId,String likeName){

        Map<String,Object> map = new HashMap<String,Object>();

        //得到的第一层级分类的list
        List<Category> categoryList = categoryService.findCategory();
        //得到的品牌的list
        List<Brand> brandList = brandService.showAll();

        //根据层级ID查找第三层级ID的集合
        List<Integer> list = categoryService.findThirdCategoryIdByCategoryId(categoryId);

        Map<String,Object> goodsMap = new HashMap<String,Object>();

        //得到获得的商品的ID的集合，得到总个数
        List<Integer> lists = goodsService.selectAllGoodsId(list,brandId,likeName);
        //分页获得商品list
        List<Goods> goodsList = goodsService.selectAllGoods(page,list,count,brandId,likeName);

        goodsMap.put("size",lists.size());
        goodsMap.put("goodsList",goodsList);


        map.put("goodsMap",goodsMap);

        map.put("categoryList",categoryList);
        map.put("brandList",brandList);
        FastJson_All.toJson(map,response);
    }

    //根据商品的具体id查找商品（点击查看商品详情）
    @RequestMapping("/selectGoodsById.do")
    public void selectGoodsById(HttpServletResponse response,int goodsId){

        Map<String,Object> map = new HashMap<String,Object>();
        Goods goods = goodsService.selectGoodsById(goodsId);

        Brand brand = brandService.selectBrandById(goods.getBrandId());

        Category category = categoryService.selectCategoryById(goods.getCategoryId());

        map.put("goods",goods);
        map.put("brandName",brand.getbName());
        map.put("categoryName",category.getcName());

        FastJson_All.toJson(map,response);
    }

    //修改商品(点击确认修改时，发的请求)
    @RequestMapping("/updateGoodsById.do")
    public void updateGoodsById(HttpServletResponse response,Goods goods){

        Map<String,Object> map = new HashMap<String,Object>();

        boolean result = goodsService.updateGoodsById(goods);

        map.put("result",result);

        FastJson_All.toJson(map,response);
    }

    //删除商品（逻辑删除）
    @RequestMapping("/luojiDelete.do")
    public void luojiDelete(HttpServletResponse response,int gId){

        Map<String,Object> map = new HashMap<String,Object>();

        boolean result = goodsService.luojiDelete(gId);
        map.put("result",result);
        FastJson_All.toJson(map,response);
    }

    //----------------------------查看商品(结束)------------------------------------//



    //----------------------------添加新的商品------------------------------------//


    //查看第一层级(用来展示层级的第一个下拉框，准备建三个下拉框)
    @RequestMapping("/selectFirstCategory.do")
    public void selectFirstCategory(HttpServletResponse response){

        Map<String,Object> map = new HashMap<String,Object>();

        List<Category> categoryList = categoryService.findCategory();
        map.put("firstCategory",categoryList);
        FastJson_All.toJson(map,response);
    }

    //查看下一个层级（用来展示层级的第一个下拉框，准备建三个下拉框）
    @RequestMapping("/selectNextCategoryById.do")
    public void selectNextCategoryById(HttpServletResponse response,int categoryId){

        Map<String,Object> map = new HashMap<String,Object>();

        List<Category> categoryList = categoryService.selectCategoryByParentId(categoryId);
        map.put("nextCategory",categoryList);
        FastJson_All.toJson(map,response);

    }

    //查看所有的品牌（选择品牌）
    @RequestMapping("/showAllBrand.do")
    public void showAllBrand(HttpServletResponse response){

        Map<String,Object> map = new HashMap<String,Object>();

        List<Brand> brandList = brandService.showAll();
        map.put("brandList",brandList);
        FastJson_All.toJson(map,response);

    }


    //添加新的商品
    @RequestMapping("/addNewGoods.do")
    public void addNewGoods(HttpServletResponse response,Goods goods){



        boolean result = goodsService.addNewGoods(goods);

        FastJson_All.toJson(result,response);

    }


    //----------------------------商品分类------------------------------------//

    //查看所有分类（三个下拉框）
    //(查看第一层级(用来展示层级的第一个下拉框，准备建三个下拉框)
    //(查看下一个层级（用来展示层级的第一个下拉框，准备建三个下拉框）

    //此块没有写，请注意

    //增加商品分类



    //----------------------------商品回收站------------------------------------//

    //查看商品回收站的商品
    @RequestMapping("/selectRecycleGoods.do")
    public void selectRecycleGoods(HttpServletResponse response,int page,int count){

        Map<String,Object> map = new HashMap<String,Object>();
        List<Goods> goodsList = goodsService.selectRecycleGoods(page,count);
        List<Integer> list = goodsService.selectRecycleGoodsId();

        map.put("size",list.size());
        map.put("goodsList",goodsList);


        FastJson_All.toJson(map,response);

    }

    //把商品从回收站放回到商品（反删除）
    @RequestMapping("/huiFuGoods.do")
    public void huiFuGoods(HttpServletResponse response,int gId){

        boolean result = goodsService.huiFuGoods(gId);

        FastJson_All.toJson(result,response);

    }


}
