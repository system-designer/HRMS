/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 单位类别编码表(dwlb)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Dwlb {
/**  单位类别编码  */
private String dwlbbm;//单位类别编码
/**  单位类别名称  */
private String dwlbmc;//单位类别名称

/**
 * dwlb不带参数的构造方法
 */
public Dwlb() {
}
/**
 * dwlb带参数的构造方法
 * @param dwlbbm 单位类别编码
 * @param dwlbmc 单位类别名称
 */
public Dwlb(String dwlbbm,String dwlbmc) {
    this.dwlbbm=dwlbbm;
    this.dwlbmc=dwlbmc;
}
/**
 *获得单位类别编码
 *@return 单位类别编码
 */
public String getDwlbbm() {
    return dwlbbm;
}

/**
 *设置单位类别编码
 *@param dwlbbm 单位类别编码
 */
public void setDwlbbm(String dwlbbm) {
    this.dwlbbm = dwlbbm;
}

/**
 *获得单位类别名称
 *@return 单位类别名称
 */
public String getDwlbmc() {
    return dwlbmc;
}

/**
 *设置单位类别名称
 *@param dwlbmc 单位类别名称
 */
public void setDwlbmc(String dwlbmc) {
    this.dwlbmc = dwlbmc;
}


}

