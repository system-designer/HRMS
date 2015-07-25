/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//给右边的工作区添加tab
var tabWidth;//右边工作区的宽度
var tabHeight;//右边工作区的高度
/**
 * 显示年月日和提示信息
 */
$(function(){
    tabWidth= parseInt($("#workTabs").css("width"));
    tabHeight = parseInt($("#workTabs").css("height"))-32;//得到右边工作区的高度和宽度
    var week = new Array();
    week[1]="一";
    week[2]="二";
    week[3]="三";
    week[4]="四";
    week[5]="五";
    week[6]="六";
    week[0]="日";
    var date = new Date();
    var year = date.getFullYear()+"年";
    var month =(date.getMonth()+1<10?("0"+(date.getMonth()+1).toString()):date.getMonth()+1)+"月";
    var day =(date.getDate()<10?("0"+date.getDate().toString()):date.getDate())+"日";
    var time = year+month+day+"  星期"+week[date.getDay()];
    $("#time").html(time);
    $.messager.show({
        title:'提示',
        msg:'欢迎登录湖北师范学院环保协会网站后台管理！</br>今天是：'+time,
        timeout:10000,
        showType:'slide'
    });   
})            
//添加一个tab到右侧工作区
function addTab(href,title){
    if($("#workTabs").tabs("exists",title)){
        $("#workTabs").tabs('select',title);
    }else{
        $("#workTabs").tabs("add",{
            title:title,
            content:"<iframe src='"+path+href+"' style='border:0px;overflow:auto;width:"+tabWidth+"px;height:"+tabHeight+"px;'  noresize='noresize'></iframe>",
            closable:true
        });
    }
}

//关闭一个tab
function delTab(title){
    $("#workTabs").tabs('close',title);
}

