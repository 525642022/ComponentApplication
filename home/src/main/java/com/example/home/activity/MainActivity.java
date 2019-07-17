package com.example.home.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base.BaseActivity;
import com.example.base.BaseFragment;
import com.example.bean.MyUserBean;
import com.example.bean.UserBean;
import com.example.constants.ARouterConfig;
import com.example.home.R;
import com.example.home.R2;
import com.example.home.api.ApiManager;
import com.example.home.bean.CheckBean;
import com.example.home.bean.TabEntity;
import com.example.home.fragment.HomeFragment;
import com.example.net.callback.CallBackObserver;
import com.example.uilibrary.dialog.CancelDialog;
import com.example.utils.ARouterUtils;
import com.example.utils.FastClickUtils;
import com.example.utils.SharedPrefUtil;
import com.example.utils.utilcode.util.AppUtils;
import com.example.utils.utilcode.util.LogUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = ARouterConfig.ZXXQ_MAIN_ACTIVITY)
public class MainActivity extends BaseActivity {
    @BindView(R2.id.home_foot_tl)
    CommonTabLayout home_foot_tl;
    public boolean isUpdate = false;
    private String[] mTitles = {"首页", "答题测试", "积分商城", "个人中心"};
    private int[] mIconUnSelectIds = {
            R.mipmap.home_shouye_moren, R.mipmap.home_dati,
            R.mipmap.home_jifen_moren, R.mipmap.home_geren_moren};
    private int[] mIconSelectIds = {
            R.mipmap.home_shouye_xuanzhong, R.mipmap.home_dati_xuanzhong,
            R.mipmap.home_jifen_xuanzhong, R.mipmap.home_geren_xuanzhong};
    private BaseFragment mHomeFragment = HomeFragment.getInstance();

    private BaseFragment mAnswerFragment = ARouterUtils.getFragment(ARouterConfig.ZXXQ_ANSWER_FRAGMENT);
    private BaseFragment mShopFragment = ARouterUtils.getFragment(ARouterConfig.ZXXQ_SHOP_FRAGMENT);
    private BaseFragment mMineFragment = ARouterUtils.getFragment(ARouterConfig.ZXXQ_MINE_FRAGMENT);

    private String mSavePath;
    private File apkFile;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private FragmentManager mFragmentManager;

    @Override
    protected void initView() {
        setDefaultFragment();
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void getData() {
        ButterKnife.bind(this);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]));
        }
        home_foot_tl.setTabData(mTabEntities);
        ApiManager.checkUpdate(new CallBackObserver<CheckBean>() {
            @Override
            public void onNext(CheckBean data) {
                CheckBean.StatusBean status = data.getStatus();
                if ("0".equals(status.getCode())) {
                    CheckBean.DataBean dataBean = data.getData();
                    if (data != null) {
                        setCancelDialog(dataBean);
                    }
                }
            }
        });
    }

    private void setCancelDialog(CheckBean.DataBean dataBean) {
        isUpdate = false;
        if (dataBean.getVersionCode() > AppUtils.getAppVersionCode()) {
            CancelDialog cancelDialog = new CancelDialog(MainActivity.this);
            cancelDialog.setCancelable(true);
            cancelDialog.setTitleText(dataBean.getTitle());
            cancelDialog.setContentText(dataBean.getInfo());
            if (dataBean.getForceUpdate() == 1) {
                cancelDialog.setLeftBtnText(dataBean.getBtnName1());
                cancelDialog.hasRight(false);
                cancelDialog.setCanceledOnTouchOutside(false);
                cancelDialog.setCancleClick(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        if (!isUpdate) {
                            finish();
                        }
                    }
                });
            } else {
                cancelDialog.setLeftBtnText(dataBean.getBtnName1());
                cancelDialog.setRightBtnText(dataBean.getBtnName2());
            }
            cancelDialog.setLeftBtnClickListener(new CancelDialog.onLeftBtnClcikListener() {
                @Override
                public void onLeftBtnClcik() {
                    if (!FastClickUtils.isNotFastClick()) {
                        return;
                    }
                    isUpdate = true;
                    downloadAPK(dataBean.getLink());
                }
            });
            cancelDialog.show();
        }
    }

    private void downloadAPK(String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        String sdPath = Environment.getExternalStorageDirectory() + "/";
//                      文件保存路径
                        mSavePath = sdPath + "ZZXQ";
                        File dir = new File(mSavePath);
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        // 下载文件
                        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                        conn.connect();
                        InputStream is = conn.getInputStream();
                        int length = conn.getContentLength();
                        apkFile = new File(mSavePath, AppUtils.getAppVersionName() + ".apk");
                        if (apkFile.exists()) {
                            mUpdateProgressHandler.sendEmptyMessage(2);
                            return;
                        }
                        FileOutputStream fos = new FileOutputStream(apkFile);

                        int count = 0;
                        byte[] buffer = new byte[1024];
                        mUpdateProgressHandler.sendEmptyMessage(1);
                        while (true) {
                            int numread = is.read(buffer);
                            count += numread;
                            // 下载完成
                            if (numread < 0) {
                                mUpdateProgressHandler.sendEmptyMessage(2);
                                break;
                            }
                            fos.write(buffer, 0, numread);
                        }
                        fos.close();
                        is.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void setControl() {
        home_foot_tl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchToFragment(position);
            }

            @Override
            public void onTabReselect(int position) {
                switchToFragment(position);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String token = SharedPrefUtil.getToken(MainActivity.this);
        ApiManager.getUserInfo(MainActivity.this, token, new CallBackObserver<MyUserBean>() {
            @Override
            public void onNext(MyUserBean myUserBean) {
                if (myUserBean.getStatus().getCode().equals("0")) {
                    UserBean user = myUserBean.getData().getUser();
                    SharedPrefUtil.setUser(user, MainActivity.this);
                    mMineFragment.setViewData(user);
                } else {
                    Toast.makeText(getApplicationContext(), myUserBean.getStatus().getCninfo(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setDefaultFragment() {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction ts = mFragmentManager.beginTransaction();
        ts.add(R.id.fragment_content, mHomeFragment);
        LogUtils.e("ljc", mAnswerFragment);
        ts.add(R.id.fragment_content, mAnswerFragment);
        ts.add(R.id.fragment_content, mShopFragment);
        ts.add(R.id.fragment_content, mMineFragment);
        ts.commitAllowingStateLoss();
        switchToFragment(0);
    }

    /**
     * 执行切换fragment 的操作
     * 注意：
     * 1. 切换页面的时候，还要调用showBottomBar来保证底部导航栏的显示
     *
     * @param index
     */
    public void switchToFragment(int index) {
        home_foot_tl.setCurrentTab(index);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        switch (index) {
            case 0:
                hideAllExculdeFragments(transaction);
                transaction.show(mHomeFragment);
                break;
            case 1:
                hideAllExculdeFragments(transaction);
                transaction.show(mAnswerFragment);
                break;
            case 2:
                hideAllExculdeFragments(transaction);
                transaction.show(mShopFragment);
                break;
            case 3:
                hideAllExculdeFragments(transaction);
                transaction.show(mMineFragment);
                break;
        }
        transaction.commit();
    }

    /**
     * 隐藏所有的fragment
     */
    private void hideAllExculdeFragments(FragmentTransaction transaction) {
        transaction.hide(mHomeFragment);
        transaction.hide(mAnswerFragment);
        transaction.hide(mShopFragment);
        transaction.hide(mMineFragment);
    }

    /**
     * 接收消息
     */
    private Handler mUpdateProgressHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    showProgressBar("下载中");
                    break;
                case 2:
                    installAPK(apkFile);
            }
        }

        ;
    };

    protected void installAPK(File file) {
        hideProgressBar();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(
                    MainActivity.this
                    , "dun.com.fileprovider"
                    , file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        startActivity(intent);
    }

}
