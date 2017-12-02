package com.lanou.entity;

import java.util.List;

/**
 * Created by lanou on 2017/12/1.
 */
public class Category {
    private Integer cId;
    private String cName;
    private int parent_id;
    private List<Category> childs;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public List<Category> getChilds() {
        return childs;
    }

    public void setChilds(List<Category> childs) {
        this.childs = childs;
    }

    public Category() {
    }

    @Override
    public String toString() {
        return "Category{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                ", parent_id=" + parent_id +
                ", childs=" + childs +
                '}';
    }
}
