package com.lanou.service.impl;

import com.lanou.dao.BrandMapper;
import com.lanou.dao.CategoryMapper;
import com.lanou.dao.FloorMapper;
import com.lanou.dao.GoodsMapper;
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

    public Floor showFloor(int fId) {
        Floor floor = floorMapper.showFloor(fId);
        Category c = categoryMapper.findByFid(fId);
        int cId = c.getcId();//一级目录id
        System.out.println("获取到的目录Id:"+cId);
        List<Brand> brands = brandMapper.selectByC_id(cId);
        List<Category> categories = categoryMapper.findChildCategory(cId); //查找二级目录
        List<Integer> gIds1 = goodsMapper.findGoodsIdByCategory1Random(cId);//随机查询一级目录下8个商品的id
        List<Goods> hotgoods = goodsMapper.findGoodsInCategoryId(gIds1);

        List<Integer> gIds2 = goodsMapper.findGoodsIdByCategory1Random(cId);//随机查询一级目录下8个商品的id
        List<Goods> newgoods = goodsMapper.findGoodsInCategoryId(gIds2);


        floor.setCategories(categories);
        floor.setBrands(brands);
        floor.setHotGoods(hotgoods);
        floor.setNewGoods(newgoods);

        List<Category> title = categoryMapper.findRandomTwo(cId);
        floor.setTitle(title);

        List<Goods> goods1 = goodsMapper.findGoodsInCategoryId(goodsMapper.findGoodsIdByCategory2Random(title.get(0).getcId()));
        List<Goods> goods2 = goodsMapper.findGoodsInCategoryId(goodsMapper.findGoodsIdByCategory2Random(title.get(1).getcId()));
        floor.setT1Goods(goods1);
        floor.setT2Goods(goods2);
        return floor;

    }
}