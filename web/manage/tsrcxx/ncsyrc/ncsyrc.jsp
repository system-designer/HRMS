<%-- 
    Document   : ncsyrc
    Created on : 2013-11-13, 19:20:43
    Author     : li_qi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="<z:path/>js/jquery-easyui-1.3.2/themes/default/easyui.css"/>
        <link type="text/css" rel="stylesheet" href="<z:path/>js/jquery-easyui-1.3.2/themes/icon.css"/>
        <script type="text/javascript" src="<z:path/>js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="<z:path/>manage/js/util.js"></script>
        <script type="text/javascript" src="<z:path/>manage/tsrcxx/ncsyrc/ncsyrc.js"></script>
        <script>
            var val = 1;
            var ryid = '${param.ryid}';
            var sfncsyrc = '${param.sfncsyrc}';
            var path = '<z:path/>'
        </script>
        <title>农村实用人才</title>
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

        </style>
    </head>
    <body>        
        <div id="xx" title="农村实用人才"  style="height:400px;width: 800px;font-size: 14px;padding:10px">
            <table>
                <thead>
                    <tr>
                        <th width="20%"></th><th width="30%"></th><th width="20%"></th><th width="30%"></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>单位名称：</td>
                        <td><input type="text" name="dwmc" style="width: 220px;height:20px" id="dwmc" class="headMsg" disabled="true" readonly="true"/></td>&nbsp;
                        <td>姓&nbsp;&nbsp;名：</td>
                        <td><input type="text" name="xm" style="width: auto;height:20px" id="xm" class="headMsg" disabled="true" readonly="true"/></td>&nbsp;
                    </tr>
                    <tr>
                        <td>身份证(护照)：</td>
                        <td><input type="text" name="zjhm" style="width: 230px;height:20px" id="zjhm" class="headMsg" disabled="true" readonly="true"/><br/></td>
                        <td>性&nbsp;&nbsp;别：</td>
                        <td><input type="text" name="xb" style="width: auto;height:20px" id="xb" class="headMsg" disabled="true" readonly="true"/></br></td>
                    </tr>
                </tbody>
            </table>

            <form id="ncsyrc" method="post">
                <fieldset style="border:#D3D3D3 solid 1px;margin: 10px;height: 100px;">
                    <legend style="margin:5px 25px 10px;font-weight: bold;">农村实用人才信息</legend>
                    <div>
                        <span>是否外出打工半年：</span>
                        <input type="radio" name="sfwcdgbn" value="true" checked="checked" />是   
                        <input type="radio" name="sfwcdgbn" value="false" />否
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span>奖励等级&nbsp;：</span>
                        <input id="jldj" class="easyui-combobox" name="jldj" style="width:100px;height:20px;"/>
                    </div><br/>               
                    <div>
                        <span>农村实用人才类别：</span>
                        <input id="ncrclb" class="easyui-combobox" name="ncrclb" style="width:150px;height:20px;"/>
                        <span>上年纯收入：</span>
                        <input id="csr" class="easyui-combobox" name="csr" style="width:130px;height:20px;"/>
                    </div><br> 
                </fieldset>
            </form>

            <div>
                <center>
                    <div>
                        <a href="javascript:void(0);"  class="easyui-linkbutton" onclick="perSubmit()"  >添加/修改</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0);" class="easyui-linkbutton"  onclick="del()" >删除</a>
                    </div>
                </center>
            </div>
        </div>
    </body>
</html>
