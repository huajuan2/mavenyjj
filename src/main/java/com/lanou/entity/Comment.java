package com.lanou.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by lanou on 2017/12/9.
 */
public class Comment {
    private Integer commentId;
    private Integer commentIndex;
    private String commentInfo;
    private String commentDate;
    private Integer commentLike;
    private Integer goods_id;
    private Integer user_id;
    private User user;
    private Goods goods;
    public Comment() {
    }

    public Comment(Integer commentId, Integer commentIndex, String commentInfo, String commentDate, Integer commentLike, Integer goods_id, Integer user_id, User user) {
        this.commentId = commentId;
        this.commentIndex = commentIndex;
        this.commentInfo = commentInfo;
        this.commentDate = commentDate;
        this.commentLike = commentLike;
        this.goods_id = goods_id;
        this.user_id = user_id;
        this.user = user;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentIndex() {
        return commentIndex;
    }

    public void setCommentIndex(Integer commentIndex) {
        this.commentIndex = commentIndex;
    }

    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getCommentLike() {
        return commentLike;
    }

    public void setCommentLike(Integer commentLike) {
        this.commentLike = commentLike;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentIndex=" + commentIndex +
                ", commentInfo='" + commentInfo + '\'' +
                ", commentDate='" + commentDate + '\'' +
                ", commentLike=" + commentLike +
                ", goods_id=" + goods_id +
                ", user_id=" + user_id +
                ", user=" + user +
                ", goods=" + goods +
                '}';
    }
}
