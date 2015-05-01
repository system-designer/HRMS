/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 权限表(qx)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Qx {
/**  权限id  */
private Integer qxid;//权限id
/**  用户ID  */
private String userid;//用户ID
/**  有权操作的单位ID  */
private Integer czdwid;//有权操作的单位ID

/**
 * qx不带参数的构造方法
 */
public Qx() {
}
/**
 * qx带参数的构造方法
 * @param qxid 权限id
 * @param userid 用户ID
 * @param czdwid 有权操作的单位ID
 */
public Qx(Integer qxid,String userid,Integer czdwid) {
    this.qxid=qxid;
    this.userid=userid;
    this.czdwid=czdwid;
}
/**
 *获得权限id
 *@return 权限id
 */
public Integer getQxid() {
    return qxid;
}

/**
 *设置权限id
 *@param qxid 权限id
 */
public void setQxid(Integer qxid) {
    this.qxid = qxid;
}

/**
 *获得用户ID
 *@return 用户ID
 */
public String getUserid() {
    return userid;
}

/**
 *设置用户ID
 *@param userid 用户ID
 */
public void setUserid(String userid) {
    this.userid = userid;
}

/**
 *获得有权操作的单位ID
 *@return 有权操作的单位ID
 */
public Integer getCzdwid() {
    return czdwid;
}

/**
 *设置有权操作的单位ID
 *@param czdwid 有权操作的单位ID
 */
public void setCzdwid(Integer czdwid) {
    this.czdwid = czdwid;
}


}

