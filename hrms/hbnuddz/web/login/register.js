/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function RefreshImage(){//验证码的刷新！
    var el =document.getElementById("Image1");
    el.src=el.src+'?';//这个特别重要
}
$(document).ready(function(){
    //    $("table tr td:even").css("float","right");
    $("table tr:odd").css({
        "color":"#999",
        "font-size":"13px"
    });
    $("#yhm").focus(function(){
        $("#yhmnotice").text("");
    })
    $("#yhm").blur(function(){
        checkYhm();
    })
    $("#mm").focus(function(){
        $("#mmnotice").text("");
    })
    $("#mm").blur(function(){
        checkMm();
    })
    $("#qrmm").focus(function(){
        $("#qrmmnotice").text("");
    })
    $("#qrmm").blur(function(){
        checkQrmm();
    })
})
/**
             *注册的提交
             */
function register(){
    if(judge=="1"&&checkMm()&&checkQrmm()){
        if(judge=="0"){
            $("#notice").text("请勿重复提交");
            return;
        }
        var yhm=$("#yhm").val();
        var mm=$("#mm").val();
        var xb= $("input:radio[name='xb'][checked]").val();
        var code=$("#code").val();
        var yx=$("#yx").val();
        var params={
            'mode':'REGISTER',
            'yhm':yhm,
            'mm':mm,
            'xb':xb,
            "yx":yx,
            'code':code
        }
        $.ajax({
            type:'post',
            dataType:'json',
            url:path+'IndexAction.jsp',
            data:params,
            success:function(msg){
                if(msg.notice=='验证码错误'){
                    $("#codenotice").text(msg.notice);
                }else{
                    RefreshImage();
                    judge="0";
                    $("#notice").html(msg.notice+"</br><a href='"+path+"web/login/login.jsp'>现在就登录</a>");
                }
            }
        });
    }else{
        $("#notice").text("请您核对好信息");
    }
}
/**
             *检测用户名
             */
function checkYhm(){
    var yhm=$("#yhm").val();
    var temp=trim(yhm);
    if(temp==''){
        $("#yhmnotice").text("用户名不能为空");
        judge="0";
        return;
    }
    if(temp.length>=20){
        $("#yhmnotice").text("用户名长度不符合");
        judge="0";
        return;
    }
    var params={
        'mode':'CHECKYHM',
        'yhm':yhm
    }
    $.ajax({
        type:'post',
        dataType:'json',
        url:path+'IndexAction.jsp',
        data:params,
        success:function(msg){
            $("#yhmnotice").text(msg.notice);
            if(msg.notice=='该用户名可以使用'){
                judge="1";
            }else{
                judge="0";
            }
        }
    });
}
/**
             *检测密码
             */
function checkMm(){
    var mm=$("#mm").val();
    if(mm==''){
        $("#mmnotice").text("密码不能为空");
        return false;
    }
    var temp=trim(mm);
    if(temp.length<5||temp.length>32){
        $("#mmnotice").text("密码长度不符合");
        return false;
    }else{
        $("#mmnotice").text("");
        return true;
    }
}
/**
             *确认密码
             */
function checkQrmm(){
    var mm=$("#mm").val();
    var qrmm= $("#qrmm").val();
    if(mm!=''){
        if(mm!=qrmm){
            $("#qrmmnotice").text("您两次输入的密码不一致");
            return false;
        }else{
            $("#qrmmnotice").text("");
            return true;
        }
    }else{     
        $("#mmnotice").text("密码不能为空");
        return false;
    }
}
/**
             * 去掉字符串前后空格
             */
function trim(str){
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

