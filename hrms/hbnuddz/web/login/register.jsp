<%-- 
    Document   : exit
    Created on : 2013-3-17, 9:58:59
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="keywords" content="环保，绿源环保协会，湖北师范学院，Jplus实验室">
        <meta name="Description" content="注册后立即成为绿源环保协会网站会员">
        <meta name="author" content="刘雷，裴秀">
        <link rel="icon" href="<z:path/>web/image/favicon.ico" type="image/x-icon" />
        <link rel="Shortcut Icon" type="image/x-icon" href="<z:path/>web/image/favicon.ico" />
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/jquery-1.7.1.min.js"></script>
        <script  type="text/javascript" src="<z:path/>web/login/register.js"></script>
        <link rel="stylesheet" type="text/css"href="<z:path/>web/css/register.css"/>
        <title>注册</title>
        <script type="text/javascript">
            var path='<z:path/>';
            var judge="0";//判断用户名是否存在
        </script>
    </head>
    <body>

        <div class="all">
            <div class="denglu">
                <span style="font-size:22px;margin-right: 5px;">绿源环保协会</span>
                <span style="font-size:20px; color: #707070">会员</span>
                <span style="font-size:18px;color: #707070">-注册</span> <a href="<z:path/>IndexAction.jsp?mode=SHOWLIST" >返回首页</a></div>
            <div style="clear:both"></div>
            <div class="main">
                <div class="zc">填写会员信息</div>
                <div class="zc1">

                    <table  >
                        <tr><td>用户名：</td><td ><input type="text" class="yhm" name="yhm" id="yhm"/></td><td width="250px;"><span  id="yhmnotice" style="width:170px;height:20px;color:red"></span></td></tr>
                        <tr ><td></td><td colspan="2" >(小于20位,不能包含特殊字符)</td></tr>
                        <tr><td>性别：</td><td>男<input type="radio" value="男" name="xb" checked/>女<input type="radio" value="女" name="xb" /></td></tr>
                        <tr><td></td><td colspan="2">(请填写您的性别)</td></tr>
                        <tr><td>密码：</td><td><input type="password" name="mm" id="mm"/></td><td><span  id="mmnotice" style="width:170px;height:20px;color:red"></span></td></tr>
                        <tr><td></td><td colspan="2">(为了您的帐户安全，强烈建议您的密码使用字符+数字等多种不同类型的组合，并且密码长度大于5位。)</td></tr>
                        <tr><td>确定密码：</td><td><input type="password" id="qrmm"/></td><td><span  id="qrmmnotice" style="width:170px;height:20px;color:red"></span></td></tr>
                        <tr><td></td><td colspan="2">(确保密码输入正确)</td></tr>
                        <tr><td>邮箱：</td><td><input type="text" id="yx" name="yx"/></td><td><span  id="yxnotice" style="width:170px;height:20px;color:red"></span></td></tr>
                        <tr><td></td><td colspan="2">(填写邮箱后方便找回密码)</td></tr>
                        <tr><td>验证码：</td><td><input type="text" id="code" name="code"style="float:left;"/> <img id="Image1" src="<z:path/>web/public/image.jsp" style="float: left;margin-left: 20px;cursor:pointer" onmouseup="RefreshImage()" title="看不清请点击更新"/><div style="clear:both;"></div></td><td><span  id="codenotice" style="width:170px;height:20px;color: red;"></span></td></tr>
                    </table>
                    <input type="button"class="zc2" value="注册" onclick="register()"/><span id="notice" style="color:red"></span>
                </div>
            </div>
        </div>
    </body>
</html>
