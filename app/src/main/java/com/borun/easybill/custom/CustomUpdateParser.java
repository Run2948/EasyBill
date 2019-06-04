package com.borun.easybill.custom;

import com.borun.easybill.common.Constants;
import com.borun.easybill.model.bean.remote.VersionBean;
import com.borun.easybill.utils.GsonUtils;
import com.xuexiang.xupdate.entity.UpdateEntity;
import com.xuexiang.xupdate.proxy.IUpdateParser;

/**
 * 自定义更新解析器
 */
public class CustomUpdateParser implements IUpdateParser {
    @Override
    public UpdateEntity parseJson(String json) throws Exception {
        VersionBean result = GsonUtils.fromJson(json, VersionBean.class);
        if (result != null) {
            return new UpdateEntity()
                    .setHasUpdate(result.hasUpdate)
                    .setIsIgnorable(false)
                    .setForce(result.updateStatus == 2)
                    .setVersionCode(result.versionCode)
                    .setVersionName(result.versionName)
                    .setUpdateContent(result.upgradeInfo)
                    .setDownloadUrl(Constants.BASE_URL + result.downloadUrl)
                    .setSize(result.apkSize)
                    .setMd5(result.apkMd5);
        }
        return null;
    }
}
