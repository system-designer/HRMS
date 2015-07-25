/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//登录
$(document).ready(function(){
    $(".btn").click(function(){
        if($(".yhm").val()==""||$(".mm").val()==""||$(".yzm").val()==""){
            $(".denglu").append('<div class="tishi"></div>');
            $(".tishi").css("color","red").text("请输入完整信息");
        } else {
            if($(".yzm").val()==$(".yzm1").val()){
                $(".denglu").append('<div class="tishi"></div>');
                $(".tishi").css("color","red").text("成功");
            }else{
                $(".denglu").append('<div class="tishi"></div>');
                $(".tishi").css("color","red").text("验证码输入错误");
            }
        }
    } )
})
//我要留言
$(function(){
    $("#btnPost").click(function(){
        var title=$("#title").val();
        var body=$("#txtBody").val();
        var tr=$("<tr><td>"+"姓名："+"</td><td>"+title+"</td><td >"+"内容："+"</td><td>"+body+"</td></tr>");
        $("#tbcon").append(tr);
        $("#title").val("");
        $("#txtBody").val("");
        $("#tbcon td:even").css({
            "color":"red",
            "font-size":"18px"
        });
    })
})