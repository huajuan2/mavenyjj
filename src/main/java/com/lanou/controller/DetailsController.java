package com.lanou.controller;

import com.lanou.entity.*;
import com.lanou.service.DetailsService;
import com.lanou.util.FastJson_All;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
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

    private static final int LIKESIZE = 5;

    private static LinkedList like = new LinkedList();

    //根据商品id查询商品详情(Id，名称，颜色，规格等等)
    //需要参数商品id
    @RequestMapping("/findGoodsNamePriceImg.do")
    public void find1(Integer gId, HttpServletResponse response, HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            int i = 0;
            for (i = 0; i < cookies.length; i++) {
                if ("userLike".equals(cookies[i].getName())) {
                    String likes = cookies[i].getValue();
                    likes = likes.substring(1, likes.length() - 1);
                    String userlikes[] = likes.split(",");
                    System.out.println("数组长度：" + userlikes.length);
                    int j = 0;
                    for (j = 0; j < userlikes.length; j++) {
                        if (gId == Integer.parseInt(userlikes[j].trim())) {
                            System.out.println("j的值是:" + j);
                            break;
                        }
                    }
                    if (j == userlikes.length) {
                        if (userlikes.length >= LIKESIZE) {

                            like.removeFirst();
                            like.addLast(gId);
                        } else {
                            like.add(gId);
                        }
                        Cookie cookie = new Cookie("userLike", like.toString());
                        cookie.setPath("/");
                        cookie.setMaxAge(60 * 60 * 24 * 7);
                        response.addCookie(cookie);
                        break;
                    }
                    break;
                }

            }
            if (i == cookies.length) {
                like.add(gId);
                Cookie cookie = new Cookie("userLike", like.toString());
                cookie.setPath("/");
                cookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(cookie);
            }
        }else{
            like.add(gId);
            Cookie cookie = new Cookie("userLike", like.toString());
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);
        }

        Map<String,Object> map = new HashMap<String,Object>();
        Goods goodss =  detailsService.findGoodsNamePriceImg(gId);
        List<Color> colorList = detailsService.findColor(gId);
        List<GuiGe> guiGeList = detailsService.findGuiGe(gId);
        List<GoodsAndTab> goodsAndTabList = detailsService.findGoodsAndTab(gId);
        List<Img> imgTopList = detailsService.findTopImg(gId);
        List<Img> imgFootList = detailsService.findFootImg(gId);
        Integer num = detailsService.finCount(gId);
        map.put("num",num);
        map.put("goodsAndTabList",goodsAndTabList);
        map.put("goods",goodss);
        map.put("color",colorList);
        map.put("guige",guiGeList);
        map.put("imgTop",imgTopList);
        map.put("imgFoot",imgFootList);
        FastJson_All.toJson(map,response);
    }

    //根据商品id查询所有评论以及评论人的信息
    @RequestMapping("/findComment.do")
    public void find4(Integer gId,HttpServletResponse response){
        Map<String,Object> map = new HashMap<String, Object>();
        List<Comment> commentList = detailsService.findComment(gId);
        map.put("commentList",commentList);
        FastJson_All.toJson(map,response);
    }

    //判断是否登录和购买过此商品
    @RequestMapping("/findIfBuying.do")
    public void find5(Integer goods_id,HttpServletRequest request,HttpServletResponse response){
        User user =(User) request.getSession().getAttribute("user1");
        Integer sum = 1;
        if (user==null){
            sum = 0;//请登录
        }else {
            Integer num = detailsService.findBuying(user.getuId(),goods_id);
            if (num==0){
                sum = 2;//没有买过此商品
            }
        }
        FastJson_All.toJson(sum,response);
    }
    //添加评论(需要)
    @RequestMapping("/addComment.do")
    public void find6(Comment comment, HttpServletResponse response, HttpServletRequest request){
        User user =(User) request.getSession().getAttribute("user1");
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String commentDate = dateFormat.format(date);
        comment.setCommentDate(commentDate);
        comment.setUser_id(user.getuId());
        boolean result = false;
            if (detailsService.addComment(comment)){
                result = true;
            }
        FastJson_All.toJson(result,response);
    }
    //根据颜色查商品规格
    //需要颜色id（color_id）
    @RequestMapping("/findGuigeByColor.do")
    public void find2(Integer color_id,HttpServletResponse response,Integer gId){
       List<GuiGe> guiGeList =  detailsService.findGuiGeByColor(color_id,gId);
       FastJson_All.toJson(guiGeList,response);
    }

    //根据规格查商品颜色
    //需要规格id（guige_id）
    @RequestMapping("/findColorByGuige.do")
    public void find3(Integer guige_id,HttpServletResponse response,Integer gId){
        List<Color> colorList = detailsService.findColorByGuiGe(guige_id,gId);
        FastJson_All.toJson(colorList,response);
    }

}
