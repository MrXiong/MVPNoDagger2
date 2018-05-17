package com.mvp.ultimate.http;


import com.mvp.ultimate.http.response.GankHttpResponse;
import com.mvp.ultimate.http.response.GoldHttpResponse;
import com.mvp.ultimate.model.bean.GankItemBean;
import com.mvp.ultimate.model.bean.GoldListBean;

import java.util.List;

import retrofit2.Call;



public interface HttpHelper {


    Call<GankHttpResponse<List<GankItemBean>>> fetchTechList(String tech, int num, int page);

    Call<GankHttpResponse<List<GankItemBean>>> fetchGirlList(int num, int page);

    Call<GankHttpResponse<List<GankItemBean>>> fetchRandomGirl(int num);

    Call<GoldHttpResponse<List<GoldListBean>>> fetchGoldList(String type, int num, int page);

    Call<GoldHttpResponse<List<GoldListBean>>> fetchGoldHotList(String type, String dataTime, int limit);

}
