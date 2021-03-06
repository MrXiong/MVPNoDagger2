package com.mvp.ultimate.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.mvp.ultimate.SnackbarUtil;
import com.mvp.ultimate.http.HttpHelper;
import com.mvp.ultimate.http.RetrofitHelper;
import com.mvp.ultimate.http.api.GankApis;
import com.mvp.ultimate.http.api.GoldApis;
import com.mvp.ultimate.model.DataManager;


import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


public abstract class BaseActivity<T extends BasePresenter> extends ToolbarActivity implements BaseView {

    protected T mPresenter;


    protected Context mContext;
    private Unbinder mUnBinder;

    protected DataManager mDataManager;
    protected HttpHelper mRetrofitHelper;

    protected abstract int getLayout();

    protected abstract T getPresenter();


    protected void initInject() {
        AppClient appClient = new AppClient(mContext);

        Retrofit.Builder builder = appClient.provideRetrofitBuilder();
        OkHttpClient.Builder client = appClient.provideOkHttpBuilder();
        OkHttpClient okHttpClient = appClient.provideClient(client);

        Retrofit gankRetrofit = appClient.provideGankRetrofit(builder, okHttpClient);
        Retrofit goldRetrofit = appClient.provideGoldRetrofit(builder, okHttpClient);

        GoldApis goldApis = goldRetrofit.create(GoldApis.class);
        GankApis gankApis = gankRetrofit.create(GankApis.class);
        mRetrofitHelper = new RetrofitHelper(gankApis, goldApis);
        mDataManager = new DataManager(mRetrofitHelper);
        mPresenter = getPresenter();
    }

    protected void onViewCreated() {
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        IApplication.getInstance().addActivity(this);
        onViewCreated();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
        IApplication.getInstance().removeActivity(this);
        mUnBinder.unbind();
    }


    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }


    // --------------------------implements-------------------------
    @Override
    public void showErrorMsg(String msg) {
        SnackbarUtil.show(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    @Override
    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }
}
