package com.example.home.api;

import com.example.bean.MyUserBean;
import com.example.home.bean.CheckBean;
import com.example.home.bean.HomeArticleBean;
import com.example.home.bean.HomeArticleDetailBean;
import com.example.home.bean.HomeBannerBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {

    @POST("/rest/api/ad/list")
    Observable<HomeBannerBean> getBannerList();

    @POST("/rest/api/version/check")
    Observable<CheckBean> checkUpdate();
    @POST("/rest/api/article/list")
    Observable<HomeArticleBean> getArticle(@Query("cid") String cid, @Query("page") int page);

    @POST("/rest/api/article/detail")
    Observable<HomeArticleDetailBean> getArticleDetail(@Query("id") String id);
    @POST("/rest/api/user/autologin")
    Observable<MyUserBean> autoLogin(@Query("uid") String uid);

    @POST("/rest/api/user/infos")
    Observable<MyUserBean> getUserInfo(@Query("uid") String uid, @Query("token") String token);
}
