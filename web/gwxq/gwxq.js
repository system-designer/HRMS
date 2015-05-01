
$(function(){
    loadDwxxDate();
    loadCombobox();
})
/**
 *初始化单位信息
 *datagrid
 */
function loadDwxxDate(){
     
    $("#dwmc").datagrid({
        url:path+"gwxq/GwxqAction.jsp",
        title:"需求岗位列表",
        queryParams:{
            mode:"SHOWLIST"
        },
        rownumbers:true,
        pagination:true,
        rownumber:true,
        fitColumns:true,
        striped:true,
        collapsible:true,
        nowrap:false,
        checkOnSelect:false,
        singleSelect:true,
        loadMsg:'数据加载中，请稍微...',
        remoteSort:false,
        columns:[[
        {
            field:"xqgwid",
            checkbox:true
        },
        {
            field:"dwid",
            hidden:"true"
        },
        {
            field:"xqgw",
            title:"需求岗位",
            width:100,
            align:'center'
        },
        {
            field:"zy",
            title:"专业",
            width:100,
            align:'center'
        },
        {
            field:"xlmc",
            title:"学历",
            width:100,
            align:'center'
        },
        {
            field:"xlbm",
            hidden:"true"
        },
        {
            field:"xwmc",
            title:"学位",
            width:100,
            align:'center'
        },
        {
            field:"xwbm",
            hidden:"true"
        },
        {
            field:"rs",
            title:"人数",
            width:100,
            align:'center'
        },
        {
            field:"yjfs",
            title:"引进方式",
            width:100,
            align:'center'
        },
        {
            field:"gwyq",
            title:"岗位要求",
            width:100,
            align:'center'
        },
        {
            field:"dy",
            title:"待遇",
            width:100,
            align:'center'
        },
        {
            field:"fbsj",
            title:"发布时间",
            width:100,
            align:"center"
        }
        ]],
    });  
}
/**
 * 添加岗位
 */
function loadGwtj(){
    getGzdw();
    $("#add_dialog").dialog("open");   
}


function getGzdw(){
    $.ajax({
        url: path + 'gwxq/GwxqAction.jsp?mode=getgzdw',
        type: 'post',
        dataType:'html',
        success: function(data) {
            if (data !== null) {
                $('input:[id=dwmc]').val(data);
                $('input:[id=yrdwa]').val(data);
            }
        }
    });
}
/**
 * 修改岗位
 */
function editGw(){
    
    var rows= $('#dwmc').datagrid("getSelections");
    var length=rows.length;
    if(length==0){
        $.messager.alert("提示","请先选中需要修改的数据","warning"); 
    }else if(length>1){
        $.messager.alert("提示","不能够同时修改多条数据","warning");
    }else{
        $("#edit_dialog").dialog("open");
        $("#edit_dialog").dialog("open"); 
        getGzdw();
        $("#yrdwa").val(rows[0].dwid);
        $("#fbsja").val(rows[0].fbsj);
        $("#xqgwa").val(rows[0].xqgw);
        $("#zya").val(rows[0].zy);
        $("#xl").combobox('select',rows[0].xlbm);
        $("#xw").combobox('select',rows[0].xwbm);
        $("#yjfsa").val(rows[0].yjfs);
        $("#gwyqa").val(rows[0].gwyq);
        $("#dya").val(rows[0].dy);
        $("#rsa").val(rows[0].rs);
        $("#yrdwa").val($("#hidden_gzdwid").val());
        $("#xqgwid_hidden").val(rows[0].xqgwid)
    }
}
/**
 * 异步加载提交表单（添加）
 */
function submitForma(){
    $("#form_add").form("submit",{
        url:path+"gwxq/GwxqAction.jsp?mode=ADDONE",
        success:function(returnData){
            $.messager.alert("提示",returnData,"info");
            $("#add_dialog").dialog("close");//关打开的窗口
        }  
    }
    )
}
/**
 *异步加载提交表单（修改）
 */
function submitForme(){
    $("#form_edit").form("submit",{
        url:path+"gwxq/GwxqAction.jsp?mode=EDITONE",
        success:function(returnData){
            $.messager.alert("提示",returnData,"info");
            $("#edit_dialog").dialog("close");//关闭打开的窗口
        }  
    }
    )
}

/**
 * 用于批量删除
 */
function deleteRy(){
    var rows = $("#dwmc").datagrid("getSelections");
    if(rows==null||rows.length==0){
        $.messager.alert("提示","请先选中需要删除的数据","warning");
        return;
    }
    $.messager.confirm("提示","确认删除 "+rows.length+" 条数据？",function(val){
        if(val){
            var ids = jsonString1(rows);//将选中的数据中的ID拼装为合法的JSON格式数据
            $.post(path+"gwxq/GwxqAction.jsp",{
                "mode":"deleteselect",
                "ids":ids
            },function(returnData,status){
                if(returnData==null||returnData.length==0){
                    $.messager.alert("错误","您提交的数据错误或系统出错！","error");
                }else{
                    $.messager.alert("提示","成功删除 "+rows.length+" 条数据！","info");
                }
            });
        }
    });
}
function jsonString1(rows){
    var str="";
    for(var i=0;i<rows.length;i++){
        str+=rows[i].xqgwid+",";
    }        
    return str;
}
/**
 * 这是添加和修改用到combobox
 */
function loadCombobox() {
    $.post(path + "gwxq/GwxqAction.jsp", {
        mode: "SHOWCOMBOBOX"
    }, function(returnData) {
        if (returnData != null) {
            loadComboboxData(returnData, 
                new Array("xlt", "Xl", "xlbm", "xlmc", false),
                new Array("xwt", "Xw", "xwbm", "xwmc", false),
                new Array("xl", "Xl", "xlbm", "xlmc", false),
                new Array("xw", "Xw", "xwbm", "xwmc", false));
        }
    });
}




