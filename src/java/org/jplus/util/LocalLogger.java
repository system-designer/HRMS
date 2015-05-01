package org.jplus.util;

import java.util.logging.Level;

/**
 * 本地默认的日志类
 * @author hyberbin
 */
public class LocalLogger implements Logger {

    /**
     * 实现了记录日志的方法 这里采用JDK自带来的日志
     * @param message 操作信息
     * @param ex Exception
     */
    @Override
    public void getLogger(String message, Exception ex) {
        System.out.println(message);
        if (ex != null) {
            java.util.logging.Logger.getLogger(message).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 设置日志类
     * @param logger 日志类
     */
    @Override
    public void setLogger(Logger logger) {
        logger = new LocalLogger();
    }

    @Override
    public void debug(String message) {
        System.out.println(message);
    }

    @Override
    public void getLogger(Object request, String message, Exception ex) {
        getLogger(message, ex);
    }

    @Override
    public void info(String message) {
        System.out.println(message);
    }
}
