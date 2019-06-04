package com.borun.easybill;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.borun.easybill.custom.OKHttpUpdateHttpService;
import com.borun.easybill.utils.ToastUtils;
import com.xuexiang.xupdate.XUpdate;
import com.xuexiang.xupdate.entity.UpdateError;
import com.xuexiang.xupdate.listener.OnUpdateFailureListener;
import com.xuexiang.xupdate.utils.UpdateUtils;

import static com.xuexiang.xupdate.entity.UpdateError.ERROR.CHECK_NO_NEW_VERSION;

public class MyApplication extends MultiDexApplication {

    public static MyApplication application;
    private static Context context;

    /**
     * 获取上下文
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        context = getApplicationContext();

        // 使用第三方检查更新
        initUpdate();
    }

    private void initUpdate() {
        XUpdate.get()
                .debug(true)
                .isWifiOnly(false)                                              //默认设置只在wifi下检查版本更新
                .isGet(true)                                                    //默认设置使用get请求检查版本
                .isAutoMode(false)                                              //默认设置非自动模式，可根据具体使用配置
                .param("versionCode", UpdateUtils.getVersionCode(this)) //设置默认公共请求参数
                .param("appKey", getPackageName())
                .setOnUpdateFailureListener(new OnUpdateFailureListener() {     //设置版本更新出错的监听
                    @Override
                    public void onFailure(UpdateError error) {
                        if (error.getCode() != CHECK_NO_NEW_VERSION) {          //对不同错误进行处理
                            ToastUtils.show(context, error.toString());
                        }
                    }
                })
                .supportSilentInstall(true)                                     //设置是否支持静默安装，默认是true
                .setIUpdateHttpService(new OKHttpUpdateHttpService())           //这个必须设置！实现网络请求功能。
                .init(this);
    }
}
