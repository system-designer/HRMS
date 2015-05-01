<%-- 
    Document   : gwxq
    Created on : 2013-12-3, 16:09:19
    Author     : T420
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="z" uri="http://opdps.hbnu.edu.cn/jplus" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/icon.css">
        <script type="text/javascript" src="<z:path/>js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>  
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="<z:path/>manage/js/util.js"></script>
        <script type="text/javascript" src="<z:path/>gwxq/gwxq.js"></script>
        <script type="text/javascript">
            var path='<z:path/>';
            var urlPattern="gwxq/GwxqAction.jsp?mode=BAIDUINPUT";
        </script>
        <script type="text/javascript">
            var path='<z:path/>';
            //下面是添加百度搜索效果需要的全局变量
            var input_id="gzdw";//输入框id
            var submit_id="submit";//提交按钮的id
            var urlPattern="manage/tsrcxx/cyrc/CyrccxAction.jsp?mode=BAIDUINPUT";//提交路径
        </script>
        <style>
            *{
                margin: 0px;
                padding: 0px;
            }
        </style>
        <title>需求岗位</title>
    </head>
    <body>
        <%--需求岗位列表--%>
        <div id="dwmc" toolbar="#dwmc_gridTools"></div>
        <div id="dwmc_gridTools" style="padding:5px;height:auto">
            <div style="margin-bottom:5px" >
                <div style="float:left; margin-top:5px"><a href="javascript:void(0);" class="easyui-linkbutton" onClick="loadGwtj()" iconCls="icon-add" plain="true" style="float:left;">添加</a></div>
                <div style="float:left; margin-top:5px"><a href="javascript:void(0);" class="easyui-linkbutton" onClick="editGw()" iconCls="icon-edit" plain="true" style="float:left;">修改</a></div>
                <div style="float:left; margin-top:5px"><a href="javascript:void(0);" class="easyui-linkbutton" onClick="deleteRy()" iconCls="icon-remove" plain="true" style="float:left;">删除</a></div>
                <div style="clear: both"></div>
            </div>
        </div> 
        <%--需求岗位添加--%>
        <div id="gwtj" toolbar="#gwtj_gridTools" ></div>
        <div id="gwtj_gridTools" style="padding:5px;height:auto" >
            <div>
                <div class="easyui-dialog"   style="width: 700px ;Margin-top: 0px" title="需求岗位添加" id="add_dialog" closed="true" modal="false">
                    <form method="post" id="form_add">
                        <div>
                            <div style="margin-top:10px; margin-left: 10px">
                                单位名称:<input style="width:200px;" readonly="true" id="dwid"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                
                                发布时间:<input type="text" name="fbsj" id="fbsj" value=""style="width: 200px" >
                            </div> 
                            <div style="margin-top:10px;margin-left: 10px">
                                需求岗位:<input type="text" name="xqgw" id="xqgw" value=""style="width: 200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                专&nbsp;&nbsp;&nbsp;&nbsp;业:<input type="text" name="zy" id="zy" value=""style="width: 200px">
                            </div>
                            <div style="margin-top:10px;margin-left: 10px">
                                学&nbsp;&nbsp;&nbsp;&nbsp;历:<input  id="xlt" class="easyui-combobox" editable="false" width="200px" name="xlbm"/>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                学&nbsp;&nbsp;&nbsp;&nbsp;位 :<input  id="xwt" class="easyui-combobox" editable="false" width="200px" name="xwbm"/>
                                &nbsp;&nbsp;
                            </div>
                            <div style="margin-top:10px;margin-left: 10px">
                                引进方式:<input type="text" id="yjfs" name="yjfs"style="width: 200px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                人&nbsp;&nbsp;&nbsp;&nbsp;数;<input type="text" id="rs" name="rs"style="width: 200px">
                            </div>
                            <div style="margin-top:10px;margin-left: 10px">
                                岗位要求:<input type="text" id="gwyq" name="gwyq"style="width: 600px">
                            </div>
                            <div style="margin-top:10px;margin-left: 10px">
                                待&nbsp;&nbsp;&nbsp;&nbsp;遇:<input type="text" id="dy" name="dy"style="width: 600px ">
                            </div>
                            <div style="padding-left: 500px; margin-top:5px;">
                                <a href="javascript:" class="easyui-linkbutton"onclick="submitForma()" iconCls="icon-add" id="add" style="cursor:pointer;">添加</a><td/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%--修改dialog--%>
    <div>
        <div class="easyui-dialog"   style="width: 700px;Margin-top: 0px" id="edit_dialog"title="需求岗位修改" closed="true" modal="false">
            <form method="post" id="form_edit">
                <div>
                    <input type="hidden" id="xqgwid_hidden" value="" name="xqgwid-hidden"/>
                    <input type="hidden" id="dwid_hidden" value="" name="dwid_hidden"/>
                    <div style="margin-top:10px;margin-left: 10px">
                        用人单位<input name="yrdwc" readonly="true" id="yrdwa" style="width: 280px"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp&nbsp;&nbsp&nbsp;&nbsp&nbsp;&nbsp&nbsp;&nbsp&nbsp;&nbsp
                        发布时间<input type="text" name="fbsj" id="fbsja" value=""style="width: 100px" >
                    </div>
                    <div style="margin-top:10px;margin-left: 10px">
                        需求岗位<input type="text" name="xqgw" id="xqgwa" value=""style="width: 340px">&nbsp;&nbsp;
                        专&nbsp;&nbsp;&nbsp;&nbsp;业<input type="text" id="zya" name="zy" id="zya" style="width: 200px">
                    </div> 
                    <div style="margin-top:10px;margin-left: 10px">
                        学&nbsp;&nbsp;&nbsp;历&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input  id="xl" class="easyui-combobox" editable="false" width="40px" name="xl"/>
                        &nbsp;&nbsp;学&nbsp;&nbsp;位<input  id="xw" class="easyui-combobox" editable="false" width="40px" name="xw"/>
                        &nbsp;&nbsp;人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数<input type="text" id="rsa" name="rs"style="width: 50px">
                    </div>
                    <div style="margin-top:10px;margin-left: 10px">
                        引进方式<input type="text" id="yjfsa" name="yjfs" style="width: 340px">
                    </div>
                    <div style="margin-top:10px;margin-left: 10px">
                        岗位要求<input type="text"name="gwyq" id="gwyqa" style="width: 600px">
                    </div>
                    <div style="margin-top:10px;margin-left: 10px">
                        待&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;遇<input type="text" id="dya" name="dy"style="width: 600px ">
                    </div>
                    <div style="padding-left: 500px; margin-top:5px;">
                        <a href="javascript:" onClick="submitForme()" class="easyui-linkbutton" iconCls="icon-edit" id="edit" style="cursor:pointer;">修改</a></td>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
