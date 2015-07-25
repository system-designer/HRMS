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
        <jsp:include page="../public/include.jsp"></jsp:include>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="keywords" content="社团活动影集">
        <meta name="Description" content="介绍湖北师范学院绿源环保协会社团活动信息，主要是活动的影集">
        <meta name="author" content="刘雷，裴秀">
        <link rel="icon" href="<z:path/>web/image/favicon.ico" type="image/x-icon" />
        <link rel="Shortcut Icon" type="image/x-icon" href="<z:path/>web/image/favicon.ico" />
        <link rel="stylesheet" type="text/css"href="<z:path/>web/css/hdjj.css"/>
        <script type="text/javascript" src="<z:path/>web/hdjy/hdjy.js"></script>
        <title>活动剪影</title>
        <script type="text/javascript">
            var path='<z:path/>';
            var hdid='${hdid}';
        </script>
    </head>
    <body>
        <%@include  file="../public/head.jsp" %>
        <div class="main">
            <input type="hidden" id="get_hdlb" value="${hdlb}"/>
            <div class="wz">
                <span>当前位置</span>
                <a href="<z:path/>IndexAction.jsp?mode=SHOWLIST">主页</a>>>
                <label style="color:red;"><a href="<z:path/>HdjyIndexAction.jsp?mode=SHOWHDLIST">活动剪影</a></label>
                <lable id="third">>><a href="<z:path/>HdjyIndexAction.jsp?mode=SHOWHDLIST&hdlb=${hdlb}&page=${page}">${lbmc}</a></lable>
                <a id="ret" href="<z:path/>HdjyIndexAction.jsp?mode=SHOWHDLIST&hdlb=${hdlb}&page=${page}" style="margin-left: 460px;"><img src="<z:path/>web/image/return.jpg" />返回上一级</a>
            </div>
            <div style="clear:both"></div>
            <div class="left">
                <div class="tpfl">
                    <p>活动导航</p>
                    <ul>
                        <c:forEach var="dghdlb" items="${hdlblist}">
                            <c:choose>
                                <c:when test="${dghdlb.lbid==hdlb}">
                                    <li style=" background: #999;"><a style="color:red;" href="javascript:onclick=changeHdlb('${dghdlb.lbid}')"><img src="<z:path/>web/image/add.png" style="margin-right: 6px;"/>${dghdlb.lbmc}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                    <li style=" background: yellowgreen;"><a style="color:black;" href="javascript:onclick=changeHdlb('${dghdlb.lbid}')"><img src="<z:path/>web/image/add.png" style="margin-right: 6px;"/>${dghdlb.lbmc}</a></li>
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
                                            <a  id="aa" style="border-bottom: none;padding-left: 10px;font-size:16px;">活动主题</a><span>
                                                <a  class="current" value="hdbt">活动主题</a>
                                                <a value="hdsj">活动时间</a>
                                            </span><input type="hidden" name="searchName" id="search_name" value="hdbt"/>
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
                <img src="<z:path/>web/image/hb1.jpg"style="margin-top: 10px;"/>
            </div>
            <div class="right">
                <div id="hdtplist" class="hdtp">
                    <c:choose>
                        <c:when test="${fn:length(hdlist)>0}">
                            <div class="hdlist">
                                <ul>
                                    <c:forEach var="hd" items="${hdlist}">
                                        <li><a href="javascript:onclick=showjynr('${hd.hdid}')"><img src="<z:path/>userfiles${hd.hdtp}" alt="${hd.hdbt}"/><em>${hd.hdbt}</em></a></li>
                                            </c:forEach>
                                </ul>
                            </div>
                            <!--翻页-->
                            <div class="hd_fy">
                                <span class="fy_ys"><a href="<z:path/>HdjyIndexAction.jsp?mode=SHOWHDLIST&hdlb=${hdlb}&page=1">首页</a></span>
                                <span class="fy_ys"><a href="<z:path/>HdjyIndexAction.jsp?mode=SHOWHDLIST&hdlb=${hdlb}&page=${page<=1?1:page-1}">上一页</a></span>
                                <span class="fy_ys"><a href="<z:path/>HdjyIndexAction.jsp?mode=SHOWHDLIST&hdlb=${hdlb}&page=${page>=totalpages?totalpages:page+1}">下一页</a></span>
                                <span class="fy_ys"><a href="<z:path/>HdjyIndexAction.jsp?mode=SHOWHDLIST&hdlb=${hdlb}&page=${totalpages}">尾页</a></span>
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
                <!--以下为显示剪影内容的iframe-->
                <div style="width:668px;display: none;border:1px solid #cccccc;margin-top: 10px;" id="jyxx">
                    <iframe width="100%" name="jyxxIframe"  scrolling="no" onload="dyniframesize(this)" id="jyxxIframe" frameborder="0">
                    </iframe>
                </div>
            </div>
            <div style="clear:both;"></div>
        </div>
        <div style="width:991px;height:20px;float:left;"></div>
        <%@include  file="../public/bott.jsp" %>
    </body>
</html>
