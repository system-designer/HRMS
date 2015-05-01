package com.hrms.manage.permission;

import com.hrms.table.Gzdw;
import com.hrms.table.Yh;
import com.hrms.util.Util;
import javax.servlet.http.HttpServletRequest;
import org.jplus.hyb.database.Hyberbin;

public class UserPermission {

    private UserContants ucontants = new UserContants();
    private Yh user = null;//封装的用户

    public UserPermission(HttpServletRequest request) {
        user = (Yh) Util.getSessionAttribute(request, "user");
    }

    /**
     * 用户是否为管理员
     *
     * @return 管理员返回true
     */
    public boolean isAdmin() {
        return ucontants.ADMIN.equals(user == null ? null : user.getLb()) ? true : false;
    }

    /**
     * 得到当前用户对象
     *
     * @return user
     */
    public Yh getUser() {
        return user;
    }

    /**
     * 是否市区级别
     *
     * @return 是否为市区级别
     */
    public boolean isShiLevel() {
        return ucontants.SHI.equals(user == null ? null : user.getJb()) ? true : false;
    }

    /**
     * 是否区（县）级别
     *
     * @return 是否为区（县）级别
     */
    public boolean isQuLevel() {
        return ucontants.QU.equals(user == null ? null : user.getJb()) ? true : false;
    }

    /**
     * 是否企业级别
     *
     * @return 是否为企业级别
     */
    public boolean isQiYeLevel() {
        return ucontants.QIYE.equals(user == null ? null : user.getJb()) ? true : false;
    }
    
    /**
     * 得到用户的所属地区
     * 0.市级
     * 1.黄石港区
     * 2.西塞山区
     * 3.下陆区
     * 4.铁山区
     * 5.经济技术开发区
     * 6.大冶市
     * 7.阳新县
     * @return 
     */
    public String getSSdqbm(){
        Gzdw gzdw=new Gzdw();
        gzdw.setGzdwid(user.getGzdwid());
        Hyberbin hyberbin=new Hyberbin(gzdw);
        gzdw=hyberbin.showOnebyKey("gzdwid");
        return gzdw.getSsdqbm();
    }
}
