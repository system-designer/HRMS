<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : hdjj
    Created on : 2013-1-21, 20:47:38
    Author     : ASUS
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="/jstl_extend.tld" prefix="extend" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="<z:path/>web/image/favicon.ico" type="image/x-icon" />
        <link rel="Shortcut Icon" type="image/x-icon" href="<z:path/>web/image/favicon.ico" />
        <link rel="stylesheet" type="text/css"href="<z:path/>web/css/blog_index.css"/>
        <link rel="stylesheet" type="text/css"href="<z:path/>web/css/HeadBott.css"/>
        <link rel="stylesheet" type="text/css"href="<z:path/>web/css/blog/blog_dyn.css"/>
        <title>${host.yhm}的动态</title>
    </head>
    <body>
        <%@include  file="../blog/blog_header.jsp" %> 
        <div class="blog_dyn_main">
            <div class="blog_dyn_left">
                <p class="blog_dyn_left_p">动态</p>
                <div class="blog_dyn_content">
                    <span class="blog_dyn_day">昨天</span>
                    <ul class="blog_dyn_ul1">
                        <c:choose>
                            <c:when test="${fn:length(hostloglist)>0}">
                                <c:forEach var="hostlog" items="${hostloglist}">
                                    <li><a href="#">${host.yhm} </a>发表了新日志 <span class="blog_dyn_gray">${hostlog.pubtime}</span>(<a href="#">评论</a>)<br/><br/>
                                        <a href="#"class="blog_dyn_bt">${hostlog.title}</a><br/>
                                        <div class="blog_dyn_nr">
                                             <%--${extend:ShowLen(hostlog.content,80)}--%>
                                             ${hostlog.content}
                                        </div>
                                    </li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                暂时没有日志动态
                            </c:otherwise>
                        </c:choose>
                      
                    </ul>
                    <ul class="blog_dyn_ul1">
                          <c:choose>
                            <c:when test="${fn:length(hostpostlist)>0}">
                                <c:forEach var="hostpost" items="${hostpostlist}">
                                    <li><a href="#">${host.yhm} </a>在<a href="#">【${hostpost.bkmc}】</a>板块发表了主题 <span class="blog_dyn_gray">${hostpost.ftsj}</span>(<a href="#">评论</a>)<br/><br/>
                                        <a href="#"class="blog_dyn_bt">${hostpost.tzbt}</a><br/>
                                        <div class="blog_dyn_nr">
                                            ${hostpost.tznr}
                                          <%--${extend:ShowLen(hostpost.tznr,80)}--%> 
                                        </div>
                                    </li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                暂时没有主题动态
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
            <div class="blog_dyn_right">
                <%@include  file="../blog/blog_picture.jsp" %> 
            </div>
            <div style="clear:both;"></div>
        </div>
        <%@include  file="../public/bott.jsp" %> 
    </body>
</html>
