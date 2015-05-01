/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 学位编码表(xw)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Xw {
/**  学位名称  */
private String xwbm;//学位名称
/**  学位名称  */
private String xwmc;//学位名称

/**
 * xw不带参数的构造方法
 */
public Xw() {
}
/**
 * xw带参数的构造方法
 * @param xwbm 学位名称
 * @param xwmc 学位名称
 */
public Xw(String xwbm,String xwmc) {
    this.xwbm=xwbm;
    this.xwmc=xwmc;
}
/**
 *获得学位名称
 *@return 学位名称
 */
public String getXwbm() {
    return xwbm;
}

/**
 *设置学位名称
 *@param xwbm 学位名称
 */
public void setXwbm(String xwbm) {
    this.xwbm = xwbm;
}

/**
 *获得学位名称
 *@return 学位名称
 */
public String getXwmc() {
    return xwmc;
}

/**
 *设置学位名称
 *@param xwmc 学位名称
 */
public void setXwmc(String xwmc) {
    this.xwmc = xwmc;
}


}

