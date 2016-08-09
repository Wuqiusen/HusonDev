package com.example.mylibrary.http.subscriber;

/**
 * 当前类注析：
 * Created by huson on 2016/7/29.
 * 940762301@qq.com
 */
public interface ApiCallback<T> {

    void onSuccess(T model);

    void onFailure(int code, String msg);

    void onCompleted();

}
