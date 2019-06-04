package com.borun.easybill.mvp.model;

import com.borun.easybill.model.bean.local.BBill;

public interface MonthDetailModel {

    /**
     * 每月账单详情
     */
    void getMonthDetailBills(String id, String year, String month);

    /**
     * 删除账单
     */
    void delete(String id);

    void update(BBill bBill);

    void onUnsubscribe();
}
