package com.example.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.example.constants.ARouterConfig;


public class LoginUtils {
    public static boolean isLogin(Context context){
        if(TextUtils.isEmpty(SharedPrefUtil.getUserId(context))){
            ARouterUtils.getActivity(ARouterConfig.ZXXQ_COMMON_LOGIN);
//            Intent intent = new Intent(context, LoginActivity.class);
//            context.startActivity(intent);
            return false;
        }else{
            return true;
        }

    }
}
