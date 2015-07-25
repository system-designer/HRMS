<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : HeadBott
    Created on : 2013-1-20, 11:32:21
    Author     : ASUS
--%>

<%@page import="com.huahuan.table.Yhb"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>

<div class="dl">
    <iframe style="float: left;" src="http://m.weather.com.cn/m/pn7/weather.htm" allowtransparency="true" width="175" height="22" marginwidth="0" marginheight="0" hspace="0" vspace="0" frameborder="0" scrolling="no"></iframe>
    <c:choose>
        <c:when test="${sessionScope.yhb==null}">
            <span id="prelogin">
                <span></span>
                <span><a id="qq" href="<z:path/>QqLogin.jsp?mode=LOGIN"><img src="<z:path/>web/image/qq.png" />用腾讯QQ登录</a></span><span class="pipe">|</span>
                <span><a href="<z:path/>web/login/login.jsp">登录</a></span><span class="pipe">|</span>
                <span><a href="<z:path/>web/login/register.jsp">注册</a></span>
                <c:if test="${sessionScope.yhb!=null}">
                    <span class="pipe">|</span>
                    <span><a target="_blank" href="<z:path/>BlogIndexServlet.jsp?mode=SHOWALL&userid=1">进入个人空间</a></span> 
                </c:if>
            </span>
        </c:when>
        <c:otherwise>
            <span id="afterlogin">
                <span id="notice">${sessionScope.yhb.yhm},您好,欢迎回来</span>
                <a target="_blank" href="<z:path/>BlogIndexServlet.jsp?mode=SHOWALL&host=${sessionScope.yhb.id}">进入个人空间</a>
                <c:choose>
                    <c:when test="${sessionScope.yhb.qx==1}">
                        <a href="<z:path/>manage/main.jsp">进入后台管理</a>
                    </c:when>
                    <c:when test="${sessionScope.yhb.qx==2}">
                        <a href="<z:path/>manage/main_second.jsp">进入后台管理</a>
                    </c:when>
                    <c:when test="${sessionScope.yhb.qx==3}">
                        <a href="<z:path/>manage/main_third.jsp">进入后台管理</a>
                    </c:when>
                </c:choose>  
                <a href="<z:path/>IndexAction.jsp?mode=LOGINOUT">注销登录</a>
            </span>
        </c:otherwise>
    </c:choose>
</div>
<div style="clear:both;"></div>
<%--头部信息--%>
<div class="all"  >
    <div class="header" >
    </div>
    <%--主导航条--%>
    <div class="dh">
        <ul>
            <li><a href="<z:path/>IndexAction.jsp?mode=SHOWLIST">主页</a></li>
            <li><a href="<z:path/>web/gywm/gywm.jsp">关于我们</a></li>
            <li><a href="<z:path/>StggIndexAction.jsp?mode=SHOWGGLIST">志愿社公告</a></li>
            <li><a href="<z:path/>SthdIndexAction.jsp?mode=SHOWHDLIST">志愿社活动</a></li>
            <li><a href="<z:path/>HdjyIndexAction.jsp?mode=SHOWHDLIST">活动剪影</a></li>
            <li><a href="<z:path/>HbbkIndexAction.jsp?mode=SHOWBKLIST">公益百科</a></li>
            <li><a target="_blank" href="<z:path/>JltdIndexAction.jsp?mode=SHOWLIST">交流论坛</a></li>
            <li style="margin-right: 0px;"><a href="<z:path/>LywmIndexAction.jsp?mode=SHOWLIST">留言我们</a></li>
        </ul>
        <div style="clear:both;"></div>
    </div>
</div>
