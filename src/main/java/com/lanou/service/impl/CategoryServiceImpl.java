package com.lanou.service.impl;

import com.lanou.dao.BrandMapper;
import com.lanou.dao.CategoryMapper;
import com.lanou.entity.Brand;
import com.lanou.entity.Category;
import com.lanou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanou on 2017/12/1.
 */

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BrandMapper brandMapper;

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

    //根据CategoryId查找Category
    public Category findCategoryByCategoryId(int categoryId) {

        Category category = categoryMapper.findById(categoryId);

        return category;
    }


    //根据categoryId判断查找child层级
    public List<Category> findCategoryListByCategoryId(int categoryId){

        List<Category> categoryList = categoryMapper.findChildCategory(categoryId);

        return categoryList;
    }


    //根据categoryId查找对应的一级分类
    public int findFirstCategoryIdByCategoryId(int categoryId){

        Category category = categoryMapper.findById(categoryId);
        if (category.getParent_id() ==0 ){

            return categoryId;
        }
        Category category1 = categoryMapper.findById(category.getParent_id());
        if (category1.getParent_id() == 0){

            return category1.getcId();
        }
        Category category2 = categoryMapper.findById(category1.getParent_id());
        if(category2.getParent_id() == 0){

            return category2.getcId();
        }

        return -1;
    }


    //根据categoryId查找对应的三级分类
    public List<Integer> findThirdCategoryIdByCategoryId(int categoryId){

        List<Integer> list = new ArrayList<Integer>();

        Category category = categoryMapper.findById(categoryId);
        if (category.getParent_id() ==0 ){
            List<Category> categoryList = categoryMapper.findChildCategory(category.getcId());
            for (int i =0 ;i<categoryList.size();i++){
                int cId = categoryList.get(i).getcId();
                List<Category> categoryList1 = categoryMapper.findChildCategory(cId);
                for (int j=0; j<categoryList1.size();j++){
                    int gCategory_id1 = categoryList1.get(j).getcId();
                    list.add(gCategory_id1);
                }
            }
            return list;
        }
        Category category1 = categoryMapper.findById(category.getParent_id());
        if (category1.getParent_id() == 0){
            List<Category> categoryList = categoryMapper.findChildCategory(category.getcId());
            for (int i = 0;i<categoryList.size();i++){
                int gCategory_id1 = categoryList.get(i).getcId();
                list.add(gCategory_id1);
            }
            return list;
        }
        list.add(categoryId);

        return list;
    }


    //*****************************************************************************************//
    //****后台管理系统****//

    //根据层级id查找层级
    public Category selectCategoryById(int categoryId){

        Category category = categoryMapper.selectCategoryById(categoryId);
        return category;
    }

    //根据parentId查找层级
    public List<Category> selectCategoryByParentId(int categoryId){

        List<Category> categoryList = categoryMapper.selectCategoryByParentId(categoryId);

        return categoryList;
    }


}
