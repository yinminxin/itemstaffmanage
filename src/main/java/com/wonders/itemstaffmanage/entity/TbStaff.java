package com.wonders.itemstaffmanage.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "tb_staff", catalog = "itemstaffmanage")
public class TbStaff implements Serializable{
    private String stId;
    private String stName;
    private byte stState;
    private Date stCreatetime;
    private String stUsername;
    private String stPassword;
    private short stRole;

    private int taskNowNum; //当前进行中的任务数

    private List<TbTask> tbTasks = new ArrayList<>(0);


    @Transient
    public int getTaskNowNum() {
        return taskNowNum;
    }

    public void setTaskNowNum(int taskNowNum) {
        this.taskNowNum = taskNowNum;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "staff")
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

    @Column(name = "ST_USERNAME")
    public String getStUsername() {
        return stUsername;
    }

    public void setStUsername(String stUsername) {
        this.stUsername = stUsername;
    }

    @Column(name = "ST_PASSWORD")
    public String getStPassword() {
        return stPassword;
    }

    public void setStPassword(String stPassword) {
        this.stPassword = stPassword;
    }

    @Column(name = "ST_ROLE")
    public short getStRole() {
        return stRole;
    }

    public void setStRole(short stRole) {
        this.stRole = stRole;
    }
}
