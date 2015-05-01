<%-- 
    Document   : zyjsrc
    Created on : 2013-11-13, 19:10:38
    Author     : 星星
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="<z:path/>js/jquery-easyui-1.3.2/themes/default/easyui.css"/>
        <link type="text/css" rel="stylesheet" href="<z:path/>js/jquery-easyui-1.3.2/themes/icon.css"/>
        <script type="text/javascript" src="<z:path/>js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="<z:path/>manage/tsrcxx/zyjsrc/zyjsrc.js"></script>
        <script type="text/javascript" src="<z:path/>ckeditor/ckeditor.js"></script>
        <script type="text/javascript" src="<z:path/>manage/jbrcxx/jl.js"></script>
        <script type="text/javascript" src="<z:path/>manage/js/util.js"></script>
        <title>专业技术人才</title>
        <script type="text/javascript">
            var zyjsrcid;
            var path = "<z:path/>";
            var ryid = '${param.ryid}';
            var sfzjrc;
        </script>
        <style>
            .headMsg{
                border-left:0px;
                border-top:0px;
                border-right:0px;
                border-bottom:1px;
                background-color: white;
                color: black;
            }
        </style>
    </head>
    <body>
        <div id="xx" title=""  style="height:500px;width: 820px;" closable="false" draggable="false">
            <div style="font-size: 15px">
                单位名称:&nbsp;<input class="headMsg" name="dwmc" style="width: 200px" id="dwmc" readonly="true"/>
                &nbsp;姓&nbsp;&nbsp;名:&nbsp;<input class="headMsg"  name="xm" style="width: 138px" id="xm" readonly="true"/>&nbsp;&nbsp;
                性&nbsp;别:&nbsp;<input class="headMsg"  name="xb" style="width: 80px" id="xb" readonly="true"/>
                <br>
                <br>
                身份证(护照)号码:&nbsp;<input class="headMsg"  name="zjlb" style="width: 477px" id="zjhm" readonly="true"/>
                <br>
                <p style="text-align: center">专业技术人才</p>
                从事专业<input type="text" name="cszy" style="width: 220px" id="cszy"/>
                <span style="margin-left: 180px">工作岗位&nbsp;<input type="text" name="gzgw" style="width: 200px" id="gzgw"/></span>
                <br>
                <br>
                职称类别<input class="easyui-combobox" type="text"  id="zclb"/>     
                <span style="margin-left:250px">技术职称&nbsp;<input class="easyui-combobox" type="text"  id="jszc"/> </span> 
                <p style="text-align: center">获得成果、奖励（含授予荣誉称号）情况</p>
                <textarea style="width: 610px;height: 200px" name="hjcq" id="hjcq"></textarea>
                <br>
                <div id="button">
                    <center>
                    <a class="easyui-linkbutton" onclick="add()" id="add">添加</a>
                    <a class="easyui-linkbutton" onclick="add()" id="update">修改</a>
                    <a class="easyui-linkbutton" onclick="del()" id="dell">删除</a>
                    </center>
                </div>
                <div style="clear:both"></div>
            </div>  
        </div>
    </body>
</html>
