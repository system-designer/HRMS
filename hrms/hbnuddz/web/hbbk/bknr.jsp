<%-- 
    Document   : bknr
    Created on : 2013-3-1, 18:46:25
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<!DOCTYPE html >
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="keywords" content="参与环保从我做起">
        <meta name="Description" content="介绍关于环保的百科知识，包括环保新闻，环保知识，身边环保事，环保微课堂">
        <meta name="author" content="刘雷，裴秀">
        <jsp:include page="../public/include.jsp"></jsp:include>
        <link rel="stylesheet" href="<z:path/>web/hbbk/bknr.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="nr">
            <p>${bk.bkbt}</p>
            <div class="nr1">
                ${bk.bknr}
            </div>
        </div>
    </body>
</html>
