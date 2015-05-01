<%-- 
    Document   : dzrc
    Created on : 2013-11-13, 19:10:09
    Author     : Xstarfct
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://opdps.hbnu.edu.cn/jplus" prefix="z"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="<z:path/>js/jquery-easyui-1.3.2/themes/icon.css"/>
        <link type="text/css" rel="stylesheet" href="<z:path/>js/jquery-easyui-1.3.2/themes/default/easyui.css"/>
        <script type="text/javascript" src="<z:path/>js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="<z:path/>manage/tsrcxx/dzrc/dzrc.js"></script>
        <title>党政人才</title>
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
        <div class="easyui-dialog" title="党政人才信息" id="dzrc_dialog" closable="false" draggable="false" style="padding:10px">
            <table id="table1">
                <tr>
                    <td>单位名称：</td><td><input class="headMsg" id="dwmc" style="width:300px;height:20px;" disabled="true" name="dwmc" /></td><td>姓&nbsp;&nbsp;&nbsp;&nbsp;名：</td><td><input class="headMsg"id="xm" name="xm" disabled="true" style="width:auto;height:20px"/></td><td>性&nbsp;&nbsp;&nbsp;&nbsp;别：</td><td><input class="headMsg"id="xb" name="xb" disabled="true" style="width:auto;height:20px" /></td>
                </tr>
                <tr>
                    <td style="width:130px">身份证（护照）号码：</td><td><input class="headMsg" id="zjhm" style="width:240px;height:20px"disabled="true" name="zjhm" /></td><td>职&nbsp;&nbsp;&nbsp;&nbsp;级：</td><td><input class="headMsg"id="zjmc" name="zjmc" disabled="true" style="width:auto;height:20px" /></td><td>职&nbsp;&nbsp;&nbsp;&nbsp;务：</td><td><input class="headMsg" id="zw" name="zw" disabled="true" style="width:auto;height:20px" /></td>
                </tr>
            </table>
            <fieldset style="border:#D3D3D3 solid 1px;margin: 10px;height: 200px;">
                <legend style="margin:5px 25px 10px;font-weight: bold;">党政人才信息</legend>
                <form id="dzrcxx" method="post">
                    <table id="table2">
                        <tr>
                            <td>任现职级（职务）时间：</td><td><input id="rxzsj" name="rxzsj" style="width:150px;height:20px"/></td> 
                        </tr>
                        <tr>
                            <td>近一年考核情况：</td><td><input id="khqk1" name="khqk1" style="width:150px;height:20px" /></td>
                        </tr>
                        <tr>
                            <td>近二年考核情况：</td><td><input id="khqk2" name="khqk2" style="width:150px;height:20px" /></td>
                        </tr>
                        <tr>
                            <td>近三年考核情况：</td><td><input id="khqk3" name="khqk3" style="width:150px;height:20px" /></td>
                        </tr>
                    </table><br/><br/>
                    <div id="dialog_buttons" style="text-align: center" >
                        <a id="dialog_button_update" href="javascript:" onclick="updatedzrc();" style="display: none" class="easyui-linkbutton" iconCls="icon-ok">修改</a>
                        <a id="dialog_button_add" href="javascript:"onclick="adddzrc();" style="display: none" class="easyui-linkbutton" iconCls="icon-add">添加</a>
                        <a id="dialog_button_delete" href="javascript:" onclick="deletedzrc();" style="display: none" class="easyui-linkbutton" iconCls="icon-cancel">删除</a>
                    </div>
                </form>
            </fieldset>
        </div>
    </body>
</html>
