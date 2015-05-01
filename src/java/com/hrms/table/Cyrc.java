/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 创业人才(cyrc)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Cyrc {
/**  创业人才  */
private Integer cyrcid;//创业人才
/**  原任职机构  */
private String yrzjg;//原任职机构
/**  原职务  */
private String yzw;//原职务
/**  在黄工作时间  */
private String zhgzsj;//在黄工作时间
/**  电子邮箱  */
private String email;//电子邮箱
/**  原居住地址  */
private String yjzdz;//原居住地址
/**  重要荣誉或重要奖项  */
private String zyry;//重要荣誉或重要奖项
/**  人员ID  */
private Integer ryid;//人员ID
/**  操作人员用户名  */
private String username;//操作人员用户名

/**
 * cyrc不带参数的构造方法
 */
public Cyrc() {
}
/**
 * cyrc带参数的构造方法
 * @param cyrcid 创业人才
 * @param yrzjg 原任职机构
 * @param yzw 原职务
 * @param zhgzsj 在黄工作时间
 * @param email 电子邮箱
 * @param yjzdz 原居住地址
 * @param zyry 重要荣誉或重要奖项
 * @param ryid 人员ID
 * @param username 操作人员用户名
 */
public Cyrc(Integer cyrcid,String yrzjg,String yzw,String zhgzsj,String email,String yjzdz,String zyry,Integer ryid,String username) {
    this.cyrcid=cyrcid;
    this.yrzjg=yrzjg;
    this.yzw=yzw;
    this.zhgzsj=zhgzsj;
    this.email=email;
    this.yjzdz=yjzdz;
    this.zyry=zyry;
    this.ryid=ryid;
    this.username=username;
}
/**
 *获得创业人才
 *@return 创业人才
 */
public Integer getCyrcid() {
    return cyrcid;
}

/**
 *设置创业人才
 *@param cyrcid 创业人才
 */
public void setCyrcid(Integer cyrcid) {
    this.cyrcid = cyrcid;
}

/**
 *获得原任职机构
 *@return 原任职机构
 */
public String getYrzjg() {
    return yrzjg;
}

/**
 *设置原任职机构
 *@param yrzjg 原任职机构
 */
public void setYrzjg(String yrzjg) {
    this.yrzjg = yrzjg;
}

/**
 *获得原职务
 *@return 原职务
 */
public String getYzw() {
    return yzw;
}

/**
 *设置原职务
 *@param yzw 原职务
 */
public void setYzw(String yzw) {
    this.yzw = yzw;
}

/**
 *获得在黄工作时间
 *@return 在黄工作时间
 */
public String getZhgzsj() {
    return zhgzsj;
}

/**
 *设置在黄工作时间
 *@param zhgzsj 在黄工作时间
 */
public void setZhgzsj(String zhgzsj) {
    this.zhgzsj = zhgzsj;
}

/**
 *获得电子邮箱
 *@return 电子邮箱
 */
public String getEmail() {
    return email;
}

/**
 *设置电子邮箱
 *@param email 电子邮箱
 */
public void setEmail(String email) {
    this.email = email;
}

/**
 *获得原居住地址
 *@return 原居住地址
 */
public String getYjzdz() {
    return yjzdz;
}

/**
 *设置原居住地址
 *@param yjzdz 原居住地址
 */
public void setYjzdz(String yjzdz) {
    this.yjzdz = yjzdz;
}

/**
 *获得重要荣誉或重要奖项
 *@return 重要荣誉或重要奖项
 */
public String getZyry() {
    return zyry;
}

/**
 *设置重要荣誉或重要奖项
 *@param zyry 重要荣誉或重要奖项
 */
public void setZyry(String zyry) {
    this.zyry = zyry;
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
 *获得操作人员用户名
 *@return 操作人员用户名
 */
public String getUsername() {
    return username;
}

/**
 *设置操作人员用户名
 *@param username 操作人员用户名
 */
public void setUsername(String username) {
    this.username = username;
}


}

