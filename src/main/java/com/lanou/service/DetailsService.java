package com.lanou.service;

import com.lanou.entity.Color;
import com.lanou.entity.Details;
import com.lanou.entity.Goods;
import com.lanou.entity.GuiGe;

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
}
