package com.hrms.manage.login;

import com.hrms.table.Yh;
import com.hrms.util.MD5;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jplus.hyb.database.Hyberbin;
import org.jplus.hyb.servlet.ServletUtil;

/**
 *
 * @author PBH
 */
@WebServlet(name = "LoginAction", urlPatterns = {"/LoginAction.jsp"})
public class LoginAction extends HttpServlet {

    /**
     * 下面是模式关键字 可以自行删除和增加自定义模式，关键字一定要大写 默认模式为OTHER=0,所以OTHER不能删除
     */
    public final static int OTHER = 0;//其它
    public final static int LOGIN = 1;//登录
    public final static int LOGINOUT = 2;//退出登录

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        excute(ServletUtil.setModel(request.getParameter("mode"), this), request, response);
    }

    /**
     * 主执行方法
     *
     * @param event 方法ID
     */
    private void excute(int event, HttpServletRequest request, HttpServletResponse response) {
        switch (event) {
            case OTHER:
                try {
                    request.getRequestDispatcher("/js_study/manage/rcxxgl/login.jsp").forward(request, response);
                } catch (ServletException ex) {
                    Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case LOGIN:
                login(request, response);
                break;
            case LOGINOUT:
                loginOut(request, response);
                break;
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * 登录
     */
    private void login(HttpServletRequest request, HttpServletResponse response) {
        Hyberbin hyb = null;
        try {
            String message = "";
            String code = request.getParameter("code");
            HttpSession session = request.getSession(true);
            if (!code.equals(session.getAttribute("code").toString())) {
                message = "验证码错误";
                ServletUtil.ajaxData("{\"notice\":\"" + message + "\"}", response);
                return;
            }
            Yh yh = new Yh();
            hyb = new Hyberbin(yh, true);
            String username = request.getParameter("adm");
            String mm = request.getParameter("pass");
            hyb.addParmeter(username);
            yh = hyb.showOne("select * from yh where username=?");
            if (yh.getUserid() == null) {
                message = "该用户不存在";
                hyb.reallyClose();
                ServletUtil.ajaxData("{\"notice\":\"" + message + "\"}", response);
                return;
            } else {
                hyb.clearParmeter();
                hyb.addParmeter(username);
                hyb.addParmeter(MD5.md5s(mm));
                yh = hyb.showOne("select * from yh where username=? and mm=?");
                if (yh.getUserid() == null) {
                    message = "密码错误，请重试";
                    hyb.reallyClose();
                    hyb = null;
                    ServletUtil.ajaxData("{\"notice\":\"" + message + "\"}", response);
                    return;
                } else {
                    message = "SUCCESS";
                    session.setAttribute("user", yh);
                    session.setMaxInactiveInterval(3600);//表示session生命期1小时
                    ServletUtil.ajaxData("{\"notice\":\"" + message + "\"}", response);
                }
            }
        } catch (Exception e) {
        } finally {
            if (hyb != null) {
                hyb.reallyClose();
                hyb = null;
            }
        }
    }

    /**
     * 退出登录
     *
     * @param request
     * @param response
     */
    private void loginOut(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().removeAttribute("yh");
            request.getRequestDispatcher("/manage/login/login.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
