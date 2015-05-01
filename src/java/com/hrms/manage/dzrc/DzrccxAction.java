package com.hrms.manage.dzrc;

import com.hrms.manage.cyrc.CyrccxAction;
import com.hrms.table.Dwlb;
import com.hrms.table.Dwxz;
import com.hrms.table.Ssxt;
import com.hrms.table.Xl;
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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.Session;
import org.jplus.hyb.database.DatabaseINI;
import org.jplus.hyb.database.Hyberbin;
import org.jplus.hyb.servlet.ServletUtil;
import org.jplus.yydbgai.DatabaseAccess;
import org.jplus.yydbgai.EasyMapsManager;

/**
 *
 * @author Xstarfct
 */
@WebServlet(name = "DzrccxAction", urlPatterns = {"/manage/tsrcxx/dzrc/DzrccxAction.jsp"})
public class DzrccxAction extends HttpServlet {

    /**
     * 下面是模式关键字 可以自行删除和增加自定义模式，关键字一定要大写 默认模式为OTHER=0,所以OTHER不能删除
     */
    public final static int OTHER = 0;//其它
    public final static int SHOWLIST = 1;//显示列表
    public final static int GETTABLES = 2;//查询得到列表
    public final static int SHOWONE = 3;//个体详情
    public final static int GETGZDW = 4;//得到工作单位
    public final static int SHOWDITGRIDE = 5;//加载ditgride中的信息
    public final static int BAIDUINPUT = 6;

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
                    Logger.getLogger(DzrccxAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(DzrccxAction.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case SHOWLIST:
                showList(request, response);
                break;
            case GETTABLES:
                getTables(request, response);
                break;
            case SHOWONE:
                showOne(request, response);
                break;
            case SHOWDITGRIDE:
                showDitgride(request, response);
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

    private void showOne(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = null;
        try {
            int dzrcid = 1;
            try {
                dzrcid = Integer.parseInt(request.getParameter("dzrcid"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            dao = new DatabaseAccess();
            EasyMapsManager emm = new EasyMapsManager(dao);
            String jbxxsql = "SELECT\n"
                    + "  `jbxx`.`zjhm`,`jbxx`.`zp`,`jbxx`.`gzdwid`, `jbxx`.`xm`, `jbxx`.`xb`, `jbxx`.`csrq`, `jbxx`.`mz`,\n"
                    + "  `jbxx`.`jg`, `gzdw`.`dwmc`, `jl`.`xxjl`, `jl`.`gzjl`, `jl`.`jcqk`,\n"
                    + "  `xw`.`xwmc`, `xl`.`xlmc`, `jbxx`.`gzsj`, `jbxx`.`rdsj`, `jbxx`.`zw`,\n"
                    + "  `zj`.`zjmc`, `jbxx`.`lxdh`, `jbxx`.`txdz`, `jbxx`.`jkzk`, `jbxx`.`hyzkbm`,\n"
                    + "  `jbxx`.`zymc`, `jbxx`.`byxx`, `jbxx`.`yzbm`, `dzrc`.*, `jbxx`.`ryid`,\n"
                    + "  `zzmm`.`zzmmmc`, `jbxx`.`hjszd`\n"
                    + "FROM\n"
                    + "  `jbxx` LEFT JOIN\n"
                    + "  `gzdw` ON `jbxx`.`gzdwid` = `gzdw`.`gzdwid` LEFT JOIN\n"
                    + "  `xw` ON `jbxx`.`xwbm` = `xw`.`xwbm` LEFT JOIN\n"
                    + "  `xl` ON `jbxx`.`xlbm` = `xl`.`xlbm` LEFT JOIN\n"
                    + "  `zj` ON `jbxx`.`zjbm` = `zj`.`zjbm` INNER JOIN\n"
                    + "  `dzrc` ON `jbxx`.`ryid` = `dzrc`.`ryid` INNER JOIN\n"
                    + "  `zzmm` ON `jbxx`.`zzmmbm` = `zzmm`.`zzmmbm`, `jl` where dzrcid=?;";
            emm.setPreparedParameter(dzrcid);
            List list = emm.executeQuery(jbxxsql);
            System.out.println(list.toString());
            request.setAttribute("list", list);
            request.getRequestDispatcher("/manage/tsrcxx/dzrc/dzrcxq.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(CyrccxAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CyrccxAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dao.close();
        }
    }

    /**
     * 为combobox提供数据
     *
     * @param request
     * @param response
     */
    private void getTables(HttpServletRequest request, HttpServletResponse response) {
        Connection conn = DatabaseINI.getDatabase().getConn();
        Hyberbin hyb = null;
        hyb = new Hyberbin(new Dwxz(), conn);//单位性质编码表
        List dwxzlist = hyb.showAll();//单位性质列表
        hyb = new Hyberbin(new Dwlb(), conn);//单位级别表
        List dwjblist = hyb.showAll();
        hyb = new Hyberbin(new Ssxt(), conn);//所属系统
        List ssxtlist = hyb.showAll();
        hyb = new Hyberbin(new Xl(), conn);//学历表
        List xllist = hyb.showAll();
        hyb = new Hyberbin(new Zj(), conn);//职级表
        List zjlist = hyb.showAll();
        hyb = new Hyberbin(new Zzmm(), conn);//政治面貌
        List zzmmlist = hyb.showAll();
        hyb.reallyClose();
        String json = ComboboxUtil.createBatchComboJSON(dwxzlist, ssxtlist, zzmmlist, xllist, zjlist, dwjblist);
        ServletUtil.ajaxData(json, response);
    }

    /**
     * 显示搜索之后的党政人才列表
     */
    private void showList(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = new DatabaseAccess();
        try {
            EasyMapsManager emm = new EasyMapsManager(dao);
            EasyUiJson datagrid = new EasyUiJson(request);
            int year = new Date(System.currentTimeMillis()).getYear() + 1900;
            int nl1 = 0;
            int nl2 = 100;
            String sql = "";
            String where = "";
            HttpSession session = request.getSession(true);
            Yh yh = (Yh) session.getAttribute("user");
            String jb = yh.getJb();//用户级别
            Integer gzdwid = yh.getGzdwid();//用户工作单位id
            String xlys = request.getParameter("xlys");//学历以上
            String zjys = request.getParameter("zjys");//职级以上
            String xltj = xlys.equals("true") ? "<=" : "=";
            String zjtj = zjys.equals("true") ? "<=" : "=";
            String dwxzbm = request.getParameter("dwxz");//单位性质编码
            String zjbm = request.getParameter("zj");//职级编码
            String xlbm = request.getParameter("xl");//学历编码
            String dwlbbm = request.getParameter("dwlb");//单位类别编码
            String ssxtbm = request.getParameter("ssxt");//所属系统编码
            String zzmmbm = request.getParameter("zzmm");//工作单位编码
            String dwmc = request.getParameter("gzdw");//单位名称
            try {
                nl1 = Integer.parseInt(request.getParameter("nl1"));
                nl2 = Integer.parseInt(request.getParameter("nl2"));
            } catch (Exception e) {
                nl1 = 0;
                nl2 = 100;
            }
            where = "  (" + year + "-year(csrq)) between ? and ? ";
            emm.setPreparedParameter(nl1);
            emm.setPreparedParameter(nl2);
            where += jb.equals("1") ? "" : jb.equals("2") ? " and `gzdw`.`gzdwid`=" + gzdwid + " or `gzdw`.`sjdwid`=" + gzdwid : " and `gzdw`.`gzdwid`=" + gzdwid;
            if (!Util.isEmpty(zjbm)) {
                where = where + "and `jbxx`.`zjbm`" + zjtj + "? ";
                emm.setPreparedParameter(zjbm);
            }
            if (!Util.isEmpty(xlbm)) {
                where = where + " and `jbxx`.`xlbm`" + xltj + "?";
                emm.setPreparedParameter(xlbm);
            }
            if (!(Util.isEmpty(dwmc))) {//如果单位名称不为空
                where = where + " and `gzdw`.`dwmc` = ? ";
                emm.setPreparedParameter(dwmc);
            }
            if (!Util.isEmpty(dwxzbm)) {
                where = where + " and dwxzbm=?";
                emm.setPreparedParameter(dwxzbm);
            }
            if (!Util.isEmpty(dwlbbm)) {
                where = where + " and dwlbbm=?";
                emm.setPreparedParameter(dwlbbm);
            }
            if (!Util.isEmpty(ssxtbm)) {
                where = where + " and ssxtbm=?";
                emm.setPreparedParameter(ssxtbm);
            }
            if (!Util.isEmpty(zzmmbm)) {
                where = where + "  and zzmmbm=?";
                emm.setPreparedParameter(zzmmbm);
            }
            sql = "SELECT  `gzdw`.`dwmc`, `xl`.`xlmc`, `jbxx`.`csrq`, `jbxx`.`zw`, `jbxx`.`ryid`,  `jbxx`.`zjhm`, `jbxx`.`xm`, `jbxx`.`xb`, `dzrc`.`dzrcid`, `zj`.`zjmc`FROM  `jbxx` LEFT JOIN  `xl` ON `xl`.`xlbm` = `jbxx`.`xlbm` LEFT JOIN  `gzdw` ON `jbxx`.`gzdwid` = `gzdw`.`gzdwid` RIGHT JOIN  `dzrc` ON `dzrc`.`ryid` = `jbxx`.`ryid` INNER JOIN  `zj` ON `jbxx`.`zjbm` = `zj`.`zjbm` where";
            sql += where;
            List list = emm.executeQuery(sql, datagrid);
            datagrid.putAll(list);
            ServletUtil.ajaxData(datagrid.toDataString(), response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询信息不正确");
        } finally {
            dao.close();
        }
    }

    private void showDitgride(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = new DatabaseAccess();
        try {
            String ryid_s = request.getParameter("ryid");
            int ryid = 0;
            try {
                ryid = Integer.parseInt(ryid_s);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ryid转换出错");
            }
            EasyMapsManager emm = new EasyMapsManager(dao);
            EasyUiJson datagrid = new EasyUiJson(request);
            String sql = "SELECT\n"
                    + "  `zzmm`.`zzmmmc`, `jtcy`.*\n"
                    + "FROM\n"
                    + "  `jtcy` INNER JOIN\n"
                    + "  `zzmm` ON `jtcy`.`zzmm` = `zzmm`.`zzmmbm` where ryid=?";
            emm.setPreparedParameter(ryid);
            List list = emm.executeQuery(sql, datagrid);
            datagrid.putAll(list);
            System.out.println(datagrid.toDataString());
            ServletUtil.ajaxData(datagrid.toDataString(), response);
        } finally {
            dao.close();
        }
    }

    private void getDwmcs(HttpServletRequest request, HttpServletResponse response) {
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
