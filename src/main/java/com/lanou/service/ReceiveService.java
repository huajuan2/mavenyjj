package com.lanou.service;

import com.lanou.entity.City;
import com.lanou.entity.Receive;

import java.util.List;

/**
 * Created by lanou on 2017/12/8.
 */
public interface ReceiveService {

    public List<Receive> findAllReceivesByUser(int uId);

    public boolean addReceive(Receive receive);

    public Receive findReceiveById(int id);

    public boolean updateReceive(Receive receive);

    public boolean deleteReceive(int id);

    public List<City> findCityLevelOne();

    public List<City> findCityChildLevel(Integer parentId);
}
