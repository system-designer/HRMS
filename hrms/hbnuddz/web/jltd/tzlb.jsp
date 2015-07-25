<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : tzlb
    Created on : 2013-3-6, 21:13:01
    Author     : Ariel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html >
<html>
    <head>
        <jsp:include page="../public/include.jsp"></jsp:include>
        <meta name="keywords" content="环保，论坛，校园，Jplus实验室">
        <meta name="Description" content="用来交流环保的最新动态，和个人对环保的看法，还可以聊聊校园里的故事">
        <meta name="author" content="刘雷，裴秀">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="<z:path/>web/image/favicon.ico" type="image/x-icon" />
        <link rel="Shortcut Icon" type="image/x-icon" href="<z:path/>web/image/favicon.ico" />
        <link rel="stylesheet" href="<z:path/>web/css/tzlb.css"/>
        <script type="text/javascript" src="<z:path/>ckeditor/ckeditor.js"></script>
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/jquery-1.7.1.min.js" ></script>
        <script type="text/javascript" src="<z:path/>web/jltd/tzlb.js" ></script>
        <script type="text/javascript">
            var path='<z:path/>';
            var editor="";
            var bkid='${bkid}';
            var bkmc='${bkmc}';
            /**
             * 发帖之前的验证与editor的显示
             */
            function ft(){
                if(${sessionScope.yhb==null}){
                    alert("您好,请登录后再发帖!");
                    return;
                }
                $("#all").hide();
                $("#editordiv").show();
            }
        </script>
        <title>交流论坛</title>
    </head>
    <body>
        <%@include  file="../public/head.jsp" %>

        <div class="main">
            <div class="wz" style="">

                <span>当前位置</span>
                <a href="<z:path/>IndexAction.jsp?mode=SHOWLIST">主页</a>>>
                <label ><a href="<z:path/>JltdIndexAction.jsp?mode=SHOWLIST">交流论坛</a></label>

                <c:choose>
                    <c:when test="${cxtzbt!=null}">
                        <lable id="third" >>>"${cxtzbt}"的搜索结果</lable>
                        <a href="<z:path/>JltdIndexAction.jsp?mode=SHOWLIST" style="margin-left: 420px;"><img src="<z:path/>web/image/return.jpg" />返回上一级</a>
                        </c:when>
                        <c:otherwise>

                        <lable id="third" >>><a href="<z:path/>TzlbIndexAction.jsp?mode=SHOWLIST&bkid=${bkid}&page=${page}" >${bkmc}</a></lable>
                        <a href="<z:path/>JltdIndexAction.jsp?mode=SHOWLIST" style="margin-left: 420px;"><img src="<z:path/>web/image/return.jpg" />返回上一级</a>
                        </c:otherwise>
                    </c:choose>


            </div>
            <div style="clear:both;"></div>
            <div  id="all">
                <div class="cx">
                    <span class="ft1" onclick="ft()">
                        发帖
                    </span>
                    <label >请输入帖子标题或发帖人姓名<input type="text" name="cxtzbt" id="cxtzbt"/> <span class="cx1" onclick="cxtz()">查询</span></label>
                </div>

                <div class="px" style="">
                    <ul>
                        <li style="line-height: 50px;"><div class="px1">排序：<label><span>全部</span><span>求助</span><span>已答复</span><span>已回应</span><span>已解决</span></label></div><div class="zz">作者</div><div class="hf">回复/查看</div><div class="zhfb">最后发表</div></li>
                        <c:choose>
                            <c:when test="${fn:length(tzlist)>0}">
                                <c:forEach var="tz" items="${tzlist}">
                                    <li>
                                        <div class="px1" >
                                            <a href="<z:path/>TznrIndexAction.jsp?mode=SHOWONE&bkid=${tz.bkid}&ftid=${tz.ftid}" style="float:left;"><img src="<z:path/>web/image/3.png"/>
                                                ${tz.tzbt}  
                                                <c:if test="${tz.sfzd==true}">
                                                    <img src="<z:path/>web/image/zding.gif"/>
                                                </c:if>
                                                <c:if test="${tz.sfjj==true}">
                                                    <img src="<z:path/>web/image/jing.gif"/>
                                                </c:if>
                                            </a>
                                        </div>
                                        <div class="zz"><span class="s1">${tz.yhm}</span><br/><span class="s2">${tz.ftsj}</span></div>
                                        <div class="hf"><span class="s3">${tz.htzs}</span><br/><span class="s2">${tz.llcs}</span></div>
                                        <div class="zhfb"><span class="s1">${tz.htyh}</span><br/><span class="s2">${tz.zhhtsj}</span></div>

                                    </li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <li>对不起,该板块暂时没有主题</li>
                            </c:otherwise>
                        </c:choose>

                    </ul>
                    <div style="clear:both"></div>
                </div>

                <div class="fy">
                    <span class="ft" onclick="ft()">
                        发帖
                    </span>

                    <c:if test="${fn:length(tzlist)>0}">
                        <label style="height:30px;">
                            <span><a href="<z:path/>TzlbIndexAction.jsp?mode=SHOWLIST&bkid=${bkid}&page=1">首页</a></span>
                            <span><a href="<z:path/>TzlbIndexAction.jsp?mode=SHOWLIST&bkid=${bkid}&page=${page<=1?1:page-1}">上一页</a></span>
                            <span><a href="<z:path/>TzlbIndexAction.jsp?mode=SHOWLIST&bkid=${bkid}&page=${page>=totalpages?totalpages:page+1}">下一页</a></span>
                            <span><a href="<z:path/>TzlbIndexAction.jsp?mode=SHOWLIST&bkid=${bkid}&page=${totalpages}">尾页</a></span>
                            第<span id="page" style="background:white;border:1px solid white">${page}</span>/<span id="totalpages" style="background:white;border:1px solid white">${totalpages}</span>页
                            <input type="hidden" value="${bkid}" id="get_bkid"/>
                            <input type="text" size="2" id="goto_page" style=""/>
                            <span  class="sc"style="margin-left: 0px;padding: 5px 13px 5px 13px;" onclick="gotoPage()">goto</span>

                        </label>

                    </c:if>

                </div>
            </div>
            <div id="editordiv" style="display: none;resize: none;">
                <form method="post" id="ftform" action="<z:path/>TzlbIndexAction.jsp?mode=FT" >
                    <label style="float:left;margin-left: 4px;">请输入标题：<input type="text" id="tzbt" name="tzbt" style="width:150px;"/>   </label>   
                    <label style="float:left;margin-left: 40px;">请选择板块:
                        <select id="bkid" name="bkid">
                            <c:forEach var="bk" items="${bklist}">
                                <option value="${bk.bkid}">${bk.bkmc}</option>
                            </c:forEach>
                        </select>
                    </label>    
                    <label style="float:left;margin-left: 40px; "> 
                        验证码：
                        <input type="text"  size="10"  id="code" name="code" style=" width:100px; height:25px; margin-left: 10px;"/> 
                        <img id="Image1" src="<z:path/>web/public/image.jsp" style="cursor:pointer ;" onmouseup="RefreshImage()" title="看不清请点击更新"/>
                    </label>  <span id="codenotice" style="width:150px;height:25px;float:left;margin-left: 20px;color:red;"></span> <br/> <br/> 
                    <div style="clear:both"></div>
                    <textarea id="tznr" name="tznr"></textarea>
                    <div style="height:35px;line-height: 35px;float:right;">
                        <label style="float: right;">
                            <input type="button" value="提交" onclick="submitForm()" style="padding-left: 5px;padding-top: 5px;padding-right: 5px;border:1px solid #cccccc;margin-right: 20px;"/>
                            <input type="reset" value="重置"  style="padding-left: 5px;padding-right: 5px;padding-top: 5px;border:1px solid #cccccc;margin-right: 20px;"/>
                            <input type="button" value="取消" onclick="qxft()" style="padding-left: 5px;padding-right: 5px;padding-top: 5px;border:1px solid #cccccc;margin-right: 20px;"/>
                        </label>
                    </div>
                    <div style="clear:both"></div>
                    <div style="height:10px;"></div>
                </form>
            </div>
        </div>
        <div style="height:10px;"></div>
        <%@include  file="../public/bott.jsp" %>
    </body>
</html>
