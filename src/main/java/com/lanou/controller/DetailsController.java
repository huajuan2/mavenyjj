package com.lanou.controller;

import com.lanou.entity.Details;
import com.lanou.entity.Goods;
import com.lanou.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lanou on 2017/12/4.
 */
@Controller
@RequestMapping("/details")
public class DetailsController {
    @Autowired
    private DetailsService detailsService;

    //根据商品id查询商品详情
    @RequestMapping("/findDetails.do")
    @ResponseBody
    public List<Goods> findGoodsDetail(Integer gId){
        List<Goods> goods = detailsService.findGoodsDetail(gId);
        return goods ;
    }

    //通过颜色和规格来查找价格
    @RequestMapping("/findPrice.do")
    public String findDoubleByCAndT(Details details, Model model){
        String price =  detailsService.findDoubleByCAndT(details);
        System.out.println(price);
        return price;
    }
}
