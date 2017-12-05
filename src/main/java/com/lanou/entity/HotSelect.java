package com.lanou.entity;

/**
 * Created by lanou on 2017/12/5.
 */
public class HotSelect {

    private Integer selectId;
    private String selectName;
    private Integer selectCount;

    public Integer getSelectId() {
        return selectId;
    }

    public void setSelectId(Integer selectId) {
        this.selectId = selectId;
    }

    public String getSelectName() {
        return selectName;
    }

    public void setSelectName(String selectName) {
        this.selectName = selectName;
    }

    public Integer getSelectCount() {
        return selectCount;
    }

    public void setSelectCount(Integer selectCount) {
        this.selectCount = selectCount;
    }

    public HotSelect() {
    }

    @Override
    public String toString() {
        return "HotSelect{" +
                "selectId=" + selectId +
                ", selectName='" + selectName + '\'' +
                ", selectCount=" + selectCount +
                '}';
    }
}
