package com.lanou.dao;

import com.lanou.entity.Brand;

import java.util.List;

/**
 * Created by lanou on 2017/12/2.
 */
public interface BrandMapper {
    public List<Brand> selectRandom();

    public List<Brand> selectFirst();

    public List<Brand> selectAll();

    public List<Brand> selectByC_id(int cId);

    //根据品牌id查找品牌
    public Brand selectBrandById(int brandId);

}
