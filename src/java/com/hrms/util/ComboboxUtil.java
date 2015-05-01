/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrms.util;

import com.jplus.json.JSONArray;
import com.jplus.json.JSONException;
import com.jplus.json.JSONObject;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jplus.yydbgai.DatabaseAccess;

/**
 *
 * @author 张浩春
 * @time 2012-4-15 10:02:50
 */
public class ComboboxUtil {

    /**
     * 将多个combobox需要用到的JSON数据拼装成一个二维数组 该方法为一个不定参方法，使用时只需要次多个LIST写入
     * 示例代码：ServletUtil.Ajaxdata(ComboboxUtil.createBatchComboJSON(list1, list2,
     * list3),response);
     *
     * @param list
     * @return
     */
    public static String createBatchComboJSON(List... list) {
        String name;
        JSONArray array;
        Object obj;
        Integer last;
        JSONObject o = new JSONObject();
        for (List combo : list) {
            if (combo.size() > 0) {
                obj = combo.get(0);
                name = obj.getClass().getName();
                last = name.lastIndexOf(".");
                name = name.substring(last + 1);
                array = new JSONArray(combo);
                try {
                    o.put(name, array);
                } catch (JSONException ex) {
                    Logger.getLogger(ComboboxUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return o.toString();
    }

    public static String createBatchComboArrayJSON(ArrayList<HashMap>... list) {
        String name = "A";
        StringBuilder json = new StringBuilder("{");
        JSONArray array;
        Integer last;
        int i = 0;
        for (ArrayList combo : list) {
            if (combo.size() > 0) {
                i++;
                name = "A";
                name = name.concat("" + i);
                last = name.lastIndexOf(".");
                name = name.substring(last + 1);
                array = new JSONArray(combo);
                json.append("\"").append(name).append("\":").append(array).append(",");
            }
        }
        return json.subSequence(0, json.length() - 1) + "}";
    }

    public static String createOneJsonObjectBySQLs(Map<String, String> map) {
        StringBuilder builder = new StringBuilder("{");
        for (String key : map.keySet()) {
            builder.append("\"" + key + "\":");
            String sql = map.get(key);
            String jsonArray = getJsonArrayBySQL(sql);
            builder.append(jsonArray).append(",");
        }
        removeLastOne(builder).append("}");
        return builder.toString();
    }

    private static String getJsonArrayBySQL(String sql) {
        DatabaseAccess db = new DatabaseAccess();
        ResultSet rs = db.executeQuery(sql);
        ResultSetMetaData metaData = null;
        try {
            if (rs.next()) {
                rs.beforeFirst();
                StringBuilder builder = new StringBuilder("[");
                metaData = rs.getMetaData();
                while (rs.next()) {
                    builder.append("{");
                    for (int i = 1; i <= metaData.getColumnCount(); i++) {
                        builder.append("\"").append(metaData.getColumnLabel(i).toLowerCase()).
                                append("\":\"").append(rs.getObject(i)).append("\",");
                    }
                    removeLastOne(builder).append("},");
                }
                builder = removeLastOne(builder).append("]");
                db.close();
                return builder.toString();
            } else {
                return "[]";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComboboxUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "[]";

    }

    private static StringBuilder removeLastOne(StringBuilder s) {
        return s.deleteCharAt(s.length() - 1);
    }
}
