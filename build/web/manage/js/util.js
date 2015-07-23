/* 
 * 通用工具类
 * author 张浩春 
 * time 2012-4-12 
 * 杨 121018后修改
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
 * 将多个combobox的数据一次从服务器加载的处理函数 该方法为不定参数
 * options 为不定参， 在使用的时候至少包含两个实参，第一个为请求后端得到的所有combobox的JSON数据集合，后面的为每个combobox的属性数组
 *   
 *  loadComboboxData(datas,new Array("fwpgxx_pgryid_list","Gzry","gzryid","xm"),...);
 *  datas 请求后端返回数据  必选参数
 *  combobox属性数组是字符串型数组元素为：
 * 0  id combobox的ID  必选参数
 * 1  name 对应combobx的数据在JSON中的名称 必选参数
 * 2  valueField combobox的valueField  必选参数
 * 3  textField combobox的textField 必选参数
 * 4  selectFirst 是否默认选中第一个 可选参数
 * 5  editable 该combobox是否可编辑  可选参数
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
            $("#"+opts[0]).css("width","100px");
            if(select){//是否选中第一个，如果不写参数，默认选中第一个
                if(data[opts[1]]!=null&&data[opts[1]].length>0){
                    $("#"+opts[0]).combobox("setValue",data[opts[1]][0][opts[2]]);
                }
            }
        }
    }
}
/**
 *请求一个combobox,第四个参数是请求datagrid数据的筛选条件字段名，
 */
function ajaxCoboxone(targetid,mode,valuefield,textField,type){
    $("#"+targetid).combobox({
        url:path+'?mode='+mode,
        valueField:valuefield,
        textField:textField,
        onChange:function(n,c){
            loadData(type,n)
        }
    });
}
/**
* 请求一个combobox后，根据所选值请求下一个关联combobox
*/
function ajaxCobox(targetid1,mode1,valuefield1,textField1,targetid2,mode2,valuefield2,textField2,guanlian){
    $("#"+targetid1).combobox({
        url:path+'?mode='+mode1,
        valueField:valuefield1,
        textField:textField1,
        onChange:function(n,c){
            $("#"+targetid2).combobox({
                url:path+'?mode='+mode2+"&"+guanlian+"="+n,
                valueField:valuefield2,
                textField:textField2
            })
        }
    }); 
}
//判空函数
function empty(v){
    switch (typeof v){
        case 'undefined' :
            return true;
        case 'string' :
            if(v.replace(/^\s+|\s+$/g, '').length === 0) return true;
            break;
        case 'number' :
            if(0 === v) return true;
            break;
        case 'object' :
            if(null === v) return true;
            if(undefined !== v.length && v.length===0) return true;
            for(var k in v){
                return false;
            }
            return true;
            break;
    }
    return false; 
}

