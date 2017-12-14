package com.lanou.service;

import com.lanou.entity.Goods;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lanou on 2017/12/4.
 */
public interface CollectionService {
    public List<Goods> findGoodsList(Integer uId);

    public Integer findRepeat(Integer uId,Integer gId);

    public boolean addCollection(Integer uId,Integer gId);

//    public boolean deleteCollection(Integer uId,Integer gId);

    public boolean deleteCollection(HttpServletRequest request,Integer user_id);
}
