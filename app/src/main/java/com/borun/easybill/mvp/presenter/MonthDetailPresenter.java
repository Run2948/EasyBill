package com.borun.easybill.mvp.presenter;

import com.borun.easybill.base.BasePresenter;
import com.borun.easybill.model.bean.local.BBill;

public abstract  class MonthDetailPresenter extends BasePresenter {

    public abstract void getMonthDetailBills(int id,String year,String month);

    public abstract void deleteBill(Long id);

    public abstract void updateBill(BBill bBill);
}
