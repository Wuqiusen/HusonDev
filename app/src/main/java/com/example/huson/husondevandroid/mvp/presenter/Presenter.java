package com.example.huson.husondevandroid.mvp.presenter;

/**
 * 当前类注析：
 * Created by huson on 2016/7/29.
 * 940762301@qq.com
 */
public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}
