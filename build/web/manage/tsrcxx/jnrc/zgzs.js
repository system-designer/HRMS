var jbxx={
    dwm:null,
    xm:null,
    xb:null,
    zjhm:null
}

$(function(){
    initJbxx(ryid);//加载表头上的基本信息
    initTableZgzs();
    initTableRyjl();
});

function initTableRyjl(){
    setTimeout("init_ryjl()",500);
    
}

function init_ryjl(){
    $("#ryjl_List").datagrid({
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
        },

        {
            field:'ryjlid',
            title:'操作',
            width:150,
            formatter:function(ryjlid,rowData,rowIndex){
                return "<button onclick='updateRyjl("+ryjlid+")'>修改</button>&nbsp;&nbsp;&nbsp;"+
                "<button onclick='delRyjl("+ryjlid+")'>删除</button>";  
            }
        }
        ]],
        pagination:true
    });
}

function initJbxx(ryid){//初始化基本信息
    $.post(path_zgzs+'?mode=SHOWONE', {
        ryid:ryid
    }, function(data){
        $("#xm").val(data.xm);
        jbxx.xm=data.xm;
        $("#xb").val(data.xb);
        jbxx.xb=data.xb;
        $("#zjhm").val(data.zjhm);
        jbxx.zjhm=data.zjhm;
        $("#dwmc").val(data.dwmc);
        jbxx.dwm=data.dwmc;
        $("[name='xm']").val(data.xm);
        $("[name='xb']").val(data.xb);
        $("[name='dwmc']").val(data.dwmc);
    })
}

function initTableZgzs(){//初始化datagrid表格
    setTimeout("init_zgzs()",500)
}

function init_zgzs(){
    $("#table_zgzs").datagrid({
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
        },

        {
            field:'zgzsid',
            title:'操作',
            width:150,
            formatter:function(value,rowData,rowIndex){//添加修改连接
                //function里面的三个参数代表当前字段值，当前行数据对象，行号（行号从0开始）
                return "<button onclick='updateZgzs("+value+")'>修改</button>&nbsp;&nbsp;&nbsp;"+
                "<button onclick='delZgzs("+value+")'>删除</button>";
            }
        }
        ]],
        pagination:true
    });
}
            
function initAddZgzs(){//初始化添加资格证书窗口
    $("#add_dialogZgzs").dialog({
        closed:false,
        title:"资格证书添加",
        width:750,
        height:350,
        modal:true,
        buttons:[
        {
            text:'添加',
            iconCls:'icon-ok',
            handler:function(){
                $("#add_formZgzs").form('submit',{
                    url:path_zgzs+'?mode=ADDONE&ryid='+ryid,
                    success:function(data){
                        if(data==='1'){
                            $.messager.alert('提示','添加成功','info');
                            $("#add_formZgzs").form('clear',true);
                            $("#add_dialogZgzs").dialog('close');
                            initTableZgzs();
                        }else{
                            $.messager.alert('提示','添加失败','info')
                        }
                    }
                });
            }
        },
        {
            text:'取消',
            iconCls:'icon-cancel',
            handler:function(){
                $("#add_formZgzs").form('clear',true);
                $("#add_dialogZgzs").dialog('close');
            }
        }
        ]
    });
}

function updateZgzs(value){//修改资格证书
    $.post(path_zgzs+'?mode=GET', {//加载删除内容
        zgzsid:value
    }, function(rowData){
        $("#updategz").val(rowData.gz);
        $("#updatedj").val(rowData.dj);
        $("#updatezsh").val(rowData.zsh);
        $("#updatefzsj").val(rowData.fzsj);
        $("#updatefzjg").val(rowData.fzjg);
    });

    $("#updateZgzs").dialog({
        closed:false,
        title:"资格证书修改",
        width:700,
        height:400,
        modal:true,
        buttons:[
        {
            text:'修改',
            iconCls:'icon-ok',
            handler:function(){
                $("#update_formZgzs").form('submit',{
                    url:path_zgzs+'?mode=UPDATE&zgzsid='+value,
                    success:function(){
                        initTableZgzs();
                    }
                });
                initTableZgzs();
                $("#updateZgzs").dialog('close');
                $.messager.alert('提示','修改成功','info');
            }
        },
        {
            text:'取消',
            iconCls:'icon-cancel',
            handler:function(){
                $("#updateZgzs").dialog('close');
                $("#update_formZgzs").form('clear');
            }
        }
        ]
    });
}

function delZgzs(value){//删除资格证书
    $.post(path_zgzs+'?mode=GET', {//加载删除内容
        zgzsid:value
    }, function(rowData){
        $("#delgz").val(rowData.gz);
        $("#deldj").val(rowData.dj);
        $("#delzsh").val(rowData.zsh);
        $("#delfzsj").val(rowData.fzsj);
        $("#delfzjg").val(rowData.fzjg);
    });
    
    $("#deleteZgzs").dialog({
        closed:false,
        title:"删除证书修改",
        width:700,
        height:400,
        modal:true,
        buttons:[
        {
            text:'删除',
            iconCls:'icon-ok',
            handler:function(){
                $.post(path_zgzs+"?mode=DELETEONE", {
                    zgzsid:value
                }, function(data){
                    $("#deleteZgzs").dialog('close');
                    if(data.statue=="success"){     
                        $.messager.alert('提示','删除成功','info');
                    }else{
                        $.messager.alert('提示','删除失败','info');
                    }
                    initTableZgzs();
                });
            }
        },
        {
            text:'取消',
            iconCls:'icon-cancel',
            handler:function(){
                $("#deleteZgzs").dialog('close');
            }
        }
        ]
    });
}

function init_AddRyjl(){
    $("#add_dialogRyjl").dialog({
        closed:false,
        title:'荣誉奖励添加',
        width:700,
        height:400,
        modal:true,
        buttons:[
        {
            text:'添加',
            iconCls:'icon-ok',
            handler:function(){
                $("#update_ryjlform").form('submit',{
                    url:path_ryjl+'?mode=ADDONE&ryid='+ryid
                });
                initTableRyjl();
                $("#add_dialogRyjl").dialog('close');
                $("#update_ryjlform").form('clear');
                $.messager.alert('提示','添加成功','info');
            }
        },
        {
            text:'取消',
            iconCls:'icon-cancel',
            handler:function(){
                $("#add_dialogRyjl").dialog('close');
                $("#update_ryjlform").form('clear');
            }
        }
        ]
    });
}

function updateRyjl(ryjlid){
    $.post(path_ryjl+'?mode=GET&ryjlid='+ryjlid, {
        ryjlid:ryjlid
    }, function(data){
        $("#ry_ryjlmc").val(data.ryjlmc);
        $("#ry_syjg").val(data.syjg);
        $("#ry_sj").val(data.sj);
    });
    
    $("#updateRyjl").dialog({
        closed:false,
        title:'荣誉奖励修改',
        width:700,
        height:400,
        modal:true,
        buttons:[
        {
            text:'修改',
            iconCls:'icon-ok',
            handler:function(){
                $("#updateformRyjl").form('submit',{
                    url:path_ryjl+'?mode=UPDATE&ryjlid='+ryjlid,
                    success:function(){
                        initTableRyjl();
                    }
                });
                $("#updateRyjl").dialog('close');
                $("#updateformRyjl").form('clear');
                initTableRyjl();
                $.messager.alert('提示','修改成功','info');
            }
        },
        {
            text:'取消',
            iconCls:'icon-cancel',
            handler:function(){
                $("#updateRyjl").dialog('close');
                $("#updateformRyjl").form('clear');
            }
        }
        ]
    });
}

function delRyjl(ryjlid){
    $.post(path_ryjl+'?mode=GET&ryjlid='+ryjlid, {
        ryjlid:ryjlid
    }, function(data){
        $("#delryjlmc").val(data.ryjlmc);
        $("#delsyjg").val(data.syjg);
        $("#delsj").val(data.sj);
    });

    $("#delRyjl").dialog({
        closed:false,
        title:'荣誉奖励删除',
        width:700,
        height:400,
        modal:true,
        buttons:[
        {
            text:'删除',
            iconCls:'icon-ok',
            handler:function(){
                $.post(path_ryjl+'?mode=DELETE', {
                    ryjlid:ryjlid
                }, function(data){
                    $("#delRyjl").dialog('close');
                    initTableRyjl();
                    $.messager.alert("提示", "删除成功", "info");
                });
            }
        },
        {
            text:'取消',
            iconCls:'icon-cancel',
            handler:function(){
                $("#delRyjl").dialog('close');
            }
        }
        ]
    });
}