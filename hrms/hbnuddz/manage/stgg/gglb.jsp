<%-- 
    Document   : gglb
    Created on : 2013-3-14, 19:19:56
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
        <script type="text/javascript" src="<z:path/>manage/stgg/gglb.js"></script>
        <script type="text/javascript">
            var path = '<z:path/>';
            var mode="";
        </script>
        <title>公告类别</title>
    </head>
    <body>
        <div id="maindatagrid" class="easyui-datagrid" toolbar="#gridTools"></div>
        <div id="gridTools" style="padding:5px;height:auto">
            <div style="margin-bottom:5px" id="test" >
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="addGglb()" iconCls="icon-add" plain="true">添加公告类别</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="editGglb()" iconCls="icon-edit" plain="true">查看/修改公告类别信息</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="deleteGglb()" iconCls="icon-remove" plain="true">删除公告类别</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="loadAllData()" iconCls="icon-back" plain="true">所有公告类别</a>
                <input type='text' id='ss' />
            </div>
            <div id="cxtj" style="width:120px;float:right">   
                <div name="lbmc">公告类别名称</div> 
            </div> 
        </div>
        <div id="cytp" class="easyui-dialog" closed="true" style="width:400px;height:500px;" iconCls="icon-search" modal="true"> </div>
        <div id="add" class="easyui-dialog" closed="true" style="width:480px;" iconCls="icon-add" modal="true">
            <form method="post" id="mainform">
                <fieldset>
                    <legend style="color: #0046D5;" >公告类别信息:</legend>  
                    <input type="hidden" id="get_lbid" name="lbid"/>
                    公告类别名称:<input type="text" id="lbmc" name="lbmc"/>
                    <br/>
                </fieldset>
                <input type="reset" class="easyui-linkbutton" style="text-align: center" value="重置"/>
                <input type="button" class="easyui-linkbutton" style="text-align: center" value="提交" onclick="submitForm()"/>
            </form>
        </div>
    </body>
</html>
