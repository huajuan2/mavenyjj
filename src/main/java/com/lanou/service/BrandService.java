package com.lanou.service;

import com.lanou.entity.Brand;

import java.util.List;

/**
 * Created by lanou on 2017/12/2.
 */
public interface BrandService {
    public List<Brand> showBrands();

    public List<Brand> showFirst();

    public List<Brand> showAll();

    public List<Brand> showByC_id(int cId);

    //根据品牌id查找品牌
    public Brand selectBrandById(int brandId);

}
