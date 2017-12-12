package com.lanou.service.impl;

import com.lanou.dao.CityMapper;
import com.lanou.dao.ReceiveMapper;
import com.lanou.entity.City;
import com.lanou.entity.Receive;
import com.lanou.service.ReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanou on 2017/12/8.
 */
@Service("receiveService")
public class ReceiveServiceImpl implements ReceiveService {

    @Autowired
    private ReceiveMapper receiveMapper;

    @Autowired
    private CityMapper cityMapper;

    public List<Receive> findAllReceivesByUser(int uId) {
        return receiveMapper.findReceivesByUid(uId);
    }

    public boolean addReceive(Receive receive){

        if(receiveMapper.addReceive(receive)){
                return true;
        }else{
            return false;
        }
    }

    public Receive findReceiveById(int id){
        return receiveMapper.findReceiveById(id);
    }

    public boolean updateReceive(Receive receive){
        if(receiveMapper.updateReceiveById(receive)){
            return true;
        }else {
            return false;
        }
    }

    public boolean deleteReceive(int id){
        if(receiveMapper.deleteById(id)){
            return  true;
        }else{
            return  false;
        }
    }

    public List<City> findCityLevelOne(){
        return cityMapper.findLevelOne();
    }

    public List<City> findCityChildLevel(int parentId){
        return cityMapper.findChildLevel(parentId);
    }

}
