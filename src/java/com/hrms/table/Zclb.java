/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 职称类别编码表(zclb)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Zclb {
/**  职称类别编码  */
private String zclbbm;//职称类别编码
/**  职称类别名称  */
private String zclbmc;//职称类别名称

/**
 * zclb不带参数的构造方法
 */
public Zclb() {
}
/**
 * zclb带参数的构造方法
 * @param zclbbm 职称类别编码
 * @param zclbmc 职称类别名称
 */
public Zclb(String zclbbm,String zclbmc) {
    this.zclbbm=zclbbm;
    this.zclbmc=zclbmc;
}
/**
 *获得职称类别编码
 *@return 职称类别编码
 */
public String getZclbbm() {
    return zclbbm;
}

/**
 *设置职称类别编码
 *@param zclbbm 职称类别编码
 */
public void setZclbbm(String zclbbm) {
    this.zclbbm = zclbbm;
}

/**
 *获得职称类别名称
 *@return 职称类别名称
 */
public String getZclbmc() {
    return zclbmc;
}

/**
 *设置职称类别名称
 *@param zclbmc 职称类别名称
 */
public void setZclbmc(String zclbmc) {
    this.zclbmc = zclbmc;
}


}

