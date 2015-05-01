package com.hrms.manage.rcxx;

import com.hrms.table.Jbxx;
import com.jplus.json.EasyUiJson;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jplus.hyb.database.Hyberbin;
import org.jplus.hyb.servlet.ServletUtil;
import org.jplus.yydbgai.DatabaseAccess;
import org.jplus.yydbgai.EasyMapsManager;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "RcxxAction", urlPatterns = {"/rcxx/manage/RcxxAction.jsp"})
public class RcxxAction extends HttpServlet {

    /**
     * 下面是模式关键字 可以自行删除和增加自定义模式，关键字一定要大写 默认模式为OTHER=0,所以OTHER不能删除
     */
    public final static int OTHER = 0;//其它
    public final static int SHOWONE = 1;//显示单例
    public final static int SHOWDWLIST = 2;//显示单位列表
    public final static int SHOWRYLIST = 3;//显示人员列表
    public final static int ADDRY = 4;//添加记录
    public final static int UPDATERY = 5;//更新记录
    public final static int DELETE = 6;//删除记录

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        excute(ServletUtil.setModel(request.getParameter("mode"), this),request, response);
    }

    /**
     * 主执行方法
     *
     * @param event 方法ID
     */
    private void excute(int event, HttpServletRequest request, HttpServletResponse response) {
        switch (event) {
            case SHOWDWLIST:
                showdwList(request,response);
                break;
            case SHOWRYLIST:
                showryList(request,response);
                break;
            case ADDRY:
                addry(request,response);
                break;
            case UPDATERY:
                updatery(request,response);
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
     * 显示单位信息列表
     */
    private void showdwList(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = new DatabaseAccess();
        EasyMapsManager emm = new EasyMapsManager(dao);
        String sql = "SELECT"
                + " `gzdw`.`dwmc`, `gzdw`.`gzdwid`, `gzdw`.`dwxzbm`, `dwxz`.`dwxzmc`,"
                + " `gzdw`.`dwjbbm`, `dwjb`.`dwjbmc`, `ssxt`.`ssxtmc`, `gzdw`.`ssxtbm`"
                + " FROM"
                + " `gzdw` LEFT JOIN"
                + " `dwxz` ON `gzdw`.`dwxzbm` = `dwxz`.`dwxzbm` LEFT JOIN"
                + " `dwjb` ON `gzdw`.`dwjbbm` = `dwjb`.`dwjbbm` LEFT JOIN"
                + " `ssxt` ON `gzdw`.`ssxtbm` = `ssxt`.`ssxtbm`";
        //将list转换为json字符串
        EasyUiJson datagrid = new EasyUiJson(request);
        List dwList = emm.executeQuery(sql, datagrid);
        dao.close();
        //异步提交
        datagrid.putAll(dwList);
        ServletUtil.ajaxData(datagrid.toDataString(), response);
    }

    /**
     * 显示人员信息列表
     */
    private void showryList(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = new DatabaseAccess();
        EasyMapsManager emm = new EasyMapsManager(dao);
        String sql = "SELECT"
                + " `ncsyrc`.`ncsyrcid`, `jtcy`.`jtcyid`, `dzrc`.`dzrcid`, `cyrc`.`cyrcid`,"
                + " `shgzrc`.`shgzrcid`, `zyjsrc`.`zyjsrcid`, `qyglrc`.`qyglrcid`,"
                + " `jnrc`.`jnrcid`, `jbxx`.*"
                + " FROM"
                + " `jbxx` LEFT JOIN"
                + " `jtcy` ON `jtcy`.`ryid` = `jbxx`.`ryid` LEFT JOIN"
                + " `ncsyrc` ON `ncsyrc`.`ryid` = `jbxx`.`ryid` LEFT JOIN"
                + " `cyrc` ON `cyrc`.`ryid` = `jbxx`.`ryid` LEFT JOIN"
                + " `dzrc` ON `dzrc`.`ryid` = `jbxx`.`ryid` LEFT JOIN"
                + " `shgzrc` ON `shgzrc`.`ryid` = `jbxx`.`ryid` LEFT JOIN"
                + " `zyjsrc` ON `zyjsrc`.`ryid` = `jbxx`.`ryid` LEFT JOIN"
                + " `qyglrc` ON `qyglrc`.`ryid` = `jbxx`.`ryid` LEFT JOIN"
                + " `jnrc` ON `jnrc`.`ryid` = `jbxx`.`ryid`";
        //将list转换为json字符串
        EasyUiJson datagrid = new EasyUiJson(request);
        List dwList = emm.executeQuery(sql, datagrid);
        dao.close();
        //异步提交
        datagrid.putAll(dwList);
        ServletUtil.ajaxData(datagrid.toDataString(), response);
    }

    /**
     * 添加人员信息
     */
    private void addry(HttpServletRequest request, HttpServletResponse response) {
        Jbxx jbxx = new Jbxx();
        Hyberbin hyb = new Hyberbin(jbxx);
        ServletUtil.loadByBean(request, jbxx, true);
        boolean insert = hyb.insert("ryid");
        String notice = insert ? "添加成功" : "添加失败";
        ServletUtil.ajaxData(notice, response);
    }

    /**
     * 修改人员基本信息
     */
    private void updatery(HttpServletRequest request, HttpServletResponse response) {
        Jbxx jbxx = new Jbxx();
        Hyberbin hyb = new Hyberbin(jbxx);
        ServletUtil.loadByBean(request, jbxx, true);
        boolean update = hyb.update("ryid");
        String notice = update ? "修改成功" : "修改失败";
        ServletUtil.ajaxData(notice, response);
    }
}
