<%@page import="com.huahuan.tools.Constants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : brower
    Created on : 2013-3-13, 20:29:00
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="<z:path/>web/image/favicon.ico" type="image/x-icon" />
        <link rel="Shortcut Icon" type="image/x-icon" href="<z:path/>web/image/favicon.ico" />
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/jquery-1.7.1.min.js"></script>
        <title>JSP Page</title>
        <script type="text/javascript">
            var path='<z:path/>';
            function choose(obj){
                $("#test").val(obj.src);
                window.close();
            }
        </script>
    </head>
    <body>
        <input type="hidden" id="test" value=""/>
        <c:choose>
            <c:when test="${fn:length(imglist)>0}">
                <c:forEach var="xzimg" items="${imglist}">
                    <img src="<z:path/>userfiles${xzimg}" onclick="choose(this)" style="width:180px;height:250px;cursor:pointer"/>
                </c:forEach>
            </c:when>
            <c:otherwise>
                对不起，没有可供选择的图片，请先在图片管理模块
                &lt;${foldername}&gt;
                文件夹下上传图片后再进行选择
            </c:otherwise>
        </c:choose>
    </body>
</html>
