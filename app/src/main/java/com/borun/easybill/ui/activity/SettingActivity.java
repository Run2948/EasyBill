package com.borun.easybill.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.borun.easybill.R;
import com.borun.easybill.common.Constants;
import com.borun.easybill.model.repository.LocalRepository;
import com.borun.easybill.utils.ExcelUtils;
import com.borun.easybill.utils.GlideCacheUtil;
import com.borun.easybill.utils.OkHttpUtils;
import com.borun.easybill.utils.ProgressUtils;
import com.borun.easybill.utils.SharedPUtils;
import com.borun.easybill.utils.SnackbarUtils;
import com.borun.easybill.utils.ToastUtils;
import com.borun.easybill.widget.CommonItemLayout;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Qing on 2018/10/30.
 */
public class SettingActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.cil_change)
    CommonItemLayout changeCL;
    @BindView(R.id.cil_forget)
    CommonItemLayout forgetCL;
    @BindView(R.id.cil_store)
    CommonItemLayout storeCL;
    @BindView(R.id.cil_sort)
    CommonItemLayout sortCL;
    @BindView(R.id.cil_pay)
    CommonItemLayout payCL;
    @BindView(R.id.cil_export)
    CommonItemLayout exportCL;

    private AlertDialog pwDialog;
    private AlertDialog cacheDialog;

    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initEventAndData() {

        //初始化Toolbar
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回消息更新上个Activity数据
                //setResult(RESULT_OK, new Intent());
                finish();
            }
        });

        storeCL.setRightText(GlideCacheUtil.getInstance().getCacheSize(mContext));
    }


    /**
     * 监听点击事件
     *
     * @param view
     */
    @OnClick({R.id.cil_forget, R.id.cil_change, R.id.cil_store,
            R.id.cil_sort, R.id.cil_pay, R.id.cil_export})
    public void onViewClicked(final View view) {
        switch (view.getId()) {
            case R.id.cil_change:  //修改密码
                showChangeDialog();
                break;
            case R.id.cil_forget:  //忘记密码
                showForgetPwDialog();
                break;
            case R.id.cil_store:  //缓存
                showCacheDialog();
                break;
            case R.id.cil_sort:  //账单分类管理
                startActivity(new Intent(this, SortEditActivity.class));
                break;
            case R.id.cil_pay:  //支付方式管理
                startActivity(new Intent(this, PayEditActivity.class));
                break;
            case R.id.cil_export:
                String filename = Environment.getExternalStorageDirectory().getAbsolutePath() + "/cocoBill.xls";
                if (ExcelUtils.export(LocalRepository.getInstance().getBBills(), filename))
                    SnackbarUtils.show(this, "导出成功,文件目录：" + filename);
                else
                    SnackbarUtils.show(this, "导出失败");
                break;
            default:
                break;
        }
    }

    /**
     * 显示修改密码对话框
     */
    public void showChangeDialog() {
        final LinearLayout layout = new LinearLayout(mContext);
        //加载布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_change_password, layout, false);
        TextInputLayout til = (TextInputLayout) view.findViewById(R.id.change_til_password);
        TextInputLayout til1 = (TextInputLayout) view.findViewById(R.id.change_til_repassword);
        final EditText editText = til.getEditText();
        final EditText editText1 = til1.getEditText();
        //加入视图
        layout.addView(view);

        if (pwDialog == null) {
            pwDialog = new AlertDialog.Builder(this)
                    .setTitle("修改密码")
                    .setView(layout)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            String input = editText.getText().toString();
                            String input1 = editText1.getText().toString();
                            if (input.equals("") || input1.equals("")) {
                                Toast.makeText(getApplicationContext(), "不能为空！" + input,
                                        Toast.LENGTH_SHORT).show();
                            } else if (input.equals(input1)) {
                                //修改密码
                                changePw();
                            } else {
                                Toast.makeText(mContext,
                                        "两次输入不一致", Toast.LENGTH_LONG).show();
                            }
                        }
                    })
                    .setNegativeButton("取消", null)
                    .create();
        }
        if (!pwDialog.isShowing()) {
            pwDialog.show();
        }
    }

    /**
     * 显示忘记密码对话框
     */
    public void showForgetPwDialog() {
        final EditText editText = new EditText(mContext);
        editText.setHint("请输入注册邮箱");
        //弹出输入框
        new AlertDialog.Builder(this)
                .setTitle("忘记密码")
                .setView(editText)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String input = editText.getText().toString();
                        if (input.equals("")) {
                            SnackbarUtils.show(mContext, "内容不能为空！");
                        } else {
                            //找回密码
                            ToastUtils.show(mContext, "重置密码请求成功，请到邮箱进行密码重置操作");
                        }
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    /**
     * 显示清除缓存对话框
     */
    public void showCacheDialog() {

        if (cacheDialog == null) {
            cacheDialog = new AlertDialog.Builder(this)
                    .setTitle("清除缓存")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            GlideCacheUtil.getInstance().clearImageAllCache(mContext);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        // 重新刷新缓存
                                        storeCL.setRightText(GlideCacheUtil.getInstance().getCacheSize(mContext));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .create();
        }
        if (!cacheDialog.isShowing()) {
            cacheDialog.show();
        }
    }

    /**
     * 更新用户密码
     */
    public void changePw() {
        if (currentUser == null)
            return;

        ProgressUtils.show(mContext, "正在修改...");
        Map<String, String> params = new HashMap<>();
        params.put("username", currentUser.getUsername());
        params.put("password", currentUser.getPassword());
        params.put("code", "000000");
        OkHttpUtils.getInstance().get(Constants.BASE_URL + Constants.USER_CHANGEPW, params,
                new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        ProgressUtils.dismiss();
                        Toast.makeText(mContext, "修改失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        ProgressUtils.dismiss();
                        SharedPUtils.setCurrentUser(mContext, currentUser);
                    }
                });
    }

}
