package com.lyp.service;

import com.lyp.model.Pages;
import com.lyp.model.Task;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TaskService {

    Task selectTaskByNum(String equipmentNuber);

}
