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

    public List<GuiGe> findGuiGeByColor(Integer color_id, Integer gId);

    public List<Color> findColorByGuiGe(Integer guige_id, Integer gId);

    public String findColorBycId(int cId);

    public String findGuigeBygId(int gId);

    public List<GoodsAndTab> findGoodsAndTab(Integer gId);

    public List<Img> findCenterImg(Integer gId);

    public List<Img> findTopImg(Integer gId);

    public List<Img> findFootImg(Integer gId);

    public List<Comment> findComment(Integer gId);

    public Integer finCount(Integer gId);

    public boolean addComment(Comment comment);
}
