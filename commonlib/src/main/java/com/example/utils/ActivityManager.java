package com.example.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * author：风不会停息 on 2017/7/22 09:31
 * mailbox：yh131412hys@163.com
 * project：Somur
 * tel：13295355900
 * describe：
 */

public class ActivityManager {

    private static List<Activity> activities = new ArrayList<>();
    public static List<Activity> activities2 = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    private static Activity mallActivity;

    public static void setIntentActivity(Activity activity) {
        mallActivity = activity;
    }

    public static Activity getIntentActivity() {
        return mallActivity;
    }

    public static String mUrl;
    public static String mType;

    public static String getIntentlUrl() {
        return mUrl;
    }

    public static String getIntentType() {
        return mType;
    }

    public static void setIntentTypeAndUrl(String type, String url) {
        mType = type;
        mUrl = url;
    }

    public static void addActivityOrder(Activity activity) {
        activities2.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static void finishAll2() {
        for (Activity activity : activities2) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static Activity mActivity;

    public static void setActivity(Activity activity) {
        mActivity = activity;
    }

    public static Activity getActivity() {
        return mActivity;
    }

    public static void cleacAll() {
        activities.clear();
    }

}
