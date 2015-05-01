/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 学历编码表(xl)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Xl {
/**  学历编码  */
private String xlbm;//学历编码
/**  学历名称  */
private String xlmc;//学历名称

/**
 * xl不带参数的构造方法
 */
public Xl() {
}
/**
 * xl带参数的构造方法
 * @param xlbm 学历编码
 * @param xlmc 学历名称
 */
public Xl(String xlbm,String xlmc) {
    this.xlbm=xlbm;
    this.xlmc=xlmc;
}
/**
 *获得学历编码
 *@return 学历编码
 */
public String getXlbm() {
    return xlbm;
}

/**
 *设置学历编码
 *@param xlbm 学历编码
 */
public void setXlbm(String xlbm) {
    this.xlbm = xlbm;
}

/**
 *获得学历名称
 *@return 学历名称
 */
public String getXlmc() {
    return xlmc;
}

/**
 *设置学历名称
 *@param xlmc 学历名称
 */
public void setXlmc(String xlmc) {
    this.xlmc = xlmc;
}


}

