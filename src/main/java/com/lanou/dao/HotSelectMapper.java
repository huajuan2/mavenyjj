package com.lanou.dao;

import com.lanou.entity.HotSelect;

import java.util.List;

/**
 * Created by lanou on 2017/12/5.
 */
public interface HotSelectMapper {

    public List<String> selectHot();

    public void addSelect(String selectName);

    public Integer findSelect(String selectName);

    public void updateSelect(String selectName);
}
