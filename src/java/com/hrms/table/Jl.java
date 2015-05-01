/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 简历(jl)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Jl {
/**  简历ID  */
private Integer jlid;//简历ID
/**  学习简历  */
private String xxjl;//学习简历
/**  工作简历  */
private String gzjl;//工作简历
/**  奖惩情况  */
private String jcqk;//奖惩情况
/**  人员ID  */
private Integer ryid;//人员ID
/**  操作员用户名  */
private String username;//操作员用户名

/**
 * jl不带参数的构造方法
 */
public Jl() {
}
/**
 * jl带参数的构造方法
 * @param jlid 简历ID
 * @param xxjl 学习简历
 * @param gzjl 工作简历
 * @param jcqk 奖惩情况
 * @param ryid 人员ID
 * @param username 操作员用户名
 */
public Jl(Integer jlid,String xxjl,String gzjl,String jcqk,Integer ryid,String username) {
    this.jlid=jlid;
    this.xxjl=xxjl;
    this.gzjl=gzjl;
    this.jcqk=jcqk;
    this.ryid=ryid;
    this.username=username;
}
/**
 *获得简历ID
 *@return 简历ID
 */
public Integer getJlid() {
    return jlid;
}

/**
 *设置简历ID
 *@param jlid 简历ID
 */
public void setJlid(Integer jlid) {
    this.jlid = jlid;
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
 *获得奖惩情况
 *@return 奖惩情况
 */
public String getJcqk() {
    return jcqk;
}

/**
 *设置奖惩情况
 *@param jcqk 奖惩情况
 */
public void setJcqk(String jcqk) {
    this.jcqk = jcqk;
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

