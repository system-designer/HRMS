<%-- 
    Document   : zyjsrcSearch
    Created on : 2013-11-20, 15:30:38
    Author     : star
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link  rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/default/easyui.css"/>
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/icon.css"/>
        <script type="text/javascript" src="<z:path/>js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="<z:path/>manage/js/util.js"></script>
        <script type="text/javascript" src="<z:path/>manage/tsrcxx/zyjsrccx/zyjsrccx.js"></script>
        <script>
            var path = "<z:path/>";
            //下面是添加百度搜索效果需要的全局变量
            var input_id = "dwmc";//输入框id
            var submit_id = "submit";//提交按钮的id
            var urlPattern = "manage/tsrcxx/zyjsrccx/ZyjsrcSearch.jsp?mode=GETDWMC";//提交路径
        </script>
        <title>专业技术人才查询</title>
    </head>
    <body class="easyui-layout"  style="position: relative">
        <div region="north" title="条件查询" style="height: 170px;">
            <fieldset style="border:#D3D3D3 solid 1px;margin: 10px;height: 100px;">
                <legend style="margin:5px 25px 10px;font-weight: bold;">查询条件</legend>
                <form id="zjrc" method="post" action="<z:path/>manage/tjbb/HrmsTjbbAction.jsp?mode=DOWNLOADEXCEL&mark=2">

                    <table border="0">
                        <tr>
                            <td style="margin-left: 2px;">单位性质：</td>
                            <td><select id="dwxz" class="easyui-combobox" name="dwxz" style="width:100px;">
                                </select></td>
                            <td style="margin-left: 2px;">单位级别：</td>
                            <td><select id="dwlb" class="easyui-combobox" name="dwlb" style="width:100px;">
                                </select></td>
                            <td >所属系统：</td>
                            <td><select id="ssxt" class="easyui-combobox" name="ssxt" style="width:100px;">
                                </select></td>
                            <td ><span>职称类别：</span></td>
                            <td><select id="zclb" class="easyui-combobox" name="zclb" style="width:100px;"></select></td>
                            <td ><span>单&nbsp;&nbsp;&nbsp;位：</span></td>
                            <td><div id="baidu_search"><input type="text" name="dwmc" id="dwmc" style="width: 160px"></div></td>
                        </tr>
                        <tr>
                            <td ><span>技术职称：</span></td>
                            <td><select id="zc" class="easyui-combobox" name="zc" style="width:100px;"></select>
                                <input type="checkbox"  id="zcys" name="zcys" ><span>以上</span>&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" id="hidden_zcys" name="hidden_zcys"/></td>
                            <td ><span>学&nbsp;&nbsp;&nbsp;&nbsp;位：</span></td>
                            <td><select id="xw" class="easyui-combobox" name="xw" style="width:100px;"></select>
                                <input type="checkbox"  id="xwys" name="xwys" ><span>以上</span>&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" id="hidden_xwys" name="hidden_xwys"/></td>
                            <td ><span>学&nbsp;&nbsp;&nbsp;&nbsp;历：</span></td>
                            <td><select id="xl" class="easyui-combobox" name="xl" style="width:100px;"></select>
                                <input type="checkbox"  id="xlys" name="xlys"><span>以上</span>&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" id="hidden_xlys" name="hidden_xlys"/></td>
                            <td ><span>年&nbsp;&nbsp;&nbsp;&nbsp;龄：</span></td>
                            <td><input type="text" id="nl1" name="nl1" style="width: 38px">～<input id="nl2" type="text" name="nl2" style="width: 38px"></td>
                            <td></td>
                            <td ><a href="javascript:" id="submit" class="easyui-linkbutton" iconCls="icon-search" onclick="searchAll();">查询</a></td>
                        </tr>
                    </table>
                </form>
            </fieldset>           
        </div>
        <div region="center" >
            <div id="jbxx" title="专技人才查询" toolbar="#jbxx_gridTools">              
            </div> 
            <div id="jbxx_gridTools" style="padding:5px;height:auto">
                <div style="margin-bottom:5px" >
                    <a style="float:left;" href="javascript:void(0);" class="easyui-linkbutton" onclick="tjbb()" iconCls="icon-back" plain="true">导出</a>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript" src="<z:path/>manage/tsrcxx/baidu_input/baidu_input.js"></script>
    <link rel="stylesheet" type="text/css" href="<z:path/>manage/tsrcxx/zyjsrccx/baidu_input.css"/>
</html>
