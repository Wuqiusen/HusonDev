package com.example.huson.husondevandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huson.husondevandroid.R;

import java.util.List;

/**
 * Created by huson on 2016/5/24.
 * 940762301@qq.com
 */
public class MytabBarAdapter extends
        RecyclerView.Adapter<MytabBarAdapter.ViewHolder> implements View.OnClickListener {

    private LayoutInflater mInflater;
    private ViewHolder viewHolder;
    private List<String> strings;
    private Context mContext;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private View beforView;

    public MytabBarAdapter(Context context, List<String> str)
    {
        mInflater = LayoutInflater.from(context);
        strings = str;
        mContext = context;

    }

    @Override
    public void onClick(View v) {
        if (beforView != null){
            setBackgroundFalse(beforView);
            ((TextView) beforView.findViewById(R.id.tv_title_name)).setTextColor(mContext.getResources().getColor(R.color.black));
        }
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (String)v.getTag(), v.getId());
           setBackgroundTrue(v);
            beforView = v;
            ((TextView) v.findViewById(R.id.tv_title_name)).setTextColor(mContext.getResources().getColor(R.color.white));
        }
    }

    public void setItem(View v){
        if (beforView != null){
            setBackgroundFalse(beforView);
            ((TextView) beforView.findViewById(R.id.tv_title_name)).setTextColor(mContext.getResources().getColor(R.color.black));
        }
            setBackgroundTrue(v);
            beforView = v;
            ((TextView) v.findViewById(R.id.tv_title_name)).setTextColor(mContext.getResources().getColor(R.color.white));
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(View arg0)
        {
            super(arg0);
        }

        TextView title;
        LinearLayout ll_tab;
    }

    @Override
    public int getItemCount()
    {

        return strings.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = mInflater.inflate(R.layout.view_tabbar,
                viewGroup, false);
        viewHolder = new ViewHolder(view);
        viewHolder.title = (TextView) view.findViewById(R.id.tv_title_name);
        viewHolder.ll_tab = (LinearLayout) view.findViewById(R.id.ll_tab);

        view.setOnClickListener(this);//注册监听事件
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i)
    {
        WindowManager wm = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        int width = (wm.getDefaultDisplay().getWidth());
        int height = wm.getDefaultDisplay().getHeight();
        width = width - 60;
        viewHolder.ll_tab.setMinimumWidth(width / (strings.size()));
        viewHolder.title.setText(strings.get(i).toString());
        viewHolder.title.setTag(strings.get(i).toString());
        viewHolder.itemView.setId(i);//保存tag，点击时获取
        if (i == 0){
            viewHolder.ll_tab.setBackground(mContext.getResources().getDrawable(R.drawable.my_tabbar_left_bg_true));
            viewHolder.title.setTextColor(mContext.getResources().getColor(R.color.white));
            beforView = viewHolder.ll_tab;
        }else if (i == (strings.size() - 1)){
            viewHolder.ll_tab.setBackground(mContext.getResources().getDrawable(R.drawable.my_tabbar_right_bg));
//            beforView = null;
        }else if (i == (strings.size() - 2)){
            viewHolder.ll_tab.setBackground(mContext.getResources().getDrawable(R.drawable.my_tabbar_bg));
        }else {
            viewHolder.ll_tab.setBackground(mContext.getResources().getDrawable(R.drawable.my_tabbar_bg_right));
        }

    }

    public void setBeforView(View beforView) {
        this.beforView = beforView;

    }

    private void setBackgroundTrue(View v){
        if (v.getId() == 0){
            v.setBackground(mContext.getResources().getDrawable(R.drawable.my_tabbar_left_bg_true));
        }else if (v.getId() == (strings.size() - 1)){
            v.setBackground(mContext.getResources().getDrawable(R.drawable.my_tabbar_right_bg_true));
        }else {
            v.setBackground(mContext.getResources().getDrawable(R.drawable.my_tabbar_bg_true));
        }
    }
    private void setBackgroundFalse(View v){
        if (v.getId() == 0){
            v.setBackground(mContext.getResources().getDrawable(R.drawable.my_tabbar_left_bg));
        }else if (v.getId() == (strings.size() - 1)){
            v.setBackground(mContext.getResources().getDrawable(R.drawable.my_tabbar_right_bg));
        }else if (v.getId() == (strings.size() - 2)){
            v.setBackground(mContext.getResources().getDrawable(R.drawable.my_tabbar_bg));
        }else {
            v.setBackground(mContext.getResources().getDrawable(R.drawable.my_tabbar_bg_right));
        }
    }


    //定义点击接口
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String str1, int poi);
    }

}
