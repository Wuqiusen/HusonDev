package com.example.huson.husondevandroid.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;

import com.example.huson.husondevandroid.utils.ClickUtil;
import com.example.huson.husondevandroid.utils.DebugLog;


public abstract class BaseActivity extends FragmentActivity {
    public BaseActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (ClickUtil.isFastDoubleClick()) {
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onStart() {
        super.onStart();
        DebugLog.d("BaseActivity onStart Invoke...");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        DebugLog.d("BaseActivity onRestart Invoke...");
    }
    @Override
    protected void onResume() {
        super.onResume();
        DebugLog.d("BaseActivity onResume Invoke...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        DebugLog.d("BaseActivity onPause Invoke...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        DebugLog.d("BaseActivity onStop Invoke...");
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        DebugLog.d("BaseActivity onLowMemory Invoke...");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DebugLog.d( "BaseActivity onBackPressed Invoke...");
    }

}
