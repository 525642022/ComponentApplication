package com.example.net.manager;



import com.example.net.cache.CacheInterceptor;
import com.example.net.cache.CommonInterceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liujingchang on 2017/8/15.
 */

public class RetrofitManager {
    private static final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);
    private static CacheInterceptor cacheInterceptor = new CacheInterceptor();
    private  static List<String> cookieUrl = new ArrayList<>();

    /***
     * 初始化okhttp信息
     */
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request= chain.request().newBuilder().addHeader("Connection", "close")
                                  .build();
                    return chain.proceed(request);
                }
            })
            .addInterceptor(new CommonInterceptor())
            //打印日志
            .addInterceptor(interceptor)
//            //设置Cache
            .addNetworkInterceptor(cacheInterceptor)//缓存方面需要加入这个拦截器
            .addInterceptor(cacheInterceptor)
            .readTimeout(100, TimeUnit.SECONDS)//设置读取超时时间
            .writeTimeout(100, TimeUnit.SECONDS)//设置写的超时时间
            .connectTimeout(100, TimeUnit.SECONDS)//设置连接超时时间
            //失败重连
            .retryOnConnectionFailure(false)
            .build();

    /***
     *
     * @param clazz
     * @param url
     * @param <T>
     * @return
     */
    public static <T> T createApi(Class<T> clazz, String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);
    }

}
