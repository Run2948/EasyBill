package com.borun.easybill.mvp.presenter.Imp;

import com.borun.easybill.model.bean.BaseBean;
import com.borun.easybill.model.bean.local.BBill;
import com.borun.easybill.model.bean.local.NoteBean;
import com.borun.easybill.mvp.model.BillModel;
import com.borun.easybill.mvp.model.Imp.BillModelImp;
import com.borun.easybill.mvp.presenter.BillPresenter;
import com.borun.easybill.mvp.view.BillView;

public class BillPresenterImp extends BillPresenter implements BillModelImp.BillOnListener {

    private BillModel model;
    private BillView view;

    public BillPresenterImp(BillView view) {
        this.model = new BillModelImp(this);
        this.view = view;
    }

    @Override
    public void onSuccess(BaseBean bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onSuccess(NoteBean bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onFailure(Throwable e) {
        view.loadDataError(e);
    }

    @Override
    public void getNote() {
        model.getNote();
    }

    @Override
    public void add(BBill bBill) {
        model.add(bBill);
    }

    @Override
    public void update(BBill bBill) {
        model.update(bBill);
    }

    @Override
    public void delete(String id) {
        model.delete(id);
    }
}
