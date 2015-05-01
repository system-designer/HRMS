package org.jplus.hyb.servlet;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jplus.util.ConverString;
import org.jplus.util.FieldUtil;
import org.jplus.util.LoggerManage;

/**
 * 表单的数据获得
 *
 * @author hyber-bin
 * @version 1.2
 */
public class DataGet {

    /** 空值列表 用于获取表单但表单值为空的放在这里 在数据库更新的时候做为标识 */
    private List<String> nullList;
    
    private void setNullList(String fieldName) {
        if (nullList == null) {
            nullList = new ArrayList();
        }
        nullList.add(fieldName);
    }
    
    public List<String> getNullList() {
        return nullList;
    }

    /**
     * 类型转换，将字符类型转换为字段需要的类型
     *
     * @param field 成员变量
     * @param value 字符串值
     * @return
     */
    public static Object parse(Field field, String value) {
        return value == null ? null : ConverString.asType(field.getType(), value, null);
    }

    /**
     * 根据参数名获得成员变量名
     *
     * @param parameterName 参数名
     * @return
     */
    public static String getFieldName(String parameterName) {
        if (!parameterName.contains(".")) {
            return parameterName;
        } else {
            String rStr = parameterName.substring(parameterName.indexOf('.') + 1);
            if (rStr.contains(".")) {
                LoggerManage.logger.getLogger("参数名称不合法,只能有一个点！" + parameterName, null);
                return null;
            }
            return rStr;
        }
    }

    /**
     * 从request中获取表单数据到pojo类中
     * 返回null证明没有获得成功返回true证明参数可以正常转换返回false证明获得了空值
     * @param request HttpServletRequest
     * @param formbean POJO类
     * @param parameterName 参数名
     * @param spaceIsNull 空格是否当null
     */
    public static Boolean load(HttpServletRequest request, Object formbean, String parameterName, boolean spaceIsNull) {
        if (parameterName.equals("mode")) {
            return null;
        }
        try {
            String rValue = request.getParameter(parameterName);
            if (rValue != null && spaceIsNull && rValue.trim().equals("")) {
                return false;
            }
            parameterName = getFieldName(parameterName);
            Field field;
            try {
                field = formbean.getClass().getDeclaredField(parameterName);
            } catch (NoSuchFieldException noSuchFieldException) {
                return null;
            } catch (SecurityException securityException) {
                return null;
            }
            Object value = parse(field, rValue);            
            FieldUtil.setFieldValue(formbean, parameterName, value);
            if (value == null) {
                return false;
            }
        } catch (Exception ex) {
            LoggerManage.logger.getLogger("从表单获得参数失败！" + parameterName, ex);
        }
        return null;
    }

    /**
     * 将参数批量存入POJO类
     * @param request HttpServletRequest
     * @param formbean POJO类
     * @param spaceIsNull 空格是否当null
     */
    public static List<String> loadByParams(HttpServletRequest request, Object formbean, boolean spaceIsNull) {
        Enumeration names = request.getParameterNames();
        List<String> nullList = null;
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            Boolean load = load(request, formbean, name, spaceIsNull);
            if (load != null && !load) {
                if (nullList == null) {
                    nullList = new ArrayList<String>();
                }
                nullList.add(name);
            }
        }
        return nullList;
    }

    /**
     * 将参数批量存入多个POJO类
     * @param request HttpServletRequest
     * @param beans POJO类数组
     * @param spaceIsNull 空格是否当null
     */
    public static void loadByParams(HttpServletRequest request, Object[] beans, boolean spaceIsNull) {
        Enumeration names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            if (!name.contains(".")) {
                LoggerManage.logger.getLogger("参数名称不合法,多个对象时参数没有加点！" + name, null);
                continue;
            }
            String beanName = name.substring(0, name.indexOf("."));
            load(request, findBean(beans, beanName), name, spaceIsNull);
            
        }
    }

    /**
     * 按照POJO类成员变量信息来从表单存入数据
     * @param request HttpServletRequest
     * @param formbean POJO类
     * @param spaceIsNull 空格是否当null
     */
    public static List<String> loadByBean(HttpServletRequest request, Object formbean, boolean spaceIsNull) {
        Field[] fields = formbean.getClass().getDeclaredFields();
        List<String> nullList = null;
        for (Field field : fields) {
            Boolean load = load(request, formbean, field.getName(), spaceIsNull);
            if (load != null && !load) {
                if (nullList == null) {
                    nullList = new ArrayList<String>();
                }
                if (request.getParameterMap().containsKey(field.getName())) {//如果有关于这个字段的就加
                    nullList.add(field.getName());
                }
            }
        }
        return nullList;
    }

    /**
     * 根据名称找到类
     * @param beans POJO类数组
     * @param name 名称
     * @return
     */
    public static Object findBean(Object[] beans, String name) {
        for (Object o : beans) {
            if (o.getClass().getSimpleName().toLowerCase().equals(name)) {
                return o;
            }
        }
        return null;
    }
}
