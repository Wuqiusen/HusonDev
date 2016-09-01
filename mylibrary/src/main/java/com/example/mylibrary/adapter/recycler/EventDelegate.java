package com.example.mylibrary.adapter.recycler;

import android.view.View;

/**
 * 当前类注析：
 * Created by huson on 2016/8/26.
 * 940762301@qq.com
 */
public interface EventDelegate {
    void addData(int length);
    void clear();

    void stopLoadMore();
    void pauseLoadMore();
    void resumeLoadMore();

    void setMore(View view, RecyclerArrayAdapter.OnLoadMoreListener listener);
    void setNoMore(View view);
    void setErrorMore(View view);
}
