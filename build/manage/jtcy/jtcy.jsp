<%-- 
    Document   : jtcy
    Created on : 2013-11-11, 10:07:02
    Author     : 寻影
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="z" uri="http://opdps.hbnu.edu.cn/jplus" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>家庭成员</title>
        <link type="text/css" rel="stylesheet" href="<z:path/>js/jquery-easyui-1.3.2/themes/icon.css"/>
        <link type="text/css" rel="stylesheet" href="<z:path/>js/jquery-easyui-1.3.2/themes/default/easyui.css"/>
        <script type="text/javascript" src="<z:path/>js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="<z:path/>manage/jtcy/jtcy.js"></script>
        <script type="text/javascript">
            var path='<z:path/>';
            var ryid='${param.ryid}';  //从前面传过来的人员id
        </script>
    </head>
    <body>
        <div style=" font-size: 12px;">
             <fieldset style="border:#D3D3D3 solid 1px;margin:5px;height: 100px;">
                <legend style="margin:5px 25px 10px;font-weight: bold;font-size: 13px">人才基本信息</legend>   
            <div id="ryxx" >
                <table id="ge_table" >
                    <tr>
                        <td >单&nbsp;&nbsp;位&nbsp;&nbsp;&nbsp;名&nbsp;&nbsp;&nbsp;称：</td>
                        <td  id="ry_dwmc"></td>
                        <td style="width:40px;height: 25px;">姓名：</td>
                        <td  id="ry_xm"> </td>
<!--                        <td style="width:40px;height: 25px;">性别：</td>
                        <td  id="ry_xb"></td>-->
                    </tr>
                    <tr>
                        <td  style="width:120px;height: 25px;">身份证(护照)号码：</td>
                        <td id="ry_zjhm"></td>
                         <td style="width:40px;height: 25px;">性别：</td>
                        <td  id="ry_xb"></td>
                        
                    </tr>
                </table>
            </div>
           </fieldset>
            <div>
                <div id="jtcy" class="datagrid" toolbar="#jtcy_toobar" ></div>
                <div id="jtcy_toobar">
                    <div><a href="javascript:void(0);"  class="easyui-linkbutton" onclick="add_jtcy()" plain="true" iconCls="icon-add">添加</a></div>
                </div>
            </div>
            <div id="dgjtcy" class="easyui-dialog" style="width: 520px;height: 250px"    closed="true" >
                <form id="jtcyxx" method="post">
                    <table >
                        <tr>
                            <td style="width: 80px;height:30px">单位名称</td>
                            <td><div id="bd_dwmc"style="width: 180px" ></div></td>
                            <td >姓&nbsp;&nbsp;&nbsp;名</td>
                            <td><div id="bd_xm" ></div></td>
                            <td>性&nbsp;别&nbsp;</td>
                            <td><div id="bd_xb" ></div></td>
                        </tr>
                        <tr>
                            <td>称&nbsp;&nbsp;&nbsp;谓</td>
                            <td><input style="width: 120px" type="text" name="cw"/></td>
                            <td>成员名字</td>
                            <td ><input style="width: 80px"  type="text" name="cymz"/></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td rowspan="2">出生年月</td>
                            <td style="width: 120px;height:30px"><input type="text" name="csny" style="width: 120px;"/></td>
                            <td rowspan="2">政治面貌</td>
                            <td ><input  id="cy_zzmm" class="easyui-combobox" editable="false" width="100px"   name="zzmm"/></td>
                        </tr>
                        <tr>
                        </tr>
                        <tr>
                            <td>工作单位</td>
                            <td><input type="text" name="gzdw" style="width: 120px"/></td>
                            <td>职&nbsp;&nbsp;&nbsp;务</td>
                            <td><input style="width: 80px" type="text" name="zw"/></td>
                        </tr>
                    </table>
                    <br><br>
                    <div style="width: 420px;" id="table_2"><input style="float: right" type="button" id="tj"  name="tj" value=""/></div>
                </form>
            </div>
        </div>
    </body>
</html>
