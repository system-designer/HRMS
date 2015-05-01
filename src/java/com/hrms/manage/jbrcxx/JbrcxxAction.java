package com.hrms.manage.jbrcxx;

import com.hrms.manage.permission.UserPermission;
import com.hrms.table.Jbxx;
import com.hrms.table.Jl;
import com.hrms.table.Xl;
import com.hrms.table.Xmfl;
import com.hrms.table.Xw;
import com.hrms.table.Yh;
import com.hrms.table.Zj;
import com.hrms.table.Zzmm;
import com.hrms.util.ComboboxUtil;
import com.hrms.util.Util;
import com.jplus.json.EasyUiJson;
import com.jplus.json.JSONException;
import com.jplus.json.JSONObject;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jplus.hyb.database.DatabaseINI;
import org.jplus.hyb.database.Hyberbin;
import org.jplus.hyb.servlet.ServletUtil;
import org.jplus.yydbgai.DatabaseAccess;
import org.jplus.yydbgai.EasyMapsManager;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "JbrcxxAction", urlPatterns = {"/manage/jbrcxx/JbrcxxAction.jsp"})
public class JbrcxxAction extends HttpServlet {

    /**
     * 下面是模式关键字 可以自行删除和增加自定义模式，关键字一定要大写 默认模式为OTHER=0,所以OTHER不能删除
     */
    public final static int OTHER = 0;//其它
    public final static int SHOWONERY = 1;//显示单例
    public final static int SHOWDWLIST = 2;//显示单位列表
    public final static int SHOWRYLIST = 3;//显示人员列表
    public final static int ADDRY = 4;//添加记录
    public final static int UPDATERY = 5;//更新记录
    public final static int DELETERY = 6;//删除记录
    public final static int SHOWCOMBOBOX = 7;//显示所有下拉框
    public final static int ADDJL = 8;//添加简历信息
    public final static int UPDATEJL = 9;//修改简历信息
    public final static int DELETEJL = 10;//删除简历信息
    public final static int BAIDUINPUT = 11;//百度输入

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
            case SHOWONERY:
                showOnery(request, response);
                break;
            case SHOWCOMBOBOX:
                showCombobox(request, response);
                break;
            case SHOWDWLIST:
                showDwlist(request, response);
                break;
            case SHOWRYLIST:
                showryList(request, response);
                break;
            case ADDRY:
                addry(request, response);
                break;
            case UPDATERY:
                updatery(request, response);
                break;
            case DELETERY:
                deletery(request, response);
                break;
            case ADDJL:
                addjl(request, response);
                break;
            case UPDATEJL:
                updatejl(request, response);
                break;
            case DELETEJL:
                deletejl(request, response);
                break;
            case BAIDUINPUT:
                getDwmcs(request, response);
                break;
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * 显示所有的combobox信息
     *
     * @param request
     * @param response
     */
    private void showCombobox(HttpServletRequest request, HttpServletResponse response) {
        Hyberbin hyb = null;
        Connection conn = DatabaseINI.getDatabase().getConn();
        hyb = new Hyberbin(new Zzmm(), conn);//政治面貌
        List listzzmm = hyb.showAll();
        hyb = new Hyberbin(new Xl(), conn);//学历表
        List listxl = hyb.showAll();
        hyb = new Hyberbin(new Xw(), conn);//学位表
        List listxw = hyb.showAll();
        hyb = new Hyberbin(new Zj(), conn);//职级
        List listzj = hyb.showAll();
        hyb = new Hyberbin(new Xmfl(), conn);//项目分类
        List listxmfl = hyb.showAll();
        hyb.reallyClose();
        ServletUtil.ajaxData(ComboboxUtil.createBatchComboJSON(listzzmm, listxl, listxw, listzj, listxmfl), response);
    }

    /**
     * 显示单位列表
     *
     * @param request
     * @param response
     */
    private void showDwlist(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = new DatabaseAccess();
        EasyMapsManager emm = new EasyMapsManager(dao);
        UserPermission upermission = new UserPermission(request);

        String sql = "SELECT `dwxz`.`dwxzmc`, `dwlb`.`dwlbmc`, `ssxt`.`ssxtmc`, `gzdw`.`gzdwid`,`gzdw`.`dwxzbm`, `gzdw`.`dwlbbm`, `gzdw`.`ssxtbm`, `gzdw`.`dwmc` FROM `gzdw` LEFT JOIN `dwxz` ON `gzdw`.`dwxzbm` = `dwxz`.`dwxzbm` LEFT JOIN `dwlb` ON `gzdw`.`dwlbbm` = `dwlb`.`dwlbbm` LEFT JOIN `ssxt` ON `gzdw`.`ssxtbm` = `ssxt`.`ssxtbm` where 1=1 ";
        //将list转换为json字符串
        if (upermission.isQuLevel()) {
            emm.setPreparedParameter(upermission.getSSdqbm());
            sql += " and gzdw.ssdqbm = ?";
        } else if (upermission.isQiYeLevel()) {
            emm.setPreparedParameter(upermission.getUser().getGzdwid());
            sql += " and gzdw.gzdwid = ?";
        }
        EasyUiJson datagrid = new EasyUiJson(request);
        String searchValue = request.getParameter("searchValue");
        String searchName = request.getParameter("searchName");
        String where = "";
        if (!Util.isEmpty(searchValue)) {
            where = " and " + searchName + " like ?";
            emm.setPreparedParameter("%" + searchValue + "%");
        }
        List dwList = emm.executeQuery(sql + where, datagrid);

        dao.close();
        //异步提交
        datagrid.putAll(dwList);
        ServletUtil.ajaxData(datagrid.toDataString(), response);
    }

    /**
     * 显示人员信息列表
     */
    private void showryList(HttpServletRequest request, HttpServletResponse response) {
        String searchValue = request.getParameter("searchValue");
        String searchName = request.getParameter("searchName");
        DatabaseAccess dao = new DatabaseAccess();
        EasyMapsManager emm = new EasyMapsManager(dao);
        String gzdwidstr = request.getParameter("gzdwid");
        int gzdwid = Integer.parseInt(gzdwidstr);
        String sql = "SELECT `jbxx`.* FROM `jbxx`";
        String where = " where gzdwid=?";
        emm.setPreparedParameter(gzdwid);
        if (!Util.isEmpty(searchValue)) {
            where += " and " + searchName + " like ?";
            emm.setPreparedParameter("%" + searchValue + "%");
        }
        //将list转换为json字符串
        EasyUiJson datagrid = new EasyUiJson(request);
        List ryList = emm.executeQuery(sql + where, datagrid);
        dao.close();
        //异步提交
        datagrid.putAll(ryList);
        ServletUtil.ajaxData(datagrid.toDataString(), response);
    }

    /**
     * 添加人员信息
     */
    private void addry(HttpServletRequest request, HttpServletResponse response) {
        Jbxx jbxx = new Jbxx();
        Hyberbin hyb = new Hyberbin(jbxx);
        ServletUtil.loadByParams(request, jbxx, true);
        jbxx.setZp(jbxx.getZjhm() + ".jpg");
        Yh yh = (Yh) request.getSession().getAttribute("yh");
        if (yh != null) {
            jbxx.setUsername(yh.getUsername());
        }
        boolean insert = hyb.insert("ryid");
        String notice = insert ? "添加成功" : "添加失败";
        ServletUtil.ajaxData(notice, response, "html");
    }

    /**
     * 修改人员基本信息
     */
    private void updatery(HttpServletRequest request, HttpServletResponse response) {
        Jbxx jbxx = new Jbxx();
        Hyberbin hyb = new Hyberbin(jbxx);
        ServletUtil.loadByParams(request, jbxx, true);
        //修改特殊人才信息
        if (Util.isEmpty(request.getParameter("sfdzrc"))) {
            jbxx.setSfdzrc(Boolean.FALSE);
        } else {
            jbxx.setSfdzrc(Boolean.TRUE);
        }
        if (Util.isEmpty(request.getParameter("sfglrc"))) {
            jbxx.setSfglrc(Boolean.FALSE);
        } else {
            jbxx.setSfglrc(Boolean.TRUE);
        }
        if (Util.isEmpty(request.getParameter("sfzjrc"))) {
            jbxx.setSfzjrc(Boolean.FALSE);
        } else {
            jbxx.setSfzjrc(Boolean.TRUE);
        }
        if (Util.isEmpty(request.getParameter("sfgjnrc"))) {
            jbxx.setSfgjnrc(Boolean.FALSE);
        } else {
            jbxx.setSfgjnrc(Boolean.TRUE);
        }
        if (Util.isEmpty(request.getParameter("sfncsyrc"))) {
            jbxx.setSfncsyrc(Boolean.FALSE);
        } else {
            jbxx.setSfncsyrc(Boolean.TRUE);
        }
        if (Util.isEmpty(request.getParameter("sfshgzrc"))) {
            jbxx.setSfshgzrc(Boolean.FALSE);
            jbxx.setXmflbm("");
        } else {
            jbxx.setSfshgzrc(Boolean.TRUE);
        }
        if (Util.isEmpty(request.getParameter("sfcyrc"))) {
            jbxx.setSfcyrc(Boolean.FALSE);
        } else {
            jbxx.setSfcyrc(Boolean.TRUE);
        }
        boolean update = hyb.updateByKey("ryid");
        String notice = update ? "修改成功" : "修改失败";
        ServletUtil.ajaxData(notice, response, "html");
    }

    /**
     * 删除人员
     *
     * @param request
     * @param response
     */
    private void deletery(HttpServletRequest request, HttpServletResponse response) {
        Jbxx jbxx = new Jbxx();
        Hyberbin hyb = new Hyberbin(jbxx);
        String ids = request.getParameter("ids");//用于批量删除拼接的id字符串
        ids = ids.substring(0, ids.length() - 1);
        String sql = " where ryid in(" + ids + ")";
        boolean b = hyb.dell(sql);
        String message = b ? "操作成功" : "操作失败";
        ServletUtil.ajaxData(message, response, "html");
    }

    /**
     * 显示单个人员信息
     *
     * @param request
     * @param response
     */
    private void showOnery(HttpServletRequest request, HttpServletResponse response) {
        String ryidstr = request.getParameter("ryid");
        int ryid = Integer.parseInt(ryidstr);
        DatabaseAccess dao = new DatabaseAccess();
        EasyMapsManager emm = new EasyMapsManager(dao);
        String sql = "SELECT `jbxx`.*, `jl`.`jcqk`, `jl`.`gzjl`, `jl`.`xxjl`, `jl`.`jlid`, `jl`.`username`,`zj`.`zjmc` FROM `jbxx` LEFT JOIN `jl` ON `jl`.`ryid` = `jbxx`.`ryid` LEFT JOIN `zj` ON `jbxx`.`zjbm` = `zj`.`zjbm` where jbxx.ryid=?";
        emm.setPreparedParameter(ryid);
        List list = emm.executeQuery(sql);
        dao.close();
        JSONObject json = new JSONObject();
        try {
            json.put("rcxx", list.get(0));
        } catch (JSONException ex) {
            Logger.getLogger(JbrcxxAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        ServletUtil.ajaxData(json.toString(), response);
    }

    /**
     * 添加简历
     *
     */
    private void addjl(HttpServletRequest request, HttpServletResponse response) {
        Jl jl = new Jl();
        ServletUtil.loadByBean(request, jl, true);
        Hyberbin hyb = new Hyberbin(jl);
        boolean b = hyb.insert("jlid");
        String message = b ? "操作成功" : "操作失败";
        ServletUtil.ajaxData(message, response, "html");
    }

    /**
     * 修改简历
     *
     * @param request
     * @param response
     */
    private void updatejl(HttpServletRequest request, HttpServletResponse response) {
        Jl jl = new Jl();
        Hyberbin hyb = new Hyberbin(jl);
        ServletUtil.loadByBean(request, jl, false);
        boolean update = hyb.updateByKey("ryid");
        String notice = update ? "修改成功" : "修改失败";
        ServletUtil.ajaxData(notice, response, "html");
    }

    /**
     * 删除简历
     *
     * @param request
     * @param response
     */
    private void deletejl(HttpServletRequest request, HttpServletResponse response) {
        Jl jl = new Jl();
        Hyberbin hyb = new Hyberbin(jl);
        ServletUtil.loadByParams(request, jl, true);
        boolean b = hyb.dellOneByKey("ryid");
        String notice = b ? "删除成功" : "删除失败";
        ServletUtil.ajaxData("{\"notice\":\"" + notice + "\"}", response);
    }

    /**
     * 得到单位名称
     *
     * @param request
     * @param response
     */
    private void getDwmcs(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = new DatabaseAccess();
        EasyMapsManager emm = new EasyMapsManager(dao);
        String dwmcpy = request.getParameter("input_id");
        String json = "";
        if (!Util.isEmpty(dwmcpy)) {
            Yh yh = new UserPermission(request).getUser();
            String sql = "SELECT `gzdw`.`dwmc` FROM `gzdw` LEFT JOIN `yh` ON `gzdw`.`gzdwid` = `yh`.`gzdwid`";
            String where = "";
            if (yh.getJb().equals("1")) {
                where = " WHERE `gzdw`.`hypy` like ?";
            } else {
                where = " WHERE (`gzdw`.`gzdwid`=? OR `gzdw`.`sjdwid`=?) AND `gzdw`.`hypy` like ?";
                emm.setPreparedParameter(yh.getGzdwid());
                emm.setPreparedParameter(yh.getGzdwid());
            }
            emm.setPreparedParameter(dwmcpy + "%");
            ArrayList<HashMap> list = emm.executeQuery(sql + where);
            json = "[";
            for (int i = 0; i < list.size(); i++) {
                json += "\"" + list.get(i).get("dwmc") + "\"" + ",";
            }
            json = json.substring(0, json.length() - 1 > 0 ? json.length() - 1 : 1);
            json += "]";
        }
        dao.close();
        ServletUtil.ajaxData(json, response);
    }
}
