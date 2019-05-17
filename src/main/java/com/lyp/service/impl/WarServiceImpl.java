package com.lyp.service.impl;


import com.lyp.dao.WarDao;
import com.lyp.utils.Page;
import com.lyp.utils.Pages;
import com.lyp.model.War;
import com.lyp.service.WarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class WarServiceImpl implements WarService{

    @Autowired
    WarDao warDao;

    @Override
    public void showUserTaskByPage(HttpServletRequest request, Model model) throws Exception {

        String pageNow = request.getParameter("pageNow");

        Page page = null;

        List<War> userTaskList = new ArrayList<War>();

        int totalCount = warDao.getUserTaskCount();

        if (pageNow != null){

            page = new Page(totalCount,Integer.parseInt(pageNow));

            //userTaskList = warDao.selectUserTaskByPage(page.getStartPos(),page.getPageSize());
        }else {
            page = new Page(totalCount,1);
        }

        userTaskList = warDao.selectUserTaskByPage(page.getStartPos(),page.getPageSize());

        model.addAttribute("userTaskList",userTaskList);

        model.addAttribute("page",page);
    }

    @Override
    public List<War> selectUserTaskPageList(Pages pages) {

        List<War> list = warDao.selectUserTaskPageList(pages);
        return list;
    }

    @Override
    public Integer selectUserTaskPageCount(Pages pages) {
        return warDao.selectUserTaskPageCount(pages);
    }

    @Override
    public void addTask(String equipmentNumber, String userNumber, String taskAcceptDate,Integer state) {
        warDao.addTask(equipmentNumber,userNumber,taskAcceptDate,state);
    }

    @Override
    public void deleteUserTask(String equipmentNumber) {
        warDao.deleteUserTask(equipmentNumber);
    }

    @Override
    public boolean updateWav(War war) {

        return warDao.updateWav(war);
    }


    //待审核的任务列表
    @Override
    public void showUserTaskByPage1(HttpServletRequest request, Model model) throws Exception {

        String pageNow = request.getParameter("pageNow");
        Page page= null;
        List<War> userTaskList1 = new ArrayList<War>();
        int totalCount = warDao.getUserTaskCount1();
        if (pageNow != null){
            page = new Page(totalCount,Integer.parseInt(pageNow));
            //userTaskList1 = warDao.selectUserTask1ByPage(page.getStartPos(),page.getPageSize());
        }else {

            page = new Page(totalCount,1);
        }
        userTaskList1 = warDao.selectUserTask1ByPage(page.getStartPos(),page.getPageSize());

        model.addAttribute("userTaskList1",userTaskList1);
        model.addAttribute("page",page);

    }

    @Override
    public List<War> selectUserTaskPageList1(Pages pages) {

        List<War> list = warDao.selectUserTaskPageList(pages);
        return list;
    }

    @Override
    public Integer selectUserTaskPageCount1(Pages pages) {
        return warDao.selectUserTaskPageCount1(pages);
    }

    @Override
    public boolean updateWavState(String equipmentNumber, int state) {
        return warDao.updateWavState(equipmentNumber,state);
    }
}
