package com.example.base;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.commonlib.R;
import com.example.uilibrary.dialog.CustomProgressDialog;
import com.example.utils.StatusBarUtil;

import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;


/**
 * 作者：liujingchang
 * 时间：2017/10/16
 */
public abstract class BaseActivity extends FragmentActivity {
    CustomProgressDialog customDialog;
    public PtrClassicFrameLayout mPtrFrameLayout;
    public boolean isFirst = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFull();
        if (isFirst) {
            if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
                finish();
                return;
            }
        }
        setContentView(getViewId());
        ButterKnife.bind(this);
        getData();
        initView();
        setControl();
        setStatus();
    }

    public void setFull() {
    }

    protected abstract void initView();

    protected abstract int getViewId();


    /**
     * 设置状态栏
     */
    public void setStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.setColor(this, ContextCompat.getColor(getApplicationContext(), R.color.colorWhite), 0);
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    /***
     * 获取信息
     */
    protected abstract void getData();

    /***
     * 设置点击事件
     */
    protected abstract void setControl();

    /***
     * 设置后退按钮
     */
    public void setLeftIcon() {
        findViewById(R.id.title_left_icon).setVisibility(View.VISIBLE);
        findViewById(R.id.title_left_icon).setOnClickListener(v -> finish());
    }

    public void setTitle(String title) {
        TextView tvTitle = (TextView) findViewById(R.id.title_text);
        tvTitle.setText(title);
    }

    /***
     * 设置右侧按钮
     * @param rightIconId
     * @param rightClick
     */
    public void setRightIcon(int rightIconId, View.OnClickListener rightClick) {
        ImageView imageView = (ImageView) findViewById(R.id.titleRightIcon);
        imageView.setImageResource(rightIconId);
        imageView.setOnClickListener(rightClick);
    }

    /***
     * 设置右侧第一个按钮
     * @param rightIconId
     * @param rightClick
     */
    public void setRightIcon1(int rightIconId, View.OnClickListener rightClick) {
        ImageView imageView = (ImageView) findViewById(R.id.titleRightIcon1);
        imageView.setImageResource(rightIconId);
        imageView.setOnClickListener(rightClick);
    }

    public void setRightText(String rightText, View.OnClickListener rightClick) {
        TextView titleRightText = (TextView) findViewById(R.id.titleRightText);
        titleRightText.setVisibility(View.VISIBLE);
        titleRightText.setText(rightText);
        titleRightText.setOnClickListener(rightClick);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v, v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    public void hideKeyboard(View v, IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    public void showKeyboard(View v, IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.SHOW_FORCED);
        }
    }

    public void showProgressBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (customDialog == null) {
                    customDialog = CustomProgressDialog.createDialog(BaseActivity.this, "");
                }
                customDialog.show();
            }
        });
    }

    public void showProgressBar(final String title) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (customDialog == null) {
                    customDialog = CustomProgressDialog.createDialog(BaseActivity.this, title);
                    customDialog.setCancelable(false);
                }
                customDialog.show();
            }
        });
    }

    public void hideProgressBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (customDialog != null && customDialog.isShowing()) {
                    customDialog.dismiss();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void setPullHead() {
        mPtrFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.material_style_ptr_frame);
        MaterialHeader materialHeader = new MaterialHeader(this);
        materialHeader.setColorSchemeColors(new int[]{Color.RED, Color.GREEN, Color.BLUE});
        mPtrFrameLayout.setHeaderView(materialHeader);
        mPtrFrameLayout.addPtrUIHandler(materialHeader);
        mPtrFrameLayout.setLoadingMinTime(1000);
        mPtrFrameLayout.setDurationToCloseHeader(1500);
        //左右滑动时刷新控件禁止掉
        mPtrFrameLayout.disableWhenHorizontalMove(true);
        mPtrFrameLayout.autoRefresh(false);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reFresh();
                        frame.refreshComplete();
                    }
                }, 1000);
            }
        });
    }

    public void reFresh() {
    }
}
