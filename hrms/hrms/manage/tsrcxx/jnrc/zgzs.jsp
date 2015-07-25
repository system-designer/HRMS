<%-- 
    Document   : zgzs
    Created on : 2013-11-11, 9:54:37
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/icon.css">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/mycss/i-style.css">
        <script type="text/javascript" src="<z:path/>js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>  
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript">
            var path_zgzs="<z:path/>manage/tsrcxx/jnrc/ZgzsAction.jsp";
            var path_ryjl="<z:path/>manage/tsrcxx/jnry/RyjlAction.jsp";
            var ryid=${param.ryid};//获取当前技能人才的id
        </script>
        <script type="text/javascript" src="<z:path/>manage/tsrcxx/jnrc/zgzs.js"></script>
        <title>资格证书</title>
        <style type="text/css">
            *{
                font-size: 14px;
            }
            .headMsg{
                border-left:0px;
                border-top:0px;
                border-right:0px;
                border-bottom:1px;
                background-color: white;
                font-weight: bold;
            }
            .center{
                position:relative;
                left: 20px;

            }
            .m10{
                margin: 10px;
            }
            .dwmc{
                width: 200px;
            }
            .xm{
                width: 60px;
            }
        </style>
    </head>
    <body>
        <!--资格证书-->
        <div>
            <div class="center">
                <table width="400">
                    <tr>
                        <td width="100">单位名称：</td><td width="300"><input class="headMsg" disabled="disabled" id="dwmc" style="width:300px;"/></td>
                    </tr>
                    <tr>
                        <td>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td><td><input class="headMsg" disabled="disabled" id="xm"/></td>
                    <tr>
                    </tr>
                    <td>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</td><td><input class="headMsg" disabled="disabled" id="xb"/></td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td colspan="2">省份证(护照)号码：</td>
                        <td colspan="2"><input class="headMsg" disabled="disabled" id="zjhm"></td>
                    </tr>
                </table>
            </div>
        </div>
        <br/>
        <br/>
        <div style="position: relative;left: 250px">
            <span style="font-size: 20px;font-weight: 800">高技能人才信息</span>
        </div>
        <div id="table_zgzs" toolbar="#toolbutton"></div>
        <div id="toolbutton">
            <a class="easyui-linkbutton" href="javascript:;" onclick="initAddZgzs()" plain="true" iconCls="icon-add">添加资格证书</a>
        </div>
        <div class="easyui-dialog" id="add_dialogZgzs" closed="true"><!--添加资格证书-->
            <div style="margin:10px;">
                <table>
                    <tr>
                        <td>单位名称：</td><td><input class="headMsg" disabled="disabled" name="dwmc" type="text" style="width: 200px"  /></td>
                        <td>姓名：</td><td><input class="headMsg" disabled="disabled" name="xm" type="text" style="width: 60px"/></td>
                        <td>性别：</td><td><input class="headMsg" disabled="disabled" name="xb" type="text"/></td>
                    </tr>
                </table>
            </div>
            <div style="margin:10px;">
                <form id="add_formZgzs" method="post">
                    <table border="0">
                        <tr>
                            <td>工种职业：</td><td><input name="gz" type="text"/></td>
                            <td>等&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</td><td><input name="dj" type="text"/></td>
                        </tr>
                        <tr>
                            <td>证&nbsp;书&nbsp;号：</td><td><input name="zsh" type="text"/></td>
                            <td>发证时间：</td><td><input name="fzsj" type="text"/></td>
                        </tr>
                        <tr>
                            <td>发证机关：</td><td><input name="fzjg" type="text"/></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="easyui-dialog" id="updateZgzs" closed="true"><!--修改资格证书-->
            <div class="m10">
                <table>
                    <tr>
                        <td>单位名称：</td><td><input class="dwmc headMsg" disabled="disabled" name="dwmc" type="text"/></td>
                        <td>姓名：</td><td><input class="xm headMsg" disabled="disabled" name="xm" type="text"/></td>
                        <td>性别：</td><td><input class="headMsg" disabled="disabled" name="xb" type="text"/></td>
                    </tr>
                </table>
            </div>
            <div class="m10">
                <form id="update_formZgzs" method="post">
                    <table border="0">
                        <tr>
                            <td>工种职业：</td><td><input name="gz" id="updategz" type="text"/></td>
                            <td>等&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</td><td><input name="dj" id="updatedj" type="text"/></td>
                        </tr>
                        <tr>
                            <td>证书号：</td><td><input name="zsh" id="updatezsh" type="text"/></td>
                            <td>发证时间：</td><td><input name="fzsj" id="updatefzsj" type="text"/></td>
                        </tr>
                        <tr>
                            <td>发证机关</td><td><input name="fzjg" id="updatefzjg" type="text"/></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="easyui-dialog" id="deleteZgzs" closed="true"><!--删除资格证书-->
            <div class="m10">
                <table border="0">
                    <tr>
                        <td>单位名称：</td><td><input class="headMsg dwmc"  disabled="disabled" name="dwmc" type="text"/></td>
                        <td>姓名：</td><td><input class="headMsg xm" disabled="disabled" name="xm" type="text"/></td>
                        <td>性别：</td><td><input class="headMsg" disabled="disabled" name="xb" type="text"/></td>
                    </tr>
                </table>
            </div>
            <div class="m10">
                <table>
                    <tr>
                        <td>工种职业：</td><td><input class="headMsg" id="delgz" type="text"/></td>
                        <td>等级：</td><td><input class="headMsg" id="deldj" type="text"/></td>
                    </tr>
                    <tr>
                        <td>证书号：</td><td><input class="headMsg" id="delzsh" type="text"/></td>
                        <td>发证时间：</td><td><input class="headMsg" id="delfzsj" type="text"/></td>
                    </tr>
                    <tr>
                        <td>发证机关</td><td><input class="headMsg" id="delfzjg" type="text"/></td>
                    </tr>
                </table>
            </div>
        </div>
        <!--荣誉奖励-->
        <br/>
        <br/>
        <div id="ryjl_List" toolbar="#toolbar"></div>
        <div id="toolbar">
            <a class="easyui-linkbutton" href="javascript:;" onclick="init_AddRyjl()" plain="true" iconCls="icon-add">添加荣誉奖励</a>
        </div>
        <div class="easyui-dialog" id="add_dialogRyjl" closed="true"><!--添加荣誉奖励对话框-->
            <div class="m10">
                <table>
                    <tr>
                        <td>单位名称：</td><td><input class="dwmc headMsg" disabled="disabled" name="dwmc" type="text"/></td>
                        <td>姓名：</td><td><input class="xm headMsg" disabled="disabled" name="xm" type="text"/></td>
                        <td>性别：</td><td><input class="headMsg" disabled="disabled" name="xb" type="text"/></td>
                    </tr>
                </table>
            </div>
            <div class="m10">
                <form id="update_ryjlform" method="post">
                    <table>
                        <tr>
                            <td>荣誉奖励名称：</td><td><input name="ryjlmc" type="text"/></td>
                        </tr>
                        <tr>
                            <td>授&nbsp;&nbsp;予&nbsp;&nbsp;机&nbsp;&nbsp;关：</td><td><input name="syjg" type="text"/></td>
                        </tr>
                        <tr>
                            <td>授&nbsp;&nbsp;予&nbsp;&nbsp;时&nbsp;&nbsp;间：</td><td><input name="sj" type="text"/></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="easyui-dialog" id="updateRyjl" closed="true"><!--修改荣誉奖励对话框-->
            <div class="m10">
                <table>
                    <tr>
                        <td>单位名称：</td><td><input class="dwmc headMsg" disabled="disabled" name="dwmc" type="text"/></td>
                        <td>姓名：</td><td><input class="xm headMsg" disabled="disabled" name="xm" type="text"/></td>
                        <td>性别：</td><td><input class="headMsg" disabled="disabled" name="xb" type="text"/></td>
                    </tr>
                </table>
            </div>
            <div class="m10">
                <form method="post" id="updateformRyjl">
                    <table>
                        <tr>
                            <td>荣誉奖励名称：</td><td><input name="ryjlmc" id="ry_ryjlmc" type="text"/></td>
                        </tr>
                        <tr>
                            <td>授&nbsp;&nbsp;予&nbsp;&nbsp;机&nbsp;&nbsp;关：</td><td><input name="syjg" id="ry_syjg" type="text"/></td>
                        </tr>
                        <tr>
                            <td>授&nbsp;&nbsp;予&nbsp;&nbsp;时&nbsp;&nbsp;间：</td><td><input name="sj" id="ry_sj" type="text"/></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="easyui-dialog" id="delRyjl" closed="true"><!--删除荣誉奖励对话框-->
            <div class="m10">
                <table>
                    <tr>
                        <td>单位名称：</td><td><input class="dwmc headMsg" disabled="disabled" name="dwmc" type="text"/></td>
                        <td>姓名：</td><td><input class="xm headMsg" disabled="disabled" name="xm" type="text"/></td>
                        <td>性别：</td><td><input class="headMsg" disabled="disabled" name="xb" type="text"/></td>
                    </tr>
                </table>
            </div>
            <div class="m10">
                <table>
                    <tr>
                        <td>荣誉奖励名称：</td><td><input class="headMsg" id="delryjlmc" type="text"/></td>
                    </tr>
                    <tr>
                        <td>授&nbsp;&nbsp;予&nbsp;&nbsp;机&nbsp;&nbsp;关：</td><td><input class="headMsg" id="delsyjg" type="text"/></td>
                    </tr>
                    <tr>
                        <td>授&nbsp;&nbsp;予&nbsp;&nbsp;时&nbsp;&nbsp;间：</td><td><input class="headMsg" id="delsj" type="text"/></td>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</html>
