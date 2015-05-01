package com.hrms.manage.gwxq;

import com.hrms.table.Dwlb;
import com.hrms.table.Dwxz;
import com.hrms.table.Sshy;
import com.hrms.table.Ssxt;
import com.hrms.table.Xl;
import com.hrms.table.Xmfl;
import com.hrms.table.Xqgw;
import com.hrms.table.Xw;
import com.hrms.table.Yh;
import com.hrms.table.Zj;
import com.hrms.table.Zzmm;
import com.hrms.util.ComboboxUtil;
import com.hrms.util.Util;
import com.jplus.json.EasyUiJson;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
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
 * @author T420
 */
@WebServlet(name = "GwxqcxAction", urlPatterns = {"/GwxqcxAction.jsp"})
public class GwxqcxAction extends HttpServlet {

    /**
     * 下面是模式关键字 可以自行删除和增加自定义模式，关键字一定要大写 默认模式为OTHER=0,所以OTHER不能删除
     */
    public final static int OTHER = 0;//其它
    public final static int SHOWLIST = 1;//显示列表
    public final static int SHOWCOMBOBOX = 2;//显示所有下拉框
    public final static int BAIDUINPUT = 3;//这个是百度输入法

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
                    Logger.getLogger(GwxqcxAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GwxqcxAction.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case SHOWLIST:
                showList(request, response);
                break;
            case SHOWCOMBOBOX:
                showCombobox(request, response);
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
     * 显示列表
     */
    private void showList(HttpServletRequest request, HttpServletResponse response) {
        String dwxzbm = request.getParameter("dwxz");
        String dwlbbm = request.getParameter("dwlb");
        String sshybm = request.getParameter("sshy");
        String gzdwbm = request.getParameter("gzdw");
        String gwxq = request.getParameter("gwxq");
        DatabaseAccess dao = new DatabaseAccess();//数据库放在外面
        EasyMapsManager emm = new EasyMapsManager(dao);
        String sql = "SELECT `xqgw`.`fbsj`, `xqgw`.`xqgw`, `xqgw`.`zy`, `xqgw`.`rs`, `xqgw`.`yjfs`,`xqgw`.`gwyq`, `xqgw`.`dy`, `xl`.`xlmc`, `xw`.`xwmc`, `gzdw`.`dwmc` FROM `xqgw` LEFT JOIN `xl` ON `xqgw`.`xlbm` = `xl`.`xlbm` LEFT JOIN `xw` ON `xqgw`.`xwbm` = `xw`.`xwbm` LEFT JOIN `gzdw` ON `xqgw`.`dwid` = `gzdw`.`gzdwid`";
        String where = " where 1=1 ";
        if (!Util.isEmpty(dwxzbm) || !Util.isEmpty(dwlbbm) || !Util.isEmpty(sshybm) || !Util.isEmpty(gzdwbm) || !Util.isEmpty(gwxq)) {
            if (!Util.isEmpty(dwxzbm)) {
                where = where + " and `gzdw`.`dwxzbm`= ?";
                emm.setPreparedParameter(dwxzbm);
            }
            if (!Util.isEmpty(dwlbbm)) {
                where = where + " and `gzdw`.`dwlbbm` =?";
                emm.setPreparedParameter(dwlbbm);
            }
            if (!Util.isEmpty(sshybm)) {
                where = where + " and `gzdw`.`sshybm` =?";
                emm.setPreparedParameter(sshybm);
            }
            if (!Util.isEmpty(gzdwbm)) {
                where = where + " and `gzdw`.`dwmc` like ?";
                emm.setPreparedParameter("%"+gzdwbm+"%");
            }
            if (!Util.isEmpty(gwxq)) {
                where = where + " and `xqgw`.`xqgw` like ?";
                emm.setPreparedParameter("%"+gwxq+"%");
            }
        } else {
            where = "";
        }
        System.out.println(sql + where);
        EasyUiJson datagrid = new EasyUiJson(request);
        List ryList = emm.executeQuery(sql + where, datagrid);
        datagrid.putAll(ryList);
        ServletUtil.ajaxData(datagrid.toDataString(), response);

        dao.close();
    }

//这个是展示所有的combobox
    private void showCombobox(HttpServletRequest request, HttpServletResponse response) {
        Hyberbin hyb = null;
        Connection conn = DatabaseINI.getDatabase().getConn();
        hyb = new Hyberbin(new Dwxz(), conn);//单位性质
        List dwxzlist = hyb.showAll();
        hyb = new Hyberbin(new Dwlb(), conn);//单位级别
        List dwlblist = hyb.showAll();
        hyb = new Hyberbin(new Sshy(), conn);//所属行业
        List sshylist = hyb.showAll();
        hyb.reallyClose();
        
        ServletUtil.ajaxData(ComboboxUtil.createBatchComboJSON(dwxzlist, dwlblist, sshylist), response);
    }

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
    /**
     * 查询时候的列表
     *
     * @param request
     * @param response
     */
}
