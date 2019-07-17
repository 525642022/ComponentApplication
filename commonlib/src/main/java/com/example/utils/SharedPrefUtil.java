package com.example.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bean.UserBean;


/**
 * 数据存储
 * <p>
 */
public class SharedPrefUtil {
    public static SharedPreferences mUserSharedPreferences;

    private static SharedPreferences getUserSharedPreferences(Context context) {
        if (mUserSharedPreferences == null) {
            mUserSharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        }
        return mUserSharedPreferences;
    }



    public static void setUser(UserBean user, Context context) {
        SharedPreferences.Editor editor = getUserSharedPreferences(context).edit();
        editor.putString("userid", user.getId());
        editor.putString("token", user.getToken());
        editor.putString("account", user.getAccount());
        editor.putString("points", user.getPoints());
        editor.putString("headImg", user.getHeadImg());
        editor.apply();
    }
    public static void clearUser( Context context) {
        SharedPreferences.Editor editor = getUserSharedPreferences(context).edit();
        editor.remove("userid");
        editor.remove("token");
        editor.remove("account");
        editor.remove("points");
        editor.remove("headImg");
        editor.apply();
    }
    public static void saveMessageToken( Context context,String messageToken) {
        SharedPreferences.Editor editor = getUserSharedPreferences(context).edit();
        editor.remove("messageToken");
        editor.putString("messageToken",messageToken);
        editor.apply();
    }
    public static String getMessageToken(Context context){
        return getUserSharedPreferences(context).getString("messageToken", "");
    }
    public static String getUserName(Context context) {
        return getUserSharedPreferences(context).getString("account", "");
    }
    public static String getPoint(Context context) {
        return getUserSharedPreferences(context).getString("points", "");
    }
    public static String getUserId(Context context) {
        return getUserSharedPreferences(context).getString("userid", "");
    }

    public static String getToken(Context context) {
        return getUserSharedPreferences(context).getString("token", "");
    }
    public static String getHeadImg(Context context) {
        return getUserSharedPreferences(context).getString("headImg", "");
    }

}
