package com.lanou.dao;

import com.lanou.entity.Category;
import com.lanou.entity.Floor;

import java.util.List;
import java.util.Map;

/**
 * Created by lanou on 2017/12/2.
 */
public interface FloorMapper {

    public Floor showFloor(int fId);

    public List<Floor> showAllFloors();

    public boolean exchangeFloor(Map<String,Object> map);

    public String selectFloorName(Integer fId);

    public Integer selectCidByFid(int fid);

    public boolean changeFloorContent(Integer f1,Integer f2);

    public List<Integer> selectlunbo(int fid);

    public boolean updatelunbo(Map<String,Object> map);

    public boolean changeFloorName(Map<String,Object> map);

    public boolean deleteFloor(int fid);

    public List<Floor> selectHasDeleted();

    public boolean replaceFloor(int fid);
}
