/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(function(){
    loadData(searchName,searchValue);
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
        url:path+"manage/SthdManageAction.jsp",
        title:"社团活动管理列表",
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
            field:"hdid",
            checkbox:true,
            sortable:true
        },
        {
            field:"hdbt",
            title:"活动标题",
            width:250
        },
        {
            field:"xm",
            title:"活动积极分子",
            width:100
        },
        {
            field:"lbmc",
            title:"活动类别",
            width:100
        },
        {
            field:"hdsj",
            title:"活动时间",
            width:100
        },
        {
            field:"action",
            title:"操作",
            width:300,
            align:"center",
            formatter:function(value,rowData,rowIndex){ 
                return "<a href='javascript:void(0)' onclick='showHdtp(\""+rowData.hdtp+"\",\""+rowData.hdbt+"\")'>查看活动图片</a>"
                +" "+"<a href='javascript:void(0)' onclick='editTp(\""+rowData.hdid+"\",\""+rowData.hdbt+"\",\""+rowData.hdtp+"\")'>修改活动图片</a>"
                +" "+ "<a href='javascript:void(0)' onclick='uploadJy(\""+rowData.hdid+"\",\""+rowData.hdbt+"\")'>上传剪影</a>"
                +" "+"<a href='javascript:void(0)' onclick='showJylb(\""+rowData.hdbt+"\")'>查看剪影列表</a>";
            }
        }
        ]],
        pagination:true,
        rownumbers:true
    });
}
/**
*添加活动
 */
function addHd(){
    mode="add";
    window.parent.addTab('manage/sthd/updatehd.jsp?mark="add"&hdid=1','添加活动');
}
/**
 * 修改活动信息
 */
function updateHd(){
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
    window.parent.addTab('manage/sthd/updatehd.jsp?mark="update"&hdid='+rows[0].hdid,'修改:'+rows[0].hdbt);
}

/**
*删除活动
*/
function deleteHd(){
    var rows = $("#maindatagrid").datagrid("getSelections");
    if(rows==null||rows.length==0){
        $.messager.alert("提示","请先选中需要删除的数据","warning");
        return;
    }
    $.messager.confirm("提示","确认删除 "+rows.length+" 条数据？",function(val){
        if(val){
            var ids = jsonString(rows);//将选中的数据中的ID拼装为合法的JSON格式数据
            $.post(path+"manage/SthdManageAction.jsp",{
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
        str+=rows[i].hdid+":";
    }        
    return str;
}
/**
 * 加载所有
 */
function loadAllData(){
    if(qjtj!=''){
        loadData("lbmc",qjtj);
    }else{
        loadData("","");
    }
}
/**
 *上传剪影
 */
function uploadJy(hdid,hdbt){
    window.parent.addTab('manage/sthd/tpgl.jsp?param1='+hdid+"&param2="+hdbt,"请上传剪影到"+" :"+hdid+"_"+hdbt);
}
/**
 * 显示该活动下的所有剪影
 */
function showJylb(hdbt){
    window.parent.addTab('manage/hdjy/hdjy.jsp?searchName=hdbt&searchValue='+hdbt,hdbt);
}
/**
 * 显示图片
 */
function showHdtp(hdtp,hdbt){
    $("#hdtpdia").dialog("open");
    $("#hdtpdia").dialog('setTitle',hdbt);
    $("#hdtp").attr('src',path+"userfiles"+hdtp);
}
//图片未找到时所作的处理
function showOtherPic(){
    $("#hdtp").attr('src',path+"manage/image/nopic.jpg");
    return;
}
/**
 * 打开一个新的窗口选择图片
 */
function editTp(id,hdbt,hdtp){
    $("#tpdia").dialog("open");
    $("#tpdia").dialog('setTitle',hdbt);
    $("#tp").attr('src',path+"userfiles"+hdtp);//显示老图片
    $("#tempid").val(id);
    $("#temphdbt").val(hdbt);//存放要用的变量
    $("#notice").text("");//上传是否成功的提示置空
}
/**
 *浏览服务器
 */
function viewbroswer(){
    var w = screen.width;
    var h = screen.height;
    var href= path+"manage/SthdPicManageAction.jsp?mode=TPXZLB&id="+$("#tempid").val()+"&dir=/image/社团图片/活动剪影/"+$("#tempid").val()+"_"+$("#temphdbt").val();
    href=encodeURI(href);//对链接进行URL重写，避免IE产生乱码
    var temp=window.open(href,"window","width="+w+",height="+h+";toolbar=no,location=no,statu=no,menubar=no,scrollbars=auto");
    temp.onbeforeunload = onbeforeunload_handler;   //temp即为打开的窗口对象
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
                url:path+'manage/SthdManageAction.jsp',
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

