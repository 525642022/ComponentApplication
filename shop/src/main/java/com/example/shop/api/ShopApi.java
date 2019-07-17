package com.example.shop.api;


import com.example.bean.CommonResult;
import com.example.shop.bean.ShopDetail;
import com.example.shop.bean.ShopListBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ShopApi {

    @POST("/rest/api/items/list")
    Observable<ShopListBean> getShopList();

    @POST("/rest/api/items/detail")
    Observable<ShopDetail> getShopDetail(@Query("id") String id);

    @POST("/rest/api/trade/commit")
    Observable<CommonResult> commitOrder(
            @Query("uid") String uid,
            @Query("itemid") String id,
            @Query("name") String name,
            @Query("mobile") String mobile,
            @Query("province") String province,
            @Query("city") String city,
            @Query("district") String district,
            @Query("address") String address
    );
}
