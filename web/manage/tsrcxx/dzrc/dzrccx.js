$(function(){  
    bindEvents();
    data();
    loadData();
})

function loadData(){
    $.post(path+'manage/tsrcxx/dzrc/DzrccxAction.jsp?mode=GETTABLES',"",
        function(data,statues){           
            if(data!= null){
                loadComboboxData(data,
                    new Array("dwxz","Dwxz","dwxzbm","dwxzmc",false,false),//单位性质Combobox
                    new Array("ssxt","Ssxt","ssxtbm","ssxtmc",false,false),//所属系统Combobox
                    new Array("zj","Zj","zjbm","zjmc",false,false),//职级Combobox
                    new Array("xl","Xl","xlbm","xlmc",false,false),//学历Combobox
                    new Array("zzmm","Zzmm","zzmmbm","zzmmmc",false,false),//政治面貌Combobox    
                    new Array("dwlb","Dwlb","dwlbbm","dwlbmc",false,false)) //单位级别Combobox     
            }
        },
        'json');
}
function submitForm(){
    data();
}

function data(){
    $('#jbxx').datagrid({
        url:path+'manage/tsrcxx/dzrc/DzrccxAction.jsp?mode=SHOWLIST',
        queryParams:{ 
            dwxz:$('#dwxz').combobox('getValue'),     
            ssxt:$('#ssxt').combobox('getValue'),
            dwlb:$('#dwlb').combobox('getValue'),
            zzmm:$('#zzmm').combobox('getValue'),
            nl1:$('#nl1').val(),
            nl2:$('#nl2').val(),
            xl:$('#xl').combobox('getValue'),
            xlys:$('#xlys').attr('checked')?"true":"false",
            zj:$('#zj').combobox('getValue'),
            zjys:$('#zjys').attr('checked')?"true":"false",
            gzdw:$('#gzdw').val()           
        },
        singleSelect:true,
        rownumbers:true,
        pagination:true,
        rownumber:true,
        fitColumns:true,
        striped:true,
        collapsible: true,
        remoteSort: false,
        nowrap: false,
        loadMsg: '数据加载中，请稍等...',
        columns:[[{
            field:'dwmc',
            width:150,
            title:'工作单位',
            align: 'center'
        },
        {
            field:'xm',
            width:100,
            title:'姓名',
            align: 'center'
        },{
            field:'xb',
            width:80,
            title:'性别',
            align: 'center'
        },{
            field:'csrq',
            width:100,
            title:'出生日期',
            align: 'center'
        },{
            field:'zjmc',
            width:100,
            title:'职级',
            align: 'center'
        },
        {
            field:'zw',
            width:100,
            title:'职务',
            align: 'center'
        },
        {
            field:'action',
            title:'操作',
            align: 'center',
            width:80,
            formatter:function(value,rowData,rowIndex){
                return "<a href='javascript:void(0)' onclick='window.parent.addTab(\"manage/tsrcxx/dzrc/DzrccxAction.jsp?mode=SHOWONE&dzrcid="+rowData.dzrcid+"\",\""+rowData.xm+"的详细信息\")'>详情</a>";
            }
        }
        ]]
    });   
}
//添加绑定事件
function bindEvents(){
    $("#hidden_xlys").val("false");
    $("#hidden_zjys").val("false");
    $("#xlys").bind("click", 
        function(event){
            if($("#xlys").attr('checked')){
                $("#hidden_xlys").val("true");
            } else{
                $("#hidden_xlys").val("false");
            }  
        });
    $("#zjys").bind("click", 
        function(event){
            if($("#zjys").attr('checked')){
                $("#hidden_zjys").val("true");
            } else{
                $("#hidden_zjys").val("false");
            }  
        });
}
function tjbb(){
    $("#dztj").submit();
}
