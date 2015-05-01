/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 政治面貌编码表(zzmm)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Zzmm {
/**  政治面貌  */
private String zzmmbm;//政治面貌
/**  政治面貌名称  */
private String zzmmmc;//政治面貌名称

/**
 * zzmm不带参数的构造方法
 */
public Zzmm() {
}
/**
 * zzmm带参数的构造方法
 * @param zzmmbm 政治面貌
 * @param zzmmmc 政治面貌名称
 */
public Zzmm(String zzmmbm,String zzmmmc) {
    this.zzmmbm=zzmmbm;
    this.zzmmmc=zzmmmc;
}
/**
 *获得政治面貌
 *@return 政治面貌
 */
public String getZzmmbm() {
    return zzmmbm;
}

/**
 *设置政治面貌
 *@param zzmmbm 政治面貌
 */
public void setZzmmbm(String zzmmbm) {
    this.zzmmbm = zzmmbm;
}

/**
 *获得政治面貌名称
 *@return 政治面貌名称
 */
public String getZzmmmc() {
    return zzmmmc;
}

/**
 *设置政治面貌名称
 *@param zzmmmc 政治面貌名称
 */
public void setZzmmmc(String zzmmmc) {
    this.zzmmmc = zzmmmc;
}


}

