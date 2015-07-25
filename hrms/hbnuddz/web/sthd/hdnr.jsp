<%-- 
    Document   : hdnr
    Created on : 2013-3-16, 20:19:18
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<!DOCTYPE html >
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../public/include.jsp"></jsp:include>
        <meta name="keywords" content="大学校园社团活动，环保，绿源环保协会，湖北师范学院，Jplus实验室">
        <meta name="Description" content="这里是绿源环保协会的社团活动，您可以在这里查看绿源环保的最新活动信息">
        <meta name="author" content="刘雷，裴秀">
        <link rel="stylesheet" href="<z:path/>web/sthd/hdnr.css"/>
        <title>活动内容</title>
    </head>
    <body>
        <div class="nr">
            <p style="font-weight:bold;">${hd.hdbt}</p>
            <img src="<z:path/>userfiles${hd.hyzp}" style="width:200px;height:220px;margin-left: 260px;"/>
            <div class="nr1">
                ${hd.hdnr}
            </div>
        </div>
    </body>
</html>
