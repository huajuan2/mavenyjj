package com.lanou.service;

import com.lanou.entity.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lanou on 2017/12/7.
 */
public interface NoticeService {

    //随机查找公告
    public List<Notice> findNoticeRandom();

    //查找到所有的公告
    public List<Notice> findAllNotice();

}
