package com.example.me.activity;

import android.webkit.WebView;

import com.example.base.BaseActivity;
import com.example.me.R;
import com.example.me.R2;
import com.example.me.api.Common;
import com.example.utils.WebViewSettingUtils;

import butterknife.BindView;

public class WebViewActivity extends BaseActivity {
    @BindView(R2.id.about_me_wv)
    WebView about_me_wv;

    @Override
    protected void initView() {
        setLeftIcon();
        setTitle("关于我们");
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void setControl() {
        WebViewSettingUtils.setWebSetting(about_me_wv, WebViewActivity.this);
        about_me_wv.loadUrl(Common.BaseUrl + "/html/about.html");
    }

}
