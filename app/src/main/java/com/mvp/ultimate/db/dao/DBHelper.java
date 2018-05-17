package com.mvp.ultimate.db.dao;

import android.content.Context;
import android.util.Log;


import com.mvp.ultimate.db.dao.auto.DaoMaster;

import org.greenrobot.greendao.database.Database;


public class DBHelper extends DaoMaster.OpenHelper {
    public static final String DBNAME = "wallet.db";

    public DBHelper(Context context) {
        super(context, DBNAME, null);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        Log.d("DEBUG", "DB_OLD_VERSION : " + oldVersion + ", DB_NEW_VERSION : " + newVersion);
    }
}
