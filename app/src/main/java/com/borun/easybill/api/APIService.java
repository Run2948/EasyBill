package com.borun.easybill.api;

import com.borun.easybill.common.Constants;
import com.borun.easybill.common.HttpConfig;
import com.borun.easybill.model.bean.BaseBean;
import com.borun.easybill.model.bean.local.BBill;
import com.borun.easybill.model.bean.local.BPay;
import com.borun.easybill.model.bean.local.BSort;
import com.borun.easybill.model.bean.local.NoteBean;
import com.borun.easybill.model.bean.remote.BPayBean;
import com.borun.easybill.model.bean.remote.BSortBean;
import com.borun.easybill.model.bean.remote.MonthAccountBean;
import com.borun.easybill.model.bean.remote.MonthChartBean;
import com.borun.easybill.model.bean.remote.MonthDetailBean;
import com.borun.easybill.model.bean.remote.UserBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Qing on 2018/1/19.
 */

public interface APIService {

    /**
     * 用户登陆
     *
     * @param username
     * @param password
     * @return
     */
    @GET(HttpConfig.USER_LOGIN)
    Observable<UserBean> login(@Query("username") String username, @Query("password") String password);

    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @param mail
     * @return
     */
    @GET(HttpConfig.USER_SIGN)
    Observable<UserBean> signup(@Query("username") String username, @Query("password") String password
            , @Query("mail") String mail);

    /**
     * 修改账号信息
     *
     * @param id
     * @param username
     * @param gender
     * @param phone
     * @param mail
     * @return
     */
    @GET(HttpConfig.USER_UPDATE)
    Observable<UserBean> updateUser(@Query("id") String id, @Query("username") String username
            , @Query("gender") String gender, @Query("phone") String phone, @Query("mail") String mail);

    /**
     * 每月账单详情
     *
     * @param id
     * @param year
     * @param month
     * @return
     */
    @GET(HttpConfig.BILL_MONTH_DETIAL)
    Observable<MonthDetailBean> getMonthDetial(@Path("id") String id, @Path("year") String year
            , @Path("month") String month);

    /**
     * 每月账单分类
     *
     * @param id
     * @param year
     * @param month
     * @return
     */
    @GET(HttpConfig.BILL_MONTH_CHART)
    Observable<MonthChartBean> getMonthChart(@Path("id") String id, @Path("year") String year
            , @Path("month") String month);

    /**
     * 每月账户统计
     *
     * @param id
     * @param year
     * @param month
     * @return
     */
    @GET(HttpConfig.BILL_MONTH_CARD)
    Observable<MonthAccountBean> getMonthAccount(@Path("id") String id, @Path("year") String year
            , @Path("month") String month);

    /**
     * 添加账单
     *
     * @param userid
     * @param sortid
     * @param payid
     * @param cost
     * @param crdate
     * @param content
     * @param income
     * @return
     */
    @GET(HttpConfig.BILL_ADD)
    Observable<BaseBean> addBill(@Query("userid") String userid, @Query("sortid") String sortid
            , @Query("payid") String payid, @Query("cost") String cost
            , @Query("content") String content, @Query("crdate") String crdate, @Query("income") boolean income);

    /**
     * 修改账单
     *
     * @param id
     * @param userid
     * @param sortid
     * @param payid
     * @param cost
     * @param content
     * @param crdate
     * @param income
     * @return
     */
    @GET(HttpConfig.BILL_UPDATE)
    Observable<BaseBean> updateBill(@Query("id") String id, @Query("userid") String userid, @Query("sortid") String sortid
            , @Query("payid") String payid, @Query("cost") String cost
            , @Query("content") String content, @Query("crdate") String crdate, @Query("income") boolean income);

    /**
     * 删除账单
     *
     * @param id
     * @return
     */
    @GET(HttpConfig.BILL_DELETE)
    Observable<BaseBean> deleteBill(@Path("id") String id);


    /**
     * 获取用户分类和支付方式
     *
     * @param id
     * @return
     */
    @GET(HttpConfig.NOTE_USER)
    Observable<NoteBean> getNote(@Path("id") String id);

    /**
     * 添加账单分类
     *
     * @param uid
     * @param sortName
     * @param sortImg
     * @param income
     * @return
     */
    @GET(HttpConfig.NOTE_SORT_ADD)
    Observable<BSortBean> addSort(@Query("uid") String uid, @Query("sortName") String sortName
            , @Query("sortImg") String sortImg, @Query("income") Boolean income);

    /**
     * 添加账单支付方式
     *
     * @param uid
     * @param sortName
     * @param sortImg
     * @param payNum
     * @return
     */
    @GET(HttpConfig.NOTE_PAY_ADD)
    Observable<BPayBean> addPay(@Query("uid") String uid, @Query("payName") String sortName
            , @Query("payImg") String sortImg, @Query("payNum") String payNum);


    /**
     * 同步支付方式
     *
     * @param pays
     * @return
     */
    //@body即非表单请求体，被@Body注解的ask将会被Gson转换成json发送到服务器，返回到Take。 其中返回类型为Call<*>，*是接收数据的类
    @POST(Constants.NOTE_PAY_SYNC)
    Observable<NoteBean> syncPay(@Query("uid") String uid,@Body List<BPay> pays);

    /**
     * 同步账单分类
     *
     * @param sorts
     * @return
     */
    @POST(Constants.NOTE_SORT_SYNC)
    Observable<NoteBean> syncSort(@Query("uid") String uid,@Body List<BSort> sorts);

    /**
     * 同步账单
     *
     * @param bills
     * @return
     */
    @POST(Constants.BILL_SYNC)
    Observable<List<BBill>> syncBill(@Query("uid") String uid,@Body List<BBill> bills);
}
