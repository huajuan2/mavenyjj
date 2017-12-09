package com.lanou.dao;

import com.lanou.entity.Receive;

import java.util.List;

/**
 * Created by lanou on 2017/12/7.
 */
public interface ReceiveMapper {

    public List<Receive> findReceivesByUid(int uId);

    public boolean addReceive(Receive receive);

    public Receive findReceiveById(int id);

    public boolean updateReceiveById(Receive receive);

    public boolean deleteById(int id);

    public boolean addOneTime(Receive receive);

    public int findNewId();

    public boolean saveOneTime(int id);
}
