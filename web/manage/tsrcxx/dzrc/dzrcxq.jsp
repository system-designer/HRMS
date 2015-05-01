<%-- 
    Document   : dzrcxq  党政人才详情
    Created on : 2013-11-20, 20:42:54
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
        <script type="text/javascript" src="<z:path/>ckeditor/ckeditor.js"></script>
        <script type="text/javascript" src="<z:path/>manage/tsrcxx/dzrc/dzrcxq.js"></script>
        <title>党政人才详情</title>        
        <script>
            var path = '<z:path/>';
            var ryid = '${requestScope.list[0].ryid}';
        </script>
        <style>
            .headMsg{
                border-left:0px;
                border-top:0px;
                border-right:0px;
                border-bottom:1px;
                background-color: white;
                font-size: 18px;
            }
        </style>
    </head>
    <body >
        <div class="printArea" id="xq" >
            <div title="基本信息">
                <%@include file="/manage/tsrcxx/dzrc/jbxx.jsp" %>
            </div>
            <div title="党政人才信息" style="padding-left: 115px;"><br/><br/>
                <table>                    
                    <tr>
                        <td>任现职级（职务）时间：</td>
                        <td colspan="5"><input id="rxzsj" class="headMsg" name="rxzsj" value="${requestScope.list[0].rxzsj}" /></td>
                    </tr>
                    <tr>
                        <td>近一年考核情况：</td>
                        <td><input id="khqk1" class="headMsg" name="khqk1" style="width:100px;height: auto;" value="${requestScope.list[0].khqk1}" /></td>
                        <td>近二年考核情况：</td>
                        <td><input id="khqk2" class="headMsg" name="khqk2" style="width:100px;height: auto;" value="${requestScope.list[0].khqk2}" /></td>
                        <td>近三年考核情况：</td>
                        <td><input id="khqk3" class="headMsg" name="khqk3" style="width:100px;height: auto;" value="${requestScope.list[0].khqk3}" /></td>
                    </tr>
                </table>
                <div style="margin-top: 10px;font-weight: 800;font-size: 18px;">
                    <span style="margin-left: 300px;font-weight: 800;">学习简历（含参加培训情况）</span>
                    <div id="xxjl"  name="xxjl" readonly="true" style="width:810px;height: auto;border: 1px solid #D3D3D3;" >${requestScope.list[0].xxjl}</div>
                    <span style="margin-left: 340px;margin-top: 20px;font-weight: 800;">工作简历</span>
                    <div id="gzjl" name="gzjl" readonly="true" style="width:810px;height: auto;border: 1px solid #D3D3D3 ;">${requestScope.list[0].gzjl}</div>
                    <span style="margin-left: 340px;margin-top: 20px;font-weight: 800;">奖惩情况</span>                    
                    <div id="jcqk" name="jcqk" readonly="true" style="width:810px;height: auto;border: 1px solid #D3D3D3;">${requestScope.list[0].jcqk}</div>      
                </div>
            </div>
            <div title="家庭成员与社会关系" class="jtcyyshgx" style="padding-left: 120px;width:810px;">
                <span style="margin-left: 310px;font-weight: 800;font-size: 18px;">家庭成员与社会关系</span>
                <div id="jtcy" style="width: 810px" ></div>
            </div>
        </div>
    </div>
</body>
<div style="margin-left: 830px;"><a href="javascript:void(0)" onclick="printView();" style="text-decoration: none;color: #ff0000">【打印】</a></div>
</html>
