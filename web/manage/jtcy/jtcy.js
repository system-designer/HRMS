var ryidxx='';//保存人员id的基本信息

$(function(){
    getRyxx();
    setTimeout('loadall()','300');
})
/*
 *ryid  人员id
 *通过ryid显示对应的家庭成员列表
 **/
function loadall(){
    $('#jtcy').datagrid({
        url: path + 'jtcy/manage/JtcyAction.jsp?mode=SHOWLIST&ryid='+ryid,
        title: '家庭成员与社会关系',
        striped: true,
        singleSelect:true,
//        fitColumns:true,
        loadMsg: '数据加载中，请稍等...',
        remoteSort: false,
        pageSize:50,
        striped:true,
        columns: [[
        {
            field: 'cw',
            title: '称谓',
            width: 70
        },
        {
            field: 'xm',
            title: '姓 名',
            width: 60
        },{
            field:'jtcyid',
            hidden:true
        }, {
            field: 'csny',
            title: '出生年月',
            width: 90
        }, {
            field: 'zzmmmc',
            title: '政治面貌',
            width: 90
        },{
            field:'zzmmbm',
            hidden:true
        },{
            field: 'gzdw',
            title: '工作单位',
            width: 120
        }, {
            field: 'zw',
            title: '职位',
            width: 90
        }, {
            field: "action1",
            width: 120,
            title:"操作",
            formatter: function(value, rowData, rowIndex) {
                return "<a href='javascript:void(0);' class='easyui-linkbutton' onclick='update_jtcy()'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' class='easyui-linkbutton' onclick='delete_jtcy()'>删除</a>";
            }
        }
        ]]
    });
}

/**
**
*通过人员id显示单个人员信息
*/
function getRyxx(){
    $.post(path+"jtcy/manage/JtcyAction.jsp?mode=SHOWONE&ryid="+ryid, '',
        function(data,status){
            var ryxx=data;
            ryidxx=ryxx[0];
            var dwmc=ryxx[0].dwmc;
            var xm=ryxx[0].xm;
            var xb=ryxx[0].xb;
            var zjhm=ryxx[0].zjhm;
            $("#ry_xm").html(xm+"&nbsp;&nbsp; &nbsp;&nbsp;");
            $("#ry_xb").html(xb+"&nbsp;&nbsp; &nbsp;&nbsp;");
            $("#ry_zjhm").html(zjhm+"&nbsp;&nbsp; &nbsp;&nbsp;");
            $("#ry_dwmc").html(dwmc+"&nbsp;&nbsp; &nbsp;&nbsp;");
        }, 'json');
}

/**
 *显示对话框信息
 *参数 mtitle 对话框的标题
 * 值分别为添加 、修改 、删除
 *
 **/
function showCyxxDialog(mtitle){
    var dwmc=ryidxx.dwmc;
    var xm=ryidxx.xm;
    var xb=ryidxx.xb;
    $("#bd_xm").html(xm+"&nbsp;&nbsp; &nbsp;&nbsp;");
    $("#bd_xb").html(xb+"&nbsp;&nbsp; &nbsp;&nbsp;");
    $("#bd_dwmc").html(dwmc+"&nbsp;&nbsp; &nbsp;&nbsp;");
    $("input[name='tj']").val(mtitle);
    $("#cy_zzmm").combobox({
        url:path+"jtcy/manage/JtcyAction.jsp?mode=COMBOBOX",   
        readOnly:true,
        width:80,
        valueField:'zzmmbm',   
        textField:'zzmmmc' 
    })
    $("#dgjtcy").dialog({
        title:"家庭成员与社会关系信息"+mtitle,
        modal:true,
        closed:false
    });
}
/*
**
*增加家庭成员信息
*
**/
function add_jtcy(){
    $("#tj").remove();
    $("#table_2").append('<input style="float: right" type="button" id="tj"  name="tj" value="" style="border:1px solid red;"/>');
    $("#jtcyxx").form('clear',true);
    $("input[name='cw']").attr('readonly',false);
    $("input[name='csny']").attr('readonly',false);
    $("input[name='gzdw']").attr('readonly',false);
    $("input[name='zw']").attr('readonly',false);
    $("input[name='cymz']").attr('readonly',false);
    showCyxxDialog("添加");
    $("#tj").click(function(){
        $("#jtcyxx").form('submit',{
            url:path+"jtcy/manage/JtcyAction.jsp?mode=ADD&&ryid="+ryid,
            success: function(data){
                if(data==='0'){
                    alert("添加失败");
                    return;
                }
                alert("添加成功");
                $("#jtcyxx").form('clear',true);
                loadall();
            }
        })
    })
                 
}
        
/**
 *
 *修改家庭成员信息
 *
 **/
function update_jtcy(){
    $("#tj").remove();
    $("#table_2").append('<input style="float: right" type="button" id="tj"  name="tj" value=""/>');
    showCyxxDialog("修改");
    var tt=$("#jtcy").datagrid({
        onClickRow:function(rowIndex, rowData){
            var cw=rowData.cw;
            var zzmmmc=rowData.cw;
            var csny=rowData.csny;
            var gzdw=rowData.gzdw;
            var zw=rowData.zw;
            var xm=rowData.xm;
            var jtcyid=rowData.jtcyid;
            var zzmmbm=rowData.zzmmbm;
            $("input[name='cw']").val(cw).attr('readonly',false);
            $('#cy_zzmm').combobox('select', zzmmbm);
            $("input[name='csny']").val(csny).attr('readonly',false);
            $("input[name='gzdw']").val(gzdw).attr('readonly',false);
            $("input[name='zw']").val(zw).attr('readonly',false);
            $("input[name='cymz']").val(xm).attr('readonly',false);
            $("#tj").click(function(){
                $("#jtcyxx").form('submit',{
                    url:path+"jtcy/manage/JtcyAction.jsp?mode=UPDATE&&ryid="+ryid+"&&jtcyid="+jtcyid,
                    success: function(data){
                        if(data==='0'){
                            alert("修改失败");
                            return;
                        }
                        alert("修改成功");
                        $("#jtcyxx").form('clear',true);
                        $("#dgjtcy").dialog({
                            closed:true
                        })
                        loadall();
                    }
                })
            })
        }
    });
}


/**
 *
 *删除某个成员信息
 **/

function delete_jtcy(){
    $("#tj").remove();
    $("#table_2").append('<input style="float: right" type="button" id="tj"  name="tj" value=""/>');
    showCyxxDialog("删除");
    var tt=$("#jtcy").datagrid({
        onClickRow:function(rowIndex, rowData){
            var cw=rowData.cw;
            var csny=rowData.csny;
            var gzdw=rowData.gzdw;
            var zw=rowData.zw;
            var xm=rowData.xm;
            var jtcyid=rowData.jtcyid;
            var zzmmmm=rowData.zzmmmc;
            $("input[name='cw']").val(cw).attr('readonly',true);
            $("input[name='csny']").val(csny).attr('readonly',true);
            $("input[name='gzdw']").val(gzdw).attr('readonly',true);
            $("input[name='zw']").val(zw).attr('readonly',true);
            $("input[name='cymz']").val(xm).attr('readonly',true);
            $('#cy_zzmm').combobox('setValue', zzmmmm);
            $("#tj").click(function(){
                $("#jtcyxx").form('submit',{
                    url:path+"jtcy/manage/JtcyAction.jsp?mode=DELETE&&ryid="+ryid+"&&jtcyid="+jtcyid,
                    success: function(data){
                        if(data==='0'){
                            alert("删除失败");
                            return;
                        }
                        alert("删除成功");
                        $("#jtcyxx").form('clear',true);
                        $("#dgjtcy").dialog({
                            closed:true
                        })
                        loadall();
                    }
                })
            })
        }
    });
}

