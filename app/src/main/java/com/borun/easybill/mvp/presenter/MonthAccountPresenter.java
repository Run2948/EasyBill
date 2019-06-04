package com.borun.easybill.mvp.presenter;

import com.borun.easybill.base.BasePresenter;

public abstract  class MonthAccountPresenter extends BasePresenter {

    public abstract void getMonthAccountBills(String id,String year,String month);
}
