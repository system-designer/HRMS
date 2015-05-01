package com.hrms.manage.zyjsrc;

import com.jplus.json.JSONArray;
import java.io.IOException;
import java.util.ArrayList;
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
 * @author star
 */
@WebServlet(name = "Zjxxxx", urlPatterns = {"/manage/tsrcxx/zyjsrccx/Zyxxxx.jsp"})
public class Zjxxxx extends HttpServlet {

    /**
     * 下面是模式关键字 可以自行删除和增加自定义模式，关键字一定要大写 默认模式为OTHER=0,所以OTHER不能删除
     */
    public final static int OTHER = 0;//其它
    public final static int SHOWJBXX = 1;//显示基本信息

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
                    Logger.getLogger(Zjxxxx.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Zjxxxx.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case SHOWJBXX:
                showJbxx(request, response);
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
    private void showJbxx(HttpServletRequest request, HttpServletResponse response) {
        String ryid = request.getParameter("ryid");
        DatabaseAccess db = new DatabaseAccess();
        EasyMapsManager easy = new EasyMapsManager(db);
        String sql = "SELECT"
                + "  `gzdw`.`dwmc`, `jbxx`.`zjhm`, `jbxx`.`xm`, `jbxx`.`xb`,"
                + "  `jbxx`.`csrq`, `jbxx`.`mz`, `jbxx`.`jg`, `jbxx`.`gzsj`, `zzmm`.`zzmmmc`,"
                + "  `jbxx`.`rdsj`, `zj`.`zjmc`, `jbxx`.`zw`, `jbxx`.`xxxs`,`jbxx`.`zp`, `jbxx`.`gzdwid`, `xl`.`xlmc`,"
                + "  `xw`.`xwmc`, `jbxx`.`byxx`, `jbxx`.`zymc`, `jbxx`.`hjszd`, `jbxx`.`jkzk`,"
                + "  `jbxx`.`txdz`, `jbxx`.`yzbm`, `jbxx`.`lxdh`, `jbxx`.`hyzkbm`, `jbxx`.`zp`,"
                + "  `zyjsrc`.`cszy`, `zyjsrc`.`gzgw`, `zclb`.`zclbmc`, `zc`.`zcmc`,"
                + "  `zyjsrc`.`hjcq`, `jl`.`gzjl`, `jl`.`xxjl`"
                + "FROM"
                + "  `jbxx` INNER JOIN"
                + "  `gzdw` ON `gzdw`.`gzdwid` = `jbxx`.`gzdwid` LEFT JOIN"
                + "  `zzmm` ON `jbxx`.`zzmmbm` = `zzmm`.`zzmmbm` LEFT JOIN"
                + "  `zj` ON `jbxx`.`zjbm` = `zj`.`zjbm` LEFT JOIN"
                + "  `xl` ON `jbxx`.`xlbm` = `xl`.`xlbm` LEFT JOIN"
                + "  `xw` ON `jbxx`.`xwbm` = `xw`.`xwbm` INNER JOIN"
                + "  `zyjsrc` ON `jbxx`.`ryid` = `zyjsrc`.`ryid` LEFT JOIN"
                + "  `zclb` ON `zyjsrc`.`zclbbm` = `zclb`.`zclbbm` LEFT JOIN"
                + "  `zc` ON `zyjsrc`.`jszcbm` = `zc`.`zcbm` LEFT JOIN"
                + "  `jl` ON `jl`.`ryid` = `jbxx`.`ryid` where jbxx.ryid=?";
        easy.setPreparedParameter(Integer.parseInt(ryid));
        ArrayList list = easy.executeQuery(sql);
        request.setAttribute("list", list);
        try {
            request.getRequestDispatcher("/manage/tsrcxx/zyjsrccx/zjxxxx.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(Zjxxxx.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Zjxxxx.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.close();
    }
}
