package com.lanou.entity;

import java.util.List;
import java.util.Map;

/**
 * Created by lanou on 2017/12/2.
 */
public class Floor {

    private Integer fId;
    private String fName;
    private List<Goods> hotGoods;
    private List<Goods> newGoods;
    private Map<String,List<Goods>> list1;
    private Map<String,List<Goods>> list2;

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

    public Map<String, List<Goods>> getList1() {
        return list1;
    }

    public void setList1(Map<String, List<Goods>> list1) {
        this.list1 = list1;
    }

    public Map<String, List<Goods>> getList2() {
        return list2;
    }

    public void setList2(Map<String, List<Goods>> list2) {
        this.list2 = list2;
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
                ", list1=" + list1 +
                ", list2=" + list2 +
                '}';
    }
}
