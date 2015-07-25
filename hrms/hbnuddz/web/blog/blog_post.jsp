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
<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="<z:path/>web/image/favicon.ico" type="image/x-icon" />
        <link rel="Shortcut Icon" type="image/x-icon" href="<z:path/>web/image/favicon.ico" />
        <link rel="stylesheet" type="text/css"href="<z:path/>web/css/blog_index.css"/>
        <link rel="stylesheet" type="text/css"href="<z:path/>web/css/HeadBott.css"/>
        <link rel="stylesheet" type="text/css"href="<z:path/>web/css/blog/blog_post.css"/>
        <title>${host.yhm}的主题</title>
    </head>
    <body>
        <%@include  file="../blog/blog_header.jsp" %> 
        <div class="blog_post_main">
            <div class="blog_post_left">
                <p class="blog_post_left_p"<span>主题</span><span>|<a href="#">回复</a></span><a href="#" class="blog_post_left_p_right">发帖</a><div style="clear:both;"></div></p>
            <ul class="blog_post_ul1">
                <li style=" height:20px;line-height:20px; background: #D0E5F5;"><div class="blog_post_zhuti">主题</div><div class="blog_post_mokuai">版块/群组</div><div class="blog_post_huifu">回复/查看</div><div class="blog_post_fatie">最后发帖</div></li>
                <li>sdfkjkjdfjsjkkjdjsjksdf</li>
            </ul>
               <ul class="blog_post_ul1" style="display:none;">
                <li style=" height:20px;line-height:20px; background: #D0E5F5;"><div class="blog_post_zhuti">主题</div><div class="blog_post_mokuai">版块/群组</div><div class="blog_post_huifu">回复/查看</div><div class="blog_post_fatie">最后发帖</div></li>
                <li>sdfkjkjdfjsjkkjdjsjksdf</li>
            </ul>
        </div>
             <div class="blog_post_right">
             <%@include  file="../blog/blog_picture.jsp" %> 
        </div>
        <div style="clear:both"></div>
        </div>
         <%@include  file="../public/bott.jsp" %> 
    </body>
</html>
