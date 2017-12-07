package com.lanou.service;

import com.lanou.entity.*;

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
    public List<GuiGe> findGuiGeByColor(Integer color_id);
    //通过规格找到颜色
    public List<Color> findColorByGuiGe(Integer guige_id);
    //通过商品Id查商品详情
    public List<GoodsAndTab> findGoodsAndTab(Integer gId);
    //通过id查上面的图片
    public List<Img> findTopImg(Integer gId);
    //通过id查下面的图片
    public List<Img> findFootImg(Integer gId);
}
