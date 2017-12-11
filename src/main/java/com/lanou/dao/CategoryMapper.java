package com.lanou.dao;

import com.lanou.entity.Category;

import java.util.List;
import java.util.Map;

/**
 * Created by lanou on 2017/12/1.
 */
public interface CategoryMapper {

    public List<Category> findCategory();

    public Category findById(int cId);

    public List<Category> findChildCategory(int parent_id);

    public Category findByFid(int f_id);

    public List<Category> findRandomTwo(int cid);

    //根据层级id查找商品
    public Category selectCategoryById(int categoryId);

    //根据parentId查找层级
    public List<Category> selectCategoryByParentId(int parentId);

}
