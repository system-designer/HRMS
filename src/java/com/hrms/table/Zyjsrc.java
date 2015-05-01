/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 专业技术人才(zyjsrc)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Zyjsrc {
/**  专业技术人才ID  */
private Integer zyjsrcid;//专业技术人才ID
/**  从事专业  */
private String cszy;//从事专业
/**  工作岗位  */
private String gzgw;//工作岗位
/**  职称类别编码  */
private String zclbbm;//职称类别编码
/**  技术职称编码  */
private String jszcbm;//技术职称编码
/**  获奖成果  */
private String hjcq;//获奖成果
/**  人员ID  */
private Integer ryid;//人员ID
/**  操作员用户名  */
private String username;//操作员用户名

/**
 * zyjsrc不带参数的构造方法
 */
public Zyjsrc() {
}
/**
 * zyjsrc带参数的构造方法
 * @param zyjsrcid 专业技术人才ID
 * @param cszy 从事专业
 * @param gzgw 工作岗位
 * @param zclbbm 职称类别编码
 * @param jszcbm 技术职称编码
 * @param hjcq 获奖成果
 * @param ryid 人员ID
 * @param username 操作员用户名
 */
public Zyjsrc(Integer zyjsrcid,String cszy,String gzgw,String zclbbm,String jszcbm,String hjcq,Integer ryid,String username) {
    this.zyjsrcid=zyjsrcid;
    this.cszy=cszy;
    this.gzgw=gzgw;
    this.zclbbm=zclbbm;
    this.jszcbm=jszcbm;
    this.hjcq=hjcq;
    this.ryid=ryid;
    this.username=username;
}
/**
 *获得专业技术人才ID
 *@return 专业技术人才ID
 */
public Integer getZyjsrcid() {
    return zyjsrcid;
}

/**
 *设置专业技术人才ID
 *@param zyjsrcid 专业技术人才ID
 */
public void setZyjsrcid(Integer zyjsrcid) {
    this.zyjsrcid = zyjsrcid;
}

/**
 *获得从事专业
 *@return 从事专业
 */
public String getCszy() {
    return cszy;
}

/**
 *设置从事专业
 *@param cszy 从事专业
 */
public void setCszy(String cszy) {
    this.cszy = cszy;
}

/**
 *获得工作岗位
 *@return 工作岗位
 */
public String getGzgw() {
    return gzgw;
}

/**
 *设置工作岗位
 *@param gzgw 工作岗位
 */
public void setGzgw(String gzgw) {
    this.gzgw = gzgw;
}

/**
 *获得职称类别编码
 *@return 职称类别编码
 */
public String getZclbbm() {
    return zclbbm;
}

/**
 *设置职称类别编码
 *@param zclbbm 职称类别编码
 */
public void setZclbbm(String zclbbm) {
    this.zclbbm = zclbbm;
}

/**
 *获得技术职称编码
 *@return 技术职称编码
 */
public String getJszcbm() {
    return jszcbm;
}

/**
 *设置技术职称编码
 *@param jszcbm 技术职称编码
 */
public void setJszcbm(String jszcbm) {
    this.jszcbm = jszcbm;
}

/**
 *获得获奖成果
 *@return 获奖成果
 */
public String getHjcq() {
    return hjcq;
}

/**
 *设置获奖成果
 *@param hjcq 获奖成果
 */
public void setHjcq(String hjcq) {
    this.hjcq = hjcq;
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
 *获得操作员用户名
 *@return 操作员用户名
 */
public String getUsername() {
    return username;
}

/**
 *设置操作员用户名
 *@param username 操作员用户名
 */
public void setUsername(String username) {
    this.username = username;
}


}

