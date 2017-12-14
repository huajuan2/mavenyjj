package com.lanou.controller;

import com.lanou.entity.Manager;
import com.lanou.service.ManagerService;
import com.lanou.util.FastJson_All;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by lanou on 2017/12/13.
 */
@Controller
@RequestMapping("/managers")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @RequestMapping("/loginManager.do")
    public void login(HttpServletResponse response, Manager manager){
        boolean result = false;
        if (managerService.loginManager(manager)){
                result = true;
        }
        FastJson_All.toJson(result,response);
    }
}
