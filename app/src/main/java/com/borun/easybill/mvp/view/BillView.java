package com.borun.easybill.mvp.view;

import com.borun.easybill.base.BaseView;
import com.borun.easybill.model.bean.BaseBean;
import com.borun.easybill.model.bean.local.NoteBean;

public interface BillView extends BaseView<BaseBean>{

    void loadDataSuccess(NoteBean tData);
}
