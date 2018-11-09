package com.borun.easybill.model.bean;

/**
 * Created by Qing on 2018/10/16 0016.
 * 通用javabean
 */

public class BaseBean {
    /**
     * status : 100
     * message : 成功！
     */

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}