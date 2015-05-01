/*
 * 
 * 
 */
package org.jplus.yydbgai;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.jplus.hyb.database.DatabaseINI;
import org.jplus.util.LoggerManage;

/**
 *
 * @author e
 */
public class DatabaseAccess {

    private Connection conn = null;
    private PreparedStatement pstm = null;
    private CallableStatement cstm = null;
    private ResultSet rs = null;
    /**
     * 预处理参数列表
     */
    private List parameterList = null;

    public DatabaseAccess() {
        //控制台提示
        //  System.out.println("---");
        System.out.println("（gai）连接数据库");
        conn = DatabaseINI.getConnection();
        parameterList = new ArrayList();
    }

    /**
     * 放置一个参数到sql预处理列表
     *
     * @param parameter 参数
     */
    public void setPreparedParameter(Object parameter) {
        parameterList.add(parameter);
    }

    /**
     * 预处理，并加入参数
     *
     * @param sql 预处理语句
     * @throws SQLException
     */
    private void prepare(String sql) throws SQLException {
        pstm = getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        int index = 1;
        for (Object parameter : parameterList) {
            pstm.setObject(index++, parameter);
        }
        printSql(sql);
    }
//准备存储过程参数

    private void prepareCall(String call) throws SQLException {
        cstm = getConn().prepareCall(call);

        int index = 1;
        for (Object parameter : parameterList) {
            cstm.setObject(index++, parameter);
        }
        printSql(call);
    }

//不清除预处理参数列表，执行select count语句 返回一个总条数
    public int executeCount(String sql) {
        int count = 0;
        try {
            this.prepare(sql);
            rs = pstm.executeQuery();
            if (rs != null && rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            LoggerManage.logger.getLogger(e.getMessage(), e);
        }
        return count;
    }
//执行查询的SQL并返回结果集

    public ResultSet executeQuery(String sql) {
        try {
            this.prepare(sql);
            parameterList.clear();
            rs = pstm.executeQuery();
        } catch (SQLException e) {
            LoggerManage.logger.getLogger(e.getMessage(), e);
        }
        return rs;
    }
//可执行插入、更新、删除的SQL

    public boolean executeUpdate(String sql) {
        boolean b = false;
        try {
            this.prepare(sql);
            parameterList.clear();
            pstm.executeUpdate();
            b = true;
        } catch (Exception e) {
            LoggerManage.logger.getLogger(e.getMessage(), e);
        }
        return b;
    }
    //专用于执行select语句，并放回数据库自动生成的主键。返回-1说明插入失败

    public Integer executeInsert(String sql) {
        Integer primarykey = -1;
        try {
            this.prepare(sql);
            pstm.execute();
            parameterList.clear();
            rs = pstm.getGeneratedKeys();
            while (rs.next()) {
                primarykey = rs.getInt(1);
            }
        } catch (Exception e) {
            LoggerManage.logger.getLogger(e.getMessage(), e);
        }
        return primarykey;
    }
//执行存储过程

    public boolean executeProc(String call) {
        boolean b = false;
        try {
            this.prepareCall(call);
            parameterList.clear();
            cstm.execute();
            b = true;
        } catch (Exception e) {
            LoggerManage.logger.getLogger(e.getMessage(), e);
        }
        return b;

    }

    public void close() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                LoggerManage.logger.getLogger(e.getMessage(), e);
            }
        }
        if (pstm != null) {
            try {
                pstm.close();
            } catch (SQLException e) {
                LoggerManage.logger.getLogger(e.getMessage(), e);
            }
        }
        if (cstm != null) {
            try {
                cstm.close();
            } catch (SQLException e) {
                LoggerManage.logger.getLogger(e.getMessage(), e);
            }
        }
        if (getConn() != null) {
            try {
                getConn().close();
            } catch (SQLException e) {
                LoggerManage.logger.getLogger(e.getMessage(), e);
            }
        }
        System.out.println("（gai）关闭数据库");
    }

    /**
     * 输出SQL语句
     *
     * @param sql
     */
    private void printSql(String sql) {
        StringBuffer s = new StringBuffer(sql);

        for (Object o : parameterList) {
            if (o.getClass().getName().equals("java.lang.String") && o != null) {
                s = s.replace(s.indexOf("?"), s.indexOf("?") + 1, "'" + o.toString() + "'");
            } else {
                s = s.replace(s.indexOf("?"), s.indexOf("?") + 1, o.toString());
            }
        }
        System.out.println("sql:" + s.toString().replaceAll("`", ""));
    }

    /**
     * @return the conn
     */
    public Connection getConn() {
        return conn;
    }
}
