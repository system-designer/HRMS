<%-- 
    Document   : index
    Created on : 2013-1-20, 10:34:00
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<!DOCTYPE html >
<html>
    <head>
        <jsp:include page="../public/include.jsp"></jsp:include>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="keywords" content="留言板，环保，绿源环保协会，湖北师范学院，Jplus实验室">
        <meta name="Description" content="这里是绿源环保协会的留言板，您可以在这里发表对绿源环保协会的意见或建议">
        <meta name="author" content="刘雷，裴秀">
        <link rel="icon" href="<z:path/>web/image/favicon.ico" type="image/x-icon" />
        <link rel="Shortcut Icon" type="image/x-icon" href="<z:path/>web/image/favicon.ico" />
        <link rel="stylesheet" type="text/css"href="<z:path/>web/css/lywm.css"/>
        <script type="text/javascript" src="<z:path/>web/lywm/lywm.js"></script>
        <title>留言我们</title>
        <script type="text/javascript">
            var path='<z:path/>';
            
        </script>
    </head>
    <body>
        <%@include  file="../public/head.jsp" %>
        <div class="main">
        <div class="wz">
            <span>当前位置</span>
            <a href="<z:path/>IndexAction.jsp?mode=SHOWLIST">主页</a>>>
            <label ><a href="<z:path/>LywmIndexAction.jsp?mode=SHOWLIST">留言我们</a></label>
        </div>
        <div id="main">
            <div id="content">
            <c:forEach var="ly" items="${lylist}">
                <div class="bk">
                    <div class="bk_left"></div>
                    <!--<div style="clear:both;"></div>-->
                    <div class="bk_right">
                        <div class="imgs"><img src="<z:path/>web/image/sj.png" /></div>
                        <ul >
                            <li class="bt"><a href="#">${ly.lyr}</a>&nbsp;&nbsp;${ly.lysj}<span class="dw"><a href="#">送鲜花</a>&nbsp;<a href="#">扔鸡蛋</a>&nbsp;<a href="#">收藏</a></span></li>
                            <li class="nr">${ly.lynr}</li>
                            <li class="hfnr">
                                <c:choose>
                                    <c:when test="${ly.hfnr!=null&&ly.hfnr!=''}">
                                           --------------<img src="<z:path/>web/image/online_admin.gif" style="margin-right: 8px;"/>
                                     管理员回复--------------${ly.hfsj}
                                    </c:when>
                                    <c:otherwise>
                                        暂无回复
                                    </c:otherwise>
                                </c:choose>
                            </li>
                            <li class="nr" >${ly.hfnr}</li><!--
-->                            <li class="sm">标签：<a>学习</a><a>娱乐</a></li>
                        </ul>
                       
                    </div>
                             <div style="clear:both;"></div>
                </div>
            </c:forEach>
            <!--分页-->
            <div class="hd_fy">
                <span class="fy_ys"><a href="<z:path/>LywmIndexAction.jsp?mode=SHOWLIST&page=1">首页</a></span>
                <span class="fy_ys"><a href="<z:path/>LywmIndexAction.jsp?mode=SHOWLIST&page=${page<=1?1:page-1}">上一页</a></span>
                <span class="fy_ys"><a href="<z:path/>LywmIndexAction.jsp?mode=SHOWLIST&page=${page>=totalpages?totalpages:page+1}">下一页</a></span>
                <span class="fy_ys"><a href="<z:path/>LywmIndexAction.jsp?mode=SHOWLIST&page=${totalpages}">尾页</a></span>
                第<span id="page">${page}</span>/<span id="totalpages">${totalpages}</span>页
                <span><input type="text" size="1" id="goto_page"/></span>
                <span class="fy_ys" id="goto" onclick="gotoPage()">goto</span>
            </div>
                <div style="clear:both;"></div>
</div>
            <!--言论发表-->
            <div id="ylfb">
                <div id="fbxx1">此处发表留言</div>
                <div id="fbxx2"></div>
                <div id="fbxx3" style="">
                    <form id="lyform" method="post" action="<z:path/>LywmIndexAction.jsp?mode=ADD">
                        用&nbsp;&nbsp;户&nbsp;&nbsp;名：<input type="text" class="inp" name="lyr"/><br/>
                        发表内容：<br/>
                        <textarea rows="7" cols="50" id="wbk" name="lynr" style="resize: none;"></textarea><br/>
                        验&nbsp;&nbsp;证&nbsp;&nbsp;码：<input type="text"  size="10"  id="code" name="code" style=" width:100px; height:25px; margin-right:10px;margin-left: 30px;"/> <img id="Image1" src="<z:path/>web/public/image.jsp" style="cursor:pointer" onmouseup="RefreshImage()" title="看不清请点击更新"/>
                        <span id="codenotice" style="color:red;"></span><br/>
                        <input type="button" value="提交" class="bt1" onclick="submitLy()"/>
                        <!--                            //图片中的src可以是jsp-->
                        <input type="reset" value="撤销" class="bt2"/>
                    </form>
                </div>
                        <div style="clear:both;"></div>
            </div>
        </div>
                 </div>
        <div style="height:2px;"></div>
        <%@include  file="../public/bott.jsp" %>
    </body>
</html>

