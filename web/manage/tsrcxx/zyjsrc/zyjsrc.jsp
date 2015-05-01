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
                <table>
                    <thead>
                        <tr>
                            <th width="20%"></th><th width="30%"></th><th width="20%"></th><th width="30%"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr><td >单位名称:</td><td width="30%"><input class="headMsg" name="dwmc" style="width:300px"  id="dwmc" readonly="true"/></td>
                            <td width="20%">姓名:</td><td width="30%"><input class="headMsg"  name="xm" id="xm" readonly="true"/></td>
                        </tr>
                        <tr><td>身份证(护照):</td><td><input class="headMsg"  name="zjlb"  id="zjhm" readonly="true"/></td>
                            <td>性别:</td><td><input class="headMsg"  name="xb"  id="xb" readonly="true"/></td>
                    </tbody>
                </table>
                <form id="zyjsrc" method="post">
                    <fieldset style="border:#D3D3D3 solid 1px;margin: 10px;height: 450px;">
                        <legend style="margin:5px 25px 10px;font-weight: bold;">专技人才信息</legend>
                        <table>
                        <tr><td>从事专业:</td><td><input type="text" name="cszy"  id="cszy"/></td>
                            <td>工作岗位:</td><td><span><input type="text" name="gzgw" id="gzgw"/></span></td>
                        </tr>
                        <tr><td>职称类别:</td><td><input class="easyui-combobox" name="zclbbm" type="text"  id="zclb"/></td>     
                            <td>技术职称:</td><td><span><input class="easyui-combobox" name="zcbm" type="text"  id="jszc"/> </span></td>
                        </tr>
                        </table>
                        <p style="text-align: center">获得成果、奖励（含授予荣誉称号）情况</p>
                <textarea style="width: 610px;height: 200px" name="hjcq" id="hjcq"></textarea>
                </fieldset>
                </form>
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
