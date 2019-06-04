package com.borun.easybill.common;

public class Constants {

    //网络请求
    public static final String BASE_URL = "http://129.28.189.106:8080";

    public static final String USER_LOGIN = "/api/user/login";
    public static final String USER_SIGN = "/api/user/sign";
    public static final String USER_UPDATE = "/api/user/update";
    public static final String USER_CHANGEPW = "/api/user/changePw";
    public static final String USER_FORGETPW = "/api/user/forgetPw";
    public static final String BILL_MONTH_DETIAL = "/api/bill/user";
    public static final String BILL_MONTH_CHART = "/api/bill/chart";
    public static final String BILL_MONTH_CARD = "/api/bill/pay";
    public static final String BILL_DELETE = "/api/bill/delete";
    public static final String BILL_UPDATE = "/api/bill/update";
    public static final String BILL_ADD = "/api/bill/add";
    public static final String BILL_SYNC = "/api/bill/sync";
    public static final String NOTE_DEFAULT = "/api/note/default";
    public static final String NOTE_USER = "/api/note/user";
    public static final String NOTE_SORT_ADD = "/api/note/sort/add";
    public static final String NOTE_SORT_UPDATE = "/api/note/sort/update";
    public static final String NOTE_SORT_SYNC = "/api/note/sort/sync";
    public static final String NOTE_PAY_ADD = "/api/note/pay/add";
    public static final String NOTE_PAY_UPDATE = "/api/note/pay/update";
    public static final String NOTE_PAY_SYNC = "/api/note/pay/sync";

    public static final String USER_IMAGE_UPLOAD = "/api/file/upload/";
    public static final String CHECK_VERSION_URL = "/api/file/app";

    public static final String IMAGE_USER = "/upload/";
    public static final String IMAGE_SORT = "/upload/noteImg/sort/";
    public static final String IMAGE_PAY = "/upload/noteImg/pay/";

    // 当前用户
    public static String currentUserId = null;
    // 公用用户
    public static String defaultUserId = "00000000a000000000000000";

    public static String EXTRA_IS_UPDATE_THEME = "com.borun.easybill.IS_UPDATE_THEME";

}
