package com.wonders.itemstaffmanage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_page_navigation", catalog = "itemstaffmanage")
public class TbPageNavigation implements Serializable {
    private String stId;
    private String stName;
    private String stUrl;
    private String stPictureUrl;
    private byte stState;

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
    @Column(name = "ST_URL")
    public String getStUrl() {
        return stUrl;
    }

    public void setStUrl(String stUrl) {
        this.stUrl = stUrl;
    }

    @Column(name = "ST_PICTUREURL")
    public String getStPictureUrl() {
        return stPictureUrl;
    }

    public void setStPictureUrl(String stPictureUrl) {
        this.stPictureUrl = stPictureUrl;
    }

    @Column(name = "ST_STATE")
    public byte getStState() {
        return stState;
    }

    public void setStState(byte stState) {
        this.stState = stState;
    }

    public TbPageNavigation() {
    }

    public TbPageNavigation(String stId, String stName, String stUrl, String stPictureUrl, byte stState) {

        this.stId = stId;
        this.stName = stName;
        this.stUrl = stUrl;
        this.stPictureUrl = stPictureUrl;
        this.stState = stState;
    }
}
