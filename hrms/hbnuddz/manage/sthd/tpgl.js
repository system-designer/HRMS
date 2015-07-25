/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var parent="";
var img;//当前选中图片
var select=-1;//当中选中图片的ID，实质上为该图片对应的ID
var folder="";
var selectNode;//选择的节点
var click = false;
var files=0;//文件上传个数
var uploads=0;//已上传的文件个数
var action;

$(function(){
    $('#folderTree').tree({
        onContextMenu: function(e, node){
            e.preventDefault();
            $('#folderTree').tree('select', node.target);
            $('#folderMenu').menu('show', {
                left: e.pageX,
                top: e.pageY
            });
            folder=node.id;
            selectNode=node;
        }
    });
    $("#uploadify").uploadify({ 
        'swf' : basePath+'manage/uploadtool/uploadify.swf', 
        'cancelImage' :  basePath+'manage/uploadtool/uploadify-cancel.png',//队列表中每个文件的取消叉叉
        'buttonText' :'浏览',
        'queueID' : 'fileQueue',
        'auto' : false,//选中自动提交
        'multi' : true, 
        'wmode' : 'transparent',
        'method':'get',
        'uploaderType':'flash',
        'simUploadLimit' : 999, 
        'height':30,
        'width':100,
        'fileTypeExts' : '*.png;*.gif;*.jpg;*.bmp;*.jpeg',
        'fileTypeDesc' : '图片文件(*.png;*.gif;*.jpg;*.bmp;*.jpeg)', //浏览窗口中的文件类型
        'onQueueComplete': function(queueData) { //当文件队列上传成功后
            getFolder();
            $.messager.alert("提示","成功上传"+uploads+"个文件");
            $("#uploadMess").html("");
            files=0;    
            uploads=0;
        },
        'onUploadStart':function(file){//当每个文件上传的时候会触发的事件
            uploads++;
            if(param1!==''&&param2!==''){
                $("#uploadify").uploadify('settings','uploader',encodeURI(basePath+"manage/SthdPicManageAction.jsp?mode=upload&folder="+folder+"&hdid="+param1));
            }else{
                $("#uploadify").uploadify('settings','uploader',encodeURI(basePath+"manage/SthdPicManageAction.jsp?mode=upload&folder="+folder));//设定文件的上传路径
            }
            $("#uploadMess").html("已上传<font color='blue'>"+files+"</font>中的<font color='blue'>"+uploads+"</font>个文件...");//每一个文件上传提示
        },
        'onSelect':function(file){
            if(constants(file.name,"#")||constants(file.name,"%")){
                $.messager.alert("警告","文件名为 "+file.name+" 的文件含有非法字符\"#\"或\"%\"，将被移出上传队列","warning");
                $('#uploadify').uploadify('cancel',file.id); 
            }else{
                files++;
                $("#uploadMess").html("<font color='blue'>"+files+"</font>个文件等待上传.");
            }
        }
    });
});

/**
 *检测一个字符串中是否存在另一个字符串
 */
function constants(str,tar){
    if(typeof(str)=="undefined"||typeof(tar)=="undefined"){
        return false;
    }
    var len = tar.length;
    for(var i=0;i<str.length;){
        if(str.substring(i,i+len)==tar){
            return true;
        }
        i+=len;
    }
    return false;
}


/**
 * 获得文件夹
 **/
function getFolder(){
    var node = $("#folderTree").tree("getSelected");
    if(node!=null){
        var children = $('#folderTree').tree('getChildren', node.target);
        if(children!=null&&children.length>0)
            return false;
        $.post(basePath+"manage/SthdPicManageAction.jsp",{
            mode:"HDTPGL",
            folder:node.id==null?node.text:node.id
        },function(returnData,status){//返回的数据格式是包含一个属性的对象，属性名是“folder”或“img”
            if(typeof(returnData)=="object"){
                var datas = new Array();
                if(returnData["folder"]!=null){
                    datas = returnData["folder"];
                    appendFloder(node,datas);//如果存在子文件夹，则将文件夹下面的子文件夹追加在后面
                }else{
                    datas = returnData["img"];
                    showIMG(datas);//加载图片
                }
            }
        });
    }
    return true;
}

/**
 * 追加文件夹 
 */
function appendFloder(node,datas){
    for(var i=0;i<datas.length;i++){
        $("#folderTree").tree("append",{
            parent:(node?node.target:null),
            data:[{
                text:datas[i]["name"],
                id:datas[i]["fullPath"]
            }
            ]
        });
    }
}


/**
 * 显示所有文件
 */
function showIMG(datas){
    if(datas.length==0){
        $("#imgShowDiv_workspace").html("<div style='padding:10px;text-align:center;'>没有图片</div>");
        return false;
    }
    var html="";
    for(var i=0;i<datas.length;i++){
        html+="<div style='padding:5px;float:left;'><img src='"
        +basePath+"userfiles/"+datas[i]["fullPath"]+
        "' onclick='getFileUrl(\""+basePath+"userfiles/"+datas[i]["fullPath"]+"\")' style='width:150px;height:120px;border:0px;cursor: pointer;'/><div style='height:30px;width:150px;text-align: center;'><input type='checkbox' name='imgPath' value='"
        +basePath+"userfiles/"+datas[i]["fullPath"]+"'/>"+datas[i]["name"]+"</div></div>";
    }
    $("#imgShowDiv_workspace").html(html);
    var imgs = $("#imgShowDiv_workspace img");
    imgs.each(function(i){//给所有的图片绑定右键事件
        imgs.eq(i).bind('contextmenu', function(e) {
            $('#imgMenu').menu('show', {
                left : e.pageX,
                top : e.pageY
            });
            img=this;
            return false;
        });
    });
    return true;
}

//上传文件
function uploadFile(){
    $.messager.confirm("提示","确认上传?",function(val){
        if(val){
            $('#uploadify').uploadify('upload','*');      
        }
    });
}

//移除所有文件
function removeFile(){
    $('#uploadify').uploadify('cancel','*');  
    files=0;    
    uploads=0;
    $("#uploadMess").html("");
}

//显示上传的窗体 
function showForm(){
    flashChecker();
    if(param1!=''&&param2!=''){//上传活动剪影时需在之前选择的活动文件夹下上传
        var index= parseInt(folder.indexOf("_"));//"_"后面的为活动标题
        var temp=folder.substring(index+1);
        if(temp!=param2){//通过检索以活动活动标题结尾的文件夹
            $.messager.alert("提示","请在正确的文件夹下上传!","warning");
            return;
        }
    }
    removeFile();//上传之前移除文件队列
    $("#uploadMess").html("");
    files=0;    
    uploads=0;
    $("#uploadDialog").dialog("open");
}
//检测flash是否安装
function flashChecker() {
    var hasFlash = 0;//是否安装了flash
    var isIE = /*@cc_on!@*/0;//是否IE浏览器
    if (isIE) {
        var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
        if (swf) {
            hasFlash = 1;
        }
    } else {
        if (navigator.plugins && navigator.plugins.length > 0) {
            var swf = navigator.plugins["Shockwave Flash"];
            if (swf) {
                hasFlash = 1;
            }
        }
    }
    if (!hasFlash) {
        $.messager.alert("提示", "浏览器未安装Flash插件,<span style='color:blue;'>上传图片</span>功能不可用！");
    }
}

//显示新建文件的窗口
function showNewFolderForm(){
    $("#newFolderDialog").dialog("open");
}

//提交新建文件夹的表单
function saveNewFolderForm(){
    $.messager.confirm("提示","确认上传?",function(val){
        if(val){
            var f = $("#newFolder").val();//填入的文件夹名为了检测，后面提交表单会自己带上此参数
            if(constants(f,"#")||constants(f,"%")){
                $.messager.alert("警告","文件夹名称中请不要包含\"#\"、\"%\"非法字符","warning");
                return false;
            }
            var action = encodeURI(basePath+"manage/SthdPicManageAction.jsp?mode=CREATENEWFOLDER&folder="+folder);//进行URL重写，避免IE产生错误
            $("#newFolderDialog_form").form("submit",{
                url:action,
                success:function(data){
                    var r = parseInt(data);//转化成整型，
                    closeForm("newFolderDialog");
                    if(isNaN(r)){
                        $.messager.alert("错误","系统出错","error");
                    }else{
                        if(r==1){
                            $("#folderTree").tree("append",{
                                parent:(selectNode?selectNode.target:null),
                                data:[{
                                    text:f,
                                    id:folder+"//"+f
                                }
                                ]
                            });
                            $.messager.alert("提示","文件夹创建成功","info");
                        }else{
                            $.messager.alert("错误","文件夹创建失败","error");
                        }
                    }
                }
            });
        }
    });
}

//关闭窗口
function closeForm(id){
    $("#"+id).dialog("close");
}

//显示大图
function showMaxImg(){
    $("#xmtpgl_lagger_img").attr("src",img.src);
    $("#xmtpgl_lagger_dialog").dialog("open");
}

//删除图片
function deleteImg(){
    var imgs = $("input[name='imgPath']:checked");
    if(imgs.length==0){
        $.messager.alert("警告","请先选择需要删除的文件","warning");
        return false;
    }
    $.messager.confirm("提示","确认删除图片",function(val){
        if(val){
            var jsonIMG = jsonIMGString(imgs,"path");
            $.post(basePath+"manage/SthdPicManageAction.jsp",{
                mode:"DELETE",
                jsonImg:jsonIMG
            },function(returnData,status){
                var res = parseInt(returnData);
                if(!isNaN(res)){
                    if(res==-1){
                        $.messager.alert("提示", "提交数据错误", "error");
                    }else{
                        $.messager.alert("错误", "成功删除"+res+"个文件", "info");
                        getFolder();
                    }
                }else{
                    $.messager.alert("错误", "系统出错", "error");
                }
            });
        }
    });
}

//拼装批量删除图片的的字符串
function jsonIMGString(datas,name){
    var str="[";
    for(var i=0;i<datas.length;i++){
        if(i<datas.length-1){
            str+="{\""+name+"\":\""+$(datas[i]).val()+"\"},";
        }else{
            str+="{\""+name+"\":\""+$(datas[i]).val()+"\"}";
        }
    }
    str+="]";
    return str;
} 

//重新载入图片
function reload(){
    
}

//全选
function selectALL(){
    $("#imgShowDiv_workspace input[type='checkbox']").attr("checked",true);
//alert(boxs.length);
}

function unselectALL(){
    $("#imgShowDiv_workspace input[type='checkbox']").attr("checked",false);
}
//以下是于ckeditor有关的图片操作
//回调函数
function getUrlParam( paramName ) {
    var reParam = new RegExp( '(?:[\?&]|&)' + paramName + '=([^&]+)', 'i' ) ;
    var match = window.location.search.match(reParam) ;
    return ( match && match.length > 1 ) ? match[ 1 ] : null ;
}
//得到选中的图片的路径
function getFileUrl(src){
    if(mark=='ckeditor'){
        var funcNum = getUrlParam( 'CKEditorFuncNum' );
        window.opener.CKEDITOR.tools.callFunction( funcNum, src );
        window.close();
    }
}