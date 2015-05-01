
var folder = "";
var gzdwid = 0;
var files = 0;//文件上传个数
var uploads = 0;//已上传的文件个数
var xxtplx="";//图片类型
var jsessionid="";
$(function() {
    loadCombobox("");
});
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
    } else {
        var $orgz = $('#orgz');
        var orgzc = $orgz.combobox('getValue');
        var orgz = $orgz.combobox('getText');
        uploadifyInit(orgzc,orgz);
        showForm();
    }
}
//初始化uploadify
function uploadifyInit(orgzc,orgz) {
    $("#uploadify").uploadify({
        'swf': basePath + 'js/jquery-uploadify-3.1/uploadify.swf',
        'buttonText': '选择文件',
        'fileTypeExts': '*.png;*.gif;*.jpg;*.bmp;*.jpeg',
        'fileTypeDesc': '图片文件',
        'cancelImage': basePath + 'js/jquery-uploadify-3.1/uploadify-cancel.png', //队列表中每个文件的取消叉叉
        'queueID': 'fileQueue',
        'multi': true,
        'auto': false, //选中自动提交
        'height': 20,
        'width': 80,
        'onUploadStart': function(file) {//当每个文件上传的时候会触发的事件
            uploads++;
            $("#uploadify").uploadify('settings', 'uploader', basePath + "manage/xtwh/RctpglAction.jsp?mode=UPLOAD&orgzc=" + orgzc+"&orgz="+orgz);//设定文件的上传路径
            $("#uploadMess").html("已上传<span style='color: blue;font-size: 22px;'>" + files + "</span>中的<span style='color: blue;font-size: 22px;'>" + uploads + "</span>个文件...");//每一个文件上传提示
        },
        'onSelect': function(file) {//文件命中含有非法字符时触发
            if (constants(file.name, "#") || constants(file.name, "%")) {
                $.messager.alert("警告", "文件名为 " + file.name + " 的文件含有非法字符\"#\"或\"%\"，将被移出上传队列", "warning", function() {
                    $('#uploadify').uploadify('cancel', file.id);
                });

            } else {
                files++;
                $("#uploadMess").html("<span style='color: blue;font-size: 22px;'>" + files + "</span>个文件等待上传.");
            }
        },
        'onQueueComplete': function(queueData) {//当文件队列上传成功后
            $.messager.alert("提示", "已经成功上传" + uploads + "个文件", '', function() {
                files = 0;
                uploads = 0;
                $("#uploadMess").html("");
                reloadImg();
            });
        }
    });
}
//显示上传的窗体
function showForm() {
    removeFile();//上传之前移除文件队列
    $("#uploadMess").html("");
    files = 0;
    uploads = 0;
    $("#uploadDialog").dialog("open");
}
//加载combobox数据
function loadCombobox(parameter) {
    $.post(basePath + "manage/xtwh/RctpglAction.jsp", {
        mode: 'LOADCOMBOBOX'
    }, function(retData, status) {
        $('#orgz').combobox('loadData',retData.orgzs);
    });
}
//加载图片路径
function reloadImg() {
    var $orgz = $('#orgz');
    var orgzVal = $orgz.combobox('getValue');
    var orgzText = $orgz.combobox('getText');
    if(empty(orgzVal)||empty(orgzText)){
        $.messager.alert('提示', '请先选择工作单位!!!');
        $("#imgShowDiv_workspace").html('');
        return false;
    }
    $.ajax({
        url: basePath + "manage/xtwh/RctpglAction.jsp",
        type: "post",
        data: {
            mode: "SHOWIMGS",
            orgzVal:orgzVal,
            orgzText:orgzText
        },
        dataType: "json",
        success: function(data, status) {
            folder = data.folder;
            showIMG(data.imgs);
        }
    });
    return false;
}
//显示图片
function showIMG(datas) {
    if (datas.length <= 0) {
        $("#imgShowDiv_workspace").html("<div style='padding:10px;text-align:center;'><img src='" + basePath + "manage/img/sorry.gif'/>该工作单位还未上传图片。</div>");
        return false;
    }
    var html = "";
    for (var i = 0; i < datas.length; i++) {
        var name = datas[i]["name"];
        name = name.substring(0, name.lastIndexOf(".")).toString();
        html += "<div style='padding:5px;float:left;'><img src='"
        + basePath + "userfiles/" + datas[i]["path"] +
        "' style='width:150px;height:120px;border:0px;cursor: pointer;' onclick='showLaggerImg(this.src)'/><div style='height:30px;width:150px;text-align: center;'><label><input type='checkbox' name='imgPath' value='"
        + basePath + "userfiles/" + datas[i]["path"] + "'/>" + datas[i]["name"] + "</label></div></div>";
    }
    $("#imgShowDiv_workspace").html(html);
    return false;
}
//显示大图
function showLaggerImg(src) {
    $("#xmtpgl_lagger_img").attr("src", src);
    $("#xmtpgl_lagger_dialog").dialog("open");
}
//全选
function selectAll() {
    $("#imgShowDiv_workspace input[type='checkbox']").attr("checked", true);
    return false;
}
//取消选择
function unselectAll() {
    $("#imgShowDiv_workspace input[type='checkbox']").attr("checked", false);
    return false;
}
//上传文件
function uploadFile() {
    $.messager.confirm("提示", "确认上传?", function(val) {
        if (val) {
            $('#uploadify').uploadify('upload', '*');
        } else {

        }
    });
}
//移除所有文件
function removeFile() {
    $('#uploadify').uploadify('cancel', '*');
    files = 0;
    uploads = 0;
    $("#uploadMess").html("");
}
/**
 *检测一个字符串中是否存在另一个字符串
 */
function constants(str, tar) {
    if (typeof(str) == "undefined" || typeof(tar) == "undefined") {
        return false;
    }
    var len = tar.length;
    for (var i = 0; i < str.length; ) {
        if (str.substring(i, i + len) == tar) {
            return true;
        }
        i += len;
    }
    return false;
}
//删除图片
function deleteImg() {
    var $imgs = $("#imgShowDiv_workspace input[name='imgPath']:checked");
    if ($imgs.length == 0) {
        $.messager.alert("警告", "请先选择需要删除的文件", "warning");
        return false;
    }
    $.messager.confirm("提示", "确认删除图片", function(val) {
        if (val) {
            var jsonIMG = jsonIMGString($imgs, "path");
            $.post(basePath + "manage/xtwh/RctpglAction.jsp", {
                mode: "DELETE",
                jsonIMG: jsonIMG
            }, function(returnData, status) {
                var res = parseInt(returnData);
                if (!isNaN(res)) {//是否是数字值
                    if (res == -1) {
                        $.messager.alert("提示", "提交数据错误", "error");
                    } else {
                        $.messager.alert("提示", "成功删除" + res + "个文件");
                        reloadImg();
                    }
                } else {
                    $.messager.alert("错误", "系统出错", "error");
                }
            });
        }
    });
    return false;
}
//拼装批量删除图片的的字符串
function jsonIMGString(datas, name) {
    var str = "[";
    for (var i = 0; i < datas.length; i++) {
        if (i < datas.length - 1) {
            str += "{\"" + name + "\":\"" + $(datas[i]).val() + "\"},";
        } else {
            str += "{\"" + name + "\":\"" + $(datas[i]).val() + "\"}";
        }
    }
    str += "]";
    return str;
}
function empty(v){
    switch (typeof v){
        case 'undefined' :
            return true;
            break;
        case 'string' :
            return v.replace(/(^\s*)|(\s*$)/g, '').length == 0;
            break;
        case 'object' :
            if(null === v) return true;
            if(undefined !== v.length && v.length==0) return true;
            for(var k in v){
                return false;
            }
            return true;
            break;
    }
    return false; 
}




