package com.hrms.manage.gwxq;

import com.hrms.manage.permission.UserPermission;
import com.hrms.table.Gzdw;
import com.hrms.table.Jbxx;
import com.hrms.table.Xl;
import com.hrms.table.Xqgw;
import com.hrms.table.Xw;
import com.hrms.table.Yh;
import com.hrms.util.ComboboxUtil;
import com.hrms.util.Util;
import com.jplus.json.EasyUiJson;
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
 * @author GuoLiang
 */
@WebServlet(name = "GwxqAction", urlPatterns = {"/gwxq/GwxqAction.jsp"})
public class GwxqAction extends HttpServlet {

    /**
     * 下面是模式关键字 可以自行删除和增加自定义模式，关键字一定要大写 默认模式为OTHER=0,所以OTHER不能删除
     */
    public final static int OTHER = 0;//其它
    public final static int SHOWLIST = 1;//显示岗位需求列表
    public final static int ADDONE = 2;//添加一条信息
    public final static int EDITONE = 3;//修改一条信息
    public final static int DELETESELECT = 4;//删除被选中的多条信息
    public final static int SHOWCOMBOBOX = 5;//显示所有combobox中的元素
    public final static int BAIDUINPUT = 6;//百度输入
    public final static int GETGZDW = 7;

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
                    Logger.getLogger(GwxqAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GwxqAction.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case SHOWLIST:
                showList(request, response);
                break;
            case ADDONE:
                addone(request, response);
                break;
            case EDITONE:
                editone(request, response);
                break;
            case DELETESELECT:
                deleteselect(request, response);
                break;
            case SHOWCOMBOBOX:
                showcombobox(request, response);
                break;

            case BAIDUINPUT:
                getDwmcs(request, response);
                break;
            case GETGZDW:
                getGzdw(request, response);
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
     * 显示岗位需求列表
     *
     * @param request
     * @param response
     */
    private void showList(HttpServletRequest request, HttpServletResponse response) {
        Xqgw xqgw = new Xqgw();
        DatabaseAccess db = new DatabaseAccess();
        EasyMapsManager em = new EasyMapsManager(db);
        String sql = "SELECT `xqgw`.`xqgwid`, `xqgw`.`dwid`, `xqgw`.`fbsj`, `xqgw`.`xqgw`, `xqgw`.`zy`, `xqgw`.`xlbm`, `xqgw`.`xwbm`, `xqgw`.`rs`, `xqgw`.`yjfs`, `xqgw`.`gwyq`,  `xqgw`.`dy`, `xl`.`xlmc`, `xw`.`xwmc`FROM`xqgw` LEFT JOIN`xl` ON `xqgw`.`xlbm` = `xl`.`xlbm` LEFT JOIN`xw` ON `xqgw`.`xwbm` = `xw`.`xwbm`";
        EasyUiJson datagrid = new EasyUiJson(request);
        List list = em.executeQuery(sql, datagrid);
        db.close();//要先关闭数据库
        datagrid.putAll(list);
        ServletUtil.ajaxData(datagrid.toDataString(), response);
    }

    /**
     * 添加一条信息
     *
     * @param request
     * @param response
     */
    private void addone(HttpServletRequest request, HttpServletResponse response) {
        Xqgw xqgw = new Xqgw();
        Hyberbin hyb = new Hyberbin(xqgw);
        ServletUtil.loadByParams(request, xqgw, true);
        boolean insert = hyb.insert("xqgwid");     //这个是主键
        String notice = insert ? "添加成功" : "添加失败";
        ServletUtil.ajaxData(notice, response, "html");
    }

    /**
     * 修改一条信息
     *
     * @param request
     * @param response
     */
    private void editone(HttpServletRequest request, HttpServletResponse response) {
        String xqgwid_s = request.getParameter("xqgwid-hidden");
        Xqgw xqgw = new Xqgw();
        Hyberbin hyb = new Hyberbin(xqgw);
        ServletUtil.loadByParams(request, xqgw, true);
        System.out.println(xqgwid_s);
        int xqgwid = 0;
        try {
            xqgwid = Integer.parseInt(xqgwid_s);
        } catch (Exception e) {
            System.out.println("人员id有误");//抛到运行时的错误
        }
        xqgw.setXqgwid(xqgwid);
        boolean update = hyb.updateByKey("xqgwid");     //这个是主键
        String notice = update ? "修改成功" : "修改失败";
        ServletUtil.ajaxData(notice, response, "html");
    }

    /**
     * 显示组合框的值
     *
     * @param request
     * @param response
     */
    private void showcombobox(HttpServletRequest request, HttpServletResponse response) {
        Hyberbin hyb = null;
        Connection conn = DatabaseINI.getDatabase().getConn();
        hyb = new Hyberbin(new Xl(), conn);//学历表
        List listxl = hyb.showAll();
        hyb = new Hyberbin(new Xw(), conn);//学位表
        List listxw = hyb.showAll();
        hyb.reallyClose();
        ServletUtil.ajaxData(ComboboxUtil.createBatchComboJSON(listxl, listxw), response);
    }

    /**
     * 删除多条信息
     *
     * @param request
     * @param response
     */
    private void deleteselect(HttpServletRequest request, HttpServletResponse response) {
        Xqgw xqgw = new Xqgw();
        Hyberbin hyb = new Hyberbin(xqgw);
        String ids = request.getParameter("ids");//用于批量删除拼接的id字符串
        System.out.println(ids);
        ids = ids.substring(0, ids.length() - 1);
        String sql = " where xqgwid in(" + ids + ")";
        boolean b = hyb.dell(sql);
        String message = b ? "删除成功" : "删除失败";
        ServletUtil.ajaxData(message, response, "html");
    }

    /**
     * 取得单位名称
     *
     * @param request
     * @param response
     */
    //采用百度输入法(单位名称)
    private void getDwmcs(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = new DatabaseAccess();
        EasyMapsManager emm = new EasyMapsManager(dao);
        String dwmcpy = request.getParameter("input_id");
        System.out.println(dwmcpy);
        String json = "";
        if (!Util.isEmpty(dwmcpy)) {
            emm.setPreparedParameter(dwmcpy + "%");
            ArrayList<HashMap> list = emm.executeQuery("select dwmc from gzdw where hypy like ?");
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

    private void getGzdw(HttpServletRequest request, HttpServletResponse response) {
        Yh yh = (Yh) request.getSession().getAttribute("user");
        System.out.println(yh.getGzdwid());
        Gzdw gzdw = new Gzdw();
        gzdw = new Hyberbin(new Gzdw()).showOne("select * from gzdw where gzdwid=" + yh.getGzdwid());
        ServletUtil.ajaxData(gzdw.getDwmc(), response);
    }
}