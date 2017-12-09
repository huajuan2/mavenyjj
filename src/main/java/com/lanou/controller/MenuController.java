package com.lanou.controller;

import com.lanou.entity.Menu;
import com.lanou.service.MenuService;
import com.lanou.util.FastJson_All;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lanou on 2017/12/8.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findMenu.do")
    public void findMenu(HttpServletResponse response){

        List<Menu> menuList = menuService.findMenuById();

        FastJson_All.toJson(menuList,response);
    }

}
