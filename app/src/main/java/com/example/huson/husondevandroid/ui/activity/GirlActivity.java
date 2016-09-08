package com.example.huson.husondevandroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.huson.husondevandroid.R;
import com.example.huson.husondevandroid.adapter.GirlsAdapter;
import com.example.huson.husondevandroid.bean.GirlBean;
import com.example.mylibrary.adapter.recycler.RecyclerArrayAdapter;
import com.example.mylibrary.base.MvpActivity;
import com.example.huson.husondevandroid.mvp.presenter.GirlPresenter;
import com.example.huson.husondevandroid.mvp.view.GirlView;
import com.example.mylibrary.utils.DebugLog;
import com.example.mylibrary.utils.ToastHelper;
import com.example.mylibrary.widget.EasyRecycleView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 当前类注析：
 * Created by huson on 2016/8/3.
 * 940762301@qq.com
 */
public class GirlActivity extends MvpActivity<GirlPresenter> implements GirlView,
        RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{

    @Bind(R.id.girls_recycler_view)
    EasyRecycleView girlsRecyclerView;
    @Bind(R.id.girls_fragment)
    FrameLayout girlsFragment;
    @Bind(R.id.swiperefreshlayout)
    LinearLayout swiperefreshlayout;

    private List<GirlBean.ResultsEntity> data;
    private GirlsAdapter mAdapter;

    private int page = 1;
    private int size = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_girl);
        ButterKnife.bind(this);
        initRecyclerView();
        mvpPresenter.loadData(20, 1, false);

    }


    @Override
    public void refresh(List<GirlBean.ResultsEntity> datas) {
        data.clear();
        data.addAll(datas);
        mAdapter.clear();
        mAdapter.addAll(datas);
    }

    @Override
    public void load(List<GirlBean.ResultsEntity> datas) {
        data.addAll(datas);
        mAdapter.addAll(datas);
    }

    @Override
    public void getDataFail(String msg) {
        ToastHelper.showToast(msg, this);
        girlsRecyclerView.showError();

    }


    @Override
    protected GirlPresenter createPresenter() {
        return new GirlPresenter(this);
    }

    private void initRecyclerView() {
        data = new ArrayList<>();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        girlsRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mAdapter = new GirlsAdapter(this);

        girlsRecyclerView.setAdapterWithProgress(mAdapter);

        mAdapter.setMore(R.layout.load_more_layout, this);
        mAdapter.setNoMore(R.layout.no_more_layout);
        mAdapter.setError(R.layout.error_layout);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mActivity, GirlDetailsActivity.class);
                intent.putExtra("girls", (Serializable)data);
                intent.putExtra("poi", position);
                startActivity(intent);
            }
        });

        girlsRecyclerView.setRefreshListener(this);
    }

//    @OnClick(R.id.fab)
//    public void onClick() {
//    }

    @Override
    public void onLoadMore() {
        if (data.size() % 20 == 0) {
            DebugLog.d("onloadmore");
            page++;
            mvpPresenter.loadData(size, page, true);
        }
    }

    @Override
    public void onRefresh() {
        mvpPresenter.loadData(size, 1, false);
        page = 1;

    }


}
