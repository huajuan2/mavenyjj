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

    public List<Floor> showAllFloors(){
        return floorMapper.showAllFloors();
    }

    public boolean exchangeFloor(Integer fId1,Integer fId2){
        String floorName1 = floorMapper.selectFloorName(fId1);
        String floorName2 = floorMapper.selectFloorName(fId2);
        Map<String,Object> map1 = new HashMap<String, Object>();
        map1.put("floorName",floorName1);
        map1.put("fId",fId2);
        floorMapper.exchangeFloor(map1);
        map1.put("floorName",floorName2);
        map1.put("fId",fId1);
        floorMapper.exchangeFloor(map1);

        int cId1 = floorMapper.selectCidByFid(fId1);
        int cId2 = floorMapper.selectCidByFid(fId2);
        floorMapper.changeFloorContent(fId2,cId1);
        floorMapper.changeFloorContent(fId1,cId2);

        List<Integer> lunbo1 = floorMapper.selectlunbo(fId1);
        List<Integer> lunbo2 = floorMapper.selectlunbo(fId2);
        map1.put("fId",fId2);
        map1.put("list",lunbo1);
        floorMapper.updatelunbo(map1);
        map1.put("fId",fId1);
        map1.put("list",lunbo2);
        floorMapper.updatelunbo(map1);

        return true;
    }

    public boolean changeFloorName(Integer fId, String newName){
        Map<String,Object> map1 = new HashMap<String, Object>();
        map1.put("floorName",newName);
        map1.put("fId",fId);
        return floorMapper.changeFloorName(map1);
    }

    public boolean deleteFloor(Integer fId){
        return floorMapper.deleteFloor(fId);
    }

    public List<Floor> findHasDeleted(){
        return floorMapper.selectHasDeleted();
    }

    public boolean replaceFloor(Integer fId){
        return floorMapper.replaceFloor(fId);
    }
}