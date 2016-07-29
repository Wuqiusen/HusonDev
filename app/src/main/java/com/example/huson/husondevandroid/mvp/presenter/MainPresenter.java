package com.example.huson.husondevandroid.mvp.presenter;

import com.example.huson.husondevandroid.bean.WeatherinfoBean;
import com.example.huson.husondevandroid.http.HttpInterfaces;
import com.example.huson.husondevandroid.http.HttpMethods;
import com.example.huson.husondevandroid.http.subscriber.ProgressSubscriber;
import com.example.huson.husondevandroid.http.subscriber.SubscriberOnNextListener;
import com.example.huson.husondevandroid.mvp.base.BasePresenter;
import com.example.huson.husondevandroid.mvp.modle.MainModel;
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


    public void loadData(String cityId) {
    }

}

