<%-- 
    Document   : bkgl
    Created on : 2013-3-11, 19:30:07
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
        <script type="text/javascript" src="<z:path/>manage/jltd/bkgl.js"></script>
        <script type="text/javascript">
            var path = '<z:path/>';
            var mode="";
        </script>
        <title>协会板块</title>
    </head>
    <body>
        <div id="maindatagrid" class="easyui-datagrid" toolbar="#gridTools"></div>
        <div id="gridTools" style="padding:5px;height:auto">
            <div style="margin-bottom:5px" id="test" >
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="addBk()" iconCls="icon-add" plain="true">添加板块</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="editBk()" iconCls="icon-edit" plain="true">查看/修改板块信息</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="deleteBk()" iconCls="icon-remove" plain="true">删除板块</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="loadAllData()" iconCls="icon-back" plain="true">所有板块</a>
                <input type='text' id='ss' />
            </div>
            <div id="cxtj" style="width:120px;float:right">    
                <div name="yhm">版主</div>   
                <div name="bkmc">板块名称</div> 
            </div> 
        </div>
        <div id="cytp" class="easyui-dialog" closed="true" style="width:400px;height:500px;" iconCls="icon-search" modal="true" title="成员图片"> </div>
        <div id="add" class="easyui-dialog" closed="true" style="width:480px;" iconCls="icon-add" modal="true">
            <form method="post" id="mainform">
                <fieldset>
                    <legend style="color: #0046D5;" >板块信息:版主可在用户表里查找</legend>  
                    <input type="hidden" id="get_bkid" name="bkid"/>
                    板块名称:<input type="text" id="bkmc" name="bkmc"/>
                    版主:<input type="text" id="yhm" name="yhm"/>
                    <br/>
                    板块描述 :<textarea id="bkms" style="width:450px;height:80px;resize: none" name="bkms"></textarea>
                </fieldset>
                <input type="reset" class="easyui-linkbutton" style="text-align: center" value="重置"/>
                <input type="button" class="easyui-linkbutton" style="text-align: center" value="提交" onclick="submitForm()"/>
            </form>
        </div>
        <input type="hidden" id="tempid"/>
        <div id="tpdia" class="easyui-dialog" closed="true" style="width:400px;height:500px;" iconCls="icon-search" modal="true">
            <center>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="viewbroswer()" iconCls="icon-search" plain="true">浏览服务器</a><br/>
                图片显示区:<span id="notice"></span><br/><br/>
                <img id="tp" src="" style="width:320px;height:380px;border:1px solid black;"/>
            </center>
        </div>
    </body>
</html>



