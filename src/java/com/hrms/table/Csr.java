/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 纯收入编码表(csr)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Csr {
/**  纯收入编码  */
private String csrbm;//纯收入编码
/**  纯收入名称  */
private String csrmc;//纯收入名称

/**
 * csr不带参数的构造方法
 */
public Csr() {
}
/**
 * csr带参数的构造方法
 * @param csrbm 纯收入编码
 * @param csrmc 纯收入名称
 */
public Csr(String csrbm,String csrmc) {
    this.csrbm=csrbm;
    this.csrmc=csrmc;
}
/**
 *获得纯收入编码
 *@return 纯收入编码
 */
public String getCsrbm() {
    return csrbm;
}

/**
 *设置纯收入编码
 *@param csrbm 纯收入编码
 */
public void setCsrbm(String csrbm) {
    this.csrbm = csrbm;
}

/**
 *获得纯收入名称
 *@return 纯收入名称
 */
public String getCsrmc() {
    return csrmc;
}

/**
 *设置纯收入名称
 *@param csrmc 纯收入名称
 */
public void setCsrmc(String csrmc) {
    this.csrmc = csrmc;
}


}

