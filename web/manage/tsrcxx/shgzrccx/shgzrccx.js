/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(function(){  
    bindEvents();
    data();
    loadData();
})

function loadData(){
    $.post(path+'manage/tsrcxx/shgzrc/ShgzrccxAction.jsp?mode=gettables',"",
        function(data,statues){           
            if(data!= null){
                loadComboboxData(data,
                    new Array("dwxz","Dwxz","dwxzbm","dwxzmc",false,false),
                    new Array("dwlb","Dwlb","dwlbbm","dwlbmc",false,false),
                    new Array("xl","Xl","xlbm","xlmc",false,false),
                    new Array("zzmm","Zzmm","zzmmbm","zzmmmc",false,false),       
                    new Array("ssxt","Ssxt","ssxtbm","ssxtmc",false,false))      
            }
        },
        'json');
}
function submitForm(){
    data();
}


function data(){
    var xlys = document.getElementById("xlys");
    $('#jbxx').datagrid({
        url:path+'manage/tsrcxx/shgzrc/ShgzrccxAction.jsp?mode=showlist',
        queryParams:{ 
            dwxz:$('#dwxz').combobox('getValue'),     
            dwlb:$('#dwlb').combobox('getValue'),
            ssxt:$('#ssxt').combobox('getValue'),
            zzmm:$('#zzmm').combobox('getValue'),
            nl1:$('#nl1').val(),
            nl2:$('#nl2').val(),
            xl:$('#xl').combobox('getValue'),
            gzdw:$('#gzdw').val(),
            xlys:xlys.checked?"true":"false"
        },
        singleSelect:true,
        rownumbers:true,
        pagination:true,
        rownumber:true,
        fitColumns:true,
        striped:true,
        collapsible:true,
        remoteSort:false,
        nowrap:false,
        checkOnSelect:false,
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
            field:'xlmc',
            width:100,
            title:'学历',
            align: 'center'
        },
        {
            field:'zzmmmc',
            width:150,
            title:'政治面貌',
            align: 'center'
        },
        {
            field:'action',
            title:'操作',
            align: 'center',
            width:80,
            formatter:function(value,rowData,rowIndex){
                return "<a href='javascript:void(0)' onclick='window.parent.addTab(\"manage/tsrcxx/shgzrc/ShgzrccxAction.jsp?mode=SHOWONE&shgzrcid="+rowData.ryid+"\",\""+rowData.xm+"的详细信息\")' style=\"text-decoration: none;\">详情</a>";
            }
        }
        ]]
    })    
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
function tjbb(){
    $("#cxtj").submit();
}
