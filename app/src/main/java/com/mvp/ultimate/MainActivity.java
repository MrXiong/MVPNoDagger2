package com.mvp.ultimate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.mvp.ultimate.base.BaseActivity;
import com.mvp.ultimate.base.ToolbarActivity;
import com.mvp.ultimate.contract.GirlContract;
import com.mvp.ultimate.db.dao.DBManager;
import com.mvp.ultimate.db.dao.auto.ZxUserDao;
import com.mvp.ultimate.db.entity.ZxUser;
import com.mvp.ultimate.utils.StatusBarUtil;

import java.util.List;

public class MainActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ZxUserDao zxUserDao = DBManager.getDaoSession().getZxUserDao();

        ZxUser user = new ZxUser();
        user.setId(0);
        user.setName("张飞");
        user.setPhone("1233434");

        ZxUser user2 = new ZxUser();
        user2.setId(1);
        user2.setName("李四");
        user2.setPhone("888888888888");


        zxUserDao.insert(user);
        zxUserDao.insert(user2);


        List<ZxUser> zxUsers = zxUserDao.loadAll();
        for (int i = 0; i <zxUsers.size() ; i++) {
            ZxUser zc = zxUsers.get(i);
            Log.v("tag", zc.getName());

        }

    }

    public void gotoGirls(View v){
        startActivity(new Intent(this, GirlsActivity.class));
    }
}
