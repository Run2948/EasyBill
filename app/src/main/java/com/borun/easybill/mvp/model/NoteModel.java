package com.borun.easybill.mvp.model;

import com.borun.easybill.model.bean.local.BPay;
import com.borun.easybill.model.bean.local.BSort;

public interface NoteModel {

    void getNote();

    void addSort(BSort bSort);

    void addPay(BPay bPay);

    void deleteSort(String id);

    void deletePay(String id);

    void onUnsubscribe();
}
