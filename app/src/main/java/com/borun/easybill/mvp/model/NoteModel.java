package com.borun.easybill.mvp.model;

import com.borun.easybill.model.bean.local.BPay;
import com.borun.easybill.model.bean.local.BSort;

public interface NoteModel {

    void getNote();

    void addSort(BSort bSort);

    void addPay(BPay bPay);

    void deleteSort(Long id);

    void deletePay(Long id);

    void onUnsubscribe();
}
