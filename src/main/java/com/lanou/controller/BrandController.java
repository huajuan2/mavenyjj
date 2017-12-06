package com.lanou.controller;

import com.lanou.entity.Brand;
import com.lanou.service.BrandService;
import com.lanou.util.FastJson_All;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lanou on 2017/12/2.
 */
@Controller
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping("/update.do")
//    @ResponseBody
    public void updates(HttpServletResponse response){

//        return brandService.showBrands();
        FastJson_All.toJson(brandService.showBrands(),response);
    }

    @RequestMapping("/findAll.do")
//    @ResponseBody
    public void findAll(HttpServletResponse response){

//        return brandService.showAll();
        FastJson_All.toJson(brandService.showAll(),response);
    }




}
