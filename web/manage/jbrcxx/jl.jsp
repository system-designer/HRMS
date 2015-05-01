<%-- 
    Document   : jl
    Created on : 2013-11-16, 14:14:14
    Author     : Administrator
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
        <script type="text/javascript" src="<z:path/>ckeditor/ckeditor.js"></script>
        <script type="text/javascript" src="<z:path/>manage/jbrcxx/jl.js"></script>
        <script type="text/javascript" src="<z:path/>manage/js/util.js"></script>
        <title>简历</title>
        <script type="text/javascript">
            var path='<z:path/>';
            var mode;
            var ryid;
            $(function(){
                ryid='${param.ryid}';
                $("#ryid").val(ryid);
                $("#dwmc").text('${param.dwmc}');
                init();
            })
            function printView()
            {
                window.document.body.innerHTML = $("#printArea").html();
                window.print();
                window.document.body.innerHTML = $("body").html();
            }
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
                font-size: 18px;
                line-height:30px;
            }
        </style>
    </head>
    <body>
        <div id="printArea">
            <div>
                <table id="jbxx_table">
                    <tr>
                        <td style="text-align: center;"><a href="javascript:void(0);" class="easyui-linkbutton" onclick="submitForm()" iconCls="icon-save" plain="true">保存</a></td>
                        <td id="delete_button"><a href="javascript:void(0);" class="easyui-linkbutton" onclick="deleteJl()" iconCls="icon-remove" plain="true">删除</a></td>
                        <td><a href="javascript:void(0);" class="easyui-linkbutton" onclick="clearForm()" iconCls="icon-redo" plain="true">重置</a></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td width="180">单位名称：</td><td><span  id="dwmc"></span></td>
                        <td >姓名：</td><td ><span id="xm" ></span></td>
                        <td >性别：</td><td ><span  id="xb"></span></td>
                    </tr>
                    <tr>
                        <td >身份证（护照）号码：</td><td><span id="zjhm"></span></td>
                        <td >职级：</td><td><span id="zj"></span></td>
                        <td ><span style="padding-left: 10px;">职务：</span></td><td><span id="zw"></span></td>
                    </tr>
                </table>
            </div>
            <div style="margin-top: 10px;">
                <form id="jlxx_form" method="post" class="easyui-form">
                    <input type="hidden" id="ryid" name="ryid"/>
                    <span style="margin-left: 250px;">学习简历（含参加培训情况）</span>
                    <textarea id="xxjl" name="xxjl"></textarea>
                    <span style="margin-left: 340px;margin-top: 20px;">工作简历</span>
                    <textarea id="gzjl" name="gzjl"></textarea>
                    <span style="margin-left: 340px;margin-top: 20px;">奖惩情况</span>
                    <textarea id="jcqk" name="jcqk"></textarea>
                </form>
            </div>
        </div>
        <div align="center">
            <input type="button" name="print" value="预览并打印" onclick="printView()">
        </div>
    </body>
</html>
