/* 
 * 通用工具类
 * author 张浩春
 * time 2012-4-12 16:00
 * and open the template in the editor.
 */


//格式化easyui-datebox的时间格式
$(function(){
    var boxs =  $(".easyui-datebox");
    boxs.each(function(i){
        boxs.eq(i).datebox({
            formatter:function(date){
                var year = (date.getFullYear()+"").length==1?"0"+date.getFullYear():date.getFullYear();
                var month = (date.getMonth()+1)<10?"0"+(date.getMonth()+1):(date.getMonth()+1);
                var day = date.getDate()<10?"0"+date.getDate():date.getDate();
                return year+"-"+month+"-"+day;
            }
        });
    });
});

/**
 * 用来将多选时datagrid中的ID拼接为合法的JSON格式字符串
 */
function jsonString(selectRows,attrName,name){
    var str="[";
    for(var i=0;i<selectRows.length;i++){
        if(i<selectRows.length-1){
            str+="{\""+name+"\":"+selectRows[i][attrName]+"},";
        }else{
            str+="{\""+name+"\":"+selectRows[i][attrName]+"}";
        }
    }
    str+="]";
    return str;
} 

/**
 * 重新加载combobx中的数据
 * param id  combobx的ID
 * param url 重新加载时的URL 
 */
function reloadData(id,url){
    $("#"+id).combobox("reload",url);
}

/**
 * 将多个combobox的数据一次从服务器加载的处理函数 该方法为不定参数
 * param options 为形参 在使用的时候至少包含两个实参，第一个为JSON数据，后面的为每个combobox的属性数组
 *   data ajax加载成功的返回数据  必选参数
 * 0  id combobox的ID  必选参数
 * 1  name 对应combobx的数据在JSON中的名称 必选参数
 * 2  valueField combobox的valueField  必选参数
 * 3 textField combobox的textField 必选参数
 * 5 editable 该combobox是否可编辑  可选参数
 * 4 selectFirst 是否默认选中第一个 可选参数
 */

function loadComboboxData(options){
    var paramLen = arguments.length;//获得实参的个数
    var data = new Array();
    data = arguments[0];//获得JSON数据
    var opts = new Array();
    for(var i=1;i<paramLen;i++){//从第一个开始遍历所有的combobox
        opts = arguments[i];
        if(data[opts[1]]!=null){//避免因为数据不存在，而产生错误
            var edit = false;
            var select = true;
            if(opts[5]!=null){//是否可编辑 如果不写参数，则默认不可编辑
                edit = opts[5];
            }
            if(opts[4]!=null&&!opts[4]){
                select = false
            }
            $("#"+opts[0]).combobox({//为每个combobox加载数据
                data:data[opts[1]],
                valueField:opts[2],
                textField:opts[3],
                editable:edit        
            }); 
            if(select){//是否选中第一个，如果不写参数，默认选中第一个
                if(data[opts[1]]!=null&&data[opts[1]].length>0){
                    $("#"+opts[0]).combobox("setValue",data[opts[1]][0][opts[2]]);
                }
            }
        }
    }
}

$.extend($.fn.validatebox.defaults.rules, {   
    integer: {   
        validator: function(value, param){   
            var reg=/^[0-9]\d*$/;
            return reg.test(value);
        },   
        message: '只能输入数字！'  
    }   
});  

var validateType = new Array(".jpg",".png",".gif",".bmp",".tif",".pcx",".tga");
$.extend($.fn.validatebox.defaults.rules, {   
    imgFile: {   
        validator: function(value, param){  
            var begin = -1;
            begin = value.lastIndexOf(".");
            var type = value.substring(begin);
            return validate(type);				    
        },   
        message: '只能上传格式为：.png、.jpg、.gif、.bmp、.tif、.pcx、.tga的图片文件'  
    }   
});  
//只能输入数字的validate validateType
$.extend($.fn.validatebox.defaults.rules, {   
    num: {   
        validator: function(value, param){  
            var reg=/\\d+.\\d+/;
            return reg.test(value);			    
        },   
        message: '只能输入数字'  
    }   
});  
//检测文件是否为图片文件
function validate(type){
    var validate = false;
    for(var i=0;i<validateType.lenght;i++){
        if(validateType[i].toLowerCase()==type){
            validate = true;
            break;
        }
    }
    return validate;
}

//^([0-9]+|[0-9]+\.{0,1}[0-9]{1,9})$
//只能输入正浮点数
$.extend($.fn.validatebox.defaults.rules, {   
    Float: {   
        validator: function(value, param){  
            var reg=/^([0-9]+|[0-9]+\.{0,1}[0-9]{1,9})$/;
            return reg.test(value);			    
        },   
        message: '只能输入小数或整数'  
    }   
}); 	

//防止用户空格时，检测结果为不为空
$.extend($.fn.validatebox.defaults.rules, {   
    notEmpty: {   
        validator: function(value, param){  
            return $.trim(value).length>0;			    
        },   
        message: '该项为必填项'  
    }   
}); 
//图片未找到时所作的处理
function showOtherPic(obj){
    obj.src=path+"manage/image/nopic.jpg";
    return;
}
