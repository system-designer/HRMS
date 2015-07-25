/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(function(){
    loadData(searchName,searchValue);
    //查找人员
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
        url:path+"manage/HbbkManageAction.jsp",
        title:"环保百科管理列表",
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
            field:"bkid",
            checkbox:true,
            sortable:true
        },
        {
            field:"bkbt",
            title:"百科标题",
            width:250
        },
        {
            field:"lbmc",
            title:"百科类别",
            width:100
        },
        {
            field:"bksj",
            title:"百科时间",
            width:200
        }
        ]],
        pagination:true,
        rownumbers:true
    });
}
/**
*添加百科
 */
function addHbbk(){
    mode="add";
    window.parent.addTab('manage/hbbk/updatehbbk.jsp?mark="add"&bkid=1','添加百科');
}
/**
 * 修改百科信息
 */
function updateHbbk(){
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
    window.parent.addTab('manage/hbbk/updatehbbk.jsp?mark="update"&bkid='+rows[0].bkid,'修改百科');
}

/**
*删除环保百科
*/
function deleteHbbk(){
    var rows = $("#maindatagrid").datagrid("getSelections");
    if(rows==null||rows.length==0){
        $.messager.alert("提示","请先选中需要删除的数据","warning");
        return;
    }
    $.messager.confirm("提示","确认删除 "+rows.length+" 条数据？",function(val){
        if(val){
            var ids = jsonString(rows);//将选中的数据中的ID拼装为合法的JSON格式数据
            $.post(path+"manage/HbbkManageAction.jsp",{
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
        str+=rows[i].bkid+":";
    }        
    return str;
}
/**
 * 加载所有
 */
function loadAllData(){
    if(qjtj!=''){
        loadData("lbmc",qjtj);
    }else{
        loadData("","");
    }
}
/**
 * 去掉字符串前后空格
 */
function trim(str){
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

