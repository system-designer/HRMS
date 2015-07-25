<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : hdjj
    Created on : 2013-1-21, 20:47:38
    Author     : ASUS
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="<z:path/>web/image/favicon.ico" type="image/x-icon" />
        <link rel="Shortcut Icon" type="image/x-icon" href="<z:path/>web/image/favicon.ico" />
        <link rel="stylesheet" type="text/css"href="<z:path/>web/css/blog_index.css"/>
        <link rel="stylesheet" type="text/css"href="<z:path/>web/css/HeadBott.css"/>
        <link rel="stylesheet" type="text/css"href="<z:path/>web/css/blog/blog_leave.css"/>
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="<z:path/>web/blog/blo_leave.js"></script>
        <title>${host.yhm}的留言</title>
    </head>
    <body>
        <%@include  file="../blog/blog_header.jsp" %> 
        <div class="blog_leave_main">
            <div class="blog_leave_left">
                <p>留言表</p>
                <div class="blog_leave_lyxx">
                    <textarea class="blog_leave_textarea"></textarea><br/><br/>
                    <span class="blog_leave_button">留言</span>
                </div>
              
                <ul class="blog_leave_ul1">
                    <c:choose>
                        <c:when test="${fn:length(hostleavelist)>0}">
                            <c:forEach var="hostleave" items="${hostleavelist}">
                                <li>
                                    <div class="blog_leave_ul1_left"><img src="<z:path/>userfiles${hostleave.yhtx}" style="width:60px;height:60px;"/></div>
                                    <div class="blog_leave_ul1_right"><div  class="blog_leave_ul1_right_left"><span><a href="#">${hostleave.yhm}</a> ${hostleave.leavetime}</span></div><div class="blog_leave_ul1_right_right"><span class="blog_leave_span_right"><a href="#">编辑</a>   <a href="#">删除</a></span></div><div style="clear:both;"></div>
                                        <div class="blog_leave_ul1_nr">${hostleave.content}</div>
                                    </div>
                                    <div style="clear:both"></div>
                                </li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            暂时没有留言，还不赶紧抢沙发
                        </c:otherwise>
                    </c:choose>
                </ul>
                 <div class="blog_log_fy">
                            <span class="fy_ys"><a href="<z:path/>BlogLogServlet.jsp?mode=SHOWLIST&current=1">首页</a></span>
                            <span class="fy_ys"><a href="<z:path/>BlogLogServlet.jsp?mode=SHOWLIST&current=${pagger.upPage}">上一页</a></span>
                            <span class="fy_ys"><a href="<z:path/>BlogLogServlet.jsp?mode=SHOWLIST&current=${pagger.nextPage}">下一页</a></span>
                            <span class="fy_ys"><a href="<z:path/>BlogLogServlet.jsp?mode=SHOWLIST&current=${pagger.totalPage}">尾页</a></span>
                            第<span id="page">${pagger.current}</span>/<span id="totalpages">${pagger.totalPage}</span>页
                            <span><input type="text" size="1" id="goto_page"/></span>
                            <span class="fy_ys" id="goto" onclick="gotoPage()">goto</span>
                        </div>  
            </div>
            <div class="blog_leave_right">
                <%@include  file="../blog/blog_picture.jsp" %> 
            </div>
            <div style="clear:both"></div>
        </div>

        <%@include  file="../public/bott.jsp" %> 
    </body>
</html>
