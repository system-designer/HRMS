/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrms.manage.xtwh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author 张浩春
 * @time 2012-4-26 19:36:02 固定变量的存储类
 * @time 2013-3-15 邹灿加入安置房源固定图片路径 IMGPAHT
 */
public class Constants {

    public static final List<String> PICTURE_TYPE = new ArrayList<String>(Arrays.asList(".jpg", ".png", ".bmp", ".jpeg", ".gif"));
    public static final String IMGPATH = "photo";
    public static final String[] LIMIT_TYPE = new String[]{".jpg", ".png", ".bmp", ".jpeg", ".gif"};
}
