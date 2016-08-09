package com.example.mylibrary.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.mylibrary.R;


/**
 * Created by huson on 2016/5/23.
 * 940762301@qq.com
 */
public class ProgressView extends ProgressDialog {
    private RelativeLayout rl_base_loading;
    public ProgressView(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_progress);
        rl_base_loading = (RelativeLayout) findViewById(R.id.rl_base_loading);

    }
}
