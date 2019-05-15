package com.lyp.controller;


import com.lyp.model.*;
import com.lyp.service.EquipmentService;
import com.lyp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class TaskController {

    @Autowired
    TaskService taskService;
    @Autowired
    EquipmentService equipmentService;

    //任务信息
    @RequestMapping(value = "/taskmessage1")
    @ResponseBody
    public String taskmessage(String equipmentNumber, HttpServletRequest request){

        System.out.println("hdashdkjashdaks++++"+equipmentNumber);

        Task task = new Task();

        task = taskService.selectTaskByNum(equipmentNumber);
        request.getSession().setAttribute("task",task);
        return "1";
    }

    //跳转任务信息界面
    @RequestMapping(value = "/taskmessage2")
    public String taskmessage2(HttpServletRequest request){
        Task task = (Task) request.getSession().getAttribute("task");
        System.out.println("task：：：：：：：：：：：：："+task);
        return "taskmessage";
    }

    //取消发布的任务
    @ResponseBody
    @RequestMapping(value = "taskdelete")
    public String taskdelete(String equipmentNumber){
        equipmentService.updateState(equipmentNumber,"良好");
        taskService.deleteTask(equipmentNumber);
        return "1";
    }
}
