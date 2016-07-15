package com.example.huson.husondevandroid.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        if (mRequestHandles != null && mRequestHandles.size() > 0) {
//            for (int i = 0; i < mRequestHandles.size(); i++) {
//                RequestHandle requestHandle = mRequestHandles.get(i);
//                if (!(requestHandle == null || requestHandle.isFinished() || requestHandle.isCancelled())) {
//                    requestHandle.cancel(true);
//                }
//            }
//        }
    }


}
