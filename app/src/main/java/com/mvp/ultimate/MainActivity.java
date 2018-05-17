package com.mvp.ultimate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.mvp.ultimate.base.BaseActivity;
import com.mvp.ultimate.base.ToolbarActivity;
import com.mvp.ultimate.contract.GirlContract;
import com.mvp.ultimate.utils.StatusBarUtil;

public class MainActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StatusBarUtil.setStatusBarColor(this, android.R.color.white);
        StatusBarUtil.StatusBarLightMode(this);
        //解决toobar嵌入和根布局底色统一设置
        View view = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        view.setFitsSystemWindows(true);
        view.setBackgroundResource(R.color.colorPrimary);*/
    }

    public void gotoGirls(View v){
        startActivity(new Intent(this, GirlsActivity.class));
    }
}
