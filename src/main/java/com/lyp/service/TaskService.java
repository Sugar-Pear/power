package com.lyp.service;

import com.lyp.model.Task;

public interface TaskService {

    Task selectTaskByNum(String equipmentNumber);

    void deleteTask(String equipmentNumber);

}
