package com.lanou.service.impl;

import com.lanou.dao.*;
import com.lanou.entity.Brand;
import com.lanou.entity.Category;
import com.lanou.entity.Floor;
import com.lanou.entity.Goods;
import com.lanou.service.FloorService;
import com.lanou.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by lanou on 2017/12/2.
 */
@Service("floorService")
public class FloorServiceImpl implements FloorService{

    @Autowired
    private FloorMapper floorMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private CarouselMapper carouselMapper;

    public Floor showFloor(int fId) {
        Floor floor = floorMapper.showFloor(fId);
        Category c = categoryMapper.findByFid(fId);
        int cId = c.getcId();//一级目录id
        System.out.println("获取到的目录Id:"+cId);
        List<Brand> brands = brandMapper.selectByC_id(cId);
        List<Category> categories = categoryMapper.findChildCategory(cId); //查找二级目录
        List<Integer> gIds1 = goodsMapper.findGoodsIdByCategory1(cId);//随机查询一级目录下8个商品的id
        List<Goods> hotgoods = goodsMapper.findGoodsInCategoryIdRandom(gIds1);

        List<Integer> gIds2 = goodsMapper.findGoodsIdByCategory1(cId);//随机查询一级目录下8个商品的id
        List<Goods> newgoods = goodsMapper.findGoodsInCategoryIdRandom(gIds2);


        floor.setCategories(categories);
        floor.setBrands(brands);
        floor.setHotGoods(hotgoods);
        floor.setNewGoods(newgoods);

        List<Category> title = categoryMapper.findRandomTwo(cId);
        floor.setTitle(title);
        floor.setT1Goods(goodsMapper.findGoodsInCategoryIdRandom(goodsMapper.findGoodsIdByCategory2(title.get(0).getcId())));
        floor.setT2Goods(goodsMapper.findGoodsInCategoryIdRandom(goodsMapper.findGoodsIdByCategory2(title.get(1).getcId())));

        floor.setCarousels(carouselMapper.selectFloorRandom(fId,3));
        return floor;
    }
}