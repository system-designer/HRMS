/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(function(){
    loadBmmcCombo();//加载板块名称的下拉框
    loadZwmcCombo();//加载职务名称的下拉框
    loadData(searchName,searchValue);//加载datagrid
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
//图片未找到时所作的处理
function showOtherPic(){
    $("#hyzp").attr('src',path+"manage/image/nopic.jpg");
    return;
}
/**
 * 初始化datagrid
 */
function loadData(searchName,searchValue){
    $("#member").datagrid({
        url:path+"manage/XhcyManageAction.jsp",
        title:"协会成员管理列表",
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
            field:"id",
            checkbox:true,
            sortable:true
        },
        {
            field:"xm",
            title:"姓名",
            width:100
        },
        {
            field:"xb",
            title:"性别",
            width:50
        },
        {
            field:"xj",
            title:"系级",
            width:100
        },
        {
            field:"lxdh",
            title:"联系电话",
            width:150
        },
        {
            field:"dh",
            title:"短号",
            width:50
        },
        {
            field:"bmmc",
            title:"部门名称",
            width:100
        },
        {
            field:"zwmc",
            title:"职务名称",
            width:50
        },
        {
            field:"action",
            title:"操作",
            width:200,
            align:"center",
            formatter:function(value,rowData,rowIndex){ 
                return "<a href='javascript:void(0)' onclick='showTp(\""+rowData.xm+"\",\""+rowData.hyzp+"\")'>查看成员图片</a>"
                +" "+"<a href='javascript:void(0)' onclick='editTp(\""+rowData.id+"\",\""+rowData.xm+"\",\""+rowData.hyzp+"\")'>修改成员图片</a>";
            }
        }
        ]],
        pagination:true,
        rownumbers:true
    });
}
/**
*添加协会成员 
 */
function addRy(){
    mode="add";
    $("#ryform").form("clear");
    $("#addRy").dialog('setTitle','添加成员');
    $("#addRy").dialog({
        "icon-Cls":'icon-add'
    });
    $("#addRy").dialog("open");
}
/**
 * 修改协会成员信息
 */
function editRy(){
    mode="update";
    var rows = $("#member").datagrid("getSelections");
    if(rows==null||rows.length==0){
        $.messager.alert("提示","选中需要修改的某一行数据","warning");
        return;
    }
    if(rows.length>1){
        $.messager.alert("提示","您一次只能修改一条数据，不能同时选中多条数据！","warning");
        return;
    }
    $("#addRy").dialog('setTitle','修改信息');
    $("#addRy").dialog({
        "icon-Cls":'icon-edit'
    });
    $("#addRy").dialog("open");
    $("#get_id").val(rows[0].id);
    $("#xm").val(rows[0].xm);
    $('[name="xb"]:radio').each(function(){ //radio表单的操作
        if (this.value ==rows[0].xb ){ 
            this.checked = true;
        }
    }); 
    $("#xj").val(rows[0].xj);
    $("#lxdh").val(rows[0].lxdh);
    $("#dh").val(rows[0].dh);
    $("#bmid").val(rows[0].bmid);
    $("#bmmc").val(rows[0].bmmc);
    $("#zwfl").val(rows[0].zwfl);
    $("#zwmc").val(rows[0].zwmc);
    $("#bmmc").combobox('select',rows[0].bmmc);
    $("#zwmc").combobox('select',rows[0].zwmc);
}
/**
*删除协会成员
*/
function deleteRy(){
    var rows = $("#member").datagrid("getSelections");
    if(rows==null||rows.length==0){
        $.messager.alert("提示","请先选中需要删除的数据","warning");
        return;
    }
    $.messager.confirm("提示","确认删除 "+rows.length+" 条数据？",function(val){
        if(val){
            var ids = jsonString(rows);//将选中的数据中的ID拼装为合法的JSON格式数据
            $.post(path+"manage/XhcyManageAction.jsp",{
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
        str+=rows[i].id+":";
    }        
    return str;
}
/**
 * 异步加载提交表单，用于添加和修改
 */
function submitForm(){
    $.messager.confirm("提示","确认保存？",function(val){
        if(val){
            $("#ryform").form("submit",{
                url:path+"manage/XhcyManageAction.jsp?mode="+mode,
                success:function(returnData){
                    $.messager.alert("提示",returnData,"info");
                    $("#addRy").dialog("close");//关打开的窗口
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
    if(qjtj!=''){
        loadData("bmmc",qjtj);
    }else{
        loadData("","");
    }
}
/**
 *加载部门名称的下拉框
 */
function loadBmmcCombo(){
    $("#bmmc").combobox({  
        url:path+"manage/XhcyManageAction.jsp?mode=LOADBMMCCOMBO", 
        valueField:'bmid',  
        textField:'bmmc'  
    });  
}
/**
 *加载职务名称的下拉框
 */
function loadZwmcCombo(){
    $("#zwmc").combobox({  
        url:path+"manage/XhcyManageAction.jsp?mode=LOADZWMCCOMBO", 
        valueField:'zwid',  
        textField:'zwmc'  
    });  
}
/**
 * 显示图片
 */
function showTp(xm,hyzp){
    $("#hyzpdia").dialog("open");
    $("#hyzpdia").dialog('setTitle',xm);
    $("#hyzp").attr('src',path+"userfiles"+hyzp);
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
    var href= path+"manage/SthdPicManageAction.jsp?mode=TPXZLB&id="+$("#tempid").val()+"&dir=/image/社团图片/会员照片";
    href=encodeURI(href);//对链接进行URL重写，避免IE产生乱码
    var temp=window.open(href,"window","width="+w+",height="+h+";toolbar=no,location=no,statu=no,menubar=no,scrollbars=auto");
    temp.onbeforeunload = onbeforeunload_handler;   
    function onbeforeunload_handler(){   //窗口关闭前获取选择的图片的值
        var str=$(temp.document.getElementById("test")).val();
        if(str==''){
            return;
        }else{
            var index=str.indexOf("/image");
            str=str.substring(index);//此处已得到图片的路径，从/
            $("#tp").attr('src',path+"userfiles"+str);//更换显示图片
            $.ajax({//异步提交，修改数据库
                type:'post',
                dataType:'json',
                url:path+'manage/XhcyManageAction.jsp',
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
