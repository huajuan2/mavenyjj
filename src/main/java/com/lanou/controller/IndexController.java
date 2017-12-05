package com.lanou.controller;

import com.lanou.entity.*;
import com.lanou.service.*;
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
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private CategoryController categoryController;

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private FloorService floorService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private HotSelectService hotSelectService;


    @RequestMapping("/showIndex.do")
    @ResponseBody
    public Map<String, Object> finds(){
        List<Category> categories = categoryController.finds();
        List<Carousel> carousels = carouselService.showCarousel();
        List<Brand> brands = brandService.showFirst();
        List<String> hot = hotSelectService.findHot();


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("categories",categories);//一级目录
        map.put("carousels",carousels);//轮播图
        map.put("brands",brands);//中间展示的14个品牌
        map.put("hotSelect",hot);//热门搜索
        return map;
    }

    @RequestMapping("/showFloor.do")
    @ResponseBody
    public Floor showFloor(int fId){
        return floorService.showFloor(fId);
    }

    @RequestMapping("/findByLikeName")
    @ResponseBody
    public List<Goods> findByLikeName(String likeName){
        String likeName1 = "%"+likeName+"%";
        hotSelectService.updateHotSelect(likeName);
        return goodsService.findGoodsByLikeName(likeName1);
    }

}
