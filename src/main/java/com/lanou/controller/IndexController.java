package com.lanou.controller;

import com.lanou.entity.Brand;
import com.lanou.entity.Carousel;
import com.lanou.entity.Category;
import com.lanou.service.BrandService;
import com.lanou.service.CarouselService;
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

    @RequestMapping("/showIndex.do")
    @ResponseBody
    public Map<String, Object> finds(){
        List<Category> categories = categoryController.finds();
        List<Carousel> carousels = carouselService.showCarousel();
        List<Brand> brands = brandService.showFirst();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("categories",categories);//一级目录
        map.put("carousels",carousels);//轮播图
        map.put("brands",brands);//品牌
        return map;
    }




}
