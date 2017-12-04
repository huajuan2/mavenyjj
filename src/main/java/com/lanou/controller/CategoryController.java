package com.lanou.controller;

import com.lanou.entity.Brand;
import com.lanou.entity.Category;
import com.lanou.entity.User;
import com.lanou.service.BrandService;
import com.lanou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by lanou on 2017/12/1.
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

//    @RequestMapping("/findCategory.do")
//    @ResponseBody
    public List<Category> finds(){
        List<Category> categories = categoryService.findCategory();
        return categories;
    }

    @RequestMapping("/findChildCategory.do")
    @ResponseBody
    public Map<String,Object> finds2(int cId){
        List<Category> categories =  categoryService.findChildCategory(cId);
        List<Brand> brands = brandService.showByC_id(cId);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("categories",categories);
        map.put("brands",brands);
        return map;
    }



}
