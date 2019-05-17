package com.lyp.controller;

import com.lyp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CompanyController {

    @Autowired
    CompanyService companyService;




}
