/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(function(){
    loadData();//延迟加载datagrid
})
/**
 * 初始化datagrid
 */
function loadData(){
    $("#maindatagrid").datagrid({
        url:path+"manage/IndexManageAction.jsp",
        title:"环保小知识列表",
        queryParams:{
            mode:"SHOWLIST"
        },
        pageSize:20,
        striped:true,
        loadMsg:'数据加载中，请稍微...',
        remoteSort:false,
        columns:[[
        {
            field:"id",
            checkbox:true,
            sortable:true
        },
        {
            field:"bt",
            title:"标题",
            width:100
        }
        ]],
        pagination:true,
        rownumbers:true
    });
}
/**
 * 修改环保小知识
 */
function editHbxzs(){
    var rows = $("#maindatagrid").datagrid("getSelections");
    if(rows==null||rows.length==0){
        $.messager.alert("提示","选中需要修改的某一行数据","warning");
        return;
    }
    if(rows.length>1){
        $.messager.alert("提示","您一次只能修改一条数据，不能同时选中多条数据！","warning");
        return;
    }
    $("#add").dialog('setTitle','修改信息');
    $("#add").dialog({
        "icon-Cls":'icon-edit'
    });
    $("#add").dialog("open");
    $("#get_id").val(rows[0].id);
    $("#nr").val(rows[0].nr);
    $("#bt").val(rows[0].bt);
}
/**
 * 异步加载提交表单，用于添加和修改
 */
function submitForm(){
    $.messager.confirm("提示","确认保存？",function(val){
        if(val){
            $("#mainform").form("submit",{
                url:path+"manage/IndexManageAction.jsp?mode=update",
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success:function(returnData){
                    $.messager.alert("提示",returnData,"info");
                    $("#add").dialog("close");//关打开的窗口
                    loadData();//重新加载表单
                }
            });
        }
    });  
}