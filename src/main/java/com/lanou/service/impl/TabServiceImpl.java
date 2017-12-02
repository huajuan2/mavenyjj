package com.lanou.service.impl;

import com.lanou.dao.TabMapper;
import com.lanou.entity.Tab;
import com.lanou.service.TabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanou on 2017/12/1.
 */
@Service("tabService")
public class TabServiceImpl implements TabService {

    @Autowired
    private TabMapper tabMapper;

    //查找标签
    public List<Tab> findTabByCategory(int category_id) {

        //根据第一层级的id查找标签
        List<Tab> tabList = tabMapper.findTabByCategory(category_id);

        for (int i=0;i<tabList.size();i++){

            int tabId = tabList.get(i).getTabId();

            tabList.get(i).setTabList(findTabByParentId(tabId));

        }

        return tabList;
    }

    //递归，根据parentId查找tab标签
    public List<Tab> findTabByParentId(int parentId) {

        List<Tab> tabList = tabMapper.findTabByParentId(parentId);

        return tabList;
    }


}
