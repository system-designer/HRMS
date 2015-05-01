package com.hrms.manager.jnrc;

import com.hrms.table.Zgzs;
import com.jplus.json.EasyUiJson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
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
 * @author Administrator
 */
@WebServlet(name = "ZgzsJAction", urlPatterns = {"/manage/tsrcxx/jnrc/ZgzsAction.jsp"})
public class ZgzsAction extends HttpServlet {

    /**
     * 下面是模式关键字 可以自行删除和增加自定义模式，关键字一定要大写 默认模式为OTHER=0,所以OTHER不能删除
     */
    public final static int OTHER = 0;//其它
    public final static int ADDONE = 1;//显示单例
    public final static int SHOWLIST = 2;//显示列表
    public final static int SHOWONE = 3;//显示一条基本信息
    public final static int GET=4;
    public final static int DELETEONE=5;
    public final static int UPDATE=6;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String mode = request.getParameter("mode");
        excute(ServletUtil.setModel(mode, this), request, response);
    }

    /**
     * 主执行方法
     *
     * @param event 方法ID
     */
    private void excute(int event, HttpServletRequest request, HttpServletResponse response) {
        switch (event) {
            case SHOWLIST:
                showList(request, response);
                break;
            case ADDONE:
                addOne(request, response);
                break;
            case SHOWONE:
                showOne(request, response);
                break;
            case GET:
                get(request,response);
                break;
            case DELETEONE:
                deleteOne(request,response);
                break;
            case UPDATE:
                update(request,response);
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
        DatabaseAccess dao = new DatabaseAccess();
        EasyMapsManager emm = new EasyMapsManager(dao);
        String sql = "SELECT `zgzs`.`gz`, `zgzs`.`dj`, `zgzs`.`zsh`, `zgzs`.`fzsj`, `zgzs`.`fzjg`, `zgzs`.`zgzsid` FROM `zgzs` where `zgzs`.`ryid`="+request.getParameter("ryid");
        EasyUiJson datagrid = new EasyUiJson(request);
        ArrayList<HashMap> dwList = emm.executeQuery(sql, datagrid);
        dao.close();
        datagrid.putAll(dwList);
        ServletUtil.ajaxData(datagrid.toDataString(), response);
    }

    /**
     * 增加一个荣誉
     */
    private void addOne(HttpServletRequest request, HttpServletResponse response) {
        Zgzs zgzs=new Zgzs();
        ServletUtil.loadByParams(request, zgzs, true);
        zgzs.setRyid(Integer.parseInt(request.getParameter("ryid")));
        Hyberbin hyb=new Hyberbin(zgzs);
        boolean insert = hyb.insert("");
        hyb.close();
        PrintWriter out=null;
        try {
            out=response.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(ZgzsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("text/html;charset=utf-8");
        if(insert){
            out.write("1");
        }else{
            out.write("0");
        }
        out.flush();
        out.close();
    }

    private void showOne(HttpServletRequest request, HttpServletResponse response) {
        String ryid = request.getParameter("ryid");
        String sql = "SELECT "
                + "`jbxx`.`xm`, `jbxx`.`xb`, `jbxx`.`zjhm`, `gzdw`.`dwmc` "
                + "FROM "
                + "`jbxx` INNER JOIN "
                + "`gzdw` ON `jbxx`.`gzdwid` = `gzdw`.`gzdwid` where `jbxx`.`ryid`=?";
        DatabaseAccess db = new DatabaseAccess();
        db.setPreparedParameter(Integer.parseInt(ryid));
        ResultSet rs = db.executeQuery(sql);//返回查询结果集
        String json=UtilTools.toJson(rs);
        db.close();
        ServletUtil.ajaxData(json, response);
    }

    private void get(HttpServletRequest request, HttpServletResponse response) {
        String zgzsid=request.getParameter("zgzsid");
        String sql="SELECT `zgzs`.`dj`, `zgzs`.`gz`, `zgzs`.`zsh`, `zgzs`.`fzsj`, `zgzs`.`fzjg` FROM `zgzs` where `zgzs`.`zgzsid`=?";
        DatabaseAccess db=new DatabaseAccess();
        db.setPreparedParameter(Integer.parseInt(zgzsid));
        ResultSet executeQuery = db.executeQuery(sql);
        String toJson = UtilTools.toJson(executeQuery);
        db.close();
        ServletUtil.ajaxData(toJson, response);
    }

    private void deleteOne(HttpServletRequest request, HttpServletResponse response) {
        String zgzsid=request.getParameter("zgzsid");
        Zgzs zgzs=new Zgzs();
        zgzs.setZgzsid(Integer.parseInt(zgzsid));
        Hyberbin hyb=new Hyberbin(zgzs);
        boolean dellOneByKey = hyb.dellOneByKey("zgzsid");
        hyb.close();
        if(dellOneByKey){
            ServletUtil.ajaxData("{\"statue\":\"success\"}", response);//{"statue":"success"}
        }else{
            
        }ServletUtil.ajaxData("{\"statue\":\"false\"}", response);//{"statue":"success"}
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        Zgzs zgzs=new Zgzs();
        ServletUtil.loadByParams(request, zgzs, true);
        zgzs.setZgzsid(Integer.parseInt(request.getParameter("zgzsid")));
        Hyberbin hyb=new Hyberbin(zgzs);
        Boolean b=hyb.updateByKey("zgzsid");
        hyb.close();
    }
}
