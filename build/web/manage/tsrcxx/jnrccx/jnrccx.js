$(function(){
    initAllCombbox();
    initDatagrid();
    bindEvents();
    jQuery.xxybaidu("#search_div", path+"?mode=DWMCLIST", "#dwmc");
});


function initAllCombbox(){
    
    $.post(path+'?mode=SHOWALLCOMBBOX', {}, function(data){
        tool_LoadCombobox(data,
            new Array("dwxz","dwxzbm","dwxzmc"),
            new Array("dwjb","dwlbbm","dwlbmc"),
            new Array("sshy","sshybm","sshymc"),
            new Array("xl","xlbm","xlmc"));
    });
}

function aaa(){
    alert("");
}

function initDatagrid(){
    $("#jnrcList").datagrid({
        url:path+'?mode=SHOWALL',
        title:'查询结果',
        columns:[[
        {
            field:'dwmc',
            title:'工作单位',
            width:250
        },
        {
            field:'xm',
            title:'姓名',
            width:100
        },
        {
            field:'xb',
            title:'性别',
            width:100
        },
        {
            field:'csrq',
            title:'出生日期',
            width:100
        },
        {
            field:'xlmc',
            title:'学历',
            width:100
        },
        {
            field:'ryid',
            title:'详情',
            width:100,
            formatter:function(ryid,rowData,rowIndex){
                //return "<a href='"+path+"?mode=SHOWONE&ryid="+rowData.ryid+"'>详情</a>";
                return "<a href='javascript:void(0)' onclick='window.parent.addTab(\"/manage/tsrcxx/rcxx/SearchAction.jsp?mode=SHOWONE&ryid="+rowData.ryid+"\",\""+rowData.xm+"详情\")'>详情</a>";
            }
        },
        ]],
        pagination:true
    });
}

function tool_LoadCombobox(){
    var data=arguments[0];
    var length=arguments.length;
    for(var i=1;i<length;i++){
        $("#"+arguments[i][0]).combobox({
            valueField:arguments[i][1],
            textField:arguments[i][2],
            data:data[i-1],
            editable:false
        });
    }
}

function searchfor(){
    var xlys;
    if($('#xlys').attr('checked')=="checked"){
        xlys='true';
    }else{
        xlys='false';
    }
    var dwmc;
    if($('#dwmc').val()==="输入单位名称的前若干字"){
        dwmc="";
    }else{
        dwmc=$('#dwmc').val();
    }
    $("#jnrcList").datagrid({
        url:path+'?mode=SHOWLIST',
        queryParams:{ 
            dwxz:$('#dwxz').combobox('getValue'), 
            dwjb:$('#dwjb').combobox('getValue'), 
            sshy:$('#sshy').combobox('getValue'),
            dwmc:dwmc,
            xl:$('#xl').combobox('getValue'),
            xlys:xlys,
            nl1:$('#nl1').val(),
            nl2:$('#nl2').val()
        }
    });
}

function bindEvents(){
    $("#hidden_xlys").val("false");
    $("#xlys").bind("click", 
        function(event){
            if($("#xlys").attr('checked')){
                $("#hidden_xlys").val("true");
            } else{
                $("#hidden_xlys").val("false");
            }  
        });
}

function downExcel(){
    
    $("#jnrcForm").submit();
//    var xlys;
//    if($('#xlys').attr('checked')=="checked"){
//        xlys='true';
//    }else{
//        xlys='false';
//    }
//    $("#jnrcList").datagrid({
//        url:downPath,
//        queryParams:{ 
//            hidden_xlys:xlys,
//            dwxz:$('#dwxz').combobox('getValue'),
//            xl:$('#xl').combobox('getValue'),
//            dwlb:$('#dwjb').combobox('getValue'),
//            gzdw:$('#dwmc').val(),
//            sshy:$('#sshy').combobox('getValue'),
//            nl1:$('#nl1').val(),
//            nl2:$('#nl2').val()
//            
//
//        }
//    });
}
