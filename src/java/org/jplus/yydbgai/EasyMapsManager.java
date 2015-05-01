/**
 *
 * @author 杨怿，用于单表或多表数据库查询后生成方便使用的结果集
 */
package org.jplus.yydbgai;

import com.jplus.json.EasyUiJson;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import org.jplus.hyb.database.SqlFilter;
import org.jplus.util.LoggerManage;
import org.jplus.util.Pagger;

/**
 *
 * @author Administrator
 */
/**
 * 数据库操作对象
 */
public class EasyMapsManager extends SqlFilter {

    private DatabaseAccess dao;
    /**
     * 结果集列表
     */
    private ArrayList<HashMap> dataList;

    public EasyMapsManager(DatabaseAccess dao) {
        this.dao = dao;//数据库操作对象
    }

    /**
     * 加入新的预处理参数
     *
     * @param parameter 参数
     * @return 自身对象
     */
    public void setPreparedParameter(Object parameter) {
        dao.setPreparedParameter(parameter);
    }

    /**
     * 从数据库查询结果集合中取得数据
     *
     * @param rs 数据库查询结果集合
     * @return List<EasyData>
     */
    private ArrayList<HashMap> loadData(ResultSet rs) {
        dataList = new ArrayList<HashMap>();
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs != null && rs.next()) {
                // Object[] rowData = new Object[columnCount];
                HashMap rowData = new HashMap();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(metaData.getColumnLabel(i), rs.getObject(i));//获得列标签和名称是有区别的

                }
                dataList.add(rowData);
            }
        } catch (SQLException ex) {
            LoggerManage.logger.getLogger(this.getClass().getName() + ex,ex);
        }
        return dataList;
    }

    /**
     * 获得查询集合，如果没有查询到结果，返回一个size=0的list
     *
     * @param sql
     * @return List<EasyData>
     */
    public ArrayList<HashMap> executeQuery(String sql) {
        if (super.isFilter()) {
            super.getSql(sql);
            sql = super.getSelectSQL();
        }
        loadData(dao.executeQuery(sql));
        return dataList;
    }

    public ArrayList<HashMap> executeQuery(String sql, Pagger page) {
        if (super.isFilter()) {
            super.getSql(sql);
            sql = super.getSelectSQL();
            page.setItems(dao.executeCount(super.getCountSQL()));
        } else {
            page.setItems(dao.executeCount("select count(*) as count from (" + sql + ") hybcount"));
        }
        return executeQuery(sql + " limit " + page.getTop() + "," + page.getSize());
    }

    public ArrayList<HashMap> executeQuery(String sql, EasyUiJson page) {
        if (super.isFilter()) {
            super.getSql(sql);
            sql = super.getSelectSQL();
            page.setTotal(dao.executeCount(super.getCountSQL()));
        } else {
            page.setTotal(dao.executeCount("select count(*) as count from (" + sql + ") hybcount"));
        }
        return executeQuery(sql + " limit " + page.getTop() + "," + page.getRows());
    }
}
