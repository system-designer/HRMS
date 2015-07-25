<%-- 
    Document   : xhcy
    Created on : 2013-3-4, 20:03:41
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
        <script type="text/javascript" src="<z:path/>manage/gywm/xhcy.js"></script>
        <script type="text/javascript">
            var path = '<z:path/>';
            var mode="";
            var qjtj='${param.searchValue}';//点击加载所有时加载指定类别的列表
            var searchName='${param.searchName}';
            var searchValue='${param.searchValue}';//定义全局的查询条件和查询内容
        </script>
        <title>协会成员</title>
    </head>
    <body>
        <div id="member" class="easyui-datagrid" toolbar="#gridTools"></div>
        <div id="gridTools" style="padding:5px;height:auto">
            <div style="margin-bottom:5px" id="test" >
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="addRy()" iconCls="icon-add" plain="true">添加人员</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="editRy()" iconCls="icon-edit" plain="true">修改人员信息</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="deleteRy()" iconCls="icon-remove" plain="true">删除人员</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="loadAllData()" iconCls="icon-back" plain="true">所有人员</a>
                <input type='text' id='ss' />
            </div>
            <div id="cxtj" style="width:120px;float:right">    
                <div name="xm">姓名</div>   
                <div name="bmmc">部门名称</div> 
            </div> 
        </div>
        <div id="hyzpdia" class="easyui-dialog" closed="true" style="width:400px;height:500px;" iconCls="icon-search" modal="true">
            <img id="hyzp" src="" onerror='showOtherPic()' style="width:384px;height:460px;"/>
        </div>
        <div id="addRy" class="easyui-dialog" closed="true" style="width:480px;" iconCls="icon-add" modal="true" title="添加成员">
            <form method="post" id="ryform">
                <input type="reset" class="easyui-linkbutton" style="text-align: center" value="重置"/>
                <input type="button" class="easyui-linkbutton" style="text-align: center" value="提交" onclick="submitForm()"/>
                <fieldset>
                    <legend style="color: #0046D5;" >会员基本信息:</legend>  
                    <input type="hidden" id="get_id" name="id"/>
                    姓名:<input type="text" id="xm" name="xm"/>
                    性别:<input type="radio" name="xb" value="男" />男
                    <input type="radio" name="xb" value="女" />女
                    <br/>
                    系级:<input type="text" id="xj" name="xj"/> 短号:<input type="text" id="dh" name="dh"/><br/>
                    联系电话:<input type="text" id="lxdh" name="lxdh"/><br/>      
                </fieldset>
                <fieldset>
                    <legend style="color: #0046D5;" >会员职务信息:部门名称和职务名称为必填项目</legend>   
                    部门名称: <select class="easyui-combobox" class="easyui-validatebox" required="true" missingMessage="请选择部门名称" id="bmmc" name="bmid" style="resize: none"></select><a href="javascript:void(0);" class="easyui-linkbutton" onclick="loadBmmcCombo()" plain="true" iconCls="icon-reload"></a>
                    职务名称: <select class="easyui-combobox" class="easyui-validatebox" required="true" missingMessage="请选择职务名称" id="zwmc" name="zwfl" style="resize: none"></select><a href="javascript:void(0);" class="easyui-linkbutton" onclick="loadZwmcCombo()" plain="true" iconCls="icon-reload"></a>
                </fieldset>
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
