package com.lyp.service;

import com.lyp.model.Task;

public interface TaskService {

    Task selectTaskByNum(String equipmentNuber);

    void addTask(String equipmentNumber, String userNumber,String taskAcceptDate);
}
