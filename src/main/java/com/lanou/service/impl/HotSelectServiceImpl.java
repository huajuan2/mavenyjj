package com.lanou.service.impl;

import com.lanou.dao.HotSelectMapper;
import com.lanou.service.HotSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanou on 2017/12/5.
 */
@Service("hotSelectService")
public class HotSelectServiceImpl implements HotSelectService {

    @Autowired
    private HotSelectMapper hotSelectMapper;

    public List<String> findHot() {
        return hotSelectMapper.selectHot();
    }

    public void updateHotSelect(String hotName){
        Integer num = hotSelectMapper.findSelect(hotName);
        if(num==null){
            hotSelectMapper.addSelect(hotName);
        }
        else{
            hotSelectMapper.updateSelect(hotName);
        }
    }
}
