package com.borun.easybill.mvp.view;

import com.borun.easybill.base.BaseView;
import com.borun.easybill.model.bean.local.BPay;
import com.borun.easybill.model.bean.local.BSort;
import com.borun.easybill.model.bean.local.NoteBean;

public interface NoteView extends BaseView<NoteBean>{

    /**
     * 请求数据成功
     * @param tData
     */
    void loadDataSuccess(BSort tData);
    void loadDataSuccess(BPay tData);
}
