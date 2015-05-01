/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 奖励等级编码表(jldj)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Jldj {
/**  奖励等级编码  */
private String jldjbm;//奖励等级编码
/**  奖励等级名称  */
private String jldjmc;//奖励等级名称

/**
 * jldj不带参数的构造方法
 */
public Jldj() {
}
/**
 * jldj带参数的构造方法
 * @param jldjbm 奖励等级编码
 * @param jldjmc 奖励等级名称
 */
public Jldj(String jldjbm,String jldjmc) {
    this.jldjbm=jldjbm;
    this.jldjmc=jldjmc;
}
/**
 *获得奖励等级编码
 *@return 奖励等级编码
 */
public String getJldjbm() {
    return jldjbm;
}

/**
 *设置奖励等级编码
 *@param jldjbm 奖励等级编码
 */
public void setJldjbm(String jldjbm) {
    this.jldjbm = jldjbm;
}

/**
 *获得奖励等级名称
 *@return 奖励等级名称
 */
public String getJldjmc() {
    return jldjmc;
}

/**
 *设置奖励等级名称
 *@param jldjmc 奖励等级名称
 */
public void setJldjmc(String jldjmc) {
    this.jldjmc = jldjmc;
}


}

