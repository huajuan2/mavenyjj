package com.lanou.controller;

import com.lanou.entity.Brand;
import com.lanou.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public List<Brand> updates(){
        return brandService.showBrands();
    }

    @RequestMapping("/findAll.do")
    @ResponseBody
    public List<Brand> findAll(){
        return brandService.showAll();
    }
}
