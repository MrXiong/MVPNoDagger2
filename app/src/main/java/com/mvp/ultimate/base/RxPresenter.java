package com.mvp.ultimate.base;

/**
 * Created by ZX on 2018/5/8.
 */

public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    protected T mView;


    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }
}
