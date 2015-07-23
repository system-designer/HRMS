<%-- 
    Document   : jbxxtj
    Created on : 2013-11-10, 18:47:24
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://opdps.hbnu.edu.cn/jplus" prefix="z"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>基本信息添加</title>
        <link type="text/css" rel="stylesheet" href="<z:path/>js/jquery-easyui-1.3.2/themes/icon.css"/>
        <link type="text/css" rel="stylesheet" href="<z:path/>js/jquery-easyui-1.3.2/themes/default/easyui.css"/>
        <link rel="stylesheet" type="text/css" href="<z:path/>manage/jbrcxx/baidu_input.css">
        <script type="text/javascript" src="<z:path/>js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="<z:path/>manage/jbrcxx/jbrcxx.js"></script>
        <script type="text/javascript" src="<z:path/>manage/js/util.js"></script>
        <script type="text/javascript" src="<z:path/>manage/tsrcxx/baidu_input/baidu_input.js"></script>
        <script type="text/javascript">
            var path='<z:path/>';
            var mode="";
            //下面是添加百度搜索效果需要的全局变量
            var input_id="dwxx_searchbox";//输入框id
            var submit_id="submit";//提交按钮的id
            var urlPattern="manage/jbrcxx/JbrcxxAction.jsp?mode=BAIDUINPUT";//提交路径
        </script>
        <style>
            *{
                margin: 0px;
                padding: 0px;
            }
            table tr{
                height:30px;
            }
            table tr td{
                padding-left: 8px;
                font-size: 13px;
            }
        </style>
    </head>
    <body >
        <%--单位列表--%>
        <div id="dwxx" toolbar="#dwxx_gridTools"></div>
        <div id="dwxx_gridTools" style="padding:5px;height:auto">
            <div style="margin-bottom:5px" >
                <a style="float:left;" href="javascript:void(0);" class="easyui-linkbutton" onclick="loadAllDw()" iconCls="icon-back" plain="true">所有单位</a>
                <div style="float:left;margin-left: 5px;" id="baidu_search"><input type='text' size="35" id='dwxx_searchbox' name="dwxx_searchValue"/><a id="submit" href="javascript:void(0);" class="easyui-linkbutton" onclick="dwxxsearch()" iconCls="icon-search" plain="true">查找</a></div>
                <div style="clear: both"></div>
            </div>
        </div>
        <%--人才信息列表--%>
        <div id="rcxx" toolbar="#rcxx_gridTools" style="display: none"></div>
        <div id="rcxx_gridTools" style="padding:5px;height:auto;display:none">
            <div style="margin-bottom:5px" >
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="addRy()" iconCls="icon-add" plain="true" style="float:left;">添加人员</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="deleteRy()" iconCls="icon-remove" plain="true" style="float:left;">删除人员</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="loadAllRy()" iconCls="icon-back" plain="true" style="float:left;">所有人员</a>
                <div style="margin-top: 2px;margin-left: 10px;float:left;"><input  type='text' id='rcxx_searchbox' /></div>
                <div style="clear:both;"></div>   
            </div>
            <div id="rcxx_search_params" style="width:120px;float:right;">    
                <div name="xm" style="font-size: 18px;">姓名</div>   
            </div> 
        </div>
        <%--修改基本人才信息的dialog--%>
        <div class="easyui-dialog" title="添加/修改人员" id="rcxx_dialog" style="padding:10px;width:950px;" closed="true" modal="false">
            <input type="hidden" id="hidden_gzdwid"/><input type="hidden" id="hidden_dwmc"/>
            <form method="post" id="rcxx_form">
                <input type="hidden" id="ryid" name="ryid"/>
                <div class="main">
                    <div class="top">
                        <div class="left" style="float:left;">
                            <fieldset style="border:#99BBE8 solid 1px;">
                                <legend style="color: #0046D5;margin-left: 50px;" >基本信息</legend>
                                <table>
                                    <tr><td style="text-align: center;"><a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitForm()" iconCls="icon-save" plain="true"><span id="submit_a">修改</span></a></td></tr>
                                    <tr><td width="80">单位名称:</td><td><span id="dwmc"></span><input type="hidden" id="gzdwid" name="gzdwid"/></td></tr>
                                    <tr> <td >姓&nbsp;&nbsp;&nbsp;&nbsp;名:</td><td ><input id="xm" name="xm" type="text" class="easyui-validatebox" required="true" missingMessage="请输入姓名"/></td><td >性&nbsp;&nbsp;&nbsp;&nbsp;别:</td><td ><input id="xb1" name="xb" value="男" type="radio"  class="easyui-validatebox" required="true" missingMessage="性别不能为空"/><span style="padding-left: 10px;"><label  for="xb1">男</label></span><input id="xb2" name="xb" value="女" type="radio" style="margin-left: 20px;" class="easyui-validatebox" required="true" missingMessage="性别不能为空"/><span style="padding-left: 10px;"><label for="xb2">女</label></span></td><td >出生日期:</td><td ><input id="csrq" name="csrq" style="width:auto;height:20px" class="easyui-datebox" class="easyui-validatebox" required="true" missingMessage="请输入出生日期"/></td></tr>
                                    <tr> <td >民&nbsp;&nbsp;&nbsp;&nbsp;族:</td><td ><input id="mz1" type="radio" name="mz" value="1"/><label for="mz1">汉族</label> <input id="mz2" type="radio" name="mz" value="2"/><label for="mz2">少数民族</label></td><td >政治面貌:</td><td ><input id="zzmmbm" name="zzmmbm" class="easyui-combobox" style="width:150px"/></td><td >入党时间:</td><td ><input id="rdsj"  name="rdsj" class="easyui-datebox" style="width:150px;height:20px"/></td></tr>
                                    <tr> <td colspan="2">身份证(护照)号码:<input id="zjhm" name="zjhm" type="text" class="easyui-validatebox" required="true" missingMessage="请输入合法的身份证(护照)号码"/></td><td >学&nbsp;&nbsp;&nbsp;&nbsp;历:</td><td colspan="3"><input id="xlbm" name="xlbm" type="text" class="easyui-combobox"/><input id="xxxs1" name="xxxs" value="1" type="radio" style="margin-left: 20px;"/><span style="padding-left: 10px;"><label for="xxxs1">全日制</label></span><input id="xxxs2" name="xxxs" type="radio" value="2" style="margin-left: 20px;"/><span style="padding-left: 10px;"><label for="xxxs2">在职</label></span></td></tr>
                                    <tr> <td >学&nbsp;&nbsp;&nbsp;&nbsp;位:</td><td ><input id="xwbm" name="xwbm" style="width:auto;height:20px" class="easyui-comboboxbox"/></td><td >毕业学校:</td><td ><input id="byxx" name="byxx" type="text"/></td><td >专业名称:</td><td ><input id="zymc" name="zymc" type="text"/></td></tr>
                                   
                                </table>
                            </fieldset>
                        </div>
                        <div id="zp_div" class="right" style="margin-top: 10px;margin-left: 10px;float:left;text-align: center;">
                            <div id="zp" style="width:150px;height:200px;border:1px solid #cccccc;"></div>
                            <span>照片</span>
                        </div>
                        <div style="clear:both;"></div>    
                    </div>
                    <div class="bottom">
                        <fieldset style="border:#99BBE8 solid 1px;">
                            <legend style="color: #0046D5;margin-left: 50px;" >其他信息</legend>
                            <table style="margin-bottom: 10px;">
                                <tr> <td>籍&nbsp;&nbsp;&nbsp;&nbsp;贯:</td><td ><input id="jg" name="jg" type="text"style="width:225px;"/></td><td >户籍所在地:<input id="hjszd" name="hjszd" type="text" style="width:150px;"/></td></tr>
                                <tr><td>健康状况:</td><td><input id="jkzk" name="jkzk" type="text" style="width:225px;"/></td><td>婚姻状况&nbsp;:<input id="hyzkbm1" name="hyzkbm" type="radio" value="1" style="margin-left: 3px;"/><label for="hyzkbm1">未婚&nbsp;</label><input id="hyzkbm2" name="hyzkbm" type="radio" value="2"/><label for="hyzkbm2">已婚&nbsp;</label><input id="hyzkbm3" name="hyzkbm" type="radio" value="3"/><label for="hyzkbm3">离异&nbsp;</label></td></tr>
                                <tr><td>通信地址:</td><td><input id="txdz" name="txdz" type="text" style="width:225px;"/></td><td>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编:<input id="yzbm" name="yzbm" type="text" style="width:150px;"/></td></tr>
                                <tr><td >职&nbsp;&nbsp;&nbsp;&nbsp;级:</td><td><input id="zjbm" name="zjbm" type="text" style="width:225px;margin-left:73px;"/></td><td>职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务:<input id="zw" name="zw" type="text" style="width:100px;"/></td></tr>
                                <tr><td>人才类型:</td>
                                    <td colspan="3" ><input id="sfdzrc" name="sfdzrc" type="checkbox" /><span style="padding-left: 5px;"><label for="sfdzrc">党政人才</label></span><input id="sfglrc" name="sfglrc" type="checkbox" style="margin-left: 15px;"/><span style="padding-left: 5px;"><label for="sfglrc">企业经营管理人才</label></span><input id="sfzjrc" name="sfzjrc" type="checkbox" style="margin-left: 15px;"/><span style="padding-left: 5px;"><label for="sfzjrc">专业技术人才</label></span>
                                        <input id="sfgjnrc" name="sfgjnrc" type="checkbox" style="margin-left: 15px;"/><span style="padding-left: 5px;"><label for="sfgjnrc">高技能人才</label></span><input id="sfncsyrc" name="sfncsyrc" type="checkbox" /><span style="padding-left: 5px;"><label for="sfncsyrc">农村实用人才</label></span><input id="sfshgzrc" name="sfshgzrc" type="checkbox" style="margin-left: 15px;"/><span style="padding-left: 5px;"><label for="sfshgzrc">社会工作人才</label></span><input id="sfcyrc" name="sfcyrc" type="checkbox" style="margin-left: 15px;"/><span style="padding-left: 5px;"><label for="sfcyrc">创业人才</label></span></td></tr>
                                <tr id="xmflbm_tr"><td>项目分类:</td><td><input id="xmflbm" name="xmflbm" type="text" class="easyui-combobox" style="width:225px;"/></td></tr>
                            </table>
                        </fieldset>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
