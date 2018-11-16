package com.wonders.itemstaffmanage.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_task", catalog = "itemstaffmanage")
public class TbTask implements Serializable{
    private String stId;
    private String stName;
    private Date dtCreatetime;
    private byte stState;

    private TbItem item;
    private TbStaff staff;

    @ManyToOne(optional = false)//fetch = FetchType.LAZY
    @JoinColumn(name = "ST_STAFF_ID")
    public TbStaff getStaff() {
        return staff;
    }

    public void setStaff(TbStaff staff) {
        this.staff = staff;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "ST_ITEM_ID")
    public TbItem getItem() {
        return item;
    }

    public void setItem(TbItem item) {
        this.item = item;
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

    @Column(name = "DT_CREATETIME")
    public Date getDtCreatetime() {
        return dtCreatetime;
    }

    public void setDtCreatetime(Date dtCreatetime) {
        this.dtCreatetime = dtCreatetime;
    }

    @Column(name = "ST_STATE")
    public byte getStState() {
        return stState;
    }

    public void setStState(byte stState) {
        this.stState = stState;
    }

}
