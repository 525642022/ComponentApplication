package com.example.me.api;



import android.content.Context;

import com.example.bean.CommonResult;
import com.example.bean.MyUserBean;
import com.example.me.bean.OrderListBean;
import com.example.net.callback.CallBackObserver;
import com.example.net.manager.RetrofitManager;
import com.example.net.manager.RxManager;
import com.example.utils.SharedPrefUtil;

public class ApiManager {

    public static void getOrderList(Context context, int page, CallBackObserver<OrderListBean> callBackObserver) {
        RxManager rxManager = new RxManager();
        rxManager.setDefaultObservable(RetrofitManager.createApi(MeApi.class, Common.BaseUrl)
                .getOrderList(SharedPrefUtil.getUserId(context), page))
                .subscribe(callBackObserver);
    }
    public static void getUserInfo(Context context, String token, CallBackObserver<MyUserBean> callBackObserver) {
        RxManager rxManager = new RxManager();
        rxManager.setDefaultObservable(RetrofitManager.createApi(MeApi.class, Common.BaseUrl)
                .getUserInfo(SharedPrefUtil.getUserId(context), token))
                .subscribe(callBackObserver);
    }

}
