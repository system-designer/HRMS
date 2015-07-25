<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : hdjj
    Created on : 2013-1-21, 20:47:38
    Author     : ASUS
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="picture">

    <img src="<z:path/>userfiles${host.yhtx}" style="width:117px;height:120px;"/><br/>
    <span>${host.yhm}</span>
    <div class="information">
        <ul>
            <li><a href="#">装扮空间</a></li>
            <li><a href="#">查看留言</a></li>
            <li><a href="#">编辑头像</a></li>
            <li><a href="<z:path/>web/blog/blog_personInfo.jsp">更新资料</a></li>
        </ul>
    </div>
    <div class="management">用户管理</div>
</div>
