package com.example.huson.husondevandroid.mvp.base;

import android.os.Bundle;

import com.example.huson.husondevandroid.mvp.base.BaseActivity;
import com.example.huson.husondevandroid.mvp.base.BasePresenter;


public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
