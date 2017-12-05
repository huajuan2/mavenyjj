package com.lanou.dao;

import com.lanou.entity.Goods;

import java.util.List;

/**
 * Created by lanou on 2017/12/4.
 */
public interface CollectionMapper {
    public List<Goods> findGoodsList(Integer gId);

    public Integer findRepeat(Integer uId,Integer gId);

    public boolean addCollection(Integer uId,Integer gId);

    public boolean deleteCollection(Integer uId,Integer gId);
}
