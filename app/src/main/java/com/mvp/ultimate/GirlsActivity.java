package com.mvp.ultimate;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mvp.ultimate.base.AppClient;
import com.mvp.ultimate.base.BaseActivity;
import com.mvp.ultimate.contract.GirlContract;
import com.mvp.ultimate.http.HttpHelper;
import com.mvp.ultimate.http.RetrofitHelper;
import com.mvp.ultimate.http.api.GankApis;
import com.mvp.ultimate.model.DataManager;
import com.mvp.ultimate.model.bean.GankItemBean;
import com.mvp.ultimate.presenter.GirlPresenter;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Retrofit;

public class GirlsActivity extends BaseActivity<GirlPresenter> implements GirlContract.View {

    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    private List<GankItemBean> mList = new ArrayList<>();
    private CommonAdapter mCommonAdapter;
    private DataManager mDataManager;
    private HttpHelper mRetrofitHelper;

    @Override
    protected int getLayout() {
        return R.layout.activity_girls;
    }

    @Override
    protected void initInject() {
        Retrofit retrofit = AppClient.retrofit(mContext);
        GankApis gankApis = retrofit.create(GankApis.class);
        mRetrofitHelper = new RetrofitHelper(gankApis);
        mDataManager = new DataManager(mRetrofitHelper);
        mPresenter = new GirlPresenter(mDataManager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        mPresenter.getGirlData();
    }

    private void initView() {
        //mRvList.setLayoutManager(new GridLayoutManager(this, 9));
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mCommonAdapter = new CommonAdapter<GankItemBean>(mContext, R.layout.listitem_girls, mList) {
            @Override
            protected void convert(ViewHolder holder, GankItemBean bean, int position) {
                holder.setText(R.id.tv_test, bean.getDesc());
                ImageView imageView = holder.getView(R.id.iv_test);
                Glide.with(mContext).load(bean.getUrl()).into(imageView);



            }
        };
        mRvList.setAdapter(mCommonAdapter);
    }


    private void configpriKey(int position, ViewHolder holder) {
        int number = position / 3;
        Log.v("tag", number + "");
        if (number % 2 == 0) {
            holder.setTextColor(R.id.tv_test, Color.RED);
        } else {
            holder.setTextColor(R.id.tv_test, Color.GREEN);
        }
    }

    @Override
    public void showContent(List<GankItemBean> list) {
        mList.clear();
        mList.addAll(list);
        mCommonAdapter.notifyDataSetChanged();
    }
}
