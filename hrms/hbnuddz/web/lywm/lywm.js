/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(function(){
    //响应键盘的enter事件
    $("#goto_page").focus(function(){
        $(document).keydown(function(e){
            if(e.keyCode==13){
                gotoPage(5);//5代表任意指定页数
            }
        });
    })
})
//指定页数翻页
function gotoPage(){
    var totalpages=parseInt($("#totalpages").text());
    var gopage=parseInt($("#goto_page").val());
    if(gopage>totalpages||gopage<1){
        gopage=1;
    }
    window.location.href=path+"LywmIndexAction.jsp?mode=SHOWLIST&page="+gopage;//js控制窗口跳转
}
/**
 * 提交留言
 */
function submitLy(){
    var lynr=$("#wbk").val();
    if(lynr==''||lynr==null){
        $("#codenotice").text("请填写留言");
        return;
    }else{
        if(trim(lynr)==''){
            $("#codenotice").text("请填写留言");
            return;
        }
    }
    var code=$("#code").val();
    $.ajax({
        type:'post',
        dataType:'json',
        url:path+'LywmIndexAction.jsp',
        data:{
            'mode':'CHECKCODE',
            'code':code
        },
        success:function(msg){
            if(msg.notice=='验证码正确'){
                $("#lyform").submit();
            }else{
                $("#codenotice").text(msg.notice);
            }
        }
    })
}
/**
 * 去掉字符串前后空格
 */
function trim(str){
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
function RefreshImage(){//验证码的刷新！
    var el =document.getElementById("Image1");
    el.src=el.src+'?';//这个特别重要
}

