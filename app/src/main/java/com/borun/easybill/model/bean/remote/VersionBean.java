package com.borun.easybill.model.bean.remote;

import com.borun.easybill.model.bean.BaseBean;

public class VersionBean extends BaseBean {
    //app名称
    public String appName;
    // 是否有更新
    public Boolean hasUpdate;
    // 文件大小
    public Long apkSize;
    // 文件MD5值
    public String apkMd5;
    // 服务器版本号
    public int versionCode;
    //服务器版本名称
    public String versionName;
    //升级方式
    public int updateStatus;
    //app下载地址
    public String downloadUrl;
    //升级信息
    public String upgradeInfo;
}
