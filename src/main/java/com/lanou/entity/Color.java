package com.lanou.entity;

/**
 * Created by lanou on 2017/12/6.
 */
public class Color {
    private Integer colorId;
    private String colorName;

    public Color(Integer colorId, String colorName) {
        this.colorId = colorId;
        this.colorName = colorName;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    @Override
    public String toString() {
        return "Color{" +
                "colorId=" + colorId +
                ", colorName='" + colorName + '\'' +
                '}';
    }

    public Color() {
    }
}
