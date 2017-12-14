package com.lanou.service;

import com.lanou.entity.Floor;

import java.util.List;

/**
 * Created by lanou on 2017/12/2.
 */
public interface FloorService {
    public Floor showFloor(int fId);

    public List<Floor> showAllFloors();

    public boolean exchangeFloor(Integer fId1,Integer fId2);

    public boolean changeFloorName(Integer fId,String newName);

    public boolean deleteFloor(Integer fId);

    public List<Floor> findHasDeleted();

    public boolean replaceFloor(Integer fId);
}
