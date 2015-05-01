/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 荣誉奖励(ryjl)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Ryjl {
/**  荣誉奖励ID  */
private Integer ryjlid;//荣誉奖励ID
/**  荣誉奖励名称  */
private String ryjlmc;//荣誉奖励名称
/**  授予机关  */
private String syjg;//授予机关
/**  时间  */
private String sj;//时间
/**  人员ID  */
private Integer ryid;//人员ID
/**  操作员用户名  */
private String username;//操作员用户名

/**
 * ryjl不带参数的构造方法
 */
public Ryjl() {
}
/**
 * ryjl带参数的构造方法
 * @param ryjlid 荣誉奖励ID
 * @param ryjlmc 荣誉奖励名称
 * @param syjg 授予机关
 * @param sj 时间
 * @param ryid 人员ID
 * @param username 操作员用户名
 */
public Ryjl(Integer ryjlid,String ryjlmc,String syjg,String sj,Integer ryid,String username) {
    this.ryjlid=ryjlid;
    this.ryjlmc=ryjlmc;
    this.syjg=syjg;
    this.sj=sj;
    this.ryid=ryid;
    this.username=username;
}
/**
 *获得荣誉奖励ID
 *@return 荣誉奖励ID
 */
public Integer getRyjlid() {
    return ryjlid;
}

/**
 *设置荣誉奖励ID
 *@param ryjlid 荣誉奖励ID
 */
public void setRyjlid(Integer ryjlid) {
    this.ryjlid = ryjlid;
}

/**
 *获得荣誉奖励名称
 *@return 荣誉奖励名称
 */
public String getRyjlmc() {
    return ryjlmc;
}

/**
 *设置荣誉奖励名称
 *@param ryjlmc 荣誉奖励名称
 */
public void setRyjlmc(String ryjlmc) {
    this.ryjlmc = ryjlmc;
}

/**
 *获得授予机关
 *@return 授予机关
 */
public String getSyjg() {
    return syjg;
}

/**
 *设置授予机关
 *@param syjg 授予机关
 */
public void setSyjg(String syjg) {
    this.syjg = syjg;
}

/**
 *获得时间
 *@return 时间
 */
public String getSj() {
    return sj;
}

/**
 *设置时间
 *@param sj 时间
 */
public void setSj(String sj) {
    this.sj = sj;
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

