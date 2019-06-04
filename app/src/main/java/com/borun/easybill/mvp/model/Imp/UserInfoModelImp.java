package com.borun.easybill.mvp.model.Imp;

import com.borun.easybill.api.RetrofitFactory;
import com.borun.easybill.base.BaseObserver;
import com.borun.easybill.model.bean.remote.UserBean;
import com.borun.easybill.mvp.model.UserInfoModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserInfoModelImp implements UserInfoModel {

    public static int UPDATE_TYPE_GENDER=1;
    public static int UPDATE_TYPE_PHONE=2;
    public static int UPDATE_TYPE_EMAIL=3;

    private UserInfoOnListener listener;

    public UserInfoModelImp(UserInfoOnListener listener) {
        this.listener = listener;
    }

    @Override
    public void update(String id, String username, String gengder, String phone, String mail) {
        RetrofitFactory.getInstence().API()
                .updateUser(id, username, gengder, phone, mail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<UserBean>() {
                    @Override
                    protected void onSuccess(UserBean userBean) throws Exception {
                        listener.onSuccess(userBean);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e);
                    }
                });
    }

    @Override
    public void onUnsubscribe() {

    }

    /**
     * 回调接口
     */
    public interface UserInfoOnListener {

        void onSuccess(UserBean user);

        void onFailure(Throwable e);
    }
}
