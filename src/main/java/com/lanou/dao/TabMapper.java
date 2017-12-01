package com.lanou.dao;

import com.lanou.entity.Tab;

import java.util.List;

/**
 * Created by lanou on 2017/12/1.
 */
public interface TabMapper {

    //根据第一层级的id查找标签
    public List<Tab> findTabByCategory(int category_id);

    //根据父级id查找标签
    public List<Tab> findTabByParentId(int parentId);

}
