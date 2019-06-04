package com.borun.easybill.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.borun.easybill.R;
import com.borun.easybill.api.RetrofitFactory;
import com.borun.easybill.base.BaseObserver;
import com.borun.easybill.common.Constants;
import com.borun.easybill.custom.CustomUpdateParser;
import com.borun.easybill.model.bean.local.BBill;
import com.borun.easybill.model.bean.local.BPay;
import com.borun.easybill.model.bean.local.BSort;
import com.borun.easybill.model.bean.local.NoteBean;
import com.borun.easybill.model.repository.LocalRepository;
import com.borun.easybill.ui.adapter.MainFragmentPagerAdapter;
import com.borun.easybill.ui.fragment.MonthAccountFragment;
import com.borun.easybill.ui.fragment.MonthChartFragment;
import com.borun.easybill.ui.fragment.MonthDetailFragment;
import com.borun.easybill.utils.ImageUtils;
import com.borun.easybill.utils.OkHttpUtils;
import com.borun.easybill.utils.ProgressUtils;
import com.borun.easybill.utils.SharedPUtils;
import com.borun.easybill.utils.ThemeManager;
import com.borun.easybill.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.xuexiang.xupdate.XUpdate;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected static final int USERINFOACTIVITY_CODE = 0;
    protected static final int LOGINACTIVITY_CODE = 1;
    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.main_viewpager)
    ViewPager viewPager;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    private NoteBean noteBean;
    private View drawerHeader;
    private ImageView drawerIv;
    private TextView drawerTvAccount, drawerTvMail;
    // Tab
    private FragmentManager mFragmentManager;
    private MainFragmentPagerAdapter mainFragmentPagerAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {

        //第一次进入将检查更新
        initUpdate();

        //第一次进入将默认账单分类添加到数据库
        if (SharedPUtils.isFirstStart(mContext)) {
            OkHttpUtils.getInstance().get(mContext, Constants.BASE_URL + Constants.NOTE_DEFAULT, null, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    ProgressUtils.show(mContext, "服务器异常，初始化数据失败...");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json = response.body().string();
                    Log.i(TAG, "第一次进入将默认账单分类添加到数据库:" + json);
//                    noteBean = new Gson().fromJson(Constants.BILL_NOTE, NoteBean.class);
                    noteBean = new Gson().fromJson(json, NoteBean.class);
                    List<BSort> sorts = noteBean.getOutSortlis();
                    sorts.addAll(noteBean.getInSortlis());
                    LocalRepository.getInstance().saveBsorts(sorts);
                    LocalRepository.getInstance().saveBPays(noteBean.getPayinfo());
                }
            });
        } else {
            noteBean = SharedPUtils.getUserNoteBean(mContext);
        }

        //初始化ViewPager
        mFragmentManager = getSupportFragmentManager();
        mainFragmentPagerAdapter = new MainFragmentPagerAdapter(mFragmentManager);
        mainFragmentPagerAdapter.addFragment(new MonthDetailFragment(), "明细");
        mainFragmentPagerAdapter.addFragment(new MonthChartFragment(), "报表");
        mainFragmentPagerAdapter.addFragment(new MonthAccountFragment(), "卡片");

        viewPager.setAdapter(mainFragmentPagerAdapter);

        //初始化TabLayout
        tabLayout.addTab(tabLayout.newTab().setText("明细"));
        tabLayout.addTab(tabLayout.newTab().setText("报表"));
        tabLayout.addTab(tabLayout.newTab().setText("卡片"));
        tabLayout.setupWithViewPager(viewPager);

        //初始化Toolbar
        toolbar.setTitle("简易记账");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        drawerHeader = navigationView.inflateHeaderView(R.layout.drawer_header);
        drawerIv = (ImageView) drawerHeader.findViewById(R.id.drawer_iv);
        drawerTvAccount = (TextView) drawerHeader.findViewById(R.id.drawer_tv_name);
        drawerTvMail = (TextView) drawerHeader.findViewById(R.id.drawer_tv_mail);
        //监听点击登陆事件
        drawerHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                if (currentUser == null) {
                    //用户id为null表示没有用户登陆
                    startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), LOGINACTIVITY_CODE);
                } else {
                    //进入账户中心
                    startActivityForResult(new Intent(MainActivity.this, UserInfoActivity.class), USERINFOACTIVITY_CODE);
                }
            }
        });
        //设置头部账户
        setDrawerHeaderAccount();
        //监听侧滑菜单
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * 设置DrawerHeader的用户信息
     */
    public void setDrawerHeaderAccount() {
        //获取当前用户
        currentUser = SharedPUtils.getCurrentUser(this);
        if (currentUser != null) {
            drawerTvAccount.setText(currentUser.getUsername());
            drawerTvMail.setText(currentUser.getMail());
            if (!TextUtils.isEmpty(currentUser.getImage())) {
                String imgPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + currentUser.getImage();
                File file = new File(imgPath);
                //判断头像文件是否存在
                if (file.exists()) {
                    //加载本地图片
                    Glide.with(this).load(file).into(drawerIv);
                } else {
                    //加载网络图片到本地
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Bitmap bitmap = null;
                            try {
                                bitmap = Glide.with(MainActivity.this)
                                        .load(Constants.BASE_URL + Constants.IMAGE_USER + currentUser.getImage())
                                        .asBitmap()
                                        .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                        .get();
                                if (bitmap != null) {
                                    drawerIv.setImageBitmap(bitmap);
                                    Log.i(TAG, "SD可写：" + Environment.getExternalStorageDirectory().canWrite() +
                                            "SD可读：" + Environment.getExternalStorageDirectory().canRead());
                                    String imgPath = ImageUtils.savePhoto(bitmap, Environment
                                            .getExternalStorageDirectory().getAbsolutePath(), currentUser.getImage());

                                    Log.i(TAG, imgPath);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
            Constants.currentUserId = currentUser.getId();
        } else {
            drawerTvAccount.setText("账号");
            drawerTvMail.setText("点我登陆");
            drawerIv.setImageResource(R.mipmap.ic_def_icon);
            Constants.currentUserId = null;
        }
    }

    /**
     * 显示修改主题色 Dialog
     */
    private void showUpdateThemeDialog() {
        final String[] themes = ThemeManager.getInstance().getThemes();
        new AlertDialog.Builder(mContext)
                .setTitle("选择主题")
                .setItems(themes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ThemeManager.getInstance().setTheme(mContext, themes[which]);
                    }
                }).create().show();
    }

    /**
     * 监听Drawer
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 监听点击事件 R.id.drawer_tv_name,R.id.drawer_tv_mail
     *
     * @param view
     */
    @OnClick({})
    public void onClick(View view) {
        switch (view.getId()) {

            default:
                break;
        }
    }

    /**
     * 监听Activity返回值
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case USERINFOACTIVITY_CODE:
                    setDrawerHeaderAccount();
                    break;
                case LOGINACTIVITY_CODE:
                    setDrawerHeaderAccount();
                    break;
            }
        }
    }

    /**
     * 监听侧滑菜单事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_account) {          //账户
            if (currentUser == null) {
                //用户id为0表示未有用户登陆
                startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), LOGINACTIVITY_CODE);
            } else {
                new AlertDialog.Builder(mContext).setTitle("确定切换账户，这将注销当前账户信息")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //清除登陆信息
                                SharedPreferences sp = getSharedPreferences(SharedPUtils.USER_INFOR, Context.MODE_PRIVATE);
                                if (sp != null)
                                    sp.edit().clear().commit();
                                //刷新账户数据
                                setDrawerHeaderAccount();
                            }
                        }).show();
            }
        } else if (id == R.id.nav_month) {     // 月报
            viewPager.setCurrentItem(1);
        } else if (id == R.id.nav_total) {     // 汇总
            viewPager.setCurrentItem(2);
        } else if (id == R.id.nav_sync) {      // 同步
            viewPager.setCurrentItem(0);
            syncUserNote();
        } else if (id == R.id.nav_setting) {   //设置
            startActivity(new Intent(this, SettingActivity.class));
        } else if (id == R.id.nav_about) {     //关于
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        } else if (id == R.id.nav_theme) {     //主题
            showUpdateThemeDialog();
        } else if (id == R.id.nav_exit) {      //退出
            new AlertDialog.Builder(mContext).setTitle("确定关闭并退出应用")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            android.os.Process.killProcess(android.os.Process.myPid());    //获取PID
                            System.exit(0);
                        }
                    }).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // 云同步
    private void syncUserNote() {
        syncPay();
        syncSort();
        syncBill();
    }

    // 同步支付方式
    private void syncPay() {
        ToastUtils.show(mContext, "正在同步支付方式...");
        List<BPay> list = noteBean.getPayinfo();
        Iterator<BPay> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (Constants.defaultUserId.equals(iterator.next().getUid())) {
                iterator.remove();
            }
        }
        RetrofitFactory.getInstence().API()
                .syncPay(currentUser.getId(), list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<NoteBean>() {
                    @Override
                    protected void onSuccess(NoteBean note) throws Exception {
                        Log.i(TAG, "onSuccess: " + note.getPayinfo().size());
                        noteBean.setPayinfo(note.getPayinfo());
                        SharedPUtils.setUserNoteBean(mContext, noteBean);
//                        ToastUtils.show(mContext, "支付方式同步完毕！");
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                    }
                });
        ToastUtils.show(mContext, "支付方式同步完毕！");
    }

    // 同步账单分类
    private void syncSort() {
        ToastUtils.show(mContext, "正在同步账单分类...");
        List<BSort> list = noteBean.getOutSortlis();
        list.addAll(noteBean.getInSortlis());
        Iterator<BSort> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (Constants.defaultUserId.equals(iterator.next().getUid())) {
                iterator.remove();
            }
        }
        RetrofitFactory.getInstence().API()
                .syncSort(currentUser.getId(), list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<NoteBean>() {
                    @Override
                    protected void onSuccess(NoteBean note) throws Exception {
                        Log.i(TAG, "onSuccess: " + note.getInSortlis().size());
                        Log.i(TAG, "onSuccess: " + note.getOutSortlis().size());
                        noteBean.setInSortlis(note.getInSortlis());
                        noteBean.setOutSortlis(note.getOutSortlis());
                        SharedPUtils.setUserNoteBean(mContext, noteBean);
//                        ToastUtils.show(mContext, "账单分类同步完毕！");
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                    }
                });
        ToastUtils.show(mContext, "账单分类同步完毕！");
    }

    // 同步账单
    private void syncBill() {
        ToastUtils.show(mContext, "正在同步本地账单...");
        List<BBill> bills = LocalRepository.getInstance().getBBills();
        Iterator<BBill> iterator = bills.iterator();
        while (iterator.hasNext()) {
            if (Constants.defaultUserId.equals(iterator.next().getUserid())) {
                iterator.remove();
            }
        }
        RetrofitFactory.getInstence().API()
                .syncBill(currentUser.getId(), bills)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<BBill>>() {
                    @Override
                    protected void onSuccess(List<BBill> billList) throws Exception {
                        Log.i(TAG, "onSuccess: " + billList.size());
                        LocalRepository.getInstance().saveBBills(billList);
//                        ToastUtils.show(mContext, "账单同步完毕！");
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                    }
                });
        ToastUtils.show(mContext, "账单同步完毕！");
    }

    // 初始化更新事件
    private void initUpdate() {
        XUpdate.newBuild(this)
                .updateUrl(Constants.BASE_URL + Constants.CHECK_VERSION_URL)
                .updateParser(new CustomUpdateParser())
                .update();
    }
}
