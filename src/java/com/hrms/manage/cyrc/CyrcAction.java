package com.hrms.manage.cyrc;

import com.hrms.table.Cyrc;
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
 * @author Raglia
 */
@WebServlet(name = "CyrcAction", urlPatterns = {"/manage/tsrcxx/cyrc/CyrcAction.jsp"})
public class CyrcAction extends HttpServlet {

    /**
     * 下面是模式关键字 可以自行删除和增加自定义模式，关键字一定要大写 默认模式为OTHER=0,所以OTHER不能删除
     */
    public final static int OTHER = 0;//其它
    public final static int SHOWONE = 1;//显示单例
    public final static int SHOWLIST = 2;//显示信息列表
    public final static int ADD = 3;//添加信息
    public final static int UPDATE = 4;//更新信息
    public final static int DELETE = 5;//删除信息
    public final static int DECIDE = 6;//判断是否为创业人才

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
                    Logger.getLogger(CyrcAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(CyrcAction.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case SHOWONE:
                showOne(request, response);
                break;
            case SHOWLIST:
                showCyrcList(request, response);
                break;
            case ADD:
                addOne(request, response);
                break;
            case UPDATE:
                updateOne(request, response);
                break;
            case DELETE:
                deleteOne(request, response);
                break;
            case DECIDE:
                decide(request, response);
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
     * 显示单例
     */
    private void showOne(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = new DatabaseAccess();
        EasyMapsManager emm = new EasyMapsManager(dao);
        String ryid = request.getParameter("ryid");
        String sfcyrc = request.getParameter("sfcyrc");
        int id = 0;
        int sf = 0;
        try {
            id = Integer.parseInt(ryid);
            sf = Integer.parseInt(sfcyrc);
        } catch (Exception e) {
            System.out.println("创业人才ID不正确!");
        }
        String sql;
        if (sf == 1) {
            sql = "SELECT"
                    + "  `cyrc`.`yrzjg`, `cyrc`.`yzw`, `cyrc`.`zhgzsj`, `cyrc`.`email`, `cyrc`.`yjzdz`,"
                    + "  `cyrc`.`zyry`, `gzdw`.`dwmc`, `jbxx`.`xm`, `jbxx`.`xb`, `jbxx`.`zjhm`"
                    + "FROM"
                    + "  `cyrc` INNER JOIN"
                    + "  `jbxx` ON `jbxx`.`ryid` = `cyrc`.`ryid` INNER JOIN"
                    + "  `gzdw` ON `gzdw`.`gzdwid` = `jbxx`.`gzdwid` where `cyrc`.`ryid`=?";
        } else {
            sql = "SELECT"
                    + "  `jbxx`.`xm`, `jbxx`.`xb`, `jbxx`.`zjhm`, `gzdw`.`dwmc`"
                    + "FROM"
                    + "  `gzdw`, `jbxx` where `jbxx`.`ryid`=?";
        }

        emm.setPreparedParameter(id);
        List list = emm.executeQuery(sql);
        dao.close();
        JSONArray json = new JSONArray(list);
        ServletUtil.ajaxData(json.toString(), response);
    }

    /**
     * 显示列表
     */
    private void showCyrcList(HttpServletRequest request, HttpServletResponse response) {
    }

    private void decide(HttpServletRequest request, HttpServletResponse response) {
       String ryid_s = request.getParameter("ryid");
        int ryid = 0;
        try {
            ryid = Integer.parseInt(ryid_s);
        } catch (Exception e) {
            System.out.println("人员ID不正确");
        }
        System.out.println("======id==="+ryid);
        Cyrc cyrc = new Cyrc();
        Hyberbin hyb = new Hyberbin(cyrc);
        String sql = "select * from cyrc where ryid=?";
        hyb.setParmeter(ryid);
        String result = "";
        cyrc=hyb.showOne(sql);
        if ((cyrc.getCyrcid())==null) {
            result = "0";
        } else {//创业人才中有此人的ryid
            result = "1";
        }
        try {
            response.getWriter().write(result);
        } catch (IOException ex) {
            Logger.getLogger(CyrcAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateOne(HttpServletRequest request, HttpServletResponse response) {
        Cyrc cyrc = new Cyrc();
        Jbxx jbxx = new Jbxx();
        Hyberbin hyb = new Hyberbin(cyrc, true);
        String ryid_s = request.getParameter("ryid");
        int ryid = 0;
        int sfcyrc;
        try {
            ryid = Integer.parseInt(ryid_s);
        } catch (Exception e) {
            System.out.println("人员id出错");
        }
        cyrc.setRyid(ryid);
        ServletUtil.loadByBean(request, cyrc, true);
        boolean b = false;
        if (hyb.updateByKey("ryid")) {
            hyb.changeTable(jbxx);
            sfcyrc = 1;
            String updateSql = "update jbxx set sfcyrc=? where ryid=?";
            hyb.addParmeter(sfcyrc);
            hyb.addParmeter(ryid);
            if (hyb.update(updateSql)) {
                b = true;
            }
        }
        hyb.reallyClose();
        String json = b ? "1" : "0";
        try {
            response.getWriter().write(json);
        } catch (IOException ex) {
            Logger.getLogger(CyrcAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addOne(HttpServletRequest request, HttpServletResponse response) {
        Cyrc cyrc = new Cyrc();
        Jbxx jbxx = new Jbxx();
        Hyberbin hyb = new Hyberbin(cyrc, true);
        String ryid_s = request.getParameter("ryid");
        int ryid = 0;
        int sfcyrc = 0;
        try {
            ryid = Integer.parseInt(ryid_s);
        } catch (Exception e) {
            System.out.println("人员id出错");
        }
        ServletUtil.loadByBean(request, cyrc, true);
        cyrc.setRyid(ryid);
        boolean b = false;
        String yrzjg = cyrc.getYrzjg();
        String yzw = cyrc.getYzw();
        String zhgzsj = cyrc.getZhgzsj();
        String email = cyrc.getEmail();
        String yjzdz = cyrc.getYjzdz();
        String zyry = cyrc.getZyry();
        String insertSql = "insert into cyrc (yrzjg,yzw,zhgzsj,email,yjzdz,zyry,ryid) values(?,?,?,?,?,?,?)";
        hyb.addParmeter(yrzjg);
        hyb.addParmeter(yzw);
        hyb.addParmeter(zhgzsj);
        hyb.addParmeter(email);
        hyb.addParmeter(yjzdz);
        hyb.addParmeter(zyry);
        hyb.addParmeter(ryid);
        if (hyb.executeInsert(insertSql) != null) {
            hyb.changeTable(jbxx);
            sfcyrc = 1;//将是否是创业人才改为是
            String updateSql = "update jbxx set sfcyrc =? where ryid=?";
            hyb.addParmeter(sfcyrc);
            hyb.addParmeter(ryid);
            if (hyb.update(updateSql)) {
                b = true;
            }
        }
        hyb.reallyClose();
        String json = b ? "1" : "0";
        try {
            response.getWriter().write(json);
        } catch (IOException ex) {
            Logger.getLogger(CyrcAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteOne(HttpServletRequest request, HttpServletResponse response) {
        Cyrc cyrc = new Cyrc();
        Jbxx jbxx = new Jbxx();
        Hyberbin hyb = new Hyberbin(cyrc, true);
        String ryid_s = request.getParameter("ryid");
        int ryid = 0;
        int sfcyrc;
        try {
            ryid = Integer.parseInt(ryid_s);
        } catch (Exception e) {
            System.out.println("人员id出错");
        }
        boolean b = false;
        cyrc.setRyid(ryid);
        if (hyb.dellOneByKey("ryid")) {
            hyb.changeTable(jbxx);
            sfcyrc = 0;//将是否是创业人才改为否
            String updateSql = "update jbxx set sfcyrc=? where ryid=?";
            hyb.addParmeter(sfcyrc);
            hyb.addParmeter(ryid);
            if (hyb.update(updateSql)) {
                b = true;
            }
        }
        hyb.reallyClose();
        String json = b ? "1" : "0";
        try {
            response.getWriter().write(json);
        } catch (IOException ex) {
            Logger.getLogger(CyrcAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
