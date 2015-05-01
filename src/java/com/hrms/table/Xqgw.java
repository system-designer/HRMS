/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 需求岗位(xqgw)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Xqgw {
/**  需求岗位ID  */
private Integer xqgwid;//需求岗位ID
/**  单位ID  */
private Integer dwid;//单位ID
/**  发布时间  */
private java.sql.Date fbsj;//发布时间
/**  需求岗位  */
private String xqgw;//需求岗位
/**  专业  */
private String zy;//专业
/**  学历编码  */
private String xlbm;//学历编码
/**  学位编码  */
private String xwbm;//学位编码
/**  人数  */
private Integer rs;//人数
/**  引进方式  */
private String yjfs;//引进方式
/**  岗位要求  */
private String gwyq;//岗位要求
/**  待遇  */
private String dy;//待遇
/**  操作员姓名  */
private String username;//操作员姓名

/**
 * xqgw不带参数的构造方法
 */
public Xqgw() {
}
/**
 * xqgw带参数的构造方法
 * @param xqgwid 需求岗位ID
 * @param dwid 单位ID
 * @param fbsj 发布时间
 * @param xqgw 需求岗位
 * @param zy 专业
 * @param xlbm 学历编码
 * @param xwbm 学位编码
 * @param rs 人数
 * @param yjfs 引进方式
 * @param gwyq 岗位要求
 * @param dy 待遇
 * @param username 操作员姓名
 */
public Xqgw(Integer xqgwid,Integer dwid,java.sql.Date fbsj,String xqgw,String zy,String xlbm,String xwbm,Integer rs,String yjfs,String gwyq,String dy,String username) {
    this.xqgwid=xqgwid;
    this.dwid=dwid;
    this.fbsj=fbsj;
    this.xqgw=xqgw;
    this.zy=zy;
    this.xlbm=xlbm;
    this.xwbm=xwbm;
    this.rs=rs;
    this.yjfs=yjfs;
    this.gwyq=gwyq;
    this.dy=dy;
    this.username=username;
}
/**
 *获得需求岗位ID
 *@return 需求岗位ID
 */
public Integer getXqgwid() {
    return xqgwid;
}

/**
 *设置需求岗位ID
 *@param xqgwid 需求岗位ID
 */
public void setXqgwid(Integer xqgwid) {
    this.xqgwid = xqgwid;
}

/**
 *获得单位ID
 *@return 单位ID
 */
public Integer getDwid() {
    return dwid;
}

/**
 *设置单位ID
 *@param dwid 单位ID
 */
public void setDwid(Integer dwid) {
    this.dwid = dwid;
}

/**
 *获得发布时间
 *@return 发布时间
 */
public java.sql.Date getFbsj() {
    return fbsj;
}

/**
 *设置发布时间
 *@param fbsj 发布时间
 */
public void setFbsj(java.sql.Date fbsj) {
    this.fbsj = fbsj;
}

/**
 *获得需求岗位
 *@return 需求岗位
 */
public String getXqgw() {
    return xqgw;
}

/**
 *设置需求岗位
 *@param xqgw 需求岗位
 */
public void setXqgw(String xqgw) {
    this.xqgw = xqgw;
}

/**
 *获得专业
 *@return 专业
 */
public String getZy() {
    return zy;
}

/**
 *设置专业
 *@param zy 专业
 */
public void setZy(String zy) {
    this.zy = zy;
}

/**
 *获得学历编码
 *@return 学历编码
 */
public String getXlbm() {
    return xlbm;
}

/**
 *设置学历编码
 *@param xlbm 学历编码
 */
public void setXlbm(String xlbm) {
    this.xlbm = xlbm;
}

/**
 *获得学位编码
 *@return 学位编码
 */
public String getXwbm() {
    return xwbm;
}

/**
 *设置学位编码
 *@param xwbm 学位编码
 */
public void setXwbm(String xwbm) {
    this.xwbm = xwbm;
}

/**
 *获得人数
 *@return 人数
 */
public Integer getRs() {
    return rs;
}

/**
 *设置人数
 *@param rs 人数
 */
public void setRs(Integer rs) {
    this.rs = rs;
}

/**
 *获得引进方式
 *@return 引进方式
 */
public String getYjfs() {
    return yjfs;
}

/**
 *设置引进方式
 *@param yjfs 引进方式
 */
public void setYjfs(String yjfs) {
    this.yjfs = yjfs;
}

/**
 *获得岗位要求
 *@return 岗位要求
 */
public String getGwyq() {
    return gwyq;
}

/**
 *设置岗位要求
 *@param gwyq 岗位要求
 */
public void setGwyq(String gwyq) {
    this.gwyq = gwyq;
}

/**
 *获得待遇
 *@return 待遇
 */
public String getDy() {
    return dy;
}

/**
 *设置待遇
 *@param dy 待遇
 */
public void setDy(String dy) {
    this.dy = dy;
}

/**
 *获得操作员姓名
 *@return 操作员姓名
 */
public String getUsername() {
    return username;
}

/**
 *设置操作员姓名
 *@param username 操作员姓名
 */
public void setUsername(String username) {
    this.username = username;
}


}

