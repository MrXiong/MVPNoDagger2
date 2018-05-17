package com.mvp.ultimate.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mvp.ultimate.R;
import com.mvp.ultimate.utils.StatusBarUtil;

import me.yokeyword.fragmentation.SupportActivity;


public class ToolbarActivity extends SupportActivity {

    protected FrameLayout mToolbarLayout;
    protected TextView mTvTitle;
    protected Toolbar mToolbar;
    private LinearLayout mContentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        initContentView();
        initStatusBar();
        initOthers();
    }


    //------------------------------设置Toolbar-----------------------------------
    private void initToolbar() {
        mToolbarLayout = new FrameLayout(this);
        LayoutInflater.from(this).inflate(R.layout.layout_toobar, mToolbarLayout,
                true);
        mTvTitle = mToolbarLayout.findViewById(R.id.tv_title);
        mToolbar = mToolbarLayout.findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(getResources().getColor(
                R.color.primary_text_default_material_dark));
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeAsUpIndicator(R.mipmap.icon_back);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    protected void initContentView() {
        mContentView = new LinearLayout(this);
        mContentView.setOrientation(LinearLayout.VERTICAL);
    }

    @Override
    public void setContentView(int layoutResID) {
        // 添加toolbar布局
        mContentView.addView(mToolbarLayout, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        //重新设置为根布局，添加toolbar的高度
        LayoutInflater.from(this).inflate(layoutResID, mContentView, true);

        super.setContentView(mContentView, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        //基类代码统一设置FitsSystemWindows
        View view = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        view.setFitsSystemWindows(true);
        view.setBackgroundResource(R.color.colorPrimary);
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        if (null != mToolbar && null != mTvTitle) {
            mToolbar.setTitle("");
            mTvTitle.setText(title);
        }
    }

    //------------------------------一些拓展的功能-----------------------------
    protected void setEmptyToolbar() {
        if (mToolbarLayout != null) {
            mToolbarLayout.setVisibility(View.GONE);
        }
    }

    protected void setToolbarCustomView(int layoutResId) {
        try {
            LayoutInflater.from(this).inflate(layoutResId, mToolbar, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mTvTitle.setVisibility(View.GONE);
    }

    protected void setToolbarCustomView(View view) {
        if (mToolbarLayout != null && view != null) {
            mToolbarLayout.addView(view, view.getLayoutParams());
        }
        mTvTitle.setVisibility(View.GONE);
    }

    //------------------------------设置Statusbar------------------------------
    private void initStatusBar() {
        StatusBarUtil.setStatusBarColor(this, android.R.color.white);
        StatusBarUtil.StatusBarLightMode(this);
    }

    private void initOthers() {
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
}
