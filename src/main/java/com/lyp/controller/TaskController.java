package com.lyp.controller;


import com.lyp.model.Task;
import com.lyp.model.User;
import com.lyp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class TaskController {

    @Autowired
    TaskService taskService;

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

        taskService.addTask(equipmentNumber,userNumber,taskAcceptDate);

        return "1";
    }
}
