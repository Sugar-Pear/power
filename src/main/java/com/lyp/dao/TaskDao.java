package com.lyp.dao;

import com.lyp.model.Task;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

public interface TaskDao {

    //通过Num查询任务
    @Select("select * from task where equipmentNumber=#{equipmentNumber}")
    Task selectTaskByNum(String equipmentNumber);

    //删除任务
    @Delete("delete from task where equipmentNumber=#{equipmentNumber}")
    void deleteTask(String equipmentNumber);
}
