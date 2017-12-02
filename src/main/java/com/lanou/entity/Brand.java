package com.lanou.entity;

/**
 * Created by lanou on 2017/12/2.
 */
public class Brand {
    private Integer bId;
    private String bName;
    private String url;

    public Integer getbId() {
        return bId;
    }

    public void setbId(Integer bId) {
        this.bId = bId;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Brand() {
    }

    @Override
    public String toString() {
        return "Brand{" +
                "bId=" + bId +
                ", bName='" + bName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
