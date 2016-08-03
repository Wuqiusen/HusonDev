package com.example.huson.husondevandroid.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.huson.husondevandroid.R;
import com.example.huson.husondevandroid.bean.GirlBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;


/**
 * viewholder
 */
public class GirlsViewHolder extends BaseViewHolder<GirlBean.ResultsEntity> {

    private ImageView image;

    public GirlsViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_girl);
        image = $(R.id.girl_img);
    }

    @Override
    public void setData(GirlBean.ResultsEntity data) {
        super.setData(data);
        Glide.with(getContext())
                .load(data.url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(image);
    }
}
