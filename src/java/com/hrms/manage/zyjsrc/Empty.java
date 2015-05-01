/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrms.manage.zyjsrc;

import com.hrms.util.Util;
import org.jplus.yydbgai.EasyMapsManager;

/**
 *
 * @author star
 */
public class Empty {

    public String empth(String ss, String str, EasyMapsManager easyMapsManager) {
        if (Util.isEmpty(str)) {
            return "";
        } else {
            easyMapsManager.setPreparedParameter(str);
            return "and " + ss + "=? ";
        }
    }

    public String emys(String name, String value, String checked, EasyMapsManager easyMapsManager) {
        if (!isEmpty(value)) {
            easyMapsManager.setPreparedParameter(value);
            if (isEmpty(checked)) {
                return "and " + name + "=? ";
            } else {
                return "and " + name + "<=? ";
            }
        } else {
            return "";
        }
    }

    public boolean isEmpty(String s) {
        if (s == null || "".equals(s)) {
            return true;
        }
        return false;
    }
}
