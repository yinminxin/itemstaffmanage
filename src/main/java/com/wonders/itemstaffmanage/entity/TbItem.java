package com.wonders.itemstaffmanage.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tb_item", catalog = "itemstaffmanage")
public class TbItem implements Serializable{
    private String stId;
    private String stName;
    private byte stState;
    private Date stCreatetime;

    private int taskNowNum; //当前进行中的任务数
    private int taskFinishNum; //当前已完成的任务数

    private List<TbTask> tbTasks = new ArrayList<>(0);

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "item")
    public List<TbTask> getTbTasks() {
        return tbTasks;
    }

    public void setTbTasks(List<TbTask> tbTasks) {
        this.tbTasks = tbTasks;
    }

    @Id
    @Column(name = "ST_ID")
    public String getStId() {
        return stId;
    }

    public void setStId(String stId) {
        this.stId = stId;
    }

    @Column(name = "ST_NAME")
    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    @Column(name = "ST_STATE")
    public byte getStState() {
        return stState;
    }

    public void setStState(byte stState) {
        this.stState = stState;
    }

    @Column(name = "DT_CREATETIME")
    public Date getStCreatetime() {
        return stCreatetime;
    }

    public void setStCreatetime(Date stCreatetime) {
        this.stCreatetime = stCreatetime;
    }

    @Transient
    public int getTaskNowNum() {
        return taskNowNum;
    }

    public void setTaskNowNum(int taskNowNum) {
        this.taskNowNum = taskNowNum;
    }

    @Transient
    public int getTaskFinishNum() {
        return taskFinishNum;
    }

    public void setTaskFinishNum(int taskFinishNum) {
        this.taskFinishNum = taskFinishNum;
    }
}
