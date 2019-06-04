package com.borun.easybill.mvp.presenter.Imp;

import com.borun.easybill.model.bean.local.BPay;
import com.borun.easybill.model.bean.local.BSort;
import com.borun.easybill.model.bean.local.NoteBean;
import com.borun.easybill.mvp.model.Imp.NoteModelImp;
import com.borun.easybill.mvp.model.NoteModel;
import com.borun.easybill.mvp.presenter.NotePresenter;
import com.borun.easybill.mvp.view.NoteView;

public class NotePresenterImp extends NotePresenter implements NoteModelImp.NoteOnListener{

    private NoteModel model;
    private NoteView view;

    public NotePresenterImp(NoteView view) {
        this.model=new NoteModelImp(this);
        this.view = view;
    }

    @Override
    public void onSuccess(NoteBean bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onSuccess(BSort bean) {
        view.loadDataSuccess(bean);
    }

    @Override
    public void onSuccess(BPay bean) {
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
    public void addSort(BSort bSort) {
        model.addSort(bSort);
    }

    @Override
    public void addPay(BPay bPay) {
        model.addPay(bPay);
    }

    @Override
    public void deleteSort(String id) {
        model.deleteSort(id);
    }

    @Override
    public void deletePay(String id) {
        model.deletePay(id);
    }
}
