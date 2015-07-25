<%-- 
    Document   : hdtpgl
    Created on : 2013-3-10, 9:11:09
    Author     : evance
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<z:path/>jquery-easyui-1.2.5/themes/default/easyui.css"/>
        <link rel="stylesheet" type="text/css" href="<z:path/>jquery-easyui-1.2.5/themes/icon.css"/>
        <link href="<z:path/>manage/uploadtool/uploadify.css" rel="stylesheet" type="text/css" /> 
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="<z:path/>manage/uploadtool/jquery.uploadify-3.1.js"></script> 
        <script type="text/javascript" src="<z:path/>manage/sthd/tpgl.js"></script>
        <script type="text/javascript">
            var path= '<z:path/>';
            var basePath = path;
            var param1='${param.param1}';
            var param2='${param.param2}';//定义全局的查询条件和查询内容
            var mark='${param.mark}';//此页面同时为ckeditor插入图片页面使用
        </script>
        <title>项目图片管理</title>
    </head>
    <body class="easyui-layout">
        <div region="west" style="width:230px;" title="图片文件夹">
            <ul class="easyui-tree" id="folderTree" onclick="getFolder()">
                <li>
                    <span checked="true">image</span>
                </li>
            </ul>
        </div>
        <div region="center" title="显示区" id="imgShowDiv" toolbar="#tools">
            <div id="tools" style="background-color: #EFEFEF;height:auto;border-bottom: 1px solid #cccccc;">
                <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-ok" onclick="selectALL()">全部选中</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="unselectALL()">取消全选</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-remove" onclick="deleteImg()">删除文件</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-reload" onclick="getFolder()">重新载入</a>
                <span style="color:red">温馨提示：上传图片宽度请不要超过<font color="green">1024px</font>，否则本系统将会自动按比例缩小。<font color="blue">上传文件时请在对应的文件夹上单击右键。</font></span>
            </div>
            <div id="imgShowDiv_workspace">
            </div>
        </div>
        <!--右边显示区图像右键菜单-->
        <div id="imgMenu" class="easyui-menu" style="width: 120px;" >
            <div onclick="showMaxImg()" iconCls="icon-search">
                查看大图
            </div>
        </div>
        <!--左边树菜单上节点的右键菜单-->
        <div id="folderMenu" class="easyui-menu" style="width:120px;">
            <div onclick="showForm()" iconCls="icon-add">上传文件</div>
            <div onclick="showNewFolderForm()" iconCls="icon-folder">新建文件夹</div>
        </div>
        <!--上传文件的表单-->
        <div id="uploadDialog" class="easyui-dialog" closed="true" modal="true" style="width:600px;height:400px;" title="文件上传">
            <div><input type="file" name="uploadify" id="uploadify" />&nbsp;&nbsp;<a href="javascript:void(0);" onclick="uploadFile()" class="easyui-linkbutton" iconCls="icon-save">上传</a>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="removeFile()" class="easyui-linkbutton" iconCls="icon-cancel">清空队列</a>&nbsp;&nbsp;<span id="uploadMess"></span></div>
            <div style="color: green;padding: 5px;">已选择文件:</div>
            <div id="fileQueue" style="width: 555px; height:260px;border-top:  1px solid green;overflow: auto;"></div>
        </div>
        <!--创建文件夹的窗体-->
        <div id="newFolderDialog"  class="easyui-dialog" closed="true" modal="true" style="width:300px;height:150px;" title="新建文件夹">
            <form id="newFolderDialog_form" method="post">
                <div style="color:red">温馨提示:文件夹名称中请不要包含"#"、"%"非法字符!</div>
                <div align="center" style="line-height:50px;">文件夹名称<input type="text" name="newFolder" size="15" class="easyui-validatebox" id="newFolder" required="true" validType="notEmpty"/></div>
                <div align="center"><a href="javascript:void(0);" onclick="saveNewFolderForm()" class="easyui-linkbutton" iconCls="icon-save">保存</a></div>
            </form>
        </div>
        <!--显示大图的窗体-->
        <div id="xmtpgl_lagger_dialog" class="easyui-dialog" closed="true" modal="true" style="width:600px;height:500px;" align="center" title="查看大图">
            <img id="xmtpgl_lagger_img" style="width:550px;height:450px;"/>
        </div>
        <div id="waitDIV" class="easyui-dialog"  closed="true" modal="true" style="width:200px;height:50px;" closable="false" align="center" title=" ">
            &nbsp;正在上传，请稍候...<img src="<z:path/>manage/sthd/loading.gif" border="0"/>
        </div>
    </body>
</html>
