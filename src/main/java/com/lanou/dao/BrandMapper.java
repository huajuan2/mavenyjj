package com.lanou.dao;

import com.lanou.entity.Brand;

import java.util.List;

/**
 * Created by lanou on 2017/12/2.
 */
public interface BrandMapper {
    public List<Brand> selectRandom();

    public List<Brand> selectFirst();
}
