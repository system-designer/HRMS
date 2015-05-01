
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">var basePath = '<z:path/>';</script>
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/icon.css">
        <script type="text/javascript" src="<z:path/>js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>  
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-uploadify-3.1/uploadify.css"/>
        <script type="text/javascript" src="<z:path/>js/jquery-uploadify-3.1/jquery.uploadify-3.1.min.js"></script>
        <script type="text/javascript" src="<z:path/>manage/xtwh/rcxxwhtpgl.js"></script>
        <style>
            *{margin:0;padding:0}
            body{margin:2px;}
        </style>
        <title>人才图片管理</title>
    </head>
    <body class="easyui-layout">

        <div region="north" title="图片选择" style="height: 120px;">
            <fieldset style="border:#D3D3D3 solid 1px;margin: 10px;height: 70px;">
                <legend style="margin:5px 25px 10px;font-weight: bold;">图片类型选择</legend>
                <table border="0">
                    <tr>
                        <td><span style="margin-left: 80px;">工作单位：</span></td>
                        <td><select id="orgz" class="easyui-combobox" editable="false" style="width: 260px" valueField="orgzc" textField="orgz" editable="false"></select></td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>
                            <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-search" onclick="reloadImg();">确定</a>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
        <div region="center" title="图片显示区" toolbar="#tools" id="imgShowDiv">
            <div id="tools" style="background-color: #EFEFEF;height:auto;border-bottom: 1px solid #cccccc;">
                <a href="javascript:;" class="easyui-linkbutton powerCtr" plain="true" iconCls="icon-ok" onclick="selectAll();">全部选中</a>
                <a href="javascript:;" class="easyui-linkbutton powerCtr" plain="true" iconCls="icon-undo" onclick="unselectAll();">取消选中</a>
                <a href="javascript:;" class="easyui-linkbutton powerCtr" plain="true" iconCls="icon-recyclebin" onclick="deleteImg();">删除图片</a>
                <a href="javascript:;" class="easyui-linkbutton" plain="true" iconCls="icon-reload" onclick="reloadImg();">重新载入</a>
                <a href="javascript:;" class="easyui-linkbutton powerCtr" plain="true" iconCls="icon-add" onclick="flashChecker();">上传图片</a>
            </div>
            <div id="imgShowDiv_workspace">

            </div>
            <!--显示大图的窗体-->
            <div id="xmtpgl_lagger_dialog" class="easyui-dialog" closed="true" modal="true" style="width:600px;height:500px;" align="center" title="查看大图">
                <img id="xmtpgl_lagger_img" style="width:550px;height:450px;"/>
            </div>
            <!--上传文件的表单-->
            <div id="uploadDialog" class="easyui-dialog" closed="true" modal="true" style="width:600px;height:400px;" title="文件上传">
                <div style="margin-top: 15px;"><input type="file" name="uploadify" id="uploadify" /></div>
                <div style="color: green;padding: 5px;">已选择文件:</div>
                <div id="fileQueue" style="width: 555px; height:230px;border-top: 1px solid green;overflow: auto;"></div>
                <div id="uploadMess" style="height: 25px;margin-left: 10px;margin-bottom: 10px;"></div>
                &nbsp;&nbsp;<a href="javascript:;" onclick="uploadFile();" class="easyui-linkbutton" iconCls="icon-save">上传文件</a>
                &nbsp;&nbsp;<a href="javascript:;" onclick="removeFile();" class="easyui-linkbutton" iconCls="icon-cancel">清空队列</a>
            </div>
        </div>
    </body>
</html>
