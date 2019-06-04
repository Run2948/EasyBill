package com.borun.easybill.model.bean.local;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 账单bean
 */
@Entity
public class BBill {

    @Id
    private String _id;  //本地id
    private int rid;  //服务器端id
    private float cost;  //金额
    private String content;  //内容
    private String userid;  //用户id
    private String payName;  //支付方式
    private String payImg;  //
    private String sortName;  //账单分类
    private String sortImg;  //
    private long crdate;  //创建时间
    private boolean income;  //收入支出
    private int version;  //版本

    @Generated(hash = 124482664)
    public BBill() {
    }

    @Generated(hash = 445949505)
    public BBill(String _id, int rid, float cost, String content, String userid, String payName, String payImg,
            String sortName, String sortImg, long crdate, boolean income, int version) {
        this._id = _id;
        this.rid = rid;
        this.cost = cost;
        this.content = content;
        this.userid = userid;
        this.payName = payName;
        this.payImg = payImg;
        this.sortName = sortName;
        this.sortImg = sortImg;
        this.crdate = crdate;
        this.income = income;
        this.version = version;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public long getCrdate() {
        return crdate;
    }

    public void setCrdate(long crdate) {
        this.crdate = crdate;
    }

    public boolean isIncome() {
        return income;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean getIncome() {
        return this.income;
    }

    public void setIncome(boolean income) {
        this.income = income;
    }

    public String get_id() {
        return this._id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
