package com.mvp.ultimate.http;


import com.mvp.ultimate.http.response.GankHttpResponse;
import com.mvp.ultimate.model.bean.GankItemBean;

import java.util.List;

import retrofit2.Call;


/**
 * @author: Est <codeest.dev@gmail.com>
 * @date: 2017/4/21
 * @description:
 */

public interface HttpHelper {


    Call<GankHttpResponse<List<GankItemBean>>> fetchTechList(String tech, int num, int page);

    Call<GankHttpResponse<List<GankItemBean>>> fetchGirlList(int num, int page);

    Call<GankHttpResponse<List<GankItemBean>>> fetchRandomGirl(int num);

}
