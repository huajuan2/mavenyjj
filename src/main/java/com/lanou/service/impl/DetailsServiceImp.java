package com.lanou.service.impl;

import com.lanou.dao.DetailsMapper;
import com.lanou.dao.GoodsMapper;
import com.lanou.dao.OrderMapper;
import com.lanou.entity.*;
import com.lanou.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
/**
 * Created by lanou on 2017/12/4.
 */
@Service("detailsService")
public class DetailsServiceImp implements DetailsService {

    @Autowired
    private DetailsMapper detailsMapper;

    @Autowired
    private OrderMapper orderMapper;
    public Goods findGoodsNamePriceImg(Integer gId){
        Goods goodss = detailsMapper.findGoodsNamePriceImg(gId);
        return goodss;
    };

    public List<Color> findColor(Integer gId){
       List<Color> colorList =  detailsMapper.findColor(gId);
       return  colorList;
    };

    public List<GuiGe> findGuiGe(Integer gId){
        List<GuiGe> guiGeList = detailsMapper.findGuiGe(gId);
        return guiGeList;
    };

    public List<GuiGe> findGuiGeByColor(Integer color_id){
        List<GuiGe> guiGeList = detailsMapper.findGuiGeByColor(color_id);
        return guiGeList;
    };

    public List<Color> findColorByGuiGe(Integer guige_id){
        List<Color> colorList = detailsMapper.findColorByGuiGe(guige_id);
        return colorList;
    };

    public List<GoodsAndTab> findGoodsAndTab(Integer gId){
        List<GoodsAndTab> goodsAndTabList = detailsMapper.findGoodsAndTab(gId);
        return  goodsAndTabList;
    };

    public List<Img> findTopImg(Integer gId){
        List<Img> imgTopList = detailsMapper.findTopImg(gId);
        return imgTopList;
    };

    public List<Img> findFootImg(Integer gId){
        List<Img> imgFootList = detailsMapper.findTopImg(gId);
        return imgFootList;
    };

    public List<Comment> findComment(Integer gId){
        List<Comment> commentList = detailsMapper.findComment(gId);
        return commentList;
    };

//    public User findUserComment(Integer gId){
//        User user = detailsMapper.findUserComment(gId);
//        return  user;
//    };

    public Integer finCount(Integer gId){
        Integer num = detailsMapper.finCount(gId);
        return num;
    };

    public boolean addComment(Comment comment){
        boolean result = detailsMapper.addComment(comment);
        return result;
    }

    public Integer findBuying(Integer uId,Integer gId){
        System.out.println("gId是："+gId);
       List<Integer> integerList =orderMapper.findBuyingOidByUid(uId);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("gId",gId);
        map.put("list",integerList);
        System.out.println(integerList);
        Integer sum = orderMapper.findIfBuying(map);
        System.out.println(map);
        System.out.println("sum是:"+sum);
        return sum;
    }
}


