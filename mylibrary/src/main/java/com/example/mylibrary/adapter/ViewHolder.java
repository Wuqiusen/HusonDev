package com.example.mylibrary.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * 作者：CangJie
 * 邮箱：cangjie2016@gmail.com
 */
public final class ViewHolder {

    private final Context mContext;
    private final SparseArray<View> mViews;
    private final View mConvertView;
    private final int mPosition;

    protected ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        mContext = context;
        mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(layoutId, this);
        mPosition = position;
    }

    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null)
            return new ViewHolder(context, parent, layoutId, position);
        return (ViewHolder) convertView.getTag(layoutId);
    }

    public <T extends View> T findViewById(int viewId) {
        return getView(viewId);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    public int getPosition() {
        return mPosition;
    }

    public ViewHolder setText(int viewId, CharSequence text) {
        TextView view = getView(viewId);
        if (view != null && text != null)
            view.setText(text);
        return this;
    }

    public String getText(int viewId) {
        TextView view = getView(viewId);
        if (view != null) {
            return view.getText().toString();
        }
        return "";
    }

    public ViewHolder setTextColor(int viewId, int color) {
        TextView view = getView(viewId);
        if (view != null)
            view.setTextColor(color);
        return this;
    }

    public ViewHolder setTextColorResource(int viewId, int colorRes) {
        TextView view = getView(viewId);
        if (view != null)
            view.setTextColor(mContext.getResources().getColor(colorRes));
        return this;
    }

    public ViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        if (view != null)
            view.setBackgroundColor(color);
        return this;
    }

    public ViewHolder setBackgroundResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        if (view != null)
            view.setBackgroundResource(resId);
        return this;
    }

    public ViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        if (view != null)
            view.setImageDrawable(drawable);
        return this;
    }

    public ViewHolder setImageResource(int viewId, int imageRes) {
        ImageView view = getView(viewId);
        if (view != null)
            view.setImageResource(imageRes);
        return this;
    }

    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        if (view != null)
            view.setImageBitmap(bitmap);
        return this;
    }

    public ViewHolder setAlpha(int viewId, float value) {
        View view = getView(viewId);
        if (view == null) return this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            view.setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            view.startAnimation(alpha);
        }
        return this;
    }

    public ViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        if (view != null)
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        if (view != null)
            view.setOnClickListener(listener);
        return this;
    }

    public ViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        if (view != null)
            view.setOnLongClickListener(listener);
        return this;
    }

    public ViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        if (view != null)
            view.setTag(tag);
        return this;
    }

    public ViewHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        if (view != null)
            view.setTag(key, tag);
        return this;
    }

    public ViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = getView(viewId);
        if (view != null)
            view.setChecked(checked);
        return this;
    }
}