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
        <title>${host.yhm}的个人空间</title>
    </head>
    <body>
        <%@include  file="../blog/blog_header.jsp" %> 
        <div class="middle">
            <div class="left">
                <div class="picture">
                    <p>头像</p>
                    <img src="<z:path/>userfiles${host.yhtx}" style="width:117px;height:120px;"/><br/>
                    <span>${host.yhm}</span>
                    <div class="information">
                        <ul>
                            <li><a href="#">装扮空间</a></li>
                            <li><a href="#">查看留言</a></li>
                            <li><a href="#">编辑头像</a></li>
                            <li><a href="<z:path/>BlogYhbServlet.jsp?mode=SHOWINFO&host=${host.id}">更新资料</a></li>
                        </ul>
                    </div>
                    <div class="management">用户管理</div>
                </div>
                <div class="statistic">
                    <p>统计信息</p>
                    <div class="guest">已有<span class="num">3</span>人来访过</div>
                    <div class="information">
                        <ul>
                            <li>积分：<span>--</span></li>
                            <li>经验：<span>--</span></li>
                            <li>威望：<span>--</span></li>
                            <li>金币：<span>--</span></li>
                            <li>好友：<span>--</span></li>
                            <li>日志：<span>--</span></li>
                            <li>相册：<span>--</span></li>
                            <li>分享：<span>--</span></li>
                            <li>担当：<span>--</span></li>
                        </ul>
                    </div>
                </div>
                <div class="photo">
                    <p>相册</p>
                    <div>现在还没有相册</div>
                </div>
                <div class="history">
                    <p>记录</p>
                    <div>现在还没有记录</div>
                </div>
            </div>
            <div class="center">
                <div class="data">
                    <p>个人资料</p>
                    <div class="xinxi">
                        <ul>
                            <li>性别:${host.xb}</li> 
                            <li>生日:${host.csrq}</li>
                        </ul>
                    </div>
                    <div class="clearIn">
                        <a href="#"> 查看全部个人资料</a>
                    </div>
                </div>
                <div class="data">
                    <p>动态</p>
                    <div class="data1">
                        <c:choose>
                            <c:when test="${hostpost==null}">
                                现在还没有动态
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="post" items="${hostpost}">
                                    【${post.lbmc}】${post.tzbt}------${fn:substring(post.ftsj, 0, 10)}<br/>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="data">
                    <p>分享</p>
                    <div class="data1">
                        <c:choose>
                            <c:when test="${hostlog==null}">
                                现在还没有分享
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="bloglog" items="${hostlog}">
                                    ${bloglog.title}------------${fn:substring(bloglog.pubtime,0,10)}<br/>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="data">
                    <p>日志</p>
                    <div class="data1">
                        <c:choose>
                            <c:when test="${hostlog==null}">
                                现在还没有日志
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="bloglog" items="${hostlog}">
                                    ${bloglog.title}------------${fn:substring(bloglog.pubtime,0,10)}<br/>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="data">
                    <p>留言板<span class="span1">全部</span></p>
                    <div class="board">
                        <c:choose>
                            <c:when test="${sessionScope.yhb==null}">
                                <div class="textarea1" >
                                    <div>你需要登录后才可以留言&nbsp;<span><a href="<z:path/>web/login/login.jsp?forward=BlogIndexServlet.jsp?mode=SHOWALLANDyhid=${host.id}">登录</a></span>|<span><a href="<z:path/>web/login/register.jsp?forword=\"BlogIndexServlet.jsp?mode=SHOWALL&host=${host.id}\"">点击注册</a></span></div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <textarea class="blog_leave_textarea"></textarea>
                            </c:otherwise>
                        </c:choose>
                        <span class="liuyan">留言</span>
                    </div>
                    <ul class="tishi">
                        <c:choose>
                            <c:when test="${hostleave==null}">
                                现在没有信息
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="leave" items="${hostleave}">
                                    <li>
                                        <div class="blog_index_ul1_left"><img src="<z:path/>userfiles${leave.yhtx}" style="width:60px;height:60px;"/></div>
                                        <div class="blog_index_ul1_right"><div  class="blog_index_ul1_right_left"><span><a href="#">${leave.yhm}:</a> ${leave.leavetime}</span></div><br/>
                                            <div class="blog_index_ul1_nr">${leave.content}</div>
                                        </div>
                                        <div style="clear:both"></div>

                                    </li>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
            <div class="right">
                <div class="friend">
                    <p>好友</p>
                    <div class="tupian">
                        <ul>
                            <c:choose>
                                <c:when test="${hostfriend==null}">
                                    TA还没有好友哦
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="friend" items="${hostfriend}">
                                        <li><img src="<z:path/>userfiles${friend.yhtx}" style="width:60px;height:60px;"><br/><a href="#">${friend.yhm}</a></li>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
<!--                            <li><img src="<z:path/>web/image/ren.png" style="width:60px;height:60px;"><br/><a href="#">22</a></li>
                    <li><img src="<z:path/>web/image/ren.png" style="width:60px;height:60px;"><br/><a href="#">22</a></li>
                    <li><img src="<z:path/>web/image/ren.png" style="width:60px;height:60px;"><br/><a href="#">22</a></li>-->
                        </ul>
                        <div style="clear:both;"></div>
                    </div>
                </div>
                <div class="friend">
                    <p>最近访客</p>
                    <div class="tupian">
                        <ul>
                            <c:choose>
                                <c:when test="${hostfriend==null}">
                                    你是第一位访客哦
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="visit" items="${hostvisit}">
                                        <li><img src="<z:path/>userfiles${visit.yhtx}" style="width:60px;height:60px;"><br/><a href="#">${visit.yhm}</a><br/><span>${visit.visittime}</span></li>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            <!--<li><img src="<z:path/>web/image/ren.png" style="width:60px;height:60px;"><br/><a href="#">22</a><br/><span>2013-12</span></li>-->
                        </ul>
                        <div style="clear:both;"></div>
                    </div>
                </div>
                <div class="friend">
                    <p>群组</p>
                    <div class="qunzu">现在还没有群组</div>
                </div>
            </div>
            <div style="clear:both;"></div>
        </div>
        <%@include  file="../public/bott.jsp" %> 
    </body>
</html>
