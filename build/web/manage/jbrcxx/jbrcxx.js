//人才信息搜索
var rcxx_searchName="";
var rcxx_searchValue="";
//单位信息搜索
var dwxx_searchName="dwmc";
var dwxx_searchValue="";
$(function(){
    rcxxsearch();
    loadDwxxData("","");
    loadCombobox();
    bindEvents();
})
//单位信息搜索功能
function dwxxsearch(){
    dwxx_searchValue=$("#dwxx_searchbox").val();
    if(empty(dwxx_searchValue)){
        $.messager.alert("警告","查询内容不能为空","warning");
        return;
    }
    loadDwxxData(dwxx_searchName,trim(dwxx_searchValue));
}
//人才信息搜索功能
function rcxxsearch(){
    $('#rcxx_searchbox').searchbox({   
        width:200,   
        searcher:function(value,name){  
            rcxx_searchName = name;
            rcxx_searchValue = value;
            if(rcxx_searchValue.length===0){
                $.messager.alert("警告","查询内容不能为空","warning");
                return;
            }
            loadRcxxData(rcxx_searchName,trim(rcxx_searchValue),$("#hidden_gzdwid").val());
            $('#rcxx_searchbox').searchbox("setValue","");
        },   
        menu:'#rcxx_search_params',   
        prompt:"请输入查询条件"
    });
}
// 初始化单位信息datagrid
function loadDwxxData(searchName,searchValue){
    $("#dwxx").datagrid({
        url:path+"manage/jbrcxx/JbrcxxAction.jsp",
        title:"单位管理列表",
        queryParams:{
            'searchName':searchName,
            'searchValue':searchValue,
            mode:"SHOWDWLIST"
        },
        pageSize:10,
        singleSelect:true,
        striped:true,
        loadMsg:'数据加载中，请稍微...',
        remoteSort:false,
        columns:[[
        {
            field:"dwmc",
            title:"单位名称",
            width:200
        },
        {
            field:"dwxzmc",
            title:"单位性质名称",
            width:100,
            align:'center'
        },
        {
            field:"dwlbmc",
            title:"单位级别名称",
            width:100,
            align:'center'
        },
        {
            field:"ssxtmc",
            title:"所属系统名称",
            width:100,
            align:'center'
        },
        {
            field:"action",
            title:"人员列表",
            width:100,
            align:"center",
            formatter:function(value,rowData,rowIndex){ 
                return "<a href='javascript:void(0)' onclick='showRcxx(\""+rowData.gzdwid+"\",\""+rowData.dwmc+"\")'>人员列表</a>";
            }
        }
        ]],
        pagination:true,
        rownumbers:true
    });
}
function showRcxx(gzdwid,dwmc){
    $("#rcxx_gridTools").css("display", "block");
    $("#rcxx").css("display", "block");
    loadRcxxData("","",gzdwid);
    $("#hidden_dwmc").val(dwmc);
    $("#dwmc").text($("#hidden_dwmc").val());
    $("#hidden_gzdwid").val(gzdwid);
}
// 初始化人才信息datagrid
function loadRcxxData(searchName,searchValue,gzdwid){
    $("#rcxx").datagrid({
        url:path+"manage/jbrcxx/JbrcxxAction.jsp",
        title:"人才管理列表",
        queryParams:{
            'searchName':searchName,
            'searchValue':searchValue,
            'mode':"SHOWRYLIST",
            'gzdwid':gzdwid
        },
        pageSize:10,
        striped:true,
        loadMsg:'数据加载中，请稍微...',
        remoteSort:false,
        columns:[[
        {
            field:"ryid",
            checkbox:true,
            sortable:true
        },
        {
            field:"xm",
            title:"姓名",
            width:100,
            align:"center"
        },
        {
            field:"xb",
            title:"性别",
            width:50,
            align:"center"
        },
        {
            field:"csrq",
            title:"出生日期",
            width:100,
            align:"center"
        },
        {
            field:"action0",
            title:"基本信息",
            width:100,
            align:"center",
            formatter:function(value,rowData,rowIndex){ 
                return "<a href='javascript:void(0)' onclick='editRy(\""+rowIndex+"\")'>查看/修改</a>";
            }
        },
        {
            field:"action1",
            title:"家庭成员",
            width:100,
            align:"center",
            formatter:function(value,rowData,rowIndex){ 
                return "<a href='javascript:void(0)' onclick='window.parent.addTab(\"manage/jtcy/jtcy.jsp?ryid="+rowData.ryid+"\",\""+rowData.xm+"的家庭成员信息\")'>家庭成员</a>";
            }
        },
        {
            field:"action2",
            title:"简历及奖惩",
            width:100,
            align:"center",
            formatter:function(value,rowData,rowIndex){ 
                return "<a href='javascript:void(0)' onclick='window.parent.addTab(\"manage/jbrcxx/jl.jsp?ryid="+rowData.ryid+"&dwmc="+$("#hidden_dwmc").val()+"\",\""+rowData.xm+"的简历及奖惩信息\")'>简历及奖惩</a>";
            }
        },
        //特殊人才信息
        {
            field:"action3",
            title:"创业",
            width:50,
            align:"center",
            formatter:function(value,rowData,rowIndex){ 
                if(rowData.sfcyrc!==false){
                    return "<a href='javascript:void(0)' onclick='window.parent.addTab(\"manage/tsrcxx/cyrc/cyrc.jsp?sfzjrc=1&ryid="+rowData.ryid+"\",\""+rowData.xm+"的创业人才信息\")'>维护</a>";
                }
            }
        },
        {
            field:"action4",
            title:"专技",
            width:50,
            align:"center",
            formatter:function(value,rowData,rowIndex){ 
                if(rowData.sfzjrc!==false){
                    return "<a href='javascript:void(0)' onclick='window.parent.addTab(\"manage/tsrcxx/zyjsrc/zyjsrc.jsp?sfzjrc=1&ryid="+rowData.ryid+"\",\""+rowData.xm+"的专技人才信息\")'>维护</a>";
                }
            }
        },
        {
            field:"action5",
            title:"党政",
            width:50,
            align:"center",
            formatter:function(value,rowData,rowIndex){ 
                if(rowData.sfdzrc!==false){
                    return "<a href='javascript:void(0)' onclick='window.parent.addTab(\"manage/tsrcxx/dzrc/dzrc.jsp?sfdzrc=1&ryid="+rowData.ryid+"\",\""+rowData.xm+"的党政人才信息\")'>维护</a>";
                }
            }
        },
        {
            field:"action6",
            title:"农村实用",
            width:80,
            align:"center",
            formatter:function(value,rowData,rowIndex){ 
                if(rowData.sfncsyrc!==false){
                    return "<a href='javascript:void(0)' onclick='window.parent.addTab(\"manage/tsrcxx/ncsyrc/ncsyrc.jsp?sfncsyrc=1&ryid="+rowData.ryid+"\",\""+rowData.xm+"的农村实用人才信息\")'>维护</a>";
                }
            }
        },
        {
            field:"action7",
            title:"高技能",
            width:80,
            align:"center",
            formatter:function(value,rowData,rowIndex){ 
                if(rowData.sfgjnrc!==false){
                    return "<a href='javascript:void(0)' onclick='window.parent.addTab(\"manage/tsrcxx/jnrc/zgzs.jsp?sfgjnrc=1&ryid="+rowData.ryid+"\",\""+rowData.xm+"的高技能人才信息\")'>维护</a>";
                }
            }
        }
        ]],
        pagination:true,
        rownumbers:true
    });
}
//添加绑定事件
function bindEvents(){
    $("#sfshgzrc").bind("click", 
        function(event){
            if($("#sfshgzrc").attr('checked')){
                $("#xmflbm_tr").show();
            } else{
                $("#xmflbm_tr").hide();
            }  
        });
}
/**
 *添加人员 
 */
function addRy(){
    mode="ADDRY";
    $("#submit_a").text("添加");
    $("#rcxx_form").form("clear");
    $("#zp_div").hide();
    $("#dwmc").text($("#hidden_dwmc").val());
    $("#gzdwid").val($("#hidden_gzdwid").val());
    $("#xmflbm_tr").hide();
    $("#rcxx_dialog").dialog({
        "setTitle":'添加人员',
        "icon-Cls":'icon-add'
    });
    $("#rcxx_dialog").dialog("open");
}
/**
 * 修改人员信息
 */
function editRy(rowIndex){
    var rowData=$('#rcxx').datagrid('getIndexRow',rowIndex);
    $("#dwmc").val($("#hidden_dwmc").val());
    $("#gzdwid").val($("#hidden_gzdwid").val());
    mode="UPDATERY";
    $("#submit_a").text("修改");
    $("#zp_div").show();
    $("#rcxx_dialog").dialog({
        "setTitle":'修改信息',
        "icon-Cls":'icon-add'
    });
    $("#ryid").val(rowData.ryid);
    $("#xm").val(rowData.xm);
    $('[name="xb"]:radio').each(function(){ //性别
        if (this.value ===rowData.xb ){ 
            this.checked = true;
        }
    }); 
    $("#csrq").datebox('setValue',rowData.csrq);
    $('[name="mz"]:radio').each(function(){ //民族
        if (this.value ===rowData.mz ){ 
            this.checked = true;
        }
    });
    $("#zzmmbm").combobox('select',rowData.zzmmbm);
    $("#rdsj").datebox('setValue',rowData.rdsj);
    $("#zjhm").val(rowData.zjhm);
    $("#zp").html("<img onerror='javascript:this.src=\""+path+"manage/img/nopic.jpg\";' style='width:148px;height:198px' src='"+path+"userfiles/photo/"+$("#hidden_gzdwid").val()+"/"+rowData.zp+"'/>");
    $("#xlbm").combobox('select',rowData.xlbm);
    $('[name="xxxs"]:radio').each(function(){ //民族
        if (this.value ===rowData.xxxs ){ 
            this.checked = true;
        }
    });
    $("#xwbm").combobox('select',rowData.xwbm);
    $("#byxx").val(rowData.byxx);
    $("#zymc").val(rowData.zymc);
    $("#jg").val(rowData.jg);
    $("#hjszd").val(rowData.hjszd);
    $("#jkzk").val(rowData.jkzk);
    $("#txdz").val(rowData.txdz);
    $("#yzbm").val(rowData.yzbm);
    $("#lxdh").val(rowData.lxdh);
    $("#gzsj").val(rowData.gzsj);
    $("#zjbm").combobox('select',rowData.zjbm);
    $("#zw").val(rowData.zw);
    $('[name="hyzkbm"]:radio').each(function(){ //婚姻状况
        if (this.value ===rowData.hyzkbm ){ 
            this.checked = true;
        }
    });
    //是否特殊人才
    $("#sfdzrc").attr('checked',rowData.sfdzrc);
    $("#sfglrc").attr('checked',rowData.sfglrc);
    $("#sfzjrc").attr('checked',rowData.sfzjrc);
    $("#sfgjnrc").attr('checked',rowData.sfgjnrc);
    $("#sfncsyrc").attr('checked',rowData.sfncsyrc);
    if(rowData.sfshgzrc){
        $("#sfshgzrc").attr('checked',true);
        $("#xmflbm_tr").show();
        $("#xmflbm").combobox('select',rowData.xmflbm);
    }else{
        $("#sfshgzrc").attr('checked',false);
        $("#xmflbm_tr").hide();
    }
    $("#sfcyrc").attr('checked',rowData.sfcyrc);
    $("#rcxx_dialog").dialog("open");
}
/**
 *删除人员
 */
function deleteRy(){
    var rows = $("#rcxx").datagrid("getSelections");
    if(rows==null||rows.length==0){
        $.messager.alert("提示","请先选中需要删除的数据","warning");
        return;
    }
    $.messager.confirm("提示","确认删除 "+rows.length+" 条数据？",function(val){
        if(val){
            var ids = jsonString(rows);//将选中的数据中的ID拼装为合法的JSON格式数据
            $.post(path+"manage/jbrcxx/JbrcxxAction.jsp",{
                "mode":"DELETERY",
                "ids":ids
            },function(returnData,status){
                if(returnData==null||returnData.length==0){
                    $.messager.alert("错误","您提交的数据错误或系统出错！","error");
                }else{
                    $.messager.alert("提示","成功删除 "+rows.length+" 条数据！","info");
                }
                loadRcxxData(rcxx_searchName,rcxx_searchValue,$("#hidden_gzdwid").val());
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
        str+=rows[i].ryid+",";
    }        
    return str;
}
/**
 * 异步加载提交表单，用于添加和修改
 */
function submitForm(){
    $("#rcxx_form").form("submit",{
        url:path+"manage/jbrcxx/JbrcxxAction.jsp?mode="+mode,
        onSubmit:function(){
            return  $("#rcxx_form").form('validate');
        },
        success:function(returnData){
            $.messager.alert("提示",returnData,"info");
            $("#rcxx_dialog").dialog("close");//关打开的窗口
            loadRcxxData("","",$("#hidden_gzdwid").val());//重新加载表单
        }
    });
}
//清空表单数据
function clearForm(){
    $("#rcxx_form").form("clear");
}
//加载所有单位
function loadAllDw(){
    loadDwxxData("","");
}
//加载所有人员
function loadAllRy(){
    loadRcxxData("","",$("#hidden_gzdwid").val());
}
//加载添加或修改人才时用到的combobox
function loadCombobox() {
    $.post(path + "manage/jbrcxx/JbrcxxAction.jsp", {
        mode: "SHOWCOMBOBOX"
    }, function(returnData) {
        if (returnData != null) {
            loadComboboxData(returnData,
                new Array("zzmmbm", "Zzmm", "zzmmbm", "zzmmmc", false),
                new Array("xlbm", "Xl", "xlbm", "xlmc", false),
                new Array("xwbm", "Xw", "xwbm", "xwmc", false),
                new Array("zjbm", "Zj", "zjbm", "zjmc", false),
                new Array("xmflbm", "Xmfl", "xmflbm", "xmflmc", false));
        }
    });
}
/**
* 去掉字符串前后空格
*/
function trim(str){
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
$.extend($.fn.datagrid.methods, {
    getIndexRow: function (jq, index) {
        if (!index){
            index = 0;
        }
        var data = $(jq).datagrid('options').data;
        if(empty(data)){
            data = $(jq).datagrid('getData');
        }
        return data.rows[index];
    }
});