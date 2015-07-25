var searchName="",searchValue="",ckeditor="";//定义全局的查询条件和查询内容
$(function(){
    ckeditor= CKEDITOR.replace("mailContent",{//加载ckeditor
        width:560,
        height:320,
        filebrowserBrowseUrl: path+'manage/sthd/tpgl.jsp?mark=ckeditor'//直接从图片管理页面选择图片即可
    });
    loadYhqxCombo();
    //查找用户
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
        url:path+"manage/YhglManageAction.jsp",
        title:"用户管理列表",
        queryParams:{
            'searchName':searchName,
            'searchValue':searchValue,
            mode:"SHOWLIST"
        },
        pageSize:10,
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
            field:"yhm",
            title:"用户名",
            width:100
        },
        {
            field:"zsxm",
            title:"真实姓名",
            width:100
        },
        {
            field:"xb",
            title:"性别",
            width:50
        },
        {
            field:"yx",
            title:"邮箱",
            width:100
        },
        {
            field:"sfjh",
            title:"是否激活",
            width:50
        },
        {
            field:"zcsj",
            title:"注册时间",
            width:100
        },
        {
            field:"zsdlsj",
            title:"最后登录时间",
            width:100
        },
        {
            field:"jf",
            title:"积分",
            width:50
        },
        {
            field:"lsjf",
            title:"临时积分",
            width:50
        },
        {
            field:"qxmc",
            title:"权限",
            width:100
        },
        {
            field:"action",
            title:"操作",
            width:200,
            align:"center",
            formatter:function(value,rowData,rowIndex){ 
                return "<a href='javascript:void(0)' onclick='editTp(\""+rowData.id+"\",\""+rowData.yhm+"\",\""+rowData.yhtx+"\")'>查看/修改用户头像</a>";
            }
        }
        ]],
        pagination:true,
        rownumbers:true
    });
}
/**
 * 修改用户信息
 */
function editYh(){
    var rows = $("#maindatagrid").datagrid("getSelections");
    if(rows==null||rows.length==0){
        $.messager.alert("提示","选中需要修改的某一行数据","warning");
        return;
    }
    if(rows.length>1){
        $.messager.alert("提示","您一次只能修改一条数据，不能同时选中多条数据！","warning");
        return;
    }
    $("#add").dialog('open'); 
    $("#get_id").val(rows[0].id);
    $('[name="xb"]:radio').each(function(){ //radio表单的操作
        if (this.value ==rows[0].xb ){ 
            this.checked = true;
        }
    }); 
    $("#yx").val(rows[0].yx);
    $("#gxqm").val(rows[0].gxqm);
    $("#yhm").val(rows[0].yhm);
    $("#zsxm").val(rows[0].zsxm);
    $("#yhtxspan").show();
    $("#yhtx").show();
    $("#yhtx").attr('src',path+"userfiles"+rows[0].yhtx);
    $("#qx").combobox('select',rows[0].qx);
}

/**
*删除论坛用户
*/
function deleteYh(){
    var rows = $("#maindatagrid").datagrid("getSelections");
    if(rows==null||rows.length==0){
        $.messager.alert("提示","请先选中需要删除的数据","warning");
        return;
    }
    $.messager.confirm("提示","确认删除 "+rows.length+" 条数据？",function(val){
        if(val){
            var ids = jsonString(rows);//将选中的数据中的ID拼装为合法的JSON格式数据
            $.post(path+"manage/YhglManageAction.jsp",{
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
            $("#mainform").form("submit",{
                url:path+"manage/YhglManageAction.jsp?mode=UPDATE",
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
    loadData(searchName,searchValue);
}
/**
 *加载用户权限的下拉框
 */
function loadYhqxCombo(){
    $("#qx").combobox({  
        url:path+"manage/YhglManageAction.jsp?mode=LOADYHQXCOMBO", 
        valueField:'qxid',  
        textField:'qxmc'  ,
        onLoadSuccess:function(){
            loadData("","");
        }
    });  
}
/**
 * 打开一个新的窗口选择图片
 */
function editTp(id,yhm,yhtx){
    $("#tpdia").dialog("open");
    $("#tpdia").dialog('setTitle',yhm);
    $("#tp").attr('src',path+"userfiles"+yhtx);
    $("#tempid").val(id);
    $("#notice").text("");//上传是否成功的提示置空
}
/**
 *浏览服务器
 */
function viewbroswer(){
    var str="";
    var w = screen.width;
    var h = screen.height;
    var href= path+"manage/SthdPicManageAction.jsp?mode=TPXZLB&id="+$("#tempid").val()+"&dir=/image/网站/论坛/用户头像";
    href=encodeURI(href);//对链接进行URL重写，避免IE产生乱码
    var temp=window.open(href,"window","width="+w+",height="+h+";toolbar=no,location=no,statu=no,menubar=no,scrollbars=auto");
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
                url:path+'manage/YhglManageAction.jsp',
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
//发送邮件给所有用户
function sendAll(){
    //发送邮件窗口
    $("#sendmail_dialog").dialog('open').dialog("setTitle","给所有用户发邮件");
    //点确定按钮
    $("#sentmailbutton").click(function(){
        $.messager.confirm('Confirm','你确定要发送该邮件到所有用户吗？',function(b){
            if(b){
                var constant=ckeditor.getData();
                var url=path+'manage/YhglManageAction.jsp';
                $.post(url, {
                    mode:"SENDALL",
                    constant:constant
                },function(returndata, type){
                    $.messager.alert('提示',returndata,'warning');                                
                }); 
            }
        });
    });
}
//给用户发送邮件，邮件类容由自己定义
function sendMail(){
    var rows=$("#maindatagrid").datagrid("getSelections");
    if(rows.length==0){
        $.messager.alert('提示','亲,还没有选择用户哦！','warning');
        return false;
    }
    //发送邮件窗口
    $("#sendmail_dialog").dialog('open').dialog("setTitle","Send Mail"); 
    var tomail=idsToStr(rows,"yhm",";");
    $("#tomail").text(tomail);
    //点确定按钮
    $("#sentmailbutton").click(function(){
        $.messager.confirm('Confirm','你确定要发送该邮件到以下'+rows.length+'位用户吗？',function(b){
            if(b){
                var constant=ckeditor.getData();
                var mailstr=idsToStr(rows,"yx",":");
                var url=path+'manage/YhglManageAction.jsp';
                $.post(url, {
                    mode:"SENDMAIL",
                    mailStr:mailstr,
                    constant:constant
                },function(returndata, type){
                    $.messager.alert('提示',returndata,'warning');                                
                }); 
            }
        });
    });
};
//jquery中将选中行中的指定的字段变成以：分割的字符串
function idsToStr(rows,idfiled,chars){
    var str="";
    for(var i=0;i<rows.length;i++){
        str+=rows[i][idfiled]+chars;
    }
    return str;
} 
/**
 * 去掉字符串前后空格
 */
function trim(str){
    return str.replace(/(^\s*)|(\s*$)/g, "");
}



