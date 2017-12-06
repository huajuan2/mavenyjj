package com.lanou.controller;

import com.lanou.entity.Category;
import com.lanou.entity.Tab;
import com.lanou.service.CategoryService;
import com.lanou.service.TabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanou on 2017/12/1.
 */
@Controller
@RequestMapping("/tab")
public class TabController {
    @Autowired
    private TabService tabService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/findsTab.do")
    @ResponseBody
    public List<Tab> finds(int categoryId){
        List<Tab> tabList = new ArrayList<Tab>();
        Category category = categoryService.findCategoryByCategoryId(categoryId);
        if (category == null){
            System.out.print("-1");
            return tabList;
        }
        if (category.getParent_id() == 0){
            tabList = tabService.findTabByCategory(categoryId);
            System.out.print("0");
            System.out.print(tabList);
            return tabList;
        }
        Category category1 = categoryService.findCategoryByCategoryId(category.getParent_id());
        if (category1.getParent_id() == 0){
            tabList = tabService.findTabByCategory(category1.getcId());
            System.out.print("1");
            System.out.print(tabList);
            return tabList;
        }
        Category category2 = categoryService.findCategoryByCategoryId(category1.getParent_id());
        if (category2.getParent_id() == 0){
            tabList = tabService.findTabByCategory(category2.getcId());
            System.out.print("2");
            System.out.print(tabList);
            return tabList;
        }
        System.out.print("3");
        return tabList;

    }






}
