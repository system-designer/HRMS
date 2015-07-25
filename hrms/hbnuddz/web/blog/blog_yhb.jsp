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
        <link rel="stylesheet" type="text/css"href="<z:path/>web/css/blog/blog_yhb.css"/>
        <title>***的个人资料</title>
    </head>
    <body>
        <%@include  file="../blog/blog_header.jsp" %> 
        <div class="blog_yhb_main">
            <div class="blog_yhb_left">
                <p>个人资料</p>
                <div class="blog_yhb_xx">
                    <table class="blog_yhb_table1">
                        <tr><td><h4>${host.yhm}&nbsp;&nbsp;online  (UID: ${host.id})</h4></td></tr>
                        <tr><td><span class="blog_yhb_span1">空间访问量</span><span style="color:red;">${visitnum==null?"1":visitnum}</span></td><td><span class="blog_yhb_span1">邮箱状态</span><span style="color:red;">${host.sfjh==1?"已激活":"未激活"}</span></td></tr>
                        <tr><td colspan="2"><span class="blog_yhb_span1">视频认证</span>   未认证</td></tr>
                    </table>
                    <ul class="blog_yhb_ul1">
                        <li>统计信息
                            <a href="#">好友数${friendnum}</a>   |
                            <%--<a href="#">记录数 0</a>   | --%>
                            <a href="#">日志数 ${lognum} </a> |
                            <%--<a href="#">相册数 0</a>  |--%>
                            <a href="#">回帖数 ${htnum} </a>  | 
                            <a href="#">主题数  ${ftnum} </a>  |
                        </li>
                        <li> 
                            <table class="blog_yhb_table2">

                                <tr><td><span class="blog_yhb_span1">性别</span>${host.xb}</td><td><span class="blog_yhb_span1">  生日</span>${host.csrq}</td></tr>

                            </table>
                        </li>
                    </ul>

                </div>
                <div class="blog_yhb_glbk">
                    <ul>
                        <li> <h4>管理以下版块</h4></li>
                        <li><a href="#">${host.bkmc}</a></li>
                    </ul>
                </div>
                <div class="blog_yhb_hy">




                    <table class="blog_yhb_table1" style="margin-top: 5px;;">
                        <tr><td><h4>活跃概况</h4></td></tr>
                        <tr><td>用户组  <img src="<z:path/>web/image/bbs/online_${host.qx}.gif" />${host.qxmc}</td></tr>
                        <tr><td><span class="blog_yhb_span1">在线时间</span>  2013-12-28 09:0</td><td><span class="blog_yhb_span1">注册时间</span>${host.zcsj}</td></tr>
                        <tr><td><span class="blog_yhb_span1"> 最后访问</span> ${host.zhdlsj}</td><td><span class="blog_yhb_span1"> 上次发表时间</span>2013-12-28 09:05</td></tr>
                    </table>
                </div>

                <div class="blog_yhb_tj">
                    <table class="blog_yhb_table1" style="margin-top: 5px;;">
                        <tr><td><h4>统计信息</h4></td></tr>
                        <tr><td><span class="blog_yhb_span1"> 已用空间 </span>  2013-12-28 09:0</td><td><span class="blog_yhb_span1"> 积分</span>${host.jf}</td></tr>
                        <tr><td><span class="blog_yhb_span1">  金币 </span>${host.lsjf}</td><td><span class="blog_yhb_span1">担当</span>2013-12-28 09:05</td></tr>
                    </table>
                </div>
            </div>
            <div class="blog_yhb_right">
                <%@include  file="../blog/blog_picture.jsp" %> 
            </div>
            <div style="clear:both;"></div>
        </div>
        <%@include  file="../public/bott.jsp" %> 
    </body>
</html>
