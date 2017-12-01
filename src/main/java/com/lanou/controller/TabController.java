package com.lanou.controller;

import com.lanou.entity.Tab;
import com.lanou.service.TabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lanou on 2017/12/1.
 */
@Controller
@RequestMapping("/tab")
public class TabController {
    @Autowired
    private TabService tabService;

    @RequestMapping("/findsTab.do")
    @ResponseBody
    public List<Tab> finds(Model model,int categoryId){

        List<Tab> tabList = tabService.findTabByCategory(categoryId);

        model.addAttribute("tabList",tabList);

        System.out.print(tabList);

        return tabList;
    }

}
