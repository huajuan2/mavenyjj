package com.lanou.entity;

/**
 * Created by lanou on 2017/12/7.
 */
public class Notice {

    private Integer noticeId;
    private String noticeName;
    private String noticeUrl;

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeName() {
        return noticeName;
    }

    public void setNoticeName(String noticeName) {
        this.noticeName = noticeName;
    }

    public String getNoticeUrl() {
        return noticeUrl;
    }

    public void setNoticeUrl(String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }

    public Notice(Integer noticeId, String noticeName, String noticeUrl) {
        this.noticeId = noticeId;
        this.noticeName = noticeName;
        this.noticeUrl = noticeUrl;
    }

    public Notice() {
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeId=" + noticeId +
                ", noticeName='" + noticeName + '\'' +
                ", noticeUrl='" + noticeUrl + '\'' +
                '}';
    }
}
