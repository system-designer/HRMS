<%-- 
    Document   : updatehbbk
    Created on : 2013-3-15, 13:53:41
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
        <script type="text/javascript" src="<z:path/>ckeditor/ckeditor.js"></script>
        <script type="text/javascript" src="<z:path/>manage/hbbk/updatehbbk.js"></script>
        <script type="text/javascript">
            var path = '<z:path/>';
            var mark=${param.mark};//判断是添加还是修改
            var bkid=${param.bkid};//需要修改的百科id
        </script>
        <title>环保百科</title>
    </head>
    <body>
        <form method="post" id="mainform">
            <input type="reset" class="easyui-linkbutton" style="text-align: center" value="重置"/>
            <input type="button" class="easyui-linkbutton" style="text-align: center" value="提交" onclick="submitForm()"/>
            <fieldset>
                <legend style="color: #0046D5;" >百科基本信息:</legend>  
                <input type="hidden" id="get_bkid" name="bkid"/>
                百科标题:<input type="text" id="bkbt" name="bkbt"/>
                百科时间:<input id="bksj" name="bksj" type="text" class="easyui-datebox"/>
                百科类别:<select class="easyui-validatebox"  required="true" missingMessage="请选择百科类别" class="easyui-combobox" id="bklb" name="bklb"></select>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="loadBklbCombo()" plain="true" iconCls="icon-reload"></a>
                <br/>
                <textarea  id="bknr" name="bknr"></textarea>   
            </fieldset>
        </form>
    </body>
</html>

