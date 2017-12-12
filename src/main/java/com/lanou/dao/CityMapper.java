package com.lanou.dao;

import com.lanou.entity.City;

import java.util.List;

/**
 * Created by lanou on 2017/12/12.
 */
public interface CityMapper {

    public List<City> findLevelOne();

    public List<City> findChildLevel(int parentId);
}
