package com.example.huson.husondevandroid.ui.fragment.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huson.husondevandroid.R;
import com.example.huson.husondevandroid.ui.activity.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fragment4 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_4, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.tvInNew)
    public void onClick() {
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }
}
