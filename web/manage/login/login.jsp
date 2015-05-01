<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="z" uri="http://opdps.hbnu.edu.cn/jplus" %>
<%-- 
    Document   : newjsp
    Created on : 2013-11-18, 23:58:47
    Author     : PBH
--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/default/easyui.css"/>
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/icon.css"/>
        <script type="text/javascript" src="<z:path/>js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="<z:path/>manage/login/login.js"></script>
        <script type="text/javascript">
            var path = '<z:path/>';
        </script>
        <style>
            *{
                padding: 0px;margin: 0px;
            }
            body{
                background: url('<z:path/>manage/img/HRMS_LOGIN_1.jpg');
            }
            ul{padding-left: 470px;
               padding-top: 300px;
               font-size:18px;
               list-style: none;
               font-family: "华文楷书";
            }
            ul li{
                height:35px;
            }
        </style>
        <title>黄石市人才信息管理系统</title>
    </head>
    <body>
        <div  style="width:1002px;height:650px;margin: 0 auto ;border-radius:10px;margin-top: 10px;background: url('<z:path/>manage/img/login1.jpg');">
            <ul>
                <li style="height:40px;"><input id="adm" type="text" style="width: 150px;height:20px;border:1px solid #cccccc;" maxlength="11" name="adm"/></li>
                <li><input id="pass"  type="password" style="width: 150px;height:20px;border:1px solid #cccccc;" name="pass" /></li>
                <li><input id="code"  type="text" style="width: 65px;height:20px;border:1px solid #cccccc;"  name="code"  />&nbsp<img id="code_image" src="<z:path/>manage/login/image.jsp" style="cursor:pointer;position:absolute;height:23px;width:80px;" onmouseup="refreshImage()" title="看不清请点击更新"/></li>
                <li style="margin-top: 10px;height:20px;">  <span id="notice" style="width:130px;height:20px;color:red;"></span></li>
                <li style="height:30px;">    <span onclick="submitForm();" style="margin-left: 100px;cursor:pointer;padding-left:7px;padding-right:7px;padding-bottom: 3px;padding-top: 3px; border-radius:5px;background: #CCC; ">登录</span>  </li>
            </ul> 
        </div>
    </body>
</html>
