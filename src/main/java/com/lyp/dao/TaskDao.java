package com.lyp.dao;

import com.lyp.model.Equipment;
import com.lyp.model.Pages;
import com.lyp.model.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskDao {

    //通过Num查询任务
    @Select("select * from task where equipmentNumber=#{equipmentNumber}")
    Task selectTaskByNum(String equipmentNumber);

}
