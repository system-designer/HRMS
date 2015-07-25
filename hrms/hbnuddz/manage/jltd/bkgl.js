/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var searchName="",searchValue="";//定义全局的查询条件和查询内容
$(function(){
    loadData("","");
    //查找人员
    $('#ss').searchbox({   
        width:200,   
        searcher:function(value,name){  
            searchName = name;
            searchValue = value;
            if(searchValue.length==0){
                $.messager.alert("警告","查询内容不能为空","warning");
                return;
            }
            loadData(searchName,trim(searchValue));
            $('#ss').searchbox("setValue","");
        },   
        menu:'#cxtj',   
        prompt:"请输入查询条件"
    });
})
/**
 * 初始化datagrid
 */
function loadData(searchName,searchValue){
    $("#maindatagrid").datagrid({
        url:path+"manage/BkglManageAction.jsp",
        title:"论坛板块管理列表",
        queryParams:{
            'searchName':searchName,
            'searchValue':searchValue,
            mode:"SHOWLIST"
        },
        pageSize:20,
        striped:true,
        loadMsg:'数据加载中，请稍微...',
        remoteSort:false,
        columns:[[
        {
            field:"bkid",
            checkbox:true,
            sortable:true
        },
        {
            field:"bkmc",
            title:"板块名称",
            width:100
        },
        {
            field:"yhm",
            title:"版主",
            width:100
        },
        {
            field:"tzzs",
            title:"帖子总数",
            width:100
        },
        {
            field:"action",
            title:"操作",
            width:200,
            align:"center",
            formatter:function(value,rowData,rowIndex){ 
                return "<a href='javascript:void(0)' onclick='editTp(\""+rowData.bkid+"\",\""+rowData.bkmc+"\",\""+rowData.bktb+"\")'>查看/修改板块图标</a>"
                +" "+"<a href='javascript:void(0)' onclick='showBkzt(\""+rowData.bkmc+"\")'>查看所有主贴</a>";
            }
        }
        ]],
        pagination:true,
        rownumbers:true
    });
}
/**
*添加板块
 */
function addBk(){
    mode="add";
    $("#mainform").form("clear");
    $("#add").dialog('setTitle','添加成员');
    $("#add").dialog({
        "icon-Cls":'icon-add'
    });
    $("#add").dialog("open");
}
/**
 * 修改板块信息
 */
function editBk(){
    mode="update";
    var rows = $("#maindatagrid").datagrid("getSelections");
    if(rows==null||rows.length==0){
        $.messager.alert("提示","选中需要修改的某一行数据","warning");
        return;
    }
    if(rows.length>1){
        $.messager.alert("提示","您一次只能修改一条数据，不能同时选中多条数据！","warning");
        return;
    }
    $("#add").dialog('setTitle','修改信息');
    $("#add").dialog({
        "icon-Cls":'icon-edit'
    });
    $("#add").dialog("open");
    $("#get_bkid").val(rows[0].bkid);
    $("#bkmc").val(rows[0].bkmc);
    $("#yhm").val(rows[0].yhm);
    $("#tzzs").val(rows[0].tzzs);
    $("#bkms").val(rows[0].bkms);
}

/**
*删除论坛板块
*/
function deleteBk(){
    var rows = $("#maindatagrid").datagrid("getSelections");
    if(rows==null||rows.length==0){
        $.messager.alert("提示","请先选中需要删除的数据","warning");
        return;
    }
    $.messager.confirm("提示","确认删除 "+rows.length+" 条数据？",function(val){
        if(val){
            var ids = jsonString(rows);//将选中的数据中的ID拼装为合法的JSON格式数据
            $.post(path+"manage/StggManageAction.jsp",{
                mode:"DELETE",
                ids:ids
            },function(returnData,status){
                if(returnData==null||returnData.length==0){
                    $.messager.alert("错误","您提交的数据错误或系统出错！","error");
                }else{
                    $.messager.alert("提示","成功删除 "+rows.length+" 条数据！","info");
                }
                loadData(searchName,searchValue);
            });
        }
    });
}
/**
*用于批量删除 
 */
function jsonString(rows){
    var str="";
    for(var i=0;i<rows.length;i++){
        str+=rows[i].ggid+":";
    }        
    return str;
}
/**
 * 异步加载提交表单，用于添加和修改
 */
function submitForm(){
    $.messager.confirm("提示","确认保存？",function(val){
        if(val){
            $("#mainform").form("submit",{
                url:path+"manage/BkglManageAction.jsp?mode="+mode,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success:function(returnData){
                    $.messager.alert("提示",returnData,"info");
                    $("#add").dialog("close");//关打开的窗口
                    loadData("","");//重新加载表单
                }
            });
        }
    });  
}
/**
 * 加载所有
 */
function loadAllData(){
    loadData("","");
}
/**
 * 显示该板块所有主贴
 */
function showBkzt(bkmc){
    window.parent.addTab('manage/jltd/ztgl.jsp?searchName=bkmc&searchValue='+bkmc,bkmc);
}
/**
 * 打开一个新的窗口选择图片
 */
function editTp(id,xm,hyzp){
    $("#tpdia").dialog("open");
    $("#tpdia").dialog('setTitle',xm);
    $("#tp").attr('src',path+"userfiles"+hyzp);
    $("#tempid").val(id);
    $("#notice").text("");//上传是否成功的提示置空
}
/**
 *浏览服务器
 */
function viewbroswer(){
    var w = screen.width;
    var h = screen.height;
    var href= path+"manage/SthdPicManageAction.jsp?mode=TPXZLB&id="+$("#tempid").val()+"&dir=/image/网站/论坛/板块图标";
    href=encodeURI(href);//对链接进行URL重写，避免IE产生乱码
    var temp= window.open(href,"window","width="+w+",height="+h+";toolbar=no,location=no,statu=no,menubar=no,scrollbars=auto");
    temp.onbeforeunload = onbeforeunload_handler;   
    function onbeforeunload_handler(){   //窗口关闭前获取选择的图片的值
        var str=$(temp.document.getElementById("test")).val();
        if(str==''){
            return;
        }else{
            var index=str.indexOf("/image");
            str=str.substring(index);//此处已得到图片的路径，从/image开始
            $("#tp").attr('src',path+"userfiles"+str);//更换显示图片
            $.ajax({//异步提交，修改数据库
                type:'post',
                dataType:'json',
                url:path+'manage/BkglManageAction.jsp',
                data:{
                    'mode':'UPDATETP',
                    'id':$("#tempid").val(),
                    'newPicPath':str
                },
                success:function(msg){
                    $("#notice").text(msg.notice);//提示修改是否成功
                }
            });
        };   
    }
}
/**
 * 去掉字符串前后空格
 */
function trim(str){
    return str.replace(/(^\s*)|(\s*$)/g, "");
}



