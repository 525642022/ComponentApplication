package com.example.home.activity;

import android.widget.TextView;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.home.R;
import com.example.home.R2;
import com.example.home.api.ApiManager;
import com.example.home.bean.HomeArticleDetailBean;
import com.example.net.callback.CallBackObserver;
import com.example.uilibrary.webview.NoScrollWebView;
import com.example.utils.WebViewSettingUtils;


import butterknife.BindView;

public class NewsDetailActivity extends BaseActivity {

    @BindView(R2.id.new_title)
    TextView new_title;
    @BindView(R2.id.news_host)
    TextView news_host;
    @BindView(R2.id.news_time)
    TextView news_time;
    @BindView(R2.id.webView)
    NoScrollWebView webView;
    private String id;

    @Override
    protected void initView() {
        setLeftIcon();
        setTitle("资讯星球");
        WebViewSettingUtils.setWebSetting(webView, this);
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void getData() {
        id = getIntent().getStringExtra("id");
        ApiManager.getArticleDetail(id, new CallBackObserver<HomeArticleDetailBean>() {
            @Override
            public void onNext(HomeArticleDetailBean homeArticleDetailBean) {
                if (homeArticleDetailBean.getStatus().getCode().equals("0")) {
                    setViewData(homeArticleDetailBean.getData());
                } else {
                    Toast.makeText(getApplicationContext(), homeArticleDetailBean.getStatus().getCninfo(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setViewData(HomeArticleDetailBean.DataBean data) {
        HomeArticleDetailBean.DataBean.DetailBean detail = data.getDetail();
        new_title.setText(detail.getTitle());
        news_host.setText(detail.getAuthor());
        news_time.setText(detail.getTimeDesc());
        webView.loadData(getHtmlData(detail.getContent()), "text/html;charset=utf-8","utf-8");
    }
    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>html{padding:15px;} body{word-wrap:break-word;font-size:13px;padding:0px;margin:0px} p{padding:0px;margin:0px;font-size:13px;color:#222222;line-height:1.3;} img{padding:0px,margin:0px;max-width:100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }
    @Override
    protected void setControl() {

    }



}
