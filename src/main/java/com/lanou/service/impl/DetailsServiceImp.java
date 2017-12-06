package com.lanou.service.impl;

import com.lanou.dao.DetailsMapper;
import com.lanou.entity.*;
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
}


