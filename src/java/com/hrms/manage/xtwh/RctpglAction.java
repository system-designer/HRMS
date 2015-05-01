package com.hrms.manage.xtwh;

import com.hrms.util.Folder;
import com.hrms.util.Util;
import com.jplus.json.JSONArray;
import com.jplus.json.JSONException;
import com.jplus.json.JSONObject;
import java.io.File;
import java.io.IOException;
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
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jplus.hyb.servlet.ServletUtil;
import org.jplus.yydbgai.DatabaseAccess;
import org.jplus.yydbgai.EasyMapsManager;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "RctpglAction", urlPatterns = {"/manage/xtwh/RctpglAction.jsp"})
public class RctpglAction extends HttpServlet {

    /**
     * 下面是模式关键字 可以自行删除和增加自定义模式，关键字一定要大写 默认模式为OTHER=0,所以OTHER不能删除
     */
    public final static int OTHER = 0;//其它
    public final static int SHOWIMGS = 1;//显示列表
    public final static int LOADCOMBOBOX = 2;
    public final static int UPLOAD = 3;
    public final static int DELETE = 4;

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
                    Logger.getLogger(RctpglAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(RctpglAction.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case SHOWIMGS:
                showimgs(request, response);
                break;
            case LOADCOMBOBOX:
                loadCombobox(request, response);
                break;
            case UPLOAD:
                upload(request, response);
                break;
            case DELETE:
                delete(request, response);
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

    private void upload(HttpServletRequest request, HttpServletResponse response) {
        String gzdwid = request.getParameter("orgzc");
        String gzdw = request.getParameter("orgz");
        String path = Util.getFileServerPath("/userfiles/photo/" + gzdwid, request);
        if (!Util.isEmpty(path) && !path.contains("WEB-INF")) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List fileList = null;
            try {
                fileList = upload.parseRequest(request);
            } catch (FileUploadException ex) {
                ex.printStackTrace(System.out);
            }
            boolean u = Util.upload(fileList, path.replace("/", "\\"), false, false, Constants.LIMIT_TYPE);
            ServletUtil.ajaxData(u ? "1" : "0", response);
        }

    }

    private void loadCombobox(HttpServletRequest request, HttpServletResponse response) {
        DatabaseAccess dao = new DatabaseAccess();
        EasyMapsManager easyMapsManager = new EasyMapsManager(dao);
        ArrayList<HashMap> list = easyMapsManager.executeQuery("select gzdwid orgzc,dwmc orgz from gzdw");
        dao.close();
        JSONArray jsonArr = new JSONArray(list);
        ServletUtil.ajaxData("{\"orgzs\":" + jsonArr + "}", response);
    }

    private void showimgs(HttpServletRequest request, HttpServletResponse response) {
        String gzdwid = request.getParameter("orgzVal");//工作单位id
        String gzdwmc = request.getParameter("orgzText");//工作单位名称
        if (!Util.isEmpty(gzdwid) && !Util.isEmpty(gzdwmc)) {
            String folder = Util.getFileServerPath("/userfiles/photo/" + gzdwid, request);
            if (!folder.contains("WEB-INF")) {
                File folderFile = new File(folder);
                if (!folderFile.exists()) {
                    folderFile.mkdirs();
                }
                File[] files = folderFile.listFiles();
                ArrayList<Folder> filesList = new ArrayList<Folder>();
                for (File file : files) {
                    String imgPath = file.getPath();
                    imgPath = imgPath.substring(imgPath.indexOf(Constants.IMGPATH)).replace("\\", "/");
                    Folder folderTemp = new Folder(file.getName(), imgPath);
                    filesList.add(folderTemp);
                }
                JSONArray array = new JSONArray(filesList);
                ServletUtil.ajaxData("{\"imgs\":" + array + ",\"folder\":\"" + folder.replace("\\", "/") + "\"}", response);
            }
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        try {
            String jsonIMG = request.getParameter("jsonIMG");
            Integer delete = 0;
            JSONArray array = null;
            try {
                array = new JSONArray(jsonIMG);
            } catch (JSONException ex) {
                Logger.getLogger(RctpglAction.class.getName()).log(Level.SEVERE, null, ex);
                delete = -1;
            }
            JSONObject obj;
            String path;
            String basePath = request.getServletContext().getRealPath("/");
            try {
                if (array != null) {
                    for (int i = 0; i < array.length(); i++) {
                        obj = array.getJSONObject(i);
                        path = obj.getString("path");
                        path = basePath + path.substring(path.indexOf("userfiles")).replace("/", "\\");
                        File file = new File(path);
                        if (file.exists() && file.canWrite()) {
                            boolean bool = file.delete();
                            if (bool) {
                                delete++;
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(RctpglAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            ServletUtil.ajaxData(delete + "", response);
        } catch (Exception ex) {
            Logger.getLogger(RctpglAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
