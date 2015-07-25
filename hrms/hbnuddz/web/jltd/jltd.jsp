<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : index
    Created on : 2013-1-20, 10:34:00
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<!DOCTYPE html >
<html>
    <head>
        <jsp:include page="../public/include.jsp"></jsp:include>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="keywords" content="环保，论坛，校园，Jplus实验室">
        <meta name="Description" content="用来交流环保的最新动态，和个人对环保的看法，还可以聊聊校园里的故事">
        <meta name="author" content="刘雷，裴秀">
        <link rel="icon" href="<z:path/>web/image/favicon.ico" type="image/x-icon" />
        <link rel="Shortcut Icon" type="image/x-icon" href="<z:path/>web/image/favicon.ico" />
        <link rel="stylesheet" type="text/css"href="<z:path/>web/css/jltd.css"/>
        <title>交流论坛</title>
    </head>
    <body>
        <%@include  file="../public/head.jsp" %>
        <div style=" clear:both;"></div>
        <div class="main">
            <div class="wz">
                <span>当前位置</span>
                <a href="<z:path/>IndexAction.jsp?mode=SHOWLIST">主页</a>>>
                <label style="color:red;"><a href="<z:path/>JltdIndexAction.jsp?mode=SHOWLIST">交流天地</a></label>
            </div>
            <div style="clear:both;"></div>
            <div class="ts" style=""><span style="float:left;"><img src="<z:path/>web/image/2.png"style="margin-right: 5px;"/>既然来了，就说点什么吧</span><div style="clear:both;"></div></div>
            <div style="clear:both;"></div>
            <!--列表-->
            <ul class="ul1">
                <c:forEach var="ar" items="${arr}">
                    <li>
                        <c:forEach var="bk" items="${bklist}" begin="${ar*2}" end="${(ar+1)*2-1}">
                            <a href="<z:path/>TzlbIndexAction.jsp?mode=SHOWLIST&bkid=${bk.bkid}">
                                <img src="<z:path/>userfiles${bk.bktb}" style="width:53px;height:54px;"/><div class="div1">${bk.bkmc}<br/>${bk.bkms}</div>
                            </a>
                            <div class="div2">帖子总数:${bk.tzzs}<br/>最后发表:${bk.zhfbsj}</div>
                            </c:forEach>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div style="width:991px;height:20px;float:left;"></div>
        <div style="clear:both"></div>
        <%@include  file="../public/bott.jsp" %>
    </body>
</html>
