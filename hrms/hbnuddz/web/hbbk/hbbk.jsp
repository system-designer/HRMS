<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : wyhb
    Created on : 2013-1-21, 20:47:50
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
        <meta name="keywords" content="环保百科">
        <meta name="Description" content="介绍关于环保的百科知识，包括环保新闻，环保知识，身边环保事，环保微课堂">
        <meta name="author" content="刘雷，裴秀">
        <link rel="icon" href="<z:path/>web/image/favicon.ico" type="image/x-icon" />
        <link rel="Shortcut Icon" type="image/x-icon" href="<z:path/>web/image/favicon.ico" />
        <link rel="stylesheet" type="text/css"href="<z:path/>web/css/hbbk.css"/>
        <script type="text/javascript" src="<z:path/>web/js/hbbk.js"></script>
        <script type="text/javascript" src="<z:path/>web/hbbk/hbbk.js"></script>
        <title>公益百科</title>
        <script type="text/javascript">
            var path ='<z:path/>';
            var bkid='${bkid}';
            var bklb='${bklb}';
        </script>
    </head>
    <body>
        <%@include  file="../public/head.jsp" %>
        <div class="main">
            <input type="hidden" id="get_bklb" value="${bklb}"/>
            <div class="wz">
                <span>当前位置</span>
                <a href="<z:path/>IndexAction.jsp?mode=SHOWLIST">主页</a>>>
                <label style="color:red;"><a href="<z:path/>HbbkIndexAction.jsp?mode=SHOWBKLIST">环保百科</a></label>
                <lable id="third">>><a href="<z:path/>HbbkIndexAction.jsp?mode=SHOWBKLIST&bklb=${bklb}&page=${page}">${lbmc}</a></lable>
                <a id="ret" href="<z:path/>HbbkIndexAction.jsp?mode=SHOWBKLIST&bklb=${bklb}&page=${page}" style="margin-left: 450px;"><img src="<z:path/>web/image/return.jpg" />返回上一级</a>
            </div>
            <div style="clear:both"></div>
            <div class="left">
                <div class="wyhb1">
                    <p>我与公益</p>
                    <ul>
                        <c:forEach var="dgbklb" items="${bklblist}">
                            <c:choose>
                                <c:when test="${dgbklb.lbid==bklb}">
                                    <li style=" background: #999;"><a style="color:red;" href="<z:path/>HbbkIndexAction.jsp?mode=SHOWBKLIST&bklb=${dgbklb.lbid}"><img src="<z:path/>web/image/add.png" style="margin-right: 6px;" />${dgbklb.lbmc}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                    <li style=" background: yellowgreen;"><a style="color:black;" href="<z:path/>HbbkIndexAction.jsp?mode=SHOWBKLIST&bklb=${dgbklb.lbid}"><img src="<z:path/>web/image/add.png" style="margin-right: 6px;" />${dgbklb.lbmc}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                    </ul>
                    <div style="border-bottom:1px dashed #cccccc;width:289px;margin-top: 10px;"></div>
                    <%--搜索--%>
                    <div class="search" >
                        <form method="post" action="<z:path/>SthdIndexAction.jsp?mode=SHOWHDLIST" id="search_form">
                            <table>
                                <tr>
                                    <td ><input type="text" name="searchValue" id="search_value" class="seac_content" value="请输入搜索内容"  placeholder="请输入搜索内容"/></td>
                                    <td style="width:102px;">
                                        <div class="selectBox">
                                            <a  id="aa" style="border-bottom: none;padding-left: 10px;font-size:16px;">标题</a><span>
                                                <a  class="current" value="bkbt">标题</a>
                                                <a value="bksj">时间</a>
                                            </span><input type="hidden" name="searchName" id="search_name" value="bkbt"/>
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
                <img src="<z:path/>web/image/hb.jpg" style="margin-top: 15px;"/>
            </div>
            <div class="right">
                <div id="showbklist" style="width:672px;height:610px;border:1px solid  #999;">
                    <c:choose>
                        <c:when test="${fn:length(bklist)>0}">
                            <div class="bklist">
                                <ul id="bklist">
                                    <c:forEach var="bk" items="${bklist}">
                                        <li  style=""><img src="<z:path/>web/image/jh.png" style="float:left"/><a href="javascript:onclick=showbk('${bk.bkid}','${bk.bklb}')" style="float:left">${bk.bkbt}</a> <span style="float:right;margin-right: 5px;">${bk.bksj}</span></li>                                    
                                        </c:forEach>
                                </ul>
                            </div>
                            <%--翻页--%>
                            <div class="bk_fy">
                                <span class="fy_ys"><a href="<z:path/>HbbkIndexAction.jsp?mode=SHOWBKLIST&bklb=${bklb}&page=1">首页</a></span>
                                <span class="fy_ys"><a href="<z:path/>HbbkIndexAction.jsp?mode=SHOWBKLIST&bklb=${bklb}&page=${page<=1?1:page-1}">上一页</a></span>
                                <span class="fy_ys"><a href="<z:path/>HbbkIndexAction.jsp?mode=SHOWBKLIST&bklb=${bklb}&page=${page>=totalpages?totalpages:page+1}">下一页</a></span>
                                <span class="fy_ys"><a href="<z:path/>HbbkIndexAction.jsp?mode=SHOWBKLIST&bklb=${bklb}&page=${totalpages}">尾页</a></span>
                                第<span id="page">${page}</span>/<span id="totalpages">${totalpages}</span>页
                                <span><input type="text" size="1" id="goto_page"/></span>
                                <span class="fy_ys" id="goto" onclick="gotoPage()">goto</span>
                            </div>  
                        </c:when>
                        <c:otherwise>
                            暂时没有相关信息
                        </c:otherwise>
                    </c:choose>
                </div>
                <%--以下为显示百科内容的iframe--%>
                <div style="width:668px;display: none;border:1px solid #cccccc;padding-left: 5px;" id="bkxx">
                    <iframe width="100%" name="bkxxIframe"  scrolling="no" onload="dyniframesize(this)" id="bkxxIframe" frameborder="0">
                    </iframe>
                </div>
            </div>
            <div style="clear:both"></div>
        </div>
        <div style="width:991px;height:20px;float:left;"></div>
        <%@include  file="../public/bott.jsp" %>   
    </body>
</html>
