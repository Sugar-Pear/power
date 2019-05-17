package com.lyp.controller;

import com.lyp.service.UserworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserworkController {

    @Autowired
    UserworkService userworkService;



}
