package com.lanou.dao;

import com.lanou.entity.Details;
import com.lanou.entity.Goods;

import java.util.List;

/**
 * Created by lanou on 2017/12/4.
 */
public interface DetailsMapper {
    public List<Goods> findGoodsDetail(Integer gId);

    public String findDoubleByCAndT(Details details);
}
