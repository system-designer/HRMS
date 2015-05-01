$(function(){
    initTableZgzs();
    initTableRyjl();
});

function initTableZgzs(){//初始化datagrid表格
    $("#zgzs1").datagrid({
        url:path_zgzs+"?mode=SHOWLIST&ryid="+ryid,
        title:'资格证书',
        columns:[[
        {
            field:'gz',
            title:'工种职业',
            width:100
        },

        {
            field:'dj',
            title:'等级',
            width:100
        },

        {
            field:'zsh',
            title:'证书号',
            width:100
        },

        {
            field:'fzsj',
            title:'发证时间',
            width:100
        },

        {
            field:'fzjg',
            title:'发证机关',
            width:100
        }
        ]],
        pagination:true
    });
}

function initTableRyjl(){
    $("#ryjl1").datagrid({
        url:path_ryjl+'?mode=SHOWLIST&ryid='+ryid,
        title:'荣誉奖励',
        columns:[[
        {
            field:'ryjlmc',
            title:'荣誉奖励名称',
            width:100
        },

        {
            field:'syjg',
            title:'授予机关',
            width:100
        },

        {
            field:'sj',
            title:'时间',
            width:100
        }
        ]],
        pagination:true
    });
}


