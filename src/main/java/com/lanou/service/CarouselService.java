package com.lanou.service;

import com.lanou.entity.Carousel;

import java.util.List;

/**
 * Created by lanou on 2017/12/2.
 */
public interface CarouselService {
    public List<Carousel> showCarousel();

    //楼层的轮播图
    public List<Carousel> selectFloorRandom(int floor_id);
}
