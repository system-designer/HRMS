<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : nr
    Created on : 2013-3-7, 20:37:41
    Author     : Ariel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../public/include.jsp"></jsp:include>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="keywords" content="环保，论坛，校园，Jplus实验室">
        <meta name="Description" content="用来交流环保的最新动态，发帖表达个人对环境保护的看法，或者对绿源环保协会的意见">
        <meta name="author" content="刘雷，裴秀">
        <link rel="icon" href="<z:path/>web/image/favicon.ico" type="image/x-icon" />
        <link rel="Shortcut Icon" type="image/x-icon" href="<z:path/>web/image/favicon.ico" />
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="<z:path/>ckeditor/ckeditor.js"></script>
        <script type="text/javascript" src="<z:path/>web/jltd/nr.js"></script>
        <link rel="stylesheet" href="<z:path/>web/css/nr.css"/>
        <script type="text/javascript">
            var path='<z:path/>';
            var editorft="";//发帖的editor
            var editorht="";//回帖的editor
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
            /**
             * 回帖之前的验证与editor的显示
             */
            function ht(){
                if(${sessionScope.yhb==null}){
                    alert("您好,请登录后再回帖!");
                    return;
                }
                var htnr=editorht.document.getBody().getText(); //取得纯文本
                if(htnr==''||htnr==null){
                    $("#htnotice").text("请输入内容");
                    return;
                }else{
                    if(trim(htnr)==''){
                        $("#htnotice").text("请输入内容");
                        return;
                    }
                }
                var htcode=$("#htcode").val();
                $.ajax({
                    type:'post',
                    dataType:'json',
                    url:path+'TzlbIndexAction.jsp',
                    data:{
                        'mode':'CHECKCODE',
                        'code':htcode
                    },
                    success:function(msg){
                        if(msg.notice=='验证码正确'){
                            $("#htform").submit();
                        }else{
                            $("#htnotice").text(msg.notice);
                        }
                    }
                })
            }     
        </script>
        <title>交流论坛</title>
    </head>
    <body>
        <%@include  file="../public/head.jsp" %>
        <div class="main">
            <div class="wz">
                <span>当前位置</span>
                <a href="<z:path/>IndexAction.jsp?mode=SHOWLIST">主页</a>>>
                <label ><a href="<z:path/>JltdIndexAction.jsp?mode=SHOWLIST">交流论坛</a></label>
                <lable id="third">>><a href="<z:path/>TzlbIndexAction.jsp?mode=SHOWLIST&bkid=${bkid}&page=${page}" style="color:red;">${bkmc}</a></lable>
                <a href="<z:path/>TzlbIndexAction.jsp?mode=SHOWLIST&bkid=${bkid}&page=${page}" style="margin-left: 430px;"><img src="<z:path/>web/image/return.jpg" />返回上一级</a>
            </div>
            <div style="clear:both;"></div>
            <div id="all" style=";">
                <div class="fh" ><span onclick="ft()">发帖</span><span id="hh" onclick="preht()">回复</span></div>
                <%--主贴--%>
                <div class="content2" id="main">
                    <div class="content" id="zt" >
                        <div class="left">
                            <div class="la">
                                <span>查看</span><span style="padding-right: 5px;border-right: 1px solid #b3c5df;">${zt.llcs}</span><span>回复</span><span>${zt.htzs}</span>
                            </div>
                            <div class="ren">
                                <a target="_blank" href="<z:path/>BlogIndexServlet.jsp?mode=SHOWALL&host=${zt.id}"><img src="<z:path/>userfiles${zt.yhtx}" style="width:117px;height:120px;"/></a><br>
                                <span class="ren1">${zt.yhm}</span>
                                <ul>
                                    <li><span>性别：</span><span>${zt.xb}</span></li>
                                    <li>
                                        <span>等级：</span>
                                        <span>
                                            <c:choose>
                                                <c:when test="${zt.qx==1}">
                                                    <img src="<z:path/>web/image/bbs/online_${zt.qx}.gif" />最高权限
                                                </c:when>
                                                <c:when test="${zt.qx==2}">
                                                    <img src="<z:path/>web/image/bbs/online_${zt.qx}.gif" />超级管理员
                                                </c:when>
                                                <c:when test="${zt.qx==3}">
                                                    <img src="<z:path/>web/image/bbs/online_${zt.qx}.gif" />普通管理员
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="<z:path/>web/image/bbs/online_${zt.qx}.gif" /> 初出茅庐
                                                </c:otherwise>
                                            </c:choose>
                                        </span>
                                    </li>
                                    <li><span>积分：</span><span>${zt.jf}</span></li>
                                    <li><span>注册时间：</span><span>${fn:substring(zt.zcsj, 0, 10)}</span></li>
                                </ul>
                            </div>
                        </div>

                        <div class="right">
                            <div class="la1">
                                <span class="nr1">${zt.tzbt}</span></div>
                            <div class="fb">
                                <label style="float:left;"><img src="<z:path/>web/image/bbs/online_${zt.qx}.gif" /><span>发表于：</span><span >${fn:substring(zt.ftsj, 0, 16)}</span></label>
                                <span style="float:right;margin-right: 15px; border:1px solid sandybrown;padding: 4px;">楼主</span>
                                <div style="clear:both;"></div>
                            </div>
                            <div class="nr">
                                ${zt.tznr}
                            </div>
                            <div class="htgxqm">
                                <span style="color: #99BBE8;font-size:12px;"> 个性签名：${zt.gxqm} </span> 
                            </div>
                        </div>
                    </div>
                </div>

                <%--回帖列表--%>
                <c:forEach var="ht" items="${htlist}" varStatus="par">
                    <div class="content3">
                        <div class="content4">
                            <div class="left">
                                <div class="ren">
                                    <a target="_blank" href="<z:path/>BlogIndexServlet.jsp?mode=SHOWALL&host=${ht.id}"><img src="<z:path/>userfiles${ht.yhtx}" style="width:117px;height:120px;"/></a><br>
                                    <span class="ren1">${ht.yhm}</span>
                                    <ul>
                                        <li><span>性别：</span><span>${ht.xb}</span></li>
                                        <li>
                                            <span>等级：</span>
                                            <span>  
                                                <c:choose>
                                                    <c:when test="${ht.qx==1}">
                                                        <img src="<z:path/>web/image/bbs/online_${ht.qx}.gif" />最高权限
                                                    </c:when>
                                                    <c:when test="${ht.qx==2}">
                                                        <img src="<z:path/>web/image/bbs/online_${ht.qx}.gif" />超级管理员
                                                    </c:when>
                                                    <c:when test="${ht.qx==3}">
                                                        <img src="<z:path/>web/image/bbs/online_${ht.qx}.gif" />普通管理员
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="<z:path/>web/image/bbs/online_${ht.qx}.gif" />初出茅庐
                                                    </c:otherwise>
                                                </c:choose>
                                            </span>
                                        </li>
                                        <li><span>积分：</span><span>${ht.jf}</span></li>
                                        <li><span>注册时间：</span><span>${fn:substring(ht.zcsj, 0, 10)}</span></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="right" style="">
                                <div class="fb" style="">
                                    <label style="float:left;"><img src="<z:path/>web/image/bbs/online_${ht.qx}.gif" /><span >回复于：</span><span>${fn:substring(ht.htsj, 0, 16)}</span></label>
                                        <c:choose>
                                            <c:when test="${par.index==0&&page==1}">
                                            <span style="float:right;margin-right: 15px; border:1px solid  sandybrown;padding: 4px;">沙发</span>
                                        </c:when>
                                        <c:when test="${par.index==1&&page==1}">
                                            <span style="float:right;margin-right: 15px; border:1px solid  sandybrown;padding: 4px;">板凳</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span style="float:right;margin-right: 15px; border:1px solid sandybrown;padding: 4px;">${(page-1)*10+par.index+1}楼</span>
                                        </c:otherwise>
                                    </c:choose>
                                    <div style="clear:both;"></div>
                                </div>
                                <div class="nr">
                                    ${ht.htnr}
                                </div>
                                <div class="htgxqm">
                                    <span style="color: #99BBE8;font-size:12px;"> 个性签名：${ht.gxqm} </span> 
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <%--发帖回复按钮--%>
                <div style="clear:both;"></div>
                <div class="fthf" style="">
                    <span class="ft" onclick="ft()">发帖</span><span class="ft" onclick="preht()">回复</span>
                    <c:if test="${fn:length(htlist)>0}">
                        <label>
                            <a href="<z:path/>TznrIndexAction.jsp?mode=SHOWONE&bkid=${bkid}&ftid=${ftid}&page=1"><span>首页</span></a>
                            <a href="<z:path/>TznrIndexAction.jsp?mode=SHOWONE&bkid=${bkid}&ftid=${ftid}&page=${page<=1?1:page-1}"><span>上一页</span></a>
                            <a href="<z:path/>TznrIndexAction.jsp?mode=SHOWONE&bkid=${bkid}&ftid=${ftid}&page=${page>=totalpages?totalpages:page+1}"><span>下一页</span></a>
                            <a href="<z:path/>TznrIndexAction.jsp?mode=SHOWONE&bkid=${bkid}&ftid=${ftid}&page=${totalpages}"><span>尾页</span></a>
                            <span class="ye" id="page" >第${page}</span>/<span id="totalpages" class="ye" >${totalpages}页</span>
                            <input type="hidden" value="${bkid}" id="get_bkid"/><input type="hidden" value="${ftid}" id="get_ftid"/>
                            <input type="text" size="2" id="goto_page"/>
                            <span  class="sc"style="margin-left: 0px;" onclick="gotoPage()">goto</span>
                        </label>
                    </c:if>
                </div> 
                <%--发表回复的界面--%>
                <div class="content1">
                    <div class="left1">
                        <div class="ren">
                            <img src="<z:path/>userfiles${sessionScope.yhb.yhtx}" style="width:117px;height:120px;" /><br>
                        </div>
                    </div>
                    <div class="right">
                        <div class="fb">
                            回复给：<span id="hfg">${zt.tzbt}</span>
                        </div>
                        <div class="nr">
                            <form method="post" id="htform" action="<z:path/>TznrIndexAction.jsp?mode=HT" >
                                <input type="hidden" name="ftid" value="${ftid}"/>
                                <input type="hidden" name="bkid" value="${bkid}"/>
                                <textarea id="htnr" name="htnr"></textarea>
                                <div class="tjhf">
                                    <span class="tjhf1" onclick="ht()">提交回复</span>
                                    <input type="text"  size="10"  name="code" id="htcode" style=" width:100px; height:25px; margin-right:10px;"/>
                                    <img id="Image2" src="<z:path/>web/public/image.jsp" style="cursor:pointer" onmouseup="RefreshImage2()" title="看不清请点击更新"/>
                                    <span id="htnotice" style="clear:both;width:150px;height:25px;color:red;"></span>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- Baidu Button BEGIN -->
                <script type="text/javascript" id="bdshare_js" data="type=slide&amp;img=1" ></script>
                <script type="text/javascript" id="bdshell_js"></script>
                <!-- Baidu Button END -->
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
                        <input type="text"  size="10"  id="code" name="code" style=" width:100px; height:23px; margin-left: 10px;"/> 
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
                </form>
            </div>    
            <div style="height:10px;"></div>
        </div>
        <%@include  file="../public/bott.jsp" %>
    </body>
</html>
