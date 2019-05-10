package com.lyp.service;

import com.lyp.model.Equipment;
import com.lyp.model.Pages;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EquipmentService {

    void showEquipmentByPage(HttpServletRequest request, Model model) throws Exception;

    //分页数据
    List<Equipment> selectPageList(Pages pages);
    //分页数据总数
    Integer selectPageCount(Pages pages);

    boolean addequipment(Equipment equipment);

    void deleteequipmentByNum(String equipmentNumber);

    void sendtask(String equipmentNumber, String task,String taskBirthDate,String endDate);

    void updateState(String equipmentNumber,String state);

    Equipment findEquipmentByNum(String equipmentNumber);

    String findEquStateByNum(String equipmentNumber);

    //任务分页
    void showEquipmentByPageByState(HttpServletRequest request, Model model) throws Exception;
    //分页数据
    List<Equipment> selectPageListByState(Pages pages);
    //分页数据总数
    Integer selectPageCountByState(Pages pages);

    //删除任务
    void deleteTask(String equipmentNumber);

    //删除用户接收任务
    void deleteUserTask(String equipmentNumber);

    //修改设备数量
    void updateEquipmentCount(int equipmentAmount,String equipmentNumber);

}
