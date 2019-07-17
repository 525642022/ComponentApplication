package com.example.utils;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * @author ljc
 * @create 2018/10/23 下午4:15
 * @function :
 */

public class WebViewSettingUtils {
    public static void   setWebSetting(WebView webView ,Context context){
        WebSettings webSetting = webView.getSettings();
        webSetting.supportMultipleWindows();  //多窗口
        webSetting.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        webSetting.setSupportZoom(true);  //支持缩放
        webSetting.setBuiltInZoomControls(true); //设置支持缩放
        webSetting.setLoadsImagesAutomatically(true);  //支持自动加载图片
        webSetting.setUseWideViewPort(true);//屏幕自适应
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setJavaScriptEnabled(true);   //与js交互
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
//       从Android5.0开始，WebView默认不支持同时加载Https和Http混合模式。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSetting.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        webSetting.setDomStorageEnabled(true);    //开启DOM形式存储
        webSetting.setDatabaseEnabled(false);   //开启数据库形式存储
        webSetting.setAppCacheEnabled(false);  //开启缓存功能
        webSetting.setDefaultTextEncodingName("utf-8");// 设置编码格式
        String appCacheDir = context.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();   //缓存数据的存储地址
        webSetting.setAppCachePath(appCacheDir);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSetting.setAllowFileAccess(true);
        webSetting.setTextZoom(100);
    }
}
