/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 基本信息(jbxx)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Jbxx {
/**  人员ID  */
private Integer ryid;//人员ID
/**  证件号码  */
private String zjhm;//证件号码
/**  姓名  */
private String xm;//姓名
/**  性别  */
private String xb;//性别
/**  出生日期  */
private java.sql.Date csrq;//出生日期
/**  民族 1.汉族 2.少数民族  */
private String mz;//民族 1.汉族 2.少数民族
/**  籍贯  */
private String jg;//籍贯
/**  工作单位ID  */
private Integer gzdwid;//工作单位ID
/**  工作时间  */
private String gzsj;//工作时间
/**  政治面貌编码  */
private String zzmmbm;//政治面貌编码
/**  入党时间  */
private java.sql.Date rdsj;//入党时间
/**  职级编码  */
private String zjbm;//职级编码
/**  职务  */
private String zw;//职务
/**  学习形式 1.全日制 2.在职  */
private String xxxs;//学习形式 1.全日制 2.在职
/**  学历编码  */
private String xlbm;//学历编码
/**  学位编码  */
private String xwbm;//学位编码
/**  毕业学校  */
private String byxx;//毕业学校
/**  专业名称  */
private String zymc;//专业名称
/**  户籍所在地  */
private String hjszd;//户籍所在地
/**  健康状况  */
private String jkzk;//健康状况
/**  通信地址  */
private String txdz;//通信地址
/**  邮政编码  */
private String yzbm;//邮政编码
/**  联系电话  */
private String lxdh;//联系电话
/**  婚姻状况编码 1.未婚 2. 已婚 3.离异  */
private String hyzkbm;//婚姻状况编码 1.未婚 2. 已婚 3.离异
/**  是否党政人才  */
private Boolean sfdzrc;//是否党政人才
/**  是否管理人才  */
private Boolean sfglrc;//是否管理人才
/**  是否专技人才  */
private Boolean sfzjrc;//是否专技人才
/**  是否高技能人才  */
private Boolean sfgjnrc;//是否高技能人才
/**  是否农村实用人才  */
private Boolean sfncsyrc;//是否农村实用人才
/**  是否社会工作人才  */
private Boolean sfshgzrc;//是否社会工作人才
/**  是否创业人才  */
private Boolean sfcyrc;//是否创业人才
/**  项目分类编码  */
private String xmflbm;//项目分类编码
/**  照片存储路径  */
private String zp;//照片存储路径
/**  操作员用户名  */
private String username;//操作员用户名
/**  是否在册  */
private Boolean sfzc;//是否在册
/**  不在册原因  */
private String bzcyy;//不在册原因

/**
 * jbxx不带参数的构造方法
 */
public Jbxx() {
}
/**
 * jbxx带参数的构造方法
 * @param ryid 人员ID
 * @param zjhm 证件号码
 * @param xm 姓名
 * @param xb 性别
 * @param csrq 出生日期
 * @param mz 民族 1.汉族 2.少数民族
 * @param jg 籍贯
 * @param gzdwid 工作单位ID
 * @param gzsj 工作时间
 * @param zzmmbm 政治面貌编码
 * @param rdsj 入党时间
 * @param zjbm 职级编码
 * @param zw 职务
 * @param xxxs 学习形式 1.全日制 2.在职
 * @param xlbm 学历编码
 * @param xwbm 学位编码
 * @param byxx 毕业学校
 * @param zymc 专业名称
 * @param hjszd 户籍所在地
 * @param jkzk 健康状况
 * @param txdz 通信地址
 * @param yzbm 邮政编码
 * @param lxdh 联系电话
 * @param hyzkbm 婚姻状况编码 1.未婚 2. 已婚 3.离异
 * @param sfdzrc 是否党政人才
 * @param sfglrc 是否管理人才
 * @param sfzjrc 是否专技人才
 * @param sfgjnrc 是否高技能人才
 * @param sfncsyrc 是否农村实用人才
 * @param sfshgzrc 是否社会工作人才
 * @param sfcyrc 是否创业人才
 * @param xmflbm 项目分类编码
 * @param zp 照片存储路径
 * @param username 操作员用户名
 * @param sfzc 是否在册
 * @param bzcyy 不在册原因
 */
public Jbxx(Integer ryid,String zjhm,String xm,String xb,java.sql.Date csrq,String mz,String jg,Integer gzdwid,String gzsj,String zzmmbm,java.sql.Date rdsj,String zjbm,String zw,String xxxs,String xlbm,String xwbm,String byxx,String zymc,String hjszd,String jkzk,String txdz,String yzbm,String lxdh,String hyzkbm,Boolean sfdzrc,Boolean sfglrc,Boolean sfzjrc,Boolean sfgjnrc,Boolean sfncsyrc,Boolean sfshgzrc,Boolean sfcyrc,String xmflbm,String zp,String username,Boolean sfzc,String bzcyy) {
    this.ryid=ryid;
    this.zjhm=zjhm;
    this.xm=xm;
    this.xb=xb;
    this.csrq=csrq;
    this.mz=mz;
    this.jg=jg;
    this.gzdwid=gzdwid;
    this.gzsj=gzsj;
    this.zzmmbm=zzmmbm;
    this.rdsj=rdsj;
    this.zjbm=zjbm;
    this.zw=zw;
    this.xxxs=xxxs;
    this.xlbm=xlbm;
    this.xwbm=xwbm;
    this.byxx=byxx;
    this.zymc=zymc;
    this.hjszd=hjszd;
    this.jkzk=jkzk;
    this.txdz=txdz;
    this.yzbm=yzbm;
    this.lxdh=lxdh;
    this.hyzkbm=hyzkbm;
    this.sfdzrc=sfdzrc;
    this.sfglrc=sfglrc;
    this.sfzjrc=sfzjrc;
    this.sfgjnrc=sfgjnrc;
    this.sfncsyrc=sfncsyrc;
    this.sfshgzrc=sfshgzrc;
    this.sfcyrc=sfcyrc;
    this.xmflbm=xmflbm;
    this.zp=zp;
    this.username=username;
    this.sfzc=sfzc;
    this.bzcyy=bzcyy;
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
 *获得证件号码
 *@return 证件号码
 */
public String getZjhm() {
    return zjhm;
}

/**
 *设置证件号码
 *@param zjhm 证件号码
 */
public void setZjhm(String zjhm) {
    this.zjhm = zjhm;
}

/**
 *获得姓名
 *@return 姓名
 */
public String getXm() {
    return xm;
}

/**
 *设置姓名
 *@param xm 姓名
 */
public void setXm(String xm) {
    this.xm = xm;
}

/**
 *获得性别
 *@return 性别
 */
public String getXb() {
    return xb;
}

/**
 *设置性别
 *@param xb 性别
 */
public void setXb(String xb) {
    this.xb = xb;
}

/**
 *获得出生日期
 *@return 出生日期
 */
public java.sql.Date getCsrq() {
    return csrq;
}

/**
 *设置出生日期
 *@param csrq 出生日期
 */
public void setCsrq(java.sql.Date csrq) {
    this.csrq = csrq;
}

/**
 *获得民族 1.汉族 2.少数民族
 *@return 民族 1.汉族 2.少数民族
 */
public String getMz() {
    return mz;
}

/**
 *设置民族 1.汉族 2.少数民族
 *@param mz 民族 1.汉族 2.少数民族
 */
public void setMz(String mz) {
    this.mz = mz;
}

/**
 *获得籍贯
 *@return 籍贯
 */
public String getJg() {
    return jg;
}

/**
 *设置籍贯
 *@param jg 籍贯
 */
public void setJg(String jg) {
    this.jg = jg;
}

/**
 *获得工作单位ID
 *@return 工作单位ID
 */
public Integer getGzdwid() {
    return gzdwid;
}

/**
 *设置工作单位ID
 *@param gzdwid 工作单位ID
 */
public void setGzdwid(Integer gzdwid) {
    this.gzdwid = gzdwid;
}

/**
 *获得工作时间
 *@return 工作时间
 */
public String getGzsj() {
    return gzsj;
}

/**
 *设置工作时间
 *@param gzsj 工作时间
 */
public void setGzsj(String gzsj) {
    this.gzsj = gzsj;
}

/**
 *获得政治面貌编码
 *@return 政治面貌编码
 */
public String getZzmmbm() {
    return zzmmbm;
}

/**
 *设置政治面貌编码
 *@param zzmmbm 政治面貌编码
 */
public void setZzmmbm(String zzmmbm) {
    this.zzmmbm = zzmmbm;
}

/**
 *获得入党时间
 *@return 入党时间
 */
public java.sql.Date getRdsj() {
    return rdsj;
}

/**
 *设置入党时间
 *@param rdsj 入党时间
 */
public void setRdsj(java.sql.Date rdsj) {
    this.rdsj = rdsj;
}

/**
 *获得职级编码
 *@return 职级编码
 */
public String getZjbm() {
    return zjbm;
}

/**
 *设置职级编码
 *@param zjbm 职级编码
 */
public void setZjbm(String zjbm) {
    this.zjbm = zjbm;
}

/**
 *获得职务
 *@return 职务
 */
public String getZw() {
    return zw;
}

/**
 *设置职务
 *@param zw 职务
 */
public void setZw(String zw) {
    this.zw = zw;
}

/**
 *获得学习形式 1.全日制 2.在职
 *@return 学习形式 1.全日制 2.在职
 */
public String getXxxs() {
    return xxxs;
}

/**
 *设置学习形式 1.全日制 2.在职
 *@param xxxs 学习形式 1.全日制 2.在职
 */
public void setXxxs(String xxxs) {
    this.xxxs = xxxs;
}

/**
 *获得学历编码
 *@return 学历编码
 */
public String getXlbm() {
    return xlbm;
}

/**
 *设置学历编码
 *@param xlbm 学历编码
 */
public void setXlbm(String xlbm) {
    this.xlbm = xlbm;
}

/**
 *获得学位编码
 *@return 学位编码
 */
public String getXwbm() {
    return xwbm;
}

/**
 *设置学位编码
 *@param xwbm 学位编码
 */
public void setXwbm(String xwbm) {
    this.xwbm = xwbm;
}

/**
 *获得毕业学校
 *@return 毕业学校
 */
public String getByxx() {
    return byxx;
}

/**
 *设置毕业学校
 *@param byxx 毕业学校
 */
public void setByxx(String byxx) {
    this.byxx = byxx;
}

/**
 *获得专业名称
 *@return 专业名称
 */
public String getZymc() {
    return zymc;
}

/**
 *设置专业名称
 *@param zymc 专业名称
 */
public void setZymc(String zymc) {
    this.zymc = zymc;
}

/**
 *获得户籍所在地
 *@return 户籍所在地
 */
public String getHjszd() {
    return hjszd;
}

/**
 *设置户籍所在地
 *@param hjszd 户籍所在地
 */
public void setHjszd(String hjszd) {
    this.hjszd = hjszd;
}

/**
 *获得健康状况
 *@return 健康状况
 */
public String getJkzk() {
    return jkzk;
}

/**
 *设置健康状况
 *@param jkzk 健康状况
 */
public void setJkzk(String jkzk) {
    this.jkzk = jkzk;
}

/**
 *获得通信地址
 *@return 通信地址
 */
public String getTxdz() {
    return txdz;
}

/**
 *设置通信地址
 *@param txdz 通信地址
 */
public void setTxdz(String txdz) {
    this.txdz = txdz;
}

/**
 *获得邮政编码
 *@return 邮政编码
 */
public String getYzbm() {
    return yzbm;
}

/**
 *设置邮政编码
 *@param yzbm 邮政编码
 */
public void setYzbm(String yzbm) {
    this.yzbm = yzbm;
}

/**
 *获得联系电话
 *@return 联系电话
 */
public String getLxdh() {
    return lxdh;
}

/**
 *设置联系电话
 *@param lxdh 联系电话
 */
public void setLxdh(String lxdh) {
    this.lxdh = lxdh;
}

/**
 *获得婚姻状况编码 1.未婚 2. 已婚 3.离异
 *@return 婚姻状况编码 1.未婚 2. 已婚 3.离异
 */
public String getHyzkbm() {
    return hyzkbm;
}

/**
 *设置婚姻状况编码 1.未婚 2. 已婚 3.离异
 *@param hyzkbm 婚姻状况编码 1.未婚 2. 已婚 3.离异
 */
public void setHyzkbm(String hyzkbm) {
    this.hyzkbm = hyzkbm;
}

/**
 *获得是否党政人才
 *@return 是否党政人才
 */
public Boolean getSfdzrc() {
    return sfdzrc;
}

/**
 *设置是否党政人才
 *@param sfdzrc 是否党政人才
 */
public void setSfdzrc(Boolean sfdzrc) {
    this.sfdzrc = sfdzrc;
}

/**
 *获得是否管理人才
 *@return 是否管理人才
 */
public Boolean getSfglrc() {
    return sfglrc;
}

/**
 *设置是否管理人才
 *@param sfglrc 是否管理人才
 */
public void setSfglrc(Boolean sfglrc) {
    this.sfglrc = sfglrc;
}

/**
 *获得是否专技人才
 *@return 是否专技人才
 */
public Boolean getSfzjrc() {
    return sfzjrc;
}

/**
 *设置是否专技人才
 *@param sfzjrc 是否专技人才
 */
public void setSfzjrc(Boolean sfzjrc) {
    this.sfzjrc = sfzjrc;
}

/**
 *获得是否高技能人才
 *@return 是否高技能人才
 */
public Boolean getSfgjnrc() {
    return sfgjnrc;
}

/**
 *设置是否高技能人才
 *@param sfgjnrc 是否高技能人才
 */
public void setSfgjnrc(Boolean sfgjnrc) {
    this.sfgjnrc = sfgjnrc;
}

/**
 *获得是否农村实用人才
 *@return 是否农村实用人才
 */
public Boolean getSfncsyrc() {
    return sfncsyrc;
}

/**
 *设置是否农村实用人才
 *@param sfncsyrc 是否农村实用人才
 */
public void setSfncsyrc(Boolean sfncsyrc) {
    this.sfncsyrc = sfncsyrc;
}

/**
 *获得是否社会工作人才
 *@return 是否社会工作人才
 */
public Boolean getSfshgzrc() {
    return sfshgzrc;
}

/**
 *设置是否社会工作人才
 *@param sfshgzrc 是否社会工作人才
 */
public void setSfshgzrc(Boolean sfshgzrc) {
    this.sfshgzrc = sfshgzrc;
}

/**
 *获得是否创业人才
 *@return 是否创业人才
 */
public Boolean getSfcyrc() {
    return sfcyrc;
}

/**
 *设置是否创业人才
 *@param sfcyrc 是否创业人才
 */
public void setSfcyrc(Boolean sfcyrc) {
    this.sfcyrc = sfcyrc;
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
 *获得照片存储路径
 *@return 照片存储路径
 */
public String getZp() {
    return zp;
}

/**
 *设置照片存储路径
 *@param zp 照片存储路径
 */
public void setZp(String zp) {
    this.zp = zp;
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

/**
 *获得是否在册
 *@return 是否在册
 */
public Boolean getSfzc() {
    return sfzc;
}

/**
 *设置是否在册
 *@param sfzc 是否在册
 */
public void setSfzc(Boolean sfzc) {
    this.sfzc = sfzc;
}

/**
 *获得不在册原因
 *@return 不在册原因
 */
public String getBzcyy() {
    return bzcyy;
}

/**
 *设置不在册原因
 *@param bzcyy 不在册原因
 */
public void setBzcyy(String bzcyy) {
    this.bzcyy = bzcyy;
}


}

