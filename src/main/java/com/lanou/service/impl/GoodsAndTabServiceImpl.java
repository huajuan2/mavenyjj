package com.lanou.service.impl;

import com.lanou.dao.GoodsAndTabMapper;
import com.lanou.service.GoodsAndTabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanou on 2017/12/5.
 */
@Service("goodsAndTabService")
public class GoodsAndTabServiceImpl implements GoodsAndTabService {

    @Autowired
    private GoodsAndTabMapper goodsAndTabMapper;

    //根据传过来的两个名字字段查询商品的id
    public List<Integer> findGoodsIdByName(String name1,String name2){

        name2 = "%"+name2+"%";

        List<Integer> list = goodsAndTabMapper.findGoodsIdByName(name1,name2);

        return list;
    }

}
