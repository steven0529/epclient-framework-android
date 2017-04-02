package com.chuck.epclient.mvp;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 01/04/2017
 */

public abstract class BasePresenter<T extends BaseView> {

    private T view;
    protected CompositeDisposable disposables;

    public BasePresenter(T view) {
        this.view = view;
        this.disposables = new CompositeDisposable();
    }

    void onCreate() {}

    void onStart(){}

    void onPause(){}

    void onStop(){}

    public void onDestroy() {
        disposables.dispose();
    }

    public T getView() {
        return view;
    }

}
