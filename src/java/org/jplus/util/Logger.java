/**
 * 本框架的日志类的接口. 要想使用其它日志类必需将其它日志再封装一次. 实现这接口的所有方法.
 */
package org.jplus.util;

/**
 * 本框架的日志类的接口
 *
 * @author hyberbin
 */
public interface Logger {

    /**
     * 记录日志的方法
     *
     * @param message 操作信息
     * @param ex Exception
     */
    public void getLogger(String message, Exception ex);

    /**
     * 记录日志的方法
     * 可以多传一个参数Object类型以便记录其它信息
     * @param Object request
     * @param message 操作信息
     * @param ex Exception
     */
    public void getLogger(Object request, String message, Exception ex);

    /**
     * 记录日志的方法
     *
     * @param message 操作信息
     */
    public void debug(String message);
    /**
     * 记录日志的方法
     *
     * @param message 操作信息
     */
    public void info(String message);

    /**
     * 设置日志类
     *
     * @param logger 日志类
     */
    public void setLogger(Logger logger);
}
