package com.hrms.manage.ncsyrc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.hrms.table.Csr;
import com.hrms.table.Jldj;
import com.hrms.table.Ncrclb;
import com.hrms.table.Ncsyrc;
import com.hrms.util.ComboboxUtil;
import com.jplus.json.JSONArray;
import com.jplus.json.JSONException;
import com.jplus.json.JSONObject;
import java.util.ArrayList;
import java.sql.Connection;
import java.io.IOException;
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
 * @author li_qi
 */
@WebServlet(name = "NcsyrcAction", urlPatterns = {"/manage/tsrcxxcx/ncsyrc/NcsyrcAction.jsp"})
public class NcsyrcAction extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public final static int OTHER = 0;//其它
    public final static int SHOWNCSYRCXX = 1;//显示农村实用人才信息
    public final static int SHOWCOMBOXLIST = 2;//显示combox列表
    public final static int UPDATE = 3;//修改数据
    public final static int DEL = 4;//删除数据
    public final static int ADD = 5;//添加数据
    public final static int SHOWONE = 6;//通过ryid获得对应id个人信息

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        excute(ServletUtil.setModel(request.getParameter("mode"), this), request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
                    Logger.getLogger(NcsyrcAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(NcsyrcAction.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case SHOWNCSYRCXX:
                showNcsyrcxx(request, response);
                break;
            case SHOWCOMBOXLIST:
                showComboxList(request, response);
                break;
            case SHOWONE:
                showone(request, response);
                break;
            case UPDATE:
                update(request, response);
                break;
            case DEL:
                del(request, response);
                break;
            case ADD:
                add(request, response);
                break;
        }
    }

    /**
     * 根据ryid获得对应的农村实用人才信息
     */
    private void showNcsyrcxx(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("====来过");
        String ryid_s = request.getParameter("ryid");
        DatabaseAccess db = new DatabaseAccess();
        EasyMapsManager easy = new EasyMapsManager(db);
        int ryid = 0;
        try {
            ryid = Integer.parseInt(ryid_s);
        } catch (Exception e) {
            System.out.println("人员id出错!!!");
        }
        String sql = "SELECT `ncsyrc`.`ncsyrcid`, `ncsyrc`.`sncsrbm`, `ncsyrc`.`rclbbm`, `ncsyrc`.`jldjbm`, `ncsyrc`.`sfwcdg` FROM `ncsyrc` where ryid="+ryid;
//        easy.setPreparedParameter(ryid);
        
        ArrayList list = easy.executeQuery(sql);
        System.out.println("对应sql语句="+sql+";size="+list.size());
        JSONArray json = new JSONArray(list);
        db.close();
        System.out.println("==="+json.toString());
        ServletUtil.ajaxData(json.toString(), response);
    }

    /**
     * combobox 获得奖励等级、农村实用人才类别、上年纯收入的列表
     *
     */
    private void showComboxList(HttpServletRequest request, HttpServletResponse response) {
        Jldj jldj = new Jldj();
        Ncrclb ncrclb = new Ncrclb();
        Csr csr = new Csr();
        Connection conn = DatabaseINI.getConnection();
        Hyberbin hyb = new Hyberbin(ncrclb, conn);
        String sql = "select * from ncrclb";
        List list = hyb.showList(sql);
        String sql1 = "select * from jldj";
        hyb.changeTable(jldj);
        List list1 = hyb.showList(sql1);
        String sql2 = "select * from csr";
        hyb.changeTable(csr);
        List list2 = hyb.showList(sql2);
        hyb.reallyClose();
        ComboboxUtil combox = new ComboboxUtil();
        String json = combox.createBatchComboJSON(list, list1, list2);
        ServletUtil.ajaxData(json, response);
    }

    /**
     * 通过人员id获得对应的人员基本信息
     */
    private void showone(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = new DatabaseAccess();
        EasyMapsManager emm = new EasyMapsManager(dao);
        String ryid_s = request.getParameter("ryid");
        int ryid = 0;
        try {
            ryid = Integer.parseInt(ryid_s);
        } catch (Exception e) {
            System.out.println("人员ID不正确");
        }
        String sql = "SELECT `gzdw`.`dwmc`, `jbxx`.`zjhm`, `jbxx`.`xm`, `jbxx`.`xb`, `jbxx`.`ryid` FROM `jbxx` INNER JOIN `gzdw` ON `jbxx`.`gzdwid` = `gzdw`.`gzdwid` where ryid=?";
        //将list转换为json字符串
        emm.setPreparedParameter(ryid);
        List list = emm.executeQuery(sql);
        dao.close();
        JSONArray json = new JSONArray(list);
        ServletUtil.ajaxData(json.toString(), response);
    }
    /**
     * 添加ryid的农村实用人才信息
     */

    private void add(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess db = new DatabaseAccess();
        String ryid_s = request.getParameter("ryid");
        String sfwcdgbn_s = request.getParameter("sfwcdgbn");
        String jldjbm_s = request.getParameter("jldjbm");
        String ncrclbbm_s = request.getParameter("ncrclbbm");
        String sncsrbm_s = request.getParameter("sncsrbm");
        int ryid = 0;
        int jldjbm = 0;
        int ncrclbbm = 0;
        int sncsrbm = 0;
        int sfwcdgbn=0;
        if(sfwcdgbn_s.equals("true")) sfwcdgbn=1;
        try {
            ryid = Integer.parseInt(ryid_s);
            jldjbm = Integer.parseInt(jldjbm_s);
            ncrclbbm = Integer.parseInt(ncrclbbm_s);
            sncsrbm = Integer.parseInt(sncsrbm_s);
            sfwcdgbn=Integer.parseInt(sfwcdgbn_s);
        } catch (Exception e) {
            System.out.println("编码不正确");
        }
        String sql = "insert into ncsyrc (ryid,jldjbm,rclbbm,sncsrbm,sfwcdg) values (?,?,?,?,?)";
        db.setPreparedParameter(ryid);
        db.setPreparedParameter(jldjbm);
        db.setPreparedParameter(ncrclbbm);
        db.setPreparedParameter(sncsrbm);
        db.setPreparedParameter(sfwcdgbn);
        boolean b = false;
        b = db.executeUpdate(sql);
        if (b) {
            String sqll = "update jbxx set sfncsyrc=1 where ryid=?";
            db.setPreparedParameter(ryid);
            b = db.executeUpdate(sqll);
        }
        db.close();
        ServletUtil.ajaxData("{\"notice\":\""+(b?"添加成功":"添加失败")+"\"}", response);
    }
    /**
     * 修改ncsyrcid农村实用人才信息
     */
    public void update(HttpServletRequest request, HttpServletResponse response){
        DatabaseAccess db = new DatabaseAccess();
        String ncsyrcid_s = request.getParameter("ncsyrcid");
        String sfwcdgbn_s = request.getParameter("sfwcdgbn");
        String jldjbm_s = request.getParameter("jldjbm");
        String ncrclbbm_s = request.getParameter("ncrclbbm");
        String sncsrbm_s = request.getParameter("sncsrbm");
        int ncsyrcid = 0;
        int jldjbm = 0;
        int ncrclbbm = 0;
        int sncsrbm = 0;
        int sfwcdgbn=0;
        if(sfwcdgbn_s.equals("true")) sfwcdgbn=1;
        try {
            ncsyrcid = Integer.parseInt(ncsyrcid_s);
            jldjbm = Integer.parseInt(jldjbm_s);
            ncrclbbm = Integer.parseInt(ncrclbbm_s);
            sncsrbm = Integer.parseInt(sncsrbm_s);
        } catch (Exception e) {
            System.out.println("编码不正确");
        }
        String sql="update ncsyrc set jldjbm=?,rclbbm=?,sncsrbm=?,sfwcdg=? where ncsyrcid=?";
        db.setPreparedParameter(jldjbm);
        db.setPreparedParameter(ncrclbbm);
        db.setPreparedParameter(sncsrbm);
        db.setPreparedParameter(sfwcdgbn);
        db.setPreparedParameter(ncsyrcid);
        boolean b = false;
        b = db.executeUpdate(sql);
        System.out.println("是否修改成功"+b);
        db.close();
        ServletUtil.ajaxData("{\"notice\":\""+(b?"修改成功":"修改失败")+"\"}", response);
    }
    /**
     * 
     */
    public void del(HttpServletRequest request, HttpServletResponse response){
        DatabaseAccess db = new DatabaseAccess();
        String ncsyrcid_s = request.getParameter("ncsyrcid");
        String ryid_s = request.getParameter("ryid");
        int ncsyrcid = 0;
        int ryid=0;
        try {
            ncsyrcid = Integer.parseInt(ncsyrcid_s);
            ryid=Integer.parseInt(ryid_s);
        } catch (Exception e) {
            System.out.println("编码不正确");
        }
        String sql="delete from ncsyrc  where ncsyrcid=?";
        db.setPreparedParameter(ncsyrcid);
        boolean b = false;
        b = db.executeUpdate(sql);
        if (b) {
            String sqll = "update jbxx set sfncsyrc=0 where ryid=?";
            db.setPreparedParameter(ryid);
            b = db.executeUpdate(sqll);
        }
        System.out.println("是否删除成功"+b);
        db.close();
        ServletUtil.ajaxData("{\"notice\":\""+(b?"删除成功":"删除失败")+"\"}", response);
    }
}
