<%--
    Document   : main
    Created on : 2013-2-7, 10:49:41
    Author     : 刘雷
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="<z:path/>web/image/favicon.ico" type="image/x-icon" />
        <link rel="Shortcut Icon" type="image/x-icon" href="<z:path/>web/image/favicon.ico" />
        <link rel="stylesheet" type="text/css" href="<z:path/>jquery-easyui-1.2.5/themes/default/easyui.css"/>
        <link rel="stylesheet" type="text/css" href="<z:path/>jquery-easyui-1.2.5/themes/icon.css"/>
        <link rel="stylesheet" type="text/css" href="<z:path/>manage/css/main.css"/>
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="<z:path/>manage/js/main.js"></script>
        <script type="text/javascript">
            var path = '<z:path/>';
        </script>
        <title>绿源环保协会后台管理系统</title>
    </head>
    <body class="easyui-layout">
        <div region="north" title="绿源环保协会后台管理系统" split="true"style="height:60px;background-color: #EDF4FE;">   
            <div style="width:58%;text-align: left;line-height: 22px;float:left;">
                <a href="<z:path/>IndexAction.jsp?mode=LOGINOUT">注销登录</a>&nbsp;&nbsp;当前登录用户：${sessionScope.yhb.yhm}
                <span style="color:red"></span>&nbsp;&nbsp;用户编号：${sessionScope.yhb.id}
                <span style="color:red"></span>&nbsp;&nbsp;用户权限：${sessionScope.yhqxmc}
                <span style="color:red"></span>&nbsp;&nbsp;&nbsp;&nbsp;今天是：<span id="time"></span>
            </div>
            <div style="width:40%;text-align: right;line-height:22px;float:right;">
                <span title="回到首页" class="spanBt" > <a href="<z:path/>IndexAction.jsp?mode=SHOWLIST">【回到首页】</a></span>&nbsp;
                <span title="安全退出系统" class="spanBt" > <a href="<z:path/>IndexAction.jsp?mode=LOGINOUT">【安全退出】</a></span>&nbsp;
            </div>
        </div> 
        <div region="west" split="false"  title="管理菜单" style="  width:170px;overflow:hidden;">
            <div class="easyui-accordion" fit="false" border="false" id="navigation" style="width:170px;">
                <div title="论坛管理">
                    <ul>
                        <li><a href="javascript:void(0);" onclick="addTab('/manage/jltd/bkgl.jsp','板块管理')">板块管理</a></li>
                        <li><a href="javascript:void(0);" onclick="addTab('/manage/jltd/ztgl.jsp','主帖管理')">主帖管理</a></li>
                        <li><a href="javascript:void(0);" onclick="addTab('/manage/jltd/htgl.jsp','回帖管理')">回帖管理</a></li>
                    </ul>
                </div>
                <div title="留言管理">
                    <ul>
                        <li><a href="javascript:void(0);" onclick="addTab('/manage/lywm/lywm.jsp','留言管理')">留言管理</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div region="center" split="false" title=""  style="overflow: hidden;" >
            <div class="easyui-tabs" fit="true" border="false" id="workTabs">
            </div>
        </div>
        <div  region="south" split="false"  style="overflow: hidden;height:30px;">
            <div class="easyui-panel" title="Copyright© 2011-2012 湖北师范学院计算机科学与技术系Jplus实验室&nbsp;&nbsp;最佳分辨率1400x900"
                 headerCls="titleCenter" collapsible="false" border="false"></div>
        </div>
    </body>
</html>
