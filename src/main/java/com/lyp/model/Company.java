package com.lyp.model;

public class Company {

    private String companyName;
    private String companyCheckNumber;
    private String companyProvince;
    private String companyCity;
    private String companyArea;

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
                "companyName='" + companyName + '\'' +
                ", companyCheckNumber='" + companyCheckNumber + '\'' +
                ", companyProvince='" + companyProvince + '\'' +
                ", companyCity='" + companyCity + '\'' +
                ", companyArea='" + companyArea + '\'' +
                '}';
    }
}
