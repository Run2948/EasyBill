package com.borun.easybill.mvp.presenter.Imp;

import com.borun.easybill.model.bean.remote.UserBean;
import com.borun.easybill.mvp.model.Imp.UserInfoModelImp;
import com.borun.easybill.mvp.model.UserInfoModel;
import com.borun.easybill.mvp.presenter.UserInfoPresenter;
import com.borun.easybill.mvp.view.UserInfoView;

public class UserInfoPresenterImp extends UserInfoPresenter implements UserInfoModelImp.UserInfoOnListener {

    private UserInfoModel model;
    private UserInfoView view;

    public UserInfoPresenterImp(UserInfoView view) {
        this.model=new UserInfoModelImp(this);
        this.view = view;
    }

    @Override
    public void onSuccess(UserBean user) {
        view.loadDataSuccess(user);
    }

    @Override
    public void onFailure(Throwable e) {
        view.loadDataError(e);
    }

    @Override
    public void update(String id, String username, String gengder, String phone, String mail) {
        model.update(id,username,gengder,phone,mail);
    }
}
