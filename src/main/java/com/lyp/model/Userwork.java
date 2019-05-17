package com.lyp.model;

public class Userwork {

    private String userNumber;
    private Integer userRepairAmount;
    private Integer userCancleAmount;

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public Integer getUserRepairAmount() {
        return userRepairAmount;
    }

    public void setUserRepairAmount(Integer userRepairAmount) {
        this.userRepairAmount = userRepairAmount;
    }

    public Integer getUserCancleAmount() {
        return userCancleAmount;
    }

    public void setUserCancleAmount(Integer userCancleAmount) {
        this.userCancleAmount = userCancleAmount;
    }

    @Override
    public String toString() {
        return "Userwork{" +
                "userNumber='" + userNumber + '\'' +
                ", userRepairAmount=" + userRepairAmount +
                ", userCancleAmount=" + userCancleAmount +
                '}';
    }
}
