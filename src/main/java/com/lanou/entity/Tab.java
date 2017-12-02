package com.lanou.entity;

import java.util.List;

/**
 * Created by lanou on 2017/12/1.
 */
public class Tab {

    private Integer tabId;

    private String tabName;

    private Integer category_id;

    private Integer parentId;

    private List<Tab> tabList;




    public Integer getTabId() {
        return tabId;
    }

    public void setTabId(Integer tabId) {
        this.tabId = tabId;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Tab> getTabList() {
        return tabList;
    }

    public void setTabList(List<Tab> tabList) {
        this.tabList = tabList;
    }

    public Tab(Integer tabId, String tabName, Integer category_id, Integer parentId, List<Tab> tabList) {
        this.tabId = tabId;
        this.tabName = tabName;
        this.category_id = category_id;
        this.parentId = parentId;
        this.tabList = tabList;
    }

    public Tab() {
    }

    @Override
    public String toString() {
        return "Tab{" +
                "tabId=" + tabId +
                ", tabName='" + tabName + '\'' +
                ", category_id=" + category_id +
                ", parentId=" + parentId +
                ", tabList=" + tabList +
                '}';
    }
}
