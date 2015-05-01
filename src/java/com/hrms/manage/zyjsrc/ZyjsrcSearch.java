package com.hrms.manage.zyjsrc;

import com.hrms.table.Dwlb;
import com.hrms.table.Dwxz;
import com.hrms.table.Ssxt;
import com.hrms.table.Xl;
import com.hrms.table.Xw;
import com.hrms.table.Yh;
import com.hrms.table.Zc;
import com.hrms.table.Zclb;
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
 * @author star
 */
@WebServlet(name = "ZyjsrcSearch", urlPatterns = {"/manage/tsrcxx/zyjsrccx/ZyjsrcSearch.jsp"})
public class ZyjsrcSearch extends HttpServlet {

    /**
     * 下面是模式关键字 可以自行删除和增加自定义模式，关键字一定要大写 默认模式为OTHER=0,所以OTHER不能删除
     */
    public final static int OTHER = 0;//其它
    public final static int SHOWCOMBOBOX = 1;//显示combobox
    public final static int SHOWSEARCH = 2;//搜索条件
    public final static int GETDWMC = 3;//得到他的工作单位

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
                    Logger.getLogger(ZyjsrcSearch.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ZyjsrcSearch.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case SHOWCOMBOBOX:
                showCombobox(request, response);
                break;
            case SHOWSEARCH:
                showSearch(request, response);
                break;
            case GETDWMC:
                getDwmc(request, response);
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
    private void showCombobox(HttpServletRequest request, HttpServletResponse response) {
        Dwxz dwxz = new Dwxz();//单位性质
        Dwlb dwlb = new Dwlb();//单位级别
        Ssxt ssxt = new Ssxt();//所属系统
        Zclb zclb = new Zclb();//职称类别
        Zc zc = new Zc();//技术职称
        Xl xl = new Xl();//学历
        Xw xw = new Xw();//学位
        Connection conn = DatabaseINI.getConnection();
        Hyberbin hyb = new Hyberbin(dwxz, conn);
        List list_dwxz = hyb.showList("select * from dwxz");
        hyb.changeTable(dwlb);
        List list_dwlb = hyb.showList("select * from dwlb");
        hyb.changeTable(ssxt);
        List list_ssxt = hyb.showList("select * from ssxt");
        hyb.changeTable(zclb);
        List list_zclb = hyb.showList("select * from zclb");
        hyb.changeTable(zc);
        List list_zc = hyb.showList("select * from zc");
        hyb.changeTable(xl);
        List list_xl = hyb.showList("select * from xl");
        hyb.changeTable(xw);
        List list_xw = hyb.showList("select * from xw");
        hyb.reallyClose();
        ComboboxUtil combobo = new ComboboxUtil();
        String json = combobo.createBatchComboJSON(list_dwxz, list_dwlb, list_ssxt, list_zclb, list_zc, list_xl, list_xw);
        ServletUtil.ajaxData(json, response);
    }

    private void showSearch(HttpServletRequest request, HttpServletResponse response) {
        String dwxzbm = request.getParameter("dwxz");
        String dwlbbm = request.getParameter("dwlb");
        String ssxtbm = request.getParameter("ssxt");
        String dwmc = request.getParameter("dwmc");
        String zclbbm = request.getParameter("zclb");
        String zc = request.getParameter("zc");
        String zcys = request.getParameter("zcys");
        String xlbm = request.getParameter("xl");
        String xlys = request.getParameter("xlys");
        String xwbm = request.getParameter("xw");
        String xwys = request.getParameter("xwys");
        String nl1 = request.getParameter("nl1");
        String nl2 = request.getParameter("nl2");
        int nl1_sj = 0;
        int nl2_sj = 0;
        try {
            nl1_sj = Integer.parseInt(nl1);
            nl2_sj = Integer.parseInt(nl2);
        } catch (NumberFormatException numberFormatException) {
//            numberFormatException.printStackTrace();
        }
        int year = new Date(System.currentTimeMillis()).getYear() + 1900;
        String sql = "SELECT"
                + " `gzdw`.`dwxzbm`, `gzdw`.`dwlbbm`, `gzdw`.`ssxtbm`, `jbxx`.`gzdwid`,"
                + " `jbxx`.`xwbm`, `jbxx`.`xlbm`, `jbxx`.`ryid`, `zyjsrc`.`zclbbm`,"
                + " `zyjsrc`.`jszcbm`, `gzdw`.`dwmc`, `zc`.`zcmc`, `zclb`.`zclbmc`,"
                + " (" + year + "-year(`jbxx`.`csrq`)) as age,`jbxx`.`xm`, `jbxx`.`xb`"
                + " FROM"
                + " `dwlb` INNER JOIN"
                + " `gzdw` ON `gzdw`.`dwlbbm` = `dwlb`.`dwlbbm` INNER JOIN"
                + " `jbxx` ON `jbxx`.`gzdwid` = `gzdw`.`gzdwid` INNER JOIN"
                + " `ssxt` ON `ssxt`.`ssxtbm` = `gzdw`.`ssxtbm` INNER JOIN"
                + " `dwxz` ON `gzdw`.`dwxzbm` = `dwxz`.`dwxzbm` INNER JOIN"
                + " `xw` ON `xw`.`xwbm` = `jbxx`.`xwbm` INNER JOIN"
                + " `xl` ON `xl`.`xlbm` = `jbxx`.`xlbm` INNER JOIN"
                + " `zyjsrc` ON `zyjsrc`.`ryid` = `jbxx`.`ryid` LEFT JOIN"
                + " `zclb` ON `zclb`.`zclbbm` = `zyjsrc`.`zclbbm` LEFT JOIN"
                + " `zc` ON `zc`.`zcbm` = `zyjsrc`.`jszcbm` where 1=1 ";
        System.out.println("zcys" + zcys);
        Empty em = new Empty();
        DatabaseAccess dao = new DatabaseAccess();
        EasyMapsManager easyMapsManager = new EasyMapsManager(dao);
        Yh yh = (Yh) request.getSession().getAttribute("user");
        String jb = yh.getJb();
        sql += jb.equals("1") ? "" : jb.equals("2") ? " and `gzdw`.`gzdwid`=" + yh.getGzdwid() + " or `gzdw`.`sjdwid`=" + yh.getGzdwid() : " and `gzdw`.`gzdwid`=" + yh.getGzdwid();
        if (Util.isEmpty(dwmc)) {
            sql += em.empth("dwxz.dwxzbm", dwxzbm, easyMapsManager) + em.empth("dwlb.dwlbbm", dwlbbm, easyMapsManager) + em.empth("ssxt.ssxtbm", ssxtbm, easyMapsManager) + em.empth("zclb.zclbbm", zclbbm, easyMapsManager)
                    + em.emys("zc.zcbm", zc, zcys, easyMapsManager) + em.emys("xl.xlbm", xlbm, xlys, easyMapsManager) + em.emys("xw.xwbm", xwbm, xwys, easyMapsManager);
            String sj = null;
            if (!Util.isEmpty(nl1) && !Util.isEmpty(nl2)) {
                sj = " and (" + year + "-year(jbxx.csrq)) between ? and ?";
                easyMapsManager.setPreparedParameter(nl1_sj);
                easyMapsManager.setPreparedParameter(nl2_sj);
            } else if (Util.isEmpty(nl1) && !Util.isEmpty(nl2)) {
                sj = " and (" + year + "-year(jbxx.csrq)) > ?";
                easyMapsManager.setPreparedParameter(nl1_sj);
            } else if (!Util.isEmpty(nl1) && Util.isEmpty(nl2)) {
                sj = " and (" + year + "-year(jbxx.csrq)) < ?";
                easyMapsManager.setPreparedParameter(nl2_sj);
            } else {
                sj = "";
            }
            sql += sj;
        } else {
            sql += "and gzdw.dwmc = ?";
            easyMapsManager.setPreparedParameter(dwmc);
        }
        System.out.println("sql===" + sql);
        EasyUiJson datagrid = new EasyUiJson(request);
        ArrayList list = easyMapsManager.executeQuery(sql, datagrid);
        datagrid.putAll(list);
        dao.close();
        ServletUtil.ajaxData(datagrid.toDataString(), response);
    }

    private void getDwmc(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = new DatabaseAccess();
        EasyMapsManager emm = new EasyMapsManager(dao);
        String dwmcpy = request.getParameter("input_id");
        System.out.println(dwmcpy);
        String json = "";
        String where = "";
        if (!Util.isEmpty(dwmcpy)) {
            emm.setPreparedParameter(dwmcpy + "%");
            String sql = "select dwmc from gzdw where hypy like ?";
            Yh yh = (Yh) request.getSession().getAttribute("user");
            String jb = yh.getJb();
            where += jb.equals("1") ? "" : jb.equals("2") ? " and `gzdw`.`gzdwid`=" + yh.getGzdwid() + " or `gzdw`.`sjdwid`=" + yh.getGzdwid() : " and `gzdw`.`gzdwid`=" + yh.getGzdwid();
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
