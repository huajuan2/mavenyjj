package com.lanou.service.impl;

import com.lanou.dao.CollectionMapper;
import com.lanou.entity.Goods;
import com.lanou.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanou on 2017/12/4.
 */
@Service("collectionService")
public class CollectionServiceImp implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    public List<Goods> findGoodsList(Integer uId){
        List<Goods> goodsList = collectionMapper.findGoodsList(uId);
        return goodsList;
    }

    public Integer findRepeat(Integer uId,Integer gId){
        Integer result = collectionMapper.findRepeat(uId, gId);
        System.out.println(result);
        return result;
    };

    public boolean addCollection(Integer uId,Integer gId){
        boolean result = collectionMapper.addCollection(uId, gId);
        if (result){
            return true;
        }
        return false;
    };

    public boolean deleteCollection(Integer uId,Integer gId){
        boolean result = collectionMapper.deleteCollection(uId, gId);
        if (result){
            return true;
        }
        return false;
    };
}
