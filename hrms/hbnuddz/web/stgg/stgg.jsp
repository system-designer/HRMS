<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : stgg
    Created on : 2013-1-21, 17:24:14
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../public/include.jsp"></jsp:include>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="keywords" content="社团公告，环保，绿源环保协会，湖北师范学院，Jplus实验室">
        <meta name="Description" content="这里是绿源环保协会的社团公告，您可以在这里查看绿源环保的最新动态">
        <meta name="author" content="刘雷，裴秀">
        <link rel="icon" href="<z:path/>web/image/favicon.ico" type="image/x-icon" />
        <link rel="Shortcut Icon" type="image/x-icon" href="<z:path/>web/image/favicon.ico" />
        <link rel="stylesheet" type="text/css"href="<z:path/>web/css/stgg.css"/>
        <script type="text/javascript" src="<z:path/>web/stgg/stgg.js"></script>
        <title>志愿社公告</title>
        <script type="text/javascript">
            var path='<z:path/>';
            var ggid='${ggid}';//公告id
            var gglb='${gglb}';//公告类别
        </script>
    </head>
    <body >
        <%@include  file="../public/head.jsp" %>
        <%--位置--%>
        <div style="clear:both">
        </div>
        <div class="main">
            <div class="wz">
                <span>当前位置</span>
                <a href="<z:path/>IndexAction.jsp?mode=SHOWLIST">主页</a>>>
                <label ><a href="<z:path/>StggIndexAction.jsp?mode=SHOWGGLIST" >社团公告</a></label>
                <lable id="third">>><a href="<z:path/>StggIndexAction.jsp?mode=SHOWGGLIST&gglb=${gglb}&page=${page}" >${lbmc}</a></lable>
                <a id="ret" href="<z:path/>StggIndexAction.jsp?mode=SHOWGGLIST&gglb=${gglb}&page=${page}" style="margin-left: 460px;"><img src="<z:path/>web/image/return.jpg" />返回上一级</a>
            </div>
            <input type="hidden" id="get_gglb" value="${gglb}"/>
            <div class="left">
                <div class="ggdh">
                    <p>公告导航</p>
                    <ul id="gglblist">
                        <c:forEach var="dggglb" items="${gglblist}">
                            <c:choose>
                                <c:when test="${dggglb.lbid==gglb}">
                                    <li style=" background: #999;"><a style="color:red;" href="<z:path/>StggIndexAction.jsp?mode=SHOWGGLIST&gglb=${dggglb.lbid}"><img src="<z:path/>web/image/add.png" style="margin-right: 6px;"/>${dggglb.lbmc}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                    <li style=" background: yellowgreen;"><a style=" color:black;" href="<z:path/>StggIndexAction.jsp?mode=SHOWGGLIST&gglb=${dggglb.lbid}"><img src="<z:path/>web/image/add.png" style="margin-right: 6px;"/>${dggglb.lbmc}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                    </ul>
                    <div style="border-bottom:1px dashed #cccccc;width:289px;margin-top: 10px;"></div>
                    <%--搜索--%>
                    <div class="search" >
                        <form method="post" action="<z:path/>StggIndexAction.jsp?mode=SHOWGGLIST" id="search_form">
                            <table>
                                <tr>
                                    <td ><input type="text" name="searchValue" id="search_value" class="seac_content" value="请输入搜索内容"  placeholder="请输入搜索内容"/></td>
                                    <td style="width:72px;">
                                        <div class="selectBox">
                                            <a  id="aa" style="border-bottom: none;padding-left: 10px;font-size:16px;">公告标题</a><span>
                                                <a  class="current" value="ggbt">公告标题</a>
                                                <a value="ggsj">发布时间</a>
                                            </span><input type="hidden" name="searchName" id="search_name" value="ggbt"/>
                                        </div>
                                    </td> 
                                    <td>
                                        <button onclick="search()" class="seac_submit">
                                            <strong style="font-family:'宋体'">搜索</strong>
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </form>
                        <span id="search_notice"></span>
                    </div>
                </div>
                <div class="lxwm">
                    <p>联系我们</p>
                    <div class="xx">
                        <ul>
                            <li>地址：<span>湖北省黄石市磁湖路11号</span></li>
                            <li>联系人：<span>湖师担当者志愿社</span></li>
                            <li>QQ：<span>1982084422</span></li>
                            <li>邮箱：<span>1982084422@qq.com</span></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="right">
                <%--以下为显示列表的div--%>
                <div id="showgglist" style="width:672px;height:550px;border:1px solid  #999;">
                    <c:choose>
                        <c:when test="${fn:length(gglist)>0}">
                            <div class="gglist">
                                <table id="gglist"  >
                                    <c:forEach var="gg" items="${gglist}">
                                        <tr ><td width="40"style="text-align: center;"><img src="<z:path/>web/image/jh.png"/></td><td width="540"><a href="javascript:onclick=showgg('${gg.ggid}','${gg.gglb}')">${gg.ggbt}</a></td><td ><a href="javascript:onclick=showgg('${gg.ggid}','${gg.gglb}')">${gg.ggsj}</a></td></tr>
                                            </c:forEach>
                                </table>
                            </div>
                            <%--翻页--%>
                            <div class="gg_fy">
                                <span class="fy_ys"><a href="<z:path/>StggIndexAction.jsp?mode=SHOWGGLIST&gglb=${gglb}&page=1">首页</a></span>
                                <span class="fy_ys"><a href="<z:path/>StggIndexAction.jsp?mode=SHOWGGLIST&gglb=${gglb}&page=${page<=1?1:page-1}">上一页</a></span>
                                <span class="fy_ys"><a href="<z:path/>StggIndexAction.jsp?mode=SHOWGGLIST&gglb=${gglb}&page=${page>=totalpages?totalpages:page+1}">下一页</a></span>
                                <span class="fy_ys"><a href="<z:path/>StggIndexAction.jsp?mode=SHOWGGLIST&gglb=${gglb}&page=${totalpages}">尾页</a></span>
                                第<span id="page" >${page}</span>/<span id="totalpages">${totalpages}</span>页
                                <span><input type="text" size="1" id="goto_page"/></span>
                                <span class="fy_ys" id="goto" onclick="gotoPage()">goto</span>
                            </div>  
                        </c:when>
                        <c:otherwise>
                            暂时没有相关信息
                        </c:otherwise>
                    </c:choose>
                </div>
                <div style="clear:both">
                </div>
                <%--以下为显示公告内容的iframe--%>
                <div style="width:668px;display: none;border:1px solid white;padding: 5px;" id="ggxx">
                    <iframe width="100%" name="ggxxIframe" scrolling="no" onload="dyniframesize(this)" id="ggxxIframe" frameborder="0" style="padding-bottom: 5px;">
                    </iframe>
                </div>
            </div>
            <div style="clear:both"></div>
        </div>
        <div style="width:991px;height:20px;float:left;"></div>
        <%@include  file="../public/bott.jsp" %>
    </body>
</html>
