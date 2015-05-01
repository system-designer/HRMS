/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 单位性质编码表(dwxz)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Dwxz {
/**  单位性质编码  */
private String dwxzbm;//单位性质编码
/**  单位性质名称  */
private String dwxzmc;//单位性质名称

/**
 * dwxz不带参数的构造方法
 */
public Dwxz() {
}
/**
 * dwxz带参数的构造方法
 * @param dwxzbm 单位性质编码
 * @param dwxzmc 单位性质名称
 */
public Dwxz(String dwxzbm,String dwxzmc) {
    this.dwxzbm=dwxzbm;
    this.dwxzmc=dwxzmc;
}
/**
 *获得单位性质编码
 *@return 单位性质编码
 */
public String getDwxzbm() {
    return dwxzbm;
}

/**
 *设置单位性质编码
 *@param dwxzbm 单位性质编码
 */
public void setDwxzbm(String dwxzbm) {
    this.dwxzbm = dwxzbm;
}

/**
 *获得单位性质名称
 *@return 单位性质名称
 */
public String getDwxzmc() {
    return dwxzmc;
}

/**
 *设置单位性质名称
 *@param dwxzmc 单位性质名称
 */
public void setDwxzmc(String dwxzmc) {
    this.dwxzmc = dwxzmc;
}


}

