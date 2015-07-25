$(function(){
    //响应键盘的enter事件实现翻页
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
    var bklb=$("#get_bklb").val();
    window.location.href=path+"HbbkIndexAction.jsp?mode=SHOWBKLIST&bklb="+bklb+"&page="+gopage;//js控制窗口跳转
}