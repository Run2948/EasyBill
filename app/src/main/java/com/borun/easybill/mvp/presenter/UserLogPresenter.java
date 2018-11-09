package com.borun.easybill.mvp.presenter;

import com.borun.easybill.base.BasePresenter;

public abstract  class UserLogPresenter extends BasePresenter {

    public abstract void login(String username,String password);

    public abstract void signup(String username,String password,String mail);
}
