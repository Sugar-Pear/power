package com.lyp.controller;

import com.lyp.model.Equipment;
import com.lyp.utils.Pages;
import com.lyp.utils.ResultMap;
import com.lyp.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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


    //跳转任务界面
    @RequestMapping(value = "/saytask")
    public ModelAndView saytask(ModelAndView modelAndView){
        System.out.println("/saytask");
        modelAndView.setViewName("announcetask");
        return modelAndView;
    }

    //发布任务
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
           if (equipmentService.findEquStateByNum(equipmentNumber).equals("良好")){
               equipmentService.updateState(equipmentNumber,"等待维修");
               equipmentService.sendtask(equipmentNumber,task,taskBirthDate,endDate);
               return "1";
           }else if (equipmentService.findEquStateByNum(equipmentNumber).equals("等待维修")){
               return "3";
           }
           else {
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
        equipmentService.deleteTask(equipmentNumber);
        return true;
    }

    //编辑设备
    @ResponseBody
    @RequestMapping(value = "/equipmentmodify",method = RequestMethod.POST)
    public String equipmentmodify(@RequestBody Map<String,Object> map){

        String equipmentNumber = (String) map.get("number");

        String equipmentCount = (String) map.get("amount");

        int equipmentCount1 =  Integer.parseInt(equipmentCount);

        System.out.println("equipdadadadadadad"+equipmentNumber);

        equipmentService.updateEquipmentCount(equipmentCount1,equipmentNumber);

        return "1";
    }



    //跳转任务列表
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

    //添加设备
    @ResponseBody
    @RequestMapping(value = "/addEq",method = RequestMethod.POST)
    public String addequipment(@RequestBody Map<String,Object> map){

        String equipmentNumber = (String) map.get("number");

        String equipmentName = (String) map.get("name");

        String amount1 = (String) map.get("amount");
        Integer equipmentAmount = Integer.parseInt(amount1);

        String equipmentInDate = (String) map.get("indate");

        String equipmentVoltage1 = (String) map.get("v");
        Integer equipmentVoltage = Integer.parseInt(equipmentVoltage1);

        String equipmentI1 = (String) map.get("i");
        Integer equipmentI = Integer.parseInt(equipmentI1);

        String equipmentState = "良好";

        Equipment equipment = new Equipment();

        equipment.setEquipmentNumber(equipmentNumber);
        equipment.setEquipmentName(equipmentName);
        equipment.setEquipmentAmount(equipmentAmount);
        equipment.setEquipmentInDate(equipmentInDate);
        equipment.setEquipmentState(equipmentState);
        equipment.setEquipmentVoltage(equipmentVoltage);
        equipment.setEquipmentI(equipmentI);

        System.out.println("EEEQQQ"+equipment);

        if (equipmentService.findEquStateByNum(equipmentNumber)!=null){

            return "2";
        }else if (equipmentService.addequipment(equipment)){

            return "1";
        }

        return "0";
    }

}
