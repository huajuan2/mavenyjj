package com.lanou.controller;
import com.lanou.entity.Receive;
import com.lanou.entity.User;
import com.lanou.service.ReceiveService;
import com.lanou.util.FastJson_All;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lanou on 2017/12/8.
 */
@Controller
@RequestMapping("/receive")
public class ReceiveController {

    @Autowired
    private ReceiveService receiveService;

    @RequestMapping("/findMyReceive.do")
    public void findMyReceive(HttpServletRequest request, HttpServletResponse response){
        User user = (User)request.getSession().getAttribute("user1");
        FastJson_All.toJson(receiveService.findAllReceivesByUser(user.getuId()),response);
    }

    @RequestMapping("/addReceive.do")
    public void addReceive(Receive receive,HttpServletRequest request, HttpServletResponse response){
        User user = (User)request.getSession().getAttribute("user1");
        receive.setU_id(user.getuId());
        Boolean result = receiveService.addReceive(receive);
        FastJson_All.toJson(result,response);
    }

//    根据id找收货地址
    @RequestMapping("/updateReceive.do")
    public void updateReceive(int id, HttpServletResponse response){
        FastJson_All.toJson(receiveService.findReceiveById(id),response);
    }

//    保存修改后的地址
    @RequestMapping("/saveUpdate.do")
    public void saveUpdate(Receive receive,HttpServletResponse response){
        Boolean result = receiveService.updateReceive(receive);
        FastJson_All.toJson(result,response);
    }

    @RequestMapping("/deleteReceive.do")
    public void deleteReceive(int id,HttpServletResponse response){
        Boolean result = receiveService.deleteReceive(id);
        FastJson_All.toJson(result,response);
    }

//    选择省
    @RequestMapping("/findProvince.do")
    public void findProvince(HttpServletResponse response){
        FastJson_All.toJson(receiveService.findCityLevelOne(),response);
    }

    @RequestMapping("/findChildCity.do")
    public void findChildCity(int cityId,HttpServletResponse response){
        FastJson_All.toJson(receiveService.findCityChildLevel(cityId),response);
    }


}
