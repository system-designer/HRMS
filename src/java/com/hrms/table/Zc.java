/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 职称编码表(zc)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Zc {
/**  职称编码  */
private String zcbm;//职称编码
/**  职称名称  */
private String zcmc;//职称名称

/**
 * zc不带参数的构造方法
 */
public Zc() {
}
/**
 * zc带参数的构造方法
 * @param zcbm 职称编码
 * @param zcmc 职称名称
 */
public Zc(String zcbm,String zcmc) {
    this.zcbm=zcbm;
    this.zcmc=zcmc;
}
/**
 *获得职称编码
 *@return 职称编码
 */
public String getZcbm() {
    return zcbm;
}

/**
 *设置职称编码
 *@param zcbm 职称编码
 */
public void setZcbm(String zcbm) {
    this.zcbm = zcbm;
}

/**
 *获得职称名称
 *@return 职称名称
 */
public String getZcmc() {
    return zcmc;
}

/**
 *设置职称名称
 *@param zcmc 职称名称
 */
public void setZcmc(String zcmc) {
    this.zcmc = zcmc;
}


}

