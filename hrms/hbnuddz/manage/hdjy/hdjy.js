$(function(){
    loadData(searchName,searchValue);
    $('#ss').searchbox({   
        width:200,   
        searcher:function(value,name){  
            searchName = name;
            searchValue = value;
            if(searchValue.length==0){
                $.messager.alert("警告","查询内容不能为空","warning");
                return;
            }
            loadData(searchName,trim(searchValue));
            $('#ss').searchbox("setValue","");
        },   
        menu:'#cxtj',   
        prompt:"请输入查询条件"
    });
})
/**
 * 初始化datagrid
 */
function loadData(searchName,searchValue){
    $("#maindatagrid").datagrid({
        url:path+"manage/HdjyManageAction.jsp",
        title:"活动剪影列表",
        queryParams:{
            'searchName':searchName,
            'searchValue':searchValue,
            mode:"SHOWLIST"
        },
        pageSize:20,
        striped:true,
        loadMsg:'数据加载中，请稍微...',
        remoteSort:false,
        columns:[[
        {
            field:"jyid",
            checkbox:true,
            sortable:true
        },
        {
            field:"hdbt",
            title:"活动标题",
            width:200
        },
        {
            field:"jybt",
            title:"剪影标题",
            width:100
        },
        {
            field:"jysj",
            title:"剪影时间",
            width:200
        },
        {
            field:"action",
            title:"操作",
            width:200,
            align:"center",
            formatter:function(value,rowData,rowIndex){ 
                return "<a href='javascript:void(0)' onclick='showTp(\""+rowData.jybt+"\",\""+rowData.jytp+"\")'>查看图片</a>";
            }
        }
        ]],
        pagination:true,
        rownumbers:true
    });
}
/**
*添加活动剪影
 */
function addHdjy(){
    mode="add";
    $("#mainform").form("clear");
    $("#add").dialog('setTitle','添加类别');
    $("#add").dialog({
        "icon-Cls":'icon-add'
    });
    $("#add").dialog("open");
}
/**
 * 修改活动剪影信息
 */
function editHdjy(){
    mode="update";
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
    $("#get_jyid").val(rows[0].jyid);
    $("#jybt").val(rows[0].jybt);
    $("#jyms").val(rows[0].jyms);
}

/**
*删除活动剪影
*/
function deleteHdjy(){
    var rows = $("#maindatagrid").datagrid("getSelections");
    if(rows==null||rows.length==0){
        $.messager.alert("提示","请先选中需要删除的数据","warning");
        return;
    }
    $.messager.confirm("提示","确认删除 "+rows.length+" 条数据？",function(val){
        if(val){
            var ids = jsonString(rows);//将选中的数据中的ID拼装为合法的JSON格式数据
            $.post(path+"manage/HdjyManageAction.jsp",{
                mode:"DELETE",
                ids:ids
            },function(returnData,status){
                if(returnData==null||returnData.length==0){
                    $.messager.alert("错误","您提交的数据错误或系统出错！","error");
                }else{
                    $.messager.alert("提示","成功删除 "+rows.length+" 条数据！","info");
                }
                loadData(searchName,searchValue);
            });
        }
    });
}
/**
*用于批量删除 
 */
function jsonString(rows){
    var str="";
    for(var i=0;i<rows.length;i++){
        str+=rows[i].lbid+":";
    }        
    return str;
}
/**
 * 异步加载提交表单，用于添加和修改
 */
function submitForm(){
    $.messager.confirm("提示","确认保存？",function(val){
        if(val){
            $("#mainform").form("submit",{
                url:path+"manage/HdjyManageAction.jsp?mode="+mode,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success:function(returnData){
                    $.messager.alert("提示",returnData,"info");
                    $("#add").dialog("close");//关打开的窗口
                    loadData("","");//重新加载表单
                }
            });
        }
    });  
}
/**
 * 加载所有
 */
function loadAllData(){
    if(qjtj!=''){
        loadData("hdbt",qjtj);
    }else{
        loadData("","");
    }
}
/**
 * 显示该类别所有活动剪影
 */
function showTp(jybt,jytp){
    $("#jytpdia").dialog("open");//打开窗口
    $("#jytpdia").dialog('setTitle',jybt);
    $("#jytp").attr('src',path+"userfiles"+jytp);
}
/**
 * 没有找到图片时显示默认的图片
 */
function showOtherPic(){
    $("#jytp").attr('src',path+"manage/image/nopic.jpg");
}
/**
 * 去掉字符串前后空格
 */
function trim(str){
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

