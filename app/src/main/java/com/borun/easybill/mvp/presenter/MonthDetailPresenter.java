package com.borun.easybill.mvp.presenter;

import com.borun.easybill.base.BasePresenter;
import com.borun.easybill.model.bean.local.BBill;

public abstract  class MonthDetailPresenter extends BasePresenter {

    public abstract void getMonthDetailBills(String id,String year,String month);

    public abstract void deleteBill(String id);

    public abstract void updateBill(BBill bBill);
}
