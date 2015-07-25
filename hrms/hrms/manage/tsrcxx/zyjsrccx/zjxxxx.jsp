<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : zjxxxx
    Created on : 2013-11-21, 16:03:04
    Author     : star
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link  rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/default/easyui.css"/>
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/icon.css"/>
        <script type="text/javascript" src="<z:path/>js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="<z:path/>ckeditor/ckeditor.js"></script>
        <script type="text/javascript" src="<z:path/>manage/jbrcxx/jl.js"></script>
        <title>JSP Page</title>
        <script type="text/javascript">
            var path = "<z:path/>";
        </script>
    </head>
    <body>
        <div  id="xq" style="height:auto;">
            <style>
                .h{
                    float: left;
                }        
            </style>
        </div>
        <div  class="printArea">
            <div>
                <div title="基本信息">
                    <%@include file="/manage/tsrcxx/dzrc/jbxx.jsp" %>
                </div>
            </div>
            <br><br>
            <div style="margin-left: 120px">
                <div>
                    <p style="margin-left: 0px; font-size: 20px">学习简历</p>
                    <div  style="border: 1px solid #D3D3D3;width: 830px;height: 100px" id="xxjl" name="xxjl" readonly="true">${requestScope.list[0].xxjl}</div>
                    <p style="margin-left: 0px;font-size: 20px">工作简历</p>
                    <div style="border: 1px solid #D3D3D3;width: 830px;height: 100px" id="gzjl" name="gzjl" readonly="true">${requestScope.list[0].gzjl}</div>
                    <p style="margin-left: 0px;font-size: 20px">获得成果、奖励(含授予荣誉称号)情况</p>
                    <div style="border: 1px solid #D3D3D3;width: 830px;height: 100px" id="hjcq" name="hjcq" readonly="true">${requestScope.list[0].hjcq}</div>
                </div>
            </div>
        </div>
        <div style="clear: both"></div>
        <div style="margin-left: 830px;"><a href="javascript:void()" onclick="printView();" style="text-decoration: none;color: #ff0000">【打印】</a></div>
    </body>
</html>
