package com.lanou.service;

import com.lanou.entity.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lanou on 2017/12/4.
 */
public interface DetailsService {
    public Goods findGoodsNamePriceImg(Integer gId);
    //找颜色
    public List<Color> findColor(Integer gId);
    //找规格
    public List<GuiGe> findGuiGe(Integer gId);
    //通过颜色找到规格
    public List<GuiGe> findGuiGeByColor(Integer color_id, Integer gId);
    //通过规格找到颜色
    public List<Color> findColorByGuiGe(Integer guige_id, Integer gId);
    //通过商品Id查商品详情
    public List<GoodsAndTab> findGoodsAndTab(Integer gId);
    //通过id查上面的大图
    public List<Img> findCenterImg(Integer gId);
    //通过id查上面的图片
    public List<Img> findTopImg(Integer gId);
    //通过id查下面的图片
    public List<Img> findFootImg(Integer gId);
    //通过商品Id查找评论
    public List<Comment> findComment(Integer gId);
    //通过商品Id查找用户
//    public User findUserComment(Integer gId);
    //通过goods_id查找评论数量
    public Integer finCount(Integer gId);
    //添加评论
    public boolean addComment(Comment comment);

    public Integer findBuying(Integer uId,Integer gId);
}
