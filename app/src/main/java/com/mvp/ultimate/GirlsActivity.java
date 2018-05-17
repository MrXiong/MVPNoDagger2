package com.mvp.ultimate;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mvp.ultimate.base.BaseActivity;
import com.mvp.ultimate.base.ToolbarActivity;
import com.mvp.ultimate.contract.GirlContract;
import com.mvp.ultimate.model.bean.GankItemBean;
import com.mvp.ultimate.model.bean.GoldListBean;
import com.mvp.ultimate.presenter.GirlPresenter;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GirlsActivity extends BaseActivity<GirlPresenter> implements GirlContract.View {

    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    private List<GankItemBean> mList = new ArrayList<>();
    private List<GoldListBean> mGoldList = new ArrayList<>();
    private CommonAdapter mCommonAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_girls;
    }


    @Override
    protected GirlPresenter getPresenter() {
        return new GirlPresenter(mDataManager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        mPresenter.getGirlData();
        mPresenter.getGoldData();
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

    @Override
    public void showGoldContent(List<GoldListBean> list) {
        mGoldList.clear();
        mGoldList.addAll(list);
    }
}
