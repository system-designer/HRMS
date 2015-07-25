function RefreshImage(){//验证码的刷新！
    var el =document.getElementById("Image1");
    el.src=el.src+'?';//这个特别重要
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

//去掉字符串前后空格
function trim(str){
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
