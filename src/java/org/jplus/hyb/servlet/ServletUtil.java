/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jplus.hyb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class ServletUtil {

    /**
     * 设置模式
     *
     * @param request
     * @param servletName
     * @return
     */
    public static int setModel(String mode, HttpServlet servletName) {
        int event = 0;
        if (mode == null) {//模式关键字判空
            mode = "OTHER";
        }
        mode = mode.toUpperCase();//模式关键字转换为大写
        try {//通过模式关键字去匹配成员变量，并获取成员变量的值
            event = (Integer) servletName.getClass().getDeclaredField(mode).get(0);
        } catch (Exception ex) {
            event = 0;
        }
        return event;
    }

    /**
     * 获得所有表单数据
     *
     * @param formbean
     * @return 返回为空的字段
     */
    public static List<String> loadByParams(HttpServletRequest request, Object formbean, boolean spaceIsNull) {
        return DataGet.loadByParams(request, formbean, spaceIsNull);
    }

    /**
     * 获得所有表单数据 不建议使用，因为可能不是所有字段都在表单中
     *
     * @param formbean
     * @return 返回为空的字段
     */
    public static List<String> loadByBean(HttpServletRequest request, Object formbean, boolean spaceIsNull) {
        return DataGet.loadByBean(request, formbean, spaceIsNull);
    }

    /**
     * 获得所有表单数据
     *
     * @param formbean POJO类数组
     * @return
     */
    public static Object[] loadByParams(HttpServletRequest request, Object[] formbean, boolean spaceIsNull) {
        DataGet.loadByParams(request, formbean, spaceIsNull);
        return formbean;
    }

    /**
     * 异步提交数据
     *
     * @param json json字符串
     * @param response http响应
     */
    public static void ajaxData(String json, HttpServletResponse response) {
        //禁止json缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(ServletUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println(json);
        out.flush();
        out.close();
    }

    /**
     * 异步提交数据
     *
     * @param json json字符串
     * @param response http响应
     * @param text_format 通知客户端解析文本的格式
     */
    public static void ajaxData(String json, HttpServletResponse response, String text_format) {
        //禁止json缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");
        response.setContentType("text/" + text_format + ";charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(ServletUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println(json);
        out.flush();
        out.close();
    }

    /**
     *
     * @param json json字符串
     * @param response http响应
     * @param out
     */
    public static void ajaxData(String json, HttpServletResponse response, PrintWriter out) {
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");
        response.setContentType("text/json;charset=utf-8");
        out.write(json);
    }
}
