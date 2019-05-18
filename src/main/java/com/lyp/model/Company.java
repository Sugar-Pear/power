package com.lyp.model;

public class Company {

    private Integer id;
    private String companyName;
    private String companyCheckNumber;
    private String companyProvince;
    private String companyCity;
    private String companyArea;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCheckNumber() {
        return companyCheckNumber;
    }

    public void setCompanyCheckNumber(String companyCheckNumber) {
        this.companyCheckNumber = companyCheckNumber;
    }

    public String getCompanyProvince() {
        return companyProvince;
    }

    public void setCompanyProvince(String companyProvince) {
        this.companyProvince = companyProvince;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyArea() {
        return companyArea;
    }

    public void setCompanyArea(String companyArea) {
        this.companyArea = companyArea;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", companyCheckNumber='" + companyCheckNumber + '\'' +
                ", companyProvince='" + companyProvince + '\'' +
                ", companyCity='" + companyCity + '\'' +
                ", companyArea='" + companyArea + '\'' +
                '}';
    }
}
