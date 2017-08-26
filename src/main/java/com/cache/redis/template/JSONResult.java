package com.cache.redis.template;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @Description: RESTful接口返回值定义
 * @Author: yangbin
 * @Date: 下午 12:20 2017-8-26
 */
public class JSONResult {

    private static JSONObject json = new JSONObject(3);

    private static String jsonProvider(int code, String msg, Object data) {
        json.put("code", code);
        json.put("msg", msg);
        json.put("data", data);
        return JSON.toJSONString(json, SerializerFeature.WriteMapNullValue);
    }

    public static String callSuccess(Object obj) {
        return jsonProvider(1,"操作成功", obj);
    }

    public static String callFail(Object obj) {
        return jsonProvider(0,"操作失败", obj);
    }

    public static String callSignFail(Object obj) {
        return jsonProvider(-1,"身份验证失败", obj);
    }

    public static String callException(Object obj) {
        return jsonProvider(-2,"应用程序异常", obj);
    }

    public static String callInvalidParameter(Object obj) {
        return jsonProvider(-3,"非法参数", obj);
    }

    public static String callNoData(Object obj) {
        return jsonProvider(-4,"暂无数据", obj);
    }

}
