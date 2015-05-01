package com.hrms.manage.tjbb;

import com.hrms.table.Tjbb;
import com.hrms.table.Yh;
import com.hrms.util.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jplus.hyb.database.Hyberbin;
import org.jplus.hyb.servlet.ServletUtil;
import org.jplus.yydbgai.DatabaseAccess;
import org.jplus.yydbgai.EasyMapsManager;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "HrmsTjbbAction", urlPatterns = {"/manage/tjbb/HrmsTjbbAction.jsp"})
public class HrmsTjbbAction extends HttpServlet {

    /**
     * 下面是模式关键字 可以自行删除和增加自定义模式，关键字一定要大写 默认模式为OTHER=0,所以OTHER不能删除
     */
    public final static int OTHER = 0;//其它
    public final static int DOWNLOADEXCEL = 1;//下载excel
    private String[] excelHeader = {"序号", "姓名", "性别", "出生年月", "籍贯", "政治面貌", "学历", "学位",
        "工作单位", "从事专业", "职称", "职务", "联系电话", "备注"};

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
                    Logger.getLogger(HrmsTjbbAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(HrmsTjbbAction.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case DOWNLOADEXCEL:
                downloadExcel(request, response);
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
     * 下载excel
     */
    private void downloadExcel(HttpServletRequest request, HttpServletResponse response) {
        try {
            String temp = request.getParameter("mark");
            if (!Util.isInteger(temp)) {
                temp = "-1";
            }
            Integer mark = Integer.parseInt(temp);
            String path = request.getRealPath("");
            String prepath = path + "\\WEB-INF\\hrmsexcel";
            String filename = "";
            List list = null;
            switch (mark) {
                case 0://党政人才
                    filename = "dzrc.xls";
                    list = getDzrcList(request, response);
                    break;
                case 1://管理人才
                    filename = "glrc.xls";
                    list = getGlrcList(request, response);
                    break;
                case 2://专技人才
                    filename = "zjrc.xls";
                    list = getZjrcList(request, response);
                    break;
                case 3://高技能人才
                    filename = "gjnrc.xls";
                    list = getGjnrcList(request, response);
                    break;
                case 4://农村实用人才
                    filename = "ncsyrc.xls";
                    list = getNcsyrcList(request, response);
                    break;
                case 5://社会工作人才
                    filename = "shgzrc.xls";
                    list = getShgzrcList(request, response);
                    break;
                case 6://创业人才
                    filename = "cyrc.xls";
                    list = getCyrcList(request, response);
                    break;
                case 7://企业管理人才
                    filename = "qyglrc.xls";
                    list = getQyglrc(request, response);
                    break;
                default:
                    break;
            }
            ExcelAction.createExcel(1, 2, 14, prepath + "\\" + filename, excelHeader, new Tjbb(), list);
            File file = new File(prepath + "\\" + filename);//得到事先创建好了的excel文件
            //告诉浏览器为下载连接
            response.setHeader("content-disposition", "attachment;filename=" + filename);
            //如果显示给用户的下载对话框中是中文文件名的话，要经过url编码 
            //创建读写流将文件读写到磁盘上
            FileInputStream in = new FileInputStream(file);
            OutputStream out = response.getOutputStream();
            int len = 0;
            byte buffer[] = new byte[1024];
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            in.close();
            out.close();
            file.delete();
        } catch (Exception ex) {
            Logger.getLogger(HrmsTjbbAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List getCyrcList(HttpServletRequest request, HttpServletResponse response) {
        Tjbb tjbb = new Tjbb();
        Hyberbin hyb = null;
        List list = null;
        try {
            hyb = new Hyberbin(tjbb);
            int year = new Date(System.currentTimeMillis()).getYear() + 1900;
            int nl1 = 0;
            int nl2 = 100;
            String sql = "SELECT `jbxx`.`xm`, `jbxx`.`xb`, `gzdw`.`dwmc` AS `gzdw`, `xl`.`xlmc` AS `xl`,`jbxx`.`csrq`, `jbxx`.`zw`, `zzmm`.`zzmmmc` AS `zzmm`, `jbxx`.`zymc` AS `cszy`, `jbxx`.`lxdh`, `xw`.`xwmc` AS `xw`, `jbxx`.`jg` FROM `cyrc` LEFT JOIN `jbxx` ON `cyrc`.`ryid` = `jbxx`.`ryid` LEFT JOIN `xl` ON `xl`.`xlbm` = `jbxx`.`xlbm` LEFT JOIN `gzdw` ON `jbxx`.`gzdwid` = `gzdw`.`gzdwid` LEFT JOIN `zzmm` ON `zzmm`.`zzmmbm` = `jbxx`.`zzmmbm` LEFT JOIN `xw` ON `jbxx`.`xwbm` = `xw`.`xwbm` WHERE ";
            String xlys = request.getParameter("hidden_xlys");
            String xwys = request.getParameter("hidden_xwys");
            String xltj = xlys.equals("true") ? "<=" : "=";
            String xwtj = xwys.equals("true") ? "<=" : "=";
            String dwxzbm = request.getParameter("dwxz");
            String xwbm = request.getParameter("xw");
            String xlbm = request.getParameter("xl");
            String dwlbbm = request.getParameter("dwlb");
            String ssxtbm = request.getParameter("ssxt");
            String sshybm = request.getParameter("sshy");
            try {
                nl1 = Integer.parseInt(request.getParameter("nl1"));
                nl1 = nl1 < 0 ? 0 : nl1;
            } catch (Exception e) {
                nl1 = 0;
            }
            try {
                nl2 = Integer.parseInt(request.getParameter("nl2"));
                nl2 = nl2 < 0 ? 0 : nl2;
            } catch (Exception e) {
                nl2 = 100;
            }
            String where = " (" + year + "-year(csrq)) between ? and ?";
            hyb.addParmeter(nl1);
            hyb.addParmeter(nl2);
            Yh yh = (Yh) request.getSession().getAttribute("user");
            String jb = yh.getJb();
            where += jb.equals("1") ? "" : jb.equals("2") ? " and `gzdw`.`gzdwid`=" + yh.getGzdwid() + " or `gzdw`.`sjdwid`=" + yh.getGzdwid() : " and `gzdw`.`gzdwid`=" + yh.getGzdwid();
            if (!Util.isEmpty(xwbm)) {
                where = where + " and `xw`.`xwbm` " + xwtj + "?";
                hyb.addParmeter(xwbm);
            }
            if (!Util.isEmpty(xlbm)) {
                where = where + " and `jbxx`.`xlbm`" + xltj + "?";
                hyb.addParmeter(xlbm);
            }
            String dwmc = request.getParameter("gzdw");
            if (!(Util.isEmpty(dwmc))) {
                where = where + " and `gzdw`.`dwmc` = ? ";
                hyb.addParmeter(dwmc);
            } else {
                if (!Util.isEmpty(dwxzbm)) {
                    where = where + " and dwxzbm=?";
                    hyb.addParmeter(dwxzbm);
                }
                if (!Util.isEmpty(dwlbbm)) {
                    where = where + " and dwlbbm=?";
                    hyb.addParmeter(dwlbbm);
                }
                if (!Util.isEmpty(ssxtbm)) {
                    where = where + " and ssxtbm=?";
                    hyb.addParmeter(ssxtbm);
                }
                if (!Util.isEmpty(sshybm)) {
                    where = where + " and sshybm=? ";
                    hyb.addParmeter(sshybm);
                }
            }
            sql += where;
            list = hyb.showList(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private List getDzrcList(HttpServletRequest request, HttpServletResponse response) {
        Tjbb tjbb = new Tjbb();
        Hyberbin hyb = null;
        List list = null;
        try {
            hyb = new Hyberbin(tjbb);
            int year = new Date(System.currentTimeMillis()).getYear() + 1900;
            int nl1 = 0;
            int nl2 = 100;
            String sql = "";
            String where = "";
            HttpSession session = request.getSession(true);
            Yh yh = (Yh) session.getAttribute("user");
            String jb = yh.getJb();//用户级别
            Integer gzdwid = yh.getGzdwid();//用户工作单位id
            String zjys = request.getParameter("hidden_zjys");//职级以上
            String xlys = request.getParameter("hidden_xlys");//学历以上
            String xltj = xlys.equals("true") ? "<=" : "=";
            String zjtj = zjys.equals("true") ? "<=" : "=";
            String dwxzbm = request.getParameter("dwxz");//单位性质编码
            String zjbm = request.getParameter("zj");//职级编码
            String xlbm = request.getParameter("xl");//学历编码
            String dwlbbm = request.getParameter("dwlb");//单位类别编码
            String ssxtbm = request.getParameter("ssxt");//所属系统编码
            String zzmmbm = request.getParameter("zzmm");//工作单位编码
            String dwmc = request.getParameter("gzdw");//单位名称
            try {
                nl1 = Integer.parseInt(request.getParameter("nl1"));
                nl2 = Integer.parseInt(request.getParameter("nl2"));
            } catch (Exception e) {
                nl1 = 0;
                nl2 = 100;
            }
            where = "  (" + year + "-year(csrq)) between ? and ?";
            hyb.addParmeter(nl1);
            hyb.addParmeter(nl2);
            where += jb.equals("1") ? "" : jb.equals("2") ? " and `gzdw`.`gzdwid`=" + gzdwid + " or `gzdw`.`sjdwid`=" + gzdwid : " and `gzdw`.`gzdwid`=" + gzdwid;
            if (!Util.isEmpty(zjbm)) {
                where = where + "and `jbxx`.`zjbm`" + zjtj + "? ";
                hyb.addParmeter(zjbm);
            }
            if (!Util.isEmpty(xlbm)) {
                where = where + " and `jbxx`.`xlbm`" + xltj + "?";
                hyb.addParmeter(xlbm);
            }
            if (!(Util.isEmpty(dwmc))) {//如果单位名称不为空
                where = where + " and `gzdw`.`dwmc` = ? ";
                hyb.addParmeter(dwmc);
            }
            if (!Util.isEmpty(dwxzbm)) {
                where = where + " and dwxzbm=?";
                hyb.addParmeter(dwxzbm);
            }
            if (!Util.isEmpty(dwlbbm)) {
                where = where + " and dwlbbm=?";
                hyb.addParmeter(dwlbbm);
            }
            if (!Util.isEmpty(ssxtbm)) {
                where = where + " and ssxtbm=?";
                hyb.addParmeter(ssxtbm);
            }
            if (!Util.isEmpty(zzmmbm)) {
                where = where + "  and zzmmbm=?";
                hyb.addParmeter(zzmmbm);
            }
            sql = "SELECT `gzdw`.`dwmc` AS `gzdw`, `xl`.`xlmc` AS `xl`, `jbxx`.`csrq`, `jbxx`.`zw`,`jbxx`.`xm`, `jbxx`.`xb`, `xw`.`xwmc` AS `xw`, `zzmm`.`zzmmmc` AS `zzmm`, `jbxx`.`jg`, `jbxx`.`zymc` AS `cszy`, `jbxx`.`lxdh` FROM `jbxx` LEFT JOIN `xl` ON `xl`.`xlbm` = `jbxx`.`xlbm` LEFT JOIN `gzdw` ON `jbxx`.`gzdwid` = `gzdw`.`gzdwid` RIGHT JOIN `dzrc` ON `dzrc`.`ryid` = `jbxx`.`ryid` INNER JOIN `zj` ON `jbxx`.`zjbm` = `zj`.`zjbm` INNER JOIN `xw` ON `jbxx`.`xwbm` = `xw`.`xwbm` INNER JOIN `zzmm` ON `jbxx`.`zzmmbm` = `zzmm`.`zzmmbm` WHERE ";
            sql += where;
            list = hyb.showList(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private List getGlrcList(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private List getZjrcList(HttpServletRequest request, HttpServletResponse response) {
        Tjbb tjbb = new Tjbb();
        Hyberbin hyb = new Hyberbin(tjbb);
        String dwxzbm = request.getParameter("dwxz");
        String dwlbbm = request.getParameter("dwlb");
        String ssxtbm = request.getParameter("ssxt");
        String dwmc = request.getParameter("dwmc");
        String zclbbm = request.getParameter("zclb");
        String zc = request.getParameter("zc");
        String zcys = request.getParameter("hidden_zcys");
        String xlbm = request.getParameter("xl");
        String xlys = request.getParameter("hidden_xlys");
        String xwbm = request.getParameter("xw");
        String xwys = request.getParameter("hidden_xwys");
        String nl1 = request.getParameter("nl1");
        String nl2 = request.getParameter("nl2");
        int nl1_sj = 0;
        int nl2_sj = 0;
        try {
            nl1_sj = Integer.parseInt(nl1);
            nl2_sj = Integer.parseInt(nl2);
        } catch (NumberFormatException numberFormatException) {
            numberFormatException.printStackTrace();
        }
        int year = new Date(System.currentTimeMillis()).getYear() + 1900;
//        String sql="SELECT `gzdw`.`dwmc` AS `gzdw`, `zc`.`zcmc` AS `zc`,`xl`.`xlmc` AS `xl`, `jbxx`.`csrq`, `jbxx`.`zw`,`jbxx`.`xm`, `jbxx`.`xb`, `xw`.`xwmc` AS `xw`, `zzmm`.`zzmmmc` AS `zzmm`, `jbxx`.`jg`, `jbxx`.`zymc` AS `cszy`, `jbxx`.`lxdh` FROM `jbxx` LEFT JOIN `xl` ON `xl`.`xlbm` = `jbxx`.`xlbm` LEFT JOIN `gzdw` ON `jbxx`.`gzdwid` = `gzdw`.`gzdwid`  RIGHT JOIN `zyjsrc` ON `zyjsrc`.`ryid` = `jbxx`.`ryid` INNER JOIN `zc` ON `zyjsrc`.`jszcbm` = `zc`.`zcbm` INNER JOIN `xw` ON `jbxx`.`xwbm` = `xw`.`xwbm` INNER JOIN `zzmm` ON `jbxx`.`zzmmbm` = `zzmm`.`zzmmbm` WHERE 1=1 ";
        String sql = "SELECT `gzdw`.`dwmc` AS `gzdw`, `zc`.`zcmc` AS `zc`,`xl`.`xlmc` AS `xl`, `jbxx`.`csrq`, `jbxx`.`zw`,`jbxx`.`xm`, `jbxx`.`xb`, `xw`.`xwmc` AS `xw`, `zzmm`.`zzmmmc` AS `zzmm`, `jbxx`.`jg`, `jbxx`.`zymc` AS `cszy`, `jbxx`.`lxdh` FROM `jbxx`  JOIN `zyjsrc` ON `zyjsrc`.`ryid` = `jbxx`.`ryid`    JOIN `xl` ON `xl`.`xlbm` = `jbxx`.`xlbm`  JOIN `gzdw` ON `jbxx`.`gzdwid` = `gzdw`.`gzdwid`    JOIN `zc` ON `zyjsrc`.`jszcbm` = `zc`.`zcbm`  JOIN `xw` ON `jbxx`.`xwbm` = `xw`.`xwbm`  JOIN `zzmm` ON `jbxx`.`zzmmbm` = `zzmm`.`zzmmbm` WHERE 1=1";
        Empty em = new Empty();
        DatabaseAccess dao = new DatabaseAccess();
        EasyMapsManager easyMapsManager = new EasyMapsManager(dao);
        Yh yh = (Yh) request.getSession().getAttribute("user");
        String jb = yh.getJb();
        sql += jb.equals("1") ? "" : jb.equals("2") ? " and `gzdw`.`gzdwid`=" + yh.getGzdwid() + " or `gzdw`.`sjdwid`=" + yh.getGzdwid() : " and `gzdw`.`gzdwid`=" + yh.getGzdwid();
        if (Util.isEmpty(dwmc)) {
            sql += em.empth("dwxz.dwxzbm", dwxzbm, hyb) + em.empth("dwlb.dwlbbm", dwlbbm, hyb) + em.empth("ssxt.ssxtbm", ssxtbm, hyb) + em.empth("zyjsrc.zclbbm", zclbbm, hyb)
                    + em.emys("zc.zcbm", zc, zcys, hyb) + em.emys("xl.xlbm", xlbm, xlys, hyb) + em.emys("xw.xwbm", xwbm, xwys, hyb);
            String sj = null;
            if (!Util.isEmpty(nl1) && !Util.isEmpty(nl2)) {
                sj = " and (" + year + "-year(jbxx.csrq)) between ? and ?";
                hyb.addParmeter(nl1_sj);
                hyb.addParmeter(nl2_sj);
            } else if (Util.isEmpty(nl1) && !Util.isEmpty(nl2)) {
                sj = " and (" + year + "-year(jbxx.csrq)) > ?";
                hyb.addParmeter(nl1_sj);
            } else if (!Util.isEmpty(nl1) && Util.isEmpty(nl2)) {
                sj = " and (" + year + "-year(jbxx.csrq)) < ?";
                hyb.addParmeter(nl2_sj);
            } else {
                sj = "";
            }
            sql += sj;
        } else {
            sql += "and gzdw.dwmc = ?";
            hyb.addParmeter(dwmc);
        }
        List list = hyb.showList(sql);
        return list;
    }

    private List getShgzrcList(HttpServletRequest request, HttpServletResponse response) {
        Tjbb tjbb = new Tjbb();
        Hyberbin hyb = null;
        List list = null;
        try {
            hyb = new Hyberbin(tjbb);
            int nl1 = 0;
            int nl2 = 100;
            int year = new Date(System.currentTimeMillis()).getYear() + 1900;
            String sql = "SELECT `jbxx`.`xm`, `jbxx`.`xb`, `gzdw`.`dwmc` AS `gzdw`, `xl`.`xlmc` AS `xl`,`jbxx`.`csrq`, `jbxx`.`zw`, `zzmm`.`zzmmmc` AS `zzmm`, `jbxx`.`zymc` AS `cszy`, `jbxx`.`lxdh`, `xw`.`xwmc` AS `xw`, `jbxx`.`jg` FROM   `shgzrc`   left JOIN `jbxx` ON `shgzrc`.`ryid` = `jbxx`.`ryid`  left JOIN `xl` ON `jbxx`.`xlbm` = `xl`.`xlbm` left JOIN `zzmm` ON `zzmm`.`zzmmbm` = `jbxx`.`zzmmbm` left JOIN `xw` ON `xw`.`xwbm` = `jbxx`.`xwbm`  left JOIN `gzdw` ON `jbxx`.`gzdwid` = `gzdw`.`gzdwid` where";
            String where = "";
            String xlys = request.getParameter("hidden_xlys");
            String xltj = xlys.equals("true") ? "<=" : "=";
            String dwxzbm = request.getParameter("dwxz");
            String xlbm = request.getParameter("xl");
            String dwlbbm = request.getParameter("dwlb");
            String ssxtbm = request.getParameter("ssxt");
            String zzmmbm = request.getParameter("zzmm");
            try {
                nl1 = Integer.parseInt(request.getParameter("nl1"));
                nl1 = nl1 < 0 ? 0 : nl1;
            } catch (Exception e) {
                nl1 = 0;
            }
            try {
                nl2 = Integer.parseInt(request.getParameter("nl2"));
                nl2 = nl2 < 0 ? 0 : nl2;
            } catch (Exception e) {
                nl2 = 100;
            }
            where = " (" + year + "-year(csrq)) between ? and ?";
            hyb.addParmeter(nl1);
            hyb.addParmeter(nl2);
            Yh yh = (Yh) request.getSession().getAttribute("user");
            String jb = yh.getJb();
            where += jb.equals("1") ? "" : jb.equals("2") ? " and `gzdw`.`gzdwid`=" + yh.getGzdwid() + " or `gzdw`.`sjdwid`=" + yh.getGzdwid() : " and `gzdw`.`gzdwid`=" + yh.getGzdwid();
            if (!Util.isEmpty(zzmmbm)) {
                where = where + " and `jbxx`.`zzmmbm`=?";
                hyb.addParmeter(zzmmbm);
            }
            if (!Util.isEmpty(xlbm)) {
                where = where + " and `jbxx`.`xlbm`" + xltj + "?";
                hyb.addParmeter(xlbm);
            }
            String dwmc = request.getParameter("gzdw");
            if (!(Util.isEmpty(dwmc))) {
                where = where + " and `gzdw`.`dwmc` = ? ";
                hyb.addParmeter(dwmc);
            } else {
                if (!Util.isEmpty(dwxzbm)) {
                    where = where + " and dwxzbm=?";
                    hyb.addParmeter(dwxzbm);
                }
                if (!Util.isEmpty(dwlbbm)) {
                    where = where + " and dwlbbm=?";
                    hyb.addParmeter(dwlbbm);
                }
                if (!Util.isEmpty(ssxtbm)) {
                    where = where + " and ssxtbm=?";
                    hyb.addParmeter(ssxtbm);
                }
            }
            sql += where;
            list = hyb.showList(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private List getNcsyrcList(HttpServletRequest request, HttpServletResponse response) {
        Tjbb tjbb = new Tjbb();
        Hyberbin hyb = null;
        List list = null;
        try {
            hyb = new Hyberbin(tjbb);
            int nl1 = 0;
            int nl2 = 100;
            int year = new Date(System.currentTimeMillis()).getYear() + 1900;
            String sql = "SELECT `jbxx`.`xm`, `jbxx`.`xb`, `gzdw`.`dwmc` AS `gzdw`, `xl`.`xlmc` AS `xl`,`jbxx`.`csrq`, `jbxx`.`zw`, `zzmm`.`zzmmmc` AS `zzmm`, `jbxx`.`zymc` AS `cszy`, `jbxx`.`lxdh`, `xw`.`xwmc` AS `xw`, `jbxx`.`jg` FROM   `ncsyrc`   left JOIN `jbxx` ON `ncsyrc`.`ryid` = `jbxx`.`ryid`  left JOIN `xl` ON `jbxx`.`xlbm` = `xl`.`xlbm` left JOIN `zzmm` ON `zzmm`.`zzmmbm` = `jbxx`.`zzmmbm` left JOIN `xw` ON `xw`.`xwbm` = `jbxx`.`xwbm` left JOIN `ncrclb` ON `ncrclb`.`ncrclbbm` = `ncsyrc`.`rclbbm`   left JOIN `csr` ON `ncsyrc`.`sncsrbm` = `csr`.`csrbm` left JOIN `gzdw` ON `jbxx`.`gzdwid` = `gzdw`.`gzdwid` where";
            String where = "";
            try {
                nl1 = Integer.parseInt(request.getParameter("nl1"));
                nl1 = nl1 < 0 ? 0 : nl1;
            } catch (Exception e) {
                nl1 = 0;
            }
            try {
                nl2 = Integer.parseInt(request.getParameter("nl2"));
                nl2 = nl2 < 0 ? 0 : nl2;
            } catch (Exception e) {
                nl2 = 100;
            }
            String xlys = request.getParameter("hidden_xlys");
            String xltj = xlys.equals("true") ? "<=" : "=";
            String dwxzbm = request.getParameter("dwxz");
            String dwlbbm = request.getParameter("dwlb");
            String sncsrbm = request.getParameter("sncsr");
            String ncrclbbm = request.getParameter("rclb");
            String xlbm = request.getParameter("xl");
            String dwmc = request.getParameter("gzdw");
            where = " (" + year + "-year(csrq)) between ? and ?";
            hyb.addParmeter(nl1);
            hyb.addParmeter(nl2);
            Yh yh = (Yh) request.getSession().getAttribute("user");
            String jb = yh.getJb();
            where += jb.equals("1") ? "" : jb.equals("2") ? " and `gzdw`.`gzdwid`=" + yh.getGzdwid() + " or `gzdw`.`sjdwid`=" + yh.getGzdwid() : " and `gzdw`.`gzdwid`=" + yh.getGzdwid();
            if (!Util.isEmpty(xlbm)) {
                where = where + " and `jbxx`.`xlbm`" + xltj + "?";
                hyb.addParmeter(xlbm);
            }
            if (!Util.isEmpty(sncsrbm)) {
                where = where + " and sncsrbm=?";
                hyb.addParmeter(sncsrbm);
            }
            if (!Util.isEmpty(ncrclbbm)) {
                where = where + " and ncrclbbm=?";
                hyb.addParmeter(ncrclbbm);
            }
            if (!Util.isEmpty(dwmc)) {
                where = where + " and `gzdw`.`dwmc` = ? ";
                hyb.addParmeter(dwmc);
            } else {
                if (!Util.isEmpty(dwxzbm)) {
                    where = where + " and dwxzbm=?";
                    hyb.addParmeter(dwxzbm);
                }
                if (!Util.isEmpty(dwlbbm)) {
                    where = where + " and dwlbbm=?";
                    hyb.addParmeter(dwlbbm);
                }
            }
            sql += where;
            list = hyb.showList(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private List getQyglrc(HttpServletRequest request, HttpServletResponse response) {
        Tjbb tjbb = new Tjbb();
        Hyberbin hyb = null;
        List list = null;
        try {
            hyb = new Hyberbin(tjbb);
            int year = new Date(System.currentTimeMillis()).getYear() + 1900;
            int nl1 = 0;
            int nl2 = 100;
            String sql = "SELECT `jbxx`.`xm`, `jbxx`.`xb`, `gzdw`.`dwmc` AS `gzdw`, `xl`.`xlmc` AS `xl`,`jbxx`.`csrq`, `jbxx`.`zw`, `zzmm`.`zzmmmc` AS `zzmm`, `jbxx`.`zymc` AS `cszy`, `jbxx`.`lxdh`, `xw`.`xwmc` AS `xw`, `jbxx`.`jg` FROM   `qyglrc`   left JOIN `jbxx` ON `qyglrc`.`ryid` = `jbxx`.`ryid`  left JOIN `xl` ON `jbxx`.`xlbm` = `xl`.`xlbm` left JOIN `xw` ON `jbxx`.`xwbm` = `xw`.`xwbm` left JOIN `zzmm` ON `zzmm`.`zzmmbm` = `jbxx`.`zzmmbm` left JOIN `zj` ON `zj`.`zjbm` = `jbxx`.`zjbm`  left JOIN `gzdw` ON `jbxx`.`gzdwid` = `gzdw`.`gzdwid` where";
            String where = "";
            String xlys = request.getParameter("hidden_xlys");
            String zjys = request.getParameter("hidden_zjys");
            String xltj = xlys.equals("true") ? "<=" : "=";
            String zjtj = zjys.equals("true") ? "<=" : "=";
            String dwxzbm = request.getParameter("dwxz");
            String zjbm = request.getParameter("zj");
            String xlbm = request.getParameter("xl");
            String dwlbbm = request.getParameter("dwlb");
            String ssxtbm = request.getParameter("ssxt");
            String zzmmbm = request.getParameter("zzmm");
            try {
                nl1 = Integer.parseInt(request.getParameter("nl1"));
                nl1 = nl1 < 0 ? 0 : nl1;
            } catch (Exception e) {
                nl1 = 0;
            }
            try {
                nl2 = Integer.parseInt(request.getParameter("nl2"));
                nl2 = nl2 < 0 ? 0 : nl2;
            } catch (Exception e) {
                nl2 = 100;
            }
            where = " (" + year + "-year(csrq)) between ? and ?";
            hyb.addParmeter(nl1);
            hyb.addParmeter(nl2);
            Yh yh = (Yh) request.getSession().getAttribute("user");
            String jb = yh.getJb();
            where += jb.equals("1") ? "" : jb.equals("2") ? " and `gzdw`.`gzdwid`=" + yh.getGzdwid() + " or `gzdw`.`sjdwid`=" + yh.getGzdwid() : " and `gzdw`.`gzdwid`=" + yh.getGzdwid();
            if (!Util.isEmpty(zjbm)) {
                where = where + " and xwbm" + zjtj + "?";
                hyb.addParmeter(zjbm);
            }
            if (!Util.isEmpty(xlbm)) {
                where = where + " and `jbxx`.`xlbm`" + xltj + "?";
                hyb.addParmeter(xlbm);
            }
            String dwmc = request.getParameter("gzdw");
            if (!(Util.isEmpty(dwmc))) {
                where = where + " and `gzdw`.`dwmc` = ? ";
                hyb.addParmeter(dwmc);
            } else {
                if (!Util.isEmpty(dwxzbm)) {
                    where = where + " and dwxzbm=?";
                    hyb.addParmeter(dwxzbm);
                }
                if (!Util.isEmpty(dwlbbm)) {
                    where = where + " and dwlbbm=?";
                    hyb.addParmeter(dwlbbm);
                }
                if (!Util.isEmpty(ssxtbm)) {
                    where = where + " and ssxtbm=?";
                    hyb.addParmeter(ssxtbm);
                }
                if (!Util.isEmpty(zzmmbm)) {
                    where = where + " and zzmmbm=? ";
                    hyb.addParmeter(zzmmbm);
                }
            }
            sql += where;
            list = hyb.showList(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private List getGjnrcList(HttpServletRequest request, HttpServletResponse response) {
        Tjbb tjbb = new Tjbb();
        Hyberbin hyb = null;
        List list = null;
        try {
            hyb = new Hyberbin(tjbb);
            int year = new Date(System.currentTimeMillis()).getYear() + 1900;
            int nl1 = 0;
            int nl2 = 100;
            //String sql = "SELECT `jbxx`.`xm`, `jbxx`.`xb`, `gzdw`.`dwmc` AS `gzdw`, `xl`.`xlmc` AS `xl`,`jbxx`.`csrq`, `jbxx`.`zw`, `zzmm`.`zzmmmc` AS `zzmm`, `jbxx`.`zymc` AS `cszy`, `jbxx`.`lxdh`, `xw`.`xwmc` AS `xw`, `jbxx`.`jg` FROM `cyrc` LEFT JOIN `jbxx` ON `cyrc`.`ryid` = `jbxx`.`ryid` LEFT JOIN `xl` ON `xl`.`xlbm` = `jbxx`.`xlbm` LEFT JOIN `gzdw` ON `jbxx`.`gzdwid` = `gzdw`.`gzdwid` LEFT JOIN `zzmm` ON `zzmm`.`zzmmbm` = `jbxx`.`zzmmbm` LEFT JOIN `xw` ON `jbxx`.`xwbm` = `xw`.`xwbm` WHERE ";
            String sql = "SELECT "
                    + "`jbxx`.`xm`, `jbxx`.`xb`, `jbxx`.`csrq`, `jbxx`.`jg`, `zzmm`.`zzmmmc` AS"
                    + "`zzmm`, `xl`.`xlmc` AS `xl`, `xw`.`xwmc` AS `xw`, `gzdw`.`dwmc` AS `gzdw`,"
                    + "`zyjsrc`.`cszy`, `zclb`.`zclbmc` AS `zc`, `jbxx`.`zw`, `jbxx`.`lxdh`"
                    + "FROM"
                    + "`jbxx` LEFT JOIN"
                    + "`zzmm` ON `jbxx`.`zzmmbm` = `zzmm`.`zzmmbm` LEFT JOIN"
                    + "`xl` ON `jbxx`.`xlbm` = `xl`.`xlbm` LEFT JOIN"
                    + "`xw` ON `xw`.`xwbm` = `jbxx`.`xwbm` LEFT JOIN"
                    + "`gzdw` ON `gzdw`.`gzdwid` = `jbxx`.`gzdwid` LEFT JOIN"
                    + "`zyjsrc` ON `zyjsrc`.`zyjsrcid` = `jbxx`.`ryid` LEFT JOIN"
                    + "`zclb` ON `zyjsrc`.`zclbbm` = `zclb`.`zclbbm` where ";
            String xlys = request.getParameter("hidden_xlys");
            String xltj = xlys.equals("true") ? "<=" : "=";
            String dwxzbm = request.getParameter("dwxz");
            String xlbm = request.getParameter("xl");
            String dwlbbm = request.getParameter("dwlb");
            String sshybm = request.getParameter("sshy");
            try {
                nl1 = Integer.parseInt(request.getParameter("nl1"));
                nl1 = nl1 < 0 ? 0 : nl1;
            } catch (Exception e) {
                nl1 = 0;
            }
            try {
                nl2 = Integer.parseInt(request.getParameter("nl2"));
                nl2 = nl2 < 0 ? 0 : nl2;
            } catch (Exception e) {
                nl2 = 100;
            }
            String where = " (" + year + "-year(csrq)) between ? and ?";
            hyb.addParmeter(nl1);
            hyb.addParmeter(nl2);
            Yh yh = (Yh) request.getSession().getAttribute("user");
            String jb = yh.getJb();
            where += jb.equals("1") ? "" : jb.equals("2") ? " and `gzdw`.`gzdwid`=" + yh.getGzdwid() + " or `gzdw`.`sjdwid`=" + yh.getGzdwid() : " and `gzdw`.`gzdwid`=" + yh.getGzdwid();
            if (!Util.isEmpty(xlbm)) {
                where = where + " and `jbxx`.`xlbm`" + xltj + "?";
                hyb.addParmeter(xlbm);
            }
            String dwmc = request.getParameter("gzdw");
            if (!(Util.isEmpty(dwmc))) {
                where = where + " and `gzdw`.`dwmc` = ? ";
                hyb.addParmeter(dwmc);
            } else {
                if (!Util.isEmpty(dwxzbm)) {
                    where = where + " and dwxzbm=?";
                    hyb.addParmeter(dwxzbm);
                }
                if (!Util.isEmpty(dwlbbm)) {
                    where = where + " and dwlbbm=?";
                    hyb.addParmeter(dwlbbm);
                }
                if (!Util.isEmpty(sshybm)) {
                    where = where + " and sshybm=? ";
                    hyb.addParmeter(sshybm);
                }
            }
            sql += where;
            list = hyb.showList(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
