<%-- 
    Document   : gwxqcx
    Created on : 2013-12-4, 22:57:51
    Author     : T420
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="z" uri="http://opdps.hbnu.edu.cn/jplus" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/icon.css">
        <script type="text/javascript" src="<z:path/>js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>  
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="<z:path/>manage/js/util.js"></script>
        <script type="text/javascript" src="<z:path/>gwxq/gwxqcx.js"></script>
        <script type="text/javascript">
            var path='<z:path/>';
            //下面是添加百度搜索效果需要的全局变量
            var input_id="gzdw";//输入框id
            var submit_id="submit";//提交按钮的id
            var urlPattern="GwxqcxAction.jsp?mode=BAIDUINPUT";//提交路径
        </script>
        <title>岗位需求查询</title>
    </head>
    <body>
        <div title="岗位需求查询" style="height: 125px;width:1135px;">
            <fieldset style="border:#D3D3D3 solid 1px;margin:5px;height: 100px;">
                <legend style="margin:5px 25px 10px;font-weight: bold;font-size: 13px">查询条件</legend>            
                <table border="0" >
                    <tr>
                        <td style="padding-left: 30px;"><span >单位性质：</span></td>
                        <td><select id="dwxz_id" class="easyui-combobox" valueField="dwxzbm" textField="dwxzmc" style="width:100px;">
                            </select></td>
                        <td style="padding-left: 30px;"><span >单位级别：</span></td>
                        <td><select id="dwlb_id" class="easyui-combobox" valueField="dwlbbm" textField="dwlbmc" style="width:100px;">
                            </select></td>
                        <td style="padding-left: 30px;"><span >单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：</span></td>
                        <td><div id="baidu_search"><input type="text" name="gzdw" id="gzdw" style="width: 200px;position:absolute;top:46px;left:615px;"></div></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 30px;"><span >所属系统：</span></td>
                        <td><select id="sshy_id" class="easyui-combobox" valueField="sshybm" textField="sshymc" style="width:100px;">
                            </select></td>
                        <td style="padding-left: 30px;"><span >需求岗位：</span></td>
                        <td><input type="text"  id="xqgw_id"style="width: 100px;" name="xqgw"></td>
                        <td style="padding-left: 30px;"><a href="javascript:" class="easyui-linkbutton" iconCls="icon-search" id="submit" onclick="loadGwxqDate()">查询</a></td>
                    </tr>
                </table>
            </fieldset>  
        </div>
        <div id="dwmccx" class="easyui-datagrid" style="width:1135px"></div>
    </body>
</html>
