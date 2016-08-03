package com.example.huson.husondevandroid.bean;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * 当前类注析：
 * Created by huson on 2016/8/3.
 * 940762301@qq.com
 */
public class GirlBean{
    public List<ResultsEntity> results;

    public static class ResultsEntity implements Serializable {
        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;
    }

}
