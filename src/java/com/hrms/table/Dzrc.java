/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 党政人才(dzrc)表POJO类
 * QQ：464863696
 * @author hyberbin
 */
public class Dzrc {
/**  党政人才ID  */
private Integer dzrcid;//党政人才ID
/**  任现职时间  */
private String rxzsj;//任现职时间
/**  近一年（考核情况）  */
private String khqk1;//近一年（考核情况）
/**  近二年（考核情况）  */
private String khqk2;//近二年（考核情况）
/**  近三年（考核情况）  */
private String khqk3;//近三年（考核情况）
/**  人员ID  */
private Integer ryid;//人员ID
/**  操作人员用户名  */
private String username;//操作人员用户名

/**
 * dzrc不带参数的构造方法
 */
public Dzrc() {
}
/**
 * dzrc带参数的构造方法
 * @param dzrcid 党政人才ID
 * @param rxzsj 任现职时间
 * @param khqk1 近一年（考核情况）
 * @param khqk2 近二年（考核情况）
 * @param khqk3 近三年（考核情况）
 * @param ryid 人员ID
 * @param username 操作人员用户名
 */
public Dzrc(Integer dzrcid,String rxzsj,String khqk1,String khqk2,String khqk3,Integer ryid,String username) {
    this.dzrcid=dzrcid;
    this.rxzsj=rxzsj;
    this.khqk1=khqk1;
    this.khqk2=khqk2;
    this.khqk3=khqk3;
    this.ryid=ryid;
    this.username=username;
}
/**
 *获得党政人才ID
 *@return 党政人才ID
 */
public Integer getDzrcid() {
    return dzrcid;
}

/**
 *设置党政人才ID
 *@param dzrcid 党政人才ID
 */
public void setDzrcid(Integer dzrcid) {
    this.dzrcid = dzrcid;
}

/**
 *获得任现职时间
 *@return 任现职时间
 */
public String getRxzsj() {
    return rxzsj;
}

/**
 *设置任现职时间
 *@param rxzsj 任现职时间
 */
public void setRxzsj(String rxzsj) {
    this.rxzsj = rxzsj;
}

/**
 *获得近一年（考核情况）
 *@return 近一年（考核情况）
 */
public String getKhqk1() {
    return khqk1;
}

/**
 *设置近一年（考核情况）
 *@param khqk1 近一年（考核情况）
 */
public void setKhqk1(String khqk1) {
    this.khqk1 = khqk1;
}

/**
 *获得近二年（考核情况）
 *@return 近二年（考核情况）
 */
public String getKhqk2() {
    return khqk2;
}

/**
 *设置近二年（考核情况）
 *@param khqk2 近二年（考核情况）
 */
public void setKhqk2(String khqk2) {
    this.khqk2 = khqk2;
}

/**
 *获得近三年（考核情况）
 *@return 近三年（考核情况）
 */
public String getKhqk3() {
    return khqk3;
}

/**
 *设置近三年（考核情况）
 *@param khqk3 近三年（考核情况）
 */
public void setKhqk3(String khqk3) {
    this.khqk3 = khqk3;
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
 *获得操作人员用户名
 *@return 操作人员用户名
 */
public String getUsername() {
    return username;
}

/**
 *设置操作人员用户名
 *@param username 操作人员用户名
 */
public void setUsername(String username) {
    this.username = username;
}


}

