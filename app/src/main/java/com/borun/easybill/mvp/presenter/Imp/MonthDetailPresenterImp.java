package com.borun.easybill.mvp.presenter.Imp;

import com.borun.easybill.model.bean.BaseBean;
import com.borun.easybill.model.bean.local.BBill;
import com.borun.easybill.model.bean.local.MonthDetailBean;
import com.borun.easybill.mvp.model.Imp.MonthDetailModelImp;
import com.borun.easybill.mvp.model.MonthDetailModel;
import com.borun.easybill.mvp.presenter.MonthDetailPresenter;
import com.borun.easybill.mvp.view.MonthDetailView;


public class MonthDetailPresenterImp extends MonthDetailPresenter implements MonthDetailModelImp.MonthDetailOnListener{

    private MonthDetailModel monthDetailModel;
    private MonthDetailView monthDetailView;

    public MonthDetailPresenterImp(MonthDetailView monthDetailView) {
        this.monthDetailModel=new MonthDetailModelImp(this);
        this.monthDetailView = monthDetailView;
    }

    /**
     * 当月账单
     * @param bean
     */
    @Override
    public void onSuccess(MonthDetailBean bean) {
        monthDetailView.loadDataSuccess(bean);
    }

    @Override
    public void onSuccess(BaseBean bean) {
        monthDetailView.loadDataSuccess(bean);
    }

    @Override
    public void onFailure(Throwable e) {
        monthDetailView.loadDataError(e);
    }

    @Override
    public void getMonthDetailBills(int id, String year, String month) {
        monthDetailModel.getMonthDetailBills(id,year,month);
    }

    @Override
    public void deleteBill(Long id) {
        monthDetailModel.delete(id);
    }

    @Override
    public void updateBill(BBill bBill) {
        monthDetailModel.update(bBill);
    }

}
