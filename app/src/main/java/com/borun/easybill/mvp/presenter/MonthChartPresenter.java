package com.borun.easybill.mvp.presenter;

import com.borun.easybill.base.BasePresenter;

public abstract  class MonthChartPresenter extends BasePresenter {

    public abstract void getMonthChartBills(String id,String year,String month);
}
