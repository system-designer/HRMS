/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(function(){
    loadData(searchName,searchValue);
    //查找主贴
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
        url:path+"manage/ZtglManageAction.jsp",
        title:"论坛主贴管理列表",
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
            field:"ftid",
            checkbox:true,
            sortable:true
        },
        {
            field:"tzbt",
            title:"帖子标题",
            width:100
        },
        {
            field:"yhm",
            title:"发帖人",
            width:100
        },
        {
            field:"bkmc",
            title:"板块类别",
            width:100
        },
        {
            field:"ftsj",
            title:"发帖时间",
            width:100
        },
        {
            field:"llcs",
            title:"浏览次数",
            width:100
        },
        {
            field:"htzs",
            title:"回帖总数",
            width:100
        },
        {
            field:"sfsh",
            title:"是否审核",
            width:100
        },
        {
            field:"sfjj",
            title:"是否加精",
            width:100
        },
        {
            field:"sfzd",
            title:"是否置顶",
            width:100
        },
        {
            field:"action",
            title:"操作",
            width:400,
            align:"center",
            formatter:function(value,rowData,rowIndex){ 
                return  "<a href='javascript:void(0)' onclick='showHt(\""+rowData.tzbt+"\")'>查看所有回帖</a>"+
                " "+"<a href='javascript:void(0)' onclick='sh(\""+rowData.ftid+"\")'>审核未通过</a>"+
                " "+"<a href='javascript:void(0)' onclick='shtg(\""+rowData.ftid+"\")'>审核通过</a>"+
                " "+"<a href='javascript:void(0)' onclick='jj(\""+rowData.ftid+"\")'>加精</a>"+
                " "+"<a href='javascript:void(0)' onclick='qxjj(\""+rowData.ftid+"\")'>取消加精</a>"+
                " "+"<a href='javascript:void(0)' onclick='zd(\""+rowData.ftid+"\")'>置顶</a>"+
                " "+"<a href='javascript:void(0)' onclick='qxzd(\""+rowData.ftid+"\")'>取消置顶</a>";

            }
        }
        ]],
        pagination:true,
        rownumbers:true
    });
}
/**
*添加主贴
 */
function addZt(){
    mode="add";
    window.parent.addTab('manage/jltd/updatezt.jsp?mark="add"&ftid=0','添加主贴');
}
/**
 * 修改主贴信息
 */
function updateZt(){
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
    window.parent.addTab('manage/jltd/updatezt.jsp?mark="update"&ftid='+rows[0].ftid,'修改主贴');
}

/**
*删除协会成员
*/
function deleteZt(){
    var rows = $("#maindatagrid").datagrid("getSelections");
    if(rows==null||rows.length==0){
        $.messager.alert("提示","请先选中需要删除的数据","warning");
        return;
    }
    $.messager.confirm("提示","确认删除 "+rows.length+" 条数据？",function(val){
        if(val){
            var ids = jsonString(rows);//将选中的数据中的ID拼装为合法的JSON格式数据
            $.post(path+"manage/ZtglManageAction.jsp",{
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
        str+=rows[i].ftid+":";
    }        
    return str;
}
/**
 * 加载所有
 */
function loadAllData(){
    if(qjtj!=''){
        loadData("bkmc",qjtj);
    }else{
        loadData("","");
    }
}
/**
 * 显示该主贴的所有回帖
 */
function showHt(tzbt){
    window.parent.addTab('manage/jltd/htgl.jsp?searchName=tzbt&searchValue='+tzbt,tzbt);
}
/**
 * 去掉字符串前后空格
 */
function trim(str){
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
//审核未通过
function sh(ftid){
    $.ajax({
        type:'post',
        dataType:'json',
        url:path+'manage/ZtglManageAction.jsp',
        data:{
            'mode':'SH',
            'ftid':ftid
        },
        success:function(returnData){
            $.messager.alert("提示","操作成功","info");
            loadAllData();
        }
    })
}
//审核通过
function shtg(ftid){
    $.ajax({
        type:'post',
        dataType:'json',
        url:path+'manage/ZtglManageAction.jsp',
        data:{
            'mode':'SHTG',
            'ftid':ftid
        },
        success:function(returnData){
            $.messager.alert("提示","操作成功","info");
            loadAllData();
        }
    })
}
//加精
function jj(ftid){
    $.ajax({
        type:'post',
        dataType:'json',
        url:path+'manage/ZtglManageAction.jsp',
        data:{
            'mode':'JJ',
            'ftid':ftid
        },
        success:function(returnData){
            $.messager.alert("提示","操作成功","info");
            loadAllData();
        }
    })
}
//取消加精
function qxjj(ftid){
    $.ajax({
        type:'post',
        dataType:'json',
        url:path+'manage/ZtglManageAction.jsp',
        data:{
            'mode':'QXJJ',
            'ftid':ftid
        },
        success:function(returnData){
            $.messager.alert("提示","操作成功","info");
            loadAllData();
        }
    })
}
//置顶
function zd(ftid){
    $.ajax({
        type:'post',
        dataType:'json',
        url:path+'manage/ZtglManageAction.jsp',
        data:{
            'mode':'ZD',
            'ftid':ftid
        },
        success:function(returnData){
            $.messager.alert("提示","操作成功","info");
            loadAllData();
        }
    })
}
//取消置顶
function qxzd(ftid){
    $.ajax({
        type:'post',
        dataType:'json',
        url:path+'manage/ZtglManageAction.jsp',
        data:{
            'mode':'QXZD',
            'ftid':ftid
        },
        success:function(returnData){
            $.messager.alert("提示","操作成功","info");
            loadAllData();
        }
    })
}

