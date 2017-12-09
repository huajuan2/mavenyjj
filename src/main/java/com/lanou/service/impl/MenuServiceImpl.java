package com.lanou.service.impl;

import com.lanou.dao.MenuMapper;
import com.lanou.entity.Menu;
import com.lanou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanou on 2017/12/8.
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    //查看菜单
    public List<Menu> findMenuById(){

        int menuId = 0;

        List<Menu> menuList = menuMapper.findMenuById(menuId);
        for (int i=0;i<menuList.size();i++){

            menuList.get(i).setMenuList(menuMapper.findMenuById(menuList.get(i).getMenuId()));
        }


        return menuList;
    }

}
