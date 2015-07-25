/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var editor=null;
$(function(){
    editor= CKEDITOR.replace("htnr",{//加载ckeditor
        width:900,
        height:520,
        filebrowserBrowseUrl: path+'manage/sthd/tpgl.jsp?mark=ckeditor'//直接从图片管理页面选择图片即可
    });
    if(mark=='update'){//填写需要修改的信息
        setTimeout('loadOneHt()', 300);
    //        loadOneHt();
    }
})
/**
 *修改前加载单个主贴信息
 */
function loadOneHt(){
    $.ajax({
        type:'post',
        dataType:'json',
        url:path+'manage/HtglManageAction.jsp',
        data:{
            'mode':'showone',
            'htid':htid
        },
        success:function(msg){
            $("#get_htid").val(msg.htid);
            $("#htr").val(msg.yhm);
            $("#tzbt").val(msg.tzbt);
            editor.setData(msg.htnr);
        }
    });
}
/**
 * 异步加载提交表单，用于添加和修改
 */
function submitForm(){
    $.messager.confirm("提示","确认保存？",function(val){
        if(val){
            $("#mainform").form("submit",{
                url:path+"manage/HtglManageAction.jsp?mode="+mark,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success:function(returnData){
                    $.messager.alert("提示",returnData,"info");
                }
            });
        }
    });  
}
