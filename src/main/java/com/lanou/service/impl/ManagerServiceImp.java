package com.lanou.service.impl;

import com.lanou.dao.ManagerMapper;
import com.lanou.entity.Manager;
import com.lanou.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lanou on 2017/12/13.
 */
@Service("managerService")
public class ManagerServiceImp implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;

    public boolean loginManager(Manager manager){
        Manager manager1 = managerMapper.loginManager(manager);
        if (manager1==null){
            return false;
        }
        return true;
    }
}
