package com.lyp.model;

public class Task {
    private String equipmentNumber;
    private String taskDes;
    private String taskBirthDate;
    private String taskEndDate;

    public String getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public String getTaskDes() {
        return taskDes;
    }

    public void setTaskDes(String taskDes) {
        this.taskDes = taskDes;
    }

    public String getTaskBirthDate() {
        return taskBirthDate;
    }

    public void setTaskBirthDate(String taskBirthDate) {
        this.taskBirthDate = taskBirthDate;
    }

    public String getTaskEndDate() {
        return taskEndDate;
    }

    public void setTaskEndDate(String taskEndDate) {
        this.taskEndDate = taskEndDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "equipmentNumber='" + equipmentNumber + '\'' +
                ", taskDes='" + taskDes + '\'' +
                ", taskBirthDate='" + taskBirthDate + '\'' +
                ", taskEndDate='" + taskEndDate + '\'' +
                '}';
    }
}
