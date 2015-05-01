/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 资格证书(zgzs)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Zgzs {
/**  资格证书ID  */
private Integer zgzsid;//资格证书ID
/**  工种  */
private String gz;//工种
/**  等级  */
private String dj;//等级
/**  证书号  */
private String zsh;//证书号
/**  发证时间  */
private String fzsj;//发证时间
/**  发证机关  */
private String fzjg;//发证机关
/**  人员ID  */
private Integer ryid;//人员ID
/**  用户名  */
private String username;//用户名

/**
 * zgzs不带参数的构造方法
 */
public Zgzs() {
}
/**
 * zgzs带参数的构造方法
 * @param zgzsid 资格证书ID
 * @param gz 工种
 * @param dj 等级
 * @param zsh 证书号
 * @param fzsj 发证时间
 * @param fzjg 发证机关
 * @param ryid 人员ID
 * @param username 用户名
 */
public Zgzs(Integer zgzsid,String gz,String dj,String zsh,String fzsj,String fzjg,Integer ryid,String username) {
    this.zgzsid=zgzsid;
    this.gz=gz;
    this.dj=dj;
    this.zsh=zsh;
    this.fzsj=fzsj;
    this.fzjg=fzjg;
    this.ryid=ryid;
    this.username=username;
}
/**
 *获得资格证书ID
 *@return 资格证书ID
 */
public Integer getZgzsid() {
    return zgzsid;
}

/**
 *设置资格证书ID
 *@param zgzsid 资格证书ID
 */
public void setZgzsid(Integer zgzsid) {
    this.zgzsid = zgzsid;
}

/**
 *获得工种
 *@return 工种
 */
public String getGz() {
    return gz;
}

/**
 *设置工种
 *@param gz 工种
 */
public void setGz(String gz) {
    this.gz = gz;
}

/**
 *获得等级
 *@return 等级
 */
public String getDj() {
    return dj;
}

/**
 *设置等级
 *@param dj 等级
 */
public void setDj(String dj) {
    this.dj = dj;
}

/**
 *获得证书号
 *@return 证书号
 */
public String getZsh() {
    return zsh;
}

/**
 *设置证书号
 *@param zsh 证书号
 */
public void setZsh(String zsh) {
    this.zsh = zsh;
}

/**
 *获得发证时间
 *@return 发证时间
 */
public String getFzsj() {
    return fzsj;
}

/**
 *设置发证时间
 *@param fzsj 发证时间
 */
public void setFzsj(String fzsj) {
    this.fzsj = fzsj;
}

/**
 *获得发证机关
 *@return 发证机关
 */
public String getFzjg() {
    return fzjg;
}

/**
 *设置发证机关
 *@param fzjg 发证机关
 */
public void setFzjg(String fzjg) {
    this.fzjg = fzjg;
}

/**
 *获得人员ID
 *@return 人员ID
 */
public Integer getRyid() {
    return ryid;
}

/**
 *设置人员ID
 *@param ryid 人员ID
 */
public void setRyid(Integer ryid) {
    this.ryid = ryid;
}

/**
 *获得用户名
 *@return 用户名
 */
public String getUsername() {
    return username;
}

/**
 *设置用户名
 *@param username 用户名
 */
public void setUsername(String username) {
    this.username = username;
}


}

