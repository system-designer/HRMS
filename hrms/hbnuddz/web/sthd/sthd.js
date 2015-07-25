$(function(){
    if(hdid!=''){
        showHdnr(hdid,hdlb);
    }else{//隐藏返回上一级按钮
        $("#ret").hide();
    }
    //响应键盘的enter事件实现翻页
    $("#goto_page").focus(function(){
        $(document).keydown(function(e){
            if(e.keyCode==13){
                gotoPage(5);//5代表任意指定页数
            }
        });
    })
    //响应键盘的enter事件实现搜索
    $("#search_value").focus(function(){
        $(document).keydown(function(e){
            if(e.keyCode==13){
                search();
            }
        });
    })
    bindSearchEvent(); 
})
//搜索
function bindSearchEvent(){
    var newSelect = $("#aa");
    newSelect.click(function(e){
        if(this.className == "open"){
            closeSelect(this);
        }else{
            this.className = "open";
            $(this.nextSibling).slideDown("fast");
        }
        e.stopPropagation();
    });
    function closeSelect(obj){
        $(obj.nextSibling).slideUp("fast",function(){
            obj.className = "";
        });
    }
    $(document).bind("click", function() {
        closeSelect(newSelect[0]);
    });
    newSelect.next().click(function(e){
        var src = e.target;
        if(src.tagName == "A"){
            var PObj = src.parentNode;
            PObj.previousSibling.innerHTML = src.innerHTML;
            $(src).siblings().removeClass();
            src.className = "current";
            PObj.nextSibling.value = src.getAttribute("value");
            $("#search_name").val(src.getAttribute("value"));
        }
    });
}
function search(){
    if(empty(trim($("#search_value").val()))){
        $("#search_notice").text("请输入内容");
    }else{
        $("#search_form").submit();
    }
}
//指定页数翻页
function gotoPage(){
    var gopage=1;
    gopage= parseInt($("#goto_page").val());
    if(gopage>totalpages||gopage<1){
        gopage=1;
    }
    window.location.href=path+"SthdIndexAction.jsp?mode=SHOWHDLIST&page="+gopage;//js控制窗口跳转
}
/**
 * 显示活动内容
 */
function showHdnr(hdid,hdlb){
    $("#ret").show();//显示返回上一级按钮
    $("#showhdlist").hide();
    $("#hdxx").show().css("border","1px solid #cccccc");
    $("#hdxxIframe").attr('src',path+"SthdIndexAction.jsp?mode=SHOWONE&hdid="+hdid+"&hdlb="+hdlb);
}
//判空函数
function empty(v){
    switch (typeof v){
        case 'undefined' :
            return true;
        case 'string' :
            if(v.replace(/^\s+|\s+$/g, '').length === 0) return true;
            break;
        case 'number' :
            if(0 === v) return true;
            break;
        case 'object' :
            if(null === v) return true;
            if(undefined !== v.length && v.length===0) return true;
            for(var k in v){
                return false;
            }
            return true;
            break;
    }
    return false; 
}

//去掉字符串前后空格
function trim(str){
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
