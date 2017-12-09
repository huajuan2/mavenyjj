package com.lanou.entity;

import java.util.List;

/**
 * Created by lanou on 2017/12/8.
 */
public class Menu {

    private Integer menuId;
    private String menuName;
    private Integer parentId;
    private List<Menu> menuList;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public Menu(Integer menuId, String menuName, Integer parentId, List<Menu> menuList) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.parentId = parentId;
        this.menuList = menuList;
    }

    public Menu() {
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", parentId=" + parentId +
                ", menuList=" + menuList +
                '}';
    }
}

