package com.example.huson.husondevandroid.mvp.view;

import com.example.huson.husondevandroid.mvp.modle.MainModel;

/**
 * 当前类注析：处理业务需要哪些方法
 * Created by huson on 2016/7/29.
 * 940762301@qq.com
 */
public interface MainView {

    void getDataSuccess(MainModel model);

    void getDataFail(String msg);

}
