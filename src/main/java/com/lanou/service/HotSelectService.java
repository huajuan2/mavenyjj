package com.lanou.service;

import java.util.List;

/**
 * Created by lanou on 2017/12/5.
 */
public interface HotSelectService {

    public List<String> findHot();

    public void updateHotSelect(String selectName);
}
