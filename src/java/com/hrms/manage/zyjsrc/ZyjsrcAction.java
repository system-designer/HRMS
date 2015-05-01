package com.hrms.manage.zyjsrc;

import com.hrms.table.Jbxx;
import com.hrms.table.Zc;
import com.hrms.table.Zclb;
import com.hrms.table.Zyjsrc;
import com.hrms.util.ComboboxUtil;
import com.jplus.json.JSONArray;
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
 * @author star
 */
@WebServlet(name = "ZyjsrcAction", urlPatterns = {"/manage/tsrcxx/zyjsrc/ZyjsrcAction.jsp"})
public class ZyjsrcAction extends HttpServlet {

    /**
     * 下面是模式关键字 可以自行删除和增加自定义模式，关键字一定要大写 默认模式为OTHER=0,所以OTHER不能删除
     */
    public final static int OTHER = 0;//其它
    public final static int SHOWLIST = 1;//显示列表
    public final static int SHOWCOMBOXLIST = 2;//显示combox列表
    public final static int UPDATE = 3;//修改数据
    public final static int DEL = 4;//删除数据
    public final static int ADD = 5;//添加数据
    public final static int SHOWONE = 6;//添加数据
    public final static int SFZJRC = 7;//判断是否专技人才

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
                    Logger.getLogger(ZyjsrcAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ZyjsrcAction.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case SHOWLIST:
                showList(request, response);
                break;
            case SHOWCOMBOXLIST:
                showComboxList(request, response);
                break;
            case DEL:
                del(request, response);
                break;
            case ADD:
                add(request, response);
                break;
            case SHOWONE:
                showOne(request, response);
                break;
            case SFZJRC:
                sfzjrc(request, response);
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
        String ryid = request.getParameter("ryid");
        DatabaseAccess db = new DatabaseAccess();
        EasyMapsManager easy = new EasyMapsManager(db);
        String sql = "SELECT"
                + "  `zyjsrc`.`hjcq`, `zyjsrc`.`jszcbm`, `zyjsrc`.`zclbbm`, `zyjsrc`.`gzgw`,"
                + "  `zyjsrc`.`cszy`, `zyjsrc`.`zyjsrcid`, `zc`.`zcbm`, `zclb`.`zclbbm`,"
                + "  `zclb`.`zclbmc`, `zc`.`zcmc`"
                + "FROM"
                + "  `jbxx` RIGHT JOIN"
                + "  `zyjsrc` ON `zyjsrc`.`ryid` = `jbxx`.`ryid` INNER JOIN"
                + "  `zc` ON `zc`.`zcbm` = `zyjsrc`.`jszcbm` INNER JOIN"
                + "  `zclb` ON `zclb`.`zclbbm` = `zyjsrc`.`zclbbm` where jbxx.ryid=?;";
        easy.setPreparedParameter(Integer.parseInt(ryid));
        ArrayList list = easy.executeQuery(sql);
        JSONArray json = new JSONArray(list);
        db.close();
        ServletUtil.ajaxData(json.toString(), response);
    }

    private void showComboxList(HttpServletRequest request, HttpServletResponse response) {
        Zclb zclb = new Zclb();
        Zc zc = new Zc();
        Connection conn = DatabaseINI.getConnection();
        Hyberbin hyb = new Hyberbin(zclb, conn);
        String sql = "select * from zclb";
        List list = hyb.showList(sql);
        String sql1 = "select * from zc";
        hyb.changeTable(zc);
        List list1 = hyb.showList(sql1);
        hyb.reallyClose();
        ComboboxUtil combox = new ComboboxUtil();
        String json = combox.createBatchComboJSON(list, list1);
        ServletUtil.ajaxData(json, response);
        System.out.println("json:" + json);
    }

    private void del(HttpServletRequest request, HttpServletResponse response) {
        String zyjsrcid = request.getParameter("zyjsrcid");
        String sfzyrc="";
        Connection conn = DatabaseINI.getConnection();
        Zyjsrc zyjsrc = new Zyjsrc();
        Jbxx jbxx = new Jbxx();
        boolean sfzjrc = false;
        Hyberbin hyb = new Hyberbin(zyjsrc, conn);
        hyb.addParmeter(Integer.parseInt(zyjsrcid));
        boolean b = false;
        String notice = null;
        try {
            b = hyb.dell("where zyjsrcid = ?");
            if (b) {
                notice = "删除成功";
                sfzyrc="0";
            }
        } catch (Exception e) {
            notice = "操作失败";
        } finally {
            hyb.reallyClose();
            try {
                JSONObject json = new JSONObject();
                json.put("sfzyrc", sfzyrc);
                json.put("notice", notice);
                ServletUtil.ajaxData(json.toString(), response);
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) {
        String sfzjrc = "";
        String ryid_1 = request.getParameter("ryid");
        Zyjsrc zyjsrc = new Zyjsrc();
        int ryid = Integer.parseInt(ryid_1);
        Jbxx jbxx = new Jbxx();
        Connection conn = DatabaseINI.getConnection();
        Hyberbin hyb = new Hyberbin(zyjsrc, conn);
        hyb.addParmeter(ryid);
        String sql = " select * from zyjsrc where ryid=?";
        Zyjsrc list = hyb.showOne(sql);
        String notice = null;
        if (list.getCszy() == null && list.getGzgw() == null && list.getHjcq() == null && list.getJszcbm() == null && list.getHjcq() == null) {
            String cszy = request.getParameter("cszy");
            String gzgw = request.getParameter("gzgw");
            String zclbbm = request.getParameter("zclbbm");
            String jszcbm = request.getParameter("jszcbm");
            String hjcq = request.getParameter("hjcq");
            String sql_1 = "insert into zyjsrc (cszy,gzgw,zclbbm,jszcbm,hjcq,ryid) values(?,?,?,?,?,?)";
            hyb.addParmeter(cszy);
            hyb.addParmeter(gzgw);
            hyb.addParmeter(zclbbm);
            hyb.addParmeter(jszcbm);
            hyb.addParmeter(hjcq);
            hyb.addParmeter(ryid);
            boolean b = false;
            try {
                hyb.executeInsert(sql_1);
                hyb.changeTable(jbxx);
                String updateSql = "update jbxx set sfzjrc=? where ryid=?";
                hyb.addParmeter(true);
                hyb.addParmeter(ryid);
                b = hyb.update(updateSql);
                if (b) {
                    notice = "添加成功";
                    sfzjrc = "1";
                }
            } catch (Exception e) {
                notice = "操作失败";
            } finally {
                hyb.reallyClose();
                JSONObject json = new JSONObject();
                try {
                    json.put("sfzjrc", sfzjrc);
                    json.put("notice", notice);
                    ServletUtil.ajaxData(json.toString(), response);
                } catch (JSONException ex) {
                    Logger.getLogger(ZyjsrcAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            ServletUtil.loadByBean(request, zyjsrc, true);
            boolean b = hyb.updateByKey("ryid");
            if (b) {
                notice = "操作成功";
                sfzjrc = "1";
            } else {
                notice = "操作失败";
            }
            hyb.reallyClose();
            JSONObject json = new JSONObject();
            try {
                json.put("sfzjrc", sfzjrc);
                json.put("notice", notice);
                ServletUtil.ajaxData(json.toString(), response);
            } catch (JSONException ex) {
                Logger.getLogger(ZyjsrcAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void showOne(HttpServletRequest request, HttpServletResponse response) {
        String ryid = request.getParameter("ryid");
        DatabaseAccess db = new DatabaseAccess();
        EasyMapsManager easy = new EasyMapsManager(db);
        String sql = "SELECT"
                + "  `jbxx`.`zjhm`, `jbxx`.`xm`, `jbxx`.`xb`, `jbxx`.`gzdwid`, `gzdw`.`dwmc`"
                + "FROM"
                + "  `jbxx` LEFT JOIN"
                + "  `gzdw` ON `jbxx`.`gzdwid` = `gzdw`.`gzdwid` where jbxx.ryid=?;";
        easy.setPreparedParameter(Integer.parseInt(ryid));
        ArrayList list = easy.executeQuery(sql);
        db.close();
        JSONArray json = new JSONArray(list);
        ServletUtil.ajaxData(json.toString(), response);
    }

    private void sfzjrc(HttpServletRequest request, HttpServletResponse response) {
       String ryid = request.getParameter("ryid");
       String sfzjrc;
       Zyjsrc zjrc = new Zyjsrc();
       Hyberbin hyb = new Hyberbin(zjrc);
       String sql = "select * from zyjsrc where ryid=?";
       hyb.addParmeter(Integer.parseInt(ryid));
        Zyjsrc zj = hyb.showOne(sql);
        hyb.reallyClose();
        if(zj.getZyjsrcid()==null) {
            sfzjrc="0";
        } else {
             sfzjrc="1";
        }
        try {        
            response.getWriter().write(sfzjrc);
        } catch (IOException ex) {
            Logger.getLogger(ZyjsrcAction.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
