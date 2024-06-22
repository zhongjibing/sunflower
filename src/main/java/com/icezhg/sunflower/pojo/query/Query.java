package com.icezhg.sunflower.pojo.query;


import com.icezhg.sunflower.util.SecurityUtil;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhongjibing on 2022/09/04.
 */
public interface Query {
    default Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("isRoot", SecurityUtil.isRootUser());
        map.put("isFuzzyQuery", isFuzzyQuery());
        addFieldValues(this.getClass(), map);

        Class<?> clazz = this.getClass().getSuperclass();
        while (clazz != null) {
            addFieldValues(clazz, map);
            clazz = clazz.getSuperclass();
        }

        return map;
    }

    private void addFieldValues(Class<?> clazz, Map<String, Object> map) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ReflectionUtils.makeAccessible(field);
            Object value = ReflectionUtils.getField(field, this);
            map.put(field.getName(), value);
        }
    }

    default boolean isFuzzyQuery() {
        return true;
    }
}
