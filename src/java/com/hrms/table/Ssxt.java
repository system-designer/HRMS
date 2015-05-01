/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 所属系统(ssxt)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Ssxt {
/**  所属系统编码  */
private String ssxtbm;//所属系统编码
/**  所属系统名称  */
private String ssxtmc;//所属系统名称

/**
 * ssxt不带参数的构造方法
 */
public Ssxt() {
}
/**
 * ssxt带参数的构造方法
 * @param ssxtbm 所属系统编码
 * @param ssxtmc 所属系统名称
 */
public Ssxt(String ssxtbm,String ssxtmc) {
    this.ssxtbm=ssxtbm;
    this.ssxtmc=ssxtmc;
}
/**
 *获得所属系统编码
 *@return 所属系统编码
 */
public String getSsxtbm() {
    return ssxtbm;
}

/**
 *设置所属系统编码
 *@param ssxtbm 所属系统编码
 */
public void setSsxtbm(String ssxtbm) {
    this.ssxtbm = ssxtbm;
}

/**
 *获得所属系统名称
 *@return 所属系统名称
 */
public String getSsxtmc() {
    return ssxtmc;
}

/**
 *设置所属系统名称
 *@param ssxtmc 所属系统名称
 */
public void setSsxtmc(String ssxtmc) {
    this.ssxtmc = ssxtmc;
}


}

