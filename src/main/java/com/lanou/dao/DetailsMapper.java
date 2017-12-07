package com.lanou.dao;

import com.lanou.entity.*;

import java.util.List;

/**
 * Created by lanou on 2017/12/4.
 */
public interface DetailsMapper {
    public Goods findGoodsNamePriceImg(Integer gId);

    public List<Color> findColor(Integer gId);

    public List<GuiGe> findGuiGe(Integer gId);

    public List<GuiGe> findGuiGeByColor(Integer color_id);

    public List<Color> findColorByGuiGe(Integer guige_id);

    public String findColorBycId(int cId);

    public String findGuigeBygId(int gId);

    public List<GoodsAndTab> findGoodsAndTab(Integer gId);

    public List<Img> findTopImg(Integer gId);

    public List<Img> findFootImg(Integer gId);
}
