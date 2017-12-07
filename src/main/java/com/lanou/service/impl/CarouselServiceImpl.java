package com.lanou.service.impl;

import com.lanou.dao.CarouselMapper;
import com.lanou.entity.Carousel;
import com.lanou.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Created by lanou on 2017/12/2.
 */
@Service("carouselService")
public class CarouselServiceImpl implements CarouselService{

    @Autowired
    private CarouselMapper carouselMapper;

    public List<Carousel> showCarousel() {
        Random random = new Random();
        int num = random.nextInt(3)+3;
        System.out.println("num:"+num);
        return carouselMapper.selectRandom(num);
    }

    //楼层的轮播图
    public List<Carousel> selectFloorRandom(int floor_id){

        int num = 3;

        List<Carousel> carouselList = carouselMapper.selectFloorRandom(floor_id,num);

        return carouselList;
    }

}
