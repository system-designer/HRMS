<%-- 
    Document   : jnrcxq
    Created on : 2013-11-25, 9:59:33
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="z" uri="http://opdps.hbnu.edu.cn/jplus" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/default/easyui.css"/>
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/icon.css">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/mycss/i-style.css">
        <script type="text/javascript" src="<z:path/>js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="<z:path/>manage/tsrcxx/jnrccx/jnrcxq.js"></script>
        <script type="text/javascript">
            var ryid=${param.ryid};
            var path_zgzs="<z:path/>manage/tsrcxx/jnrc/ZgzsAction.jsp";
            var path_ryjl="<z:path/>manage/tsrcxx/jnry/RyjlAction.jsp";
        </script>
        <style>
            .h{
                float: left;
            }        
        </style>
        <title>查询详情</title>
    </head>
    <body>
        <div class="printArea" id="xq" >
            
            <table style="margin-left:120px">
                <tr>
                    <td><%@include file="/manage/tsrcxx/public/jbxx.jsp" %></td>
                </tr>
                <tr>
                    <td><div id="zgzs1"></div></td>
                </tr>
                <tr>
                    <td><div id="ryjl1"></div></td>
                </tr>
            </table>
            <div style="margin-left: 830px;">
                <a href="javascript:void()" onclick="printView();" style="text-decoration: none;color: #ff0000">【打印】</a>
            </div>
        </div>
    </body>
</html>
