package com.mvp.ultimate.base;

import android.content.Context;

import com.mvp.ultimate.BuildConfig;
import com.mvp.ultimate.http.api.GankApis;
import com.mvp.ultimate.http.api.GoldApis;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppClient {

    private Context mContext;

    public AppClient(Context context) {
        this.mContext = context;
    }

    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }


    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    Retrofit provideGankRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, GankApis.HOST);
    }

    Retrofit provideGoldRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, GoldApis.HOST);
    }


    //----------------------------------具体构建--------------------------------------------

    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        RetrofitFactory factory = new RetrofitFactory();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = factory.initLogInterceptor();
            builder.addInterceptor(httpLoggingInterceptor);
        }

        //设置缓存
        Interceptor cacheInterceptor = factory.initCacheInterceptor(mContext);
        Cache cache = factory.initCache(mContext);
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);

        //Header
        Interceptor headerInterceptor = factory.initHeader();
        builder.addInterceptor(headerInterceptor);

        //公共参数
        Interceptor queryInterceptor = factory.initQuery(mContext);
        builder.addInterceptor(queryInterceptor);

        //置cookie，代码略
        //CookieManager cookieManager = new CookieManager();
        //cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        //builder.cookieJar(new JavaNetCookieJar(cookieManager));

        //设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);

        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}