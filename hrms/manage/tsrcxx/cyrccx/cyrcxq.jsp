<%-- 
    Document   : xxxx
    Created on : 2013-11-14, 14:54:08
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
                <div title="创业人才信息">
                    <div>
                        <%@include file="/manage/tsrcxx/public/jbxx.jsp" %><br>         
                        <div style="padding-left: 483px;margin-top: 30px;font-weight: 800;font-size: 18px;">创业人才信息</div>
                        <div style="margin-top:20px;padding-left: 220px">
                            <div class="h" style="width: 240px;"><span>原任职机构：${requestScope.list[0].yrzjg}</span></div><div class="h" style="width: 195px;"><span>原&nbsp;&nbsp;职&nbsp;&nbsp;务：${requestScope.list[0].yzw}</span></div><div class="h" style="width: 300px;"><span>电子邮箱：${requestScope.list[0].email}</span></div>
                        </div><br>
                        <div style="margin-top:20px;padding-left: 220px">
                            <div class="h" style="width: 240px;"><span>在黄工作时间：${requestScope.list[0].zhgzsj}</span></div><div class="h" style="width: 240px;"><span>原居住地：${requestScope.list[0].yjzdz}</span></div>
                        </div><br>
                        <div style="padding-left:486px;margin-top: 20px;font-weight: 800;font-size: 18px;">
                            重要荣誉或重要奖项
                        </div>
                        <div style="margin-top:20px;padding-left: 215px"><div id="zyry" style="width:700px; height:1000px;min-height:150px !important; height:auto!important; border: 0.5px solid #D3D3D3;">${requestScope.list[0].zyry}</div></div>
                        <div style="padding-left: 473px;margin-top: 20px;font-weight: 800;font-size: 18px;">
                            学习简历（含参加培训情况）
                        </div>
                        <div style="margin-top:20px;padding-left: 215px"><div id="jyjl" style="width:700px;height:1000px;min-height:150px !important; height:auto!important;border: 0.5px solid #D3D3D3;">${requestScope.list[0].xxjl}</div></div>
                        <div style="padding-left: 510px;margin-top: 20px;font-weight: 800;font-size: 18px;">
                            工作简历
                        </div>
                        <div style="margin-top:20px;padding-left: 215px"><div id="zyjc" style="width:700px;height:1000px;min-height:150px !important; height:auto!important;border: 0.5px solid #D3D3D3;">${requestScope.list[0].gzjl}</div></div>
                    </div> 
                </div>
            </div>
        </div>
        <div style="margin-left: 830px;"><a href="javascript:void()" onclick="printView();" style="text-decoration: none;color: #ff0000">【打印】</a></div>
    </body>
</html>
