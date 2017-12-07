package com.lanou.service.impl;

import com.lanou.dao.NoticeMapper;
import com.lanou.entity.Notice;
import com.lanou.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanou on 2017/12/7.
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    //随机查找公告
    public List<Notice> findNoticeRandom(){

        int num = 4;
        List<Notice> noticeList = noticeMapper.findNoticeRandom(num);

        return noticeList;
    }

    //查找到所有的公告
    public List<Notice> findAllNotice(){

        List<Notice> noticeList = noticeMapper.findAllNotice();

        return noticeList;
    }


}
