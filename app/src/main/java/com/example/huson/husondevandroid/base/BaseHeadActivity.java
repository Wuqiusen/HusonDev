package com.example.huson.husondevandroid.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huson.husondevandroid.R;
import com.example.huson.husondevandroid.utils.ToastHelper;


public abstract class BaseHeadActivity extends BaseActivity {

    protected RelativeLayout rel_contentArea;
    private RelativeLayout rel_base_headArea;
    private Button btn_headRightButton;
    private Button btn_backButton;
    private TextView btn_headTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.base_head);
        assignViews();
    }


    private void assignViews() {
        rel_contentArea = (RelativeLayout) findViewById(R.id.rel_base_contentArea);
        rel_base_headArea = (RelativeLayout) findViewById(R.id.rel_base_headArea);
        btn_backButton = (Button) findViewById(R.id.btn_base_head_back);
        btn_headTitle = (TextView) findViewById(R.id.tv_base_head_title);
        btn_headRightButton = (Button) findViewById(R.id.btn_base_head_right);
        btn_backButton.setVisibility(View.GONE);
        btn_headTitle.setVisibility(View.GONE);
        btn_headRightButton.setVisibility(View.GONE);
    }

    @Override
    public void setContentView(int layoutResID) {
        View v = getLayoutInflater().inflate(layoutResID, rel_contentArea, false);
        setContentView(v);
        findView();
        initData();
        initView();
        initEvent();
    }

    public void hideHeadArea(){
        rel_base_headArea.setVisibility(View.GONE);
    }


    @Override
    public void setContentView(View view) {
        setContentView(view, view.getLayoutParams());
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        rel_contentArea.addView(view, params);

    }


    public void showBackButton(View.OnClickListener listener) {
        btn_backButton.setOnClickListener(listener);
        btn_backButton.setVisibility(View.VISIBLE);
    }
    public void showBackButton() {
        btn_backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_backButton.setVisibility(View.VISIBLE);
    }

    public void showTitle(String title) {
        btn_headTitle.setText(title);
        btn_headTitle.setVisibility(View.VISIBLE);
    }
    public void hideTitle(){
        btn_headTitle.setVisibility(View.GONE);
    }

    public void showRightButton(View.OnClickListener listener){
        btn_headRightButton.setOnClickListener(listener);
        btn_headRightButton.setVisibility(View.VISIBLE);
    }

    public boolean checkIsEmpty(String str, String toast){
        if (TextUtils.isEmpty(str)) {
            ToastHelper.showToast(toast, mContext);
            return true;
        }
        return false;
    }

    public View getView(){
        return rel_contentArea;
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 获取布局控件
     */
    protected abstract void findView();

    /**
     * 初始化View的一些数据
     */
    protected abstract void initView();

    /**
     * 设置点击监听
     */
    protected abstract void initEvent();
}
