package com.lanou.service;

import com.lanou.entity.Category;

import java.util.List;

/**
 * Created by lanou on 2017/12/1.
 */
public interface CategoryService {
    public List<Category> findCategory();

    public List<Category> findChildCategory(int uId);

    //根据CategoryId查找Category
    public Category findCategoryByCategoryId(int categoryId);

    //根据categoryId判断查找child层级
    public List<Category> findCategoryListByCategoryId(int categoryId);


    //根据categoryId查找对应的一级分类
    public int findFirstCategoryIdByCategoryId(int categoryId);

    //根据categoryId查找对应的三级分类
    public List<Integer> findThirdCategoryIdByCategoryId(int categoryId);



    //*****************************************************************************************//
    //****后台管理系统****//

    //根据层级id查找层级
    public Category selectCategoryById(int categoryId);

    //根据parentId查找层级
    public List<Category> selectCategoryByParentId(int categoryId);
}


