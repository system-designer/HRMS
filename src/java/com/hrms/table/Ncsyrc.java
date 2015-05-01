/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 农村实用人才(ncsyrc)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Ncsyrc {
/**  农村实用人才ID  */
private Integer ncsyrcid;//农村实用人才ID
/**  是否外出务工半年  */
private Boolean sfwcdg;//是否外出务工半年
/**  奖励等级编码  */
private String jldjbm;//奖励等级编码
/**  人才类别编码  */
private String rclbbm;//人才类别编码
/**  上年纯收入编码  */
private String sncsrbm;//上年纯收入编码
/**  人员ID  */
private Integer ryid;//人员ID
/**  操作人员用户名  */
private String username;//操作人员用户名

/**
 * ncsyrc不带参数的构造方法
 */
public Ncsyrc() {
}
/**
 * ncsyrc带参数的构造方法
 * @param ncsyrcid 农村实用人才ID
 * @param sfwcdg 是否外出务工半年
 * @param jldjbm 奖励等级编码
 * @param rclbbm 人才类别编码
 * @param sncsrbm 上年纯收入编码
 * @param ryid 人员ID
 * @param username 操作人员用户名
 */
public Ncsyrc(Integer ncsyrcid,Boolean sfwcdg,String jldjbm,String rclbbm,String sncsrbm,Integer ryid,String username) {
    this.ncsyrcid=ncsyrcid;
    this.sfwcdg=sfwcdg;
    this.jldjbm=jldjbm;
    this.rclbbm=rclbbm;
    this.sncsrbm=sncsrbm;
    this.ryid=ryid;
    this.username=username;
}
/**
 *获得农村实用人才ID
 *@return 农村实用人才ID
 */
public Integer getNcsyrcid() {
    return ncsyrcid;
}

/**
 *设置农村实用人才ID
 *@param ncsyrcid 农村实用人才ID
 */
public void setNcsyrcid(Integer ncsyrcid) {
    this.ncsyrcid = ncsyrcid;
}

/**
 *获得是否外出务工半年
 *@return 是否外出务工半年
 */
public Boolean getSfwcdg() {
    return sfwcdg;
}

/**
 *设置是否外出务工半年
 *@param sfwcdg 是否外出务工半年
 */
public void setSfwcdg(Boolean sfwcdg) {
    this.sfwcdg = sfwcdg;
}

/**
 *获得奖励等级编码
 *@return 奖励等级编码
 */
public String getJldjbm() {
    return jldjbm;
}

/**
 *设置奖励等级编码
 *@param jldjbm 奖励等级编码
 */
public void setJldjbm(String jldjbm) {
    this.jldjbm = jldjbm;
}

/**
 *获得人才类别编码
 *@return 人才类别编码
 */
public String getRclbbm() {
    return rclbbm;
}

/**
 *设置人才类别编码
 *@param rclbbm 人才类别编码
 */
public void setRclbbm(String rclbbm) {
    this.rclbbm = rclbbm;
}

/**
 *获得上年纯收入编码
 *@return 上年纯收入编码
 */
public String getSncsrbm() {
    return sncsrbm;
}

/**
 *设置上年纯收入编码
 *@param sncsrbm 上年纯收入编码
 */
public void setSncsrbm(String sncsrbm) {
    this.sncsrbm = sncsrbm;
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

