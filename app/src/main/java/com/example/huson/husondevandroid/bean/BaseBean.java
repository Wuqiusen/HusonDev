package com.example.huson.husondevandroid.bean;

import java.util.List;

/**
 * 作者：CangJie on 2016/2/26 10:20
 * 邮箱：cangjie2016@gmail.com
 */
public class BaseBean<T> {
    public int returnCode;
    public int returnSize;
    public String returnInfo;
    public T returnData;
    public boolean error;
    public T results;
}
