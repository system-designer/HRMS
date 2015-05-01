package com.hrms.manager.jnrc;

import com.hrms.table.Ryjl;
import com.jplus.json.EasyUiJson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
@WebServlet(name = "RyjlAction", urlPatterns = {"/manage/tsrcxx/jnry/RyjlAction.jsp"})
public class RyjlAction extends HttpServlet {

    /**
     * 下面是模式关键字 可以自行删除和增加自定义模式，关键字一定要大写 默认模式为OTHER=0,所以OTHER不能删除
     */
    public final static int OTHER = 0;//其它
    public final static int SHOWONE = 1;//显示单例
    public final static int SHOWLIST = 2;//显示列表
    public final static int ADDONE=3;
    public final static int GET=4;
    public final static int UPDATE=5;
    public final static int DELETE=6;

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
                addOne(request,response);
                break;
            case GET:
                get(request,response);
                break;
            case UPDATE:
                update(request,response);
                break;
            case DELETE:
                deleteOne(request,response);
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
        String sql = "SELECT `ryjl`.`ryjlid`, `ryjl`.`ryjlmc`, `ryjl`.`syjg`, `ryjl`.`sj` FROM `ryjl` where `ryjl`.`ryid`="+request.getParameter("ryid");
        EasyUiJson datagrid = new EasyUiJson(request);
        ArrayList<HashMap> dwList = emm.executeQuery(sql, datagrid);
        dao.close();
        datagrid.putAll(dwList);
        ServletUtil.ajaxData(datagrid.toDataString(), response);
    }

    private void addOne(HttpServletRequest request, HttpServletResponse response) {
        Ryjl ryjl=new Ryjl();
        ServletUtil.loadByParams(request, ryjl, true);
        ryjl.setRyid(Integer.parseInt(request.getParameter("ryid")));
        Hyberbin hyb=new Hyberbin(ryjl);
        hyb.insert("");
        hyb.close();
    }

    private void get(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("jin");
        DatabaseAccess db=new DatabaseAccess();
        String sql="SELECT `ryjl`.`ryjlmc`, `ryjl`.`syjg`, `ryjl`.`sj` FROM `ryjl` where `ryjl`.`ryjlid`=? ";
        db.setPreparedParameter(Integer.parseInt(request.getParameter("ryjlid")));
        String json=UtilTools.toJson(db.executeQuery(sql));
        db.close();
        ServletUtil.ajaxData(json, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        Ryjl ryjl=new Ryjl();
        ServletUtil.loadByParams(request, ryjl, true);
        ryjl.setRyjlid(Integer.parseInt(request.getParameter("ryjlid")));
        Hyberbin hyb=new Hyberbin(ryjl);
        hyb.updateByKey("ryjlid");
        hyb.close();
    }

    private void deleteOne(HttpServletRequest request, HttpServletResponse response) {
        Ryjl ryjl=new Ryjl();
        ryjl.setRyjlid(Integer.parseInt(request.getParameter("ryjlid")));
        Hyberbin hyb=new Hyberbin(ryjl);
        hyb.dellOneByKey("ryjlid");
        hyb.close();
    }
}
