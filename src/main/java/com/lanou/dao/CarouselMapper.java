package com.lanou.dao;

import com.lanou.entity.Carousel;

import java.util.List;

/**
 * Created by lanou on 2017/12/2.
 */
public interface CarouselMapper {

    public List<Carousel> selectRandom(int num);
}
