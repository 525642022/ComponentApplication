package com.example.net.cache;


import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.example.utils.GeneralKey;
import com.example.utils.utilcode.util.AppUtils;
import com.example.utils.utilcode.util.EncryptUtils;
import com.example.utils.utilcode.util.PhoneUtils;
import com.example.utils.utilcode.util.SDCardUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class CommonInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取到request
        Request request = chain.request();
        //获取到方法
        String method = request.method();
        //添加公共参数
        HashMap<String, Object> commomParamsMap = getCommonParamsMap(request);
        addOldParams(commomParamsMap, request);
        //添加加密参数
        HttpUrl url = request.url();
        String signUrl = url.scheme() + "://" + url.host() + url.encodedPath();
        String sign = GeneralKey.sign(signUrl, commomParamsMap);
        HttpUrl.Builder authorizedUrlBuilder = request.url()
                .newBuilder()
                .scheme(request.url().scheme())
                .host(request.url().host())
                .addQueryParameter("deviceId",getDeviceId())
                .addQueryParameter("osType", "android")
                .addQueryParameter("versionCode", AppUtils.getAppVersionCode()+"")
                .addQueryParameter("sign", sign);
        // 新的请求
        Request newRequest = request.newBuilder()
                .method(request.method(), request.body())
                .url(authorizedUrlBuilder.build())
                .build();
        return chain.proceed(newRequest);
    }

    /***
     * 在公共参数的基础上为其添加旧的单数
     * @param commomParamsMap
     * @param request
     */
    private void addOldParams(HashMap<String, Object> commomParamsMap, Request request) {
        //获取到请求地址api
        HttpUrl httpUrl = request.url();
        //通过请求地址(最初始的请求地址)获取到参数列表
        Set<String> parameterNames = httpUrl.queryParameterNames();
        for (String key : parameterNames) {  //循环参数列表
            commomParamsMap.put(key, httpUrl.queryParameter(key));
        }
    }

    /***
     * 获取带有公共参数的map
     * @return
     * @param request
     */
    private HashMap<String, Object> getCommonParamsMap(Request request) {
        HashMap<String, Object> commomParamsMap = new HashMap<>();
        commomParamsMap.put("deviceId",getDeviceId());
        commomParamsMap.put("osType", "android");
        commomParamsMap.put("versionCode", AppUtils.getAppVersionCode()+"");
        return commomParamsMap;
    }

    @SuppressLint("MissingPermission")
    private String getDeviceId() {
        String deviceId;
        deviceId= PhoneUtils.getDeviceId();
        if(TextUtils.isEmpty(deviceId)){
            deviceId = getSDCardString();
            if(TextUtils.isEmpty(deviceId)){
                String key = EncryptUtils.encryptMD5ToString(System.currentTimeMillis()+"");
                saveString(deviceId);
            }
        }
        return deviceId;
    }
    private void  saveString(String deviceId ){
        File saveFile = new File(SDCardUtils.getSDCardPathByEnvironment(), "entry");
        try {
            FileWriter fw = new FileWriter(saveFile);
            fw.write(deviceId);
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private  String getSDCardString (){
        String result = null;
            File saveFile = new File(SDCardUtils.getSDCardPathByEnvironment(), "entry");
            try {
                FileReader fr = new FileReader(saveFile);
                BufferedReader r = new BufferedReader(fr);
                result = r.readLine();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return result;
    }
}
