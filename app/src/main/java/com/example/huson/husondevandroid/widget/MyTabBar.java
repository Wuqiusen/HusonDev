package com.example.huson.husondevandroid.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.huson.husondevandroid.R;
import com.example.huson.husondevandroid.adapter.MytabBarAdapter;

import java.util.List;

/**
 * 当前类注析：
 * Created by huson on 2016/5/24.
 * 940762301@qq.com
 */
public class MyTabBar extends LinearLayout {
    private RecyclerView recyclerView;
    private Context mContext;
    private int mPoi;
    private MytabBarAdapter mytabBarAdapter;
    public MyTabBar(Context context) {
        super(context);
    }

    public MyTabBar(Context context, AttributeSet attrs){
        super(context, attrs);
        mContext = context;
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.horizontal_recycleview, this);
        recyclerView = (RecyclerView) findViewById(R.id.rv_my);
        setOrientation();


    }

    public void setAdapter(List<String> strings){
        mytabBarAdapter = new MytabBarAdapter(mContext, strings);
        recyclerView.setAdapter(mytabBarAdapter);
//        mytabBarAdapter.setOnItemClickListener(new MytabBarAdapter.OnRecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(View view, String str1, int poi) {
//                DebugLog.e("++++++++" + poi);
//                mPoi = poi;
//
//            }
//        });
    }

    public void setClickLisetener(MytabBarAdapter.OnRecyclerViewItemClickListener lisetener){
        mytabBarAdapter.setOnItemClickListener(lisetener);

    }

    public void setMyTabBarItem(int i){
        mytabBarAdapter.setItem(recyclerView.getChildAt(i));

    }

    public int getPoi(){
        return mPoi;
    }

    private void setOrientation(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

//    public interface ItemClickListener {
//        void OnItemClick
//    }
}
