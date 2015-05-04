package com.hrms.manage.xtwh;

import com.hrms.manage.permission.UserPermission;
import com.hrms.table.Yh;
import com.hrms.util.CnToSpell;
import com.hrms.util.Util;
import com.jplus.json.JSONException;
import com.jplus.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import org.jplus.yydbgai.DatabaseAccess;

public class GzdwCrudFormBean {

    public String username;//操作人员
    public Integer gzdwid;//工作单位id
    public String dwmc;//单位名称
    public String dwxzbm;//单位性质编码
    public String dwlbbm;//单位类别编码
    public String ssxtbm;//所属系统编码
    public String sshybm;//所属行业编码
    public String dwdh;//联系电话
    public String dwdz;//单位地址
    public String hypy;//单位名称汉语拼音首字母
    public String lxr;//联系人
    public String dzyx;//电子邮箱
    public String dwjj;//单位简介
    public String ssdqbm;//所属地区编码
    public JSONObject jsonObj = new JSONObject();

    public GzdwCrudFormBean() {
    }

    public static class GzdwFolder {
    }

    public GzdwCrudFormBean(HttpServletRequest request) {
        username = ((Yh) Util.getSessionAttribute(request, "user")).getXm();
        String idstr = request.getParameter("id");
        if (Util.isInteger(idstr)) {
            gzdwid = Integer.parseInt(idstr);
        }
        dwmc = request.getParameter("name-crud");
        dwxzbm = request.getParameter("prop-crud");
        dwlbbm = request.getParameter("level-crud");
        ssxtbm = request.getParameter("dep-crud");
        sshybm = request.getParameter("trade-crud");
        dwdh = request.getParameter("phone-crud");
        dwdz = request.getParameter("addr-crud");
        if (!Util.isEmpty(dwmc)) {
            hypy = CnToSpell.getPinYinHeadChar(dwmc);
        }
        lxr = request.getParameter("contacts-crud");
        dzyx = request.getParameter("email-crud");
        dwjj = request.getParameter("introduction-crud");
        UserPermission user=new UserPermission(request);
        ssdqbm=user.getSSdqbm();
    }

    public boolean validate(boolean b) {
        if (b) {
            try {
                if (Util.isEmpty(dwxzbm) || Util.isEmpty(dwlbbm) || Util.isEmpty(ssxtbm) || Util.isEmpty(sshybm)) {
                    jsonObj.put("code", "编码不能为空!!!");
                    return false;
                }
                if (Util.isEmpty(dwmc)) {
                    jsonObj.put("name_notice", "单位名称不能为空!!!");
                    return false;
                }
                if (Util.isEmpty(dwdz)) {
                    jsonObj.put("addr_notice", "单位地址不能为空!!!");
                    return false;
                }
                /*
                if (!Util.isMobileNO(dwdh)) {
                    jsonObj.put("phone_notice", "号码格式不正确!!!");
                    return false;
                }
                 */
            } catch (JSONException e) {
                throw new RuntimeException();
            }
        }
        return true;

    }

    public void deleteGzdwFolder(HttpServletRequest request) {
        Util.deleteDirectory(Util.getFileServerPath("/userfiles/photo/" + gzdwid + "_" + dwmc, request));
    }

    public void setPrimaryKeyParam(DatabaseAccess dao) {
        dao.setPreparedParameter(gzdwid);
    }

    public void setOtherParam(DatabaseAccess dao) {
        dao.setPreparedParameter(dwmc);
        dao.setPreparedParameter(dwxzbm);
        dao.setPreparedParameter(dwlbbm);
        dao.setPreparedParameter(ssxtbm);
        dao.setPreparedParameter(sshybm);
        dao.setPreparedParameter(dwdh);
        dao.setPreparedParameter(dwdz);
        dao.setPreparedParameter(hypy);
        dao.setPreparedParameter(lxr);
        dao.setPreparedParameter(dzyx);
        dao.setPreparedParameter(dwjj);
        dao.setPreparedParameter(ssdqbm);
        dao.setPreparedParameter(username);
    }
    /*
     * 根据数据库字段顺序预处理数据
     * @param dao 数据库封装类
     * @param doSetPrimaryKey 是否预处理主键
     * @param doSetOther 是否预处理其他字段
     */

    public void setParamByOrder(DatabaseAccess dao, boolean doSetPrimaryKey, boolean doSetOther) {
        if (doSetOther) {
            setOtherParam(dao);
        }
        if (doSetPrimaryKey) {
            setPrimaryKeyParam(dao);
        }
    }

    @Override
    public String toString() {
        return "GzdwCrudFormBean{" + "gzdwid=" + gzdwid + ", dwmc=" + dwmc + ", dwxzbm=" + dwxzbm + ", dwlbbm=" + dwlbbm + ", ssxtbm=" + ssxtbm + ", sshybm=" + sshybm + ", dwdh=" + dwdh + ", dwdz=" + dwdz + ", hypy=" + hypy + ", jsonObj=" + jsonObj + '}';
    }
}
