package com.wm.shoppingWeb.util.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @ClassName GsonUtil
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/8 10:25
 * @Version 1.0
 **/
public class GsonUtil {
    public static Gson getGson() {
        return new GsonBuilder().create();
    }
}
