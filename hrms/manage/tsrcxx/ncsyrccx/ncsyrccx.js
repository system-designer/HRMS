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
    $.post(path+'manage/tsrcxx/ncsyrc/NcsyrccxAction.jsp?mode=gettables',"",
        function(data,statues){           
            if(data!= null){
                loadComboboxData(data,
                    new Array("dwxz","Dwxz","dwxzbm","dwxzmc",false,false),
                    new Array("dwlb","Dwlb","dwlbbm","dwlbmc",false,false),
                    new Array("xl","Xl","xlbm","xlmc",false,false),
                    new Array("sncsr","Csr","csrbm","csrmc",false,false),       
                    new Array("rclb","Ncrclb","ncrclbbm","ncrclbmc",false,false))      
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
        url:path+'manage/tsrcxx/ncsyrc/NcsyrccxAction.jsp?mode=showlist',
        queryParams:{ 
            dwxz:$('#dwxz').combobox('getValue'),     
            dwlb:$('#dwlb').combobox('getValue'),
            sncsr:$('#sncsr').combobox('getValue'),
            rclb:$('#rclb').combobox('getValue'),
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
            field:'ncrclbmc',
            width:100,
            title:'人才类别',
            align: 'center'
        },
        {
            field:'csrmc',
            width:150,
            title:'上年纯收入',
            align: 'center'
        },
        {
            field:'action',
            title:'操作',
            align: 'center',
            width:80,
            formatter:function(value,rowData,rowIndex){
                return "<a href='javascript:void(0)' onclick='window.parent.addTab(\"manage/tsrcxx/ncsyrc/NcsyrccxAction.jsp?mode=SHOWONE&ncsyrcid="+rowData.ryid+"\",\""+rowData.xm+"的详细信息\")' style=\"text-decoration: none;\">详情</a>";
            }
        }
        ]]
    })    
}

//添加绑定事件
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

