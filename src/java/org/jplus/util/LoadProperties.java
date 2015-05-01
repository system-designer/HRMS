package org.jplus.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * 读取配置文件
 *
 * @version 1.0
 * @author hyberbin
 */
public class LoadProperties {

    /** Properties对象 */
    private Properties props = new Properties();
    private String filePath;

    /**
     * 构造方法
     */
    public LoadProperties(String filePath) {
        this.filePath = filePath;
        try {
            if (!filePath.replace("\\", "/").contains("/")) {
                filePath = filePath.replace(".", "/");
                String typeString = filePath.substring(filePath.lastIndexOf("/")).replace("/", ".");
                filePath = filePath.substring(0, filePath.lastIndexOf("/"))+typeString;
                this.filePath = LoadProperties.class.getClassLoader().getResource(filePath).getPath();
            } else {
                File f = new File(filePath);
                this.filePath = f.getAbsolutePath();
            }
            this.filePath = java.net.URLDecoder.decode(this.filePath, "utf-8");
            InputStream in = new FileInputStream(this.filePath);
            props.load(in);
            in.close();
        } catch (Exception ex) {
            LoggerManage.logger.getLogger("LoadProperties找不到数据库配置文件，请检查缺省包下面的" + filePath + "文件!", ex);
        }
    }

    /**
     * 自动读取整个配置文件并存入一个对象
     *
     * @param object
     * @return
     */
    public Object loadProperties(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                String name = fields[i].getName();//获得成员变量名
                String pValue = props.getProperty(fields[i].getName());
                if (pValue == null) {
                    continue;
                }
                Object value = ConverString.asType(fields[i].getType(), pValue);//获得成员变量的值                
                FieldUtil.setFieldValue(object, name, value);
            }
        } catch (Exception ex) {
            LoggerManage.logger.getLogger("LoadProperties读取配置文件错误\t", ex);
        }
        return object;
    }

    public Properties getProps() {
        return props;
    }

    public void store() {
        try {
            OutputStream fos = new FileOutputStream(filePath);
            props.store(fos, "");
            fos.flush();
            fos.close();
        } catch (Exception ex) {
            LoggerManage.logger.getLogger("LoadProperties写入" + filePath + "文件失败\t", ex);
        }

    }

    public String getProperty(String name) {
        return props.getProperty(name);
    }

    public int getIntProperties(String name) {
        return Integer.parseInt(props.getProperty(name));
    }
}
