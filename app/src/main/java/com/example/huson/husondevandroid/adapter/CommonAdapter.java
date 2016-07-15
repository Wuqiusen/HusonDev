package com.example.huson.husondevandroid.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者：CangJie
 * 邮箱：cangjie2016@gmail.com
 */
public abstract class CommonAdapter<T> extends android.widget.BaseAdapter {

    protected final Context mContext;
    protected final int mLayoutId;
    protected List<T> mItems;

    public CommonAdapter(Context context) {
        this(context, -1, null);
    }

    public CommonAdapter(Context context, int layoutId) {
        this(context, layoutId, null);
    }

    public CommonAdapter(Context context, int layoutId, List<T> items) {
        mContext = context;
        mLayoutId = layoutId;
        mItems = items == null ? new ArrayList<T>() : new ArrayList<T>(items);
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder = getViewHolder(position, convertView, parent);
        convert(holder, position, getItem(position));
        return holder.getConvertView();
    }

    protected ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        if (mLayoutId == -1) throw new IllegalArgumentException("mLayoutId == -1");
        return ViewHolder.get(mContext, convertView, parent, mLayoutId, position);
    }

    protected abstract void convert(ViewHolder holder, int position, T item);

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public T getItem(int position) {
        if (position >= mItems.size()) return null;
        return mItems.get(position);
    }

    public List<T> getItems() {
        return mItems;
    }

    public void setItems(List<T> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(T item) {
        mItems.add(item);
        notifyDataSetChanged();
    }

    public void addAll(List<T> items) {
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void addAll(int index, List<T> items) {
        mItems.addAll(index, items);
        notifyDataSetChanged();
    }

    public void set(T oldItem, T newItem) {
        set(mItems.indexOf(oldItem), newItem);
    }

    public void set(int index, T item) {
        mItems.set(index, item);
        notifyDataSetChanged();
    }

    public void remove(T item) {
        mItems.remove(item);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        mItems.remove(index);
        notifyDataSetChanged();
    }

    public void replaceAll(List<T> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public boolean contains(T item) {
        return mItems.contains(item);
    }

    /**
     * Clear data list
     */
    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    public void setTVNullText(ViewHolder holder ,int id, String str){
        TextView tv = (TextView)holder.getView(id);
        if (TextUtils.isEmpty(str)){
            tv.setText("--");
        }else {
            tv.setText(str);
        }
    }
}
