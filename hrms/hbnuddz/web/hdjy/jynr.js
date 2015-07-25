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
    var hdid=$("#get_hdid").val();
    window.location.href=path+"ShowHdjyIndexAction.jsp?mode=SHOWHDJYLIST&hdid="+hdid+"&page="+gopage;//js控制窗口跳转
}
//查看剪影图
function showIMG(img){
    $("#jyImgDiv").show();
    $("#jyImgDiv >div").eq(1).css({
        position:"relative",
        top:-83,
        "z-index":-1
    }).html("<img height='450px' src='"+path+"userfiles"+img+"' id='jyImg' onerror='javascript:this.src=\""+path+"manage/image/nopic.jpg\";' />");
    var hxtImgDiv_w = parseInt($("#jyImg").css("width"));
    var hxtImgDiv_h = parseInt($("#jyImg").css("height"));
    $("#jyImgDiv").css({
        left:(document.documentElement.clientWidth-hxtImgDiv_w)/2,
        top:document.documentElement.scrollTop + (document.documentElement.clientHeight-hxtImgDiv_h)/2
    }).show();
}

function closeIMG(){
    $("#jyImgDiv").hide();
}
