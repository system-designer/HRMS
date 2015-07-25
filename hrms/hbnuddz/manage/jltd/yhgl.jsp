<%-- 
    Document   : yhgl
    Created on : 2013-3-14, 21:37:01
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<z:path/>jquery-easyui-1.2.5/themes/default/easyui.css"/>
        <link rel="stylesheet" type="text/css" href="<z:path/>jquery-easyui-1.2.5/themes/icon.css"/>
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="<z:path/>ckeditor/ckeditor.js"></script>
        <script type="text/javascript" src="<z:path/>manage/jltd/yhgl.js"></script>
        <script type="text/javascript">
            var path = '<z:path/>';
        </script>
        <title>用户</title>
    </head>
    <body>
        <div id="maindatagrid" toolbar="#gridTools"></div>
        <div id="gridTools" style="padding:5px;height:auto">
            <div style="margin-bottom:5px" id="test" >
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="editYh()" iconCls="icon-edit" plain="true">查看/修改用户信息</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="deleteYh()" iconCls="icon-remove" plain="true">删除用户</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="loadAllData()" iconCls="icon-back" plain="true">所有用户</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="sendMail()" plain="true" iconCls="icon-edit">发送邮件</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="sendAll()" plain="true" iconCls="icon-edit">发送邮件给所有用户</a>
                <input type='text' id='ss' />
            </div>
            <div id="cxtj" style="width:120px;float:right">    
                <div name="xm">用户名</div>
                <div name="zsxm">真实姓名</div>
            </div> 
        </div>
        <%--修改用户信息的窗口--%>
        <div id="add" class="easyui-dialog" closed="true" style="width:480px;" iconCls="icon-edit" modal="true" title="修改信息">
            <form method="post" id="mainform">
                <input type="reset" class="easyui-linkbutton" style="text-align: center" value="重置"/>
                <input type="button" class="easyui-linkbutton" style="text-align: center" value="提交" onclick="submitForm()"/>
                <fieldset>
                    <legend style="color: #0046D5;" >温馨提示:密码不填即为不修改,管理员请不要随意修改其他用户的密码</legend>  
                    <input type="hidden" id="get_id" name="id"/>
                    姓名:<input type="text" id="yhm" name="yhm" disabled/>
                    性别:<input type="radio" name="xb" value="男"/>男
                    <input type="radio" name="xb" value="女" />女
                    <br/>
                    真实姓名:<input type="text" id="zsxm" name="zsxm"/> 邮箱:<input type="text" id="yx" name="yx"/><br/>
                    密码:<input type="text" id="mm" name="xmm"/> 指派权限:<select class="easyui-validatebox" required="true" missingMessage="请指派权限" id="qx" name="qx"></select><br/>
                    <span>个性签名: </span><span style="margin-left:250px;" id="yhtxspan">用户图像:</span><br/><br/>
                    <textarea type="text" id="gxqm" name="gxqm" style="height:120px;width:300px;resize:none"></textarea>
                    <img id="yhtx" style="width:117px;height:120px;" />
                </fieldset>
            </form>
        </div>
        <input type="hidden" id="tempid"/>
        <%--修改用户头像的窗口--%>
        <div id="tpdia" class="easyui-dialog" closed="true" style="width:400px;height:500px;" iconCls="icon-search" modal="true">
            <center>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="viewbroswer()" iconCls="icon-search" plain="true">浏览服务器</a><br/>
                图片显示区:<span id="notice"></span><br/><br/>
                <img id="tp" src="" style="width:320px;height:380px;border:1px solid black;"/>
            </center>
        </div>
        <%--发送邮件的窗口--%>
        <div id="sendmail_dialog" class="easyui-dialog" title="Send Mail" closed="true" modal="true" style="width:600px;height:400px;">  
            <strong>发送给：</strong><br/>
            <div id="tomail" style="border:2px solid #e6effe; width: auto;height: 50px; "></div>
            <div id="mailContent">谢谢您关注担当者！</div>
            <div style="text-align: right;">
                <input  id="sentmailbutton" type="button" value="确定">
            </div>
        </div>
    </body>
</html>
