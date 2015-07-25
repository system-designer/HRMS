<%-- 
    Document   : webindex
    Created on : 2013-3-16, 8:48:46
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
        <script type="text/javascript" src="<z:path/>manage/webindex/webindex.js"></script>
        <script type="text/javascript">
            var path = '<z:path/>';
        </script>
        <title>首页管理</title>
    </head>
    <body>
        <div id="maindatagrid" class="easyui-datagrid" toolbar="#gridTools"></div>
        <div id="gridTools" style="padding:5px;height:auto">
            <div style="margin-bottom:5px" id="test" >
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="editHbxzs()" iconCls="icon-edit" plain="true">查看/修改环保小知识</a>
            </div>
        </div>
        <div id="add" class="easyui-dialog" closed="true" style="width:400px;" iconCls="icon-add" modal="true">
            <form method="post" id="mainform">
                <input type="reset" class="easyui-linkbutton" style="text-align: center" value="重置"/>
                <input type="button" class="easyui-linkbutton" style="text-align: center" value="提交" onclick="submitForm()"/>
                <fieldset>
                    <legend style="color: #0046D5;" >主页的环保小知识:</legend>  
                    <input type="hidden" id="get_id" name="id"/>
                    标题:<input type="text" id="bt" name="bt"/><br/>
                    内容:<br/>
                    <textarea type="text" id="nr" name="nr" style="height:150px;width:360px;resize:none"></textarea><br/>  
                </fieldset>
            </form>
        </div>
        <div id="notice" class="easyui-panel" style="width:400px;" iconCls="icon-add" modal="true">
            <fieldset>
                <legend style="color: #0046D5;" >温馨提示</legend>  
                对于首页的精彩瞬间和环保漫画的修改，只需在图片管理页面相应的文件夹内，
                删除旧的图片，上传新的图片即可
            </fieldset>
        </div>
    </body>
</html>
