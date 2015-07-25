<%-- 
    Document   : htgl
    Created on : 2013-3-11, 19:29:59
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<z:path/>jquery-easyui-1.2.5/themes/default/easyui.css"/>
        <link rel="stylesheet" type="text/css" href="<z:path/>jquery-easyui-1.2.5/themes/icon.css"/>
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="<z:path/>manage/jltd/htgl.js"></script>
        <script type="text/javascript">
            var path = '<z:path/>';
            var mode="";
            var qjtj='${param.searchValue}';//点击加载所有时加载指定类别的列表
            var searchName='${param.searchName}';
            var searchValue='${param.searchValue}';//定义全局的查询条件和查询内容
        </script>
        <title>回贴管理</title>
    </head>
    <body>
        <div id="maindatagrid" class="easyui-datagrid" toolbar="#gridTools"></div>
        <div id="gridTools" style="padding:5px;height:auto">
            <div style="margin-bottom:5px" id="test" >
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="updateHt()" iconCls="icon-edit" plain="true">查看/修改回贴信息</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="deleteHt()" iconCls="icon-remove" plain="true">删除回帖</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="loadAllData()" iconCls="icon-back" plain="true">所有回帖</a>
                <input type='text' id='ss' />
            </div>
            <div id="cxtj" style="width:120px;float:right">    
                <div name="yhm">回帖人</div>   
                <div name="tzbt">主贴标题</div> 
            </div> 
        </div>
    </body>
</html>



