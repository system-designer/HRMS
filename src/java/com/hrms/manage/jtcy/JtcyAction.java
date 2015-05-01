package com.hrms.manage.jtcy;

import com.jplus.json.EasyUiJson;
import com.jplus.json.JSONArray;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jplus.hyb.servlet.ServletUtil;
import org.jplus.yydbgai.DatabaseAccess;
import org.jplus.yydbgai.EasyMapsManager;

/**
 *
 * @author 寻影
 */
@WebServlet(name = "newJplusServlet", urlPatterns = {"/jtcy/manage/JtcyAction.jsp"})
public class JtcyAction extends HttpServlet {

    /**
     * 下面是模式关键字 可以自行删除和增加自定义模式，关键字一定要大写 默 认模式为OTHER=0,所以OTHER不能删除
     */
    public final static int OTHER = 0;//其它
    public final static int SHOWONE = 1;//显示人员id的信息
    public final static int SHOWLIST = 2;//根据ryid显示家庭成员列表
    public final static int ADD = 3;//添加一条家庭成员记录
    public final static int UPDATE = 4;//更新一条家庭成员记录
    public final static int DELETE = 5;//删除一条家庭成员记录
    public final static int COMBOBOX = 6;//政治面貌类别列表

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
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                } catch (ServletException ex) {
                    Logger.getLogger(JtcyAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(JtcyAction.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case SHOWONE:
                showone(request, response);
                break;
            case SHOWLIST:
                showJtcyList(request, response);
                break;
            case ADD:
                addJtcy(request, response);
                break;
            case UPDATE:
                updatetjcy(request, response);
                break;
            case DELETE:
                deleteJtcy(request, response);
                break;
            case COMBOBOX:
                getZzmmlb(request, response);
                break;
        }
    }

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
     * 显示ryid的家庭成员列表
     */
    private void showJtcyList(HttpServletRequest request,
            HttpServletResponse response) {
        DatabaseAccess dao = new DatabaseAccess();
        EasyMapsManager emm = new EasyMapsManager(dao);
        String ryid_s = request.getParameter("ryid");
        int ryid = 0;
        try {
            ryid = Integer.parseInt(ryid_s);
        } catch (Exception e) {
            System.out.println("人员ID不正确");
        }
        String sql = "SELECT `jtcy`.`cw`, `jtcy`.`xm`, `jtcy`.`gzdw`, `jtcy`.`zw`, `jtcy`.`csny`, `zzmm`.`zzmmmc`, `zzmm`.`zzmmbm`,`jtcy`.`jtcyid` FROM `jtcy` INNER JOIN `zzmm` ON `zzmm`.`zzmmbm` = `jtcy`.`zzmm` where ryid=? ";
        //将list转换为json字符串
        EasyUiJson datagrid = new EasyUiJson(request);
        emm.setPreparedParameter(ryid);
        List list = emm.executeQuery(sql, datagrid);
        dao.close();
        //异步提交
        datagrid.putAll(list);
        ServletUtil.ajaxData(datagrid.toDataString(), response);
    }

    /**
     * 显示ryid的个人基本信息
     */
    private void showone(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = new DatabaseAccess();
        EasyMapsManager emm = new EasyMapsManager(dao);
        String ryid_s = request.getParameter("ryid");
        int ryid = 0;
        try {
            ryid = Integer.parseInt(ryid_s);
        } catch (Exception e) {
            System.out.println("人员ID不正确");
        }
        String sql = "SELECT `gzdw`.`dwmc`, `jbxx`.`zjhm`, `jbxx`.`xm`, `jbxx`.`xb`, `jbxx`.`ryid` FROM `jbxx` INNER JOIN `gzdw` ON `jbxx`.`gzdwid` = `gzdw`.`gzdwid` where ryid=?";
        //将list转换为json字符串
        emm.setPreparedParameter(ryid);
        List list = emm.executeQuery(sql);
        dao.close();
        JSONArray json = new JSONArray(list);
        ServletUtil.ajaxData(json.toString(), response);
    }

    /**
     * combobox显示政治面貌的类别列表
     */
    private void getZzmmlb(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = new DatabaseAccess();
        EasyMapsManager emm = new EasyMapsManager(dao);
        String sql = "SELECT `zzmm`.`zzmmbm`, `zzmm`.`zzmmmc` FROM `zzmm`";
        //将list转换为json字符串
        List list = emm.executeQuery(sql);
        dao.close();
        //异步提交
        JSONArray json = new JSONArray(list);
        String jsonstr = json.toString();
        ServletUtil.ajaxData(jsonstr, response);
    }

    /**
     * 添加ryid的家庭成员
     */
    private void addJtcy(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = new DatabaseAccess();
        String ryid_s = request.getParameter("ryid");
        String cw = request.getParameter("cw");
        String gzdw = request.getParameter("gzdw");
        String csny = request.getParameter("csny");
        String zzmm = request.getParameter("zzmm");
        String cyxm = request.getParameter("cymz");
        String zw = request.getParameter("zw");
        int ryid = 0;
        try {
            ryid = Integer.parseInt(ryid_s);
        } catch (Exception e) {
            System.out.println("人员id出错");
        }
        String sql = "insert into jtcy (ryid,gzdw,cw,csny,zzmm,xm,zw) values (?,?,?,?,?,?,?)";
        dao.setPreparedParameter(ryid);
        dao.setPreparedParameter(gzdw);
        dao.setPreparedParameter(cw);
        dao.setPreparedParameter(csny);
        dao.setPreparedParameter(zzmm);
        dao.setPreparedParameter(cyxm);
        dao.setPreparedParameter(zw);
        boolean b = false;
        try {
            b = dao.executeUpdate(sql);
        } catch (Exception e) {
            dao.close();
            System.out.println("==执行出错");
        }
        dao.close();
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(ServletUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println(b ? "1" : "0");
        out.flush();
        out.close();
    }

    /**
     * 修改ryid的家庭成员
     */
    private void updatetjcy(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = new DatabaseAccess();
        String ryid_s = request.getParameter("ryid");
        String jtcyid_s = request.getParameter("jtcyid");
        String cw = request.getParameter("cw");
        String gzdw = request.getParameter("gzdw");
        String csny = request.getParameter("csny");
        String zzmm = request.getParameter("zzmm");
        String cyxm = request.getParameter("cymz");
        String zw = request.getParameter("zw");
        int ryid = 0;
        int jtcyid = 0;
        try {
            ryid = Integer.parseInt(ryid_s);
            jtcyid = Integer.parseInt(jtcyid_s);

        } catch (Exception e) {
            System.out.println("人员id出错");
        }
        String sql = "update jtcy set cw=?,gzdw=?,csny=?,zzmm=?,xm=?,zw=? where ryid=? and jtcyid=?";
        dao.setPreparedParameter(cw);
        dao.setPreparedParameter(gzdw);
        dao.setPreparedParameter(csny);
        dao.setPreparedParameter(zzmm);
        dao.setPreparedParameter(cyxm);
        dao.setPreparedParameter(zw);
        dao.setPreparedParameter(ryid);
        dao.setPreparedParameter(jtcyid);
        boolean b = false;
        try {
            b = dao.executeUpdate(sql);
        } catch (Exception e) {
            dao.close();
            System.out.println("==执行出错");
        }
        dao.close();
        String json = b ? "1" : "0";
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(ServletUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println(b ? "1" : "0");
        out.flush();
        out.close();
    }

    /**
     * 删除ryid的家庭成员
     */
    private void deleteJtcy(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = new DatabaseAccess();
        String ryid_s = request.getParameter("ryid");
        String jtcyid_s = request.getParameter("jtcyid");
        int ryid = 0;
        int jtcyid = 0;
        try {
            ryid = Integer.parseInt(ryid_s);
            jtcyid = Integer.parseInt(jtcyid_s);

        } catch (Exception e) {
            System.out.println("人员id出错");
        }
        String sql = "delete from jtcy where ryid=? and jtcyid=?";
        dao.setPreparedParameter(ryid);
        dao.setPreparedParameter(jtcyid);
        boolean b = false;
        try {
            b = dao.executeUpdate(sql);
        } catch (Exception e) {
            dao.close();
            System.out.println("==执行出错");
        }
        dao.close();
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(ServletUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println(b ? "1" : "0");
        out.flush();
        out.close();
    }
}
