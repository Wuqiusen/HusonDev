package com.example.huson.husondevandroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.huson.husondevandroid.R;
import com.example.mylibrary.base.MvpActivity;
import com.example.huson.husondevandroid.mvp.modle.MainModel;
import com.example.huson.husondevandroid.mvp.presenter.MainPresenter;
import com.example.huson.husondevandroid.mvp.view.MainView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBar("美女");
//        mvpPresenter.loadData();
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void getDataSuccess(MainModel model) {

    }

    @Override
    public void getDataFail(String msg) {

    }

    @OnClick(R.id.btn_pretty_girls)
    public void onClick() {
        startActivity(new Intent(this, GirlActivity.class));
    }
}
