package com.lanou.controller;

import com.lanou.entity.Goods;
import com.lanou.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lanou on 2017/12/4.
 */
@Controller
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    //通过用户Id查询收藏的商品
    @RequestMapping("/findGoodsList.do")
    @ResponseBody
    public List<Goods> findGoodsList(Integer uId){
          List<Goods> goodsList = collectionService.findGoodsList(uId);
          return goodsList;
    }

    @RequestMapping("/findRepeat")
    public String findRepeat(Integer uId,Integer gId){
        Integer result = collectionService.findRepeat(uId, gId);
        if (result==null){
            return "test";
        }
        return "index";
    }
}
