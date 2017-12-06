package com.lanou.entity;

/**
 * Created by lanou on 2017/12/6.
 */
public class GuiGe {
    private Integer guigeId;
    private String guigeName;

    public GuiGe(Integer guigeId, String guigeName) {
        this.guigeId = guigeId;
        this.guigeName = guigeName;
    }

    public Integer getGuigeId() {
        return guigeId;
    }

    public void setGuigeId(Integer guigeId) {
        this.guigeId = guigeId;
    }

    public String getGuigeName() {
        return guigeName;
    }

    public void setGuigeName(String guigeName) {
        this.guigeName = guigeName;
    }

    @Override
    public String toString() {
        return "GuiGe{" +
                "guigeId=" + guigeId +
                ", guigeName='" + guigeName + '\'' +
                '}';
    }

    public GuiGe() {
    }
}
