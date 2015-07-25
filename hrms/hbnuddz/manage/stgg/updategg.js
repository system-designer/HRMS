/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var editor=null;
$(function(){
    editor= CKEDITOR.replace("ggnr",{//加载ckeditor
        width:900,
        height:520,
        filebrowserBrowseUrl: path+'manage/sthd/tpgl.jsp?mark=ckeditor'//直接从图片管理页面选择图片即可
    });
    loadGglbCombo();//加载公告类别的下拉框
    if(mark=='update'){//填写需要修改的信息
        setTimeout('loadOneGg()', 1000);
    //        loadOneGg();
    }
})
/**
 *加载公告类别的下拉框
 */
function loadGglbCombo(){
    $("#gglb").combobox({  
        url:path+"manage/StggManageAction.jsp?mode=LOADGGLBCOMBO", 
        valueField:'lbid',  
        textField:'lbmc'  
    });  
}
/**
 *修改前加载单个公告信息
 */
function loadOneGg(){
    $.ajax({
        type:'post',
        dataType:'json',
        url:path+'manage/StggManageAction.jsp',
        data:{
            'mode':'showone',
            'ggid':ggid
        },
        success:function(msg){
            $("#get_ggid").val(msg.ggid);
            $("#ggbt").val(msg.ggbt);
            editor.setData(msg.ggnr);
            $("#ggsj").datebox('setValue',msg.ggsj);
            $("#gglb").combobox('select',msg.gglb);
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
                url:path+"manage/StggManageAction.jsp?mode="+mark,
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
