<%-- 
    Document   : updateht
    Created on : 2013-3-12, 18:37:53
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
        <script type="text/javascript" src="<z:path/>manage/jltd/updateht.js"></script>
        <script type="text/javascript">
            var path = '<z:path/>';
            var mark=${param.mark};//判断是添加还是修改
            var htid=${param.htid};//需要修改的回贴id
        </script>
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" id="mainform">
            <input type="reset" class="easyui-linkbutton" style="text-align: center" value="重置"/>
            <input type="button" class="easyui-linkbutton" style="text-align: center" value="提交" onclick="submitForm()"/>
            <fieldset>
                <legend style="color: #0046D5;" >回贴基本信息:</legend>  
                <input type="hidden" id="get_htid" name="htid"/>
                主贴标题:<input type="text" id="tzbt" name="tzbt" disabled/>
                回帖人:<input  id="htr" name="htr" disabled />
                <br/>
                <textarea  id="htnr" name="htnr"></textarea>   
            </fieldset>
        </form>
    </body>
</html>
