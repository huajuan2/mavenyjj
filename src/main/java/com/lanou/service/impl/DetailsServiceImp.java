package com.lanou.service.impl;

import com.lanou.dao.DetailsMapper;
import com.lanou.entity.Details;
import com.lanou.entity.Goods;
import com.lanou.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanou on 2017/12/4.
 */
@Service("detailsService")
public class DetailsServiceImp implements DetailsService {

    @Autowired
    private DetailsMapper detailsMapper;

    public List<Goods> findGoodsDetail(Integer gId){
        List<Goods> goods =  detailsMapper.findGoodsDetail(gId);
        return goods;
    };

    public String findDoubleByCAndT(Details details){
        String price =  detailsMapper.findDoubleByCAndT(details);
        return price;
    };

}

