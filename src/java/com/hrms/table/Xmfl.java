/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 项目分类(xmfl)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Xmfl {
/**  项目分类编码  */
private String xmflbm;//项目分类编码
/**  项目分类名称  */
private String xmflmc;//项目分类名称

/**
 * xmfl不带参数的构造方法
 */
public Xmfl() {
}
/**
 * xmfl带参数的构造方法
 * @param xmflbm 项目分类编码
 * @param xmflmc 项目分类名称
 */
public Xmfl(String xmflbm,String xmflmc) {
    this.xmflbm=xmflbm;
    this.xmflmc=xmflmc;
}
/**
 *获得项目分类编码
 *@return 项目分类编码
 */
public String getXmflbm() {
    return xmflbm;
}

/**
 *设置项目分类编码
 *@param xmflbm 项目分类编码
 */
public void setXmflbm(String xmflbm) {
    this.xmflbm = xmflbm;
}

/**
 *获得项目分类名称
 *@return 项目分类名称
 */
public String getXmflmc() {
    return xmflmc;
}

/**
 *设置项目分类名称
 *@param xmflmc 项目分类名称
 */
public void setXmflmc(String xmflmc) {
    this.xmflmc = xmflmc;
}


}

