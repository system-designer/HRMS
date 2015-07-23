/*
 * 页面加载遮罩效果,经过Closure Compiler压缩
 * @param window.parent.tabWidth window.parent.tabHeight path
 * @author zc
 */
window.onload=function(){var a=document.getElementById("loading");a.parentNode.removeChild(a)};document.write("<div id='loading' style='position:absolute;left:0px;top:0px;width:"+window.parent.tabWidth+"px;height:"+window.parent.tabHeight+"px;background:#EBEBEB;opacity:0.8;filter:alpha(opacity=80);'><div style='text-align: left;border: 2px solid #D3D3D3;position:absolute;cursor1:wait;display: block;left:"+(window.parent.tabWidth-170)/2+"px;top:"+((window.parent.tabHeight-16)/2-100)+"px;width:auto;height:16px;padding:12px 5px 10px 30px;background:url("+path+"/js/jquery-easyui-1.3.2/themes/default/images/loading.gif) no-repeat scroll 5px center #FFFFFF;color:#000000;font-size: 12px'>\u9875\u9762\u6b63\u5728\u52a0\u8f7d\uff0c\u8bf7\u7a0d\u7b49\u3002\u3002\u3002</div></div>");


