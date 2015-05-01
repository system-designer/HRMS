package org.jplus.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Hyberbin
 */
public class CommonUtil {

    public static int parseInt(String num_str) {
        int integer = 0;
        try {
            integer = Integer.parseInt(num_str);
        } catch (NumberFormatException numberFormatException) {
            //LoggerManage.logger.getLogger("字符串到整型类型转换出错！", numberFormatException);
        }
        return integer;
    }

    public static boolean converToNull(Object o) {
        if (o == null || o.toString().trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static String DateFormat(Object date, String format) {
        if (date != null && date.getClass().isAssignableFrom(Date.class)) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } else {
            return "";
        }
    }
}
