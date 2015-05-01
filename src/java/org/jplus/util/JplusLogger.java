package org.jplus.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.jplus.hyb.database.DatabaseINI;
import org.jplus.hyb.database.Hyberbin;
import org.jplus.table.Log;

/**
 *
 * @author Admin_d
 */
public class JplusLogger implements Logger {

    public final static int SYSTEMLOG = 0;
    public final static int ERROR = 1;
    public final static int NORMALLOG = 2;

    /**
     * 实现了记录日志的方法 这里采用JDK自带来的日志
     *
     * @param message 操作信息
     * @param ex Exception
     */
    @Override
    public void getLogger(String message, Exception ex) {
        System.out.println(message);
        String error = getError(ex);
        System.out.println(error);
        Log log = new Log();
        log.setMessage(message);
        log.setType(SYSTEMLOG);
        if (ex != null) {
            log.setMessage("[" + message + "]:[" + ex.getMessage() + "]:[" + error + "]");
            log.setType(ERROR);
        }
//        log(log);
    }

    /**
     * 设置日志类
     *
     * @param logger 日志类
     */
    @Override
    public void setLogger(Logger logger) {
        logger = new JplusLogger();
    }

    @Override
    public void debug(String message) {
        System.out.println(message);
    }

    public static void log(HttpServletRequest request, String message) {
        System.out.println(message);
        Log log = new Log();
        log.setType(NORMALLOG);
        log.setIp(getIp(request));
        log.setMessage("url:[" + request.getRequestURI() + "][" + request.getQueryString() + "][" + message + "]");
        Object user = request.getSession().getAttribute("user");
        if (user != null) {
            log.setUsername((String) ((Map) user).get("yhm"));
        }
//        log(log);
    }

    private synchronized static void log(Log log) {
        Hyberbin hyberbin = new Hyberbin(log);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.setLogtime(df.format(new Date()));
        if (DatabaseINI.isSqlout) {
            DatabaseINI.isSqlout = false;
            log.setType(SYSTEMLOG);
            hyberbin.insert("id");
            DatabaseINI.isSqlout = true;
        } else {
            log.setMessage(log.getMessage().replace("[null]", ""));
            hyberbin.insert("id");
        }
    }

    @Override
    public void getLogger(Object object, String message, Exception ex) {
        System.out.println(message);
        String error = getError(ex);
        System.out.println(error);
        HttpServletRequest request = (HttpServletRequest) object;
        Log log = new Log();
        log.setType(ERROR);
        log.setIp(getIp(request));
        log.setMessage("url:[" + request.getRequestURI() + "][" + request.getQueryString() + "][" + ex.getMessage() + "]:[" + error + "]");
        Object user = request.getSession().getAttribute("user");
        if (user != null) {
            log.setUsername((String) ((Map) user).get("yhm"));
        }
//        log(log);
    }

    /**
     * 获得客户端IP地址
     *
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip.trim())) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip.trim())) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip.trim())) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    @Override
    public void info(String message) {
        System.out.println(message);
        Log log = new Log();
        log.setType(NORMALLOG);
        log.setMessage(message);
//        log(log);
    }

    private static String getError(Exception ex) {
        if (ex != null) {
            StringBuilder builder = new StringBuilder();
            StackTraceElement[] stackTrace = ex.getStackTrace();
            for (StackTraceElement ste : stackTrace) {
                String errorString = ste.toString();
                if (!errorString.startsWith("org.apache") && !errorString.startsWith("com.mysql")) {
                    builder.append(errorString).append("\n");
                }
            }
            return builder.toString();
        } else {
            return "";
        }
    }
}
