package com.mvp.ultimate.http;


import com.mvp.ultimate.http.api.GankApis;
import com.mvp.ultimate.http.response.GankHttpResponse;
import com.mvp.ultimate.model.bean.GankItemBean;

import java.util.List;

import retrofit2.Call;


/**
 * Created by codeest on 2016/8/3.
 */
public class RetrofitHelper implements HttpHelper {

    private GankApis mGankApiService;

    public RetrofitHelper(GankApis gankApiService) {
        this.mGankApiService = gankApiService;
    }

    @Override
    public Call<GankHttpResponse<List<GankItemBean>>> fetchTechList(String tech, int num, int page) {
        return mGankApiService.getTechList(tech, num, page);
    }

    @Override
    public Call<GankHttpResponse<List<GankItemBean>>> fetchGirlList(int num, int page) {
        return mGankApiService.getGirlList(num, page);
    }

    @Override
    public Call<GankHttpResponse<List<GankItemBean>>> fetchRandomGirl(int num) {
        return mGankApiService.getRandomGirl(num);
    }

}
