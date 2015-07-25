<%-- 
    Document   : login
    Created on : 2013-3-16, 16:21:05
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<!DOCTYPE html >
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="keywords" content="环保，绿源环保协会，湖北师范学院，Jplus实验室">
        <meta name="Description" content="登录以用户身份进入绿源环保协会网站">
        <meta name="author" content="刘雷，裴秀">
        <link rel="icon" href="<z:path/>web/image/favicon.ico" type="image/x-icon" />
        <link rel="Shortcut Icon" type="image/x-icon" href="<z:path/>web/image/favicon.ico" />
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="<z:path/>web/login/login.js"></script>
        <link rel="stylesheet" type="text/css"href="<z:path/>web/css/login.css"/>
        <title>登录</title>
        <script type="text/javascript">
            var path='<z:path/>';
        </script>
    </head>
    <body>
        <div class="all">
            <div class="denglu">
                <span style="font-size:22px;margin-right: 5px;">绿源环保协会</span>
                <span style="font-size:20px; color: #707070">会员</span>
                <span style="font-size:18px;color: #707070">-登录</span> <a href="<z:path/>IndexAction.jsp?mode=SHOWLIST" style="text-decoration: none;float:right;font-size:18px;color:red;">返回首页</a></div>
            <div class="main" >
                <div class="left">
                    <img src="<z:path/>web/image/hh.jpeg" style="padding-left: 20px;"/>
                </div>
                <div class="right">
                    <form id="loginForm" method="post" action="<z:path/>IndexAction.jsp?mode=LOGIN">
                        <div class="hy">
                            绿源环保协会欢迎您
                        </div>
                        <ul>
                            <li><span>用户名:</span>
                                <input type="text" class="yhm" id="yhm" name="yhm" value="${yhm}"/></li> 
                            <li>
                                <span>密&nbsp;&nbsp;&nbsp;&nbsp;码:</span>
                                <input type="password" class="mm" id="mm" name="mm"/>
                            </li> 
                            <li><span>验证码:</span>
                                <input type="text" size="12" class="yzm" name="code" id="code"/>&nbsp;&nbsp;
                                <img id="Image1" src="<z:path/>web/public/image.jsp" style="cursor:pointer" onmouseup="RefreshImage()" title="看不清请点击更新"/></li>                      
                        </ul>
                        <input type="hidden" id="forward" name="forward" value="${param.forward}"/>
                        <input id="autologin" type="checkbox" name="autologin" value="ok"/><label for="autologin">记住我一个月</label></span><input type="submit" value="提交" class="tj"/> 
                        <span id="notice" style="margin-left: 20px;width:130px;height:20px;color:red">${notice}</span>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
