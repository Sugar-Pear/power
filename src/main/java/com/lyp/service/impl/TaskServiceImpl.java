package com.lyp.service.impl;


import com.lyp.dao.TaskDao;
import com.lyp.model.Task;
import com.lyp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskDao taskDao;

    @Override
    public Task selectTaskByNum(String equipmentNuber) {
        return taskDao.selectTaskByNum(equipmentNuber);
    }
}
