<%-- 
    Document   : index
    Created on : 2013-2-7, 10:55:37
    Author     : evance
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <style>
        img{
            border:none;
        }
    </style>
    <body>
        <center>
            <img src="<z:path/>web/image/404_not.jpg" style="margin-top: 80px;"/><br/>
            <a href="<z:path/>IndexAction.jsp?mode=SHOWLIST" style="color:black;text-decoration: none;margin-left: 200px;"><img src="<z:path/>web/image/back.png" style="width:50px;height:50px;"/>回到首页</a>
        </center>
    </body>
</html>
