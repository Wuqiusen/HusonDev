package com.example.mylibrary.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.R;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class BaseHeadActivity extends AppCompatActivity {
    public Activity mActivity;
    protected RelativeLayout rel_contentArea;
    private ImageButton ibtn_headLeftImageButton;
    private ImageButton ibtn_headRightImageButton;
    private Button btn_headRightButton;
    private Button btn_backButton;
    private TextView btn_headTitle;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        mActivity = this;
    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
        mActivity = this;
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
        mActivity = this;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.include_head);
        assignViews();
    }

    private void assignViews() {
        rel_contentArea = (RelativeLayout) findViewById(R.id.rel_base_contentArea);
        ibtn_headLeftImageButton = (ImageButton) findViewById(R.id.btn_base_head_left_imgbutton);
        ibtn_headRightImageButton = (ImageButton) findViewById(R.id.btn_base_head_right_imgbutton);
        btn_backButton = (Button) findViewById(R.id.btn_base_head_back_btn);
        btn_headTitle = (TextView) findViewById(R.id.tv_base_head_title);
        btn_headRightButton = (Button) findViewById(R.id.btn_base_head_right_button);
        ibtn_headLeftImageButton.setVisibility(View.GONE);
        btn_headTitle.setVisibility(View.GONE);
        btn_headRightButton.setVisibility(View.GONE);
        ibtn_headRightImageButton.setVisibility(View.GONE);
    }


    @Override
    protected void onDestroy() {
        onUnsubscribe();
        super.onDestroy();
    }

    private CompositeSubscription mCompositeSubscription;

    public void onUnsubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();//取消注册，以避免内存泄露
        }
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public void showBackButton() {
        this.showBackButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_backButton.setVisibility(View.VISIBLE);
    }
    public void showBackButton(View.OnClickListener listener) {
        btn_backButton.setOnClickListener(listener);
        btn_backButton.setVisibility(View.VISIBLE);
    }

    public void showTitle(String title) {
        btn_headTitle.setText(title);
        btn_headTitle.setVisibility(View.VISIBLE);
    }

    public void showRightImageButton(View.OnClickListener listener) {
        ibtn_headRightImageButton.setOnClickListener(listener);
        ibtn_headRightImageButton.setVisibility(View.VISIBLE);
    }
    public void setRightIBtnResource(int iBtnResource){
        ibtn_headRightImageButton.setImageResource(iBtnResource);
    }
    public void setRightIBtnEnable(boolean enable){
        ibtn_headRightImageButton.setEnabled(enable);
    }


    public ProgressDialog progressDialog;

    public ProgressDialog showProgressDialog() {
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setMessage("加载中");
        progressDialog.show();
        return progressDialog;
    }

    public ProgressDialog showProgressDialog(String message) {
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setMessage(message);
        progressDialog.show();
        return progressDialog;
    }

    public ProgressDialog showProgressDialog(CharSequence message) {
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setMessage(message);
        progressDialog.show();
        return progressDialog;
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            // progressDialog.hide();会导致android.view.WindowLeaked
            progressDialog.dismiss();
        }
    }

    public void toastShow(int resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    public void toastShow(String resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }


}
