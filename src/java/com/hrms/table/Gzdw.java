/*
 * 当前使用数据库为mysql
 * 此文件由Hyberbin自动生成仅供参考
 * QQ：464863696
 */
package com.hrms.table;

/**
 * 工作单位(gzdw)表POJO类 QQ：464863696
 *
 * @author hyberbin
 */
public class Gzdw {

    /**
     * 工作单位ID
     */
    private Integer gzdwid;//工作单位ID
    /**
     * 单位名称
     */
    private String dwmc;//单位名称
    /**
     * 单位性质编码
     */
    private String dwxzbm;//单位性质编码
    /**
     * 单位类别编码
     */
    private String dwlbbm;//单位类别编码
    /**
     * 所属系统编码
     */
    private String ssxtbm;//所属系统编码
    /**
     * 所属行业编码
     */
    private String sshybm;//所属行业编码
    /**
     * 单位电话
     */
    private String dwdh;//单位电话
    /**
     * 单位地址
     */
    private String dwdz;//单位地址
    /**
     * 单位名称汉语拼音首字母
     */
    private String hypy;//单位名称汉语拼音首字母
    /**
     * 上级单位ID :市级单位的上级为空
     */
    private Integer sjdwid;//上级单位ID :市级单位的上级为空
    /**
     * 联系人
     */
    private String lxr;//联系人
    /**
     * 电子邮箱
     */
    private String dzyx;//电子邮箱
    /**
     * 单位简介
     */
    private String dwjj;//单位简介
    /**
     * 操作员用户姓名
     */
    private String username;//操作员用户姓名
    private String ssdqbm;

    /**
     * gzdw不带参数的构造方法
     */
    public Gzdw() {
    }

    /**
     * gzdw带参数的构造方法
     *
     * @param gzdwid 工作单位ID
     * @param dwmc 单位名称
     * @param dwxzbm 单位性质编码
     * @param dwlbbm 单位类别编码
     * @param ssxtbm 所属系统编码
     * @param sshybm 所属行业编码
     * @param dwdh 单位电话
     * @param dwdz 单位地址
     * @param hypy 单位名称汉语拼音首字母
     * @param sjdwid 上级单位ID :市级单位的上级为空
     * @param lxr 联系人
     * @param dzyx 电子邮箱
     * @param dwjj 单位简介
     * @param username 操作员用户姓名
     */
    public Gzdw(Integer gzdwid, String dwmc, String dwxzbm, String dwlbbm, String ssxtbm, String sshybm, String dwdh, String dwdz, String hypy, Integer sjdwid, String lxr, String dzyx, String dwjj, String username) {
        this.gzdwid = gzdwid;
        this.dwmc = dwmc;
        this.dwxzbm = dwxzbm;
        this.dwlbbm = dwlbbm;
        this.ssxtbm = ssxtbm;
        this.sshybm = sshybm;
        this.dwdh = dwdh;
        this.dwdz = dwdz;
        this.hypy = hypy;
        this.sjdwid = sjdwid;
        this.lxr = lxr;
        this.dzyx = dzyx;
        this.dwjj = dwjj;
        this.username = username;
    }

    /**
     * 获得工作单位ID
     *
     * @return 工作单位ID
     */
    public Integer getGzdwid() {
        return gzdwid;
    }

    /**
     * 设置工作单位ID
     *
     * @param gzdwid 工作单位ID
     */
    public void setGzdwid(Integer gzdwid) {
        this.gzdwid = gzdwid;
    }

    /**
     * 获得单位名称
     *
     * @return 单位名称
     */
    public String getDwmc() {
        return dwmc;
    }

    /**
     * 设置单位名称
     *
     * @param dwmc 单位名称
     */
    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    /**
     * 获得单位性质编码
     *
     * @return 单位性质编码
     */
    public String getDwxzbm() {
        return dwxzbm;
    }

    /**
     * 设置单位性质编码
     *
     * @param dwxzbm 单位性质编码
     */
    public void setDwxzbm(String dwxzbm) {
        this.dwxzbm = dwxzbm;
    }

    /**
     * 获得单位类别编码
     *
     * @return 单位类别编码
     */
    public String getDwlbbm() {
        return dwlbbm;
    }

    /**
     * 设置单位类别编码
     *
     * @param dwlbbm 单位类别编码
     */
    public void setDwlbbm(String dwlbbm) {
        this.dwlbbm = dwlbbm;
    }

    /**
     * 获得所属系统编码
     *
     * @return 所属系统编码
     */
    public String getSsxtbm() {
        return ssxtbm;
    }

    /**
     * 设置所属系统编码
     *
     * @param ssxtbm 所属系统编码
     */
    public void setSsxtbm(String ssxtbm) {
        this.ssxtbm = ssxtbm;
    }

    /**
     * 获得所属行业编码
     *
     * @return 所属行业编码
     */
    public String getSshybm() {
        return sshybm;
    }

    /**
     * 设置所属行业编码
     *
     * @param sshybm 所属行业编码
     */
    public void setSshybm(String sshybm) {
        this.sshybm = sshybm;
    }

    /**
     * 获得单位电话
     *
     * @return 单位电话
     */
    public String getDwdh() {
        return dwdh;
    }

    /**
     * 设置单位电话
     *
     * @param dwdh 单位电话
     */
    public void setDwdh(String dwdh) {
        this.dwdh = dwdh;
    }

    /**
     * 获得单位地址
     *
     * @return 单位地址
     */
    public String getDwdz() {
        return dwdz;
    }

    /**
     * 设置单位地址
     *
     * @param dwdz 单位地址
     */
    public void setDwdz(String dwdz) {
        this.dwdz = dwdz;
    }

    /**
     * 获得单位名称汉语拼音首字母
     *
     * @return 单位名称汉语拼音首字母
     */
    public String getHypy() {
        return hypy;
    }

    /**
     * 设置单位名称汉语拼音首字母
     *
     * @param hypy 单位名称汉语拼音首字母
     */
    public void setHypy(String hypy) {
        this.hypy = hypy;
    }

    /**
     * 获得上级单位ID :市级单位的上级为空
     *
     * @return 上级单位ID :市级单位的上级为空
     */
    public Integer getSjdwid() {
        return sjdwid;
    }

    /**
     * 设置上级单位ID :市级单位的上级为空
     *
     * @param sjdwid 上级单位ID :市级单位的上级为空
     */
    public void setSjdwid(Integer sjdwid) {
        this.sjdwid = sjdwid;
    }

    /**
     * 获得联系人
     *
     * @return 联系人
     */
    public String getLxr() {
        return lxr;
    }

    /**
     * 设置联系人
     *
     * @param lxr 联系人
     */
    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    /**
     * 获得电子邮箱
     *
     * @return 电子邮箱
     */
    public String getDzyx() {
        return dzyx;
    }

    /**
     * 设置电子邮箱
     *
     * @param dzyx 电子邮箱
     */
    public void setDzyx(String dzyx) {
        this.dzyx = dzyx;
    }

    /**
     * 获得单位简介
     *
     * @return 单位简介
     */
    public String getDwjj() {
        return dwjj;
    }

    /**
     * 设置单位简介
     *
     * @param dwjj 单位简介
     */
    public void setDwjj(String dwjj) {
        this.dwjj = dwjj;
    }

    /**
     * 获得操作员用户姓名
     *
     * @return 操作员用户姓名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置操作员用户姓名
     *
     * @param username 操作员用户姓名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getSsdqbm() {
        return ssdqbm;
    }

    public void setSsdqbm(String ssdqbm) {
        this.ssdqbm = ssdqbm;
    }
}
