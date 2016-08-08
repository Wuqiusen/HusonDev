package com.example.huson.husondevandroid.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.example.huson.husondevandroid.R;
import com.example.huson.husondevandroid.adapter.GirlDetailsAdapter;
import com.example.huson.husondevandroid.bean.GirlBean;
import com.example.huson.husondevandroid.mvp.base.MvpActivity;
import com.example.huson.husondevandroid.mvp.presenter.GirlDetailsPresenter;
import com.example.huson.husondevandroid.mvp.view.GirlDetailsView;
import com.example.huson.husondevandroid.view.PinchImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 当前类注析：
 * Created by huson on 2016/8/3.
 * 940762301@qq.com
 */
public class GirlDetailsActivity extends MvpActivity<GirlDetailsPresenter> implements GirlDetailsView {

    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.rootView)
    LinearLayout rootView;

    GirlDetailsAdapter girlDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl);
        ButterKnife.bind(this);
        mvpPresenter.loadData(GirlDetailsActivity.this);
    }


    @Override
    protected GirlDetailsPresenter createPresenter() {
        return new GirlDetailsPresenter(this);
    }

    @Override
    public void setData(List<GirlBean.ResultsEntity> datas, int poi) {
        girlDetailsAdapter = new GirlDetailsAdapter(GirlDetailsActivity.this, datas);
        viewPager.setAdapter(girlDetailsAdapter);
        viewPager.setCurrentItem(poi);
    }

    private PinchImageView getCurrentImageView() {
        View currentItem = girlDetailsAdapter.getPrimaryItem();
        if (currentItem == null) {
            return null;
        }
        PinchImageView imageView = (PinchImageView) currentItem.findViewById(R.id.img);
        if (imageView == null) {
            return null;
        }
        return imageView;
    }
}
