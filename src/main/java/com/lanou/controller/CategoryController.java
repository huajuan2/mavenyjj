package com.lanou.controller;

import com.lanou.entity.Category;
import com.lanou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lanou on 2017/12/1.
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/findCategory.do")
    private String finds(Model model){
        List<Category> categories = categoryService.findCategory();
        model.addAttribute("categories",categories);
        return "index";
    }

    @RequestMapping("/findChildCategory.do")
    @ResponseBody
    private Category finds2(Model model, int cId){
        List<Category> categories =  categoryService.findChildCategory(cId);
        return null;
    }

}
