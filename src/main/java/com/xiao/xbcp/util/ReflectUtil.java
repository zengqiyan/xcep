package com.xiao.xbcp.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 反射工具类，对反射field和method进行缓存
 * 
 * @author cobe
 * @date 2020/07/14
 */
@Slf4j
public class ReflectUtil {

    private static Map<String, Method> cachedMethodMap = new ConcurrentHashMap<>();
    private static Map<String, Field> cachedFieldMap = new ConcurrentHashMap<>();

    private ReflectUtil() {}

    public static boolean isPrimitive(Class<?> cls) {
        return cls.isPrimitive() || cls == String.class || cls == Boolean.class || cls == Character.class
                || Number.class.isAssignableFrom(cls) || Date.class.isAssignableFrom(cls);
    }

    public static boolean isPrimitives(Class<?> cls) {
        if (cls.isArray()) {
            return isPrimitive(cls.getComponentType());
        }
        return isPrimitive(cls);
    }

    public static WrapperMethod getMethod(Object instance, String methodName, Class<?>... parameterTypes) {
        // 这里不直接返回null，方便调用者不需要进行null判断，不影响实际效果
        // if (instance == null) {
        // return null;
        // }

        return new WrapperMethod(instance, methodName, parameterTypes);
    }

    public static Field getField(Object instance, String fieldName) {
        if (instance == null) {
            return null;
        }

        String cacheKey = instance.getClass().getCanonicalName() + "." + fieldName;
        Field field = cachedFieldMap.get(cacheKey);
        if (field == null) {
            try {
                field = instance.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);

                cachedFieldMap.put(cacheKey, field);
                log.info("cached reflected field: {}", cacheKey);
            } catch (Exception e) {
                log.error(e.getMessage());
                return null;
            }
        }
        return field;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(Object instance, String fieldName) {
        Field field = getField(instance, fieldName);

        try {
            return field == null ? null : (T)field.get(instance);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static boolean setFieldValue(Object instance, String fieldName, Object value) {
        Field field = getField(instance, fieldName);
        if (field == null) {
            return false;
        }

        try {
            field.set(instance, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    public static class WrapperMethod {
        private Object instance;
        private Method method;

        public WrapperMethod(Object instance, String methodName, Class<?>... parameterTypes) {
            this.instance = instance;

            if (instance == null) {
                return;
            }

            try {
                String cacheKey = instance.getClass().getCanonicalName() + "." + methodName;
                method = cachedMethodMap.get(cacheKey);
                if (method == null) {
                    method = instance.getClass().getMethod(methodName, parameterTypes);
                    method.setAccessible(true);
                    cachedMethodMap.put(cacheKey, method);
                    log.info("cached reflected method: {}", cacheKey);
                }
            } catch (NoSuchMethodException e) {
                log.error(e.getMessage());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

        @SuppressWarnings("unchecked")
        public <T> T invoke(Object... args) {
            if (instance == null || method == null) {
                return null;
            }

            try {
                return (T)method.invoke(instance, args);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return null;
            }
        }
    }

}
