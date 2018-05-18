package com.mvp.ultimate.base;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ZX on 2018/5/18.
 * 需要定制Callback的话需继承BaseCallback
 */

public abstract class BaseCallback<T> implements Callback<T> {


    // call ：重复请求等请求的处理
    //response ：返回后的状态
    @Override
    public void onResponse(Call call, Response response) {
        if (response.isSuccessful()) {
            onLogicSuccess(call, response);
        } else {
            onLogicFailure(call, response);
        }
    }

    //code >= 200 && code < 300
    public abstract void onLogicSuccess(Call<T> call, Response<T> response);

    ////请求出现错误例如：404 或者 500
    public abstract void onLogicFailure(Call<T> call, Response<T> response);

    ////GSON解析数据转换错误，手机断网或者网络异常。
    @Override
    public abstract void onFailure(Call<T> call, Throwable t);


}
