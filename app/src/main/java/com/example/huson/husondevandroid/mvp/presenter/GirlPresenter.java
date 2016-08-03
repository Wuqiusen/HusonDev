package com.example.huson.husondevandroid.mvp.presenter;

import com.example.huson.husondevandroid.bean.GirlBean;
import com.example.huson.husondevandroid.http.HttpMethods;
import com.example.huson.husondevandroid.http.subscriber.ApiCallback;
import com.example.huson.husondevandroid.http.subscriber.ProgressSubscriber;
import com.example.huson.husondevandroid.mvp.base.BasePresenter;
import com.example.huson.husondevandroid.mvp.view.GirlView;
import com.example.huson.husondevandroid.mvp.view.MainView;

import rx.Subscriber;

/**
 * 当前类注析：
 * Created by huson on 2016/7/29.
 * 940762301@qq.com
 */
public class GirlPresenter extends BasePresenter<GirlView> {

    public GirlPresenter(GirlView view) {
        attachView(view);
    }


    public void loadData(int count, int page, final boolean isLoad) {
        HttpMethods.getInstance().getGirls(new ProgressSubscriber<GirlBean>(new ApiCallback<GirlBean>() {
            @Override
            public void onSuccess(GirlBean model) {
                if (isLoad){
                    mvpView.load(model.results);
                }else {
                    mvpView.refresh(model.results);
                }
//                mvpView.getDataSuccess(model.results);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail(msg);
            }

            @Override
            public void onCompleted() {

            }
        }), "福利", count, page);
    }

}

