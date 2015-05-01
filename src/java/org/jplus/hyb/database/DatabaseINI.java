/*
 * 下面几行值不要动，请到缺省包下面database.properties去配置
 */
package org.jplus.hyb.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jplus.util.LoadProperties;
import org.jplus.util.LoggerManage;

/**
 * 为数据库进行配置
 *
 * @version 1.5
 * @author hyber-bin
 */
public class DatabaseINI {

    /** 数据库类型为MYSQL */
    public static final String MYSQL = "mysql";
    /** 数据库类型为SQLSERVER */
    public static final String SQLSERVER = "sqlserver";
    /** 数据库类型为ORACLE */
    public static final String ORACLE = "oracle";
    /** 数据库类型为ACCESS */
    public static final String ACCESS = "access";
    /** 数据库配置模型 */
    public static LoadProperties hyberbin_config_props;
    public static LoadProperties db_config_props;
    public static LoadProperties mapping_props;
    public static boolean isSqlout;
    public static DatabaseINI databaseINI=new DatabaseINI();

    private DatabaseINI() {
        hyberbin_config_props=new LoadProperties("hyberbin.properties");
        String dbConfigPath=hyberbin_config_props.getProperty("DatabaseConfig");
        db_config_props = new LoadProperties(dbConfigPath==null||dbConfigPath.trim().equals("")?"database.properties":dbConfigPath);
        String typeConfigPath=hyberbin_config_props.getProperty("MappingConfig");
        mapping_props =new LoadProperties(typeConfigPath==null||dbConfigPath.trim().equals("")?"typemapping.properties":typeConfigPath);
        isSqlout = db_config_props.getProperty("sqlout").equals("true");
        LoggerManage.setLoggerClass(hyberbin_config_props.getProperty("Logger"));        
    }

    /**
     * 获取一个数据库操作对象
     *
     * @return
     */
    public static DatabaseAccess getDatabase() {    
            return new DatabaseAccess(getConnection());
    }

    /**
     * 用默认配置文件创建连接
     *
     * @return Connection
     */
    public static Connection getConnection() {        
        return getConnection(db_config_props.getProperty("driver"), db_config_props.getProperty("url"), db_config_props.getProperty("user"), db_config_props.getProperty("pass"));
    }

    /**
     * 创建指定组的连接
     *
     * @return Connection
     */
    public static Connection getConnection(String group) {        
        return getConnection(db_config_props.getProperty(group + "-driver"), db_config_props.getProperty(group + "-url"), db_config_props.getProperty(group + "-user"), db_config_props.getProperty(group + "-pass"));
    }

    /**
     * 用提供的参数来创建连接
     *
     * @return Connection
     */
    public static Connection getConnection(String driver, String url, String username, String password) {       
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            LoggerManage.logger.getLogger("DatabaseINI数据库连接错误\t", ex);
        }
        return conn;
    }

    /**
     * 获得数据库类型
     *
     * @param conn 数据库连接
     * @return
     */
    public static String getDatabaseType(Connection conn) {        
        try {
            String name = conn.getMetaData().getDatabaseProductName().toLowerCase();
            if (name.contains(MYSQL)) {
                return MYSQL;
            } else if (name.contains(ACCESS)) {
                return ACCESS;
            } else if (name.contains(ORACLE)) {
                return ORACLE;
            } else if (name.contains(SQLSERVER)) {
                return SQLSERVER;
            }
        } catch (SQLException sQLException) {
        }
        return null;
    }
}