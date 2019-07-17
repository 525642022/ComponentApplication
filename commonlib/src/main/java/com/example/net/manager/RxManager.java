package com.example.net.manager;




import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by liujingchang on 2017/8/15.
 */

public class RxManager {
    public <T> Observable setDefaultObservable(Observable<T> apiObservable) {
        return apiObservable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
