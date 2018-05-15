package com.mvp.ultimate.model;

import com.mvp.ultimate.http.HttpHelper;
import com.mvp.ultimate.http.response.GankHttpResponse;
import com.mvp.ultimate.model.bean.GankItemBean;

import java.util.List;

import retrofit2.Call;

/**
 * Created by ZX on 2018/5/9.
 */

public class DataManager implements HttpHelper {

    HttpHelper mHttpHelper;

    public DataManager(HttpHelper httpHelper) {
        mHttpHelper = httpHelper;
    }

    @Override
    public Call<GankHttpResponse<List<GankItemBean>>> fetchTechList(String tech, int num, int page) {
        return mHttpHelper.fetchTechList(tech, num, page);
    }

    @Override
    public Call<GankHttpResponse<List<GankItemBean>>> fetchGirlList(int num, int page) {
        return mHttpHelper.fetchGirlList(num, page);
    }

    @Override
    public Call<GankHttpResponse<List<GankItemBean>>> fetchRandomGirl(int num) {
        return mHttpHelper.fetchRandomGirl(num);
    }
}
