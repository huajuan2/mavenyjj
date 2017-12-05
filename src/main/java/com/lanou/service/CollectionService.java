package com.lanou.service;

import com.lanou.entity.Goods;

import java.util.List;

/**
 * Created by lanou on 2017/12/4.
 */
public interface CollectionService {
    public List<Goods> findGoodsList(Integer uId);

    public Integer findRepeat(Integer uId,Integer gId);
}
