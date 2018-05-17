package com.mvp.ultimate.contract;

import com.mvp.ultimate.base.BasePresenter;
import com.mvp.ultimate.base.BaseView;
import com.mvp.ultimate.model.bean.GankItemBean;
import com.mvp.ultimate.model.bean.GoldListBean;

import java.util.List;


public interface GirlContract {
    interface Model {
    }

    interface View extends BaseView {
        void showContent(List<GankItemBean> list);
        void showGoldContent(List<GoldListBean> list);
    }

    interface Presenter extends BasePresenter<View> {
        void getGirlData();
        void getGoldData();
    }
}
