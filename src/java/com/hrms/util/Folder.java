/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrms.util;

/**
 *
 * @author 张浩春
 * @time 2012-5-16 12:59:31
 */
public class Folder {

    private String name;//文件名称
    private String path;//文件绝对路径

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Folder() {
    }

    public Folder(String name, String path) {
        this.name = name;
        this.path = path;
    }
    

}
