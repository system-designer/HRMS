<%-- 
    Document   : ncsyrcxq
    Created on : 2013-11-21, 12:21:17
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="z" uri="http://opdps.hbnu.edu.cn/jplus" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/default/easyui.css"/>
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/icon.css">
        <script type="text/javascript" src="<z:path/>js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
        <style type="text/css">
            .h{
                float: left
            }
        </style>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="printArea">
            <div class="easyui-tabs" id="xq" style="height: 432px;">
                <div title="基本信息" style="height: 432px">
                    <%@include file="/manage/tsrcxx/public/jbxx.jsp" %>
                    <div style="margin-top: 280px;padding-left: 220px;"> 
                        <div class="h" style="width: 240px;"><span>是否外出打工半年：${requestScope.list[0].sfwcdg==null?"":requestScope.list[0].sfwcdg==false?"否":"是"}</span></div><div class="h" style="width: 195px;"><span>上年纯收入：${requestScope.list[0].csrmc}</span></div><div class="h" style="width: 250px;"><span>农村实用人才类别：${requestScope.list[0].ncrclbmc}</span></div>
                    </div>
                </div>
            </div>
        </div>
        <div style="margin-left: 830px;"><a href="javascript:void()" onclick="printView();" style="text-decoration: none;color: #ff0000">【打印】</a></div>
    </body>
</html>
