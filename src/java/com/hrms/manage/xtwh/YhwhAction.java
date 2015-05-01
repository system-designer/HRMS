package com.hrms.manage.xtwh;

import com.hrms.manage.permission.UserContants;
import com.hrms.manage.permission.UserPermission;
import com.hrms.util.MD5;
import com.hrms.util.Util;
import com.jplus.json.EasyUiJson;
import com.jplus.json.JSONArray;
import com.jplus.json.JSONException;
import com.jplus.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

@WebServlet(name = "YhwhAction", urlPatterns = {"/manage/xtwh/YhwhAction.jsp"}, asyncSupported = true)
public class YhwhAction extends HttpServlet {

    /**
     * 下面是模式关键字 可以自行删除和增加自定义模式，关键字一定要大写 默认模式为OTHER=0,所以OTHER不能删除
     */
    public final static int OTHER = 0;//其它
    public static final int LOADLIST = 1;//
    public final static int LOADCOMBOBOX = 2;
    public final static int LOADGZDWCOMBOBOX = 3;
    public final static int ADD = 4;//添加用户
    public final static int DELETE = 5;
    public final static int UPDATE = 6;
    private UserContants userContants;

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
                    Logger.getLogger(YhwhAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(YhwhAction.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case LOADCOMBOBOX:
                loadCombobox(request, response);
                break;
            case LOADLIST:
                loadYhList(request, response);
                break;
            case LOADGZDWCOMBOBOX:
                loadGzdwCombobox(request, response);
                break;
            case ADD:
                addYh(request, response);
                break;
            case DELETE:
                deleteYh(request, response);
                break;
            case UPDATE:
                updateYh(request, response);
                break;

        }
    }

    private void loadYhList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> pMap = request.getParameterMap();
        EasyUiJson json = new EasyUiJson(request);
        StringBuilder having = new StringBuilder();
        StringBuilder sql = new StringBuilder("select yh.userid id, yh.username uname, yh.jb ulevelc, yh.lb utypec, yh.xm `name`, yh.lxdh phone,gzdw.dwmc orgz, gzdw.dwxzbm propc, gzdw.dwlbbm levelc, gzdw.ssxtbm deptc,gzdw.sshybm tradec, gzdw.gzdwid orgzc from yh inner join gzdw on yh.gzdwid = gzdw.gzdwid");
        UserPermission upermission = new UserPermission(request);
        DatabaseAccess dao = new DatabaseAccess();
        EasyMapsManager easyMapsManager = new EasyMapsManager(dao);
        userContants = userContants == null ? new UserContants() : userContants;
        filterUser(dao, upermission, sql);
        for (Map.Entry<String, String[]> entry : pMap.entrySet()) {
            String pname = entry.getKey();
            if (!com.hrms.util.Util.isEmpty(pname) && pname.endsWith("search")) {
                String name = pname.substring(0, pname.indexOf("_"));
                String[] pVal = entry.getValue();
                for (int i = 0; i < pVal.length; i++) {
                    if (!com.hrms.util.Util.isEmpty(pVal[i])) {
                        having.append(" and ").append(name).append("c").append("=?");
                        easyMapsManager.setPreparedParameter(pVal[i]);
                    }
                }
            }
        }
        sql.append(com.hrms.util.Util.isEmpty(having.toString()) ? "" : " having " + having.substring(4));
        ArrayList<HashMap> list = easyMapsManager.executeQuery(sql.toString() + " order by yh.lb", json);
        dao.close();
        dao = null;
        json.putAll(list);
        ServletUtil.ajaxData(json.toDataString(), response);
    }

    private void loadCombobox(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = null;
        try {
            dao = new DatabaseAccess();
            EasyMapsManager easyMapsManager = new EasyMapsManager(dao);
            //单位性质
            ArrayList<HashMap> propList = easyMapsManager.executeQuery("select dwxzbm propc, dwxzmc prop from dwxz where length(trim(dwxzmc))>1");
            //单位类别
            ArrayList<HashMap> levelList = easyMapsManager.executeQuery("select dwlbbm levelc,dwlbmc `level` from dwlb where length(trim(dwlbmc))>1");
            //所属系统
            ArrayList<HashMap> deptList = easyMapsManager.executeQuery("select ssxtbm deptc, ssxtmc dept from ssxt where length(trim(ssxtmc))>1");
            //所属行业
            ArrayList<HashMap> tradeList = easyMapsManager.executeQuery("select sshybm tradec, sshymc trade from sshy where length(trim(sshymc))>1");
            dao.close();
            dao = null;
            JSONObject jobj = new JSONObject();
            jobj.put("propList", new JSONArray(propList));
            jobj.put("levelList", new JSONArray(levelList));
            jobj.put("deptList", new JSONArray(deptList));
            jobj.put("tradeList", new JSONArray(tradeList));
            ServletUtil.ajaxData(jobj.toString(), response);
        } catch (Exception ex) {
            Logger.getLogger(GzdwwhAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (dao != null) {
                dao.close();
                dao = null;
            }
        }
    }

    private boolean filterUser(DatabaseAccess dao, UserPermission userPermission, StringBuilder sql) {
        userContants = userContants == null ? new UserContants() : userContants;
        sql.append(" where (yh.jb>?) or (yh.jb=? and yh.lb >?)");
        dao.setPreparedParameter(userPermission.getUser().getJb());
        dao.setPreparedParameter(userPermission.getUser().getJb());
        dao.setPreparedParameter(userPermission.getUser().getLb());
        return true;
        /**
         * sql.append(" where (yh.jb=? and yh.lb = ?) or (yh.jb=? and yh.lb =
         * ?)"); dao.setPreparedParameter(superLevel);
         * dao.setPreparedParameter(userContants.PUTONG); if
         * (userContants.SHI.equals(superLevel)) {
         * dao.setPreparedParameter(userContants.QU);
         * dao.setPreparedParameter(userContants.ADMIN); return true; } if
         * (userContants.QU.equals(superLevel)) {
         * dao.setPreparedParameter(userContants.QIYE);
         * dao.setPreparedParameter(userContants.PUTONG); return true; } return
         * false;
         *
         */
    }

    private void loadGzdwCombobox(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = null;
        try {
            Map<String, String[]> pMap = request.getParameterMap();
            StringBuilder sql = new StringBuilder("select distinct gzdw.gzdwid orgzc,gzdw.dwmc orgz, gzdw.dwxzbm propc, gzdw.dwlbbm levelc, gzdw.ssxtbm deptc,gzdw.sshybm tradec from gzdw ");
            StringBuilder having = new StringBuilder();
            UserPermission upermission = new UserPermission(request);
            dao = new DatabaseAccess();
            if (upermission.isQuLevel()) {
                sql.append(" where gzdw.ssdqbm=? ");
                dao.setPreparedParameter(upermission.getSSdqbm());
            }
            EasyMapsManager easyMapsManager = new EasyMapsManager(dao);
            for (Map.Entry<String, String[]> entry : pMap.entrySet()) {
                String pname = entry.getKey();
                if (!com.hrms.util.Util.isEmpty(pname) && pname.endsWith("crud")) {
                    String name = pname.substring(0, pname.indexOf("_"));
                    String[] pVal = entry.getValue();
                    for (int i = 0; i < pVal.length; i++) {
                        if (!com.hrms.util.Util.isEmpty(pVal[i])) {
                            having.append(" and ").append(name).append("c").append("=?");
                            easyMapsManager.setPreparedParameter(pVal[i]);
                        }
                    }
                }
            }
            sql.append(com.hrms.util.Util.isEmpty(having.toString()) ? "" : " having " + having.substring(4));
            ArrayList<HashMap> orgzList = easyMapsManager.executeQuery(sql.toString());
            dao.close();
            dao = null;
            JSONObject jobj = new JSONObject();
            jobj.put("orgzList", new JSONArray(orgzList));
            ServletUtil.ajaxData(jobj.toString(), response);
        } catch (Exception ex) {
            Logger.getLogger(GzdwwhAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (dao != null) {
                dao.close();
                dao = null;
            }
        }
    }

    private void updateYh(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = null;
        PrintWriter out = null;
        try {
            String useridStr = request.getParameter("id");
            String gzdwidStr = request.getParameter("orgz-crud");
            String username = request.getParameter("uname-crud");
            String jb = request.getParameter("utype");
            String lxdh = request.getParameter("phone-crud");
            String xm = request.getParameter("name-crud");
            Integer gzdwid = com.hrms.util.Util.isEmpty(gzdwidStr) ? 0 : Integer.parseInt(gzdwidStr);
            Integer userid = com.hrms.util.Util.isEmpty(useridStr) ? 0 : Integer.parseInt(useridStr);
            if (userid == 0) {
                throw new IllegalArgumentException("用户id不能为空!!!");
            }
            if (gzdwid == 0) {
                throw new IllegalArgumentException("单位不能为空!!!");
            }
            if (com.hrms.util.Util.isEmpty(username)) {
                throw new IllegalArgumentException("用户名不能为空!!!");
            }
            if (!Util.isMobileNO(lxdh)) {
                throw new IllegalArgumentException("号码格式不正确!!!");
            }
            if (com.hrms.util.Util.isEmpty(jb)) {
                throw new IllegalArgumentException("用户级别不能为空!!!");
            }

            dao = new DatabaseAccess();
            String sql = "update yh set username=?,jb=?,lb=?,mm=?,gzdwid=?,xm=?,lxdh=? where userid=?";
            dao.setPreparedParameter(username);
            dao.setPreparedParameter(jb);
            userContants = userContants == null ? new UserContants() : userContants;
            UserPermission upermission = new UserPermission(request);
//            if (upermission.isShiLevel()) {//市级用户登录
//                if (userContants.SHI.equals(jb)) {//如果添加级别是市级，则类别为普通用户
//                    dao.setPreparedParameter(userContants.PUTONG);
//                }
//                if (userContants.QU.equals(jb)) {//如果添加级别是区级，则类别为管理用户
//                    dao.setPreparedParameter(userContants.ADMIN);
//                }
//            }
//            if (upermission.isQuLevel()) {//区级用户登录
//                dao.setPreparedParameter(userContants.PUTONG);
//            }
            dao.setPreparedParameter(upermission.isAdmin() && userContants.QU.equals(jb) ? userContants.ADMIN : userContants.PUTONG);
            dao.setPreparedParameter(MD5.md5s("000000"));
            dao.setPreparedParameter(gzdwid);
            dao.setPreparedParameter(xm);
            dao.setPreparedParameter(lxdh);
            dao.setPreparedParameter(userid);
            boolean b = dao.executeUpdate(sql);
            dao.close();
            dao = null;
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("update", b ? true : false);
            out = response.getWriter();
            out.write(jsonObj.toString());
            out.flush();
            out.close();
            out = null;
        } catch (IOException ex) {
            Logger.getLogger(YhwhAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(YhwhAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            try {
                response.getWriter().write(new JSONObject().put("update", false).put("mode", "修改").put("notice", ex.getMessage()).toString());
            } catch (Exception e) {
                Logger.getLogger(YhwhAction.class.getName()).log(Level.SEVERE, null, e);
            }
        } finally {
            if (dao != null) {
                dao.close();
                dao = null;
            }
            if (out != null) {
                out.close();
                out = null;
            }
        }

    }

    private void addYh(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = null;
        PrintWriter out = null;
        try {

            String gzdwidStr = request.getParameter("orgz-crud");
            String username = request.getParameter("uname-crud");
            String jb = request.getParameter("utype");
            String lxdh = request.getParameter("phone-crud");
            String xm = request.getParameter("name-crud");
            Integer gzdwid = com.hrms.util.Util.isEmpty(gzdwidStr) ? 0 : Integer.parseInt(gzdwidStr);
            if (gzdwid == 0) {
                throw new IllegalArgumentException("单位不能为空!!!");
            }
            if (com.hrms.util.Util.isEmpty(username)) {
                throw new IllegalArgumentException("用户名不能为空!!!");
            }
            if (!Util.isMobileNO(lxdh)) {
                throw new IllegalArgumentException("号码格式不正确!!!");
            }
            if (com.hrms.util.Util.isEmpty(jb)) {
                throw new IllegalArgumentException("用户级别不能为空!!!");
            }

            dao = new DatabaseAccess();
            String sql = "insert into yh(username,jb,lb,mm,gzdwid,xm,lxdh) values(?,?,?,?,?,?,?)";
            dao.setPreparedParameter(username);
            dao.setPreparedParameter(jb);
            userContants = userContants == null ? new UserContants() : userContants;
            UserPermission upermission = new UserPermission(request);
//            if (upermission.isShiLevel()) {//市级用户登录
//                if (userContants.SHI.equals(jb)) {//如果添加级别是市级，则类别为普通用户
//                    dao.setPreparedParameter(userContants.PUTONG);
//                }
//                if (userContants.QU.equals(jb)) {//如果添加级别是区级，则类别为管理用户
//                    dao.setPreparedParameter(userContants.ADMIN);
//                }
//            }
//            if (upermission.isQuLevel()) {//区级用户登录
//                dao.setPreparedParameter(userContants.PUTONG);
//            }
            dao.setPreparedParameter(upermission.isAdmin() && userContants.QU.equals(jb) ? userContants.ADMIN : userContants.PUTONG);
            dao.setPreparedParameter(MD5.md5s("000000"));
            dao.setPreparedParameter(gzdwid);
            dao.setPreparedParameter(xm);
            dao.setPreparedParameter(lxdh);
            boolean b = dao.executeUpdate(sql);
            dao.close();
            dao = null;
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("update", b ? true : false);
            out = response.getWriter();
            out.write(jsonObj.toString());
            out.flush();
            out.close();
            out = null;
        } catch (IOException ex) {
            Logger.getLogger(YhwhAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(YhwhAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            try {
                response.getWriter().write(new JSONObject().put("update", false).put("mode", "添加").put("notice", ex.getMessage()).toString());
            } catch (Exception e) {
                Logger.getLogger(YhwhAction.class.getName()).log(Level.SEVERE, null, e);
            }
        } finally {
            if (dao != null) {
                dao.close();
                dao = null;
            }
            if (out != null) {
                out.close();
                out = null;
            }
        }
    }

    private void deleteYh(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = null;
        PrintWriter out = null;
        try {
            String useridStr = request.getParameter("id");
            dao = new DatabaseAccess();
            String sql = "delete from yh where userid=?";
            Integer userid = com.hrms.util.Util.isEmpty(useridStr) ? 0 : Integer.parseInt(useridStr);
            if (userid == 0) {
                throw new IllegalArgumentException("用户id不能为空!!!");
            }
            dao.setPreparedParameter(userid);
            boolean b = dao.executeUpdate(sql);
            dao.close();
            dao = null;
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("update", b ? true : false);
            out = response.getWriter();
            out.write(jsonObj.toString());
            out.flush();
            out.close();
            out = null;
        } catch (JSONException ex) {
            Logger.getLogger(YhwhAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(YhwhAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException e) {
            try {
                response.getWriter().write(new JSONObject().put("update", false).put("mode", "删除").put("notice", e.getMessage()).toString());
            } catch (Exception ex) {
                Logger.getLogger(YhwhAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            if (dao != null) {
                dao.close();
                dao = null;
            }
            if (out != null) {
                out.close();
                out = null;
            }
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
}
