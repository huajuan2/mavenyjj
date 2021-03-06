package com.lanou.service.impl;

import com.lanou.dao.BrandMapper;
import com.lanou.entity.Brand;
import com.lanou.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanou on 2017/12/2.
 */
@Service("brandService")
public class BrandServiceImpl implements BrandService{

    @Autowired
    private BrandMapper brandMapper;

    public List<Brand> showBrands() {
        return brandMapper.selectRandom();
    }

    public List<Brand> showFirst() {
        return brandMapper.selectFirst();
    }

    public List<Brand> showAll() {
        return brandMapper.selectAll();
    }

    public List<Brand> showByC_id(int cId) {
        return brandMapper.selectByC_id(cId);
    }




    //*****************************************************************************************//
    //后台管理系统

    //根据品牌id查找品牌
    public Brand selectBrandById(int brandId){

        Brand brand = brandMapper.selectBrandById(brandId);

        return brand;
    }



}
