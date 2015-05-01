/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 职级编码编码表(zj)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Zj {
/**  职级编码  */
private String zjbm;//职级编码
/**  职级名称  */
private String zjmc;//职级名称

/**
 * zj不带参数的构造方法
 */
public Zj() {
}
/**
 * zj带参数的构造方法
 * @param zjbm 职级编码
 * @param zjmc 职级名称
 */
public Zj(String zjbm,String zjmc) {
    this.zjbm=zjbm;
    this.zjmc=zjmc;
}
/**
 *获得职级编码
 *@return 职级编码
 */
public String getZjbm() {
    return zjbm;
}

/**
 *设置职级编码
 *@param zjbm 职级编码
 */
public void setZjbm(String zjbm) {
    this.zjbm = zjbm;
}

/**
 *获得职级名称
 *@return 职级名称
 */
public String getZjmc() {
    return zjmc;
}

/**
 *设置职级名称
 *@param zjmc 职级名称
 */
public void setZjmc(String zjmc) {
    this.zjmc = zjmc;
}


}

