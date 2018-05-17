package com.mvp.ultimate.base;


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
