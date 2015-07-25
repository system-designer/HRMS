$(function(){
    //js控制一些样式
    $(".content4:last").css("border-bottom","1px solid #c2d5e3");
    $(".la span:odd").css("color","red");
    var heightR=$(".content .right").outerHeight()+parseInt($(".content .right").css("padding-top"))+parseInt($(".content .right").css("padding-bottom"));
    var heightL=$(".content .left").height()+parseInt($(".content .left").css("padding-top"))+parseInt($(".content .left").css("padding-bottom"));
    if(heightL<=heightR){
        $(".content .left").height(heightR);
        $(".htgxqm").css('bottom',3);
    }else{
        $(".content .right").height(heightL);
    }
    //给span加样式
    for(var i=0; i<$(".content4 .right").length;i++){
        var heightR1=$(".content4 .right").eq(i).outerHeight()+parseInt($(".content4 .right").eq(i).css("padding-top"))+parseInt($(".content4 .right").eq(i).css("padding-bottom"));
        var heightL1=$(".content4 .left").eq(i).height()+parseInt($(".content4 .left").eq(i).css("padding-top"))+parseInt($(".content4 .left").eq(i).css("padding-bottom"));

        if(heightL1<= heightR1){
            $(".content4 .left").eq(i).height(heightR1);
            $(".htgxqm").eq(i).css("bottom",3);
        }else{
            $(".content4 .right").eq(i).height(heightL1);
        }
    }
    //发帖editor的加载
    editorft= CKEDITOR.replace("tznr",{//加载ckeditor
        width:991,
        height:320,
        filebrowserBrowseUrl: path+'WebConnector.jsp?type=Images&mode=SHOWPICLIST',
        filebrowserUploadUrl: path+'WebConnector.jsp?type=Images&mode=UPLOADFILE'
    });
    //回帖editor的加载,回帖时不允许插入图片
    editorht= CKEDITOR.replace("htnr",{
        width:770,
        height:200,
        toolbar:[
        ['Styles','Format','Font','FontSize'],//样式，格式，字体，字号
        ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],//加粗，斜体，下划线
        ['Smiley','TextColor','BGColor','Table','HorizontalRule','SpecialChar','PageBreak'],//笑脸，文本颜色，背景色
        ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock',"Maximize"]//居左，中，右，，最大化
        ]
    });
    //响应键盘的enter事件
    $("#goto_page").focus(function(){
        $(document).keydown(function(e){
            if(e.keyCode==13){
                gotoPage();
            }
        });
    })
    //百度分享
    document.getElementById("bdshell_js").src = "http://share.baidu.com/static/js/shell_v2.js?cdnversion=" + new Date().getHours();
})
// 输入页数跳转
function gotoPage(){
    var totalpages=parseInt($("#totalpages").text());
    var gopage=parseInt($("#goto_page").val());
    if(gopage>totalpages||gopage<1){
        gopage=1;
    }
    var bkid=$("#get_bkid").val();
    var ftid=$("#get_ftid").val();
    window.location.href=path+"TznrIndexAction.jsp?mode=SHOWONE&bkid="+bkid+"&ftid="+ftid+"&page="+gopage;//js控制窗口跳转
}
/**
 * 发帖之前要检查验证码和判空
 */
function submitForm(){
    var tzbt=$("#tzbt").val();
    if(tzbt==''||tzbt==null){
        $("#codenotice").text("请填写帖子标题");
        return;
    }else{
        if(trim(tzbt)==''){
            $("#codenotice").text("请填写帖子标题");
            return;
        }
    }
    var tznr=editorft.document.getBody().getText();
    var index = editorft.document.getBody().getHtml().search("img");
    if((tznr==''||tznr==null)&&index==-1){
        $("#codenotice").text("请输入内容");
        return;
    }else{
        if(trim(tznr)==''&&index==-1){
            $("#codenotice").text("请输入内容");
            return;
        }
    }
    var code=$("#code").val();
    $.ajax({
        type:'post',
        dataType:'json',
        url:path+'TzlbIndexAction.jsp',
        data:{
            'mode':'CHECKCODE',
            'code':code
        },
        success:function(msg){
            if(msg.notice=='验证码正确'){
                $("#ftform").submit();
            }else{
                $("#codenotice").text(msg.notice);
            }
        }
    })
}
//取消发帖
function qxft(){
    $("#all").show();
    $("#editordiv").hide();
}
//点击回复，网页回到底部
function preht(){
    $('html,body').animate({
        scrollTop: '10000px'
    }, 700);
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
function RefreshImage2(){//验证码的刷新！
    var el =document.getElementById("Image2");
    el.src=el.src+'?';//这个特别重要
}
    
