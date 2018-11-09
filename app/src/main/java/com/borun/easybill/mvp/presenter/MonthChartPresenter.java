package com.borun.easybill.mvp.presenter;

import com.borun.easybill.base.BasePresenter;

public abstract  class MonthChartPresenter extends BasePresenter {

    public abstract void getMonthChartBills(int id,String year,String month);
}
