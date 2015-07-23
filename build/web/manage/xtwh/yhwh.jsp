<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.hrms.manage.permission.UserContants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<!DOCTYPE html>
<%
    pageContext.setAttribute("ucontants", new UserContants(), PageContext.PAGE_SCOPE);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>用户维护</title>
        <script type="text/javascript">var path = '<z:path/>';</script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/framePageLoader.js"></script>
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/icon.css">
        <script type="text/javascript" src="<z:path/>js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>  
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="<z:path/>manage/xtwh/yhwh.js"></script>
        <script type="text/javascript">
            function typeFormat(value, rowData, rowIndex) {
                return ('${pageScope.ucontants.SHI}'===rowData.ulevelc?'${pageScope.ucontants.userLevelMap[pageScope.ucontants.SHI]}':'${pageScope.ucontants.QU}'===rowData.ulevelc?'${pageScope.ucontants.userLevelMap[pageScope.ucontants.QU]}':'${pageScope.ucontants.QIYE}'===rowData.ulevelc?'${pageScope.ucontants.userLevelMap[pageScope.ucontants.QIYE]}':'')+('${pageScope.ucontants.ADMIN}'===rowData.utypec?'${pageScope.ucontants.userTypeMap[pageScope.ucontants.ADMIN]}':'${pageScope.ucontants.PUTONG}'===rowData.utypec?'${pageScope.ucontants.userTypeMap[pageScope.ucontants.PUTONG]}':'');
            }
        </script>
        <style type="text/css" >
            *{
                margin:0px;
                padding:0px;
            }
            body{
                margin:2px;
            }
            .datagrid-cell-font{
                font-size: 12px;
                color: #444444;
                height: 16px;
                line-height: 16px;
                display: inline-block;
                height: 16px;
                vertical-align: baseline;
                width: auto;
                margin-top: 6px;
            }
            .combo{
                margin-bottom: 4px;
            }
            .width-1{
                width: 100px;
            }
            #yhwh_crud_dialog div{
                padding: 5px;
            }
            .text-align-left{
                text-align: left;
            }
            .dialog-button{
                text-align: center;
            }
        </style>
    </head>
    <body>
        <table id="yhwh_list" toolbar="#datagrid_toolbar" pagination="true" title="用户一览表" pageSize="20" fitColumns="true" striped="true" closed="false"></table>
        <div id="datagrid_toolbar">
            <table>
                <tr>
                    <td><a class="easyui-linkbutton" href="javascript:;" onclick="add();" plain="true" iconCls="icon-add">添加用户</a></td>
                    <td><a class="easyui-linkbutton" href="javascript:;" onclick="update()" plain="true" iconCls="icon-edit">修改用户</a></td>
                    <td><a class="easyui-linkbutton" href="javascript:;" onclick="del()" plain="true" iconCls="icon-remove">删除用户</a></td>
                    <td><div class="datagrid-btn-separator"></div></td>
                    <td>
                        <span class="datagrid-cell-font">单位性质：</span>
                        <%-- i-list 对应combobx的数据在JSON中的名称--%>
                        <span><input class="easyui-combobox width-1" id="prop_search" name="prop-search" valueField="propc" textField="prop" editable="false" i-list="propList"/></span>
                        <span class="datagrid-cell-font">单位类别：</span>
                        <span><input class="easyui-combobox width-1" id="level_search" name="level-search" valueField="levelc" textField="level" editable="false" i-list="levelList"/></span>
                        <span class="datagrid-cell-font">所属系统：</span>
                        <span><input class="easyui-combobox width-1" id="dept_search" name="dep-search" valueField="deptc" textField="dept" editable="false" i-list="deptList"/></span>
                        <span class="datagrid-cell-font">所属行业：</span>
                        <span><input class="easyui-combobox width-1" id="trade_search" name="trade-search" valueField="tradec" textField="trade" editable="false" i-list="tradeList" /></span>
                    </td>
                </tr>
            </table>
        </div>
        <div id="yhwh_crud_dialog" title="添加用户信息" class="easyui-dialog" closed="true" style="width:425px;height:395px;text-align: left;padding: 10px;">
            <form method="POST" id="form_crud">
                <div>
                    <span>单位性质：</span>
                    <span><input id="prop_crud" class="easyui-combobox width-1" name="prop-crud" i-list="propList" valueField="propc" textField="prop" editable="false"/></span>
                    <span>单位类别：</span>
                    <span><input id="level_crud" class="easyui-combobox width-1" name="level-crud" i-list="levelList" valueField="levelc" textField="level" editable="false"/></span>
                </div>
                <div>
                    <span>所属系统：</span>
                    <span><input id="dept_crud" class="easyui-combobox width-1" name="dept-crud" i-list="deptList" valueField="deptc" textField="dept" editable="false"/>
                    </span>
                    <span>所属行业：</span>
                    <span><input id="trade_crud" class="easyui-combobox width-1" name="trade-crud" i-list="tradeList" valueField="tradec" textField="trade" editable="false"/></span>
                </div>
                <div class="text-align-left">
                    <span>单位名称：</span>
                    <input id="orgz_crud" class="easyui-combobox"  name="orgz-crud" style="width: 275px;height: 18px;vertical-align: middle;" i-list="orgzList" textField="orgz" valueField="orgzc"/>
                </div>
                <div class="text-align-left">
                    <span style="margin-left: 10px;">用户名：</span>
                    <input type="text" id="uname_crud" style="width: 275px;height: 18px;vertical-align: middle;" name="uname-crud"/>
                </div>
                <div class="text-align-left">
                <span>用户姓名：</span>
                    <input type="text" id="name_crud" style="width: 275px;height: 18px;vertical-align: middle;" name="name-crud"/>
                </div>
                <div class="text-align-left">
                    <span>联系电话：</span>
                    <input type="text" id="phone_crud" style="width: 275px;height: 18px;vertical-align: middle;" name="phone-crud"/>
                </div>
                <div class="text-align-left">
                    <span style="text-align: right">用户级别：</span>
                    <span id="ulevel_crud">
                        <label style="cursor: pointer">
                            <input type="radio" name="ulevel" value="${pageScope.ucontants.SHI}" style="vertical-align: middle;"/><font style="vertical-align:middle;margin: 0 8px 0 3px;">${pageScope.ucontants.userLevelMap[pageScope.ucontants.SHI]}</font>
                        </label>
                        <label style="cursor: pointer"><input type="radio" name="ulevel" value="${pageScope.ucontants.QU}" style="vertical-align: middle"/><font style="vertical-align:middle;margin: 0 8px 0 3px">${pageScope.ucontants.userLevelMap[pageScope.ucontants.QU]}</font></label>
                        <label style="cursor: pointer"><input checked type="radio" name="ulevel" value="${pageScope.ucontants.QIYE}" style="vertical-align: middle"/><font style="vertical-align:middle;margin: 0 8px 0 3px">${pageScope.ucontants.userLevelMap[pageScope.ucontants.QIYE]}</font></label>
                    </span>
                </div>
                <div class="text-align-left">
                    <span style="text-align: right">用户类别：</span>
                    <span id="utype_crud">
                        <label style="cursor: pointer"><input type="radio" name="utype" value="${pageScope.ucontants.ADMIN}" style="vertical-align: middle"/><font style="vertical-align:middle;margin: 0 8px 0 3px">${pageScope.ucontants.userTypeMap[pageScope.ucontants.ADMIN]}</font></label>
                        <label style="cursor: pointer"><input checked type="radio" name="utype" value="${pageScope.ucontants.PUTONG}" style="vertical-align: middle"/><font style="vertical-align:middle;margin: 0 8px 0 3px">${pageScope.ucontants.userTypeMap[pageScope.ucontants.PUTONG]}</font></label>
                    </span>
                </div>
            </form>
        </div>
    </body>
</html>
