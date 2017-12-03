package com.lanou.entity;

import java.util.List;
import java.util.Map;

/**
 * Created by lanou on 2017/12/2.
 */
public class Floor {

    private Integer fId;
    private String fName;
    private List<Brand> brands;
    private List<Category> categories;
    private List<Goods> hotGoods;
    private List<Goods> newGoods;

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Goods> getHotGoods() {
        return hotGoods;
    }

    public void setHotGoods(List<Goods> hotGoods) {
        this.hotGoods = hotGoods;
    }

    public List<Goods> getNewGoods() {
        return newGoods;
    }

    public void setNewGoods(List<Goods> newGoods) {
        this.newGoods = newGoods;
    }



    public Floor() {

    }

    @Override
    public String toString() {
        return "Floor{" +
                "fId=" + fId +
                ", fName='" + fName + '\'' +
                ", hotGoods=" + hotGoods +
                ", newGoods=" + newGoods +
                '}';
    }
}
