package com.borun.easybill.mvp.presenter;

import com.borun.easybill.base.BasePresenter;

public abstract  class UserInfoPresenter extends BasePresenter {

    public abstract void update(String id, String username, String gengder, String phone, String mail);
}
