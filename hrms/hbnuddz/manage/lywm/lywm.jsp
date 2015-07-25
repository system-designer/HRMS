<%-- 
    Document   : lywm
    Created on : 2013-3-15, 12:42:59
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
        <script type="text/javascript" src="<z:path/>manage/lywm/lywm.js"></script>
        <script type="text/javascript">
            var path = '<z:path/>';
            var mode="";
        </script>
        <title>留言板</title>
    </head>
    <body>
        <div id="maindatagrid" class="easyui-datagrid" toolbar="#gridTools"></div>
        <div id="gridTools" style="padding:5px;height:auto">
            <div style="margin-bottom:5px" id="test" >
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="addLy()" iconCls="icon-add" plain="true">添加留言</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="editLy()" iconCls="icon-edit" plain="true">查看/修改留言</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="deleteLy()" iconCls="icon-remove" plain="true">删除留言</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="loadAllData()" iconCls="icon-back" plain="true">所有留言</a>
                <input type='text' id='ss' />
            </div>
            <div id="cxtj" style="width:120px;float:right">    
                <div name="lyr">留言人</div> 
                <div name="yhm">回复人</div> 
            </div> 
        </div>
        <div id="add" class="easyui-dialog" closed="true" style="width:400px;" iconCls="icon-add" modal="true">
            <form method="post" id="mainform">
                <input type="reset" class="easyui-linkbutton" style="text-align: center" value="重置"/>
                <input type="button" class="easyui-linkbutton" style="text-align: center" value="提交" onclick="submitForm()"/>
                <fieldset>
                    <legend style="color: #0046D5;" >留言基本信息:</legend>  
                    <input type="hidden" id="get_lyid" name="lyid"/>
                    留言人:<input type="text" id="lyr" name="lyr"/><br/>
                    留言内容:<br/>
                    <textarea type="text" id="lynr" name="lynr" style="height:150px;width:360px;resize:none"></textarea><br/>
                    回复内容:<br/>
                    <textarea type="text" id="hfnr" name="hfnr" style="height:150px;width:360px;resize:none"></textarea><br/>
                </fieldset>
            </form>
        </div>
    </body>
</html>


