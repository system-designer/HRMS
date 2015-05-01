<%-- 
    Document   : cyrc
    Created on : 2013-11-14, 18:52:58
    Author     : Raglia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>创业人才</title>
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/icon.css">
        <script type="text/javascript" src="<z:path/>js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>  
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="<z:path/>manage/tsrcxx/cyrc/cyrc.js"></script>
        <script type="text/javascript">
            var path = '<z:path/>';
            var ryid = '${param.ryid}';  //从前面传过来的人员id
        </script>
        <style>
            .headMsg{
                border-left:0px;
                border-top:0px;
                border-right:0px;
                border-bottom:1px;
                background-color: white;
                /*font-weight: bold;*/
            }
        </style>
    </head>
    <body>
        <div title="创业人才信息" class="easyui-dialog"  id="cyrc_dialog"  closable="false" draggable="false" style="padding:10px;width: 830px;">
            <table id="table1">
                <tr>
                    <td>单位名称：</td><td><input id="dwmc" class="headMsg" style="width:180px;height:20px;" readonly="true" name="dwmc" /></td>
                    <td>姓名：</td><td><input id="xm" class="headMsg" name="xm" readonly="true" style="width:auto;height:20px"/></td>
                    <td>性别：</td><td><input id="xb" class="headMsg" name="xb" readonly="true" style="width:auto;height:20px" /></td>
                </tr>
                <tr>
                    <td style="width:180px">身份证（护照）号码：</td><td><input id="zjhm" class="headMsg" style="width:180px;height:20px" readonly="true" name="zjhm" /></td>
                </tr>
            </table>
            <fieldset style="border:#D3D3D3 solid 1px;margin: 10px;height: 315px;">
                <legend style="margin:5px 25px 10px;font-weight: bold;">创业人才信息</legend>
                <form method="post" id="cyrctj">
                    <div>
                        <table id="table2">
                            <tr>
                                <td>原任职机构：</td>
                                <td><input id="yrzjg" name="yrzjg" style="width:270px;height:20px"/></td>
                                <td>原职务：</td>
                                <td><input id="yzw" name="yzw" style="width: 270px;height: 20px"/></td>
                            </tr>
                            <tr>
                                <td>在黄工作时间：</td>
                                <td><input id="zhgzsj" name="zhgzsj" style="width:270px;height:20px"/></td>
                                <td>电子邮箱：</td>
                                <td><input id="email" name="email" style="width: 270px;height: 20px"/></td>
                            </tr>
                            <tr>
                                <td>原居住地：</td>
                                <td colspan="3"><input id="yjzdz" name="yjzdz" style="width: 570px;height: 20px"/></td>
                            </tr>
                        </table>
                        <div>
                            <br><center>重要荣誉或重要奖项</center><br>
                            <textarea id="zyry" name="zyry" style="width: 750px;height: 150px"></textarea>
                        </div>
                    </div>
                    <div id="dialog_buttons" style="text-align: center" >
                        <a id="dialog_update" href="javascript:" onclick="update();" style="display: none" class="easyui-linkbutton" iconCls="icon-ok">修改</a>
                        <a id="dialog_add" href="javascript:"onclick="add();" style="display: none" class="easyui-linkbutton" iconCls="icon-add">添加</a>
                        <a id="dialog_delete" href="javascript:" onclick="del();" style="display: none" class="easyui-linkbutton" iconCls="icon-cancel">删除</a>
                    </div>
                </form>
            </fieldset>
        </div>
    </div>
</body>
</html>