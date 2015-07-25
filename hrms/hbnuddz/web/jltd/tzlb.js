$(function(){
    //响应键盘的enter事件
    $("#goto_page").focus(function(){
        $(document).keydown(function(e){
            if(e.keyCode==13){
                gotoPage();
            }
        });
    })
    $("#cxtzbt").focus(function(){
        $(document).keydown(function(e){
            if(e.keyCode==13){
                cxtz();
            }
        });
    })
    //发帖editor的加载
    editor= CKEDITOR.replace("tznr",{//加载ckeditor
        width:991,
        height:320,
        filebrowserBrowseUrl: path+'WebConnector.jsp?type=Images&mode=SHOWPICLIST',
        filebrowserUploadUrl: path+'WebConnector.jsp?type=Images&mode=UPLOADFILE'
    });
})
/**
             * 输入页数跳转
             */
function gotoPage(){
    var totalpages=parseInt($("#totalpages").text());
    var gopage=parseInt($("#goto_page").val());
    if(gopage>totalpages||gopage<1){
        gopage=1;
    }
    var bkid=$("#get_bkid").val();
    window.location.href=path+"TzlbIndexAction.jsp?mode=SHOWLIST&bkid="+bkid+"&page="+gopage;//js控制窗口跳转
}
/**
 * 发帖之前要检查验证码和判空
 */
function submitForm(){
    bkid=$("#bkid").children('option:selected').val();
    bkmc=$("#bkid").children('option:selected').text();
    var tzbt=$("#tzbt").val();//通过帖子标题或发帖人姓名查询
    if(tzbt==''||tzbt==null){
        $("#codenotice").text("请输入标题");
        return;
    }else{
        if(trim(tzbt)==''){
            $("#codenotice").text("请输入标题");
            return;
        }
    }
    var tznr= editor.document.getBody().getText(); //取得纯文本
    var index = editor.document.getBody().getHtml().search("img");
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
//通过帖子标题查询帖子
function cxtz(){
    var cxtzbt=trim($("#cxtzbt").val());
    window.location.href=path+"TzlbIndexAction.jsp?mode=SHOWLIST&cxtzbt="+cxtzbt+"&bkid=0"
}