package org.jplus.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 对任意类中的成员变量进行set或者get操作
 * @author hyberbin
 */
public class FieldUtil {

    /**
     * 实体的getXXX方法
     *
     * @param name 成员变量名
     * @return
     */
    public static String get(String name) {
        String get = "get" + (name.charAt(0) + "").toUpperCase() + name.substring(1);//get+变量名的第一个字母大写
        return get;
    }

    /**
     * 实体的setXXX方法
     *
     * @param name 成员变量名
     * @return
     */
    public static String set(String name) {
        return "set" + (name.charAt(0) + "").toUpperCase() + name.substring(1);//get+变量名的第一个字母大写
    }

    /**
     * 取得一个成员变量的值
     *
     * @param field 成员变量
     * @return
     */
    public static Object getFieldValue(Object tablebean, String fieldName) {
        if (tablebean==null||fieldName == null || fieldName.trim().equals("")) {
            return null;
        }
        try {
            Method method = tablebean.getClass().getMethod(get(fieldName), (Class[]) null);//取得get方法
            return method.invoke(tablebean, (Object[]) (Class[]) null);//调用实体类的getXXX方法
        } catch (NoSuchMethodException noSuchMethodException) {
            LoggerManage.logger.getLogger("没有这个方法：" + get(fieldName), noSuchMethodException);
        } catch (SecurityException securityException) {
        } catch (IllegalAccessException illegalAccessException) {
        } catch (IllegalArgumentException illegalArgumentException) {
        } catch (InvocationTargetException invocationTargetException) {
        }
        return null;
    }


    /**
     * 存入一个实体的成员变量值
     * @param field
     * @param value
     * @return
     */
    public static Object setFieldValue(Object tablebean, String fieldName, Object value) {
        if (value == null) {
            return tablebean;
        }
        try {
            Method method = tablebean.getClass().getMethod(set(fieldName), value.getClass());//取得set方法
            method.invoke(tablebean, value);//调用实体类的setXXX方法
        } catch (Exception ex) {
            LoggerManage.logger.getLogger("FieldUtil存入一个实体的成员变量值失败！", ex);
        }
        return tablebean;
    }

    /**
     * 把两个相同类型的对象复制成值也相同
     * @param src 源对象
     * @param dist 目的对象
     */
    public static void clone(Object src, Object dist) {
        if (src != null && dist != null && src.getClass().equals(dist.getClass())) {
            Field[] declaredFields = src.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                setFieldValue(dist, field.getName(), getFieldValue(src, field.getName()));
            }
        }
    }
}
