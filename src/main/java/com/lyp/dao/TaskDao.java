package com.lyp.dao;

import com.lyp.model.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface TaskDao {

    //通过Num查询任务
    @Select("select * from task where equipmentNumber=#{equipmentNumber}")
    Task selectTaskByNum(String equipmentNumber);

    //接收任务
    @Insert("insert into usertask(userNum,equipmentNum,taskAcceptDate) values(#{1},#{0},#{2})")
    void addTask(String equipmentNumber, String userNumber,String taskAcceptDate);

}
