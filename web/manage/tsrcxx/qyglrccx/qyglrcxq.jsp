<%-- 
    Document   : qyglrcxq
    Created on : 2013-12-1, 13:47:15
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="z" uri="http://opdps.hbnu.edu.cn/jplus"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/default/easyui.css"/>
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/icon.css">
        <script type="text/javascript" src="<z:path/>js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
        <title>JSP Page</title>
        <style>
            .h{
                float: left;
            }        
        </style>
    </head>
    <body>
        <div class="printArea">
            <div class="easyui-tabs"  id="xq" style="height:auto">
                <div title="企业管理人才信息">
                    <div>
                        <%@include file="/manage/tsrcxx/public/jbxx.jsp" %><br>         
                        <div style="padding-left: 510px;margin-top: 20px;">
                            学习简历
                        </div>
                        <div style="margin-top:20px;padding-left: 245px"><div id="zyry" style="width:600px; height:1000px;min-height:150px !important; height:auto!important; border: 0.5px solid #00ffff">${requestScope.list[0].xxjl}</div></div>
                        <div style="padding-left: 510px;margin-top: 20px;">
                            工作简历
                        </div>
                        <div style="margin-top:20px;padding-left: 245px"><div id="jyjl" style="width:600px;height:1000px;min-height:150px !important; height:auto!important;border: 0.5px solid #00ffff">${requestScope.list[0].gzjl}</div></div>
                        <div style="padding-left: 510px;margin-top: 20px;">
                            奖惩情况
                        </div>
                        <div style="margin-top:20px;padding-left: 245px"><div id="zyjc" style="width:600px;height:1000px;min-height:150px !important; height:auto!important;border: 0.5px solid #00ffff">${requestScope.list[0].jcqk}</div></div>
                    </div> 
                </div>
            </div>
        </div>
        <div style="margin-left: 830px;"><a href="javascript:void()" onclick="printView();" style="text-decoration: none;color: #ff0000">【打印】</a></div>
    </body>
</html>
