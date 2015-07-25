<%-- 
    Document   : shgzrcxq
    Created on : 2013-11-30, 18:07:03
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/default/easyui.css"/>
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/icon.css">
        <script type="text/javascript" src="<z:path/>js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
    </head>
    <body>
        <div class="printArea">
            <div class="easyui-tabs" id="xq" style="height: 432px;">
                <div title="基本信息" style="height: 432px">
                    <%@include file="/manage/tsrcxx/public/jbxx.jsp" %>
                    <div style="margin-top: 280px;padding-left: 220px;"> 
                        <div class="h" style="width: 240px;"><span>项目分类：${requestScope.list[0].xmflmc}</span></div>
                    </div>
                </div>
            </div>
        </div>
        <div style="margin-left: 830px;"><a href="javascript:void()" onclick="printView();" style="text-decoration: none;color: #ff0000">【打印】</a></div>
    </body>
</html>
