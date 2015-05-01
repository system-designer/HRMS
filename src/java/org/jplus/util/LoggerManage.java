/**
 * 基本日志管理类 此日志对象是一个接口 通过配置注射进来
 */
package org.jplus.util;

import java.util.logging.Level;

/**
 * 日志管理类 . <P>要使用其他日志请在database.properties文件中配置类名.</p>
 * <p>DatabaseINI会在系统加载的时候读取日志类.</p> <p>没有配置默认加载LocalLogger.</p>
 * @author hyberbin
 */
public class LoggerManage {

    /**
     * 日志对象
     */
    public static Logger logger;

    /**
     * 注入日志对象
     * @param logger
     */
    public static void setLogger(Logger logger) {
        LoggerManage.logger = logger;
    }

    /**
     * 设置日志类
     * @param className 类名
     */
    public static void setLoggerClass(String className) {
        try {
            setLogger((Logger) Class.forName(className).newInstance());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger("LoggerManage设置日志类失败！").log(Level.SEVERE, null, ex);
        }
    }
}
