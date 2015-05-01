/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 所属行业(sshy)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Sshy {
/**  所属行业编码  */
private String sshybm;//所属行业编码
/**  所属行业名称  */
private String sshymc;//所属行业名称

/**
 * sshy不带参数的构造方法
 */
public Sshy() {
}
/**
 * sshy带参数的构造方法
 * @param sshybm 所属行业编码
 * @param sshymc 所属行业名称
 */
public Sshy(String sshybm,String sshymc) {
    this.sshybm=sshybm;
    this.sshymc=sshymc;
}
/**
 *获得所属行业编码
 *@return 所属行业编码
 */
public String getSshybm() {
    return sshybm;
}

/**
 *设置所属行业编码
 *@param sshybm 所属行业编码
 */
public void setSshybm(String sshybm) {
    this.sshybm = sshybm;
}

/**
 *获得所属行业名称
 *@return 所属行业名称
 */
public String getSshymc() {
    return sshymc;
}

/**
 *设置所属行业名称
 *@param sshymc 所属行业名称
 */
public void setSshymc(String sshymc) {
    this.sshymc = sshymc;
}


}

