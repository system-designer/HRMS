/**
 * @date 2012/2/19 15:10 添加事务处理，重复使用的方法
 * @date 2012/4/13 14:10
 * @date 2012/8/6 添加延迟加载 自动关联查询 结果集从LinkedList改成ArrayList
 */
package org.jplus.hyb.database;

import com.jplus.json.EasyUiJson;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jplus.util.FieldUtil;
import org.jplus.util.LoggerManage;
import org.jplus.util.Pagger;

/**
 * 数据库持久层框架核心类之一 <p> 此类用于给定POJO类的数据库操作 强制绑定了数据库预处理机制。
 * 需要在项目缺省包下面添加配置文件database.properties(整个项目仅此一个数据库配置)。
 * 多用于对数据库单表的插入、更新、删除、查询操作，对POJO类的依赖性很强。 可以支持数据库事务处理。 支持一个数据库连接的单次，多次利用。 </p>
 *
 * @version 3.6
 * @author hyberbin
 */
public class Hyberbin extends DatabaseAccess {

    /** 表名 */
    private String tableName;
    /** 成员变量列表 */
    private List<String> fields = null;
    /** 表的实体类 */
    private Object tablebean;
    /** 是否更新空值 */
    private boolean updateNull = false;
    /** 是否查询到了数据 */
    private boolean isLoaded = false;
    /** 为空的字段列表 */
    private List<String> nuList;
    /**是否第一次SetField*/
    private boolean firstSetField=true;

    /**
     * 根据表的实体类初始化
     *
     * @param tablebean 表的实体类
     */
    private void ini(Object tablebean) {
        if (tablebean != null) {
            try {
                this.tableName = tablebean.getClass().getSimpleName().toLowerCase();
                this.tablebean = tablebean;
                firstSetField=true;
                fields = new ArrayList<String>();
                for (Field field : tablebean.getClass().getDeclaredFields()) {
                    fields.add(field.getName());
                }
            } catch (Exception ex) {
                LoggerManage.logger.getLogger("Hyberbin初始化错误\t", ex);
            }
        }
    }

    /**
     * 构造方法,一次性操作。操作完后自动关闭数据库。 <p> Ex: <p>News news=new News();//POJO类，源自于数据库
     * <strong><p>Hyberbin hyberbin=new
     * Hyberbin(news);//构建Hyberbin,给出POJO类表示当前只对news表进行操作，而且是一次性操作。操作完后自动关闭数据库。</strong>
     *
     * @param tablebean 表的实体类
     */
    public Hyberbin(Object tablebean) {
        super(DatabaseINI.getConnection());
        ini(tablebean);
        //database = ;//数据库操作对象        
    }

    /**
     * 用自带的数据库连接进行数据库操作,用于构造可多次使用的Hyberbin <p>Ex: <p>News news=new
     * News();//POJO类，源自于数据库 <p>Connection conn =
     * DatabaseINI.getDatabase().getConn();//获得一个数据库连接 <strong><p>Hyberbin
     * hyberbin=new Hyberbin(news,conn);//用自带的数据库连接进行数据库操作</strong>
     *
     * @param tablebean 表的实体类
     * @param conn 数据库连接
     */
    public Hyberbin(Object tablebean, Connection conn) {
        super(conn);
        ini(tablebean);
        transactionBegan();
    }

    /**
     * 自动获得一个连接，获得后设置该连接可以重复使用,用于构造可多次使用的Hyberbin <p>Ex: <p>News news=new
     * News();//POJO类，源自于数据库 <p>Type type=new Type();//POJO类，源自于数据库
     * <p>news.setId(1); <p>type.setType("公司新闻"); <strong><p>Hyberbin
     * hyberbin=new
     * Hyberbin(news,true);//构建Hyberbin,给出POJO类表示当前只对news表进行操作，而且是一次性操作。true表示操作完后要重复使用不自动关闭数据库。</strong>
     * <p>Boolean b=hyberbin.dellOneByKey("id");//根据id删除一条新闻
     * <p>hyberbin.changeTable(type);//切换数据库表的操作对象（换一个表操作）。 <p>Boolean
     * b=hyberbin.insert("id");//在表中插入一条数据。传入一个字段名，表示该字段名是数据库自动生成，无需插入。
     * <p>hyberbin.reallyClose();//只有调用这个方法之后数据库连接才真正关闭。
     *
     * @param tablebean 表的实体类
     * @param repeat 是否多次使用
     */
    public Hyberbin(Object tablebean, boolean repeat) {
        this(tablebean);
        transactionBegan();
    }

    /**
     * 设置是否自动清除预处理参数
     * @param auto
     */
    public void setAutoClearPrepare(boolean auto) {
        autoClearPrepare = auto;
    }

    /**
     * 设置操作的表名. <p>如果没有设置将会默认采用实体名
     *
     * @param tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * 对一个表进行操作或者进行过一次数据库操作后换一个表进行操作
     * <p>用此方法的前提是数据库连接没有自动关闭.即构造方法必需是Hyberbin(Object tablebean, Connection
     * conn)或者是Hyberbin(Object tablebean, boolean repeat)。 <p>Ex: <p>News
     * news=new News();//POJO类，源自于数据库 <p>Type type=new Type();//POJO类，源自于数据库
     * <p>news.setId(1); <p>type.setType("公司新闻"); <p>Hyberbin hyberbin=new
     * <p>Hyberbin(news,true);//构建Hyberbin,给出POJO类表示当前只对news表进行操作，而且是一次性操作。true表示操作完后要重复使用不自动关闭数据库。
     * <p>Boolean b=hyberbin.dellOneByKey("id");//根据id删除一条新闻
     * <p><strong>hyberbin.changeTable(type);//切换数据库表的操作对象（换一个表操作）。</strong>
     * <p>Boolean
     * b=hyberbin.insert("id");//在表中插入一条数据。传入一个字段名，表示该字段名是数据库自动生成，无需插入。
     * <p>hyberbin.reallyClose();//只有调用这个方法之后数据库连接才真正关闭。
     *
     * @param tablebean 表的实体类
     * @return 自身
     */
    public Hyberbin changeTable(Object tablebean) {
        ini(tablebean);
        clearParmeter();
        return this;
    }

    /**
     * 移去一个成员变量 不需要更新或者读取的字段将不会出现在数据库操作当中
     * <p>此方法对所有的查询、修改、添加都能起作用。用于在有很多字段情况下去除少量不需要操作的字段，提高数据库效率。
     * <p>些方法返回自身对象,可以链式编程。hyberbin.removeField("newstype").removeField("id");
     * <p>Ex: <p>News news=new News();//POJO类，源自于数据库 <p>Hyberbin hyberbin=new
     * Hyberbin(news);//构建Hyberbin,给出POJO类表示当前只对news表进行操作，而且是一次性操作。操作完后自动关闭数据库。
     * <strong><p>hyberbin.removeField("newstype");</strong>
     * <p>如果还需要移除查其它字段可以继续removeField("id")等等。 <p>hyberbin.showAll();
     *
     * @param fieldName 要移去的成员变量
     * @return 自身
     */
    public Hyberbin removeField(String fieldName) {
        if (fieldName == null || fieldName.trim().equals("")) {
            return this;
        }
        fields.remove(fieldName);
        return this;
    }

    /**
     * 设置一个成员变量 只有指定字段才出现在数据库操作当中
     * <p>此方法对所有的查询、修改、添加都能起作用。用于在有很多字段情况下只对少量字段进行操作，提高数据库效率。
     * <p>些方法返回自身对象,可以链式编程。hyberbin.setField("newstype").setField("id"); <p>Ex:
     * <p>News news=new News();//POJO类，源自于数据库 <p>Hyberbin hyberbin=new
     * Hyberbin(news);//构建Hyberbin,给出POJO类表示当前只对news表进行操作，而且是一次性操作。操作完后自动关闭数据库。
     * <strong><p>hyberbin.setField("newstype");</strong>
     * <p>如果还需要查其它字段可以继续setField("id")等等。
     * <p>hyberbin.showAll();//在这个查询到的集合中将只会查到“newstype”这个字段的信息。
     *
     * @param fieldName 要查询的成员变量
     * @return 自身
     */
    public Hyberbin setField(String fieldName) {
        if(firstSetField){
            fields=null;
            firstSetField=false;
        }
        if (fields == null) {
            fields = new ArrayList<String>();
        }
        fields.add(fieldName);
        return this;
    }

    /**
     * 查询的时候获得所有字段名
     *
     * @return
     */
    private String getFieldList() {
        if (fields != null) {
            StringBuilder fieldlist = new StringBuilder();
            for (String field : fields) {
                fieldlist.append(",").append(field);
            }
            return fieldlist.substring(1);
        } else {
            return "";
        }
    }

    /**
     * 从查询结果中取得数据存入表的实体类
     *
     * @param table 表的实体类
     * @param rs 查询结果
     */
    private Object loadData(Object table, ResultSet rs) throws Exception {
        ResultSetMetaData metaData = rs.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            Field field = table.getClass().getDeclaredField(metaData.getColumnName(i).toLowerCase());
            loadDataToPojo(table, field, field.getName(), rs);
        }
        return table;
    }

    /**
     * 将数据库中取到的值存入POJO类
     *
     * @param table POJO类
     * @param field POJO类的字段
     * @param rs 数据查询集合
     */
    private void loadDataToPojo(Object table, Field field, String column, ResultSet rs) {
        Object o = null;
        try {
            Method method = table.getClass().getMethod(FieldUtil.set(field.getName()), field.getType());//取得set方法
            o = resultSetGet(rs, field, column);
            if (o != null) {
                method.invoke(table, o); //调用实体类的setXXX方法
            }
        } catch (Exception ex) {
            LoggerManage.logger.getLogger("表：" + table.getClass().getSimpleName() + " 字段：" + field.getName() + "类型不匹配！" + o.getClass().getName(), ex);
        }
    }

    private Object resultSetGet(ResultSet rs, Field field, String column) throws Exception {
        String methodName = DatabaseINI.mapping_props.getProperty(field.getType().getSimpleName());
        Method method = ResultSet.class.getMethod(methodName == null ? "getObject" : methodName, String.class);//取得set方法
        return method.invoke(rs, column);
    }

    /**
     * 自动生成SQL语句
     *
     * @return
     */
    private GetSql getSql() {
        GetSql gs = new GetSql();
        for (String field : fields) {
            Object value = FieldUtil.getFieldValue(tablebean, field);
            if (value != null) {
                setParmeter(value);
                gs.add(field, "?", "");//将有值的进行预处理
                if (nuList != null && nuList.size() > 0) {
                    nuList.remove(field);
                }
            } else if (value == null && updateNull) {
                gs.add(field, "null", "");//生成部分sql语句
            }
        }
        if (nuList != null && nuList.size() > 0) {
            for (String nullF : nuList) {
                if (fields.contains(nullF)) {
                    gs.add(nullF, "null", "");//生成部分sql语句
                }
            }
        }
        nuList = null;
        return gs;
    }

    /**
     * 设置是否更新NULL <p>一般情况下系统会默认直接跳过值为空的字段，以提高数据库效率。 <p>如果要清空该字段的信息就应该调用这个方法。
     * <p>此方法对数据库的插入、修改起作用。 <p>Ex:
     * <strong><p>hyberbin.setUpdateNull(true);</strong>
     *
     * @param b true或者false
     */
    public void setUpdateNull(boolean b) {
        updateNull = b;
    }

    /**
     * 在预处理中加入新的参数 <p>此方法可以对数据库的增、删、改、查起作用。
     * <p>些方法返回自身对象,可以链式编程。hyberbin.addParmeter("%"+other+"%").addParmeter("1");
     * <p>为了增加数据库操作的安全性，建议所有自构的sql语句均要调用本方法。 <p>加入参数时所有参数均不需要单引号。
     * <p>加入时应该按照参数出现的位置按顺序调用。 <p>like语句应该这样添加addParmeter("%"+other+"%"); <p>Ex:
     * <p>News news=new News();//POJO类，源自于数据库 <p>Hyberbin hyberbin=new
     * Hyberbin(news);//构建Hyberbin,给出POJO类表示当前只对news表进行操作，而且是一次性操作。操作完后自动关闭数据库。
     * <p>String other="hyb";
     * <strong><p>hyberbin.addParmeter("%"+other+"%");</strong>
     * <strong><p>hyberbin.addParmeter("1");</strong>
     * <p>hyberbin.showOne("select * from news where content like ? and
     * newstype=?");
     *
     * @param parmeter 参数
     * @return 自身对象
     */
    public Hyberbin addParmeter(Object parmeter) {
        setParmeter(parmeter);
        return this;
    }

    /**
     * 数据库插入 <p>此方法用于对数据库单表的插入操作，默认情况下不插入字段值为空的字段。 <p>此方法已经默认采用预处理技术。
     * <p>传入的参数是字段名而不是字段值。 <p>Ex: <p>News news=new News();//POJO类，源自于数据库
     * <p>Hyberbin hyberbin=new
     * Hyberbin(news);//构建Hyberbin,给出POJO类表示当前只对news表进行操作，而且是一次性操作。操作完后自动关闭数据库。
     * <p>news.setNewsType(2); <p>news.setContent("这是一则新闻！"); <strong><p>boolean
     * b=hyberbin.insert("id");//参数传入“id”表示id数据库会自动生成。如果不是自动生成那么可以传入""或者null。</strong>
     *
     * @param primarkey 插入的主键，可以为空
     * @return 是否成功
     */
    public boolean insert(String primarkey) {
        removeField(primarkey);
        GetSql gs = getSql();
        String sql = gs.getInsert(tableName);//生成sql语句
        return update(sql);
    }

    /**
     * 数据库更新 <p>此方法用于对数据库单表的修改操作，默认情况下不修改字段值为空的字段。 <p>此方法已经默认采用预处理技术。
     * <p>传入的参数是字段名而不是字段值。 <p>Ex: <p>News news=new News();//POJO类，源自于数据库
     * <p>Hyberbin hyberbin=new
     * Hyberbin(news);//构建Hyberbin,给出POJO类表示当前只对news表进行操作，而且是一次性操作。操作完后自动关闭数据库。
     * <p>news.setId(1); <p>news.setNewsType(2); <p>news.setContent("这是一则新闻！");
     * <strong><p>boolean
     * b=hyberbin.updataByKey("id");//参数传入“id”表示根据id的值去修改，即修改id=1的那条记录。</strong>
     *
     * @param key 更新条件字段
     * @return 是否成功
     */
    public boolean updateByKey(String key) {
        removeField(key);
        GetSql gs = getSql();//自动生成sql语句
        Object PKvalue = FieldUtil.getFieldValue(tablebean, key);
        setParmeter(PKvalue);
        gs.add(quote + key + quote, "?", "where");//生成sql的where部分，quote即为引号
        String sql = gs.getUpdate(tableName);//生成sql语句
        boolean b = update(sql);
        return b;
    }

    /**
     * 给指定字段指定条件的语句自增 <p>此方法用于给指定字段指定条件的语句自动加1操作，例如用户看一则新闻后点击量自动加1;
     * <p>此方法默认不会使用sql预处理技术。 <p>参数where中应该包含“where”关键字。
     * <p>建议用户自己使用预处理技术（addParmeter("……")）以提高安全性。 <p>Ex: <p>Hyberbin
     * hyberbin=new
     * Hyberbin(news);//构建Hyberbin,给出POJO类表示当前只对news表进行操作，操作完后自动关闭数据库。
     * <p>hyberbin.addParmeter(1);
     * <strong><p>hyberbin.autoUp("clickTimes","where id=?");</strong>
     *
     * @param field 要自增的字段
     * @param where 条件 含有“where”
     * @return 是否成功
     */
    public boolean autoUp(String field, String where) {
        String sql = new StringBuilder("update  ").append(quote).append(tableName).append(quote).append(" set ").append(quote).append(field).append(quote).append("=").append(quote).append(field).append(quote).append("+1 ").append(where).toString();
        return update(sql);
    }

    /**
     * 数据库删除 <p>此方法默认不会使用sql预处理技术。 <p>参数where中应该包含“where”关键字。
     * <p>建议用户自己使用预处理技术（addParmeter("……")）以提高安全性。 <p>Ex: <p>Hyberbin
     * hyberbin=new
     * Hyberbin(news);//构建Hyberbin,给出POJO类表示当前只对news表进行操作，操作完后自动关闭数据库。
     * <p>hyberbin.addParmeter(1); <strong><p>hyberbin.dell("where
     * id=?");//删除id为1的新闻</strong>
     *
     * @param where 要删除的条件 含有“where”
     * @return 是否成功
     */
    public boolean dell(String where) {
        String sql = new StringBuilder("delete from ").append(quote).append(tableName).append(quote).append(" ").append(where).toString();
        return update(sql);
    }

    /**
     * 根据键值删除一条数据 <p>此方法默认使用sql预处理技术。 <p>用户只需要提供关键字段的字段名即可。注意：参数是字段名。 <p>Ex:
     * <p>Hyberbin hyberbin=new
     * Hyberbin(news);//构建Hyberbin,给出POJO类表示当前只对news表进行操作，操作完后自动关闭数据库。
     * <p>hyberbin.addParmeter(1);
     * <strong><p>hyberbin.dellOneByKey("id");//删除id为1的新闻</strong>
     *
     * @param key 键值
     * @return 是否成功
     */
    public boolean dellOneByKey(String key) {
        String sql = new StringBuilder("delete from ").append(quote).append(tableName).append(quote).append(" where ").append(key).append(" =?").toString();
        Object PKvalue = FieldUtil.getFieldValue(tablebean, key);
        setParmeter(PKvalue);
        return update(sql);
    }

    /**
     * 根据指定SQL语句查询一条数据 <p>此方法用于对数据库单表的查询操作，用于用户自己提供sql语句。
     * <p>此方法返回的是Object对象，用户需要自行强制类型转换。 <p>此方法默认不会使用sql预处理技术。
     * <p>建议用户自己使用预处理技术（addParmeter("……")）以提高安全性。 <p>Ex: <p>Hyberbin
     * hyberbin=new
     * Hyberbin(news);//构建Hyberbin,给出POJO类表示当前只对news表进行操作，操作完后自动关闭数据库。
     * <p>hyberbin.addParmeter(2); <strong><p>hyberbin.showOne("select * from
     * news where id=?"); //查询id为2的新闻</strong>
     *
     * @param <T> 所有POJO类
     * @param sql 指定SQL语句
     * @return 查询对象
     */
    public <T> T showOne(String sql) {
        sql += " limit 0,1";
        if (super.isFilter()) {
            super.getSql(sql);
            sql = super.getSelectSQL();
        }
        ResultSet rs = query(sql);//执行查询
        try {
            if (rs != null && rs.next()) {
                loadData(tablebean, rs);
                isLoaded = true;
            } else {
                isLoaded = false;
                tablebean = tablebean.getClass().newInstance();//创建实体
            }
        } catch (Exception ex) {
            LoggerManage.logger.getLogger("Hyberbin查询出错，表:" + tableName + "\nsql:" + sql + "\n", ex);
        } finally {
            close();
        }
        return (T) tablebean;
    }

    /**
     * 通过指定字段查询一条数据 <p>此方法用于对数据库单表的查询操作，用于只提供关键的字段名信息。
     * <p>此方法返回的是Object对象，用户需要自行强制类型转换。 <p>此方法默认使用sql预处理技术。 <p>Ex:
     * <p>news.setId(2); <p>Hyberbin hyberbin=new
     * Hyberbin(news);//构建Hyberbin,给出POJO类表示当前只对news表进行操作，操作完后自动关闭数据库。
     * <strong><p>hyberbin.showOnebyKey("id"); //查询id为2的新闻</strong>
     * 如果有缓存会自动从缓存中取出对象并克隆到tablebean 如果在存入缓存时用了setField或者removeField方法将导致缓存中数据不全
     *
     * @param <T> 所有POJO类
     * @param key 指定字段
     * @return 查询对象
     */
    public <T> T showOnebyKey(String key) {
        Object value = FieldUtil.getFieldValue(tablebean, key);
        setParmeter(value);
        String sql = new StringBuilder("select ").append(getFieldList()).append(" from ").append(quote).append(tableName).append(quote).append(" where ").append(key).append("=?").toString();//sql语句
        showOne(sql);
        return (T) tablebean;
    }

    /**
     * 数据库批量查询 <p>此方法查询一个表的所有记录，不常用。 <p>此方法返回的是 List，用户需要自行强制类型转换。 <p>Ex:
     * <p>Hyberbin hyberbin=new Hyberbin(news);
     * <strong><p>hyberbin.showAll();</strong>
     *
     * @return 查询结果集合
     */
    public List showAll() {
        String sql = new StringBuilder("select ").append(getFieldList()).append(" from ").append(tableName).toString();//sql语句
        return showList(sql);
    }

    /**
     * 查询数据库中符合条件的记录数 <p>此方法用于查询符合用户条件的数据记录条数。 <p>参数where中应该包含“where”关键字。
     * <p>此方法默认不使用sql预处理技术。 <p>建议用户自己使用预处理技术（addParmeter("……")）以提高安全性。 <p>Ex:
     * <p>Hyberbin hyberbin=new
     * Hyberbin(news);//构建Hyberbin,给出POJO类表示当前只对news表进行操作，操作完后自动关闭数据库。
     * <p>hyberbin.addParmeter(2); <strong><p>hyberbin.getCount("where
     * newstype=?"); //查询newstype为2的新闻有多少条</strong>
     *
     * @param where 查询条件 含有“where”
     * @return 个数
     */
    public int getCount(String sql) {
        int count = 0;
        if (sql.trim().startsWith("where")) {
            sql = new StringBuilder("select count(*) as count from ").append(quote).append(tableName).append(quote).append(" ").append(sql).toString();
        } else if (sql.trim().equals("")) {
            sql = new StringBuilder("select count(*) as count from ").append(quote).append(tableName).append(quote).toString();
        } else {
            sql = new StringBuilder("select count(*) as count from (").append(sql).toString() + ") hyberbin";
        }
        ResultSet rs = query(sql);
        try {
            if (rs != null && rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            LoggerManage.logger.getLogger("Hyberbin查询总记录数出错，表:" + tableName + "\nsql:" + sql + "\n", ex);
        }
        close();
        return count;
    }

    /**
     * mysql分页查询 <p>注意：此方法只适用于mysql数据库 <p>此方法用于分页查询用户给定的条件下的数据集合，提供了分页查询的接口。
     * <p>参数where中应该包含“where”关键字。 <p>此方法默认不使用sql预处理技术。
     * <p>建议用户自己使用预处理技术（addParmeter("……")）以提高安全性。 <p>Ex: <p>Hyberbin
     * hyberbin=new
     * Hyberbin(news);//构建Hyberbin,给出POJO类表示当前只对news表进行操作，操作完后自动关闭数据库。
     * <p>hyberbin.addParmeter(2); <strong><p>hyberbin.showByMySqlPage("where
     * newstype=?",0,10,true);</strong>
     * //查询newstype为2的新闻记录，从0开始取10条记录，true表示返回集合的第一条数据为符合条件的总记录数。
     *
     * @param where 查询条件 含有“where”
     * @param top 起始位置
     * @param size 查询量
     * @param total 是否查询符合条件的总条数
     * @return 查询集合
     */
    public List showByMySqlPage(String where, int top, int size, boolean total) {
        if (autoClearPrepare) {
            delayClear = true;
        }
        if (total) {//如果之前是自动关闭
            delayClose = true;
        }
        StringBuilder sql = new StringBuilder("select ").append(getFieldList()).append("  from ").append(quote).append(tableName).append(quote).append(" ").append(where);
        if (super.isFilter()) {
            super.getSql(sql.toString());
            sql = new StringBuilder(super.getSelectSQL());
        }
        List list = showList(sql.toString() + " limit " + top + "," + size);
        return list;
    }

    /**
     * mysql分页查询 <p>注意：此方法只适用于mysql数据库 <p>此方法用于分页查询用户给定的条件下的数据集合，提供了分页查询的接口。
     * <p>参数where中应该包含“where”关键字。 <p>此方法默认不使用sql预处理技术。
     * <p>建议用户自己使用预处理技术（addParmeter("……")）以提高安全性。 <p>Ex: <p>Hyberbin
     * hyberbin=new
     * Hyberbin(news);//构建Hyberbin,给出POJO类表示当前只对news表进行操作，操作完后自动关闭数据库。
     * <p>hyberbin.addParmeter(2); <strong><p>hyberbin.showByMySqlPage("where
     * newstype=?",0,10,true);</strong>
     * //查询newstype为2的新闻记录，从0开始取10条记录，true表示返回集合的第一条数据为符合条件的总记录数。
     *
     * @param where 查询条件 含有“where”
     * @param top 起始位置
     * @param size 查询量
     * @param total 是否查询符合条件的总条数
     * @return 查询集合
     */
    public List showByMySqlPage(String where, Pagger pagger) {
        if (autoClearPrepare) {
            delayClear = true;
        }
        if (autoClose) {//如果之前是自动关闭
            delayClose = true;
        }
        StringBuilder sql = new StringBuilder("select ").append(getFieldList()).append("  from ").append(quote).append(tableName).append(quote).append(" ").append(where);
        if (super.isFilter()) {
            super.getSql(sql.toString());
            sql = new StringBuilder(super.getSelectSQL());
        }
        List list = showList(sql.toString() + " limit " + pagger.getTop() + "," + pagger.getSize());
        pagger.setItems(getCount(sql.toString()));
        return list;
    }

    public List showByMySqlPage(String where, EasyUiJson pagger) {
        if (autoClearPrepare) {
            delayClear = true;
        }
        if (autoClose) {//如果之前是自动关闭
            delayClose = true;
        }
        StringBuilder sql = new StringBuilder("select ").append(getFieldList()).append("  from ").append(quote).append(tableName).append(quote).append(" ").append(where);
        if (super.isFilter()) {
            super.getSql(sql.toString());
            sql = new StringBuilder(super.getSelectSQL());
        }
        List list = showList(sql.toString() + " limit " + pagger.getTop() + "," + pagger.getRows());
        pagger.setTotal(getCount(sql.toString()));
        return list;
    }

    /**
     * 数据库的批量查询 <p>此方法用于对数据库单表的查询操作，用于用户自己提供sql语句。 <p>由于是自己构造sql语句所以适用于所有数据库。
     * <p>此方法默认不会使用sql预处理技术。 <p>建议用户自己使用预处理技术（addParmeter("……")）以提高安全性。 <p>Ex:
     * <p>Hyberbin hyberbin=new
     * Hyberbin(news);//构建Hyberbin,给出POJO类表示当前只对news表进行操作，而且是一次性操作。操作完后自动关闭数据库。
     * <p>hyberbin.addParmeter(2); <strong><p>hyberbin.showList("select * from
     * news where newstype=?");</strong>
     *
     * @param sql
     * @return 查询结果集合
     */
    public List showList(String sql) {
        List list = new ArrayList();
        if (super.isFilter()) {
            super.getSql(sql);
            sql = super.getSelectSQL();
        }
        ResultSet rs = query(sql);//执行查询
        try {
            while (rs != null && rs.next()) {
                list.add(loadData(tablebean.getClass().newInstance(), rs));
            }
        } catch (Exception ex) {
            LoggerManage.logger.getLogger("Hyberbin查询列表出错，表:" + tableName + "\nsql:" + sql + "\n", ex);
        } finally {
            close();
        }
        isLoaded = list.size() > 0 ? true : false;
        return list;
    }

    /**
     * 设置空值列表 更新的时候会自动将下列值设置为空.
     * 用于只更新从表单获得的空值 原数据库的空值不动
     * @param nuList
     */
    public void setNuList(List<String> nuList) {
        this.nuList = nuList;
        updateNull = false;
    }

    /**
     * 当前Hyberbin是否真正查询到了数据
     *
     * @return
     */
    public boolean isLoaded() {
        return isLoaded;
    }
}
