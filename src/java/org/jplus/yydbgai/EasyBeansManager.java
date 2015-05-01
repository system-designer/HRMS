/**
 *
 * @author 杨怿，EasyORM， 用于简单的ORM操作。修改简化并规范了hyberbin
 * 出了insert,其他都要带where 子句。
 *
 *  * 2.3 1301
 * 完成了EasyBeansManager的ORM数据库操作方法
 */
package org.jplus.yydbgai;

import com.jplus.json.EasyUiJson;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jplus.hyb.database.SqlFilter;
import org.jplus.util.LoggerManage;
import org.jplus.util.Pagger;

public class EasyBeansManager extends SqlFilter {

    /**
     * 表名
     */
    private String tableName;
    /**
     * 成员变量列表
     */
    private List<Field> fields = null;
    /**
     * 表的实体类
     */
    private Object tablebean;
    /**
     * 数据库操作对象
     */
    private DatabaseAccess dao;

    /**
     * 获取用于引用 SQL 标识符的字符串
     */
    // public static String quote = "";
    public EasyBeansManager(DatabaseAccess dao) {
        this.dao = dao;
    }

    //设置要操作的tablebean
    private void setTableBean(Object tablebean) {
        if (this.tablebean != tablebean) {
            try {
                this.tableName = tablebean.getClass().getSimpleName().toLowerCase();
                this.tablebean = tablebean;
                fields = new ArrayList<Field>();
                fields.addAll(Arrays.asList(tablebean.getClass().getDeclaredFields()));
                //   firstSetField = false;
            } catch (Exception ex) {
                 LoggerManage.logger.getLogger(ex.getMessage(),ex);
            }
        }
    }

    public void setPrepareParmeter(Object parameter) {
        dao.setPreparedParameter(parameter);
    }

//根据sql的where部分查询数据库，将rs转换成包含当前tablebean类型的ArrayList。
    public List select(Object tablebean, String where) {
        this.setTableBean(tablebean);
        String sql = "select " + getFieldList() + " from " + tableName + " " + where;
        if (super.isFilter()) {
            super.getSql(sql);
            sql = super.getSelectSQL();
        }
        List list = new ArrayList();
        ResultSet rs = dao.executeQuery(sql);//执行查询

        //将ResultSet转化为对象的List
        try {
            ResultSetMetaData metaData = rs.getMetaData();

            while (rs != null && rs.next()) {

                Object table = tablebean.getClass().newInstance();

                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    Field field = table.getClass().getDeclaredField(metaData.getColumnName(i).toLowerCase());

                    try {
                        Method method = table.getClass().getMethod(set(field.getName()), field.getType());//取得set方法
                        method.invoke(table, rs.getObject(field.getName()));//调用实体类的setXXX方法
                    } catch (Exception ex) {
                         LoggerManage.logger.getLogger("表：" + table.getClass().getSimpleName() + " 字段：" + field.getName() + "类型不匹配！" + this.getClass().getName() + ex,ex);
                    }

                }
                //此处可以把table放入list
                list.add(table);
            }
        } catch (Exception ex) {
            LoggerManage.logger.getLogger("查询出错，表:" + tableName + "\nsql:" + sql + "\n" + EasyBeansManager.class.getName() + "\n" + ex,ex);
        } finally {
        }
        return list;
    }

    public List select(Object tablebean, String where, Pagger page) {
        String sql = "select count(*) as count from " + tableName + " " + where;
        page.setItems(dao.executeCount(sql));
        return select(tablebean, where+" limit "+page.getTop()+","+page.getSize());
    }
    
    public List select(Object tablebean, String where,EasyUiJson page) {
        String sql = "select count(*) as count from " + tableName + " " + where;
        page.setTotal(dao.executeCount(sql));
        return select(tablebean, where+" limit "+page.getTop()+","+page.getRows());
    }

    public boolean insert(Object tablebean) {
        this.setTableBean(tablebean);

        String sql = "insert into " + tableName + "(" + getFieldList() + ") values (" + getValueList() + ")";
        return (dao.executeUpdate(sql));
    }

    public boolean update(Object tablebean, String where) {
        this.setTableBean(tablebean);
        StringBuilder sql = new StringBuilder();
        sql.append("update ").append(tableName).append(" set ");
        boolean b = true;//判断是否第一次循环
        for (Field field : fields) {
            if (!b) {
                sql.append(",");
            } else {
                b = false;
            }
            Object value = getFieldValue(tablebean, field.getName());
            String name = field.getName();
            if (value != null) {
                dao.setPreparedParameter(value);
                sql.append(name).append("=").append("?");
            } else {
                sql.append(name).append("=").append("null");
            }
        }

        sql.append(" ").append(where);
        return (dao.executeUpdate(sql.toString()));
    }

    public boolean delete(Object tablebean, String where) {
        this.setTableBean(tablebean);
        StringBuilder sql = new StringBuilder();
        sql.append("delete from ").append(tableName).append(" ").append(where);
        return (dao.executeUpdate(sql.toString()));

    }

    /**
     * 拼装当前bean的所有字段名的字符串
     *
     * @return
     */
    private StringBuffer getFieldList() {
        StringBuffer fieldlist = new StringBuffer();
        boolean b = true;//判断是否第一次循环
        for (Field field : fields) {
            //第一次循环不加逗号，并置为false
            if (!b) {
                fieldlist.append(",");
            } else {
                b = false;
            }
            fieldlist.append(field.getName().toLowerCase());
        }
        return fieldlist;
    }

    /**
     * 拼装当前bean的所有字段值的字符串，如果有值的话做了预处理，实质上就“？”字符串。
     *
     * @return
     */
    private StringBuffer getValueList() {
        StringBuffer valuelist = new StringBuffer();
        boolean b = true;//判断是否第一次循环
        for (Field field : fields) {
            Object value = getFieldValue(tablebean, field.getName());
            //第一次循环不加逗号，并置为false
            if (!b) {
                valuelist.append(",");
            } else {
                b = false;
            }
            if (value != null) {
                dao.setPreparedParameter(value);
                valuelist.append("?");
            } else {
                valuelist.append("null");
            }

        }

        return valuelist;

    }

    /**
     * 取得一个成员变量的值
     *
     * @param field 成员变量
     * @return
     */
    private Object getFieldValue(Object tablebean, String fieldName) {
        if (fieldName == null || fieldName.trim().equals("")) {
            return null;
        }
        Object value = null;
        try {
            Method method = tablebean.getClass().getMethod(get(fieldName), (Class[]) null);//取得get方法
            value = method.invoke(tablebean, (Object[]) null);//调用实体类的getXXX方法
        } catch (Exception ex) {
            LoggerManage.logger.getLogger(this.getClass().getName() + ex,ex);
        }
        return value;
    }

    /**
     * 存入一个实体的成员变量值
     *
     * @param field
     * @param value
     * @return
     */
    private Object setFieldValue(Object tablebean, String fieldName, Object value) {
        if (value == null) {
            return tablebean;
        }
        try {
            Method method = tablebean.getClass().getMethod(set(fieldName), value.getClass());//取得set方法
            method.invoke(tablebean, value);//调用实体类的setXXX方法
        } catch (Exception ex) {
            LoggerManage.logger.getLogger(this.getClass().getName() + ex,ex);
        }
        return tablebean;
    }

    /**
     * 拼装getXXX方法的方法名字符串
     *
     * @param name 成员变量名
     * @return
     */
    public static String get(String name) {
        String get = "get" + (name.charAt(0) + "").toUpperCase() + name.substring(1);//get+变量名的第一个字母大写
        return get;
    }

    /**
     * 拼装setXXX方法的方法名字符串
     *
     * @param name 成员变量名
     * @return
     */
    private static String set(String name) {
        return "set" + (name.charAt(0) + "").toUpperCase() + name.substring(1);//get+变量名的第一个字母大写
    }
}
