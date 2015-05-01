package com.hrms.manage.dzrc;

import com.hrms.table.Dzrc;
import com.hrms.table.Jbxx;
import com.jplus.json.JSONArray;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Xstarfct
 */
@WebServlet(name = "DzrcAction", urlPatterns = {"/manage/tsrcxx/dzrc/DzrcAction.jsp"})
public class DzrcAction extends HttpServlet {

    /**
     * 下面是模式关键字 可以自行删除和增加自定义模式，关键字一定要大写 默认模式为OTHER=0,所以OTHER不能删除
     */
    public final static int OTHER = 0;//其它
    public final static int SHOWLIST = 1;//显示列表
    public final static int SHOWONE = 2;//显示一条信息
    public final static int UPDATE = 3;//更新信息
    public final static int ADD = 4;//添加信息
    public final static int DELETE = 5;//删除信息
    public final static int PANDUAN = 6;//删除信息

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
                    Logger.getLogger(DzrcAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(DzrcAction.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case SHOWLIST:
                showList(request, response);
            case SHOWONE:
                showOne(request, response);
                break;
            case UPDATE:
                update(request, response);
                break;
            case ADD:
                add(request, response);
                break;
            case DELETE:
                delete(request, response);
                break;
            case PANDUAN:
                panDuan(request, response);
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
    }
    /*
     * 显示表格中的数据
     */

    private void showOne(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = new DatabaseAccess();
        EasyMapsManager emm = new EasyMapsManager(dao);
        String ryid_s = request.getParameter("ryid");
        String sfdzrc_s = request.getParameter("sfdzrc");
        int ryid = 0;
        int sfdzrc = 0;
        try {
            ryid = Integer.parseInt(ryid_s);
            sfdzrc = Integer.parseInt(sfdzrc_s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List list = null;
        if (sfdzrc == 1) {//如果是党政人才就获取全部信息
            String sql1 = "SELECT\n"
                    + "  `dzrc`.`ryid`, `jbxx`.`zjhm`, `jbxx`.`xm`, `jbxx`.`xb`, `jbxx`.`zw`,\n"
                    + "  `jbxx`.`zjbm`, `zj`.`zjmc`, `gzdw`.`dwmc`, `jbxx`.`sfdzrc`, `dzrc`.`dzrcid`,\n"
                    + "  `dzrc`.`rxzsj`, `dzrc`.`khqk1`, `dzrc`.`khqk2`, `dzrc`.`khqk3`,\n"
                    + "  `dzrc`.`username`\n"
                    + "FROM\n"
                    + "  `jbxx` INNER JOIN\n"
                    + "  `dzrc` ON `dzrc`.`ryid` = `jbxx`.`ryid` INNER JOIN\n"
                    + "  `zj` ON `jbxx`.`zjbm` = `zj`.`zjbm` INNER JOIN\n"
                    + "  `gzdw` ON `jbxx`.`gzdwid` = `gzdw`.`gzdwid` where `dzrc`.`ryid`=?";
            emm.setPreparedParameter(ryid);
            list = emm.executeQuery(sql1);
        } else {//如果不是党政人才就只获取部分信息
            String sql2 = "SELECT\n"
                    + "  `jbxx`.`ryid`, `gzdw`.`dwmc`, `jbxx`.`xm`, `jbxx`.`xb`, `jbxx`.`zw`,\n"
                    + "  `zj`.`zjmc`, `jbxx`.`zjhm`\n"
                    + "FROM\n"
                    + "  `jbxx` INNER JOIN\n"
                    + "  `gzdw` ON `jbxx`.`gzdwid` = `gzdw`.`gzdwid` INNER JOIN\n"
                    + "  `zj` ON `jbxx`.`zjbm` = `zj`.`zjbm` where `jbxx`.`ryid`=?";
            emm.setPreparedParameter(ryid);
            list = emm.executeQuery(sql2);
        }

        JSONArray json = new JSONArray(list);
        dao.close();
        ServletUtil.ajaxData(json.toString(), response);
    }

    /*
     * 更新党政人才信息
     */
    private void update(HttpServletRequest request, HttpServletResponse response) {
        Dzrc dzrc = new Dzrc();
        Jbxx jbxx = new Jbxx();
        Hyberbin hyb = new Hyberbin(dzrc, true);
        String ryid_s = request.getParameter("ryid");
        int ryid = 0;
        int sfdzrc;
        try {
            ryid = Integer.parseInt(ryid_s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dzrc.setRyid(ryid);
        ServletUtil.loadByBean(request, dzrc, true);
        boolean b = false;
        if (hyb.updateByKey("ryid")) {
//            hyb.changeTable(jbxx);
//            sfdzrc = 1;
//            String updateSql = "update jbxx set sfdzrc=? where ryid=?";
//            hyb.addParmeter(sfdzrc);
//            hyb.addParmeter(ryid);
//            if (hyb.update(updateSql)) {
                b = true;
//            }
        }
        hyb.reallyClose();
        String json = b ? "1" : "0";
        try {
            response.getWriter().write(json);
        } catch (IOException ex) {
            Logger.getLogger(DzrcAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * 添加该人员党政人才信息
     */
    private void add(HttpServletRequest request, HttpServletResponse response) {
        Dzrc dzrc = new Dzrc();
        Jbxx jbxx = new Jbxx();
        Hyberbin hyb = new Hyberbin(dzrc, true);
        String ryid_s = request.getParameter("ryid");
        int ryid = 0;
        int sfdzrc = 0;
        try {
            ryid = Integer.parseInt(ryid_s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ServletUtil.loadByBean(request, dzrc, true);
        dzrc.setRyid(ryid);
        boolean b = false;
        String rxzsj = dzrc.getRxzsj();
        String khqk1 = dzrc.getKhqk1();
        String khqk2 = dzrc.getKhqk2();
        String khqk3 = dzrc.getKhqk3();
        String insertSql = "insert into dzrc (rxzsj,khqk1,khqk2,khqk3,ryid) values(?,?,?,?,?)";
        hyb.addParmeter(rxzsj);
        hyb.addParmeter(khqk1);
        hyb.addParmeter(khqk2);
        hyb.addParmeter(khqk3);
        hyb.addParmeter(ryid);
        if (hyb.executeInsert(insertSql) != null) {
//            hyb.changeTable(jbxx);
//            sfdzrc = 1;//将是否是党政人才改为是
//            String updateSql = "update jbxx set sfdzrc=? where ryid=?";
//            hyb.addParmeter(sfdzrc);
//            hyb.addParmeter(ryid);
//            if (hyb.update(updateSql)) {
                b = true;
//            }
        }
        hyb.reallyClose();
        String json = b ? "1" : "0";
        try {
            response.getWriter().write(json);
        } catch (IOException ex) {
            Logger.getLogger(DzrcAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * 删除该人员的党政人才信息
     */
    private void delete(HttpServletRequest request, HttpServletResponse response) {
        Dzrc dzrc = new Dzrc();
        Jbxx jbxx = new Jbxx();
        Hyberbin hyb = new Hyberbin(dzrc, true);
        String ryid_s = request.getParameter("ryid");
        int ryid = 0;
        int sfdzrc;
        try {
            ryid = Integer.parseInt(ryid_s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean b = false;
        dzrc.setRyid(ryid);
        if (hyb.dellOneByKey("ryid")) {
//            hyb.changeTable(jbxx);
//            sfdzrc = 0;//将是否是党政人才改为否
//            String updateSql = "update jbxx set sfdzrc=? where ryid=?";
//            hyb.addParmeter(sfdzrc);
//            hyb.addParmeter(ryid);
//            if (hyb.update(updateSql)) {
                b = true;
//            }
        }
        hyb.reallyClose();
        String json = b ? "1" : "0";
        try {
            response.getWriter().write(json);
        } catch (IOException ex) {
            Logger.getLogger(DzrcAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void panDuan(HttpServletRequest request, HttpServletResponse response) {
        String ryid_s = request.getParameter("ryid");
        int ryid = 0;
        try {
            ryid = Integer.parseInt(ryid_s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Dzrc dzrc=new Dzrc();
        Hyberbin hyb = new Hyberbin(dzrc);
        String sql = "select * from dzrc where ryid=?";
        hyb.setParmeter(ryid);
        String result = "";
        dzrc=hyb.showOne(sql);
        if (dzrc.getKhqk1()==null) {
            result = "0";
        } else {//党政人才中有此人的ryid
            result = "1";
        }
        try {
            response.getWriter().write(result);
        } catch (IOException ex) {
            Logger.getLogger(DzrcAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}