package com.borun.easybill.custom;

import android.support.annotation.NonNull;

import com.borun.easybill.utils.OkHttpUtils;
import com.xuexiang.xupdate.proxy.IUpdateHttpService;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 使用OKHttp自定义更新服务
 */
public class OKHttpUpdateHttpService implements IUpdateHttpService {
    @Override
    public void asyncGet(@NonNull String url, @NonNull Map<String, Object> params, @NonNull final Callback callBack) {
        OkHttpUtils.getInstance().get(url, params, new okhttp3.Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccess(response.body().string());
            }
        });
    }

    @Override
    public void asyncPost(@NonNull String url, @NonNull Map<String, Object> params, @NonNull final Callback callBack) {
        OkHttpUtils.getInstance().post(url, params, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccess(response.body().string());
            }
        });
    }

    @Override
    public void download(@NonNull String url, @NonNull final String path, @NonNull final String fileName, @NonNull final DownloadCallback callback) {
        OkHttpUtils.getInstance().download(url, path, fileName, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                File file = OkHttpUtils.getInstance().saveFile(response, path, fileName);
                callback.onSuccess(file);
            }
        });
    }

    @Override
    public void cancelDownload(@NonNull String url) {

    }
}
