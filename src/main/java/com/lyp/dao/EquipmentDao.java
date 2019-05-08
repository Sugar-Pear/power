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

    @Select("select * from equipment order by equipmentNumber DESC limit #{start},#{rows}")
    List<Equipment> selectPageList(Pages pages);

    @Select("select count(*) from equipment")
    Integer selectPageCount(Pages pages);

    @Insert("insert into equipment(equipmentNumber,equipmentName,equipmentAmount,equipmentInDate,equipmentState,equipmentState,equipmentVoltage,equipmentElectricCurrent) " +
            "values(#{equipmentNumber},#{equipmentName},#{equipmentAmount},#{equipmentInDate},#{equipmentState},#{equipmentVoltage},#{equipmentElectricCurrent})")
    void addEquipment(Equipment equipment);

    @Delete("delete from equipment where equipmentNumber=#{equipmentNumber}")
    void deleteequipmentByNum(String equipmentNumber);

    @Insert("insert into task(equipmentNumber,taskDes,taskBirthDate) values(#{0},#{1},#{2})")
    void sendtask(String equipmentNumber, String task, String taskBirthDate);

    @Update("update equipment set equipmentState=#{1} where equipmentNumber=#{0}")
    void updateState(String equipmentNumber, String state);


    @Select("select * from equipment where equipmentNumber=#{0}")
    Equipment findEquipmentByNum(String equipmentNumber);

    @Select("select equipmentState from equipment where equipmentNumber=#{0}")
    String findEquStateByNum(String equipmentNumber);

    //任务列表
    @Select("select count(*) from equipment where equipmentState='维修'")
    int getEquipmentCountByState();

    @Select("select * from equipment where equipmentState='维修' limit #{startNow},#{pageSize}")
    List<Equipment> selectEquipmentByPageByState(@Param(value = "startPage") Integer startPage, @Param(value = "pageSize") Integer pageSize) throws Exception;

    @Select("select * from equipment where equipmentState='维修' order by equipmentNumber DESC limit #{start},#{rows}")
    List<Equipment> selectPageListByState(Pages pages);

    @Select("select count(*) from equipment where equipmentState='维修'")
    Integer selectPageCountByState(Pages pages);
}
