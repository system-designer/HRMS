package org.jplus.hyb.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *获得session中的权限值
 * @author hyber-bin
 */
public class GetAdm {

    final static int MANAGEERPOWER = -2;//管理员权限

    /**
     * 从session中获得权限值
     *
     * @param request
     * @return 权限值
     */
    public static int GetAdm(HttpServletRequest request) {
        int adm = -1;
        HttpSession session = request.getSession(true);
        try {
            adm = (Integer) session.getAttribute("adm");
        } catch (Exception e) {
            session.setAttribute("username", "游客");
            session.removeAttribute("adm");
            session.setAttribute("adm", -1);
        }
        return adm;
    }

    /**
     * 设置权限值
     *
     * @param request
     * @param adm  
     */
    public static void setAdm(HttpServletRequest request, int adm) {
        HttpSession session = request.getSession(true);
        session.setAttribute("adm", adm);
    }
    /**
     * 获得图片验证码
     * @param request
     * @param codeName 验证码表单名 
     */
    public static boolean getCode(HttpServletRequest request,String codeName){
        HttpSession session = request.getSession(true);
        String scode = (String)session.getAttribute("code");
        String code=request.getParameter("code");
        session.removeAttribute("code");
        if(code==null||scode==null||!code.equals(scode)){
            return false;
        }
        return true;
    }
}
