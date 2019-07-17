package com.example.shop.api;


import android.content.Context;

import com.example.bean.CommonResult;
import com.example.net.callback.CallBackObserver;
import com.example.net.manager.RetrofitManager;
import com.example.net.manager.RxManager;
import com.example.shop.bean.ShopDetail;
import com.example.shop.bean.ShopListBean;
import com.example.utils.SharedPrefUtil;

public class ApiManager {

    public static void getShopList(CallBackObserver<ShopListBean> callBackObserver) {
        RxManager rxManager = new RxManager();
        rxManager.setDefaultObservable(RetrofitManager.createApi(ShopApi.class, Common.BaseUrl)
                .getShopList())
                .subscribe(callBackObserver);
    }
    public static void getShopDetail(String id, CallBackObserver<ShopDetail> callBackObserver) {
        RxManager rxManager = new RxManager();
        rxManager.setDefaultObservable(RetrofitManager.createApi(ShopApi.class, Common.BaseUrl)
                .getShopDetail(id))
                .subscribe(callBackObserver);
    }

    public static void commitOrder(
            Context context,
            String id,
            String name,
            String mobile,
            String province,
            String city,
            String district,
            String address,
            CallBackObserver<CommonResult> callBackObserver) {
        RxManager rxManager = new RxManager();
        rxManager.setDefaultObservable(RetrofitManager.createApi(ShopApi.class, Common.BaseUrl)
                .commitOrder(SharedPrefUtil.getUserId(context), id, name, mobile, province, city, district, address))
                .subscribe(callBackObserver);
    }

}
