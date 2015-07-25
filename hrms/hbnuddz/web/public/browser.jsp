<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : brower
    Created on : 2013-3-13, 20:29:00
    Author     : Administrator
--%>
<!--ckeditor的浏览服务器页面-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/jquery-1.7.1.min.js" ></script>
        <meta name="keywords" content="环保，论坛，校园，Jplus实验室">
        <meta name="Description" content="湖北师范学院绿源环保协会">
        <meta name="author" content="刘雷，裴秀">
        <link rel="icon" href="<z:path/>web/image/favicon.ico" type="image/x-icon" />
        <link rel="Shortcut Icon" type="image/x-icon" href="<z:path/>web/image/favicon.ico" />
        <title>请选择图片</title>
        <script type="text/javascript">
            var mark='${mark}';
            var path='<z:path/>';
            $(function(){
                if(mark=='upload'){
                    var imgurl=path+"userfiles"+'${imgurl}';
                    var funcNum = getUrlParam( 'CKEditorFuncNum' );
                    window.parent.CKEDITOR.tools.callFunction( funcNum, imgurl );
                    window.close();
                }
            })
            // Helper function to get parameters from the query string.
            //回调函数
            function getUrlParam( paramName ) {
                var reParam = new RegExp( '(?:[\?&]|&)' + paramName + '=([^&]+)', 'i' ) ;
                var match = window.location.search.match(reParam) ;
                return ( match && match.length > 1 ) ? match[ 1 ] : null ;
            }
            //得到选中的图片的路径
            function getFileUrl(obj){
                var src=obj.src;
                var funcNum = getUrlParam( 'CKEditorFuncNum' );
                window.opener.CKEDITOR.tools.callFunction( funcNum, src );
                window.close();
            }
        </script>
    </head>
    <body>
        <c:forEach var="serverimg" items="${imglist}">
            <img style="width:100px;height:100px;cursor:pointer" src="<z:path/>userfiles${serverimg}" onclick="getFileUrl(this)"/>
        </c:forEach>
    </body>
</html>
