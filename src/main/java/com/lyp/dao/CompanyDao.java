package com.lyp.dao;

import com.lyp.model.Company;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CompanyDao {

    //添加公司
    @Insert("insert into company(companyName,companyCheckNumber,companyProvince,companyCity,companyArea) " +
            "values(#(companyName),#{companyCheckNumber},#{companyProvince},#{companyCity},#{companyArea})")
    boolean addCompany(Company company);

    //删除公司
    @Delete("delete from company where companyName=#{companyName}")
    boolean deleteCompanyByName(String companyName);

    //修改公司
    @Update("update company set companyProvince=#{0},companyCity=#{1},companyArea=#{2}")
    boolean updateCompany(String companyProvince,String companyCity,String companyArea);

    //搜索所有的公司
    @Select("select * from company")
    List<Company> selectAllCompany();

}
