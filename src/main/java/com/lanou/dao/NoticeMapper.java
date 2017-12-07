package com.lanou.dao;

import com.lanou.entity.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lanou on 2017/12/7.
 */
public interface NoticeMapper {

    public List<Notice> findNoticeRandom(int num);

    public List<Notice> findAllNotice();

}
