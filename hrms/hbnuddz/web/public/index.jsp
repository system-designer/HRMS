<%-- 
    Document   : index
    Created on : 2013-1-20, 10:34:00
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="include.jsp"></jsp:include>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="keywords" content="湖北师范学院绿源环保协会，Jplus实验室">
        <meta name="Description" content="这里是湖北师范学院绿源环保协会网站首页，对于环保，我们能做的还有很多">
        <meta name="author" content="刘雷，裴秀">
        <meta property="qc:admins" content="053106456763360265654563670510516" />
        <link rel="icon" href="<z:path/>web/image/favicon.ico" type="image/x-icon" />
        <link rel="Shortcut Icon" type="image/x-icon" href="<z:path/>web/image/favicon.ico" />
        <link rel="stylesheet" type="text/css" href="<z:path/>web/css/index.css"/>
        <script type="text/javascript" src="<z:path/>web/js/jsMarquee.js"></script>
        <script type="text/javascript" src="<z:path/>web/js/tupian.js"></script>
        <script type="text/javascript">
            var path='<z:path/>';
        </script>
        <title>湖师担当者志愿社</title>
    </head>
    <body>
        <%@include  file="head.jsp" %>
        <div class="main">
            <div class="left">
                <div class="jcsj">
                    <p><img src="<z:path/>web/image/12.png"style="float:left;margin-left: 8px;height:25px;width:25px;"  />精彩瞬间</p>
                    <div class="jcsj1">
                        <div id="jcsj" style="position:relative;overflow:hidden;width:298px;height:240px;clear:none;border:1px solid white;"></div>
                    </div>   
                </div>
                <div class="hbmh">
                    <p>公益漫画</p>
                    <img src="<z:path/>userfiles/${hbmh}" style="width:298px;height:222px;"/>
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
            <div class="cen">
                <div class="cen1">
                    <p>
                        <img src="<z:path/>web/image/stgg.jpg"style="float:left;margin-left: 8px;width:28px;height:28px;"  /> 
                        志愿社公告  <a target="_blank" href="<z:path/>StggIndexAction.jsp?mode=SHOWGGLIST"><img src="<z:path/>web/image/more.png"/></a>
                    </p>
                    <table>
                        <c:forEach var="gg" items="${gglist}">
                            <c:choose>
                                <c:when test="${fn:length(gglist)>8}">
                                    <tr><td><img src="<z:path/>web/image/jh.png"/><td width="300"><a target="_blank" href="<z:path/>StggIndexAction.jsp?mode=SHOWGGLIST&gglb=${gg.gglb}&ggid=${gg.ggid}&index=index">${fn:substring(gg.ggbt, 0, 8)}...</a></td><td style="color:#cccccc;">[${gg.ggsj}]</td></tr>
                                        </c:when>
                                        <c:otherwise>
                                    <tr><td><img src="<z:path/>web/image/jh.png"/><td width="300"><a target="_blank" href="<z:path/>StggIndexAction.jsp?mode=SHOWGGLIST&gglb=${gg.gglb}&ggid=${gg.ggid}&index=index">${gg.ggbt}</a></td><td style="color:#cccccc;">[${gg.ggsj}]</td></tr>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                    </table>
                </div>
                <div class="cen2">
                    <p>
                        <img src="<z:path/>web/image/sthd.jpg"style="float:left;margin-left: 8px;"  />志愿社活动  
                        <a target="_blank" href="<z:path/>SthdIndexAction.jsp?mode=SHOWHDLIST"><img src="<z:path/>web/image/more.png"/></a>
                    </p>
                    <table>
                        <c:forEach var="hd" items="${hdlist}">
                            <c:choose>
                                <c:when test="${fn:length(gglist)>8}">
                                    <tr><td><img src="<z:path/>web/image/jh.png"/><td width="300" ><a target="_blank" href="<z:path/>SthdIndexAction.jsp?mode=SHOWHDLIST&hdlb=${hd.hdlb}&hdid=${hd.hdid}&index=index">${fn:substring(hd.hdbt,0,8)}...</a></td><td style="color:#cccccc; ">[${hd.hdsj}]</td></tr>
                                        </c:when>
                                        <c:otherwise>
                                    <tr><td><img src="<z:path/>web/image/jh.png"/><td width="300" ><a target="_blank" href="<z:path/>SthdIndexAction.jsp?mode=SHOWHDLIST&hdlb=${hd.hdlb}&hdid=${hd.hdid}&index=index">${hd.hdbt}</a></td><td style="color:#cccccc; ">[${hd.hdsj}]</td></tr>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                    </table>
                </div>
                <div class="cen3">
                    <p>
                        <img src="<z:path/>web/image/xw.jpg"style="float:left;margin-left: 8px;"  /> 公益新闻 
                        <a target="_blank" href="<z:path/>HbbkIndexAction.jsp?mode=SHOWBKLIST"><img src="<z:path/>web/image/more.png"/></a>  
                    </p>
                    <table>
                        <c:forEach var="hbxw" items="${hbxwlist}">
                            <c:choose>
                                <c:when test="${fn:length(gglist)>8}">
                                    <tr><td><img src="<z:path/>web/image/jh.png"/><td width="300"><a target="_blank" href="<z:path/>HbbkIndexAction.jsp?mode=SHOWBKLIST&bklb=${hbxw.bklb}&bkid=${hbxw.bkid}&index=index">${fn:substring(hbxw.bkbt,0,8)}...</a></td><td style="color:#cccccc;">[${hbxw.bksj}]</td></tr>
                                        </c:when>
                                        <c:otherwise>
                                    <tr><td><img src="<z:path/>web/image/jh.png"/><td width="300"><a target="_blank" href="<z:path/>HbbkIndexAction.jsp?mode=SHOWBKLIST&bklb=${hbxw.bklb}&bkid=${hbxw.bkid}&index=index">${hbxw.bkbt}</a></td><td style="color:#cccccc;">[${hbxw.bksj}]</td></tr>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                    </table>
                </div>
            </div>
            <div class="right">
                <div class="lyczrj">
                    <p>
                        <img src="<z:path/>web/image/95.gif" style="float:left;margin-left: 8px;"/>担当者成长日记</p>
                    <div class="rj" style="overflow: hidden;">
                        <MARQUEE onmouseover=this.stop() onmouseout=this.start() bgcolor="#cccccc"scrollAmount=1 scrollDelay=40 behavior="scroll"direction=up>
                            <span> 1、环保教育 走进小学：</span>2012年3月9日，湖北师范学院绿源环保协会联合湖北理工学院在黄石市环保局的领导下，在黄石袁家畈小学开展了环保知识进课堂的活动，利用环保知识宣传、变废为宝等各种方法向学生们灌输环保的理念，旨在让学生从小就养成环保的好习惯。<br/>
                            <span>2、绿色文明寝室：</span>2012年3月12日——3月27日，湖北师范学院绿源环保协会连同全校各大院系学生会劳生部共同开展“绿色文明寝室”评比活动，旨在为宣传环保理念，增强大学生的环境保护意识。
                        </MARQUEE>
                    </div>
                </div>
                <div class="hbcy">
                    <p>公益创意</p>
                    ${hbcy.nr}
                </div>
                <div class="hbzn">
                    <p>公益指南</p>
                    ${hbzn.nr}
                </div>
            </div>
            <div class="hdyj">
                <p>活动影集<a href="<z:path/>HdjyIndexAction.jsp?mode=SHOWHDLIST"><img src="<z:path/>web/image/more.png"/></a>  </p>
                <div class="marquee">
                    <ul>
                        <c:forEach var="hdtp" items="${hdtplist}">
                            <li><a href="<z:path/>HdjyIndexAction.jsp?mode=SHOWHDLIST&hdlb=${hdtp.hdlb}&hdid=${hdtp.hdid}&index=index"><img src="<z:path/>userfiles${hdtp.hdtp}" alt="${hdtp.hdbt}"/><em>${hdtp.hdbt}</em></a></li>
                                </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <div style="width:991px;height:20px;float:left;"></div>
        <%@include  file="bott.jsp" %>
        <div style="width:991px;height:20px;float:left;"></div>
    </body>
</html>
