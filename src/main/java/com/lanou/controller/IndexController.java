package com.lanou.controller;

import com.lanou.entity.*;
import com.lanou.service.*;
import com.lanou.util.FastJson_All;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
/**
 * Created by lanou on 2017/12/2.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private FloorService floorService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private HotSelectService hotSelectService;

    @Autowired
    private NoticeService noticeService;

    private static int TUANGOU_PAGE = 0;

    private static int TEMAI_PAGE = 0;
    @RequestMapping("/showIndex.do")
//    @ResponseBody
    public void finds(HttpServletResponse response){
        List<Category> categories = categoryService.findCategory();
        List<Carousel> carousels = carouselService.showCarousel();
        List<Brand> brands = brandService.showFirst();
        List<String> hot = hotSelectService.findHot();
        List<Goods> tuangou = goodsService.findTuangou(0);
        List<Goods> temai = goodsService.findTemai(0);
        List<Notice> notices = noticeService.findNoticeRandom();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("categories",categories);//一级目录
        map.put("carousels",carousels);//轮播图
        map.put("brands",brands);//中间展示的14个品牌
        map.put("hotSelect",hot);//热门搜索
        map.put("tuangou",tuangou);//团购
        map.put("temai",temai);//特卖
        map.put("notices",notices);//公告
//        return map;
        FastJson_All.toJson(map,response);
    }

    @RequestMapping("/showFloor.do")
//    @ResponseBody
    public void showFloor(HttpServletResponse response,int fId){

//        return floorService.showFloor(fId);
        FastJson_All.toJson(floorService.showFloor(fId),response);
    }

    @RequestMapping("/findByLikeName.do")
//    @ResponseBody
    public void findByLikeName(HttpServletResponse response,String likeName){
        String likeName1 = "%"+likeName+"%";
        hotSelectService.updateHotSelect(likeName);
//        return goodsService.findGoodsByLikeName(likeName1);
        FastJson_All.toJson(goodsService.findGoodsByLikeName(likeName1),response);
    }


    @RequestMapping("/updateTuangou.do")
    public void updateTuangou(HttpServletResponse response){
        TUANGOU_PAGE++;
        if(TUANGOU_PAGE>2){
            TUANGOU_PAGE = 0;
        }

        FastJson_All.toJson(goodsService.findTuangou(TUANGOU_PAGE*6),response);
    }

    @RequestMapping("/updateTemai.do")
    public void updateTemai(HttpServletResponse response){
        TEMAI_PAGE++;
        if(TEMAI_PAGE>2){
            TEMAI_PAGE = 0;
        }

        FastJson_All.toJson(goodsService.findTemai(TEMAI_PAGE*6),response);
    }

    @RequestMapping("/enterTuangou.do")
    public void enterTuangou(HttpServletResponse response){
        FastJson_All.toJson(goodsService.findAllTuangou(),response);
    }

    @RequestMapping("/enterTemai.do")
    public void enterTemai(HttpServletResponse response){
        FastJson_All.toJson(goodsService.findAllTemai(),response);
    }

    @RequestMapping("/findAllNotice.do")
    public void findAllNotice(HttpServletResponse response){
        FastJson_All.toJson(noticeService.findAllNotice(),response);
    }
}
