package org.jplus.hyb.database;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Hyberbin
 */
public class SqlFilter {

    private Map<String, Object> eqMap;//条件
    private Map<String, String> otherMap;
    //查询条数和记录的sql语句
    private String countSQL;
    private String selectSQL;
    private boolean isFilter = false;//是否要转换
    private boolean isFiltered=false;//是否转换过

    public void setEqMap(String key, Object value) {
        if (value != null) {
            if (eqMap == null) {
                eqMap = new HashMap();
                isFilter = true;
            }
            eqMap.put(key, value);
        }
    }

    public void setOtherMap(String key, String value) {
        if (value != null) {
            if (otherMap == null) {
                otherMap = new HashMap();
                isFilter = true;
            }
            otherMap.put(key, value);
        }
    }
    
    public void clear(){
        eqMap=null;
        otherMap=null;
        isFiltered=false;
        isFilter=false;
    }

    public void getSql(String sql) {
        if (isFilter&&!isFiltered) {
            StringBuilder heads = new StringBuilder();
            heads.append("select hyberbin.* from( ");
            StringBuilder headc = new StringBuilder();
            headc.append("select count(*) as count from( ");
            StringBuilder sb = new StringBuilder();
            String lower = sql.toLowerCase();
            String limit = "";
            if (lower.contains("limit")) {
                limit = sql.substring(lower.indexOf("limit"));
                sql = sql.substring(0, lower.indexOf("limit"));
            }
            sb.append(sql);
            sb.append(") hyberbin ");
            sb.append(" where 1=1 ");
            if (eqMap != null && eqMap.size() > 0) {
                for (String key : eqMap.keySet()) {
                    sb.append(" and hyberbin.").append(key).append("=").append(eqMap.get(key)).append(" ");
                }
            }
            if (otherMap != null && otherMap.size() > 0) {
                for (String key : otherMap.keySet()) {
                    sb.append(" and hyberbin.").append(key).append(eqMap.get(key)).append(" ");
                }
            }
            selectSQL = heads.append(sb.append(limit)).toString();
            countSQL = headc.append(sb).toString();
            isFiltered=true;
        }else if(isFiltered){
            selectSQL=sql;
        }
    }

    public String getCountSQL() {
        return countSQL;
    }

    public String getSelectSQL() {
        return selectSQL;
    }

    public boolean isFilter() {
        return isFilter;
    }

    public boolean isFiltered() {
        return isFiltered;
    }
    
}