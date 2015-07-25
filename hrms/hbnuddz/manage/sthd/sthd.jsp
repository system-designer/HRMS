<%-- 
    Document   : stgg
    Created on : 2013-3-7, 15:21:54
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
        <script type="text/javascript" src="<z:path/>manage/sthd/sthd.js"></script>
        <script type="text/javascript">
            var path = '<z:path/>';
            var mode="";
            var qjtj='${param.searchValue}';//点击加载所有时加载指定类别的列表
            var searchName='${param.searchName}';
            var searchValue='${param.searchValue}';//定义全局的查询条件和查询内容
        </script>
        <title>社团活动</title>
    </head>
    <body>
        <div id="maindatagrid" class="easyui-datagrid" toolbar="#gridTools"></div>
        <div id="gridTools" style="padding:5px;height:auto">
            <div style="margin-bottom:5px" id="test" >
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="addHd()" iconCls="icon-add" plain="true">添加活动</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="updateHd()" iconCls="icon-edit" plain="true">查看/修改活动信息</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="deleteHd()" iconCls="icon-remove" plain="true">删除活动</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="loadAllData()" iconCls="icon-back" plain="true">所有活动</a>
                <input type='text' id='ss' />
            </div>
            <div id="cxtj" style="width:120px;float:right">    
                <div name="hdbt">活动标题</div> 
                <div name="lbmc">活动类别</div> 
                <div name="xm">活动积极分子</div>  
            </div> 
        </div>
        <div id="hdtpdia" class="easyui-dialog" closed="true" style="width:400px;height:500px;" iconCls="icon-search" modal="true">
            <img id="hdtp" src="" onerror='showOtherPic()' style="width:384px;height:460px;"/>
        </div>
        <input type="hidden" id="tempid"/><input type="hidden" id="temphdbt"/>
        <div id="tpdia" class="easyui-dialog" closed="true" style="width:400px;height:500px;" iconCls="icon-search" modal="true">
            <center>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="viewbroswer()" iconCls="icon-search" plain="true">浏览服务器</a><br/>
                图片显示区:<span id="notice"></span><br/><br/>
                <img id="tp" src="" style="width:320px;height:380px;border:1px solid black;"/>
            </center>
        </div>
    </body>
</html>

