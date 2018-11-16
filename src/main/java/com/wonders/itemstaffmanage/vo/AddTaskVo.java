package com.wonders.itemstaffmanage.vo;

public class AddTaskVo {
    private String staffId; //人员id
    private String itemId;  //项目id
    private String taskName;//任务名称

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public AddTaskVo() {

    }

    public AddTaskVo(String staffId, String itemId, String taskName) {

        this.staffId = staffId;
        this.itemId = itemId;
        this.taskName = taskName;
    }
}
