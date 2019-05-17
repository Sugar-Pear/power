package com.lyp.service;

import com.lyp.utils.Pages;
import com.lyp.model.War;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface WarService {

    //分页
    void showUserTaskByPage(HttpServletRequest request, Model model) throws Exception;

    List<War> selectUserTaskPageList(Pages pages);

    Integer selectUserTaskPageCount(Pages pages);

    //用户领取任务
    void addTask(String equipmentNumber, String userNumber,String taskAcceptDate,Integer state);

    //用户取消任务
    void deleteUserTask(String equipmentNumber);

    //添加音频
    boolean updateWav(War war);

    //待审核的任务列表
    void showUserTaskByPage1(HttpServletRequest request, Model model) throws Exception;

    List<War> selectUserTaskPageList1(Pages pages);

    Integer selectUserTaskPageCount1(Pages pages);

    //更改提交任务状态
    boolean updateWavState(String equipmentNumber,int state);
}
