package com.hrms.manage.xtwh;

import com.hrms.manage.permission.UserPermission;
import com.hrms.util.EnumUtil;
import com.hrms.util.FileAction;
import com.hrms.util.Util;
import com.jplus.json.EasyUiJson;
import com.jplus.json.JSONArray;
import com.jplus.json.JSONObject;
import java.io.File;
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

/**
 * 工作单位维护后台servlet
 *
 * @author zc
 */
@WebServlet(name = "GzdwwhAction", urlPatterns = {"/manage/xtwh/GzdwwhAction.jsp"}, asyncSupported = true)
public class GzdwwhAction extends HttpServlet {

    /**
     * 下面是模式关键字 可以自行删除和增加自定义模式，关键字一定要大写 默认模式为OTHER=0,所以OTHER不能删除
     */
    public final static int OTHER = 0;//其它
    public static final int LOADLIST = 1;//工作单位list
    public static final int LOADCOMBOBOX = 2;//加载工作单位combobox
    public static final int ADDGZDW = 3;
    public static final int UPDATEGZDW = 4;
    public static final int DELETEGZDW = 5;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            case LOADLIST:
                loadGzdwList(request, response);
                break;
            case LOADCOMBOBOX:
                loadCombobox(request, response);
                break;
            case ADDGZDW:
                GzdwCUD(request, response, "insert into gzdw(dwmc,dwxzbm,dwlbbm,ssxtbm,sshybm,dwdh,dwdz,hypy,lxr,dzyx,dwjj,username) values(?,?,?,?,?,?,?,?,?,?,?,?)", false, true, true, false, new UserPermission(request));
                break;
            case UPDATEGZDW:
                GzdwCUD(request, response, "update gzdw set dwmc=?,dwxzbm=?,dwlbbm=?,ssxtbm=?,sshybm=?,dwdh=?,dwdz=?,hypy=?,lxr=?,dzyx=?,dwjj=?,username=? where gzdwid=?", true, true, true, false, null);
                break;
            case DELETEGZDW:
                GzdwCUD(request, response, "delete from gzdw where gzdwid=?", true, false, false, false, null);
                break;


        }
    }

    private void loadGzdwList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> pMap = request.getParameterMap();
        EasyUiJson json = new EasyUiJson(request);
        StringBuilder having = new StringBuilder();
        StringBuilder sql = new StringBuilder("select gzdw.gzdwid id, gzdw.dwmc `name`,dwxz.dwxzmc prop,dwlb.dwlbmc `level`, ssxt.ssxtmc dept,sshy.sshymc trade, gzdw.dwdh phone, gzdw.dwdz addr,gzdw.hypy,gzdw.dwxzbm propc,gzdw.dwlbbm levelc,gzdw.ssxtbm deptc, gzdw.sshybm tradec,gzdw.ssdqbm ssdqc from gzdw left join dwxz on gzdw.dwxzbm = dwxz.dwxzbm left join ssxt on gzdw.ssxtbm = ssxt.ssxtbm left join sshy on gzdw.sshybm = sshy.sshybm left join dwlb on gzdw.dwlbbm = dwlb.dwlbbm");
        UserPermission upermission = new UserPermission(request);
        DatabaseAccess dao = new DatabaseAccess();
        EasyMapsManager easyMapsManager = new EasyMapsManager(dao);
        if (upermission.isQuLevel()) {
            easyMapsManager.setPreparedParameter(upermission.getSSdqbm());
            sql.append(" where gzdw.ssdqbm = ?");
        } else if (upermission.isQiYeLevel()){
            easyMapsManager.setPreparedParameter(upermission.getUser().getGzdwid());
            sql.append(" where gzdw.gzdwid = ?");
        }
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
        ArrayList<HashMap> list = easyMapsManager.executeQuery(sql.toString(), json);
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
            UserPermission upermission = new UserPermission(request);
            jobj.put("ssdqList", new JSONArray(EnumUtil.getSsdqList(upermission)));
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

    /**
     * 工作单位 添加、修改、删除
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param sql sql语句
     * @param doSetid 是否预处理主键
     * @param doSetOther 是否预处理其他字段
     * @param needValidate 是否需要验证表单
     * @param deleteFolder 是否删除工作单位文件夹
     */
    private void GzdwCUD(HttpServletRequest request, HttpServletResponse response, String sql, boolean doSetPrimaryKey, boolean doSetOther, boolean needValidate, boolean deleteFolder, UserPermission userPermission) {
        DatabaseAccess dao = null;
        PrintWriter out = null;
        try {
            GzdwCrudFormBean bean = new GzdwCrudFormBean(request);
            out = response.getWriter();
            if (bean.validate(needValidate)) {
                dao = new DatabaseAccess();
                bean.setParamByOrder(dao, doSetPrimaryKey, doSetOther);
                if (userPermission != null && !userPermission.isShiLevel()) {
                    sql = Util.addField2InsertSql(sql, "sjdwid");
                    dao.setPreparedParameter(userPermission.getUser().getGzdwid());
                }
                boolean bool = dao.executeUpdate(sql);
                dao.close();
                dao = null;
                bean.jsonObj.put("update", bool ? true : false);
                if (deleteFolder && bool) {
                    bean.deleteGzdwFolder(request);
                }
            } else {
                bean.jsonObj.put("update", false);
            }
            out.write(bean.jsonObj.toString());
            out.flush();
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(GzdwwhAction.class.getName()).log(Level.SEVERE, null, ex);
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
