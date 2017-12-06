package com.lanou.controller;

import com.lanou.entity.*;
import com.lanou.service.DetailsService;
import com.lanou.util.FastJson_All;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lanou on 2017/12/4.
 */
@Controller
@RequestMapping("/details")
public class DetailsController {
    @Autowired
    private DetailsService detailsService;

    //根据商品id查询商品详情(Id，名称，颜色，规格等等)
    //需要参数商品id
    @RequestMapping("/findGoodsNamePriceImg.do")
    public void find1(Integer gId, HttpServletResponse response){
        Map<String,Object> map = new HashMap<String,Object>();
        Goods goodss =  detailsService.findGoodsNamePriceImg(gId);
        List<Color> colorList = detailsService.findColor(gId);
        List<GuiGe> guiGeList = detailsService.findGuiGe(gId);
        List<GoodsAndTab> goodsAndTabList = detailsService.findGoodsAndTab(gId);
        map.put("goodsAndTabList",goodsAndTabList);
        map.put("goods",goodss);
        map.put("color",colorList);
        map.put("guige",guiGeList);
        FastJson_All.toJson(map,response);
    }

    //根据颜色查商品规格
    //需要颜色id（color_id）
    @RequestMapping("/findGuigeByColor.do")
    public void find2(Integer color_id,HttpServletResponse response){
       List<GuiGe> guiGeList =  detailsService.findGuiGeByColor(color_id);
       FastJson_All.toJson(guiGeList,response);
    }

    //根据规格查商品颜色
    //需要规格id（guige_id）
    @RequestMapping("/findColorByGuige.do")
    public void find3(Integer guige_id,HttpServletResponse response){
        List<Color> colorList = detailsService.findColorByGuiGe(guige_id);
        FastJson_All.toJson(colorList,response);
    }

}
