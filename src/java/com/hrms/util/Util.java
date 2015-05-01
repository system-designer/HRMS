/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hrms.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.jplus.util.LoggerManage;
import sun.misc.BASE64Encoder;

/**
 * 常用工具类
 *
 * @author 张浩春
 * @time 2012-5-13 20:20:44
 * @version 2.0 邹灿整理，加入upload和zoomOut
 * @version 3.0 邹灿,加入md5和base64编码加密工具
 */
public class Util {

    private final static int MAX_WIDTH = 1024;//图片的最大像素

    /**
     * 生成MD5加密和BASE64编码后的密文
     *
     * @return 编码后的密文
     */
    public static String generateMd5() {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            String token = System.currentTimeMillis() + "" + new Random().nextInt(999999);
            byte[] md5 = md.digest(token.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            result = encoder.encode(md5);
        } catch (Exception ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * 用来检查字符串是否为空，为空返回true，否则返回false
     *
     * @param str 字符串
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断一个字符串是否为整数，是返回true
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        return str != null && str.trim().length() > 0 && str.matches("^-?[1-9]\\d*|0$");
        //return str != null && str.trim().length() > 0 && str.matches("^[0-9]\\d*$");
    }

    /**
     * json格式中的特殊字符处理
     *
     * @param s
     * @return
     */
    public static String specialJson(String s) {
        StringBuilder sb = new StringBuilder();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 文件上传
     *
     * @param items
     * @param dir 文件保存路径，不存在时自动创建
     * @param bool 图片文件是否启用压缩
     * @param repeat 是否启用同名文件自动重名
     * @param limitType 限制的文件类型
     *
     * @return
     */
    public static boolean upload(List<FileItem> items, String dir, boolean bool, boolean repeat, String... limitType) {
        boolean upload = false;
        String fileName;
        Date date = new Date();
        ArrayList<String> list = null;
        SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");//用上传时间的时分秒来命名文件，防止文件名重复
        if (items == null || items.isEmpty()) {
            return false;
        }
        for (FileItem item : items) {
            if (!item.isFormField()) {
                try {
                    String fileType = item.getName().substring((item.getName().lastIndexOf("."))).toLowerCase();
                    fileName = item.getName().substring(0, item.getName().indexOf("."));
                    fileName = fileName + (repeat ? "(" + format.format(date) + ")" : "") + fileType;
                    if (limitType != null) {
                        list = new ArrayList<String>();
                        list.addAll(Arrays.asList(limitType));
                        if (!list.contains(fileType)) {//限制文件上传类型
                            return false;
                        }
                    }
                    File tempFile = new File(dir);
                    if (!tempFile.exists()) {//如果文件夹不存在
                        tempFile.mkdirs();//创建一个新的空文件夹
                    }
                    if (bool) {
                        upload = zoomOut(item.getInputStream(), fileName, dir);
                    } else {
                        BufferedInputStream bis = null;
                        BufferedOutputStream bos = null;
                        try {
                            bis = new BufferedInputStream(item.getInputStream());
                            byte[] bit = new byte[2048];
                            bos = new BufferedOutputStream(new FileOutputStream(dir + File.separator + fileName));
                            while (bis.read(bit) > -1) {
                                bos.write(bit);
                            }
                            bos.flush();
                            bos.close();
                            bis.close();
                        } catch (IOException e) {
                            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, e);
                        } finally {
                            if (bos != null) {
                                bos.close();
                            }
                            if (bis != null) {
                                bis.close();
                            }
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return upload;
    }

    /**
     * 将比较大的图片按比例缩小图片 例如几M的图片刚将缩小为宽度为1024的图片缩小后大概只有几百K
     *
     * @param in 图片的输入流
     * @param imgName 图片的名称
     * @param dir 存放的路径
     * @param MAX_WIDTH 图片最大像素
     * @return
     */
    public static boolean zoomOut(InputStream in, String imgName, String dir) {
        boolean zoom = false;
        int new_w, new_h;
        File file = new File(dir + "\\" + imgName);
        try {
            if (!file.exists()) {//如果文件不存在，则创建新的空文件
                file.createNewFile();
            }
            BufferedImage image = ImageIO.read(in);
            int width = image.getWidth();
            int height = image.getHeight();
            new_w = width;
            new_h = height;
            if (width > MAX_WIDTH) {//按比例缩小图片
                new_w = MAX_WIDTH;
                new_h = new_w * height / width;
            }
            BufferedImage result = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
            Graphics gs = result.createGraphics();
            gs.drawImage(image, 0, 0, new_w, new_h, null);
            gs.dispose();
            FileOutputStream out = new FileOutputStream(file);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(result);
            out.close();
        } catch (IOException e) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, e);
        }
        return zoom;
    }

    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     *
     * @param sPath 要删除的目录或文件
     * @return 删除成功返回 true，否则返回 false。
     */
    public static boolean DeleteFolder(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else {  // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            return false;
        }
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证手机号码
     *
     * @param phoneNum
     * @return
     */
    public static boolean isMobileNO(String phoneNum) {
        if (phoneNum != null) {
            String mobileRegx = "^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
            String phoneRegx = "0\\d{2,3}-\\d{7,8}";
            if (!phoneNum.matches(mobileRegx)) {
                if (!phoneNum.matches(phoneRegx)) {
                    return false;
                }
                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    //得到文件/文件夹在服务器路径
    public static String getFileServerPath(String path, HttpServletRequest request) {
        return request.getServletContext().getRealPath(path);
    }

    /**
     * 验证对象是否为空
     *
     * @param object
     * @param message
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Check the given request for a session attribute of the given name.
     * Returns null if there is no session or if the session has no such
     * attribute. Does not create a new session if none has existed before!
     *
     * @param request current HTTP request
     * @param name the name of the session attribute
     * @return the value of the session attribute, or <code>null</code> if not
     * found
     */
    public static Object getSessionAttribute(HttpServletRequest request, String name) {
        notNull(request, "Request must not be null");
        HttpSession session = request.getSession(false);
        return (session != null ? session.getAttribute(name) : null);
    }

    public static String addField2InsertSql(String sql, String... fieldNames) {
        StringBuilder sBuilder = new StringBuilder(sql);
        boolean nofield = ((sBuilder.indexOf("(") + 1) == sBuilder.indexOf(")") && (sBuilder.lastIndexOf("(") + 1) == sBuilder.lastIndexOf(")")) ? true : false;
        for (int i = 0, len = fieldNames.length; fieldNames != null && i < len; i++) {
            sBuilder.insert(sBuilder.indexOf(")"), (i == 0 && nofield ? "" : ",") + fieldNames[i]).insert(sBuilder.lastIndexOf(")"), (i == 0 && nofield ? "" : ",") + "?");
        }
        return sBuilder.toString();
    }
    
}
