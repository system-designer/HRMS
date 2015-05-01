/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 社会工作人才(shgzrc)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Shgzrc {
/**  社会工作人才id  */
private Integer shgzrcid;//社会工作人才id
/**  从事专业  */
private String cszy;//从事专业
/**  工作岗位  */
private String gzgw;//工作岗位
/**  备注  */
private String bz;//备注
/**  人员id  */
private Integer ryid;//人员id
/**  操作员姓名  */
private String username;//操作员姓名

/**
 * shgzrc不带参数的构造方法
 */
public Shgzrc() {
}
/**
 * shgzrc带参数的构造方法
 * @param shgzrcid 社会工作人才id
 * @param cszy 从事专业
 * @param gzgw 工作岗位
 * @param bz 备注
 * @param ryid 人员id
 * @param username 操作员姓名
 */
public Shgzrc(Integer shgzrcid,String cszy,String gzgw,String bz,Integer ryid,String username) {
    this.shgzrcid=shgzrcid;
    this.cszy=cszy;
    this.gzgw=gzgw;
    this.bz=bz;
    this.ryid=ryid;
    this.username=username;
}
/**
 *获得社会工作人才id
 *@return 社会工作人才id
 */
public Integer getShgzrcid() {
    return shgzrcid;
}

/**
 *设置社会工作人才id
 *@param shgzrcid 社会工作人才id
 */
public void setShgzrcid(Integer shgzrcid) {
    this.shgzrcid = shgzrcid;
}

/**
 *获得从事专业
 *@return 从事专业
 */
public String getCszy() {
    return cszy;
}

/**
 *设置从事专业
 *@param cszy 从事专业
 */
public void setCszy(String cszy) {
    this.cszy = cszy;
}

/**
 *获得工作岗位
 *@return 工作岗位
 */
public String getGzgw() {
    return gzgw;
}

/**
 *设置工作岗位
 *@param gzgw 工作岗位
 */
public void setGzgw(String gzgw) {
    this.gzgw = gzgw;
}

/**
 *获得备注
 *@return 备注
 */
public String getBz() {
    return bz;
}

/**
 *设置备注
 *@param bz 备注
 */
public void setBz(String bz) {
    this.bz = bz;
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

