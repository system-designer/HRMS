<%-- 
    Document   : ggnr
    Created on : 2013-2-16, 11:22:08
    Author     : evance
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<!DOCTYPE html >
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../public/include.jsp"></jsp:include>
        <meta name="keywords" content="社团公告，环保，绿源环保协会，湖北师范学院，Jplus实验室">
        <meta name="Description" content="这里是绿源环保协会的社团公告，您可以在这里查看绿源环保的最新动态">
        <meta name="author" content="刘雷，裴秀">
        <link rel="stylesheet" href="<z:path/>web/stgg/ggnr.css"/>
        <title>公告内容</title>
    </head>
    <body>
        <div class="nr">
            <p style="font-weight:bold;">${gg.ggbt}</p>
            <div class="nr1">
                ${gg.ggnr}
                <div style="height:10px;"></div>
            </div>
        </div>
    </body>
</html>
