package org.jplus.hyb.database;

import java.util.Arrays;
import java.util.List;

/**
 * 此类用来描述一行数据库查询结果
 * 以及数据的读取
 * <p>此类应该与SoEasy类配合使用。
 * <p>此类一般不自己构造，从SoEasy中获得。
 * <p>用于在使用SoEasy后分析和取得其查询数据
 * @author hyberbin
 * @date 2012/6/8
 */
public class EasyData {

    /** 结果集的字段名 */
    private List columnName;
    /** 结果集的数据 */
    private Object[] rowData;

    /**
     * 空构造方法
     */
    public EasyData() {
    }

    /**
     * 带一个参的构造方法只用于存放数据
     * @param rowData 一行数据
     */
    public EasyData(Object[] rowData) {
        this.rowData = rowData;
    }

    /**
     * 带两个参数的构造方法
     * @param rowData    一行数据
     * @param columnName 字段信息
     */
    public EasyData(Object[] rowData, String[] columnName) {
        this.rowData = rowData;
        this.columnName = Arrays.asList(columnName);
    }

    /**
     * 带两个参数的构造方法
     * @param rowData    一行数据
     * @param columnName 字段信息
     */
    public EasyData(Object[] rowData, List columnName) {
        this.rowData = rowData;
        this.columnName = columnName;
    }

    /**
     * 返回查询结果
     * @return 结果数组
     */
    public Object[] getRowData() {
        return rowData;
    }

    /**
     * 设置字段信息
     * @param columnName 字段信息
     */
    public void setColumn(String[] columnName) {
        this.columnName = Arrays.asList(columnName);
    }

    /**
     * 根据字段名获得一个数据
     * <p>Ex:
     * <p>SoEasy soEasy=new SoEasy();//操作完后自动关闭数据库。
     * <p>soEasy.addParmeter(2);
     * <strong><p>LinkedList<EasyData> list=soEasy.getList("select * from
     * news,newstype where newtype.id=news.type and news.type=?");</strong>
     * <p>EasyData data=list.get(0);//取第0条数据
     * <p>int id=data.get("id");//获得这条数据的id信息
     * <p>String content=data.get("content");//获得这条数据的内容信息
     * <p>……
     * @param Name 字段名
     * @return
     */
    public Object get(String Name) {
        return rowData[columnName.indexOf(Name)];
    }

    /**
     * 根据索引返回一个数据
     * <p>Ex:
     * <p>SoEasy soEasy=new SoEasy();//操作完后自动关闭数据库。
     * <p>soEasy.addParmeter(2);
     * <strong><p>LinkedList<EasyData> list=soEasy.getList("select * from
     * news,newstype where newtype.id=news.type and news.type=?");</strong>
     * <p>EasyData data=list.get(0);//取第0条数据
     * <p>int id=data.get(0);//获得这条数据的id信息
     * <p>String content=data.get(1);//获得这条数据的内容信息
     * <p>……
     * @param index 索引
     * @return
     */
    public Object get(int index) {
        return rowData[index];
    }

    /**
     * 按顺序输出字段信息
     * <p>此方法供测试的时候用
     * <p>用于在不知道查询结果的字段信息时输出一下字段信息
     */
    public void printColumn() {
        System.out.println(columnName);
    }
}
