<%-- 
    Document   : ncsyrccx
    Created on : 2013-11-20, 14:42:38
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="z" uri="http://opdps.hbnu.edu.cn/jplus" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/default/easyui.css"/>
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/icon.css">
        <link rel="stylesheet" type="text/css" href="<z:path/>manage/tsrcxx/baidu_input/baidu_input.css"> 
        <script type="text/javascript" src="<z:path/>js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<z:path/>manage/tsrcxx/baidu_input/baidu_input.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="<z:path/>manage/tsrcxx/ncsyrccx/ncsyrccx.js"></script>
        <script type="text/javascript" src="<z:path/>manage/js/util.js"></script>
        <script type="text/javascript">
            var path = '<z:path/>'
            var input_id = "gzdw";//输入框id
            var submit_id = "submit";//提交按钮的id
            var urlPattern = "manage/tsrcxx/ncsyrc/NcsyrccxAction.jsp?mode=BAIDUINPUT";//提交路径
        </script>
        <title>JSP Page</title>
    </head>
    <body class="easyui-layout">
        <div region="north" title="查询" style="height: 165px;">
            <fieldset style="border:#D3D3D3 solid 1px;margin: 10px;height: 100px;">
                <legend style="margin:5px 25px 10px;font-weight: bold;font-size: 13px">查询条件</legend>
                <form id="cxtj" method="post" action="<z:path/>manage/tjbb/HrmsTjbbAction.jsp?mode=DOWNLOADEXCEL&mark=4">
                    <table border="0">
                        <tr>
                            <td style="padding-left: 30px;"><span >单位性质：</span></td>
                            <td><select class="easyui-combobox" id="dwxz" name="dwxz" style="width:100px"></select></td>
                            <td style="padding-left: 30px;"><span >单位类别：</span></td>
                            <td><select class="easyui-combobox" id="dwlb" name="dwlb" style="width:100px"></select></td>
                            <td style="padding-left: 30px"><span >人才类别：</span></td>
                            <td><select class="easyui-combobox" id="rclb" name="rclb" style="width:100px"></select></td>
                            <td style="padding-left: 30px"><span >单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：</span></td>
                            <td><div id="baidu_search"><input type="text" name="gzdw" id="gzdw" style="width: 200px;position:absolute;top:75px;left:770px;"></div></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 30px"><span >上年纯收入：</span></td>
                            <td><select class="easyui-combobox" id="sncsr" style="width:100px"></select></td>
                            <td style="padding-left: 30px"><span >学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历：</span></td>
                            <td><select class="easyui-combobox" id="xl" name="xl" style="width:100px"></select><input type="checkbox" id="xlys"><span>以上</span><input type="hidden" id="hidden_xlys" name="hidden_xlys"/></td>
                            <td style="padding-left: 30px"><span >年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：</span></td>
                            <td><input type="text" style="width:36px;" id="nl1" >～<input type="text" style="width:36px;" id="nl2"></td>
                            <td style="padding-left: 30px"><a href="javascript:" class="easyui-linkbutton" iconCls="icon-search" id="submit">查询</a></td>
                        </tr>
                    </table>  
                </form>
            </fieldset>
        </div>
        <div region="center">
            <div id="jbxx" class="easyui-dategrid" title="农村实用人才查询" toolbar="#jbxx_gridTools"></div>
            <div id="jbxx_gridTools" style="padding:5px;height:auto">
                <div style="margin-bottom:5px" >
                    <a style="float:left;" href="javascript:void(0);" class="easyui-linkbutton" onclick="tjbb()" iconCls="icon-back" plain="true">导出</a>
                </div>
            </div>
        </div>
    </body>
</html>
