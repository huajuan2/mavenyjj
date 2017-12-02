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
}
