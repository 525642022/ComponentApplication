package com.example.me.api;



import com.example.bean.CommonResult;
import com.example.bean.MyUserBean;
import com.example.me.bean.OrderListBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MeApi {


    @POST("/rest/api/trade/list")
    Observable<OrderListBean> getOrderList(@Query("uid") String uid, @Query("page") int page);

    @POST("/rest/api/user/infos")
    Observable<MyUserBean> getUserInfo(@Query("uid") String uid, @Query("token") String token);
}
