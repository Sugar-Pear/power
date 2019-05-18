package com.lyp.service;

import com.lyp.model.Company;

import java.util.List;

public interface CompanyService {

    boolean addCompany(Company company);
    boolean deleteCompanyByName(String companyName);
    boolean updateCompany(String companyProvince,String companyCity,String companyArea);
    List<Company> selectAllCompany();
    String selectCheckNumById(Integer id);

}
