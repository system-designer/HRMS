/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 企业管理人才(qyglrc)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Qyglrc {
/**  企业管理人才id  */
private Integer qyglrcid;//企业管理人才id
/**  学习简历  */
private String xxjl;//学习简历
/**  工作简历  */
private String gzjl;//工作简历
/**  主要奖惩情况  */
private String zyjcqk;//主要奖惩情况
/**  人员id  */
private Integer ryid;//人员id
/**  操作员姓名  */
private String username;//操作员姓名

/**
 * qyglrc不带参数的构造方法
 */
public Qyglrc() {
}
/**
 * qyglrc带参数的构造方法
 * @param qyglrcid 企业管理人才id
 * @param xxjl 学习简历
 * @param gzjl 工作简历
 * @param zyjcqk 主要奖惩情况
 * @param ryid 人员id
 * @param username 操作员姓名
 */
public Qyglrc(Integer qyglrcid,String xxjl,String gzjl,String zyjcqk,Integer ryid,String username) {
    this.qyglrcid=qyglrcid;
    this.xxjl=xxjl;
    this.gzjl=gzjl;
    this.zyjcqk=zyjcqk;
    this.ryid=ryid;
    this.username=username;
}
/**
 *获得企业管理人才id
 *@return 企业管理人才id
 */
public Integer getQyglrcid() {
    return qyglrcid;
}

/**
 *设置企业管理人才id
 *@param qyglrcid 企业管理人才id
 */
public void setQyglrcid(Integer qyglrcid) {
    this.qyglrcid = qyglrcid;
}

/**
 *获得学习简历
 *@return 学习简历
 */
public String getXxjl() {
    return xxjl;
}

/**
 *设置学习简历
 *@param xxjl 学习简历
 */
public void setXxjl(String xxjl) {
    this.xxjl = xxjl;
}

/**
 *获得工作简历
 *@return 工作简历
 */
public String getGzjl() {
    return gzjl;
}

/**
 *设置工作简历
 *@param gzjl 工作简历
 */
public void setGzjl(String gzjl) {
    this.gzjl = gzjl;
}

/**
 *获得主要奖惩情况
 *@return 主要奖惩情况
 */
public String getZyjcqk() {
    return zyjcqk;
}

/**
 *设置主要奖惩情况
 *@param zyjcqk 主要奖惩情况
 */
public void setZyjcqk(String zyjcqk) {
    this.zyjcqk = zyjcqk;
}

/**
 *获得人员id
 *@return 人员id
 */
public Integer getRyid() {
    return ryid;
}

/**
 *设置人员id
 *@param ryid 人员id
 */
public void setRyid(Integer ryid) {
    this.ryid = ryid;
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

