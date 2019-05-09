package com.lyp.dao;

import com.lyp.model.Task;
import org.apache.ibatis.annotations.Select;

public interface TaskDao {

    //通过Num查询任务
    @Select("select * from task where equipmentNumber=#{equipmentNumber}")
    Task selectTaskByNum(String equipmentNumber);
}
