package com.lyp.dao;

import com.lyp.model.Equipment;
import com.lyp.model.Pages;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EquipmentDao {

    //设备列表
    @Select("select count(*) from equipment")
    int getEquipmentCount();

    @Select("select * from equipment limit #{startNow},#{pageSize}")
    List<Equipment> selectEquipmentByPage(@Param(value = "startPage") Integer startPage, @Param(value = "pageSize") Integer pageSize) throws Exception;

    @Select("select * from equipment order by equipmentNumber ASC limit #{start},#{rows}")
    List<Equipment> selectPageList(Pages pages);

    @Select("select count(*) from equipment")
    Integer selectPageCount(Pages pages);

    @Insert("insert into equipment(equipmentNumber,equipmentName,equipmentAmount,equipmentInDate,equipmentState,equipmentVoltage,equipmentI) " +
            "values(#{equipmentNumber},#{equipmentName},#{equipmentAmount},#{equipmentInDate},#{equipmentState},#{equipmentVoltage},#{equipmentI})")
    void addEquipment(Equipment equipment);

    //添加任务
    @Insert("insert into task(equipmentNumber,taskDes,taskBirthDate,taskEndDate) values(#{0},#{1},#{2},#{3})")
    void sendtask(String equipmentNumber, String task, String taskBirthDate,String endDate);

    @Update("update equipment set equipmentState=#{1} where equipmentNumber=#{0}")
    void updateState(String equipmentNumber, String state);

    @Select("select * from equipment where equipmentNumber=#{0}")
    Equipment findEquipmentByNum(String equipmentNumber);

    @Select("select equipmentState from equipment where equipmentNumber=#{0}")
    String findEquStateByNum(String equipmentNumber);

    //任务列表
    @Select("select count(*) from equipment where equipmentState='等待维修'")
    int getEquipmentCountByState();

    @Select("select * from equipment where equipmentState='等待维修' limit #{startNow},#{pageSize}")
    List<Equipment> selectEquipmentByPageByState(@Param(value = "startPage") Integer startPage, @Param(value = "pageSize") Integer pageSize) throws Exception;

    @Select("select * from equipment where equipmentState='等待维修' order by equipmentNumber ASC limit #{start},#{rows}")
    List<Equipment> selectPageListByState(Pages pages);

    @Select("select count(*) from equipment where equipmentState='等待维修'")
    Integer selectPageCountByState(Pages pages);

    //删除设备
    @Delete("delete from equipment where equipmentNumber=#{equipmentNumber}")
    void deleteequipmentByNum(String equipmentNumber);

    //删除任务列表
    @Delete("delete from task where equipmentNumber=#{equipmentNumber}")
    void deleteTask(String equipmentNumber);

    //删除用户接受的任务
    @Delete("delete from usertask where equipmentNumber=#{equipmentNumber}")
    void deleteUserTask(String equipmentNumber);

    //修改设备数量
    @Update("update equipment set equipmentAmount=#{0} where equipmentNumber=#{1}")
    void updateEquipmentCount(int equipmentAmount,String equipmentNumber);
}
