package com.mvp.ultimate.presenter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.mvp.ultimate.base.RxPresenter;
import com.mvp.ultimate.contract.GirlContract;
import com.mvp.ultimate.http.response.GankHttpResponse;
import com.mvp.ultimate.http.response.GoldHttpResponse;
import com.mvp.ultimate.model.DataManager;
import com.mvp.ultimate.model.bean.GankItemBean;
import com.mvp.ultimate.model.bean.GoldListBean;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GirlPresenter extends RxPresenter<GirlContract.View> implements GirlContract.Presenter {
    private DataManager mDataManager;

    public static final int NUM_OF_PAGE = 20;

    private int currentPage = 1;

    public GirlPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void getGirlData() {
        Call<GankHttpResponse<List<GankItemBean>>> call = mDataManager.fetchGirlList(NUM_OF_PAGE, currentPage);
        call.enqueue(new Callback<GankHttpResponse<List<GankItemBean>>>() {
            @Override
            public void onResponse(Call<GankHttpResponse<List<GankItemBean>>> call, Response<GankHttpResponse<List<GankItemBean>>> response) {
                GankHttpResponse<List<GankItemBean>> gankHttpResponse = response.body();
                mView.showContent(gankHttpResponse.getResults());

            }

            @Override
            public void onFailure(Call<GankHttpResponse<List<GankItemBean>>> call, Throwable t) {

            }
        });
    }
    public static final String IT_GOLD_TYPE = "gold_type";
    @Override
    public void getGoldData() {

        Call<GoldHttpResponse<List<GoldListBean>>> call = mDataManager.fetchGoldList(IT_GOLD_TYPE, NUM_OF_PAGE, currentPage);
        call.enqueue(new Callback<GoldHttpResponse<List<GoldListBean>>>() {
            @Override
            public void onResponse(Call<GoldHttpResponse<List<GoldListBean>>> call, Response<GoldHttpResponse<List<GoldListBean>>> response) {
                GoldHttpResponse<List<GoldListBean>> gankHttpResponse = response.body();
                mView.showGoldContent(gankHttpResponse.getResults());
            }

            @Override
            public void onFailure(Call<GoldHttpResponse<List<GoldListBean>>> call, Throwable t) {

            }
        });
    }
}
