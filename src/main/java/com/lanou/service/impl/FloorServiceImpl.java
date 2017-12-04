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

import java.util.List;
import java.util.Random;

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
    private GoodsService goodsService;

    public Floor showFloor(int fId) {
        Floor floor = floorMapper.showFloor(fId);
        Category c = categoryMapper.findByFid(fId);
        int cId = c.getcId();
        System.out.println("获取到的目录Id:"+cId);
        List<Brand> brands = brandMapper.selectByC_id(cId);
        List<Category> categories = categoryMapper.findChildCategory(cId); //二级目录
        List<Goods> goods = goodsService.findGoodsByCategoryId(cId);
        Goods[] hotgoods = new Goods[8];
        for (int i=0;i<hotgoods.length;i++){
            hotgoods[i] = goods.get(i);
        }

        Goods[] newgoods = new Goods[8];
        for (int i=0;i<newgoods.length;i++){
            newgoods[i] = goods.get(i+8);
        }
        floor.setCategories(categories);
        floor.setBrands(brands);
        floor.setHotGoods(hotgoods);
        floor.setNewGoods(newgoods);

        List<Category> title = categoryMapper.findRandomTwo(cId);
        floor.setTitle(title);

        List<Goods> goods1 = goodsService.findGoodsByCategoryIdRandom(title.get(0).getcId());
        System.out.println(goods1);
        List<Goods> goods2 = goodsService.findGoodsByCategoryIdRandom(title.get(1).getcId());
        floor.setT1Goods(goods1);
        floor.setT2Goods(goods2);
        return floor;

    }
}
