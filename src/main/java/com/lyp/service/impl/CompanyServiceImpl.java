package com.lyp.service.impl;

import com.lyp.dao.CompanyDao;
import com.lyp.model.Company;
import com.lyp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyDao companyDao;


    @Override
    public boolean addCompany(Company company) {
        return companyDao.addCompany(company);
    }

    @Override
    public boolean deleteCompanyByName(String companyName) {
        return companyDao.deleteCompanyByName(companyName);
    }

    @Override
    public boolean updateCompany(String companyProvince, String companyCity, String companyArea) {
        return companyDao.updateCompany(companyProvince,companyCity,companyArea);
    }

    @Override
    public List<Company> selectAllCompany() {
        List<Company> list = companyDao.selectAllCompany();
        return list;
    }

    @Override
    public String selectCheckNumById(Integer id) {
        return companyDao.selectCheckNumById(id);
    }
}
