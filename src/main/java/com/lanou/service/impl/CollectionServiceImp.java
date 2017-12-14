package com.lanou.service.impl;

import com.lanou.dao.CollectionMapper;
import com.lanou.entity.Goods;
import com.lanou.service.CollectionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

//    public boolean deleteCollection(Integer uId,Integer gId){
//        boolean result = collectionMapper.deleteCollection(uId, gId);
//        if (result){
//            return true;
//        }
//        return false;
//    };


    //需要goods_id数组
    public boolean deleteCollection(HttpServletRequest request,Integer user_id){
       String[] goods_id = request.getParameterValues("goods_id");
       List<Integer> list= new ArrayList<Integer>();
       Map<String,Object> map = new HashMap<String,Object>();
       for (int i = 0;i<goods_id.length;i++){
            list.add(Integer.valueOf(goods_id[i]));
       }
        map.put("list",list);
        map.put("user_id",user_id);
        boolean result = collectionMapper.deleteCollection(map);
        if (result){
            return true;
        }
        return false;
    }
}
