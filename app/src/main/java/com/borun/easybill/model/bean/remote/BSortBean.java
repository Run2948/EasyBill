package com.borun.easybill.model.bean.remote;

import com.borun.easybill.model.bean.local.BSort;
import com.borun.easybill.model.bean.BaseBean;

public class BSortBean extends BaseBean {
    private String _id;

    private String uid;

    private String sortName;

    private String sortImg;

    private Integer priority;

    private Boolean income;

    public BSortBean(){
        super();
    }

    public BSortBean(BSort sort) {
        this.sortName = sort.getSortName();
        this.sortImg = sort.getSortImg();
        this.income = sort.getIncome();
    }

    public BSortBean(String uid, String sortName, String sortImg, Boolean income) {
        this.uid = uid;
        this.sortName = sortName;
        this.sortImg = sortImg;
        this.income = income;
    }
    public BSortBean(String id, String uid, String sortName, String sortImg, Integer priority, Boolean income) {
        this._id = id;
        this.uid = uid;
        this.sortName = sortName;
        this.sortImg = sortImg;
        this.priority = priority;
        this.income = income;
    }



    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName == null ? null : sortName.trim();
    }

    public String getSortImg() {
        return sortImg;
    }

    public void setSortImg(String sortImg) {
        this.sortImg = sortImg == null ? null : sortImg.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean getIncome() {
        return income;
    }

    public void setIncome(Boolean income) {
        this.income = income;
    }
}