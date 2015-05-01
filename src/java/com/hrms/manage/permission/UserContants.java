package com.hrms.manage.permission;

import java.util.HashMap;
import java.util.Map;

public class UserContants {

    public final String SHI = "1";//市级别
    public final String QU = "2";//区(县)级别
    public final String QIYE = "3";//企业级别
    public final String ADMIN = "1";//管理员
    public final String PUTONG = "2";//普通用户
    public final Map<String, String> userLevelMap = new HashMap<String, String>();
    public final Map<String, String> userTypeMap = new HashMap<String, String>();

    {
        userLevelMap.put(SHI, "市级");
        userLevelMap.put(QU, "区级");
        userLevelMap.put(QIYE, "企业级");
        userTypeMap.put(ADMIN, "管理员");
        userTypeMap.put(PUTONG, "普通用户");
    }

    public String getADMIN() {
        return ADMIN;
    }

    public String getSHI() {
        return SHI;
    }

    public String getQU() {
        return QU;
    }

    public String getQIYE() {
        return QIYE;
    }

    public String getPUTONG() {
        return PUTONG;
    }

    public Map<String, String> getUserTypeMap() {
        return userTypeMap;
    }

    public Map<String, String> getUserLevelMap() {
        return userLevelMap;
    }
}
