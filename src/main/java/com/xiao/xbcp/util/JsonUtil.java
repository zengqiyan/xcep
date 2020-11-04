package com.xiao.xbcp.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * json util
 *
 * @author KyleZeng
 * @date 2020-11-04
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
public class JsonUtil {


    private static Gson gson = null;

    public static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        }
        return gson;
    }

    public static String toJson(Object src) {
        return getGson().toJson(src);
    }

    public static <T> T toObject(String json, Type typeOfT) {
        return getGson().fromJson(json, typeOfT);
    }

    public static <T> List<T> toList(String json, Type typeOfT) {
        return getGson().fromJson(json, new TypeToken<List<T>>() {
        }.getType());
    }
    public static <T> List<Map<String, T>> toListMaps(String json) {
        return getGson().fromJson(json, new TypeToken<List<Map<String, T>>>() {
        }.getType());
    }

    public static <T> Map<String, T> toMap(String json) {

        return getGson().fromJson(json, new TypeToken<Map<String, T>>() {
        }.getType());
    }
}
