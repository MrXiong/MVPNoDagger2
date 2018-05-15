package com.mvp.ultimate.http;


import com.mvp.ultimate.base.Constants;
import com.mvp.ultimate.http.api.GankApis;
import com.mvp.ultimate.http.api.GoldApis;
import com.mvp.ultimate.http.response.GankHttpResponse;
import com.mvp.ultimate.http.response.GoldHttpResponse;
import com.mvp.ultimate.model.bean.GankItemBean;
import com.mvp.ultimate.model.bean.GoldListBean;

import java.util.List;

import retrofit2.Call;


/**
 * Created by codeest on 2016/8/3.
 */
public class RetrofitHelper implements HttpHelper {

    private GankApis mGankApiService;
    private GoldApis mGoldApiService;

    public RetrofitHelper(GankApis gankApis, GoldApis goldApis) {
        this.mGankApiService = gankApis;
        this.mGoldApiService = goldApis;
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

    @Override
    public Call<GoldHttpResponse<List<GoldListBean>>> fetchGoldList(String type, int num, int page) {
        return mGoldApiService.getGoldList(Constants.LEANCLOUD_ID, Constants.LEANCLOUD_SIGN,
                "{\"category\":\"" + type + "\"}", "-createdAt", "user,user.installation", num, page * num);
    }

    @Override
    public Call<GoldHttpResponse<List<GoldListBean>>> fetchGoldHotList(String type, String dataTime, int limit) {
        return mGoldApiService.getGoldHot(Constants.LEANCLOUD_ID, Constants.LEANCLOUD_SIGN,
                "{\"category\":\"" + type + "\",\"createdAt\":{\"$gt\":{\"__type\":\"Date\",\"iso\":\"" + dataTime + "T00:00:00.000Z\"}},\"objectId\":{\"$nin\":[\"58362f160ce463005890753e\",\"583659fcc59e0d005775cc8c\",\"5836b7358ac2470065d3df62\"]}}",
                "-hotIndex", "user,user.installation", limit);
    }

}
