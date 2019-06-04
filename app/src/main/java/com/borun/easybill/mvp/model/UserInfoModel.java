package com.borun.easybill.mvp.model;

public interface UserInfoModel {

    void update(String id, String username, String gengder, String phone, String mail);

    void onUnsubscribe();
}
