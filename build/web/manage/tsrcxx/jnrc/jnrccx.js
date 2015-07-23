$(function(){
    initAllCombbox();
});


function initAllCombbox(){
    
    $.post(path+'?mode=SHOWALLCOMBBOX', {}, function(data){
        tool_LoadCombobox(data,
            new Array("dwxz","dwxzbm","dwxzmc"),
            new Array("dwjb","dwlbbm","dwlbmc"),
            new Array("sshy","sshybm","sshymc"),
            new Array("xl","xlbm","xlmc"));
    });
    
    $("#dwmc").combobox({
        valueField:'gzdwid',
        textField:'dwmc',
        onChange:function(newValue, oldValue){
            $.post(path+"?mode=DWMCLIST", {
                dwmc:newValue
            }, function(data){
                $("#dwmc").combobox({
                    data:data
                });
            });
        }
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
    $("#jnrcList").datagrid({
        url:path+'?mode=SHOWLIST',
        queryParams:{ 
            dwxz:$('#dwxz').combobox('getValue'), 
            dwjb:$('#dwjb').combobox('getValue'), 
            sshy:$('#sshy').combobox('getValue'),
            dwmc:$('#dwmc').combobox('getValue'),
            xl:$('#xl').combobox('getValue'),
            nl1:$('#nl1').val(),
            nl2:$('#nl2').val()   
        },
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
            formatter:function(ryjlid,rowData,rowIndex){
                return "<a href='javascript:void(0)'>详情</a>";
            }
        },
        ]],
        pagination:true
    });
}

