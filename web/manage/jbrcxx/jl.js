//根据textarea_id加载ckeditor
function loadEditor(textarea_id){
    var temp= CKEDITOR.replace(textarea_id,{
        width:770,
        height:200,
        toolbar:[
        ['Styles','Format','Font','FontSize'],//样式，格式，字体，字号
        ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],//加粗，斜体，下划线
        ['TextColor','BGColor','Table','HorizontalRule','SpecialChar','PageBreak'],//笑脸，文本颜色，背景色
        ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock',"Maximize"]//居左，中，右，，最大化
        ]
    });
    return temp;
}
/**
 * 异步加载提交表单，用于添加和修改
 */
function submitForm(){
    $.messager.confirm("提示","确认保存？",function(val){
        if(val){
            $("#jlxx_form").form("submit",{
                url:path+"manage/jbrcxx/JbrcxxAction.jsp?mode="+mode,
                onSubmit:function(){
                    return  $(this).form('validate');
                },
                success:function(returnData){
                    $.messager.alert("提示",returnData,"info");
                }
            });
        }
    });  
}
//清空表单数据
function clearForm(){
    $("#jlxx_form").form("clear");
}
function deleteJl(){
    $.messager.confirm("提示","确认删除？",function(val){
        if(val){
            $.post(path+"manage/jbrcxx/JbrcxxAction.jsp",{
                "mode":"DELETEJL",
                "ryid":ryid
            },function(returnData,status){
                if(returnData==null||returnData.length==0){
                    $.messager.alert("错误","您提交的数据错误或系统出错！","error");
                }else{
                    $.messager.alert("提示","删除成功！","info");
                }
                clearForm();
            });
        }
    });
}
//初始化
function init(){
    $.ajax({
        type:'post',
        dataType:'json',
        url:path+'/manage/jbrcxx/JbrcxxAction.jsp',
        data:{
            'ryid':ryid,
            'mode':'SHOWONERY'
        },
        success:function(returnData){
            var rcxx=returnData.rcxx;
            if(empty(rcxx.jlid)){
                $("#delete_button").hide();
                mode="ADDJL";
            }else{
                mode="UPDATEJL";
            }
            $("#xm").text(rcxx.xm);
            $("#xb").text(rcxx.xb);
            $("#zjhm").text(rcxx.zjhm);
            $("#zj").text(rcxx.zjmc);
            $("#zw").text(rcxx.zw);
            //填写特殊人才信息
            var xxjl_editor=loadEditor("xxjl");
            $("#xxjl").val(rcxx.xxjl);
            var gzjl_editor=loadEditor("gzjl");
            $("#gzjl").val(rcxx.gzjl);
            var jcqk_editor=loadEditor("jcqk");
            $("#jcqk").val(rcxx.jcqk);
        }
    });
}