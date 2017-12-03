package com.lanou.service.impl;

import com.lanou.dao.CategoryMapper;
import com.lanou.dao.FloorMapper;
import com.lanou.entity.Category;
import com.lanou.entity.Floor;
import com.lanou.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanou on 2017/12/2.
 */
@Service("floorService")
public class FloorServiceImpl implements FloorService{

    @Autowired
    private FloorMapper floorMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    public Floor showFloor(int fId) {
        Floor floor = floorMapper.showFloor(fId);
        List<Category> c = categoryMapper.findByFid(fId);
        List<Category> categories2 = null;
        for (int i=0;i<c.size();i++){
             categories2 = categoryMapper.findChildCategory(c.get(i).getcId());
        }
        floor.setCategories(categories2);
//        List<Category> categories = categoryMapper.findChildCategory(cId);
//        floor.setCategories(categories);
        return floor;

    }
}
