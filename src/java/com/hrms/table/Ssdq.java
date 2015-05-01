/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrms.table;

/**
 *
 * @author liulei
 */
public class Ssdq {

    private String ssdqbm;
    private String ssdqmc;

    public Ssdq(String ssdqbm, String ssdqmc) {
        this.ssdqbm = ssdqbm;
        this.ssdqmc = ssdqmc;
    }
    
    public String getSsdqbm() {
        return ssdqbm;
    }

    public void setSsdqbm(String ssdqbm) {
        this.ssdqbm = ssdqbm;
    }

    public String getSsdqmc() {
        return ssdqmc;
    }

    public void setSsdqmc(String ssdqmc) {
        this.ssdqmc = ssdqmc;
    }
}
