package com.hrms.manage.permission;

import com.hrms.table.Yh;
import com.hrms.util.Util;
import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebFilter(filterName = "UserFilter", urlPatterns = {"/manage/*"})
public class UserFilter implements Filter {

    private static String basePath = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        UserProperties uproperties = UserProperties.getInstance();
        if (basePath == null) {
            basePath = req.getContextPath();
        }
        String url = req.getServletPath();
        //判断是否是无需权限的资源
        if ((url.lastIndexOf(".jsp") == -1) || url.startsWith(uproperties.getLoginUrl().substring(0, uproperties.getLoginUrl().lastIndexOf("/") + 1))) {
            chain.doFilter(request, response);
            return;
        }
        //判断是否登录
        Yh user = (Yh) Util.getSessionAttribute(req, "user");
        if (user == null) {
            res.sendRedirect(basePath + uproperties.getLoginUrl());
            return;
        }
        //已经登录,允许访问主页
        if (uproperties.getMainUrl().equals(url)) {
            chain.doFilter(request, response);
            return;
        }
        //判断是否有当前URL的访问权限，判断当前页面是否在权限列表中。
        List<String> umodule = uproperties.getUserModulelist();
        boolean havePermission = false;
        for (int i = 0, len = umodule.size(); i < len; i++) {
            if (url.startsWith(umodule.get(i))) {
                havePermission = true;
                break;
            }
        }
        if (!havePermission) {
            req.setAttribute("notice", "没有访问权限");
            req.getRequestDispatcher(uproperties.getLoginUrl()).forward(request, response);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
