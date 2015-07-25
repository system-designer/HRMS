/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var editor=null;
$(function(){
    editor= CKEDITOR.replace("bknr",{//加载ckeditor
        width:900,
        height:520,
        filebrowserBrowseUrl: path+'manage/sthd/tpgl.jsp?mark=ckeditor'//直接从图片管理页面选择图片即可
    });
    loadBklbCombo();//加载百科类别的下拉框
    if(mark=='update'){//填写需要修改的信息
        setTimeout('loadOneHbbk()', 1000);
    //        loadOneHbbk();
    }
})
/**
 *加载百科类别的下拉框
 */
function loadBklbCombo(){
    $("#bklb").combobox({  
        url:path+"manage/HbbkManageAction.jsp?mode=LOADBKLBCOMBO", 
        valueField:'lbid',  
        textField:'lbmc'  
    });  
}
/**
 *修改前加载单个百科信息
 */
function loadOneHbbk(){
    $.ajax({
        type:'post',
        dataType:'json',
        url:path+'manage/HbbkManageAction.jsp',
        data:{
            'mode':'showone',
            'bkid':bkid
        },
        success:function(msg){
            $("#get_bkid").val(msg.bkid);
            $("#bkbt").val(msg.bkbt);
            editor.setData(msg.bknr);
            $("#bksj").datebox('setValue',msg.bksj);
            $("#bklb").combobox('select',msg.bklb);
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
                url:path+"manage/HbbkManageAction.jsp?mode="+mark,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success:function(returnData){
                    $.messager.alert("提示",returnData,"info");
                    $("#add").dialog("close");//关打开的窗口
                }
            });
        }
    });  
}
