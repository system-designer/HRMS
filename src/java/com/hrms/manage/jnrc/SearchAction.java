package com.hrms.manage.jnrc;

import com.hrms.manage.cyrc.CyrccxAction;
import com.hrms.table.Yh;
import com.jplus.json.EasyUiJson;
import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;
import org.jplus.hyb.servlet.ServletUtil;
import org.jplus.yydbgai.DatabaseAccess;
import org.jplus.yydbgai.EasyMapsManager;

/**
 *
 * @author 肖雪勇
 */
@WebServlet(name = "SearchAction", urlPatterns = {"/manage/tsrcxx/rcxx/SearchAction.jsp"})
public class SearchAction extends HttpServlet {

    /**
     * 下面是模式关键字 可以自行删除和增加自定义模式，关键字一定要大写 默认模式为OTHER=0,所以OTHER不能删除
     */
    public final static int OTHER = 0;//其它
    public final static int SHOWALLCOMBBOX = 1;//显示列表
    public final static int DWMCLIST = 2;//单位名称集合
    public final static int SHOWLIST = 3;//显示所有查询信息
    public final static int SHOWONE = 4;//显示查询详情
    public final static int SHOWALL = 5;//显示所有

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
                    Logger.getLogger(SearchAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SearchAction.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case SHOWALLCOMBBOX:
                showAllCombbox(request, response);
                break;
            case DWMCLIST:
                showDwmcList(request, response);
                break;
            case SHOWLIST:
                showList(request, response);
                break;
            case SHOWONE:
                showOne(request, response);
                break;
            case SHOWALL:
                showAll(request, response);
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
    private void showAllCombbox(HttpServletRequest request, HttpServletResponse response) {
        String sql_dwxz = "SELECT `dwxz`.`dwxzbm`, `dwxz`.`dwxzmc` FROM `dwxz`";
        String sql_dwjb = "SELECT `dwlb`.`dwlbbm`, `dwlb`.`dwlbmc` FROM `dwlb`";
        String sql_sshy = "SELECT `sshy`.`sshybm`, `sshy`.`sshymc` FROM `sshy`";
        String sql_xl = "SELECT `xl`.`xlbm`, `xl`.`xlmc` FROM `xl`";
        String jsonArrays = UtilTools.toJsonArrays(sql_dwxz, sql_dwjb, sql_sshy, sql_xl);
        ServletUtil.ajaxData(jsonArrays, response);
    }

    private void showDwmcList(HttpServletRequest request, HttpServletResponse response) {
        String dwmc = request.getParameter("keyword");
        String sql = "SELECT `gzdw`.`dwmc` as `keyword`, `gzdw`.`gzdwid` FROM `gzdw` where `gzdw`.`dwmc` like '" + dwmc + "%'";
        DatabaseAccess db = new DatabaseAccess();
        String jsons = UtilTools.toJsonArray(sql, db);
        db.close();
        ServletUtil.ajaxData(jsons, response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) {
        String dwxz = request.getParameter("dwxz");
        String dwjb = request.getParameter("dwjb");
        String sshy = request.getParameter("sshy");
        String dwmc = request.getParameter("dwmc");
        String xl = request.getParameter("xl");
        String xlys = request.getParameter("xlys");
        String nl1 = request.getParameter("nl1");
        String nl2 = request.getParameter("nl2");

//        System.out.println("--------------------");
//        System.out.println("dwxz:"+dwxz+"end");
//        System.out.println("dwjb:"+dwjb+"end");
//        System.out.println("sshy:"+sshy+"end");
//        System.out.println("dwmc:"+dwmc+"end");
//        System.out.println("xl:"+xl+"end");
//        System.out.println("xlys:"+xlys+"end");
//        System.out.println("nl1:"+nl1+"end");
//        System.out.println("nl2:"+nl2+"end");
//        System.out.println("--------------------");

        DatabaseAccess dao = new DatabaseAccess();
        StringBuilder where = new StringBuilder(" where 1=1 ");
        if (dwxz != null && !dwxz.equals("")) {
            where.append("and `gzdw`.`dwxzbm`=? ");
            dao.setPreparedParameter(dwxz);
        }
        if (dwjb != null && !dwjb.equals("")) {
            where.append("and `gzdw`.`dwlbbm`=? ");
            dao.setPreparedParameter(Integer.parseInt(dwjb));
        }
        if (sshy != null && !sshy.equals("")) {
            where.append("and `gzdw`.`sshybm`=? ");
            dao.setPreparedParameter(sshy);
        }
        if (dwmc != null && !dwmc.equals("")) {
            where.append("and `gzdw`.`dwmc`=? ");
            dao.setPreparedParameter(dwmc);
        }
        if (xlys.equals("true") && !"".equals(xl)) {
            where.append("and `xl`.`xlbm`<=? ");
            dao.setPreparedParameter(xl);
        }
        if (!xlys.equals("true") && !"".equals(xl)) {
            where.append("and `xl`.`xlbm`=? ");
            dao.setPreparedParameter(xl);
        }


        if (!nl1.equals("") && nl2.equals("")) {//年龄1有数据 年龄2没有数据
            where.append("and year(now())-year(`jbxx`.`csrq`) between ? and ?");
            dao.setPreparedParameter(Integer.parseInt(nl1));
            dao.setPreparedParameter(Integer.parseInt(nl1));
        }

        if (!nl1.equals("") && !nl2.equals("")) {//年龄1,2都有数据
            where.append("and year(now())-year(`jbxx`.`csrq`) between ? and ?");
            dao.setPreparedParameter(Integer.parseInt(nl1));
            dao.setPreparedParameter(Integer.parseInt(nl2));
        }

        HttpSession session = request.getSession(true);
        Yh yh = (Yh) session.getAttribute("user");
        String jb = yh.getJb();//用户级别
        Integer gzdwid = yh.getGzdwid();//用户工作单位id
        String sss = jb.equals("1") ? "" : jb.equals("2") ? " and `gzdw`.`gzdwid`=" + gzdwid + " or `gzdw`.`sjdwid`=" + gzdwid : " and `gzdw`.`gzdwid`=" + gzdwid;
        System.out.println("---->" + sss);

        String sql = "SELECT "
                + "`jbxx`.`xm`, `jbxx`.`xb`, `jbxx`.`csrq`, `xl`.`xlmc`, `jbxx`.`ryid`, "
                + "`gzdw`.`dwmc` "
                + "FROM "
                + "`jbxx` INNER JOIN "
                + "`xl` ON `jbxx`.`xlbm` = `xl`.`xlbm` INNER JOIN "
                + "`gzdw` ON `jbxx`.`gzdwid` = `gzdw`.`gzdwid` " + where.toString() + sss;
        EasyMapsManager emm = new EasyMapsManager(dao);
        EasyUiJson datagrid = new EasyUiJson(request);
        ArrayList<HashMap> dwList = emm.executeQuery(sql, datagrid);
        dao.close();
        datagrid.putAll(dwList);
        try {
            PrintWriter out = response.getWriter();
            out.write(datagrid.toDataString());
            //System.out.println(datagrid.toDataString());
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(SearchAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showOne(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = null;
        try {
            int rcid = 1;
            try {
                rcid = Integer.parseInt(request.getParameter("ryid"));
            } catch (Exception e) {
                e.printStackTrace();
                rcid = 1;
            }
            dao = new DatabaseAccess();
            EasyMapsManager emm = new EasyMapsManager(dao);
            String jbxxsql = "SELECT   `cyrc`.`yrzjg`,   `cyrc`.`yzw`,   `cyrc`.`zhgzsj`,   `cyrc`.`email`,   `cyrc`.`yjzdz`,   `cyrc`.`zyry`,   `jbxx`.`zjhm`,   `jbxx`.`xm`,   `jbxx`.`xb`, `jbxx`.`zp`,   `jbxx`.`gzdwid`,  `jbxx`.`csrq`,   `jbxx`.`mz`,   `jbxx`.`jg`,   `gzdw`.`dwmc`,   `jl`.`xxjl`,   `jl`.`gzjl`,   `jl`.`jcqk`,   `xw`.`xwmc`,   `xl`.`xlmc`,   `jbxx`.`gzsj`,   `jbxx`.`rdsj`,   `jbxx`.`zw`,   `zj`.`zjmc`,   `jbxx`.`lxdh`,   `jbxx`.`txdz`,   `jbxx`.`jkzk`,   `jbxx`.`hyzkbm`,   `jbxx`.`zymc`,   `jbxx`.`byxx`,   `jbxx`.`yzbm` FROM   `jbxx`  LEFT  JOIN `cyrc` ON `cyrc`.`ryid` = `jbxx`.`ryid`  LEFT  JOIN `gzdw` ON `jbxx`.`gzdwid` = `gzdw`.`gzdwid`   LEFT JOIN `jl` ON `cyrc`.`cyrcid` = `jl`.`jlid`  LEFT  JOIN `xw` ON `jbxx`.`xwbm` = `xw`.`xwbm`   LEFT JOIN `xl` ON `jbxx`.`xlbm` = `xl`.`xlbm` LEFT  JOIN `zj` ON `jbxx`.`zjbm` = `zj`.`zjbm` where `jbxx`.`ryid`=?;";
            emm.setPreparedParameter(rcid);
            List list = emm.executeQuery(jbxxsql);
            request.setAttribute("list", list);
            request.getRequestDispatcher("/manage/tsrcxx/jnrccx/jnrcxq.jsp").forward(request, response);

        } catch (ServletException ex) {
            Logger.getLogger(CyrccxAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CyrccxAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dao.close();
        }
        //System.out.println(request.getParameter("ryid"));
    }

    private void showAll(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        Yh yh = (Yh) session.getAttribute("user");
        String jb = yh.getJb();//用户级别
        Integer gzdwid = yh.getGzdwid();//用户工作单位id
        String sss = jb.equals("1") ? "" : jb.equals("2") ? " and `gzdw`.`gzdwid`=" + gzdwid + " or `gzdw`.`sjdwid`=" + gzdwid : " and `gzdw`.`gzdwid`=" + gzdwid;
        if (sss != "") {
            sss="where "+sss;
        }

        String sql = "SELECT "
                + "`jbxx`.`xm`, `jbxx`.`xb`, `jbxx`.`csrq`, `xl`.`xlmc`, `jbxx`.`ryid`, "
                + "`gzdw`.`dwmc` "
                + "FROM "
                + "`jbxx` INNER JOIN "
                + "`xl` ON `jbxx`.`xlbm` = `xl`.`xlbm` INNER JOIN "
                + "`gzdw` ON `jbxx`.`gzdwid` = `gzdw`.`gzdwid` " + sss;
        DatabaseAccess dao = new DatabaseAccess();
        EasyMapsManager emm = new EasyMapsManager(dao);
        EasyUiJson datagrid = new EasyUiJson(request);
        ArrayList<HashMap> dwList = emm.executeQuery(sql, datagrid);
        dao.close();
        datagrid.putAll(dwList);
        try {
            PrintWriter out = response.getWriter();
            out.write(datagrid.toDataString());
            //System.out.println(datagrid.toDataString());
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(SearchAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
