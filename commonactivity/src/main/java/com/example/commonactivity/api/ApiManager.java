package com.example.commonactivity.api;


import com.example.bean.CommonResult;
import com.example.bean.MyUserBean;
import com.example.net.callback.CallBackObserver;
import com.example.net.manager.RetrofitManager;
import com.example.net.manager.RxManager;

public class ApiManager {
    public static void login(String mobile, String pwd, CallBackObserver<MyUserBean> callBackObserver) {
        RxManager rxManager = new RxManager();
        rxManager.setDefaultObservable(RetrofitManager.createApi(CommonApi.class, Common.BaseUrl)
                .login(mobile, pwd))
                .subscribe(callBackObserver);
    }

    public static void register(String mobile, String pwd, String code, CallBackObserver<MyUserBean> callBackObserver) {
        RxManager rxManager = new RxManager();
        rxManager.setDefaultObservable(RetrofitManager.createApi(CommonApi.class, Common.BaseUrl)
                .register(mobile, pwd, code))
                .subscribe(callBackObserver);
    }

    public static void getCode(CallBackObserver<CommonResult> callBackObserver) {
        RxManager rxManager = new RxManager();
        rxManager.setDefaultObservable(RetrofitManager.createApi(CommonApi.class, Common.BaseUrl)
                .getCode())
                .subscribe(callBackObserver);
    }

}
