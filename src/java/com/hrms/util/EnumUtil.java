/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrms.util;

import com.hrms.manage.permission.UserPermission;
import com.hrms.table.Ssdq;
import java.util.ArrayList;

/**
 *
 * @author liulei
 */
public class EnumUtil {

    /**
     * 得到所属地区列表
     * @param userPermission
     * @return 
     */
    public static ArrayList<Ssdq> getSsdqList(UserPermission userPermission) {
        ArrayList<Ssdq> ssdqList = new ArrayList<Ssdq>();
        String[] ssdqmc = {"黄石市","黄石港区", "西塞山区", "下陆区", "铁山区", "经济技术开发区", "大冶市", "阳新县"};
        if (userPermission.isShiLevel()) {
            for (int i = 0; i < 8; i++) {
                Ssdq ssdq=new Ssdq(i+"",ssdqmc[i]);
                ssdqList.add(ssdq);
            }
        } else if (userPermission.isQuLevel()) {
            String ssdqbm=userPermission.getSSdqbm();
            Ssdq ssdq=new Ssdq(ssdqbm,ssdqmc[Integer.parseInt(ssdqbm)]);
            ssdqList.add(ssdq);
        }
        return ssdqList;
    }
}
