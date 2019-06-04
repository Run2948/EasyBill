package com.borun.easybill.mvp.model.Imp;

import com.borun.easybill.base.BaseObserver;
import com.borun.easybill.model.bean.local.BBill;
import com.borun.easybill.model.bean.BaseBean;
import com.borun.easybill.model.bean.local.MonthDetailBean;
import com.borun.easybill.model.repository.LocalRepository;
import com.borun.easybill.mvp.model.MonthDetailModel;
import com.borun.easybill.utils.BillUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

public class MonthDetailModelImp implements MonthDetailModel {

    private MonthDetailOnListener listener;

    public MonthDetailModelImp(MonthDetailOnListener listener) {
        this.listener = listener;
    }


    @Override
    public void getMonthDetailBills(String id, String year, String month) {
        LocalRepository.getInstance().getBBillByUserIdWithYM(id, year, month)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<BBill>>() {
                    @Override
                    protected void onSuccess(List<BBill> bBills) throws Exception {
                        listener.onSuccess(BillUtils.packageDetailList(bBills));
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e);
                    }
                });
    }


    @Override
    public void delete(String id) {
        LocalRepository.getInstance().deleteBBillById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Long>() {
                    @Override
                    protected void onSuccess(Long l) throws Exception {
                        listener.onSuccess(new BaseBean());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e);
                    }
                });
    }

    /**
     * 伪删除操作
     * @param bBill
     */
    @Override
    public void update(BBill bBill) {
        LocalRepository.getInstance()
                .updateBBill(bBill)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BBill>() {
                    @Override
                    protected void onSuccess(BBill bBill) throws Exception {
                        listener.onSuccess(new BaseBean());
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
    public interface MonthDetailOnListener {

        void onSuccess(MonthDetailBean bean);

        void onSuccess(BaseBean bean);

        void onFailure(Throwable e);
    }
}
