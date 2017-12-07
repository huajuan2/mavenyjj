package com.lanou.dao;

import com.lanou.entity.Carousel;

import java.util.List;

/**
 * Created by lanou on 2017/12/2.
 */
public interface CarouselMapper {

    public List<Carousel> selectRandom(int num);

    //楼层的轮播图
    public List<Carousel> selectFloorRandom(int floor_id,int num);

}
