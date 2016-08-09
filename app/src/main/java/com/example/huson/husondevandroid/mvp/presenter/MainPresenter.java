package com.example.huson.husondevandroid.mvp.presenter;

import com.example.mylibrary.base.BasePresenter;
import com.example.huson.husondevandroid.mvp.view.MainView;

/**
 * 当前类注析：
 * Created by huson on 2016/7/29.
 * 940762301@qq.com
 */
public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView view) {
        attachView(view);
    }


    public void loadData(int page, int size) {
    }

}

