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
        <link rel="stylesheet" type="text/css"href="<z:path/>web/css/blog/blog_log.css"/>
        <script type="text/javascript" src="<z:path/>web/blog/blo_log.js"></script>
        <title>${host.yhm}的日志</title>
    </head>
    <body>
        <%@include  file="../blog/blog_header.jsp" %> 
        <div class="blog_log_main">
            <div class="blog_log_left">
                <p>日志 <a href="#" class="blog_log_a">发表新日志</a></p><div style="clear:both;"></div>
                <div class="blog_log_line"></div>
                <!--                <div class="blog_log_content">
                                    <label><a href="#" class="blog_log_contest">测试</a><a  href="#" class="blog_log_share">分享</a><div style="clear:both;"></div></label><br/><br/>
                                    <span class="blog_log_gray">2014-3-10 21:27</span>
                                    <div class="blog_log_nr">
                                        测试2测试2测试2测试2测试2测试2测试2测试2测试2测试2测试2测试2测试2
                
                                    </div>
                                    <div class="blog_log_pl"> <a href="#"> 2 次阅读</a>|<a href="#">0 个评论</a> |<a href="#">编辑</a>| <a href="#">删除</a> | <a href="#">置顶 </a></div>
                                </div>-->
                <c:choose>
                    <c:when test="${fn:length(hostloglist)>0}">
                        <c:forEach var="hostlog" items="${hostloglist}">
                            <div class="blog_log_content">
                                <label><a href="#" class="blog_log_contest">${hostlog.title}</a><a  href="#" class="blog_log_share">分享</a><div style="clear:both;"></div></label><br/>
                                <span class="blog_log_gray">${hostlog.pubtime}</span><br/><br/>
                                <div class="blog_log_nr">
                                    ${extend:ShowLen(hostlog.content,80)}
                                </div>
                                <div class="blog_log_pl"> <a href="#"> ${hostlog.scantimes}次阅读</a>|<a href="#">${hostlog.replytimes} 个评论</a> |<a href="#">编辑</a>| <a href="#">删除</a> | <a href="#">置顶 </a></div>
                            </div>
                        </c:forEach>
                        <%--翻页--%>
                        <div class="blog_log_fy">
                            <span class="fy_ys"><a href="<z:path/>BlogLogServlet.jsp?mode=SHOWLIST&current=1">首页</a></span>
                            <span class="fy_ys"><a href="<z:path/>BlogLogServlet.jsp?mode=SHOWLIST&current=${pagger.upPage}">上一页</a></span>
                            <span class="fy_ys"><a href="<z:path/>BlogLogServlet.jsp?mode=SHOWLIST&current=${pagger.nextPage}">下一页</a></span>
                            <span class="fy_ys"><a href="<z:path/>BlogLogServlet.jsp?mode=SHOWLIST&current=${pagger.totalPage}">尾页</a></span>
                            第<span id="page">${pagger.current}</span>/<span id="totalpages">${pagger.totalPage}</span>页
                            <span><input type="text" size="1" id="goto_page"/></span>
                            <span class="fy_ys" id="goto" onclick="gotoPage()">goto</span>
                        </div>  
                    </c:when>
                    <c:otherwise>
                        主人暂时没有日志
                    </c:otherwise>
                </c:choose>

            </div>
            <div class="blog_log_right">
                <%@include  file="../blog/blog_picture.jsp" %> 
            </div>
        </div>
        <%@include  file="../public/bott.jsp" %> 
    </body>
</html>
