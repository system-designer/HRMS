/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 用户表(yh)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Yh {
/**  用户ID  */
private Integer userid;//用户ID
/**  用户名  */
private String username;//用户名
/**  级别 1.市级  2:区级 3:企业级  */
private String jb;//级别 1.市级  2:区级 3:企业级
/**  类别 1.市级管理员 2.普通用户  */
private String lb;//类别 1.市级管理员 2.普通用户
/**  密码  */
private String mm;//密码
/**  工作单位ID  */
private Integer gzdwid;//工作单位ID
/**  姓名  */
private String xm;//姓名
/**  联系电话  */
private String lxdh;//联系电话

/**
 * yh不带参数的构造方法
 */
public Yh() {
}
/**
 * yh带参数的构造方法
 * @param userid 用户ID
 * @param username 用户名
 * @param jb 级别 1.市级  2:区级 3:企业级
 * @param lb 类别 1.市级管理员 2.普通用户
 * @param mm 密码
 * @param gzdwid 工作单位ID
 * @param xm 姓名
 * @param lxdh 联系电话
 */
public Yh(Integer userid,String username,String jb,String lb,String mm,Integer gzdwid,String xm,String lxdh) {
    this.userid=userid;
    this.username=username;
    this.jb=jb;
    this.lb=lb;
    this.mm=mm;
    this.gzdwid=gzdwid;
    this.xm=xm;
    this.lxdh=lxdh;
}
/**
 *获得用户ID
 *@return 用户ID
 */
public Integer getUserid() {
    return userid;
}

/**
 *设置用户ID
 *@param userid 用户ID
 */
public void setUserid(Integer userid) {
    this.userid = userid;
}

/**
 *获得用户名
 *@return 用户名
 */
public String getUsername() {
    return username;
}

/**
 *设置用户名
 *@param username 用户名
 */
public void setUsername(String username) {
    this.username = username;
}

/**
 *获得级别 1.市级  2:区级 3:企业级
 *@return 级别 1.市级  2:区级 3:企业级
 */
public String getJb() {
    return jb;
}

/**
 *设置级别 1.市级  2:区级 3:企业级
 *@param jb 级别 1.市级  2:区级 3:企业级
 */
public void setJb(String jb) {
    this.jb = jb;
}

/**
 *获得类别 1.市级管理员 2.普通用户
 *@return 类别 1.市级管理员 2.普通用户
 */
public String getLb() {
    return lb;
}

/**
 *设置类别 1.市级管理员 2.普通用户
 *@param lb 类别 1.市级管理员 2.普通用户
 */
public void setLb(String lb) {
    this.lb = lb;
}

/**
 *获得密码
 *@return 密码
 */
public String getMm() {
    return mm;
}

/**
 *设置密码
 *@param mm 密码
 */
public void setMm(String mm) {
    this.mm = mm;
}

/**
 *获得工作单位ID
 *@return 工作单位ID
 */
public Integer getGzdwid() {
    return gzdwid;
}

/**
 *设置工作单位ID
 *@param gzdwid 工作单位ID
 */
public void setGzdwid(Integer gzdwid) {
    this.gzdwid = gzdwid;
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
 *获得联系电话
 *@return 联系电话
 */
public String getLxdh() {
    return lxdh;
}

/**
 *设置联系电话
 *@param lxdh 联系电话
 */
public void setLxdh(String lxdh) {
    this.lxdh = lxdh;
}


}

