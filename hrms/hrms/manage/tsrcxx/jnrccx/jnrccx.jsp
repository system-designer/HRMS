<%-- 
    Document   : 该技能人才查询
    Created on : 2013-11-24, 15:12:23
    Author     : 肖雪勇
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/icon.css">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/mycss/i-style.css">
        <script type="text/javascript" src="<z:path/>js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>  
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="baidu_input/baidu.js"></script>
        <link rel="stylesheet" type="text/css" href="baidu_input/baidu.css"/>
        <script type="text/javascript">
            var path="<z:path/>manage/tsrcxx/rcxx/SearchAction.jsp";
            var downPath="<z:path/>manage/tjbb/HrmsTjbbAction.jsp?mode=DOWNLOADEXCEL&mark=3";
        </script>
        <script type="text/javascript" src="jnrccx.js"></script>
        <style type="text/css">
            body{
                font-size: 16px;
            }
            .search_dwrc{
                position: absolute;
                left: 700px;
                top: 93px;
                width: 200px;
                z-index: 99;
                visibility: hidden
            }
        </style>
        <title>高技能人才查询</title>
    </head>
    <div id="search_div" class="search_dwrc"></div>
    <body class="easyui-layout">
        
        <div region="north" title="查询" style="height: 165px;">
            <fieldset style="border:#D3D3D3 solid 1px;margin: 10px;height: 100px;">
                <legend style="margin:5px 25px 10px;font-weight: bold;">查询条件</legend>
                <form id="jnrcForm" method="post" action="<z:path/>manage/tjbb/HrmsTjbbAction.jsp?mode=DOWNLOADEXCEL&mark=3">
                    <table border="0">
                        <tr>
                            <td><span style="margin-left: 2px;">单位性质：</span></td>
                            <td><input id="dwxz" name="dwxz" style="width:100px;"/></td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;<span>单位级别：</span></td>
                            <td><input id="dwjb" name="dwjb" style="width:100px;"/></td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;<span>所属行业：</span></td>
                            <td><input id="sshy" name="sshy" style="width:100px;"/></td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;<span>单位名称：</span></td>
                            <td><input type="text" name="dwmc" id="dwmc" value="输入单位名称的前若干字" style="width: 200px;height: 20px"></td>
                        </tr>
                        <tr>
                            <td><span>学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历：</span><input type="hidden" id="hidden_xlys" name="hidden_xlys"/></td>
                            <td><input id="xl" name="xl" style="width:100px;"/>
                                <input type="checkbox"  id="xlys" name="xlys" value="true"><span>以上</span></td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;<span>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：</span></td>
                            <td><input type="text" id="nl1" name="nl1" style="width: 38px">～<input id="nl2" type="text" name="nl2" style="width: 38px"></td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:" class="easyui-linkbutton" iconCls="icon-search" onclick="searchfor()">查询</a></td>
                        </tr>
                    </table>
                </form>
            </fieldset>
            
        </div>
        <div id="search_div" class="search_dwrc"></div>
        <div region="center" >
            <div id="jnrcList"  toolbar="#tools"></div>
            <div id="tools"  toolbar="#tools">
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="initDatagrid()">显示所有</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="downExcel()" iconCls="icon-back">导出</a>
            </div>
        </div>       
    </body>
</html>
