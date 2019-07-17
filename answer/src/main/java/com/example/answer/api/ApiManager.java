package com.example.answer.api;


import android.content.Context;

import com.example.answer.bean.AnswerDetail;
import com.example.answer.bean.AnswerList;
import com.example.answer.bean.AnswerPoint;
import com.example.net.callback.CallBackObserver;
import com.example.net.manager.RetrofitManager;
import com.example.net.manager.RxManager;
import com.example.utils.SharedPrefUtil;

public class ApiManager {
    public static void getAnswerList(String cid, Context context, CallBackObserver<AnswerList> callBackObserver) {
        RxManager rxManager = new RxManager();
        rxManager.setDefaultObservable(RetrofitManager.createApi(AnswerApi.class, Common.BaseUrl)
                .getAnswerList(cid, SharedPrefUtil.getUserId(context)))
                .subscribe(callBackObserver);
    }
    public static void getAnswerDetail(String id, CallBackObserver<AnswerDetail> callBackObserver) {
        RxManager rxManager = new RxManager();
        rxManager.setDefaultObservable(RetrofitManager.createApi(AnswerApi.class, Common.BaseUrl)
                .getAnswerDetail(id))
                .subscribe(callBackObserver);
    }

    public static void commitAnswer(String pid, String ids, Context context, CallBackObserver<AnswerPoint> callBackObserver) {
        RxManager rxManager = new RxManager();
        rxManager.setDefaultObservable(RetrofitManager.createApi(AnswerApi.class, Common.BaseUrl)
                .commitAnswer(SharedPrefUtil.getUserId(context), pid, ids))
                .subscribe(callBackObserver);
    }


}
