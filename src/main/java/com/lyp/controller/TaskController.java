package com.lyp.controller;


import com.lyp.model.Task;
import com.lyp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
}