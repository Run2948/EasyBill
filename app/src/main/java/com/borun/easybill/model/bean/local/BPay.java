package com.borun.easybill.model.bean.local;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 支付方式bean
 */
@Entity
public class BPay {

    @Id
    private String _id;
    private String payName;
    private String payImg;
    private String uid;

    @Generated(hash = 48271616)
    public BPay() {
    }

    @Generated(hash = 2077662387)
    public BPay(String _id, String payName, String payImg, String uid) {
        this._id = _id;
        this.payName = payName;
        this.payImg = payImg;
        this.uid = uid;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getPayImg() {
        return payImg;
    }

    public void setPayImg(String payImg) {
        this.payImg = payImg;
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
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}