package com.borun.easybill.mvp.view;

import com.borun.easybill.base.BaseView;
import com.borun.easybill.model.bean.BaseBean;
import com.borun.easybill.model.bean.local.MonthDetailBean;

public interface MonthDetailView extends BaseView<MonthDetailBean>{

    /**
     * 本地当月账单
     * @param list
     */
    void loadDataSuccess(MonthDetailBean list);
    /**
     * 删除成功
     * @param tData
     */
    void loadDataSuccess(BaseBean tData);
}
