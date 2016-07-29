package com.example.huson.husondevandroid.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huson.husondevandroid.R;


/**
 * A simple {@link } subclass.
 */
public abstract class BaseHeadFragment extends BaseFragment {

    private boolean autoCloseMenu = true;

    public View baseView;

    private RelativeLayout rel_contentArea;
    private Button btn_backButton;
    private RelativeLayout rel_headArea;
    private TextView btn_headTitle;

    private int curr_menu_index = -1;




    public BaseHeadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        baseView = inflater.inflate(R.layout.base_head, container, false);
        assignHeadViews(baseView);
        return baseView;
    }

    public View setContentView(View view) {
        rel_contentArea.addView(view);
        return baseView;
    }

    public void hideHeadArea() {
        rel_headArea.setVisibility(View.GONE);
    }

    public void showBackButton(OnClickListener listener) {

        btn_backButton.setOnClickListener(listener);
        btn_backButton.setVisibility(View.VISIBLE);
    }

    public void showTitle(String title) {
        btn_headTitle.setText(title);
        btn_headTitle.setVisibility(View.VISIBLE);
    }


    private void assignHeadViews(View view) {
        rel_contentArea = (RelativeLayout) view.findViewById(R.id.rel_base_contentArea);
        btn_backButton = (Button) view.findViewById(R.id.btn_base_head_back);
        btn_headTitle = (TextView) view.findViewById(R.id.tv_base_head_title);
        rel_headArea = (RelativeLayout) view.findViewById(R.id.rel_base_headArea);
        btn_backButton.setVisibility(View.GONE);
        btn_headTitle.setVisibility(View.GONE);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
