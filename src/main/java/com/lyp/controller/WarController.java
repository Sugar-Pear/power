package com.lyp.controller;


import com.alibaba.fastjson.JSONObject;
import com.lyp.model.*;
import com.lyp.service.EquipmentService;
import com.lyp.service.WarService;
import com.lyp.utils.Pages;
import com.lyp.utils.ResultMap;
import com.lyp.utils.RunPythonWav;
import com.lyp.utils.Wartwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

        Integer state = 0;

       if(equipmentService.findEquStateByNum(equipmentNumber).equals("等待维修")){
           equipmentService.updateState(equipmentNumber,"维修中");
            warService.addTask(equipmentNumber,userNumber,taskAcceptDate,state);
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
    public ResultMap<List<Wartwo>> backContent(Pages page, @RequestParam("limit") int limit) {

        page.setRows(limit);

        List<War> contentList = warService.selectUserTaskPageList(page);
        List<Wartwo> list = new ArrayList<Wartwo>();

        for (int i = 0; i < contentList.size(); i++) {
            Wartwo wartwo = new Wartwo();
            String equipmentNumber = contentList.get(i).getEquipmentNumber();
            String equipmentName = equipmentService.findEquipmentByNum(equipmentNumber).getEquipmentName();
             wartwo.setEquipmentName(equipmentName);
             wartwo.setEquipmentNumber(contentList.get(i).getEquipmentNumber());
             wartwo.setTaskAcceptDate(contentList.get(i).getTaskAcceptDate());
            list.add(wartwo);
        }

        for (int i=0;i<list.size();i++){
            System.out.println("这是一个新的list"+list.get(i).toString());
        }

        int totals = warService.selectUserTaskPageCount(page);

        page.setTotalRecord(totals);

        ResultMap<List<Wartwo>> userTaskResultMap = new ResultMap<List<Wartwo>>(0, "", totals, list);

        return userTaskResultMap;
    }

    //取消任务
    @ResponseBody
    @RequestMapping(value = "/deleteusertask")
    boolean deleteusertask(String equipmentNumber){

        System.out.println("delectusertask===>>"+equipmentNumber);
        warService.deleteUserTask(equipmentNumber);
        equipmentService.updateState(equipmentNumber,"等待维修");
        return true;
    }

    //上传文件
    @ResponseBody
    @RequestMapping(value = "/uploadFile")
    JSONObject uploadFile(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws IOException {

        JSONObject res = new JSONObject();
        JSONObject resUrl = new JSONObject();
        if (file !=null){
            String fileName = file.getOriginalFilename();
            String extName = fileName.substring(fileName.lastIndexOf("."));
            List list = new ArrayList();
            list.add(".wav");
            list.add(".mp3");
            if (list.contains(extName.toLowerCase())){
                Date date = new Date();
                SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
                String getTime = simpleFormat.format(date);
                String realFilePath = "D:\\studysoft\\ideaproject\\power\\src\\main\\webapp\\uploadFile";
                String fileName1 = getTime+extName;
                String descPath = realFilePath + "\\" + fileName1;
                System.out.println("大哥文件"+descPath);
                file.transferTo(new File(realFilePath,fileName1));
                res.put("src",fileName1);
                res.put("code",1);
                return res;
            }
            res.put("code",-1);
            return res;
        }
        //System.out.println("uploadFile===>>");
        res.put("code",0);
        return res;
    }

    //提交任务至数据库
    @ResponseBody
    @RequestMapping(value = "/submitTask1",method = RequestMethod.POST)
    String submitTask(@RequestBody Map<String,Object> map){

        String equipmentNumber = (String) map.get("number");
        String checkProblem = (String) map.get("problem");
        String warFile = (String) map.get("wavSrc");
        Date date = new Date();
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy.MM.dd hh.mm.ss");
        String taskEndDate = simpleFormat.format(date);

        War war = new War();
        Integer state = 1;
        war.setEquipmentNumber(equipmentNumber);
        war.setCheckProblem(checkProblem);
        war.setUploadWar(warFile);
        war.setTaskEndDate(taskEndDate);
        war.setState(state);
        if(warService.updateWav(war)){
            equipmentService.updateState(equipmentNumber,"等待审核");
            return "1";
        }
        //System.out.println("数字"+equipmentNumber+"问题"+checkProblem+"文件名"+warFile);

        return "0";
    }

    //跳转审核中心
    @RequestMapping(value = "/checkwav")
    public String checkwav(){
        return "checkwav";
    }

    //展示需要审核的任务
    @ResponseBody
    @RequestMapping(value = "/checkwavlist")
    public ResultMap<List<War>> backContent1(Pages pages,@RequestParam(value = "limit") int limit){
        pages.setRows(limit);
        System.out.println("page:"+pages.toString());
        List<War> contentList = warService.selectUserTaskPageList1(pages);
        for (int i=0;i<contentList.size();i++){
            System.out.println("管理员将要审核的表"+contentList.get(i).toString());
        }
        int totals = warService.selectUserTaskPageCount1(pages);
        pages.setTotalRecord(totals);
        ResultMap<List<War>> checkWavResultMap = new ResultMap<List<War>>(0,"",totals,contentList);
        return checkWavResultMap;
    }

    //是否通过审核
    @ResponseBody
    @RequestMapping(value = "/checkWav")
    public String checkWavOk(@RequestParam(value = "equipmentNumber") String equipmentNumber){

        int state = 2;
        if (warService.updateWavState(equipmentNumber,state)){
            return "1";
        }
        return "0";
    }

    //wav测试
    @RequestMapping(value = "/test1")
    public void test1() throws IOException {
        //D:\studysoft\pythonPro\picture\DemoPicture.py
        String[] arg = new String[]{"python","D:\\studysoft\\pythonPro\\picture\\DemoPicture.py",""};
        RunPythonWav.getPython(arg);
    }


}
