package com.borun.easybill.model.bean.remote;

import com.borun.easybill.model.bean.local.BPay;
import com.borun.easybill.model.bean.BaseBean;

public class BPayBean extends BaseBean {

    private String _id;

    private String uid;

    private String payName;

    private String payImg;

    private String payNum;

    public BPayBean(){
        super();
    }

    public BPayBean(BPay pay){
        this.payName = pay.getPayName();
        this.payImg = pay.getPayImg();
    }

    public BPayBean(String uid, String payName, String payImg, String payNum) {
        this.uid = uid;
        this.payName = payName;
        this.payImg = payImg;
        this.payNum = payNum;
    }

    public BPayBean(String id, String uid, String payName, String payImg, String payNum) {
        this._id = id;
        this.uid = uid;
        this.payName = payName;
        this.payImg = payImg;
        this.payNum = payNum;
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

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName == null ? null : payName.trim();
    }

    public String getPayImg() {
        return payImg;
    }

    public void setPayImg(String payImg) {
        this.payImg = payImg == null ? null : payImg.trim();
    }

    public String getPayNum() {
        return payNum;
    }

    public void setPayNum(String payNum) {
        this.payNum = payNum == null ? null : payNum.trim();
    }
}