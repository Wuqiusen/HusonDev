package com.example.huson.husondevandroid.mvp.presenter;

import android.app.Activity;

import com.example.huson.husondevandroid.bean.GirlBean;
import com.example.huson.husondevandroid.mvp.base.BasePresenter;
import com.example.huson.husondevandroid.mvp.view.GirlDetailsView;
import com.example.huson.husondevandroid.mvp.view.GirlView;

import java.util.List;

/**
 * 当前类注析：
 * Created by huson on 2016/7/29.
 * 940762301@qq.com
 */
public class GirlDetailsPresenter extends BasePresenter<GirlDetailsView> {

    public GirlDetailsPresenter(GirlDetailsView view) {
        attachView(view);
    }


    public void loadData(Activity activity) {
        mvpView.setData((List<GirlBean.ResultsEntity>) activity.getIntent().getSerializableExtra("girls"), activity.getIntent().getIntExtra("poi", 0));

    }

}

