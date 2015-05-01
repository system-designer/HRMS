/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrms.manage.jnrc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jplus.yydbgai.DatabaseAccess;

/**
 *
 * @author Administrator
 */
public class UtilTools {

    /**
     * 将结果集转换为json
     * @param rs
     * @return rs对应的json
     */
    public static String toJson(ResultSet rs) {
        StringBuilder sb = new StringBuilder("{");//{"":"","":""}
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String lableName=metaData.getColumnLabel(i);
                    sb.append("\"").append(lableName).append("\"").append(":");
                    sb.append("\"").append(rs.getObject(lableName)).append("\",");
                }
            }
            sb.deleteCharAt(sb.length() - 1).append("}");
        } catch (SQLException ex) {
            Logger.getLogger(UtilTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }
    
    /**
     * 
     * @param sqls
     * @return 
     */
    public static String toJsonArrays(String... sqls){//[[{},{}],[{},{}],[{},{}],[{},{}]]
        DatabaseAccess db=new DatabaseAccess();
        StringBuilder sb=new StringBuilder("[");
        for(String sql:sqls){
            sb.append(toJsonArray(sql,db)).append(",");
        }
        db.close();
        sb.deleteCharAt(sb.length()-1).append("]");
        return sb.toString();
    }
    
    public static String toJsonArray(String sql,DatabaseAccess db){//[{"":"","":""},{"":""},{"":""}]
        StringBuilder sb=new StringBuilder("[");
        ResultSet rs = db.executeQuery(sql);
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            while(rs.next()){
                sb.append("{");
                for(int i=1;i<=metaData.getColumnCount();i++){
                    String tempS=metaData.getColumnLabel(i);
                    sb.append("\"").append(tempS).append("\":\"");
                    sb.append(rs.getObject(i)).append("\",");
                }
                sb.deleteCharAt(sb.length() - 1).append("},");
            }
            sb.deleteCharAt(sb.length() - 1).append("]");
        } catch (SQLException ex) {
            Logger.getLogger(UtilTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }
}
