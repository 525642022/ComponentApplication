package com.example.home.api;

import android.content.Context;


import com.example.bean.MyUserBean;
import com.example.home.bean.CheckBean;
import com.example.home.bean.HomeArticleBean;
import com.example.home.bean.HomeArticleDetailBean;
import com.example.home.bean.HomeBannerBean;

import com.example.net.callback.CallBackObserver;
import com.example.net.manager.RetrofitManager;
import com.example.net.manager.RxManager;
import com.example.utils.SharedPrefUtil;

public class ApiManager {
    public static void getBannerList(CallBackObserver<HomeBannerBean> callBackObserver) {
        RxManager rxManager = new RxManager();
        rxManager.setDefaultObservable(RetrofitManager.createApi(UserApi.class, Common.BaseUrl)
                .getBannerList())
                .subscribe(callBackObserver);
    }
    public static void checkUpdate(CallBackObserver<CheckBean> callBackObserver) {
        RxManager rxManager = new RxManager();
        rxManager.setDefaultObservable(RetrofitManager.createApi(UserApi.class, Common.BaseUrl)
                .checkUpdate())
                .subscribe(callBackObserver);
    }
    public static void autoLogin(Context context,CallBackObserver<MyUserBean> callBackObserver) {
        RxManager rxManager = new RxManager();
        rxManager.setDefaultObservable(RetrofitManager.createApi(UserApi.class, Common.BaseUrl)
                .autoLogin(SharedPrefUtil.getUserId(context)))
                .subscribe(callBackObserver);
    }

    public static void getUserInfo(Context context, String token, CallBackObserver<MyUserBean> callBackObserver) {
        RxManager rxManager = new RxManager();
        rxManager.setDefaultObservable(RetrofitManager.createApi(UserApi.class, Common.BaseUrl)
                .getUserInfo(SharedPrefUtil.getUserId(context), token))
                .subscribe(callBackObserver);
    }
    public static void getArticle(String cid, int page, CallBackObserver<HomeArticleBean> callBackObserver) {
        RxManager rxManager = new RxManager();
        rxManager.setDefaultObservable(RetrofitManager.createApi(UserApi.class, Common.BaseUrl)
                .getArticle(cid, page))
                .subscribe(callBackObserver);
    }
    public static void getArticleDetail(String id, CallBackObserver<HomeArticleDetailBean> callBackObserver) {
        RxManager rxManager = new RxManager();
        rxManager.setDefaultObservable(RetrofitManager.createApi(UserApi.class, Common.BaseUrl)
                .getArticleDetail(id))
                .subscribe(callBackObserver);
    }
}
