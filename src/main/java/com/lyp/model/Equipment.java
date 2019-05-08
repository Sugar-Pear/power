package com.lyp.model;

public class Equipment {
    private String equipmentNumber;
    private String equipmentName;
    private String equipmentInDate;
    private String equipmentState;
    private Integer equipmentAmount;
    private Integer equipmentVoltage;
    private Integer equipmentElectricCurrent;

    public String getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentInDate() {
        return equipmentInDate;
    }

    public void setEquipmentInDate(String equipmentInDate) {
        this.equipmentInDate = equipmentInDate;
    }

    public String getEquipmentState() {
        return equipmentState;
    }

    public void setEquipmentState(String equipmentState) {
        this.equipmentState = equipmentState;
    }

    public Integer getEquipmentAmount() {
        return equipmentAmount;
    }

    public void setEquipmentAmount(Integer equipmentAmount) {
        this.equipmentAmount = equipmentAmount;
    }

    public Integer getEquipmentVoltage() {
        return equipmentVoltage;
    }

    public void setEquipmentVoltage(Integer equipmentVoltage) {
        this.equipmentVoltage = equipmentVoltage;
    }

    public Integer getEquipmentElcetricCurrent() {
        return equipmentElectricCurrent;
    }

    public void setEquipmentElectricCurrent(Integer equipmentEletricCurrent) {
        this.equipmentElectricCurrent = equipmentEletricCurrent;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentNumber='" + equipmentNumber + '\'' +
                ", equipmentName='" + equipmentName + '\'' +
                ", equipmentInDate='" + equipmentInDate + '\'' +
                ", equipmentState='" + equipmentState + '\'' +
                ", equipmentAmount=" + equipmentAmount +
                ", equipmentVoltage=" + equipmentVoltage +
                ", equipmentEletricCurrent=" + equipmentElectricCurrent +
                '}';
    }
}
