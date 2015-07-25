/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    $.extend($.fn.validatebox.defaults.rules, {
        minLength: {
            validator: function(value, param) {
                return value.length >= param[0];
            },
            message: '请输入至少5个字符.'
        }
    });
});
function submitForm() {
    var adm=$("#adm").val();
    var pass=$("#pass").val();
    var code=$("#code").val();
    var params={
        'mode':'LOGIN',
        'adm':adm,
        'pass':pass,
        'code':code
    }
    $.ajax({
        type:'post',
        dataType:'json',
        url:path+'LoginAction.jsp',
        data:params,
        success:function(returnData){
            var notice=returnData.notice;
            if(notice==='SUCCESS'){
                window.location.href = path + 'manage/main.jsp';
            }else{
                $("#notice").text(notice);
                refreshImage();
            }
        }
    });
}
function refreshImage(){//验证码的刷新！
    var el =document.getElementById("code_image");
    el.src=el.src+'?';//这个特别重要
}
//判空函数
function empty(v){
    switch (typeof v){
        case 'undefined' :
            return true;
        case 'string' :
            if(v.replace(/^\s+|\s+$/g, '').length == 0) return true;
            break;
        case 'number' :
            if(0 === v) return true;
            break;
        case 'object' :
            if(null === v) return true;
            if(undefined !== v.length && v.length==0) return true;
            for(var k in v){
                return false;
            }
            return true;
            break;
    }
    return false; 
}