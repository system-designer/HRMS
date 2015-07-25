<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : hdjj
    Created on : 2013-1-21, 20:47:38
    Author     : ASUS
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="header2">
    <div class="header2_left"><a href="<z:path/>IndexAction.jsp?mode=SHOWLIST">担当者行动</a>  <a href="<z:path/>IndexAction.jsp?mode=SHOWLIST">返回首页</a></div>
    <div class="header2_right">
        <c:if test="${sessionScope.yhb!=null}">
            ${sessionScope.yhb.yhm}
            <a href="<z:path/>IndexAction.jsp?mode=LOGINOUT">退出</a>
        </c:if>
    </div>
</div>
    <div style="height:3px;"></div>
<div class="header1" >
    <div class="message">
        **********<br/>
        <a href="http://www.baidu.com">dddd</a>
    </div>
    <div class="nav">
        <ul>
            <li><a href="<z:path/>BlogIndexServlet.jsp?mode=SHOWALL&host=${host.id}">空间首页</a></li>
            <li><a href="<z:path/>BlogDynServlet.jsp?mode=SHOWALL&host=${host.id}">动态</a></li>
            <li><a href="<z:path/>BlogLogServlet.jsp?mode=SHOWLOGLIST&host=${host.id}">日志</a></li>
            <li><a href="<z:path/>BlogLogServlet.jsp?mode=SHOWPOSTLIST&host=${host.id}">主题</a></li>
            <li><a href="<z:path/>BlogLeaveServlet.jsp?mode=SHOWLEAVELIST&host=${host.id}">留言板</a></li>
            <li><a href="<z:path/>BlogYhbServlet.jsp?mode=SHOWONE&host=${host.id}">个人资料</a></li>
        </ul>
    </div>
</div>
