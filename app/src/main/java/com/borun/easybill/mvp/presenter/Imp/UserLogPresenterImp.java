package com.borun.easybill.mvp.presenter.Imp;

import com.borun.easybill.model.bean.remote.UserBean;
import com.borun.easybill.mvp.model.Imp.UserLogModelImp;
import com.borun.easybill.mvp.model.UserLogModel;
import com.borun.easybill.mvp.presenter.UserLogPresenter;
import com.borun.easybill.mvp.view.UserLogView;

public class UserLogPresenterImp extends UserLogPresenter implements UserLogModelImp.UserLogOnListener{

    private UserLogModel userLogModel;
    private UserLogView userLogView;

    public UserLogPresenterImp(UserLogView userLogView) {
        this.userLogModel=new UserLogModelImp(this);
        this.userLogView = userLogView;
    }

    @Override
    public void onSuccess(UserBean user) {
        userLogView.loadDataSuccess(user);
    }


    @Override
    public void onFailure(Throwable e) {
        userLogView.loadDataError(e);
    }

    @Override
    public void login(String username,String password) {
        userLogModel.login(username,password);
    }

    @Override
    public void signup(String username,String password,String mail) {
        userLogModel.signup(username,password,mail);
    }
}
