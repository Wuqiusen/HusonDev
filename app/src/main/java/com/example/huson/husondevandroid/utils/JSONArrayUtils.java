package com.example.huson.husondevandroid.utils;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 当前类注析：合拼json工具类
 * Created by huson on 2016/6/4.
 * 940762301@qq.com
 */
public class JSONArrayUtils {
    public static JSONArray JSONArrayUtils(JSONArray mData, JSONArray array) {
        StringBuffer buffer = new StringBuffer();
        try {
            int len = mData.length();
            for (int i = 0; i < len; i++) {
                JSONObject obj1 = (JSONObject) mData.get(i);
                if (i == len - 1)
                    buffer.append(obj1.toString());
                else
                    buffer.append(obj1.toString()).append(",");
            }
            len = array.length();
            if (len > 0)
                buffer.append(",");
            for (int i = 0; i < len; i++) {
                JSONObject obj1 = (JSONObject) array.get(i);
                if (i == len - 1)
                    buffer.append(obj1.toString());
                else
                    buffer.append(obj1.toString()).append(",");
            }
            buffer.insert(0, "[").append("]");
            return new JSONArray(buffer.toString());
        } catch (Exception e) {
        }
        return null;
    }
}
