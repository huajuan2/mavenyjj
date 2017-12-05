package com.lanou.controller;

import com.lanou.entity.Goods;
import com.lanou.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lanou on 2017/12/4.
 */
@Controller
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    //通过用户Id查询收藏的商品(查看收藏夹)
    @RequestMapping("/findGoodsList.do")
    @ResponseBody
    public List<Goods> findGoodsList(Integer uId){
          List<Goods> goodsList = collectionService.findGoodsList(uId);
          return goodsList;
    }

//    @RequestMapping("/findRepeat")
//    public String findRepeat(Integer uId,Integer gId){
//        Integer result = collectionService.findRepeat(uId, gId);
//        if (result==null){
//            return "test";
//        }
//        return "index";
//    }

    //收藏商品
    @RequestMapping("/addCollection.do")
    @ResponseBody
    public int addCollection(Integer uId, Integer gId, HttpServletRequest request){
        HttpSession session =  request.getSession();
        if (session==null){
//          String login = "请登录";
            return 1;
        }
        Integer result = collectionService.findRepeat(uId, gId);
        if (result==null){
            boolean results = collectionService.addCollection(uId, gId);
            if (results){
//                String OK = "商品收藏成功!";
                return 2;
            }
        }
//        String NO= "您已经收藏过了!";
        return 0;
    }

    @RequestMapping("/deleteCollection.do")
    @ResponseBody
    public boolean delete(Integer uId, Integer gId){
        boolean result = collectionService.deleteCollection(uId, gId);
        if (result){
            //删除成功
            return true;
        }
        //删除失败
        return false;
    }

}
