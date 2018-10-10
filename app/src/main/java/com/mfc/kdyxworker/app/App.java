package com.mfc.kdyxworker.app;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.mfc.kdyxworker.utils.log.XLog;
import com.mfc.kdyxworker.utils.log.XLogConfig;

import java.util.LinkedList;
import java.util.List;

public class App extends MultiDexApplication {
    private List<Activity> activityList = new LinkedList();
    private static App mApp;
    // #log
    public static String tag = "KDYXWorker";
    public static boolean isDebug = true;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }
    public static Context getContext() {
        return mApp;
    }

    //单例模式中获取唯一的MyApplication实例
    public static App getInstance() {
        if (mApp == null) {
            mApp = new App();
        }
        return mApp;
    }
    public static XLogConfig initXLog() {
        return XLog.init();
    }

    //添加Activity到容器中
    public void addActivity(Activity activity) {
        if (activity==null||activityList==null){
            return;
        }
        activityList.add(activity);
    }
    public void removeActivity(Activity activity){
        if (activity==null||activityList==null){
            return;
        }
        activityList.remove(activity);
    }

    //遍历所有Activity并finish
    public void exit() {
        if (activityList==null){
            return;
        }
        for (Activity activity : activityList) {
            if (activity!=null){
                activity.finish();
            }
        }
    }
}
