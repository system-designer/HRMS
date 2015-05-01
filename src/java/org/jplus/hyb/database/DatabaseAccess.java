package org.jplus.hyb.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.jplus.util.LoggerManage;

/**
 * 数据库基本操作工具
 * @version 2.0
 * @author hyberbin
 */
public class DatabaseAccess extends SqlFilter {

    /** 数据库连接对象 */
    private Connection conn = null;
    /** Statement对象，用于执行带参数的SQL语句 */
    private PreparedStatement stm = null;
    /** 数据集 */
    private ResultSet rs = null;
    /** 预处理参数列表 */
    public List parmeterList = null;
    /** 是否自动关闭 */
    public boolean autoClose = true;
    /** 是否自动清除预处理参数 */
    public boolean autoClearPrepare = true;
    /** 数据库是否关闭 */
    public boolean isClosed = false;
    /** 获取用于引用 SQL 标识符的字符串 */
    public String quote = "";
    public boolean isTransaction = false;//是否带有事务
    public boolean isAlive = true;//当前对象是否可用,当前数据库连接是否可用
    private boolean isTransactionSucces = false;//事务是否执行成功
    public boolean isCommited = false;//事务是否提交过
    private String lockedTable;

    /**
     * 自带的连接创建数据库操作对象
     * @param conn 连接
     */
    public DatabaseAccess(Connection conn) {
        this.conn = conn;
    }

    /**
     * 放置一个参数到sql预处理列表
     * @param parameter 参数
     */
    public void setParmeter(Object parameter) {
        if (isAlive) {
            if (parmeterList == null) {
                parmeterList = new ArrayList();
            }
            parmeterList.add(parameter);
        } else {
            LoggerManage.logger.getLogger("DatabaseAccess由于事务已经提交参数不能添加！\nparameter:" + parameter + "\n", null);
        }
    }
    /** 是否设定下一次不清除（用于分页的时候查询主表和查询总条数共用） */
    public boolean delayClear = false;

    /**
     * 清空预处理参数列表
     * @return
     */
    public void clearParmeter() {
        if (!delayClear) {
            parmeterList = null;
        } else {
            delayClear = !delayClear;
        }
    }

    /**
     * 创建预处理对象
     * @param sql 预处理语句
     * @throws SQLException
     */
    private void createStatement(String sql) throws SQLException {
        stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        if (DatabaseINI.isSqlout) {
            sqlOut(sql, false);
        }
        int index = 1;
        if (parmeterList != null) {
            for (Object parmeter : parmeterList) {
                stm.setObject(index++, parmeter);
            }
        }
    }

    /**
     * 获取连接对象
     * @return 连接对象
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * 获得Statement
     * @return
     */
    public PreparedStatement getStatement() {
        return stm;
    }

    /**
     * 数据库更新操作
     * @param sql SQL语句
     * @return 是否成功
     */
    public boolean update(String sql) {
        boolean b = false;
        if (isAlive) {
            try {
                createStatement(sql);
                stm.executeUpdate();
                b = true;
            } catch (SQLException ex) {
                if (isTransaction) {//如果带有事务
                    this.transactionEnd(false);
                }
                LoggerManage.logger.getLogger("DatabaseAccess数据库修改出错\nsql:" + sqlOut(sql, true) + "\n", ex);
            } finally {
                close();
            }
            if (autoClearPrepare) {
                clearParmeter();
            }
        } else {
            LoggerManage.logger.getLogger("DatabaseAccess由于事务已经提交以下语句未能执行！\nsql:" + sqlOut(sql, true) + "\n", null);
        }
        return b;
    }

    /**
     * 数据库查询操作
     * @param sql SQL语句
     * @return 查询结果
     */
    public ResultSet query(String sql) {
        if (isAlive) {
            try {
                createStatement(sql);
                rs = stm.executeQuery();
            } catch (SQLException ex) {
                if (isTransaction) {//如果带有事务
                    this.transactionEnd(false);
                }
                LoggerManage.logger.getLogger("DatabaseAccess数据库查询出错\nsql:" + sqlOut(sql, true) + "\n", ex);
            }
            if (autoClearPrepare) {
                clearParmeter();
            }
        } else {
            LoggerManage.logger.getLogger("DatabaseAccess由于事务已经提交以下语句未能执行！\nsql:" + sqlOut(sql, true) + "\n", null);
        }
        return rs;
    }

    /**
     * 用于插入并返回主键
     * @param sql
     * @return
     */
    public Integer executeInsert(String sql) {
        update(sql);
        return getGeneratedKeys();

    }

    public Integer getGeneratedKeys() {
        Integer primarykey = -1;
        try {
            rs = stm.getGeneratedKeys();
            if (rs.next()) {
                primarykey = rs.getInt(1);
            }
        } catch (Exception e) {
            if (isTransaction) {//如果带有事务
                this.transactionEnd(false);
            }
            LoggerManage.logger.getLogger("DatabaseAccess返回生成的主键出错！", null);
        }
        return primarykey;
    }

    /**
     * 判断查询结果是否为空
     * @return 是否为空
     */
    public boolean isResultSetNull() {
        boolean b = false;
        try {
            if (rs.next()) {
                b = true;
            }
        } catch (SQLException ex) {
            LoggerManage.logger.getLogger("DatabaseAccess数据集合判空出错\t", ex);
        }
        return b;
    }

    /**
     * 开启事务 <p>此方法用于开启数据库事务处理机制。 <p>用此方法不会自动关闭数据库连接。 <p>主要用于数据库的修改、删除、添加。
     * <p>用于在一定条件下可以还原之前的操作 <p>Ex: <p>Hyberbin hyberbin=new Hyberbin(news);
     * <strong><p>transactionBegan();</strong> <p>hyberbin.insert(news);
     * <p>transactionEnd(false);//执行这个语句之上的操作就会无效
     */
    public void transactionBegan() {
        try {
            if (!conn.isClosed() && !isTransaction) {
                if (DatabaseINI.isSqlout) {
                    LoggerManage.logger.debug("开户事务");
                }
                conn.setAutoCommit(false);
                autoClose = false;
            }
        } catch (SQLException ex) {
            LoggerManage.logger.getLogger("Hyberbin开启事务出错\t", ex);
        }
        isTransaction = true;
    }

    /**
     * 事务完毕 <p>此方法用于完成数据库事务处理机制。 <p>用此方法不会自动关闭数据库连接。 <p>主要用于数据库的修改、删除、添加。
     * <p>用于在一定条件下可以还原或提交之前的操作 <p>Ex: <p>Hyberbin hyberbin=new Hyberbin(news);
     * <p>transactionBegan(); <p>hyberbin.insert(news);
     * <strong><p>transactionEnd(false);//执行这个语句之上的操作就会无效</strong>
     * <p>如果要提交操作就执行transactionEnd(true);
     *
     * @param success 是否提交
     */
    public void transactionEnd(boolean success) {
        if (this.lockedTable != null) {//如果有锁表先解锁
            this.unlockTable();
        }
        try {
            if (!isCommited && !conn.isClosed()&&isTransaction) {
                isCommited = true;
                if (DatabaseINI.isSqlout) {
                    LoggerManage.logger.debug(success ? "提交" : "回滚" + "事务");
                }
                if (success) {
                    conn.commit();
                    isTransactionSucces = true;
                } else {
                    conn.rollback();
                }
                reallyClose();
            }
        } catch (SQLException ex) {
            LoggerManage.logger.getLogger("Hyberbin" + (success ? "提交" : "回滚" + "事务出错\t"), ex);
        }
    }

    /**
     * 自动提交事务 并返回事务是否执行成功
     * @return
     */
    public boolean transactionEnd() {
        if (isAlive && !isCommited) {
            transactionEnd(true);
        }
        return isTransactionSucces;
    }

    /**
     * 返回事务是否成功
     * @return
     */
    public boolean isTransactionSucces() {
        return isTransactionSucces;
    }
    /** 是否设定下一次不清除（用于分页的时候查询主表和查询总条数共用） */
    public boolean delayClose = false;

    /**
     * 关闭数据库连接释放资源
     */
    public void close() {
        if (!delayClose && isAlive) {
            if (!autoClose || isClosed) {//如果非自动关闭就直接返回
                return;
            }
            if (isTransaction && !isCommited) {//如果有事务就先提交事务
                transactionEnd();
            }
            if (!isAlive) {
                return;
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (Exception ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (DatabaseINI.isSqlout) {
                LoggerManage.logger.debug("DatabaseAccess关闭数据库连接");
            }
            isAlive = false;
            isClosed = true;
        } else {
            delayClose = !delayClose;
        }
    }

    /**
     * 真正地关闭数据库连接
     */
    public void reallyClose() {
        autoClose = true;
        close();
    }

    /**
     * 输出SQL语句
     * @param sql
     */
    private String sqlOut(String sql, boolean out) {
        try {
            if ((out || DatabaseINI.isSqlout) && parmeterList != null) {
                for (Object o : parmeterList) {
                    sql = sql.replaceFirst("[?]", o + "");
                }
            }
            LoggerManage.logger.debug("参考sql语句(各参数的引号已被忽略)：" + sql);
        } catch (Exception e) {
            LoggerManage.logger.getLogger("在运行正则表达式时发生错误（不严重）：" + sql, e);
        }
        return sql;
    }

    /**
     * 锁表
     * @param tableName 表名
     */
    public void lockTable(String tableName) {
        lockedTable = tableName;
        update("lock table " + lockedTable + " write");
    }

    /**
     * 解锁
     */
    public void unlockTable() {
        if (lockedTable != null) {
            lockedTable = null;
            update("unlock table");
        }
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
