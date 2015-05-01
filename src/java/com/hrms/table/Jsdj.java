/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 技术等级编码表(jsdj)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Jsdj {
/**  技术等级编码  */
private String jsdjbm;//技术等级编码
/**  技术等级名称  */
private String jsdjmc;//技术等级名称

/**
 * jsdj不带参数的构造方法
 */
public Jsdj() {
}
/**
 * jsdj带参数的构造方法
 * @param jsdjbm 技术等级编码
 * @param jsdjmc 技术等级名称
 */
public Jsdj(String jsdjbm,String jsdjmc) {
    this.jsdjbm=jsdjbm;
    this.jsdjmc=jsdjmc;
}
/**
 *获得技术等级编码
 *@return 技术等级编码
 */
public String getJsdjbm() {
    return jsdjbm;
}

/**
 *设置技术等级编码
 *@param jsdjbm 技术等级编码
 */
public void setJsdjbm(String jsdjbm) {
    this.jsdjbm = jsdjbm;
}

/**
 *获得技术等级名称
 *@return 技术等级名称
 */
public String getJsdjmc() {
    return jsdjmc;
}

/**
 *设置技术等级名称
 *@param jsdjmc 技术等级名称
 */
public void setJsdjmc(String jsdjmc) {
    this.jsdjmc = jsdjmc;
}


}

