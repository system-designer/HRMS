package org.jplus.hyb.database;

import com.jplus.json.EasyUiJson;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jplus.util.LoggerManage;
import org.jplus.util.Pagger;

/**
 * 数据库持久层框架核心类之一
 * <p>
 * 此类用于不给定POJO类的数据库查询，所有查询到的信息只与查询结果集的信息有关。 需要与EasyData类配合使用。
 * 均没有绑定数据库预处理机制，需要用户自己调用addParmeter(……)方法。
 * 需要在项目缺省包下面添加配置文件properties(整个项目仅此一个数据库配置).
 * 多用于对数据库单表或者多表的查询操作，不对POJO类产生依赖性。 支持一个数据库连接的单次，多次利用。
 * </p>
 *
 * @version 3.6
 * @author hyberbin
 */
public class SoEasy extends DatabaseAccess {

    /** 结果集的字段名 */
    private List columnName;
    /** 结果集列表 */
    private List<EasyData> dataList;

    /**
     * 空构造方法，适用于一次查询，操作完后自动关闭数据库。
     * <p> Ex:
     * <strong> <p>SoEasy soeasy=new SoEasy();</strong>
     */
    public SoEasy() {
        super(DatabaseINI.getConnection());//数据库操作对象
    }

    /**
     * 带一个参数的构造方法，适用于一个连接做多次操作
     * <p> Ex:
     * <p>Connection conn = DatabaseINI.getDatabase().getConn();//获得一个数据库连接
     * <strong><p>SoEasy soeasy=new SoEasy(conn);//用自带的数据库连接进行数据库操作</strong>
     *
     * @param conn 数据库连接对象
     */
    public SoEasy(Connection conn) {
        super(conn);
        transactionBegan();
    }

    /**
     * 一个连接可以多次操作的构造方法
     * <p> Ex:
     * <strong><p>SoEasy soeasy=new SoEasy(true);//可多次使用数据库连接</strong>
     *
     * @param repeat 是否可以重复操作
     */
    public SoEasy(boolean repeat) {
        super(DatabaseINI.getConnection());
        if (repeat) {
            transactionBegan();
        }
    }

    /**
     * 设置字段信息
     * <p>设置字段名信息方便最后取数据，一般情况下可以自动获取，所以不需要调用。（此方法不常用）
     * <p>Ex:
     * <p>String[] column=new String[]{"id","content","type"};
     * <p>SoEasy soeasy=new SoEasy();
     * <p>soeasy.setColumn(column);
     *
     * @param columnName 字段信息
     */
    public void setColumn(String[] columnName) {
        this.columnName = Arrays.asList(columnName);
    }

    /**
     * 在预处理中加入新的参数
     * <p>些方法返回自身对象,可以链式编程。soEasy.addParmeter("%"+other+"%").soEasy("1");
     * <p>为了增加数据库操作的安全性，建议所有自构的sql语句均要调用本方法。
     * <p>加入参数时所有参数均不需要单引号。
     * <p>加入时应该按照参数出现的位置按顺序调用。
     * <p>like语句应该这样添加addParmeter("%"+other+"%");
     * <p>Ex:
     * <p>SoEasy soEasy=new SoEasy();//构建SoEasy,而且是一次性操作。操作完后自动关闭数据库。
     * <p>String other="hyb";
     * <strong><p>soEasy.addParmeter("%"+other+"%");</strong>
     * <strong><p>soEasy.addParmeter("1");</strong>
     * <p>soEasy.showOne("select * from news where content like ? and
     * newstype=?");
     *
     * @param parmeter 参数
     * @return 自身对象
     */
    public SoEasy addParmeter(Object parmeter) {
        setParmeter(parmeter);
        return this;
    }

    /**
     * 根据指定SQL语句查询一条数据
     * <p>此方法用于对数据库单表的查询操作，用于用户自己提供sql语句。
     * <p>此方法默认不会使用sql预处理技术。
     * <p>建议用户自己使用预处理技术（addParmeter("……")）以提高安全性。
     * <p>Ex:
     * <p>SoEasy soEasy=new SoEasy();//操作完后自动关闭数据库。
     * <p>soEasy.addParmeter(2);
     * <strong><p>soEasy.showOne("select * from news where id=?");
     * //查询id为2的新闻</strong>
     *
     * @param sql 指定SQL语句 sql语句可以是很复杂的多表关联查询等
     * @return EasyData
     */
    public EasyData showOne(String sql) {
        if (super.isFilter()) {
            super.getSql(sql);
            sql = super.getSelectSQL();
        }
        List<EasyData> list = loadData(query(sql + " limit 0,1")); //执行查询
        return list.size() > 0 ? list.get(0) : null;
    }

    public Map showOneForMap(String sql) {
        if (super.isFilter()) {
            super.getSql(sql);
            sql = super.getSelectSQL();
        }
        Map map = new HashMap();
        ResultSet rs = query(sql + " limit 0,1");
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            getColumn(metaData);
            if (rs != null && rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    map.put(metaData.getColumnName(i), rs.getObject(i));
                }
            }
        } catch (SQLException ex) {
            LoggerManage.logger.getLogger("SoEasy查询出错，\nsql:" + sql + "\n", ex);
        } finally {
            close();
        }
        return map;
    }

    /**
     * 从数据库查询结果集合中取得数据
     *
     * @param rs 数据库查询结果集合
     * @return List<EasyData>
     */
    private List<EasyData> loadData(ResultSet rs) {
        dataList = new ArrayList<EasyData>();
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            getColumn(metaData);
            while (rs != null && rs.next()) {
                Object[] object = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    object[i - 1] = rs.getObject(i);
                }
                dataList.add(new EasyData(object, columnName));
            }
        } catch (SQLException ex) {
            LoggerManage.logger.getLogger("SoEasy加载数据出错", ex);
        } finally {
            close();
        }
        return dataList;
    }

    public int getCount(String sql) {
        if (super.isFilter()) {
            super.getSql(sql);
            sql = super.getCountSQL();
        }
        int count = 0;
        ResultSet rs = query(sql);
        try {
            if (rs != null && rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            LoggerManage.logger.getLogger("SoEasy查询数据总数出错\nsql:" + sql + "\n", ex);
        } finally {
            close();
        }
        return count;
    }

    /**
     * 获得查询集合
     * <p>此方法用于返回一个数据库集合
     * <p>此方法默认不会使用sql预处理技术。
     * <p>建议用户自己使用预处理技术（addParmeter("……")）以提高安全性。
     * <p>Ex:
     * <p>SoEasy soEasy=new SoEasy();//操作完后自动关闭数据库。
     * <p>soEasy.addParmeter(2);
     * <strong><p>List<EasyData> list=soEasy.getList("select * from
     * news,newstype where newtype.id=news.type and news.type=?");</strong>
     *
     * @param sql
     * @return List<EasyData>
     */
    public List<EasyData> getList(String sql) {
        if (super.isFilter()) {
            super.getSql(sql);
            sql = super.getSelectSQL();
        }
        loadData(query(sql));
        close();
        return dataList;
    }

    private void getColumn(ResultSetMetaData metaData) throws SQLException {
        if (metaData != null) {
            int columnCount = metaData.getColumnCount();
            columnName = new ArrayList();
            for (int i = 1; i <= columnCount; i++) {
                columnName.add(metaData.getColumnName(i));
            }
        }
    }

    public List getColumnName() {
        return columnName;
    }

    /**
     * 根据SQL语句查出一个map集合
     * <p>此方法用于返回一个数据库集合生成json格式数据更方便
     * <p>此方法默认不会使用sql预处理技术。
     * <p>建议用户自己使用预处理技术（addParmeter("……")）以提高安全性。
     * <p>经大数据量测试getMapList比getList占用内存多。
     * <p>Ex:
     * <p>SoEasy soEasy=new SoEasy();//操作完后自动关闭数据库。
     * <p>soEasy.addParmeter(2);
     * <strong><p>List<Map> list=soEasy.getMapList("select * from news,newstype
     * where newtype.id=news.type and news.type=?");</strong>
     *
     * @param sql
     * @return
     */
    public List<Map> getMapList(String sql) {
        if (super.isFilter()) {
            super.getSql(sql);
            sql = super.getSelectSQL();
        }
        List<Map> list = new ArrayList<Map>();
        ResultSet rs = query(sql);
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            getColumn(metaData);
            while (rs != null && rs.next()) {
                Map map = new HashMap();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(metaData.getColumnName(i), rs.getObject(i));
                }
                list.add(map);
            }
        } catch (SQLException ex) {
            LoggerManage.logger.getLogger("SoEasy查询数据出错\nsql:" + sql + "\n", ex);
        } finally {
            close();
        }
        return list;
    }

    public List<Map> getMapList(String sql, Pagger page) {
        if (autoClearPrepare) {
            delayClear = true;
        }
        if (autoClose) {//如果之前是自动关闭
            delayClose = true;
        }
        if (super.isFilter()) {
            super.getSql(sql);
            sql = super.getSelectSQL();
            page.setItems(getCount(sql));
        } else {
            page.setItems(getCount("select count(*) as count from (" + sql + ")hybcount"));
        }
        List<Map> mapList = getMapList(sql + " limit " + page.getTop() + "," + page.getSize());
        return mapList;
    }

    public List<Map> getMapList(String sql, EasyUiJson page) {
        if (autoClearPrepare) {
            delayClear = true;
        }
        if (autoClose) {//如果之前是自动关闭
            delayClose = true;
        }
        if (super.isFilter()) {
            super.getSql(sql);
            sql = super.getSelectSQL();
            page.setTotal(getCount(sql));
        } else {
            page.setTotal(getCount("select count(*) as count from (" + sql + ")hybcount"));
        }
        List<Map> mapList = getMapList(sql + " limit " + page.getTop() + "," + page.getRows());
        return mapList;
    }
}
