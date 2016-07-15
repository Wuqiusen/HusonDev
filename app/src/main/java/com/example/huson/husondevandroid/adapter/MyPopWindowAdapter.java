package com.example.huson.husondevandroid.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.huson.husondevandroid.R;

import java.util.List;

/**
 * Created by huson on 2016/5/24.
 * 940762301@qq.com
 */
public class MyPopWindowAdapter extends CommonAdapter<String> {
    private Context mContext;
    private List<String> stringList;
    private OnClickItem onClickItem = null;

    private int currentPhotoCount = 0;

    public MyPopWindowAdapter(Context context, int layoutId, List<String> items) {
        super(context, layoutId, items);
        mContext = context;
        stringList = items;
    }
    @Override
    protected void convert(ViewHolder holder, final int position, String item) {
        LinearLayout ll_pw = (LinearLayout) holder.getView(R.id.ll_pw);
        TextView tv_item_pw = (TextView) holder.getView(R.id.tv_item_pw);
        tv_item_pw.setText(item);
        ll_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickItem != null){
                    onClickItem.ItemClick(stringList.get(position));
                }

            }
        });
    }


    public void setOnClickListener(OnClickItem onClickListener){
        this.onClickItem = onClickListener;
    }

    public  interface OnClickItem{
        void ItemClick(String string);
    }
    public int getCurrentCount(){
        return currentPhotoCount;
    }
}
