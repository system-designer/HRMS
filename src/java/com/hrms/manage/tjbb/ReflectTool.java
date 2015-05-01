/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrms.manage.tjbb;

import java.lang.reflect.Method;

/**
 * 反射工具
 *
 * @author evance
 */
public class ReflectTool {

    /**
     * 实体的setXXX方法
     *
     * @param name 成员变量名
     * @return
     */
    public static String set(String name) {
        return "set" + (name.charAt(0) + "").toUpperCase() + name.substring(1);//set+变量名的第一个字母大写
    }

    /**
     * 实体的getXXX方法
     *
     * @param name 成员变量名
     * @return
     */
    public static String get(String name) {
        return "get" + (name.charAt(0) + "").toUpperCase() + name.substring(1);//get+变量名的第一个字母大写
    }

    /**
     * 将读出来的行放到实体类中
     *
     * @param name 表头关键词
     * @param type 字段类型
     * @param name 字段名
     * @param value 值
     */
    public static void setBean(Object table, Class<?> type, String name, Object value) throws Exception {
        Method method = table.getClass().getMethod(set(name), type); //取得set方法
        method.invoke(table, value);//调用实体类的setXXX方法
    }
}
