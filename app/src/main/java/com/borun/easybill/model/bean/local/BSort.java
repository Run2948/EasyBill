package com.borun.easybill.model.bean.local;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 账单分类bean
 */
@Entity
public class BSort {

    @Id
    private String _id;
    private String uid;
    private String sortName;
    private String sortImg;
    private Boolean income;

    @Generated(hash = 2092983496)
    public BSort() {
    }

    @Generated(hash = 1512215185)
    public BSort(String _id, String uid, String sortName, String sortImg,
            Boolean income) {
        this._id = _id;
        this.uid = uid;
        this.sortName = sortName;
        this.sortImg = sortImg;
        this.income = income;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortImg() {
        return sortImg;
    }

    public void setSortImg(String sortImg) {
        this.sortImg = sortImg;
    }

    public Boolean getIncome() {
        return income;
    }

    public void setIncome(Boolean income) {
        this.income = income;
    }

    public String getId() {
        return this._id;
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

    public String get_id() {
        return this._id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}