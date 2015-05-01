/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 农村人才类别(ncrclb)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Ncrclb {
/**  农村人才类别编码  */
private String ncrclbbm;//农村人才类别编码
/**  农村人才类别名称  */
private String ncrclbmc;//农村人才类别名称

/**
 * ncrclb不带参数的构造方法
 */
public Ncrclb() {
}
/**
 * ncrclb带参数的构造方法
 * @param ncrclbbm 农村人才类别编码
 * @param ncrclbmc 农村人才类别名称
 */
public Ncrclb(String ncrclbbm,String ncrclbmc) {
    this.ncrclbbm=ncrclbbm;
    this.ncrclbmc=ncrclbmc;
}
/**
 *获得农村人才类别编码
 *@return 农村人才类别编码
 */
public String getNcrclbbm() {
    return ncrclbbm;
}

/**
 *设置农村人才类别编码
 *@param ncrclbbm 农村人才类别编码
 */
public void setNcrclbbm(String ncrclbbm) {
    this.ncrclbbm = ncrclbbm;
}

/**
 *获得农村人才类别名称
 *@return 农村人才类别名称
 */
public String getNcrclbmc() {
    return ncrclbmc;
}

/**
 *设置农村人才类别名称
 *@param ncrclbmc 农村人才类别名称
 */
public void setNcrclbmc(String ncrclbmc) {
    this.ncrclbmc = ncrclbmc;
}


}

