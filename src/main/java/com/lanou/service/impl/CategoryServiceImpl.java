package com.lanou.service.impl;

import com.lanou.dao.CategoryMapper;
import com.lanou.entity.Category;
import com.lanou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanou on 2017/12/1.
 */

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryMapper categoryMapper;

//    查询所有一级目录
    public List<Category> findCategory() {
        return categoryMapper.findCategory();
    }

//    递归法查询一级目录下的所有二级目录和三级目录
    public List<Category> findChildCategory(int cId){
        List<Category> categories = categoryMapper.findChildCategory(cId);
        for(Category categoryItem:categories){
            categoryItem.setChilds(findChildCategory(categoryItem.getcId()));
        }
        return categories;
    }


}
