package com.lanou.service;

import com.lanou.entity.Menu;

import java.util.List;

/**
 * Created by lanou on 2017/12/8.
 */
public interface MenuService {

    //查看菜单
    public List<Menu> findMenuById();

}
