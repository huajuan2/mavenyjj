package com.lanou.controller;

import com.lanou.service.FloorService;
import com.lanou.util.FastJson_All;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.HashMap;
/**
 * Created by lanou on 2017/12/13.
 */

@Controller
@RequestMapping("/floorManage")
public class FloorManageController {

    @Autowired
    private FloorService floorService;
//************后台管理之楼层***********
    @RequestMapping("/findFloors.do")
    public void findFloors(HttpServletResponse response){
        FastJson_All.toJson(floorService.showAllFloors(),response);
    }

    @RequestMapping("/exchangeFloor.do")
    public void exchangeFloor(Integer fId1,Integer fId2,HttpServletResponse response){

        boolean result = floorService.exchangeFloor(fId1,fId2);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("result",result);
        FastJson_All.toJson(map,response);
    }

    @RequestMapping("/changeFloorName.do")
    public void changeFloorName(Integer fId,String newName,HttpServletResponse response){
        boolean result = floorService.changeFloorName(fId,newName);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("result",result);
        FastJson_All.toJson(map,response);
    }

    @RequestMapping("/deleteFloor.do")
    public void deleteFloor(Integer fId,HttpServletResponse response){
        boolean result = floorService.deleteFloor(fId);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("result",result);
        FastJson_All.toJson(map,response);
    }

    @RequestMapping("/findHasDeleted.do")
    public void findHasDeleted(HttpServletResponse response){
        FastJson_All.toJson(floorService.findHasDeleted(),response);
    }

    @RequestMapping("/replaceFloor.do")
    public void replaceFloor(Integer fId,HttpServletResponse response){
        boolean result = floorService.replaceFloor(fId);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("result",result);
        FastJson_All.toJson(map,response);
    }
}
