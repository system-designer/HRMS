/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 家庭成员(jtcy)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Jtcy {
/**  家庭成员ID  */
private Integer jtcyid;//家庭成员ID
/**  称谓  */
private String cw;//称谓
/**  姓名  */
private String xm;//姓名
/**  出生年月  */
private String csny;//出生年月
/**  政治面貌编码  */
private String zzmm;//政治面貌编码
/**  工作单位  */
private String gzdw;//工作单位
/**  职务  */
private String zw;//职务
/**  人员ID  */
private Integer ryid;//人员ID
/**  操作员用户名  */
private String username;//操作员用户名

/**
 * jtcy不带参数的构造方法
 */
public Jtcy() {
}
/**
 * jtcy带参数的构造方法
 * @param jtcyid 家庭成员ID
 * @param cw 称谓
 * @param xm 姓名
 * @param csny 出生年月
 * @param zzmm 政治面貌编码
 * @param gzdw 工作单位
 * @param zw 职务
 * @param ryid 人员ID
 * @param username 操作员用户名
 */
public Jtcy(Integer jtcyid,String cw,String xm,String csny,String zzmm,String gzdw,String zw,Integer ryid,String username) {
    this.jtcyid=jtcyid;
    this.cw=cw;
    this.xm=xm;
    this.csny=csny;
    this.zzmm=zzmm;
    this.gzdw=gzdw;
    this.zw=zw;
    this.ryid=ryid;
    this.username=username;
}
/**
 *获得家庭成员ID
 *@return 家庭成员ID
 */
public Integer getJtcyid() {
    return jtcyid;
}

/**
 *设置家庭成员ID
 *@param jtcyid 家庭成员ID
 */
public void setJtcyid(Integer jtcyid) {
    this.jtcyid = jtcyid;
}

/**
 *获得称谓
 *@return 称谓
 */
public String getCw() {
    return cw;
}

/**
 *设置称谓
 *@param cw 称谓
 */
public void setCw(String cw) {
    this.cw = cw;
}

/**
 *获得姓名
 *@return 姓名
 */
public String getXm() {
    return xm;
}

/**
 *设置姓名
 *@param xm 姓名
 */
public void setXm(String xm) {
    this.xm = xm;
}

/**
 *获得出生年月
 *@return 出生年月
 */
public String getCsny() {
    return csny;
}

/**
 *设置出生年月
 *@param csny 出生年月
 */
public void setCsny(String csny) {
    this.csny = csny;
}

/**
 *获得政治面貌编码
 *@return 政治面貌编码
 */
public String getZzmm() {
    return zzmm;
}

/**
 *设置政治面貌编码
 *@param zzmm 政治面貌编码
 */
public void setZzmm(String zzmm) {
    this.zzmm = zzmm;
}

/**
 *获得工作单位
 *@return 工作单位
 */
public String getGzdw() {
    return gzdw;
}

/**
 *设置工作单位
 *@param gzdw 工作单位
 */
public void setGzdw(String gzdw) {
    this.gzdw = gzdw;
}

/**
 *获得职务
 *@return 职务
 */
public String getZw() {
    return zw;
}

/**
 *设置职务
 *@param zw 职务
 */
public void setZw(String zw) {
    this.zw = zw;
}

/**
 *获得人员ID
 *@return 人员ID
 */
public Integer getRyid() {
    return ryid;
}

/**
 *设置人员ID
 *@param ryid 人员ID
 */
public void setRyid(Integer ryid) {
    this.ryid = ryid;
}

/**
 *获得操作员用户名
 *@return 操作员用户名
 */
public String getUsername() {
    return username;
}

/**
 *设置操作员用户名
 *@param username 操作员用户名
 */
public void setUsername(String username) {
    this.username = username;
}


}

