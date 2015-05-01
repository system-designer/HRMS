var comboxsData="";
var selectedUser="";
$(function(){
    dialogInit($('#yhwh_crud_dialog'),window.parent.tabHeight,window.parent.tabWidth);
    loadCombobox(['#prop_search','#level_search','#dept_search','#trade_search'],['#prop_crud','#level_crud','#dept_crud','#trade_crud']);
    loadYhwhList({
        keepSelect:true
    });
});
function loadCombobox(arg0,arg1){
    var args2Arr =arg0.concat(arg1);
    $.ajax({
        type: 'POST',
        url: path+'manage/xtwh/YhwhAction.jsp',
        dataType: "json",
        data:{
            mode:'LOADCOMBOBOX'
        },
        async:false,
        success:function (data, status){
            comboxsData = data;
            for(var i=0;!empty(args2Arr)&&i<args2Arr.length;i++){
                var $combobox =$(args2Arr[i]);
                var name=$combobox.attr('i-list');
                if(!empty(data[name])){
                    $combobox.combobox('loadData',data[name]);
                }
            }
        }
    });
    var str0=arg0.join(",");
    $(str0).each(function(){
        $(this).combobox({
            onChange:function(newVal,oldVal){
                var keep = empty(newVal)?true:false;
                loadYhwhList({
                    comboxids:arg0,
                    keepSelect:keep
                });
            }
        })
    });
    var str1 = arg1.join(",");
    $(str1).each(function(){
        $(this).combobox({
            onChange:function(newVal,oldVal){
                loadGzdwCombobox(arg1);
            }
        })
    });
}

function loadGzdwCombobox(arg){
    var query = {};
    for(var i=0;!empty(arg)&&i<arg.length;i++){
        var str =arg[i];
        query[str.substr(1)]=$(str).combobox('getValue');
    }
    query['mode']='LOADGZDWCOMBOBOX';
    $.ajax({
        asyn:false,
        type: 'POST',
        url: path+'manage/xtwh/YhwhAction.jsp',
        dataType: "json",
        data:query,
        success:function (data, status){
            $('#orgz_crud').combobox('clear').combobox('loadData',data[$('#orgz_crud').attr('i-list')]);
            if(selectedUser!=""){
                $('#orgz_crud').combobox('setValue',parseInt(selectedUser.orgzc));
            }
        }
    });
}
function loadYhwhList(options){
    var query = {};
    var opts ={};
    if(!empty(options)){
        opts= $.extend({
            comboxids:[],
            keepSelect:false
        },options|| {});
        var arg= opts.comboxids;
        for(var i=0;!empty(arg)&&i<arg.length;i++){
            var str =arg[i];
            query[str.substr(1)]=$(str).combobox('getValue');
        }
    }
    query['mode']='LOADLIST';
    var rows='';
    var $datagrid = $('#yhwh_list');
    $datagrid.datagrid({
        url:path+'manage/xtwh/YhwhAction.jsp',
        queryParams:query,
        idField:'id',
        remoteSort:false,
        columns: [[
        {
            align: "center",
            field: 'id',
            checkbox: true
        },{
            field: 'orgz',
            title: '用户名称',
            width: 200
        },{
            field: 'name',
            title: '姓名',
            width: 200
        },{
            field: 'uname',
            title: '用户名',
            width: 100
        },{
            field: 'utype',
            title: '用户类型',
            width: 200,
            formatter: typeFormat
        },{
            field: "action",
            title: "相关操作",
            width: 200,
            align: "center",
            formatter: function(value, rowData, rowIndex) {
                return "<a href='javascript:;' onclick='detail("+rowIndex+""+")'>查看用户详情</a>";
            }
        }
        ]],
        onBeforeLoad:function(param){
            var keepSelect =$datagrid.data('keepSelect');
            opts.keepSelect = empty(keepSelect)?opts.keepSelect:keepSelect;
            if(opts.keepSelect){
                $datagrid.data('keepSelect',undefined);
                rows = $(this).datagrid('getSelections');
            }else{
                $(this).datagrid('clearSelections');
            }
            return true;
        },
        onLoadSuccess:function(data){
            if(!empty(rows)){
                for(var i=0;i<rows.length;i++){
                    $(this).datagrid('selectRecord',rows[i]['id']);
                }
            }
        }
    });
}
// 添加用户信息
function add(){
    CRUD(
        setDialogText('添加用户信息',['添加',"icon-ok",function(){
            submitFrom(path+'manage/xtwh/YhwhAction.jsp?mode=ADD',function(){
                $.messager.confirm('添加成功', "添加成功!!!是否继续添加？", function(r){
                    if (r){
                        clearForm();
                        $('#yhwh_list').datagrid('reload');
                        $('#yhwh_crud_dialog').dialog('open');
                    }else{
                        closeDialog();
                    }
                });
            });
        }],['取消',"icon-cancel",function(){
            closeDialog();
        }]),//dialog参数
        false,//不对form进行设值
        '',
        false//不禁用form
        );
}
function update(){
    var rows = $('#yhwh_list').datagrid('getSelections');
    if(!rowsChcek(rows,'修改')){
        return;
    }
    CRUD(setDialogText('修改用户信息',['修改',"icon-ok",function(){
        submitFrom(path+'manage/xtwh/YhwhAction.jsp?mode=UPDATE&id='+rows[0].id,function(){
            $.messager.alert('修改成功', '修改成功!!!','info',function(){
                closeDialog();
            });
        });
    }],['取消',"icon-cancel",function(){
        closeDialog();
    }]),true,rows[0],false);
}
function del(){
    var rows = $('#yhwh_list').datagrid('getSelections');
    if(!rowsChcek(rows,'删除')){
        return;
    }
    CRUD(
        setDialogText('删除用户信息',['确认删除',"icon-recyclebin",function(){
            submitFrom(path+'manage/xtwh/YhwhAction.jsp?mode=DELETE&id='+rows[0].id,function(){
                $.messager.alert('删除成功', '删除成功!!!','info',function(){
                    closeDialog(false);
                });
            });
        }],['关闭窗口',"icon-cancel",function(){
            closeDialog();
        }]),
        true,//对form进行设值
        rows[0],
        true//禁用form
        );
}
function detail(rowIndex){
    CRUD(setDialogText('用户详细信息',['关闭窗口',"icon-cancel",function(){
        closeDialog();
    }]),true,$('#yhwh_list').datagrid('getIndexRow',rowIndex),true)
}
/*
 * 增删改查核心方法
 * @param $dialog dialog的jquery对象
 * @param doSetFromVal 是否对form进行设值
 * @param rowData datagrid选中行的数据
 * @param doDisableForm 是否禁用form表单
 * @param formRegisterAction 注册form表单提交事件,传入的是方法
 */
function CRUD($dialog,doSetFromVal,rowData,doDisableForm,formRegisterAction){
    if(doSetFromVal){
        selectedUser=rowData;
        setFromValue(rowData);
    }
    if(doDisableForm){
        disableForm();
    }
    if(!empty(formRegisterAction)&&(typeof formRegisterAction)==='function'){
        formRegisterAction();
    }
    $dialog.dialog('open');
}
function submitFrom(url,altFuct){
    var $form=$('#form_crud');
    $form.attr('action',url);
    $form.form('submit',{
        success:function(data){
            var jsonObj = parseJson(data);
            if(jsonObj.update){
                if(altFuct==undefined){
                }
                else if(typeof altFuct=='function'){
                    altFuct();
                }
            }else{
                $.messager.alert(jsonObj.mode+'失败', jsonObj.notice,'info');
            }
        }
    });
}
/**
 * 设置dialog标题和buttons
 * @param options 不定参数
 ***/
function setDialogText(options){
    var data = arguments;
    var paramLen = data.length;//获得实参的个数
    var buttons = [];
    var $dialog = $('#yhwh_crud_dialog');
    $dialog.dialog({
        title:arguments[0]
    });
    enableFrom();
    for(var i=1;i<paramLen;i++){
        var opts = data[i];
        buttons.push({
            text:opts[0],
            iconCls:opts[1],
            handler:function(){
                var thisIndex = $(this).index();
                var callback = data[thisIndex+1][2];
                if(!empty(callback)&&(typeof callback)==='function'){
                    callback();
                }
            }
        });
    }
    $('#yhwh_crud_dialog > div.dialog-button').remove();
    if(!empty(buttons)){
        $dialog.dialog({
            buttons:buttons
        });
    }
    return $dialog;
}
/** 以下是easyui常规方法 **/
function disableForm(){
    $('#prop_crud').combobox('disable');
    $('#level_crud').combobox('disable');
    $('#dept_crud').combobox('disable');
    $('#trade_crud').combobox('disable');
    $('#orgz_crud').combobox('disable');
    $('#uname_crud').attr("readonly",true);
    $('#phone_crud').attr("readonly",true);
    $('#name_crud').attr("readonly",true);
    $('#ulevel_crud').find("input[type='radio']").attr("disabled",true);
    $('#utype_crud').find("input[type='radio']").attr("disabled",true);
}
function enableFrom(){
    $('#prop_crud').combobox('enable');
    $('#level_crud').combobox('enable');
    $('#dept_crud').combobox('enable');
    $('#trade_crud').combobox('enable');
    $('#orgz_crud').combobox('enable');
    
    
    $('#uname_crud').removeAttr("readonly");
    $('#phone_crud').removeAttr("readonly");
    $('#name_crud').removeAttr("readonly");
    $('#ulevel_crud').find("input[type='radio']").removeAttr("disabled");
    $('#utype_crud').find("input[type='radio']").removeAttr("disabled");
}
function clearForm(){
    selectedUser="";
    clearSelect();
    clearInput();
}
function clearInput(){
    $('#uname_crud').val('');
    $('#phone_crud').val('');
    $('#name_crud').val('');
    $('#utype_crud').find("input[type='radio']").eq(0).attr("checked", true).siblings().removeAttr("checked");
}
function clearSelect(){
    $('#prop_crud').combobox('select','');
    $('#level_crud').combobox('select','');
    $('#dept_crud').combobox('select','');
    $('#trade_crud').combobox('select','');
    $('#orgz_crud').combobox('select','');
}
function setFromValue(rowData){
    $('#prop_crud').combobox('setValue',rowData.propc);
    $('#level_crud').combobox('setValue',rowData.levelc);
    $('#dept_crud').combobox('setValue',rowData.deptc);
    $('#trade_crud').combobox('setValue',rowData.tradec);
    $('#orgz_crud').combobox('setValue',parseInt(rowData.orgzc));
    $('#uname_crud').val(rowData.uname);
    $('#phone_crud').val(rowData.phone);
    $('#name_crud').val(rowData.name);
    $('#ulevel_crud').find("input[type='radio']").each(function(){
        if($(this).val()===rowData.ulevelc){
            $(this).attr("checked",true).css('background', 'red');
        } 
    });
    $('#utype_crud').find("input[type='radio']").each(function(){
        if($(this).val()===rowData.utypec){
            $(this).attr("checked",true).css('background', 'red');
        } 
    });
}
function dialogInit($dialog,fHeight,fWidth){
    var width = $dialog.dialog('options').width;
    var height = $dialog.dialog('options').height;
    $dialog.dialog({
        onClose:function(){
            var $datagrid = $('#yhwh_list');
            clearForm();
            $datagrid.datagrid('reload');
        },
        left:(fWidth-width)/2,
        top:(fHeight-height)/2
    });
}
function closeDialog(keepSelect){
    if(keepSelect!=undefined)$("#yhwh_list").data('keepSelect', keepSelect);
    $('#yhwh_crud_dialog').dialog('close');
}
function rowsChcek(rows,msg){
    if (rows.length <= 0) {
        $.messager.alert('提示', '请先选择要'+msg+'的数据！', 'warning');
        return false;
    } else if (rows.length > 1) {
        $.messager.alert('提示', '只能选择一行数据！', 'warning');
        return false;
    }
    return true;
}
/** 以下是工具方法 **/
//空值判断
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
}
//string->json,来自json.js
function parseJson(text) {
    var result;
    var regx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;
    function walk(holder, key) {
        var k, v, value = holder[key];
        if (value && typeof value === 'object') {
            for (k in value) {
                if (Object.prototype.hasOwnProperty.call(value, k)) {
                    v = walk(value, k);
                    if (v !== undefined) {
                        value[k] = v;
                    } else {
                        delete value[k];
                    }
                }
            }
        }
    }
    text = String(text);
    regx.lastIndex = 0;
    if (regx.test(text)) {
        text = text.replace(regx, function (a) {
            return '\\u' +('0000' + a.charCodeAt(0).toString(16)).slice(-4);
        });
    }
    if (/^[\],:{}\s]*$/.test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@').replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']').replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {
        result = eval('(' + text + ')');
        return result;
    }
    throw new SyntaxError('JSON.parse');
}
/*********以下是自定义的easyui扩展方法**********/
$.extend($.fn.combobox.methods, {
    selectedIndex: function (jq, index) {
        if (!index){
            index = 0;
        }
        var data = $(jq).combobox('options').data;
        if(empty(data)){
            data = $(jq).combobox('getData');
        }
        var vf = $(jq).combobox('options').valueField;
        $(jq).combobox('setValue', data[index][vf]);
    }
});
$.extend($.fn.datagrid.methods, {
    getIndexRow: function (jq, index) {
        if (!index){
            index = 0;
        }
        var data = $(jq).datagrid('options').data;
        if(empty(data)){
            data = $(jq).datagrid('getData');
        }
        return data.rows[index];
    }
});
