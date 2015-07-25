<%-- 
    Document   : qyglrccx
    Created on : 2013-12-1, 13:46:50
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="z" uri="http://opdps.hbnu.edu.cn/jplus"%>
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
        <script type="text/javascript" src="<z:path/>manage/tsrcxx/qyglrccx/qyglrccx.js"></script>
        <script type="text/javascript" src="<z:path/>manage/js/util.js"></script>
        <title>JSP Page</title>
        <script type="text/javascript">
            var path='<z:path/>';
            //            下面是添加百度搜索效果需要的全局变量
            var input_id="gzdw";//输入框id
            var submit_id="submit";//提交按钮的id
            var urlPattern="manage/tsrcxx/qyglrc/QyglrccxAction.jsp?mode=BAIDUINPUT";//提交路径
        </script>
    </head>
    <body class="easyui-layout">   
        <div region="north" title="查询" style="height: 165px;">
            <fieldset style="border:#D3D3D3 solid 1px;margin: 10px;height: 100px;">
                <legend style="margin:5px 25px 10px;font-weight: bold;font-size: 13px">查询条件</legend>
                <form id="cxtj" method="post" action="<z:path/>manage/tjbb/HrmsTjbbAction.jsp?mode=DOWNLOADEXCEL&mark=7">
                    <table border="0">
                        <tr>
                            <td style="padding-left: 30px;"><span >单位性质：</span></td>
                            <td><select id="dwxz" class="easyui-combobox" name="dwxz" style="width:100px;">
                                </select></td>
                            <td style="padding-left: 30px;"><span >单位级别：</span></td>
                            <td><select id="dwlb" class="easyui-combobox" name="dwlb" style="width:100px;">
                                </select></td>
                            <td style="padding-left: 30px;"><span >所属行业：</span></td>
                            <td><select id="sshy" class="easyui-combobox" name="sshy" style="width:100px;">
                                </select></td>
                            <td style="padding-left: 30px;"><span >单位：</span></td>
                            <td><div id="baidu_search"><input type="text" name="gzdw" id="gzdw" style="width: 120px;position:absolute;top:75px;left:791px;margin-left: 5px"></div></td>
                        </tr>
                        <tr>
                            <td style="padding-left: 30px;"><span >政治面貌：</span></td>
                            <td><select id="zzmm" class="easyui-combobox" name="zzmm" style="width:100px;"></select></td>
                            <td style="padding-left: 30px;"><span >职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</span></td>
                            <td><select id="zj" class="easyui-combobox" name="zj" style="width:100px;"></select>
                                <input type="checkbox"  id="zjys" name="zjys" ><span>以上</span><input type="hidden" id="hidden_xlys" name="hidden_zjys"/></td>
                            <td style="padding-left: 30px;"><span >学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历：</span></td>
                            <td><select id="xl" class="easyui-combobox" name="xl" style="width:100px;"></select>
                                <input type="checkbox"  id="xlys" name="xlys"><span>以上</span><input type="hidden" id="hidden_xlys" name="hidden_xlys"/></td>
                            <td style="padding-left: 30px;"><span >年龄：</span></td>
                            <td><input type="text" id="nl1" name="nl1" style="width: 36px">～<input id="nl2" type="text" name="nl2" style="width: 36px">
                                <a href="javascript:" class="easyui-linkbutton" iconCls="icon-search" id="submit">查询</a></td>
                        </tr>
                    </table>
                </form>
            </fieldset>           
        </div>
        <div region="center">
            <div id="jbxx" title="企业管理人才查询" toolbar="#jbxx_gridTools">              
            </div>  
            <div id="jbxx_gridTools" style="padding:5px;height:auto">
                <div style="margin-bottom:5px" >
                    <a style="float:left;" href="javascript:void(0);" class="easyui-linkbutton" onclick="tjbb()" iconCls="icon-back" plain="true">导出</a>
                </div>
            </div>
        </div>
    </body>
</html>
