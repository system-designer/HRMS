$(function() {
    loadCombobox();
    searchAll();
});
function loadCombobox() {
    $.post(path + "manage/tsrcxx/zyjsrccx/ZyjsrcSearch.jsp?mode=SHOWCOMBOBOX", '', function(datas) {
        if (datas !== null) {
            loadComboboxData(datas,
                new Array("dwxz", "Dwxz", "dwxzbm", "dwxzmc", false, false),
                new Array("dwlb", "Dwlb", "dwlbbm", "dwlbmc", false, false),
                new Array("ssxt", "Ssxt", "ssxtbm", "ssxtmc", false, false),
                new Array("zclb", "Zclb", "zclbbm", "zclbmc", false, false),
                new Array("zc", "Zc", "zcbm", "zcmc", false, false),
                new Array("xl", "Xl", "xlbm", "xlmc", false, false),
                new Array("xw", "Xw", "xwbm", "xwmc", false, false)
                );
        }
    }, "json");
}
function searchAll() {
    var dwxz = $("#dwxz").combo("getValue");//单位性质
    var dwlb = $("#dwlb").combo("getValue");//单位级别
    var ssxt = $("#ssxt").combo("getValue");//所属系统
    var dwmc = $("#dwmc").val();//单位
    var zclb = $("#zclb").combo("getValue");//职称类别
    var zc = $("#zc").combo("getValue");//技术职称
    var zcys = $("#zcys").attr("checked")?"true":"";
    var xl = $("#xl").combo("getValue");//学历
    var xlys = $("#xlys").attr("checked")?"true":"";
    var xw = $("#xw").combo("getValue");//学位
    var xwys = $("#xwys").attr("checked")?"true":"";
    var nl1 = $("#nl1").val();//年龄
    var nl2 = $("#nl2").val();
    $("#jbxx").datagrid({
        queryParams: {
            dwxz: dwxz,
            dwlb: dwlb,
            ssxt: ssxt,
            dwmc: dwmc,
            zclb: zclb,
            zc: zc,
            zcys: zcys,
            xl: xl,
            xlys: xlys,
            xw: xw,
            xwys: xwys,
            nl1: nl1,
            nl2: nl2
        },
        url: path + "manage/tsrcxx/zyjsrccx/ZyjsrcSearch.jsp?mode=SHOWSEARCH",
        pagination: true,
        rownumbers:true,
        rownumber:true,
        pageSize:10,
        fitColumns:true,
        striped:true,
        loadMsg:'数据加载中，请稍微...',
        collapsible: true,
        remoteSort: false,
        nowrap: false,
        columns: [[
        {
            field: 'dwmc', 
            title: '工作单位', 
            width: 100
        },

        {
            field: 'xm', 
            title: '姓名', 
            width: 50,
            align:'center'
        },

        {
            field: 'xb', 
            title: '性别', 
            width: 50,
            align:'center'
        },

        {
            field: 'age', 
            title: '出生年龄', 
            width: 100,
            align:'center'
        },

        {
            field: 'zclbmc', 
            title: '职称类别', 
            width: 100,
            align:'center'
        },

        {
            field: 'zcmc', 
            title: '技术职称', 
            width: 100,
            align:'center'
        },

        {
            title: '操作',
            field: 'action', 
            width: 100,
            align:'center',
            formatter: function(value, rowData, rowIndex) {
                return "<a href='javascript:;'onclick='window.parent.addTab(\"manage/tsrcxx/zyjsrccx/Zyxxxx.jsp?mode=SHOWJBXX&ryid="+rowData.ryid+"\",\""+rowData.xm+"的详细信息\")')'>详情</a>";
            }
        }
        ]]
    });
}
function bindEvents(){
    $("#hidden_xlys").val("false");
    $("#hidden_zjys").val("false");
    $("#hidden_xwys").val("false");
    $("#xlys").bind("click", 
        function(event){
            if($("#xlys").attr('checked')){
                $("#hidden_xlys").val("true");
            } else{
                $("#hidden_xlys").val("false");
            }  
        });
    $("#xwys").bind("click", 
        function(event){
            if($("#xwys").attr('checked')){
                $("#hidden_xwys").val("true");
            } else{
                $("#hidden_xwys").val("false");
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

function tjbb() {
    $("#zjrc").submit();
}