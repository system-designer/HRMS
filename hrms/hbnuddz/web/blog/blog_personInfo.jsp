<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : hdjj
    Created on : 2013-1-21, 20:47:38
    Author     : ASUS
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="/jstl_extend.tld" prefix="extend" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="<z:path/>web/image/favicon.ico" type="image/x-icon" />
        <link rel="Shortcut Icon" type="image/x-icon" href="<z:path/>web/image/favicon.ico" />
        <link rel="stylesheet" type="text/css" href="<z:path/>jquery-easyui-1.2.5/themes/default/easyui.css"/>
        <link rel="stylesheet" type="text/css"href="<z:path/>web/css/blog_index.css"/>
        <link rel="stylesheet" type="text/css"href="<z:path/>web/css/HeadBott.css"/>
        <link rel="stylesheet" type="text/css"href="<z:path/>web/css/blog/blog_personInfo.css"/>
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="<z:path/>web/blog/blog_personInfo.js"></script>
        <title>个人资料</title>
        <script type="text/javascript">
            $(function(){
                //为页面左边的导航栏绑定事件
                $(".blog_personinfo_ul1 li").each(function(){      
                    $(this).click(function(){
                        var i=$(".blog_personinfo_ul1 li").index(this);
                        $(".blog_personinfo_ul1 li").eq(i).css({"background":"white","border-top":"1px solid #cccccc","border-bottom":"1px solid #cccccc"}) 
                        $(".blog_personinfo_ul1 li").eq(i).siblings().css({"background":" #e8f0f7","border-top":"none","border-bottom":"1px dashed #cccccc"}) 
                        $(".blog_personinfo_right").eq(i).css("display","block");
                        $(".blog_personinfo_right").eq(i).siblings(".blog_personinfo_right").css("display","none");
                    })
                })
                $("#csrq").datebox('setValue','${host.csrq}');
            })
        </script>
    </head>
    <body>
        <%@include  file="../blog/blog_header.jsp" %> 
        <div class="blog_personinfo_main">
            <div class="blog_personinfo_left" >
                <span class="blog_personinfo_span1">设置</span>
                <ul class="blog_personinfo_ul1">
                    <li style="background: white;border:1px solid #cccccc;border-right: none;border-left: none;">个人资料</li>
                    <li>修改头像</li>
                    <li>密码安全</li>
                </ul>
            </div>
            <div class="blog_personinfo_right" >
                <form method="post" action="<z:path/>BlogYhbServlet.jsp?mode=UPDATE">
                    <input type="hidden" name="id" value="${host.id}"/>
                    <div class="blog_personinfo_right_top"><span class="blog_personinfo_right_span1">个人资料</span></div>
                    <table class="blog_personinfo_right_table">
                        <tr><td class="blog_personinfo_right_table_td1">UID</td><td class="blog_personinfo_right_table_td2">${host.id}</td><td class="blog_personinfo_right_table_td3"></td></tr>
                        <tr><td class="blog_personinfo_right_table_td1">用户名</td><td class="blog_personinfo_right_table_td2"> <input name="yhm" type="text" style="width:300px;height:25px;" value="${host.yhm}"/></td></tr>
                        <tr><td>真实姓名</td><td><input type="text" style="width:300px;height:25px;" name="zsxm" value="${host.zsxm}"/></td></tr>
                        <tr><td>性别</td><td><select name="xb" class="blog_personinfo_right_table_select" value="${host.xb}">
                                <option value="密" <c:if test="${host.xb=='密'}">selected="true"</c:if>>保密</option>
                                <option value="男" <c:if test="${host.xb=='男'}">selected="true"</c:if>>男</option>
                                <option value="女" <c:if test="${host.xb=='女'}">selected="true"</c:if>>女</option>
                            </select>
                        </td></tr>
                        <!--<tr><td>生日</td><td><select class="blog_personinfo_right_table_select"><option>年</option><option>男</option><option>女</option></select>  <select class="blog_personinfo_right_table_select"><option>月</option><option>男</option><option>女</option></select>    <select class="blog_personinfo_right_table_select"><option>日</option><option>男</option><option>女</option></select></td></tr>-->
                        <tr><td>生日</td><td><select id="csrq" name="csrq" class="easyui-datebox"></select></td></tr>
                        <tr><td>个性签名</td><td><textarea name="gxqm" style="width:300px;height:85px;resize:none;">${host.gxqm}</textarea></td></tr>
                        <tr><td><input type="submit" class="blog_personinfo_right_table_span" value="保存"/></td></tr>
                    </table>
                </form>
            </div>
            <div class="blog_personinfo_right" style="display:none;">
                <div class="blog_personinfo_right_top"><span class="blog_personinfo_right_span1">修改头像</span></div>
                <div class="blog_personinfo_right_center">
                    <span style="font-size:16px;padding-left: 10px;font-weight: bold;height:30px;line-height: 30px;">当前我的头像</span><br/>
                    <span class="blog_personinfo_right_center_span1"> 如果您还没有设置自己的头像，系统会显示为默认头像，您需要自己上传一张新照片来作为自己的个人头像</span> <br/>
                    <img src="<z:path/>userfiles${host.yhtx}" style="width:200px;height:200px;margin-left: 10px;margin-top: 10px;" /><br/><br/>
                    <span style="font-size:16px;padding-left: 10px;font-weight: bold;height:30px;line-height: 30px;">设置我的新头像</span><br/>
                    <span class="blog_personinfo_right_center_span1"> 请选择一个新照片进行上传编辑。</span> <br/>
                    <span class="blog_personinfo_right_center_span1">头像保存后，您可能需要刷新一下本页面(按F5键)，才能查看最新的头像效果 </span> <br/>
                    <div style="padding-left:  10px;margin-top: 10px;">
                        <script type="text/javascript">
                            document.write(AC_FL_RunContent('width','450','height','253','scale','exactfit','src','http://www.dandang.org/uc_server/images/camera.swf?inajax=1&appid=1&input=b5bbwvPM%2BQZPrBaLct03edF96V3ZS4sD6Td3X%2F8H3ed4h8Qa7hFtAQBI8nbo3g0EfsR%2FmpqUDRajaUb1e%2BQydzsJ9ELYySK%2FOSI1GBPq7n5xJ2IGh6FcoG6bQJ8&agent=dbbaf6ebb0240fdf90c82a951e227dd1&ucapi=www.dandang.org%2Fuc_server&avatartype=virtual&uploadSize=2048','id','mycamera','name','mycamera','quality','high','bgcolor','#ffffff','menu','false','swLiveConnect','true','allowScriptAccess','always'));
                        </script>
                    </div>
                </div>
            </div>
            <div class="blog_personinfo_right" style="display:none;" >
                <div class="blog_personinfo_right_top"><span class="blog_personinfo_right_span1">密码安全</span></div>
                <table class="blog_personinfo_right_table1" >
                    <tr><td class="blog_personinfo_right_table_td1">旧密码</td><td class="blog_personinfo_right_table_td2"><input type="text" style="width:300px;height:25px;"/></td></tr>
                    <tr><td>新密码</td><td><input type="text" style="width:300px;height:25px;"/></td></tr>
                    <tr><td></td><td><span style="color:#cccccc;font-size:13px"> 如不需要更改密码，此处请留空 </span></td></tr>
                    <tr><td>确认新密码</td><td><input type="text" style="width:300px;height:25px;"/></td></tr>
                    <tr ><td ></td><td ><span style="color:#cccccc;font-size:13px"> 如不需要更改密码，此处请留空 </span></td></tr>
                    <tr><td>验证码</td><td><input type="text" style="width:80px;height:25px;"/>&nbsp;&nbsp;<a href="#">换一张</a></td></tr>
                    <tr><td>  </td><td><span style="color:#cccccc;font-size:13px">输入下图中的字符</span><br/><img id="Image1" src="<z:path/>web/public/image.jsp" style="cursor:pointer;width:140px;height:50px;" onmouseup="RefreshImage()" title="看不清请点击更新"/><br/></td></tr>
                    <tr><td></td><td><span class="blog_personinfo_right_table_span">保存</span></td></tr>
                </table>
            </div>
            <div style="clear:both;"></div>
        </div>
        <%@include  file="../public/bott.jsp" %> 
    </body>
</html>
