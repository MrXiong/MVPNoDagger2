package com.mvp.ultimate.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;


import com.mvp.ultimate.db.dao.DBManager;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ZX on 2018/5/8.
 */

public class IApplication extends Application {

    private static IApplication instance;
    private static Context context;
    private Set<Activity> allActivities;

    public static synchronized IApplication getInstance() {
        return instance;
    }
    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
        initDB();
    }

    private void initDB() {
         DBManager.newInstance();
    }


    //------------------Activity Manager---------------------------
    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

}
