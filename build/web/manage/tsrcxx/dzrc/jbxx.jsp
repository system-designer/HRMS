<%-- 
    Document   : jbxx
    Created on : 2013-11-28, 18:52:33
    Author     : Xstarfct
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="z" uri="http://opdps.hbnu.edu.cn/jplus" %>
<script language="javascript">
    function printView()
    {
        window.document.body.innerHTML = $(".printArea").html();
        window.print();
        window.document.body.innerHTML = $("body").html();
    }
</script>
<style type="text/css">
    .h{
        float: left;
    }        
</style>
<div  id="xq" style="height:auto;">
    <div>
        <div style="font-size: 18px;font-weight: 800;margin-left: 450px">基本信息</div>
        <div style="position: absolute">
            <div style="margin-top:28px;position: relative;left:700px;width:120px;height: 170px;border:1px solid black;"><img style="width:120px;height: 170px;" onerror='javascript:this.src="<z:path/>manage/img/nopic.jpg";' src="<z:path/>userfiles/photo/${requestScope.list[0].gzdwid}/${requestScope.list[0].zp}"></div>
        </div>
        <div>
            <div style="padding-left: 120px;padding-top: 20px;width: 300px;">
                <span>单位名称：${requestScope.list[0].dwmc}</span>
            </div>
            <div style="margin-top: 10px;padding-left: 120px;"> 
                <div class="h" style="width: 240px;"><span>姓&nbsp;&nbsp;&nbsp;&nbsp;名：${requestScope.list[0].xm}</span></div><div class="h" style="width: 200px;margin-left: 50px"><span>性&nbsp;&nbsp;&nbsp;&nbsp;别：${requestScope.list[0].xb}</span></div>
            </div>
            <div style="margin-top: 40px;padding-left: 120px;"> 
                <div class="h" style="width: 240px;"><span>民&nbsp;&nbsp;&nbsp;&nbsp;族：${requestScope.list[0].mz == null?"":requestScope.list[0].mz==1?"汉族":"少数民族"}</span></div><div class="h" style="width: 200px;margin-left: 50px"><span>出生日期：${requestScope.list[0].csrq}</span></div>
            </div>
            <div style="margin-top: 70px;padding-left: 120px;"> 
                <div class="h" style="width: 240px;"><span>政治面貌：${requestScope.list[0].zzmmmc}</span></div><div class="h" style="width: 200px;margin-left: 50px"><span>入党时间：${requestScope.list[0].rdsj}</span></div>
            </div>
            <div style="margin-top: 100px;padding-left: 120px;"> 
                <div class="h" style="width: 240px;"><span>学&nbsp;&nbsp;&nbsp;&nbsp;历：${requestScope.list[0].xlmc}（全日制）</span></div><div class="h" style="width: 200px;margin-left: 50px"><span>学&nbsp;&nbsp;&nbsp;&nbsp;位：${requestScope.list[0].xwmc}</span></div><div class="h" style="width: 200px;"></div>
            </div>
            <div style="margin-top: 130px;padding-left: 120px;"> 
                <div class="h" style="width: 240px;"><span>毕业学校：${requestScope.list[0].byxx}</span></div><div class="h" style="width: 250px;margin-left: 50px"><span>专业名称：${requestScope.list[0].zymc}</span></div>
            </div>             
            <div style="margin-top: 160px;padding-left: 120px;"> 
                <div class="h" style="width: 240px;"><span>户籍所在地：${requestScope.list[0].hjszd}</span></div><div class="h" style="width: 200px;margin-left: 50px"><span>籍&nbsp;&nbsp;&nbsp;&nbsp;贯：${requestScope.list[0].jg}</span></div>                        
            </div>
            <div style="margin-top: 190px;padding-left: 120px;"> 
                <div class="h" style="width: 240px;"><span>联系电话：${requestScope.list[0].lxdh}</span></div><div class="h" style="width: 195px;margin-left: 50px"><span>邮政编码：${requestScope.list[0].yzbm}</span></div><div class="h" style="width: 200px;"><span>参加工作时间：<c:if test="${requestScope.list[0].gzsj!=null}">${requestScope.list[0].gzsj}年</c:if></span></div>
                </div>
                <div style="margin-top: 220px;padding-left: 120px;"> 
                    <div class="h" style="width: 240px;"><span>婚姻状况：${requestScope.list[0].hyzkbm == null?"":requestScope.list[0].hyzkbm == 1?"未婚":requestScope.list[0].hyzkbm == 2?"已婚":"离异"}</span></div><div class="h" style="width: 195px;margin-left: 50px"><span>健康状况：${requestScope.list[0].jkzk}</span></div><div class="h" ><span>通信地址：${requestScope.list[0].txdz}</span></div>
            </div>
            <div style="margin-top: 250px;padding-left: 120px;"> 
                <div class="h" style="width: 240px;"><span>职&nbsp;&nbsp;&nbsp;&nbsp;级：${requestScope.list[0].zjmc}</span></div><div class="h" style="width: 195px;margin-left: 50px"><span>职&nbsp;&nbsp;&nbsp;&nbsp;务：${requestScope.list[0].zw}</span></div><div class="h"><span>身份证（护照）号码：${requestScope.list[0].zjhm}</span></div>
            </div> 
        </div>
    </div>    
