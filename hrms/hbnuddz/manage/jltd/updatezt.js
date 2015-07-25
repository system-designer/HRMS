/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var editor=null;
$(function(){
    editor= CKEDITOR.replace("tznr",{//加载ckeditor
        width:900,
        height:520,
        filebrowserBrowseUrl: path+'manage/sthd/tpgl.jsp?mark=ckeditor'//直接从图片管理页面选择图片即可
    });
    loadBklbCombo();//加载板块类别的下拉框
    if(mark=='update'){//填写需要修改的信息
        setTimeout('loadOneZt()', 300);
    //        loadOneZt();
    }
})
/**
 *加载板块类别的下拉框
 */
function loadBklbCombo(){
    $("#bkid").combobox({  
        url:path+"manage/ZtglManageAction.jsp?mode=LOADBKLBCOMBO", 
        valueField:'bkid',  
        textField:'bkmc'  
    });  
}
/**
 *修改前加载单个主贴信息
 */
function loadOneZt(){
    $.ajax({
        type:'post',
        dataType:'json',
        url:path+'manage/ZtglManageAction.jsp',
        data:{
            'mode':'showone',
            'ftid':ftid
        },
        success:function(msg){
            $("#get_ftid").val(msg.ftid);
            $("#get_ftr").val(msg.ftr);
            $("#tzbt").val(msg.tzbt);
            editor.setData(msg.tznr);
            $("#bkid").combobox('select',msg.bkid);
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
                url:path+"manage/ZtglManageAction.jsp?mode="+mark,
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
