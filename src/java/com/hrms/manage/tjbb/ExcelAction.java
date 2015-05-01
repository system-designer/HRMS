/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrms.manage.tjbb;

import com.hrms.util.Util;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.jplus.hyb.database.Hyberbin;
import org.jplus.util.CommonUtil;

/**
 * excel文件的操作
 *
 * @author evance
 */
public class ExcelAction {

    /**
     * 指定读取数据库临时表的列创建存放信息的excel文件
     *
     * @param beginRow excel开始行
     * @param beginColumn excel开始列
     * @param endColumn excel结束列
     * @param fileName 生成的excel文件名
     * @param excelHeader 生成的excel文件的表头
     * @param obj 实体类
     * @param list 查询的结果
     */
    public static void createExcel(int beginRow, int beginColumn, int endColumn, String fileName, String[] excelHeader, Object obj, List list) {
        try {
            //新建excel文件
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            WritableWorkbook book = Workbook.createWorkbook(file);
            WritableSheet sheet = book.createSheet("查询结果", 0);
            //生成表头
            //定义表头单元格样式
            WritableFont wf_head = new WritableFont(WritableFont.ARIAL, 10,
                    WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                    jxl.format.Colour.GREEN); // 定义格式 字体 下划线 斜体 粗体 颜色  
            WritableCellFormat wcf_head = new WritableCellFormat(wf_head);
            wcf_head.setAlignment(jxl.format.Alignment.CENTRE);
            for (int k = 0; k < excelHeader.length; k++) {
                Label label = new Label(k, 0, excelHeader[k], wcf_head);
                sheet.addCell(label);
            }
            //利用反射得到实体类的字段信息
            Field[] fields = obj.getClass().getDeclaredFields();
            //设置工作簿的行高和列宽
            for (int i = 0; i < list.size() + beginRow; i++) {
                sheet.setRowView(i, 400);
            }
            for (int i = 0; i < endColumn; i++) {
                sheet.setColumnView(i, 15);
            }
            //设置单元格字体样式
            WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 8,
                    WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                    jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色  
            WritableCellFormat wcf_table = new WritableCellFormat(wf_table);
            wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
            //在Label对象的构造子中指名单元格位置是第j列第i行(j,i)
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < endColumn; j++) {
                    if (j == 0) {
                        Label label = new Label(j, i + beginRow, (i + 1) + "", wcf_table);
                        sheet.addCell(label);
                    } else {
                        Object object = list.get(i);
                        Method method = object.getClass().getMethod(ReflectTool.get(fields[j].getName()), null); //取得get方法
                        Object o = method.invoke(object, null);//调用实体类的getXXX方法
                        if (fields[j].getType() == int.class) {
                            jxl.write.Number labelN = new jxl.write.Number(j, i + beginRow, CommonUtil.parseInt(o.toString()), wcf_table);
                            sheet.addCell(labelN);
                        } else if (fields[j].getType() == double.class || fields[j].getType() == float.class) {
                            jxl.write.NumberFormat nf = new jxl.write.NumberFormat("#.##");
                            jxl.write.WritableCellFormat wcfN = new jxl.write.WritableCellFormat(nf);
                            wcfN.setAlignment(Alignment.CENTRE);
                            jxl.write.Number labelNF = new jxl.write.Number(j, i + beginRow, Double.parseDouble(o.toString()), wcfN);
                            sheet.addCell(labelNF);
                        } else {//非数字类型
                            Label label = null;
                            if (o == null) {
                                label = new Label(j, i + beginRow, "", wcf_table);
                            } else {
                                label = new Label(j, i + beginRow, o.toString(), wcf_table);
                            }
                            sheet.addCell(label);
                        }
                    }
                }
            }
            //写入数据并关闭文件
            book.write();
            book.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
