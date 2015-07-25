/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(function(){
    loadData(searchName,searchValue);
    //查找回帖
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
        url:path+"manage/HtglManageAction.jsp",
        title:"论坛回贴管理列表",
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
            field:"htid",
            checkbox:true,
            sortable:true
        },
        {
            field:"ftid",
            title:"主帖id",
            width:100
        },
        {
            field:"tzbt",
            title:"主帖标题",
            width:100
        },
        {
            field:"yhm",
            title:"回帖人",
            width:100
        },
        {
            field:"htsj",
            title:"发帖时间",
            width:100
        },
        {
            field:"sfsh",
            title:"是否审核",
            width:100
        },
        {
            field:"action",
            title:"操作",
            width:200,
            align:"center",
            formatter:function(value,rowData,rowIndex){ 
                return "<a href='javascript:void(0)' onclick='sh(\""+rowData.htid+"\")'>审核未通过</a>"
                +" "+ "<a href='javascript:void(0)' onclick='shtg(\""+rowData.htid+"\")'>审核通过</a>";
            }
        }
        ]],
        pagination:true,
        rownumbers:true
    });
}

/**
*删除回帖
*/
function deleteHt(){
    var rows = $("#maindatagrid").datagrid("getSelections");
    if(rows==null||rows.length==0){
        $.messager.alert("提示","请先选中需要删除的数据","warning");
        return;
    }
    $.messager.confirm("提示","确认删除 "+rows.length+" 条数据？",function(val){
        if(val){
            var ids = jsonString(rows);//将选中的数据中的ID拼装为合法的JSON格式数据
            $.post(path+"manage/HtglManageAction.jsp",{
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
        str+=rows[i].htid+":";
    }        
    return str;
}
/**
 * 加载所有
 */
function loadAllData(){
    if(qjtj!=''){
        loadData("tzbt",qjtj);
    }else{
        loadData("","");
    }
}
/**
 * 修改回贴信息
 */
function updateHt(){
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
    window.parent.addTab('manage/jltd/updateht.jsp?mark="update"&htid='+rows[0].htid,'修改回贴');
}
/**
 * 去掉字符串前后空格
 */
function trim(str){
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
//审核未通过
function sh(htid){
    $.ajax({
        type:'post',
        dataType:'json',
        url:path+'manage/HtglManageAction.jsp',
        data:{
            'mode':'SH',
            'htid':htid
        },
        success:function(returnData){
            $.messager.alert("提示","操作成功","info");
            loadData("","");
        }
    })
}
//审核通过
function shtg(htid){
    $.ajax({
        type:'post',
        dataType:'json',
        url:path+'manage/HtglManageAction.jsp',
        data:{
            'mode':'SHTG',
            'htid':htid
        },
        success:function(returnData){
            $.messager.alert("提示","操作成功","info");
            loadData("","");
        }
    })
}
