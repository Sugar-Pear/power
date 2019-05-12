package com.lyp.dao;

import com.lyp.model.Pages;
import com.lyp.model.War;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WarDao {

    //接收任务
    @Insert("insert into war(userNumber,equipmentNumber,taskAcceptDate) values(#{1},#{0},#{2})")
    void addTask(String equipmentNumber, String userNumber,String taskAcceptDate);




    //查看个人任务
    @Select("select count(*) from war")
    int getUserTaskCount();

    @Select("select * from war limit #{startNow},#{pageSize}")
    List<War> selectUserTaskByPage(@Param(value = "startPage") Integer startPage, @Param(value = "pageSize") Integer pageSize) throws Exception;

    @Select("select * from war order by userNumber ASC limit #{start},#{rows}")
    List<War> selectUserTaskPageList(Pages pages);

    @Select("select count(*) from war")
    Integer selectUserTaskPageCount(Pages pages);

}
