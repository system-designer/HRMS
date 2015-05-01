$(function(){
    loadGwxqDate();
    loadCombobox();
});

function loadGwxqDate(){
    $("#dwmccx").datagrid({
        url:path+"GwxqcxAction.jsp?mode=SHOWLIST",
        title:"需求岗位查询",
        queryParams:{
            dwxz:$('#dwxz_id').combobox('getValue'),
            dwjb:$('#dwlb_id').combobox('getValue'),
            sshy:$('#sshy_id').combobox('getValue'),
            gzdw:$('#gzdw').val(),
            gwxq:$('#xqgw_id').val()
        },
        loadMsg:'信息正在加载，请稍候……',
        remoteSort:false,
        striped:true,
        pageSize:10,
        columns:[[   
        {
            field:"dwmc",
            title:"单位名称",
            width:200,
            align:'center'
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
            field:"xwmc",
            title:"学位",
            width:100,
            align:'center'
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
        pagination:true,
        rownumbers:true 
    })
}
function loadCombobox(){
    $.post(path+'GwxqcxAction.jsp?mode=SHOWCOMBOBOX',"",
        function(data,statues){        
            if(data!= null){
                loadComboboxData(data,
                    new Array("dwxz_id","Dwxz","dwxzbm","dwxzmc",false,false),
                    new Array("dwlb_id","Dwlb","dwlbbm","dwlbmc",false,false),
                    new Array("sshy_id","Sshy","sshybm","sshymc",false,false))      
            }
        },
        'json');
}