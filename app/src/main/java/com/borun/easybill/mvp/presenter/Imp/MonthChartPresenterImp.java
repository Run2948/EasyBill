package com.borun.easybill.mvp.presenter.Imp;

import com.borun.easybill.model.bean.local.MonthChartBean;
import com.borun.easybill.mvp.model.Imp.MonthChartModelImp;
import com.borun.easybill.mvp.model.MonthChartModel;
import com.borun.easybill.mvp.presenter.MonthChartPresenter;
import com.borun.easybill.mvp.view.MonthChartView;

public class MonthChartPresenterImp extends MonthChartPresenter implements MonthChartModelImp.MonthChartOnListener{

    private MonthChartModel model;
    private MonthChartView view;

    public MonthChartPresenterImp(MonthChartView view) {
        this.model=new MonthChartModelImp(this);
        this.view = view;
    }

    @Override
    public void onSuccess(MonthChartBean bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onFailure(Throwable e) {
        view.loadDataError(e);
    }

    @Override
    public void getMonthChartBills(int id, String year, String month) {
        model.getMonthChartBills(id,year,month);
    }

}
