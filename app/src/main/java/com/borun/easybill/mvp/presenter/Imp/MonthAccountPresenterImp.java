package com.borun.easybill.mvp.presenter.Imp;

import com.borun.easybill.model.bean.local.MonthAccountBean;
import com.borun.easybill.mvp.model.Imp.MonthAccountModelImp;
import com.borun.easybill.mvp.model.MonthAccountModel;
import com.borun.easybill.mvp.presenter.MonthAccountPresenter;
import com.borun.easybill.mvp.view.MonthAccountView;

public class MonthAccountPresenterImp extends MonthAccountPresenter implements MonthAccountModelImp.MonthAccountOnListener{

    private MonthAccountModel model;
    private MonthAccountView view;

    public MonthAccountPresenterImp(MonthAccountView view) {
        this.model=new MonthAccountModelImp(this);
        this.view = view;
    }

    @Override
    public void onSuccess(MonthAccountBean bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onFailure(Throwable e) {
        view.loadDataError(e);
    }


    @Override
    public void getMonthAccountBills(int id, String year, String month) {
        model.getMonthAccountBills(id,year,month);
    }
}
