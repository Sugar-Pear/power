package com.lyp.controller;

import com.lyp.model.Equipment;
import com.lyp.model.Pages;
import com.lyp.model.ResultMap;
import com.lyp.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

@Controller
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @RequestMapping("/equipmentlist")
    public ModelAndView toequipmentlist(ModelAndView modelAndView) {
        modelAndView.setViewName("equipmentlist");
        return modelAndView;
    }

    @RequestMapping("/equipmentlists")
    @ResponseBody
    public ResultMap<List<Equipment>> backContent(Pages page, @RequestParam("limit") int limit) {

        page.setRows(limit);

        //System.out.println("page:" + page.toString());

        List<Equipment> contentList = equipmentService.selectPageList(page);

        int totals = equipmentService.selectPageCount(page);

        for (int i = 0; i < contentList.size(); i++) {
            System.out.println(contentList.get(i).toString());
        }

        page.setTotalRecord(totals);

        ResultMap<List<Equipment>> equipmentResultMap = new ResultMap<List<Equipment>>(0, "", totals, contentList);

        return equipmentResultMap;
    }


    //设备任务
    @RequestMapping(value = "/saytask")
    public ModelAndView saytask(ModelAndView modelAndView){
        System.out.println("/saytask");
        modelAndView.setViewName("announcetask");
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/sendtask",method = RequestMethod.POST)
    public String  sendtask(@RequestBody Map<String ,String> map ){

           String taskBirthDate;
           System.out.println("/sendtask");
           String equipmentNumber = map.get("number");
           String task = map.get("task");
           String endDate = map.get("endDate");
           Date date = new Date();
           SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");

           taskBirthDate = simpleFormat.format(date);

           System.out.println("n:"+equipmentNumber+" t:"+task+" time:"+simpleFormat.format(date));
           // System.out.println(equipmentService.findEquipmentByNum(equipmentNumber).toString());
        if (equipmentService.findEquipmentByNum(equipmentNumber)!=null)
        {
            System.out.println("true");
           if (equipmentService.findEquStateByNum(equipmentNumber).equals("未维修")){
               equipmentService.updateState(equipmentNumber,"维修");
               equipmentService.sendtask(equipmentNumber,task,taskBirthDate,endDate);
               return "1";
           }else {
               return "2";
           }
       }else {
           return "0";
       }
    }

    //删除设备
    @RequestMapping(value = "/equipmentdelete")
    @ResponseBody
    public boolean delectUser(String equipmentNumber) {
        System.out.println("删除");
        System.out.println("userNumber====" + equipmentNumber);
        //删除
        equipmentService.deleteequipmentByNum(equipmentNumber);
        return true;
    }

    @RequestMapping("/tasklists")
    public ModelAndView tL(ModelAndView m){
        m.setViewName("tasklist");
        return m;
    }

    //任务列表
    @RequestMapping("/tasklist")
    @ResponseBody
    public ResultMap<List<Equipment>> tasklist(Pages page, @RequestParam("limit") int limit) {
        System.out.println("limit:++++"+limit);
        page.setRows(limit);

        List<Equipment> contentList = equipmentService.selectPageListByState(page);

        int totals = equipmentService.selectPageCountByState(page);

        page.setTotalRecord(totals);

        ResultMap<List<Equipment>> equipmentResultMap = new ResultMap<List<Equipment>>(0, "", totals, contentList);

        return equipmentResultMap;
    }

}
