package com.lyp.controller;


import com.lyp.model.Pages;
import com.lyp.model.ResultMap;
import com.lyp.model.User;
import com.lyp.model.War;
import com.lyp.service.EquipmentService;
import com.lyp.service.WarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class WarController {

    @Autowired
    WarService warService;

    @Autowired
    EquipmentService equipmentService;


    //用户接收任务
    //接收任务
    @ResponseBody
    @RequestMapping(value = "/acceptTask",method = RequestMethod.POST)
    public String acceptTask(HttpServletRequest httpServletRequest,@RequestBody Map map){

        String equipmentNumber = (String) map.get("number");

        System.out.println("dadadadadadadadadad"+equipmentNumber);

        User user= (User) httpServletRequest.getSession().getAttribute("user");

        String userNumber = user.getUserNumber();

        String taskAcceptDate;

        Date date = new Date();

        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");

        taskAcceptDate = simpleFormat.format(date);

       if(equipmentService.findEquStateByNum(equipmentNumber).equals("等待维修")){
           equipmentService.updateState(equipmentNumber,"维修中");
            warService.addTask(equipmentNumber,userNumber,taskAcceptDate);
            return "1";
       }else{
           return "2";
       }

    }



    //用户查看已领取的任务
    @RequestMapping(value = "looktask")
    public String looktask(HttpServletRequest request){

        return "usertask";
    }

    //已领取任务的列表
    @ResponseBody
    @RequestMapping(value = "usertasklist")
    public ResultMap<List<War>> backContent(Pages page, @RequestParam("limit") int limit) {

        page.setRows(limit);

        List<War> contentList = warService.selectUserTaskPageList(page);

        int totals = warService.selectUserTaskPageCount(page);

        page.setTotalRecord(totals);

        ResultMap<List<War>> userTaskResultMap = new ResultMap<List<War>>(0, "", totals, contentList);

        return userTaskResultMap;
    }


}
