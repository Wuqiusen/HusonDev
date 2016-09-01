package com.example.huson.husondevandroid.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.huson.husondevandroid.bean.GirlBean;
import com.example.mylibrary.adapter.recycler.BaseViewHolder;
import com.example.mylibrary.adapter.recycler.RecyclerArrayAdapter;
//import com.jude.easyrecyclerview.adapter.BaseViewHolder;
//import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;


/**
 * adapter
 */
public class GirlsAdapter extends RecyclerArrayAdapter<GirlBean.ResultsEntity> {

    public GirlsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new GirlsViewHolder(parent);
    }
}
