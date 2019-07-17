package com.example.commonactivity.api;


import com.example.bean.CommonResult;
import com.example.bean.MyUserBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CommonApi {

    @POST("/rest/api/user/login")
    Observable<MyUserBean> login(@Query("mobile") String mobile, @Query("pwd") String pwd);

    @POST("/rest/api/user/register")
    Observable<MyUserBean> register(@Query("mobile") String mobile, @Query("pwd") String pwd, @Query("code") String code);

    @POST("/rest/api/user/getCode")
    Observable<CommonResult> getCode();


}
