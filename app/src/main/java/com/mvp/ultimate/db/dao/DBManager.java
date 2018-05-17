package com.mvp.ultimate.db.dao;


import com.mvp.ultimate.base.IApplication;
import com.mvp.ultimate.db.dao.auto.DaoMaster;
import com.mvp.ultimate.db.dao.auto.DaoSession;


public class DBManager {

    private static DBManager INSTANCE;
    private static DaoSession daoSession;

    private DBManager() {
        DBHelper dbHelper = new DBHelper(IApplication.getContext());
        DaoMaster daoMaster = new DaoMaster(dbHelper.getWritableDb());
        daoSession = daoMaster.newSession();
    }

    public static DBManager newInstance() {
        if (INSTANCE == null) {
            synchronized (DBManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DBManager();
                }
            }
        }
        return INSTANCE;
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
