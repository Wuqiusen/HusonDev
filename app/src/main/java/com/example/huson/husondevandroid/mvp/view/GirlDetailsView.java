package com.example.huson.husondevandroid.mvp.view;

import com.example.huson.husondevandroid.bean.GirlBean;

import java.util.List;

/**
 * 当前类注析：处理业务需要哪些方法
 * Created by huson on 2016/7/29.
 * 940762301@qq.com
 */
public interface GirlDetailsView {
    void setData(List<GirlBean.ResultsEntity> datas, int poi);

}
