/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var editor=null;
$(function(){
    editor= CKEDITOR.replace("hdnr",{//加载ckeditor
        width:900,
        height:520,
        filebrowserBrowseUrl: path+'manage/sthd/tpgl.jsp?mark=ckeditor'//直接从图片管理页面选择图片即可
    });
    loadHdlbCombo();//加载活动类别的下拉框
    if(mark=='update'){//填写需要修改的信息
        $("#hidehdbt").hide();
        $("#hdbt").hide();//活动标题不能修改
        setTimeout(' loadOneHd()',1000);
    }else{
        $("#hidehdbt").show();
        $("#hdbt").show();
    }
})
/**
 *加载公告类别的下拉框
 */
function loadHdlbCombo(){
    $("#hdlb").combobox({  
        url:path+"manage/SthdManageAction.jsp?mode=LOADHDLBCOMBO", 
        valueField:'lbid',  
        textField:'lbmc'  
    });  
}
/**
 *修改前加载单个活动信息
 */
function loadOneHd(){
    $.ajax({
        type:'post',
        dataType:'json',
        url:path+'manage/SthdManageAction.jsp',
        data:{
            'mode':'showone',
            'hdid':hdid
        },
        success:function(msg){
            $("#get_hdid").val(msg.hdid);
            $("#xm").val(msg.xm);
            editor.setData(msg.hdnr);
            $("#hdsj").datebox('setValue',msg.hdsj);
            $("#hdlb").combobox('select',msg.hdlb);
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
                url:path+"manage/SthdManageAction.jsp?mode="+mark,
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
